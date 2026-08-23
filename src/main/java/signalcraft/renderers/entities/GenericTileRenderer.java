package signalcraft.renderers.entities;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.IGeneric;
import signalcraft.entities.gsar.signalsSO.TileGSARHectometer;
import signalcraft.entities.signals.signSignals.TileSignSignal;
import signalcraft.fonts.GSARFontRendererDin1451Alt;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class GenericTileRenderer extends TileEntitySpecialRenderer {
    private final IGeneric genericTile;
    private final ResourceLocation metal = TextureRegistry.GSAR_ROD.get();
    private final IModelCustom modelMetalRod = ModelRegistry.GSAR_ROD.getModel();
    private final Minecraft MC = Minecraft.getMinecraft();
    private final GSARFontRendererDin1451Alt fontrenderer = new GSARFontRendererDin1451Alt(MC.gameSettings, new ResourceLocation("signalcraft:fonts/din1451alt.png"), MC.renderEngine, true);

    public GenericTileRenderer(IGeneric tile) {
        this.genericTile = tile;
    }

    public void renderTileEntityAt(final TileEntity tileE, final double x, final double y, final double z, final float tick) {
        int meta;
        if (tileE.getWorldObj() == null) {
            meta = 0;
        } else {
            final Block block = tileE.getBlockType();
            meta = tileE.getBlockMetadata();
            if (block != null && meta == 0) {
                meta = tileE.getBlockMetadata();
            }
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y + 3.0f, (float) z + 0.5f);
        final float f2 = meta * 360 / 16.0f;
        GL11.glRotatef(-f2, 0.0f, 1.0f, 0.0f);
        GL11.glTranslatef(0.0f, -3.0f, 0.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(genericTile.getTexture());
        genericTile.getModel().renderAll();
        if (genericTile.needsRod()) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.metal);
            this.modelMetalRod.renderAll();
        }

        if (genericTile.doesRenderGenericString()) {
            renderString((TileSignSignal) tileE);
        }

        if (genericTile instanceof TileGSARHectometer) {
            renderHectoString((TileGSARHectometer) tileE);
        }


        GL11.glPopMatrix();
    }

    private void renderString(final TileSignSignal tileSignSignal) {
        String str = tileSignSignal.getSignalLabelStativ();
        final float[] adjust = {tileSignSignal.getScaleAdjust(), tileSignSignal.getXAdjust(), tileSignSignal.getYAdjust()};
        final int color = 0;
        if (str == null) {
            str = "";
        }
        this.fontrenderer.setUnicodeFlag(true);
        this.fontrenderer.setBidiFlag(true);
        final String displayString = this.fontrenderer.trimStringToWidth(str, 80);
        final int stringWidth = this.fontrenderer.getStringWidth(displayString);
        float scaleParam = (stringWidth <= 2) ? ((stringWidth <= 1) ? 15.0f : 12.5f) : 12.5f;
        if (str.getBytes().length <= 2) {
            scaleParam = 40.0f;
        }
        if (str.getBytes().length == 1) {
            scaleParam = 60.0f;
        }
        if (str.getBytes().length == 1) {
            GL11.glTranslatef(0.02f, 0.8f, 0.065f);
        } else {
            GL11.glTranslatef(0.02f, 0.85f, 0.065f);
        }
        GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
        GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
        GL11.glDepthMask(false);
        this.fontrenderer.drawString(displayString, -stringWidth / 2.0f + adjust[1], adjust[2], color);
        GL11.glDepthMask(true);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    private void renderHectoString(TileGSARHectometer tileEntity) {
        String str = tileEntity.getSignalLabelStativ();
        final String str2 = tileEntity.getStationLabelStativ();
        final float[] adjust = {tileEntity.getScaleAdjust(), tileEntity.getXAdjust(), tileEntity.getYAdjust()};
        final int color = 0;
        if (str == null) {
            str = "";
        }
        final String displayString = this.fontrenderer.trimStringToWidth(str, 80);
        final String displayString2 = this.fontrenderer.trimStringToWidth(str2, 80);
        final int stringWidth = this.fontrenderer.getStringWidth(displayString);
        final int stringWidth2 = this.fontrenderer.getStringWidth(displayString2);
        float scaleParam = (stringWidth <= 3) ? 90.0f : 50.0f;
        if (str.getBytes().length <= 3) {
            scaleParam = 50.0f;
        }
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0f, 0.7f, 0.07f);
        GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
        GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
        GL11.glDepthMask(false);

        for (int i = 0; i < 2; i++) {
            if (i == 1){
                GL11.glTranslatef(0.0f, 0.7f, -2.8f);
                GL11.glRotatef(180, 0.0f, 1.0f, 0.0f);
            }
            if (str.getBytes().length == 3) {
                this.fontrenderer.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
            } else if (str.getBytes().length == 2) {
                this.fontrenderer.drawString(displayString, -stringWidth / 2.5f + adjust[1], -4.5f + adjust[2], color);
            } else if (str.getBytes().length <= 1) {
                this.fontrenderer.drawString(displayString, -stringWidth / 2.75f + adjust[1], -4.5f + adjust[2], color);
            }
            this.fontrenderer.drawString(displayString2, -stringWidth2 / 2.75f + adjust[1], 3.5f + adjust[2], color);
        }
        GL11.glDepthMask(true);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();

    }
}