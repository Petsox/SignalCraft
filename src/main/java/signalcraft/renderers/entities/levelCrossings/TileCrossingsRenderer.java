package signalcraft.renderers.entities.levelCrossings;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.levelCrossings.IAnglesAddable;
import signalcraft.entities.levelCrossings.TileLevelCrossing;
import signalcraft.entities.levelCrossings.sssr.TileSSSRHead;
import signalcraft.entities.levelCrossings.vud.TileVUD;
import signalcraft.models.levelCrossing.ILevelCrossingModel;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.LampFade;

public class TileCrossingsRenderer extends TileEntitySpecialRenderer {
    private final ILevelCrossingModel modelCross;

    public TileCrossingsRenderer(final ILevelCrossingModel model) {
        this.modelCross = model;
    }

    public void renderTileEntityAt(final TileEntity tileE, final double x, final double y, final double z, final float tick) {
        final Block block = tileE.getBlockType();
        final TileLevelCrossing thisCrossingTile = (TileLevelCrossing) tileE;
        final int i1 = 15728880;
        final int j1 = i1 % 65536;
        final int k1 = i1 / 65536;
        int meta;
        if (tileE.getWorldObj() == null) {
            meta = 0;
        } else {
            meta = tileE.getBlockMetadata();
            if (block != null && meta == 0) {
                meta = tileE.getBlockMetadata();
            }
        }

        GL11.glPushMatrix(); // A
        GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);
        GL11.glRotatef(-meta * 360 / 16.0f, 0.0f, 1.0f, 0.0f);
        // Head-only crossing units (AZD71/AZD97/SSSR/SSSRSingle "Head" variants) have no base
        // pole of their own and are usually mounted onto other structures - yOffset lets them be
        // fine-tuned vertically. Applied in unscaled world blocks, before getScale(), so it stays
        // consistent regardless of the model's scale setting.
        // Full AZD71/AZD97/SSSR/SSSRSingle units use it too, but only their head moves - their pole stays on
        // the ground by rendering itself shifted back down (see ILevelCrossingModel.setFixedPoleOffset).
        GL11.glTranslatef(0f, thisCrossingTile.getYOffset(), 0f);

        // Scaling and translation adjustments based on tile type
        float baseScale = thisCrossingTile instanceof TileVUD ? 1.0f : 1.5f;
        GL11.glScalef(baseScale, baseScale, baseScale);
        if (thisCrossingTile instanceof TileSSSRHead) GL11.glTranslatef(0f, 0.15f, -0.049f);

        GL11.glScalef(thisCrossingTile.getScale(), thisCrossingTile.getScale(), thisCrossingTile.getScale());
        float totalScale = baseScale * thisCrossingTile.getScale();
        this.modelCross.setFixedPoleOffset(totalScale != 0f ? thisCrossingTile.getYOffset() / totalScale : 0f);

        LampFade fade = thisCrossingTile.getLampFade();
        long dt = fade.beginFrame(Minecraft.getSystemTime());

        // The tile's real ambient lighting, captured once so renderHead can restore it before each
        // angle's static geometry - the lamp block further down force-overrides the lightmap to
        // full-bright and never puts it back, so on the 2nd+ iteration of the angle loop below,
        // renderSloup/renderStozar/renderVystraznik would otherwise inherit the previous angle's
        // full-bright lamp lighting instead of this block's actual light level.
        int ambientBrightness = thisCrossingTile.getWorldObj().getLightBrightnessForSkyBlocks(thisCrossingTile.xCoord, thisCrossingTile.yCoord, thisCrossingTile.zCoord, 0);
        int aj = ambientBrightness % 65536;
        int ak = ambientBrightness / 65536;

        if (thisCrossingTile instanceof IAnglesAddable){
            GL11.glTranslatef(0f, 0.2935f, 0f);

            int[] angles = ((IAnglesAddable) thisCrossingTile).getAngles();
            for (int i = 0; i < angles.length; i++) {
                // Each angle is an absolute world-facing rotation captured from the player's yaw
                // when it was added (see ItemWrench), not an offset from the previous one - push/pop
                // so every head rotates fresh from the base orientation instead of compounding onto
                // whatever the prior iteration left behind. Without this, added heads drift further
                // off their intended direction with each one, often ending up overlapping an earlier
                // head - multiple additively-blended lamps stacked on the same spot is what reads as
                // the added signals "glowing".
                GL11.glPushMatrix();
                GL11.glRotatef(angles[i], 0, 1, 0);
                renderHead(thisCrossingTile, j1, k1, aj, ak, i + 1, i == 0, dt);
                GL11.glPopMatrix();
            }

        } else {
            this.modelCross.renderZaklad(thisCrossingTile.getLightPos().Pos, thisCrossingTile.hasPozLight(), thisCrossingTile.isLightCoverShort());
            renderHead(thisCrossingTile, j1, k1, aj, ak, 1, true, dt);
        }

