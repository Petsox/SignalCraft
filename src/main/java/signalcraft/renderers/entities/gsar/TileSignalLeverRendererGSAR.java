package signalcraft.renderers.entities.gsar;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsSO.TileGSARSignalLever;
import signalcraft.fonts.GSARFontRenderer;
import signalcraft.models.gsar.signalsSO.ModelSignalsLeverGSAR;

public class TileSignalLeverRendererGSAR extends TileEntitySpecialRenderer
{
    private final Minecraft MC = Minecraft.getMinecraft();
    private final GSARFontRenderer fontrenderer = new GSARFontRenderer(MC.gameSettings, new ResourceLocation("textures/font/ascii.png"), MC.renderEngine, true);
    private final ModelSignalsLeverGSAR modelSignalsLever;

    public TileSignalLeverRendererGSAR(ModelSignalsLeverGSAR modelSignalsLever) {
        this.modelSignalsLever = modelSignalsLever;

    }
    
    public void renderTileEntityAt(final TileEntity tileE, final double x, final double y, final double z, final float tick) {
        final TileGSARSignalLever tileESignalsLever = (TileGSARSignalLever)tileE;
        int meta;
        if (tileE.getWorldObj() == null) {
            meta = 0;
        }
        else {
            final Block block = tileE.getBlockType();
            meta = tileE.getBlockMetadata();
            if (block != null && meta == 0) {
                meta = tileE.getBlockMetadata();
            }
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5f, (float)y + 1.6f, (float)z + 0.5f);
        final float f2 = meta * 360 / 16.0f;
        GL11.glRotatef(-f2, 0.0f, 1.0f, 0.0f);


        GL11.glPushMatrix();
        this.modelSignalsLever.renderSwitchBase();

        GL11.glPushMatrix();
        this.modelSignalsLever.renderSwitchHebel(tileESignalsLever);
        this.modelSignalsLever.renderSwitchHP1(tileESignalsLever);
        GL11.glPopMatrix();

        this.renderStringOperatingLocation(tileESignalsLever);
        this.renderStringOperatingName(tileESignalsLever);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
    
    private void renderStringOperatingLocation(final TileGSARSignalLever tileEntity) {
        String str = tileEntity.getStationLabelStativ();
        final float[] adjust = { tileEntity.getScaleAdjust(), tileEntity.getXAdjust(), tileEntity.getYAdjust() };
        final int color = 0;
        if (str == null) {
            str = "";
        }
        final String displayString = this.fontrenderer.trimStringToWidth(str, 80);
        final int stringWidth = this.fontrenderer.getStringWidth(displayString);
        float scaleParam = (stringWidth <= 3) ? 90.0f : 50.0f;
        if (str.getBytes().length <= 3) {
            scaleParam = 50.0f;
        }
        GL11.glPushMatrix();
        GL11.glTranslatef(-0.265f, 0.0f, 0.08f);
        GL11.glRotatef(-30.0f, 1.0f, 0.0f, 0.0f);
        GL11.glScalef((scaleParam + adjust[0]) / 3000.0f, -(scaleParam + adjust[0]) / 3000.0f, (scaleParam + adjust[0]) / 3000.0f);
        GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
        GL11.glDepthMask(false);
        this.fontrenderer.drawString(displayString, -stringWidth / 1.85f + adjust[1], 0.5f + adjust[2], color);
        GL11.glDepthMask(true);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }
    
    private void renderStringOperatingName(final TileGSARSignalLever tileEntity) {
        String str = tileEntity.getSignalLabelStativ();
        final float[] adjust = { tileEntity.getScaleAdjust(), tileEntity.getXAdjust(), tileEntity.getYAdjust() };
        final int color = 0;
        if (str == null) {
            str = "";
        }
        final String displayString = this.fontrenderer.trimStringToWidth(str, 60);
        final int stringWidth = this.fontrenderer.getStringWidth(displayString);
        float scaleParam = (stringWidth <= 3) ? 30.0f : 25.0f;
        if (str.getBytes().length <= 3) {
            scaleParam = 45.0f;
        }
        if (str.getBytes().length == 4) {
            scaleParam = 35.0f;
        }
        if (str.getBytes().length == 5) {
            scaleParam = 30.0f;
        }
        if (str.getBytes().length == 6) {
            scaleParam = 25.0f;
        }
        GL11.glPushMatrix();
        GL11.glTranslatef(-0.265f, -0.095f, 0.128f);
        GL11.glRotatef(-30.0f, 1.0f, 0.0f, 0.0f);
        GL11.glScalef((scaleParam + adjust[0]) / 3000.0f, -(scaleParam + adjust[0]) / 3000.0f, (scaleParam + adjust[0]) / 3000.0f);
        GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
        GL11.glDepthMask(false);
        if (str.getBytes().length == 6) {
            GL11.glTranslated(-0.0, 3.0, 0.0);
            this.fontrenderer.drawString(displayString, -stringWidth / 1.85f + adjust[1], 0.5f + adjust[2], color);
        }
        if (str.getBytes().length == 5) {
            GL11.glTranslated(-0.0, 2.0, 0.0);
            this.fontrenderer.drawString(displayString, -stringWidth / 1.85f + adjust[1], 0.5f + adjust[2], color);
        }
        if (str.getBytes().length == 4) {
            GL11.glTranslated(-0.0, 1.0, 0.0);
            this.fontrenderer.drawString(displayString, -stringWidth / 1.85f + adjust[1], 0.55f + adjust[2], color);
        }
        else {
            this.fontrenderer.drawString(displayString, -stringWidth / 1.85f + adjust[1], 0.5f + adjust[2], color);
        }
        GL11.glDepthMask(true);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }
}
