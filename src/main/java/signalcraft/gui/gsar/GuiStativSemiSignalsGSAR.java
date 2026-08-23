package signalcraft.gui.gsar;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import signalcraft.ItemBlocks.SCItemBlocks;
import signalcraft.entities.gsar.signalsHP.TileGSARStativSemiSignals;
import signalcraft.signalUtils.Network;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiStativSemiSignalsGSAR extends GuiScreen
{
    Minecraft MC;
    private GuiButton doneBtn;
    private final TileGSARStativSemiSignals tileStativ;
    private final String opLocStativ;
    private final String opNameStativ;
    public static final ResourceLocation pic1_SG;
    GuiTextField editTextField_BS;
    GuiTextField editTextField_Name;
    GuiButton selectedButton;
    private final String guiName;
    private final String editMsg_TITLE;
    private final String editMsgSB_TEXT;
    private final String editMsgSB_TEXT2;
    private final String editMsgSB_TEXT3;
    private final String editMsgSB_TEXT4;
    private final String editMsgSB_DESC;
    private final String editMsgSB_DESC1;
    private final String editMsgSB_DESC2;
    private final String editMsgSB_DESC3;
    private final String editMsgSB_DESC4;
    private final String editMsgSB_DESC5;
    private final String editMsgSB_DESC6;
    private final String editMsgSB_DESC7;
    private final String editMsgSB_DESC8;
    private final String editMsgSB_DESC9;
    private final String editMsgSB_DESC10;
    private final String editMsgSB_DESC11;
    private final String editMsgSB_DESC12;
    private final String editMsgSB_DESC13;
    private final String editMsgSB_DESC14;
    private final String editMsgSB_DESC15;
    private final String editMsgSB_DESC16;
    private final String editMsgSB_DESC17;

    public GuiStativSemiSignalsGSAR(final TileGSARStativSemiSignals tileEntity) {
        this.MC = Minecraft.getMinecraft();
        this.guiName = this.getClass().getSimpleName();
        this.editMsg_TITLE = GuiModI18.gui(this.guiName, "editMsg_TITLE");
        this.editMsgSB_TEXT = I18n.format("GuiEditMsgSB_TEXT");
        this.editMsgSB_TEXT2 = I18n.format("GuiEditMsgSB_TEXT2");
        this.editMsgSB_TEXT3 = I18n.format("GuiEditMsgSB_TEXT3");
        this.editMsgSB_TEXT4 = I18n.format("GuiEditMsgSB_TEXT4");
        this.editMsgSB_DESC = I18n.format("GuiEditMsgSB_DESC");
        this.editMsgSB_DESC1 = I18n.format("GuiEditMsgSB_DESC1");
        this.editMsgSB_DESC2 = I18n.format("GuiEditMsgSB_DESC2");
        this.editMsgSB_DESC3 = I18n.format("GuiEditMsgSB_DESC3");
        this.editMsgSB_DESC4 = I18n.format("GuiEditMsgSB_DESC4");
        this.editMsgSB_DESC5 = I18n.format("GuiEditMsgSB_DESC5");
        this.editMsgSB_DESC6 = I18n.format("GuiEditMsgSB_DESC6");
        this.editMsgSB_DESC7 = I18n.format("GuiEditMsgSB_DESC7");
        this.editMsgSB_DESC8 = I18n.format("GuiEditMsgSB_DESC8");
        this.editMsgSB_DESC9 = I18n.format("GuiEditMsgSB_DESC9");
        this.editMsgSB_DESC10 = I18n.format("GuiEditMsgSB_DESC10");
        this.editMsgSB_DESC11 = I18n.format("GuiEditMsgSB_DESC11");
        this.editMsgSB_DESC12 = I18n.format("GuiEditMsgSB_DESC12");
        this.editMsgSB_DESC13 = I18n.format("GuiEditMsgSB_DESC13");
        this.editMsgSB_DESC14 = I18n.format("GuiEditMsgSB_DESC14");
        this.editMsgSB_DESC15 = I18n.format("GuiEditMsgSB_DESC15");
        this.editMsgSB_DESC16 = I18n.format("GuiEditMsgSB_DESC16");
        this.editMsgSB_DESC17 = I18n.format("GuiEditMsgSB_DESC17");
        this.tileStativ = tileEntity;
        this.opLocStativ = tileEntity.getStationLabelStativ();
        this.opNameStativ = tileEntity.getSignalLabelStativ();
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float par3) {
        //final int i = (this.width - 176) / 2;
        //final int j = (this.height - 166) / 2;
        this.drawDefaultBackground();
        this.drawSignSignalGround();
        this.drawHorizontalLine(0, this.width, this.height / 32 * 4 + 4, new Color(255, 255, 255, 128).getRGB());
        this.drawCenteredString(this.fontRendererObj, this.editMsg_TITLE, this.width / 2, this.height / 4 - 64, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_TEXT, this.width / 2 - 160, this.height / 4, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_TEXT2, this.width / 2 - 160, this.height / 4 + 8, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_TEXT3, this.width / 2 - 160, this.height / 4 + 16, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_TEXT4, this.width / 2 - 160, this.height / 4 + 24, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_DESC, this.width / 2 + 30, this.height / 4 - 8, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_DESC1, this.width / 2 + 30, this.height / 4, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_DESC2, this.width / 2 + 30, this.height / 4 + 8, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_DESC3, this.width / 2 + 30, this.height / 4 + 16, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_DESC4, this.width / 2 + 30, this.height / 4 + 24, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_DESC5, this.width / 2 + 30, this.height / 4 + 32, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_DESC6, this.width / 2 + 30, this.height / 4 + 40, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_DESC7, this.width / 2 + 30, this.height / 4 + 48, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_DESC8, this.width / 2 + 30, this.height / 4 + 56, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_DESC9, this.width / 2 + 30, this.height / 4 + 64, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_DESC10, this.width / 2 + 30, this.height / 4 + 72, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_DESC11, this.width / 2 + 30, this.height / 4 + 80, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_DESC12, this.width / 2 + 30, this.height / 4 + 88, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_DESC13, this.width / 2 + 30, this.height / 4 + 96, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_DESC14, this.width / 2 + 30, this.height / 4 + 104, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_DESC15, this.width / 2 + 30, this.height / 4 + 112, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_DESC16, this.width / 2 + 30, this.height / 4 + 120, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_DESC17, this.width / 2 + 30, this.height / 4 + 128, 16777215);
        this.editTextField_BS.drawTextBox();
        this.editTextField_Name.drawTextBox();
        GL11.glPushMatrix();
        GL11.glScalef(0.35f, 0.7f, 1.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(pic1_SG);
        this.drawTexturedModalRect(this.width / 2 - 220, this.height / 4 - 10, 10, 0, 256, 256);
        GL11.glPopMatrix();
        super.drawScreen(mouseX, mouseY, par3);
    }
    
    protected void keyTyped(final char character, final int code) {
        if (Character.isDigit(character) || code == 14) {
            this.editTextField_BS.textboxKeyTyped(character, code);
        }
        this.editTextField_Name.textboxKeyTyped(character, code);
        if ((code != 18 || !this.editTextField_BS.isFocused()) && (code != 18 || !this.editTextField_Name.isFocused())) {
            super.keyTyped(character, code);
        }
        if (code == 1) {
            this.actionPerformed(this.doneBtn);
        }
    }
    
    protected void mouseClicked(final int x, final int y, final int buttonClicked) {
        this.editTextField_BS.mouseClicked(x, y, buttonClicked);
        this.editTextField_Name.mouseClicked(x, y, buttonClicked);
        super.mouseClicked(x, y, buttonClicked);
    }
    
    protected void actionPerformed(final GuiButton button) {
        if (button.enabled && button.id == 0) {
            this.tileStativ.markDirty();
            this.mc.displayGuiScreen(null);
        }
    }
    
    public void initGui() {
        Keyboard.enableRepeatEvents(this.allowUserInput = true);
        //final int x = (this.width - 176) / 2;
        //final int y = (this.height - 166) / 2;
        if (!this.MC.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(true);
            this.fontRendererObj.setBidiFlag(true);
        }
        this.buttonList.clear();
        this.buttonList.add(this.doneBtn = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 140, I18n.format("gui.done")));
        (this.editTextField_BS = new GuiTextField(this.fontRendererObj, this.width / 2 - 50, this.height / 4 + 5, 20, 10)).setMaxStringLength(3);
        this.editTextField_BS.setText(this.opLocStativ);
        (this.editTextField_Name = new GuiTextField(this.fontRendererObj, this.width / 2 - 50, this.height / 4 + 20, 32, 10)).setMaxStringLength(6);
        this.editTextField_Name.setText(this.opNameStativ);
    }
    
    public void updateScreen() {
        this.editTextField_BS.updateCursorCounter();
        this.editTextField_Name.updateCursorCounter();
        this.tileStativ.setStationLabelStativ(this.editTextField_BS.getText());
        this.tileStativ.setSignalLabelStativ(this.editTextField_Name.getText());
    }
    
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        if (!this.MC.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(false);
            this.fontRendererObj.setBidiFlag(false);
        }
        Network.updateSignSignals(tileStativ);
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    private void drawSignSignalGround() {
        final float SizePercent = 85.0f;
        final int k = this.mc.theWorld.getBlockMetadata(this.tileStativ.xCoord, this.tileStativ.yCoord, this.tileStativ.zCoord);
        GL11.glPushMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glTranslatef((float)(this.width / 2), this.height / 4.0f - 60.0f, 50.0f);
        GL11.glScalef(-SizePercent, -SizePercent, -SizePercent);
        GL11.glRotatef(90f, 0.0f, 1.0f, 0.0f);
        final Block getBlockType = this.tileStativ.getBlockType();
        if (getBlockType == SCItemBlocks.STATIV_LIGHT_HP.block) {
            final float f2 = this.tileStativ.getBlockMetadata() * 360 / 16.0f;
            GL11.glRotatef(f2, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.0f, -1.5f, 0.0f);
            GL11.glRotatef(-20.0f, 0.0f, 1.0f, 0.0f);
        }
        else {
            float angle = 0.0f;
            if (k == 2) {
                angle = 180.0f;
            }
            if (k == 4) {
                angle = 90.0f;
            }
            if (k == 5) {
                angle = -90.0f;
            }
            GL11.glRotatef(angle, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.0f, -1.5f, 0.0f);
            GL11.glRotatef(-20.0f, 0.0f, 1.0f, 0.0f);
        }
        TileEntityRendererDispatcher.instance.renderTileEntityAt(this.tileStativ, -0.5, -0.5, -0.5, 0.0f);
        GL11.glPopMatrix();
    }

    static {
        pic1_SG = new ResourceLocation("signalcraft:textures/gsar/gui/pics/pic1_SG.png");
    }
}