        GL11.glPopMatrix(); // pop A
        // the model instance is shared with every other tile/item render of this type
        this.modelCross.setFixedPoleOffset(0f);
    }

    private void renderHead(TileLevelCrossing thisCrossingTile, int j1, int k1, int aj, int ak, int angleIndex, boolean renderKrizAndCedule, long dt) {
        // Always start from this block's real ambient lighting rather than trusting whatever the
        // previous angle iteration's lamp rendering left the lightmap set to (see the aj/ak comment
        // in renderTileEntityAt).
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, aj, ak);

        this.modelCross.renderSloup(thisCrossingTile.getDistFromSloup().Dist, thisCrossingTile.hasZebrik(), thisCrossingTile.isCedule(), thisCrossingTile.hasKrizNaStozaru(), thisCrossingTile.hasKriz());
        this.modelCross.renderStozar(thisCrossingTile.getDistFromSloup().Dist, thisCrossingTile.hasPruhy());
        this.modelCross.renderStozarDelsi(thisCrossingTile.getStozarDelsiCount());

        if (renderKrizAndCedule) {
            if (thisCrossingTile.hasKriz()) {
                float[] krizPivot = this.modelCross.getKrizPivotOffset(thisCrossingTile.getDistFromSloup(), thisCrossingTile.hasKrizNaStozaru());
                if (krizPivot == null) krizPivot = getVystraznikPivot(thisCrossingTile.getDistFromSloup());

                GL11.glPushMatrix(); // K
                rotateAroundPivot(krizPivot, thisCrossingTile.getHeadRot());
                this.modelCross.renderKriz(thisCrossingTile.getDistFromSloup().Dist, thisCrossingTile.isKrizJedno(), thisCrossingTile.hasKrizNaStozaru(), thisCrossingTile.isSlovak(), thisCrossingTile.isKrizReflex(), thisCrossingTile.isKrizVelky());
                GL11.glPopMatrix(); // pop K
            }

            float[] pozorVlakPivot = this.modelCross.getPozorVlakPivotOffset(thisCrossingTile.getDistFromSloup());
            if (pozorVlakPivot == null) pozorVlakPivot = getVystraznikPivot(thisCrossingTile.getDistFromSloup());

            GL11.glPushMatrix(); // P
            rotateAroundPivot(pozorVlakPivot, thisCrossingTile.getHeadRot());
            this.modelCross.renderPozorVlak(thisCrossingTile.getDistFromSloup().Dist, thisCrossingTile.isCedule(), thisCrossingTile.hasPozLight());
            GL11.glPopMatrix(); // pop P
        }

        GL11.glPushMatrix(); // B
        rotateAroundPivot(getVystraznikPivot(thisCrossingTile.getDistFromSloup()), thisCrossingTile.getHeadRot());

        this.modelCross.renderVystraznik(thisCrossingTile.getDistFromSloup().Dist, thisCrossingTile.getLightPos().Pos, thisCrossingTile.hasPozLight(), thisCrossingTile.isPozLightShort(), thisCrossingTile.isLightCoverShort(), thisCrossingTile.isCedule());

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j1, k1);

        boolean active = thisCrossingTile.getIsActive();
        boolean lOn = active && thisCrossingTile.getBlinkCounter() <= thisCrossingTile.getSoundType().soundTimer;
        boolean rOn = active && !lOn;
        boolean pozOn = !active && thisCrossingTile.hasPozLight() && thisCrossingTile.usePozLight()
                && thisCrossingTile.getPozitBlinkCounter() < thisCrossingTile.getSoundType().pozitBlinkTimer / 2
                && thisCrossingTile.getPozLightDelayTimer() == 0;

        // While active, the L/R lamp that isn't currently lit idles at a dim glow instead
        // of going fully dark; once the crossing deactivates both drop to 0 and stop rendering.
        float lTarget = active ? (lOn ? 1.0f : LampFade.IDLE_BRIGHTNESS) : 0.0f;
        float rTarget = active ? (rOn ? 1.0f : LampFade.IDLE_BRIGHTNESS) : 0.0f;

        LampFade fade = thisCrossingTile.getLampFade();
        float lBrightness = fade.step("L" + angleIndex, lTarget, dt);
        float rBrightness = fade.step("R" + angleIndex, rTarget, dt);

        // LED poz lamps (isNewer, korona_poz_led_*) snap instantly instead of fading like the
        // incandescent variant - real LEDs don't have a filament to warm up or cool down.
        boolean pozFades = !thisCrossingTile.isNewer();
        float pozBrightness = pozFades ? fade.step("Poz" + angleIndex, pozOn, dt) : (pozOn ? 1.0f : 0.0f);

        // Plain alpha-test glColor4f, same as the rest of this renderer - no GL_BLEND/GL_LIGHTING
        // toggling here. That approach caused the crossing GUI's own background/textfields to break
        // (still unexplained), so IDLE_BRIGHTNESS is instead tuned to clear the alpha-test cutoff
        // with margin (see LampFade) rather than relying on blending to avoid the cutoff entirely.
        if (lBrightness > 0.0f) {
            GL11.glColor4f(2.0f * lBrightness, 2.0f * lBrightness, 2.0f * lBrightness, 2.0f * lBrightness);
            this.modelCross.renderSvetloL(thisCrossingTile.getDistFromSloup().Dist, thisCrossingTile.getLightPos().Pos, angleIndex, thisCrossingTile.doLightsAlter());
        }
        if (rBrightness > 0.0f) {
            GL11.glColor4f(2.0f * rBrightness, 2.0f * rBrightness, 2.0f * rBrightness, 2.0f * rBrightness);
            this.modelCross.renderSvetloR(thisCrossingTile.getDistFromSloup().Dist, thisCrossingTile.getLightPos().Pos, angleIndex, thisCrossingTile.doLightsAlter());
        }
        // A tile that has no poz light at all has no lens/bulb mesh to show - some model classes
        // (e.g. ModelCrossSSSR/ModelCrossSSSRHead) only gate the poz *housing* on hasPoz and draw
        // the lens mesh here unconditionally, assuming the housing will frame/backstop it. With no
        // housing, that lens is left floating and visible on its own, even at zero color - depth
        // writes/reads and alpha-test/blending don't matter if the draw call never happens at all.
        if (thisCrossingTile.hasPozLight()) {
            // Unlike L/R, poz never idles above 0 - but it still never skips its render call while
            // the light exists, so it stays part of the draw order the same way L/R does while
            // idling. Alpha-test runs before blending in the fixed-function pipeline, so it discards
            // this fragment outright whenever pozBrightness sweeps below the ~0.1 cutoff while fading
            // - defeating the fade entirely unless alpha-test is disabled for this one draw (additive
            // blend takes over instead). LED poz lamps never take on a value in that cutoff range
            // (only ever exactly 0 or 1), so they skip this and use the same plain alpha-test path as L/R.
            if (pozFades) {
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(1, 1);
                // The additive draw below has near-zero color while fading out, but depth writes stay
                // on by default - an invisible quad would still occlude the model's own later-drawn
                // parts behind it, showing through to whatever's further back. Depth-test still
                // applies; only writing is suppressed.
                GL11.glDepthMask(false);
            }
            // Left uncapped at the full 2x, same as L/R below - 1.0 killed the shader-visible glow
            // entirely and an intermediate 1.5 cap still read as too dark, so this now matches L/R's
            // brightness exactly instead of deliberately under-driving the HDR range for bloom.
            float pozColor = 2.0f * pozBrightness;
            GL11.glColor4f(pozColor, pozColor, pozColor, pozColor);
            this.modelCross.renderSvetloPoz(thisCrossingTile.getDistFromSloup().Dist, thisCrossingTile.getLightPos().Pos, thisCrossingTile.isNewer());
            if (pozFades) {
                GL11.glDepthMask(true);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
            }
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix(); // pop B after inner block
    }

    private static float[] getVystraznikPivot(Consts.DistFromPole dist) {
        switch (dist) {
            case DIST_30:
                return new float[]{-0.123142f, 0.181485f};
            case DIST_50:
                return new float[]{-0.123142f, 0.3025f};
            case DIST_75:
                return new float[]{-0.123142f, 0.453743f};
            case DIST_100:
                return new float[]{-0.123142f, 0.604985f};
            default:
                return new float[]{0f, 0f};
        }
    }

    private static void rotateAroundPivot(float[] pivot, float headRot) {
        GL11.glTranslatef(0f, pivot[0], pivot[1]);
        GL11.glRotatef(headRot, 0, 1, 0);
        GL11.glTranslatef(0f, -pivot[0], -pivot[1]);
    }
}
