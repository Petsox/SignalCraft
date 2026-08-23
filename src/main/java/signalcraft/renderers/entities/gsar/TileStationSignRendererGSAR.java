package signalcraft.renderers.entities.gsar;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.text.StrBuilder;
import org.lwjgl.opengl.GL11;
import signalcraft.ItemBlocks.SCItemBlocks;
import signalcraft.SignalCraft;
import signalcraft.entities.gsar.signalsSO.TileGSARStationSign;
import signalcraft.fonts.*;
import signalcraft.gui.gsar.GuiStationSignGSAR;
import signalcraft.models.gsar.signalsSO.ModelStationSignGSAR;

public class TileStationSignRendererGSAR extends TileEntitySpecialRenderer
{

    private final ModelStationSignGSAR modelStationSigns;

    private final Minecraft MC = Minecraft.getMinecraft();
    private final GSARFontRenderer fontRendererDefault = new GSARFontRenderer(MC.gameSettings, new ResourceLocation("textures/font/ascii.png"), MC.renderEngine, true);
    private final GSARFontRendererBatangChe fontRendererBatangChe = new GSARFontRendererBatangChe(MC.gameSettings, new ResourceLocation(SignalCraft.MOD_ID + ":fonts/batang_che.png"), MC.renderEngine, true);
    private final GSARFontRendererFaithCollapsing fontRendererFaithCollapsing = new GSARFontRendererFaithCollapsing(MC.gameSettings, new ResourceLocation(SignalCraft.MOD_ID + ":fonts/faith_collapsing.png"), MC.renderEngine, true);
    private final GSARFontRendererTimesNewRoman fontRendererTimesNewRoman = new GSARFontRendererTimesNewRoman(MC.gameSettings, new ResourceLocation(SignalCraft.MOD_ID + ":fonts/times.png"), MC.renderEngine, true);
    private final GSARFontRendererGlasgow fontRendererGlasgow = new GSARFontRendererGlasgow(MC.gameSettings, new ResourceLocation(SignalCraft.MOD_ID + ":fonts/glasgow.png"), MC.renderEngine, true);

    public TileStationSignRendererGSAR(ModelStationSignGSAR modelStationSigns) {
        this.modelStationSigns = modelStationSigns;
    }

