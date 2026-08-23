package signalcraft.renderers.entities.gsar;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsBU.TileGSARCrossing;
import signalcraft.entities.gsar.signalsBU.TileGSARRailCrossModern;
import signalcraft.models.gsar.signalsBU.ModelGSARRailCross;
import signalcraft.signalUtils.LampFade;

public class TileRailCrossRendererGSAR extends TileEntitySpecialRenderer {
    private final ModelGSARRailCross modelRailCross;

    public TileRailCrossRendererGSAR(ModelGSARRailCross model) {
        this.modelRailCross = model;
    }

    public void renderTileEntityAt(final TileEntity tileE, final double x, final double y, final double z, final float tick) {
        final Block block = tileE.getBlockType();
        final TileGSARCrossing railCrossLight = (TileGSARCrossing) tileE;
        int meta;
        if (tileE.getWorldObj() == null) {
            meta = 0;
        } else {
            meta = tileE.getBlockMetadata();
            if (block != null && meta == 0) {
                meta = tileE.getBlockMetadata();
            }
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y + 0.0f, (float) z + 0.5f);
        final float f2 = meta * 360 / 16.0f;
        GL11.glRotatef(-f2, 0.0f, 1.0f, 0.0f);

        this.modelRailCross.renderBaseLight(railCrossLight);
        if (!(tileE instanceof TileGSARRailCrossModern)) this.modelRailCross.renderStativLight();

        int c = railCrossLight.blinkCounter;
        boolean active = railCrossLight.isActive();
        boolean lampOn = active && !((c >= 1 && c < 15) || (c >= 30 && c < 45));

        // While active, the off phase of the blink idles at a dim glow instead of going
        // fully dark, mirroring the level crossing L/R lamps' filament-afterglow behavior.
        float target = active ? (lampOn ? 1.0f : LampFade.IDLE_BRIGHTNESS) : 0.0f;

        LampFade fade = railCrossLight.getLampFade();
        long dt = fade.beginFrame(Minecraft.getSystemTime());
        float brightness = fade.step("light", target, dt);

        if (brightness > 0.0f) {
            final int i1 = 15728880;
            final int j1 = i1 % 65536;
            final int k1 = i1 / 65536;
            GL11.glPushMatrix();
            GL11.glBlendFunc(1, 1);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDepthMask(true);
            GL11.glColor4f(2.0f * brightness, 2.0f * brightness, 2.0f * brightness, 2.0f * brightness);
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j1, k1);
            this.modelRailCross.renderLightOn(railCrossLight);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }
}
