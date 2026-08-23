package signalcraft.renderers.entities;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.signals.signSignals.TileSpeedSign;
import signalcraft.fonts.DinFontRenderer;
import signalcraft.models.ModelSpeedSign;

public class TileSpeedSignRenderer extends TileEntitySpecialRenderer {
    final ModelSpeedSign modelSpeedSign = new ModelSpeedSign();
    private final Minecraft MC = Minecraft.getMinecraft();
    private final DinFontRenderer fontrenderer = new DinFontRenderer(MC.gameSettings, new ResourceLocation("textures/font/ascii.png"), MC.renderEngine, true);
    public TileSpeedSignRenderer() {
    }

    public void renderTileEntityAt(final TileEntity tileE, final double x, final double y, final double z, final float tick) {
        final TileSpeedSign tileSpeed = (TileSpeedSign) tileE;
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
        GL11.glTranslatef(0.0f, -3f, 0.0f);
        GL11.glScalef(1.5f, 1.5f, 1.5f);
        this.modelSpeedSign.renderRychlostnik();
        renderString(tileSpeed);
        GL11.glPopMatrix();
    }

    private void renderString(final TileSpeedSign tileEntity) {
        String str = tileEntity.getSpeedSignalText();
        if (str == null) str = "";

        int stringWidth = this.fontrenderer.getStringWidth(str);
        int byteLength = str.getBytes().length;

        float scaleParam = (stringWidth < 3 || byteLength < 3) ? 15.0f : 12.0f;

        float xOffset;
        if (byteLength == 3) {
            xOffset = -stringWidth / 2f;
        } else if (byteLength == 2) {
            xOffset = -stringWidth / 2f;
        } else {
            xOffset = -stringWidth / 1.75f;
        }

        GL11.glPushMatrix();
        GL11.glTranslatef(0.0f, 0.7f, 0.04f);
        GL11.glScalef(scaleParam / 1000.0f, -15.0f / 1000.0f, 15.0f / 1000.0f);
        GL11.glNormal3f(0.0f, 0.0f, -1.75f * scaleParam / 1000.0f);
        GL11.glDepthMask(false);

        fontrenderer.drawString(str, (int) xOffset, (int) -6.75f, 0);

        GL11.glDepthMask(true);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }


}