    public void renderTileEntityAt(final TileEntity tileE, final double x, final double y, final double z, final float tick) {
        final TileGSARStationSign tileEStationSigns = (TileGSARStationSign)tileE;
        final Block block = tileE.getBlockType();
        int meta;
        if (tileE.getWorldObj() == null) {
            meta = 0;
        }
        else {
            meta = tileE.getBlockMetadata();
            if (block != null && meta == 0) {
                meta = tileE.getBlockMetadata();
            }
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5f, (float)y + 0.0f, (float)z + 0.5f);
        final float f2 = meta * 360 / 4.0f;
        assert block != null;
        if (block.equals(SCItemBlocks.STATION_SIGN.block)) {
            GL11.glRotatef(-f2, 0.0f, 1.0f, 0.0f);
            if (tileEStationSigns.getModelButtonStatus() != 2) {
                this.modelStationSigns.renderBase();
            }
            this.renderString(tileEStationSigns);
        }
        else {
            final int j = tileE.getBlockMetadata();
            float f3 = 0.0f;
            if (j == 2) {
                f3 = 180.0f;
            }
            if (j == 3) {
                f3 = 0.0f;
            }
            if (j == 4) {
                f3 = 90.0f;
            }
            if (j == 5) {
                f3 = -90.0f;
            }
            GL11.glRotatef(-f3, 0.0f, 1.0f, 0.0f);
            this.renderStringWall(tileEStationSigns);
            if (tileEStationSigns.getModelButtonStatus() != 2) {
                this.modelStationSigns.renderBaseWall();
            }
        }
        if (tileEStationSigns.getModelButtonStatus() == 1) {
            if (block.equals(SCItemBlocks.STATION_SIGN.block)) {
                this.modelStationSigns.renderMetalRodLong();
            }
            else {
                this.modelStationSigns.renderMetalRodLongWall();
            }
        }
        final int i1 = 15728880;
        final int j2 = i1 % 65536;
        final int k1 = i1 / 65536;
        if (tileEStationSigns.getIsActive()) {
            GL11.glPushMatrix();
            GL11.glBlendFunc(1, 1);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDepthMask(true);
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j2, k1);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glPopMatrix();
        }
        if (block.equals(SCItemBlocks.STATION_SIGN.block)) {
            this.renderString(tileEStationSigns);
        }
        else {
            this.renderStringWall(tileEStationSigns);
        }
        GL11.glPopMatrix();
    }

    private void renderString(final TileGSARStationSign tileE) {
        String str = tileE.getSignTextField();
        final float[] adjust = { tileE.getScaleAdjust(), tileE.getXAdjust(), tileE.getYAdjust()};
        final int color = tileE.getSignTextColor();
        if (str == null) {
            str = "";
        }
        if (tileE.getFontStyleList() == 1) {
            this.fontRendererBatangChe.setUnicodeFlag(true);
            this.fontRendererBatangChe.setBidiFlag(true);
            final String displayString = this.fontRendererBatangChe.trimStringToWidth(str, 180);
            final int stringWidth = this.fontRendererBatangChe.getStringWidth(displayString);
            float scaleParam = (stringWidth <= 72) ? ((stringWidth <= 63) ? ((stringWidth <= 54) ? ((stringWidth <= 45) ? ((stringWidth <= 36) ? ((stringWidth <= 27) ? ((stringWidth <= 18) ? ((stringWidth <= 9) ? 90.0f : 50.0f) : 35.0f) : 26.0f) : 21.0f) : 17.0f) : 15.0f) : 12.5f) : 12.5f;
            if (str.getBytes().length == 1) {
                scaleParam = 110.0f;
            }
            if (this.formatStringClear(str).getBytes().length == 1) {
                scaleParam = 100.0f;
            }
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, 0.5f, 0.04f);
            GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
            GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
            this.fontRendererBatangChe.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPopMatrix();
            if (tileE.getShowsTextSide()) {
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, 0.5f, -0.04f);
                GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
                GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
                GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
                this.fontRendererBatangChe.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                GL11.glPopMatrix();
            }
        }
        else if (tileE.getFontStyleList() == 2) {
            this.fontRendererFaithCollapsing.setUnicodeFlag(true);
            this.fontRendererFaithCollapsing.setBidiFlag(true);
            final String displayString = this.fontRendererFaithCollapsing.trimStringToWidth(str, 180);
            final int stringWidth = this.fontRendererFaithCollapsing.getStringWidth(displayString);
            float scaleParam = (stringWidth <= 72) ? ((stringWidth <= 63) ? ((stringWidth <= 54) ? ((stringWidth <= 45) ? ((stringWidth <= 36) ? ((stringWidth <= 27) ? ((stringWidth <= 18) ? ((stringWidth <= 9) ? 90.0f : 50.0f) : 35.0f) : 26.0f) : 21.0f) : 17.0f) : 15.0f) : 12.5f) : 12.5f;
            if (str.getBytes().length == 1) {
                scaleParam = 110.0f;
            }
            if (this.formatStringClear(str).getBytes().length == 1) {
                scaleParam = 100.0f;
            }
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, 0.5f, 0.04f);
            GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
            GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
            this.fontRendererFaithCollapsing.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPopMatrix();
            if (tileE.getShowsTextSide()) {
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, 0.5f, -0.04f);
                GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
                GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
                GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
                this.fontRendererFaithCollapsing.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                GL11.glPopMatrix();
            }
        }
        else if (tileE.getFontStyleList() == 3) {
            this.fontRendererTimesNewRoman.setUnicodeFlag(true);
            this.fontRendererTimesNewRoman.setBidiFlag(true);
            final String displayString = this.fontRendererTimesNewRoman.trimStringToWidth(str, 180);
            final int stringWidth = this.fontRendererTimesNewRoman.getStringWidth(displayString);
            float scaleParam = (stringWidth <= 72) ? ((stringWidth <= 63) ? ((stringWidth <= 54) ? ((stringWidth <= 45) ? ((stringWidth <= 36) ? ((stringWidth <= 27) ? ((stringWidth <= 18) ? ((stringWidth <= 9) ? 90.0f : 50.0f) : 35.0f) : 26.0f) : 21.0f) : 17.0f) : 15.0f) : 12.5f) : 12.5f;
            if (str.getBytes().length == 1) {
                scaleParam = 110.0f;
            }
            if (this.formatStringClear(str).getBytes().length == 1) {
                scaleParam = 100.0f;
            }
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, 0.5f, 0.04f);
            GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
            GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
            this.fontRendererTimesNewRoman.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPopMatrix();
            if (tileE.getShowsTextSide()) {
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, 0.5f, -0.04f);
                GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
                GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
                GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
                this.fontRendererTimesNewRoman.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                GL11.glPopMatrix();
            }
        }
        else if (tileE.getFontStyleList() == 4) {
            this.fontRendererGlasgow.setUnicodeFlag(true);
            this.fontRendererGlasgow.setBidiFlag(true);
            final String displayString = this.fontRendererGlasgow.trimStringToWidth(str, 180);
            final int stringWidth = this.fontRendererGlasgow.getStringWidth(displayString);
            float scaleParam = (stringWidth <= 72) ? ((stringWidth <= 63) ? ((stringWidth <= 54) ? ((stringWidth <= 45) ? ((stringWidth <= 36) ? ((stringWidth <= 27) ? ((stringWidth <= 18) ? ((stringWidth <= 9) ? 90.0f : 50.0f) : 35.0f) : 26.0f) : 21.0f) : 17.0f) : 15.0f) : 12.5f) : 12.5f;
            if (str.getBytes().length == 1) {
                scaleParam = 110.0f;
            }
            if (this.formatStringClear(str).getBytes().length == 1) {
                scaleParam = 100.0f;
            }
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, 0.5f, 0.04f);
            GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
            GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
            this.fontRendererGlasgow.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPopMatrix();
            if (tileE.getShowsTextSide()) {
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, 0.5f, -0.04f);
                GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
                GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
                GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
                this.fontRendererGlasgow.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                GL11.glPopMatrix();
            }
        }
        else {
            this.fontRendererDefault.setUnicodeFlag(true);
            this.fontRendererDefault.setBidiFlag(true);
            final String displayString = this.fontRendererDefault.trimStringToWidth(str, 180);
            final int stringWidth = this.fontRendererDefault.getStringWidth(displayString);
            float scaleParam = (stringWidth <= 72) ? ((stringWidth <= 63) ? ((stringWidth <= 54) ? ((stringWidth <= 45) ? ((stringWidth <= 36) ? ((stringWidth <= 27) ? ((stringWidth <= 18) ? ((stringWidth <= 9) ? 90.0f : 50.0f) : 35.0f) : 26.0f) : 21.0f) : 17.0f) : 15.0f) : 12.5f) : 12.5f;
            if (str.getBytes().length == 1) {
                scaleParam = 110.0f;
            }
            if (this.formatStringClear(str).getBytes().length == 1) {
                scaleParam = 100.0f;
            }
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, 0.5f, 0.04f);
            GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
            GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
            this.fontRendererDefault.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPopMatrix();
            if (tileE.getShowsTextSide()) {
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, 0.5f, -0.04f);
                GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
                GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
                GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
                this.fontRendererDefault.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                GL11.glPopMatrix();
            }
        }
    }

    private void renderStringWall(final TileGSARStationSign tileE) {
        String str = tileE.getSignTextField();
        final float[] adjust = { tileE.getScaleAdjust(), tileE.getXAdjust(), tileE.getYAdjust() };
        final int color = tileE.getSignTextColor();
        if (str == null) {
            str = "";
        }
        if (tileE.getFontStyleList() == 1) {
            this.fontRendererBatangChe.setUnicodeFlag(true);
            this.fontRendererBatangChe.setBidiFlag(true);
            final String displayString = this.fontRendererBatangChe.trimStringToWidth(str, 180);
            final int stringWidth = this.fontRendererBatangChe.getStringWidth(displayString);
            float scaleParam = (stringWidth <= 72) ? ((stringWidth <= 63) ? ((stringWidth <= 54) ? ((stringWidth <= 45) ? ((stringWidth <= 36) ? ((stringWidth <= 27) ? ((stringWidth <= 18) ? ((stringWidth <= 9) ? 90.0f : 50.0f) : 35.0f) : 26.0f) : 21.0f) : 17.0f) : 15.0f) : 12.5f) : 12.5f;
            if (str.getBytes().length == 1) {
                scaleParam = 110.0f;
            }
            if (this.formatStringClear(str).getBytes().length == 1) {
                scaleParam = 100.0f;
            }
            int c = 0;
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, 0.5f, -0.43f);
            GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
            GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
            this.fontRendererBatangChe.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
            c += this.fontRendererBatangChe.FONT_HEIGHT;
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPopMatrix();
            if (tileE.getShowsTextSide()) {
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, 0.5f, -0.51f);
                GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
                GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
                GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
                this.fontRendererBatangChe.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
                c += this.fontRendererBatangChe.FONT_HEIGHT;
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                GL11.glPopMatrix();
            }
        }
        else if (tileE.getFontStyleList() == 2) {
            this.fontRendererFaithCollapsing.setUnicodeFlag(true);
            this.fontRendererFaithCollapsing.setBidiFlag(true);
            final String displayString = this.fontRendererFaithCollapsing.trimStringToWidth(str, 180);
            final int stringWidth = this.fontRendererFaithCollapsing.getStringWidth(displayString);
            float scaleParam = (stringWidth <= 72) ? ((stringWidth <= 63) ? ((stringWidth <= 54) ? ((stringWidth <= 45) ? ((stringWidth <= 36) ? ((stringWidth <= 27) ? ((stringWidth <= 18) ? ((stringWidth <= 9) ? 90.0f : 50.0f) : 35.0f) : 26.0f) : 21.0f) : 17.0f) : 15.0f) : 12.5f) : 12.5f;
            if (str.getBytes().length == 1) {
                scaleParam = 110.0f;
            }
            if (this.formatStringClear(str).getBytes().length == 1) {
                scaleParam = 100.0f;
            }
            int c = 0;
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, 0.5f, -0.43f);
            GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
            GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
            this.fontRendererFaithCollapsing.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
            c += this.fontRendererFaithCollapsing.FONT_HEIGHT;
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPopMatrix();
            if (tileE.getShowsTextSide()) {
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, 0.5f, -0.51f);
                GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
                GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
                GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
                this.fontRendererFaithCollapsing.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
                c += this.fontRendererFaithCollapsing.FONT_HEIGHT;
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                GL11.glPopMatrix();
            }
        }
        else if (tileE.getFontStyleList() == 3) {
            this.fontRendererTimesNewRoman.setUnicodeFlag(true);
            this.fontRendererTimesNewRoman.setBidiFlag(true);
            final String displayString = this.fontRendererTimesNewRoman.trimStringToWidth(str, 180);
            final int stringWidth = this.fontRendererTimesNewRoman.getStringWidth(displayString);
            float scaleParam = (stringWidth <= 72) ? ((stringWidth <= 63) ? ((stringWidth <= 54) ? ((stringWidth <= 45) ? ((stringWidth <= 36) ? ((stringWidth <= 27) ? ((stringWidth <= 18) ? ((stringWidth <= 9) ? 90.0f : 50.0f) : 35.0f) : 26.0f) : 21.0f) : 17.0f) : 15.0f) : 12.5f) : 12.5f;
            if (str.getBytes().length == 1) {
                scaleParam = 110.0f;
            }
            if (this.formatStringClear(str).getBytes().length == 1) {
                scaleParam = 100.0f;
            }
            int c = 0;
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, 0.5f, -0.43f);
            GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
            GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
            this.fontRendererTimesNewRoman.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
            c += this.fontRendererTimesNewRoman.FONT_HEIGHT;
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPopMatrix();
            if (tileE.getShowsTextSide()) {
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, 0.5f, -0.51f);
                GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
                GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
                GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
                this.fontRendererTimesNewRoman.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
                c += this.fontRendererTimesNewRoman.FONT_HEIGHT;
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                GL11.glPopMatrix();
            }
        }
        else if (tileE.getFontStyleList() == 4) {
            this.fontRendererGlasgow.setUnicodeFlag(true);
            this.fontRendererGlasgow.setBidiFlag(true);
            final String displayString = this.fontRendererGlasgow.trimStringToWidth(str, 180);
            final int stringWidth = this.fontRendererGlasgow.getStringWidth(displayString);
            float scaleParam = (stringWidth <= 72) ? ((stringWidth <= 63) ? ((stringWidth <= 54) ? ((stringWidth <= 45) ? ((stringWidth <= 36) ? ((stringWidth <= 27) ? ((stringWidth <= 18) ? ((stringWidth <= 9) ? 90.0f : 50.0f) : 35.0f) : 26.0f) : 21.0f) : 17.0f) : 15.0f) : 12.5f) : 12.5f;
            if (str.getBytes().length == 1) {
                scaleParam = 110.0f;
            }
            if (this.formatStringClear(str).getBytes().length == 1) {
                scaleParam = 100.0f;
            }
            int c = 0;
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, 0.5f, -0.43f);
            GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
            GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
            this.fontRendererGlasgow.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
            c += this.fontRendererGlasgow.FONT_HEIGHT;
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPopMatrix();
            if (tileE.getShowsTextSide()) {
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, 0.5f, -0.51f);
                GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
                GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
                GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
                this.fontRendererGlasgow.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
                c += this.fontRendererGlasgow.FONT_HEIGHT;
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                GL11.glPopMatrix();
            }
        }
        else {
            this.fontRendererDefault.setUnicodeFlag(true);
            this.fontRendererDefault.setBidiFlag(true);
            final String displayString = this.fontRendererDefault.trimStringToWidth(str, 180);
            final int stringWidth = this.fontRendererDefault.getStringWidth(displayString);
            float scaleParam = (stringWidth <= 72) ? ((stringWidth <= 63) ? ((stringWidth <= 54) ? ((stringWidth <= 45) ? ((stringWidth <= 36) ? ((stringWidth <= 27) ? ((stringWidth <= 18) ? ((stringWidth <= 9) ? 90.0f : 50.0f) : 35.0f) : 26.0f) : 21.0f) : 17.0f) : 15.0f) : 12.5f) : 12.5f;
            if (str.getBytes().length == 1) {
                scaleParam = 110.0f;
            }
            if (this.formatStringClear(str).getBytes().length == 1) {
                scaleParam = 100.0f;
            }
            int c = 0;
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, 0.5f, -0.43f);
            GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
            GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
            this.fontRendererDefault.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
            c += this.fontRendererDefault.FONT_HEIGHT;
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPopMatrix();
            if (tileE.getShowsTextSide()) {
                GL11.glPushMatrix();
                GL11.glTranslatef(0.0f, 0.5f, -0.51f);
                GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
                GL11.glScalef((scaleParam + adjust[0]) / 1000.0f, -(scaleParam + adjust[0]) / 1000.0f, (scaleParam + adjust[0]) / 1000.0f);
                GL11.glNormal3f(0.0f, 0.0f, -1.75f * (scaleParam + adjust[0]) / 1000.0f);
                this.fontRendererDefault.drawString(displayString, -stringWidth / 2.3f + adjust[1], -4.5f + adjust[2], color);
                c += this.fontRendererDefault.FONT_HEIGHT;
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                GL11.glPopMatrix();
            }
        }
    }

    private String formatStringClear(final String str) {
        final String[] displycodes = {GuiStationSignGSAR.FontStyles.BOLD.styleCode, GuiStationSignGSAR.FontStyles.ITALIC.styleCode, GuiStationSignGSAR.FontStyles.RESET.styleCode, GuiStationSignGSAR.FontStyles.STRIKETHROUGH.styleCode, GuiStationSignGSAR.FontStyles.UNDERLINE.styleCode };
        final StrBuilder strb = new StrBuilder(str);
        if (!strb.isEmpty()) {
            for (final String s : displycodes) {
                strb.deleteAll(s);
            }
        }
        return strb.toString();
    }
}
