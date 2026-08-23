package signalcraft.renderers.entities.gsar;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.switches.TileSwitch;
import signalcraft.fonts.GSARFontRendererDin1451Alt;
import signalcraft.models.gsar.signalsWN.ModelSwitchesGSAR;

public class TileSwitchRendererGSAR extends TileEntitySpecialRenderer
{
    private final ModelSwitchesGSAR modelSwitch;
    private final Minecraft MC = Minecraft.getMinecraft();
    private final GSARFontRendererDin1451Alt fontrenderer = new GSARFontRendererDin1451Alt(MC.gameSettings, new ResourceLocation("signalcraft:fonts/din1451alt.png"), MC.renderEngine, true);

    public TileSwitchRendererGSAR(ModelSwitchesGSAR model) {
        this.modelSwitch = model;
    }

    public void renderTileEntityAt(final TileEntity tileE, final double x, final double y, final double z, final float tick) {
        final TileSwitch tileSignal = (TileSwitch)tileE;
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
        GL11.glTranslatef((float)x + 0.5f, (float)y + 0.0f, (float)z + 0.5f);
        final float f2 = meta * 360 / 16.0f;
        GL11.glRotatef(-f2, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-90f, 0.0f, 1.0f, 0.0f);

        String switchSide = tileSignal.getSwitchSide() ? "_P" : "_L";
        String switchPos = tileSignal.getSwitchPos() ? "_P" : "_L";
        Boolean isInverted = tileSignal.isInverted();

        modelSwitch.renderZaklad(tileSignal);

        GL11.glPushMatrix();
        if (tileSignal.getSwitchPos()) GL11.glTranslatef(0.0f, 0.0f, 0.4f);
        modelSwitch.renderPrestavnik(isInverted, tileSignal);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        modelSwitch.renderPojezdy(switchPos, switchSide, tileSignal);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        if (tileSignal.getSwitchPos()) GL11.glTranslatef(0.0f, 0.0f, 0.4f);

        if (tileSignal.getSwitchMode()) {
            modelSwitch.renderHlavaS(tileSignal);
        } else {
            modelSwitch.renderHlavaR(switchSide, tileSignal);
        }

        renderString(tileSignal);
        GL11.glPopMatrix();

        GL11.glPopMatrix();
    }

    private void renderString(final TileSwitch tileEntity) {
        String str = tileEntity.getSwitchName();
        if (str == null) {
            str = "";
        }

        final int color = 16777215;
        this.fontrenderer.setUnicodeFlag(true);
        this.fontrenderer.setBidiFlag(true);

        final String displayString = this.fontrenderer.trimStringToWidth(str, 80);
        final int stringWidth = this.fontrenderer.getStringWidth(displayString);
        final float scaleParam = (str.getBytes().length <= 3) ? 40.0f : 12.5f;

        GL11.glPushMatrix();
        if (tileEntity.getSwitchSide()) {
            GL11.glRotatef(tileEntity.getSwitchMode() ? 270.0f : 90.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(tileEntity.getSwitchMode() ? -0.21f : 0.13f, 0.85f, 0.077f);
        } else {
            GL11.glRotatef(tileEntity.getSwitchMode() ? 270.0f : 90.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(tileEntity.getSwitchMode() ? -0.28f : 0.28f, 0.85f, 0.077f);
        }

        GL11.glScalef(scaleParam / 3000.0f, -scaleParam / 3000.0f, scaleParam / 3000.0f);
        GL11.glNormal3f(0.0f, 0.0f, -1.75f * scaleParam / 1000.0f);
        GL11.glDepthMask(false);
        this.fontrenderer.drawString(displayString, -stringWidth / 2.0f, -4.0f, color);

        if (tileEntity.getSwitchMode()) {
            GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(1.0f, 0.0f, 11.477f);
            this.fontrenderer.drawString(displayString, -stringWidth / 2.0f, -4.0f, color);
        }

        GL11.glDepthMask(true);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }
}
