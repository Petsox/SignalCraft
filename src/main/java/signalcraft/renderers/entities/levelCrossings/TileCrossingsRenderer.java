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

        // Scaling and translation adjustments based on tile type
        if (thisCrossingTile instanceof TileVUD){
            GL11.glScalef(1.0f, 1.0f, 1.0f);
        } else GL11.glScalef(1.5f, 1.5f, 1.5f);
        if (thisCrossingTile instanceof TileSSSRHead) GL11.glTranslatef(0f, 0.15f, -0.049f);

        GL11.glScalef(thisCrossingTile.getScale(), thisCrossingTile.getScale(), thisCrossingTile.getScale());

        LampFade fade = thisCrossingTile.getLampFade();
        long dt = fade.beginFrame(Minecraft.getSystemTime());

        if (thisCrossingTile instanceof IAnglesAddable){
            GL11.glTranslatef(0f, 0.2935f, 0f);

            int[] angles = ((IAnglesAddable) thisCrossingTile).getAngles();
            for (int i = 0; i < angles.length; i++) {
                GL11.glRotatef(angles[i], 0, 1, 0);
                renderHead(thisCrossingTile, j1, k1, i + 1, i == 0, dt);
            }

        } else {
            this.modelCross.renderZaklad(thisCrossingTile.getLightPos().Pos, thisCrossingTile.hasPozLight(), thisCrossingTile.isLightCoverShort());
            renderHead(thisCrossingTile, j1, k1, 1, true, dt);
        }

        GL11.glPopMatrix(); // pop A

    }

    private void renderHead(TileLevelCrossing thisCrossingTile, int j1, int k1, int angleIndex, boolean renderKrizAndCedule, long dt) {
        this.modelCross.renderSloup(thisCrossingTile.getDistFromSloup().Dist, thisCrossingTile.hasZebrik(), thisCrossingTile.isCedule(), thisCrossingTile.hasKrizNaStozaru());
        this.modelCross.renderStozar(thisCrossingTile.getDistFromSloup().Dist, thisCrossingTile.hasPruhy());

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
        // Unlike L/R, poz never idles above 0 - but it still never skips its render call, so it
        // stays part of the draw order the same way L/R does while idling. Alpha-test runs before
        // blending in the fixed-function pipeline, so it discards this fragment outright whenever
        // pozBrightness sweeps below the ~0.1 cutoff while fading - defeating the fade entirely
        // unless alpha-test is disabled for this one draw (additive blend takes over instead).
        // LED poz lamps never take on a value in that cutoff range (only ever exactly 0 or 1), so
        // they skip this and use the same plain alpha-test path as L/R.
        if (pozFades) {
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(1, 1);
        }
        GL11.glColor4f(2.0f * pozBrightness, 2.0f * pozBrightness, 2.0f * pozBrightness, 2.0f * pozBrightness);
        this.modelCross.renderSvetloPoz(thisCrossingTile.getDistFromSloup().Dist, thisCrossingTile.getLightPos().Pos, thisCrossingTile.isNewer());
        if (pozFades) {
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
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
