package signalcraft.renderers.entities.gsar;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsHP.TileGSARStativ;
import signalcraft.entities.gsar.signalsHP.TileGSARStativSemiSignals;
import signalcraft.fonts.BetterFontRenderer;
import signalcraft.models.gsar.IStativModelGSAR;

public class TileStativRendererGSAR extends TileEntitySpecialRenderer
{
    private final IStativModelGSAR lightSignalsModel;
    private final Minecraft MC = Minecraft.getMinecraft();
    private final BetterFontRenderer fontrenderer = new BetterFontRenderer(MC.gameSettings, new ResourceLocation("textures/font/ascii.png"), MC.renderEngine, true);

    public TileStativRendererGSAR(IStativModelGSAR model) {
        this.lightSignalsModel = model;
    }

    public void renderTileEntityAt(final TileEntity tileE, final double x, final double y, final double z, final float tick) {
        final TileGSARStativ tileSignal = (TileGSARStativ)tileE;
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
        GL11.glTranslatef((float)x + 0.5f, (float)y + 3.0f, (float)z + 0.5f);
        final float f2 = meta * 360 / 16.0f;
        GL11.glRotatef(-f2, 0.0f, 1.0f, 0.0f);
        GL11.glTranslatef(0.0f, -3.0f, 0.0f);
        this.lightSignalsModel.renderStativ(tileSignal);
        this.renderStringOperatingLocation(tileSignal);
        this.renderStringOperatingName(tileSignal);
        GL11.glPopMatrix();
    }

    private void renderLabel(TileGSARStativ tileEntity, String str, int trimWidth, float baseX, float baseY, float zSemi, float zDefault, boolean applyLengthYOffset) {
        final float[] adjust = { tileEntity.getScaleAdjust(), tileEntity.getXAdjust(), tileEntity.getYAdjust() };
        final int color = 0;
        if (str == null) {
            str = "";
        }
        final String displayString = this.fontrenderer.trimStringToWidth(str, trimWidth);
        final int stringWidth = this.fontrenderer.getStringWidth(displayString);
        final int len = str.getBytes().length;

        // Determine scaleParam using original behavior condensed
        float scaleParam;
        if (trimWidth == 80) { // operating location original rules
            scaleParam = (stringWidth <= 3) ? 90.0f : 50.0f;
            if (len <= 3) scaleParam = 50.0f;
        } else { // operating name original rules
            scaleParam = (stringWidth <= 3) ? 30.0f : 25.0f;
            if (len <= 3) scaleParam = 45.0f;
            if (len == 4) scaleParam = 35.0f;
            if (len == 5) scaleParam = 30.0f;
            if (len == 6) scaleParam = 25.0f;
        }

        GL11.glPushMatrix();
        final float z = (tileEntity instanceof TileGSARStativSemiSignals) ? zSemi : zDefault;
        GL11.glTranslatef(baseX, baseY, z);
        GL11.glScalef((scaleParam + adjust[0]) / 3000.0f, -(scaleParam + adjust[0]) / 3000.0f, (scaleParam + adjust[0]) / 3000.0f);
        GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
        GL11.glDepthMask(false);

        // apply vertical micro-offsets that were previously handled by multiple branches
        if (applyLengthYOffset) {
            if (len == 6) GL11.glTranslated(-0.0, 3.0, 0.0);
            else if (len == 5) GL11.glTranslated(-0.0, 2.0, 0.0);
            else if (len == 4) GL11.glTranslated(-0.0, 1.0, 0.0);
        }

        final float drawY = (len == 4 && applyLengthYOffset) ? 0.55f + adjust[2] : 0.5f + adjust[2];
        this.fontrenderer.drawString(displayString, -stringWidth / 1.85f + adjust[1], drawY, color);

        GL11.glDepthMask(true);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }

    private void renderStringOperatingLocation(final TileGSARStativ tileEntity) {
        this.renderLabel(tileEntity, tileEntity.getStationLabelStativ(), 80, 0.015f, 0.68f, 0.226f, 0.201f, false);
    }

    private void renderStringOperatingName(final TileGSARStativ tileEntity) {
        this.renderLabel(tileEntity, tileEntity.getSignalLabelStativ(), 60, 0.013f, 0.58f, 0.226f, 0.201f, true);
    }
}