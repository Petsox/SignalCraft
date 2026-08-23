package signalcraft.gui.gsar;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsSO.TileGSARSignalLever;
import signalcraft.gui.gsar.buttons.GuiButtonModeStates;
import signalcraft.signalUtils.Network;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class GuiSignalLeverGSAR extends GuiScreen {
    final Minecraft MC;
    private GuiButton doneBtn;
    private GuiButton signalClearBtn;
    private GuiButton signalHPLightStateBtn;
    private GuiButton signalRailcrossStateBtn;
    private GuiButton signalHP1StateBtn;
    private GuiButton signalHP2StateBtn;
    private GuiButton signalWN1StateBtn;
    private int modeNumberState;
    private final TileGSARSignalLever tileESignalsLever;
    private final String opLocLever;
    private final String opNameLever;
    GuiTextField editTextField_BS;
    GuiTextField editTextField_Name;
    private final String guiName;
    private final String editMsgSB_TITLE;
    private final String editMsgSB_TEXT;
    private final String editMsgSB_TEXT2;
    private final String editMsgSB_TEXT3;
    private final String editMsgSB_TEXT4;
    private final String editMsgSB_TEXT5;
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
    private GuiColorPicker guiColorPicker;

    public GuiSignalLeverGSAR(final TileGSARSignalLever tileEntity) {
        this.MC = Minecraft.getMinecraft();
        this.guiName = this.getClass().getSimpleName();
        this.editMsgSB_TITLE = GuiModI18.gui(this.guiName, "editMsgSB_TITLE");
        this.editMsgSB_TEXT = I18n.format("GuiEditMsgSB_TEXT");
        this.editMsgSB_TEXT2 = I18n.format("GuiEditMsgSB_TEXT2");
        this.editMsgSB_TEXT3 = I18n.format("GuiEditMsgSB_TEXT3");
        this.editMsgSB_TEXT4 = I18n.format("GuiEditMsgSB_TEXT4");
        this.editMsgSB_TEXT5 = I18n.format("GuiEditMsgSB_TEXT5");
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
        this.tileESignalsLever = tileEntity;
        this.opLocLever = tileEntity.getStationLabelStativ();
        this.opNameLever = tileEntity.getSignalLabelStativ();
    }

    public void drawScreen(final int mouseX, final int mouseY, final float par3) {
        this.drawDefaultBackground();
        this.drawSignSignalsLever();
        this.drawHorizontalLine(0, this.width, this.height / 32 * 4 + 4, new Color(255, 255, 255, 128).getRGB());
        this.drawCenteredString(this.fontRendererObj, this.editMsgSB_TITLE, this.width / 2, this.height / 4 - 64, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_TEXT, this.width / 2 - 160, this.height / 4, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_TEXT2, this.width / 2 - 160, this.height / 4 + 8, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_TEXT3, this.width / 2 - 160, this.height / 4 + 16, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_TEXT4, this.width / 2 - 160, this.height / 4 + 24, 16777215);
        this.drawString(this.fontRendererObj, this.editMsgSB_TEXT5, this.width / 2 - 160, this.height / 4 + 40, 16777215);
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
        if (button.id >= 1 && button.id <= 16) {
            this.tileESignalsLever.setLeverTexture(button.id - 1);
        }

        switch (button.id) {
            case 0: {
                this.tileESignalsLever.markDirty();
                this.mc.displayGuiScreen(null);
                break;
            }
            case 17: {
                this.tileESignalsLever.setSignID("0");
                this.modeChange(this.modeNumberState = 0);
                break;
            }
            case 18: {
                this.tileESignalsLever.setSignID("1");
                this.modeChange(this.modeNumberState = 1);
                break;
            }
            case 19: {
                this.tileESignalsLever.setSignID("2");
                this.modeChange(this.modeNumberState = 2);
                break;
            }
            case 20: {
                this.tileESignalsLever.setSignID("3");
                this.modeChange(this.modeNumberState = 3);
                break;
            }
            case 21: {
                this.tileESignalsLever.setSignID("4");
                this.modeChange(this.modeNumberState = 4);
                break;
            }
            case 22: {
                this.tileESignalsLever.setSignID("5");
                this.modeChange(this.modeNumberState = 5);
                break;
            }
        }
        if (this.tileESignalsLever.getLeverTexture() > 16) {
            this.tileESignalsLever.setLeverTexture(0);
        }
        if (this.tileESignalsLever.getLeverTexture() < 0) {
            this.tileESignalsLever.setLeverTexture(0);
        }
    }

    public void initGui() {
        Keyboard.enableRepeatEvents(this.allowUserInput = true);
        if (!this.MC.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(true);
            this.fontRendererObj.setBidiFlag(true);
        }
        this.buttonList.clear();
        (this.editTextField_BS = new GuiTextField(this.fontRendererObj, this.width / 2 - 30, this.height / 4 + 5, 20, 10)).setMaxStringLength(3);
        this.editTextField_BS.setText(this.opLocLever);
        (this.editTextField_Name = new GuiTextField(this.fontRendererObj, this.width / 2 - 30, this.height / 4 + 20, 32, 10)).setMaxStringLength(6);
        this.editTextField_Name.setText(this.opNameLever);
        this.buttonList.add(this.doneBtn = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 140, I18n.format("gui.done")));
        this.guiColorPicker = new GuiColorPicker(buttonList, 1, this.width / 2 - 160, this.height / 4 + 50);
        guiColorPicker.addPalette();
        this.buttonList.add(this.signalClearBtn = new GuiButtonModeStates(17, this.width / 2 - 108, this.height / 4 - 20, 20, 20, " "));
        this.buttonList.add(this.signalHP1StateBtn = new GuiButtonModeStates(18, this.width / 2 - 86, this.height / 4 - 20, 20, 20, "HP1"));
        this.buttonList.add(this.signalHP2StateBtn = new GuiButtonModeStates(19, this.width / 2 - 64, this.height / 4 - 20, 20, 20, "HP2"));
        this.buttonList.add(this.signalWN1StateBtn = new GuiButtonModeStates(20, this.width / 2 - 42, this.height / 4 - 20, 20, 20, "WN1"));
        this.buttonList.add(this.signalHPLightStateBtn = new GuiButtonModeStates(21, this.width / 2 - 20, this.height / 4 - 20, 20, 20, "HL1"));
        this.buttonList.add(this.signalRailcrossStateBtn = new GuiButtonModeStates(22, this.width / 2 + 2, this.height / 4 - 20, 20, 20, "RC"));
        this.modeChange(this.modeNumberState);
    }

    public void updateScreen() {
        this.editTextField_BS.updateCursorCounter();
        this.editTextField_Name.updateCursorCounter();
        this.tileESignalsLever.setSignalLabelStativ(this.editTextField_BS.getText());
        this.tileESignalsLever.setStationLabelStativ(this.editTextField_Name.getText());
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        if (!this.MC.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(false);
            this.fontRendererObj.setBidiFlag(false);
        }
        Network.updateSignSignals(tileESignalsLever);
    }

    private void modeChange(final int mode) {
        switch (mode) {
            case 0: {
                this.signalHP1StateBtn.enabled = true;
                this.signalHP2StateBtn.enabled = true;
                this.signalWN1StateBtn.enabled = true;
                this.signalClearBtn.enabled = false;
                this.signalHPLightStateBtn.enabled = true;
                this.signalRailcrossStateBtn.enabled = true;
                break;
            }
            case 1: {
                this.signalHP1StateBtn.enabled = false;
                this.signalHP2StateBtn.enabled = true;
                this.signalWN1StateBtn.enabled = true;
                this.signalClearBtn.enabled = true;
                this.signalHPLightStateBtn.enabled = true;
                this.signalRailcrossStateBtn.enabled = true;
                break;
            }
            case 2: {
                this.signalHP1StateBtn.enabled = true;
                this.signalHP2StateBtn.enabled = false;
                this.signalWN1StateBtn.enabled = true;
                this.signalClearBtn.enabled = true;
                this.signalHPLightStateBtn.enabled = true;
                this.signalRailcrossStateBtn.enabled = true;
                break;
            }
            case 3: {
                this.signalHP1StateBtn.enabled = true;
                this.signalHP2StateBtn.enabled = true;
                this.signalWN1StateBtn.enabled = false;
                this.signalClearBtn.enabled = true;
                this.signalHPLightStateBtn.enabled = true;
                this.signalRailcrossStateBtn.enabled = true;
                break;
            }
            case 4: {
                this.signalHP1StateBtn.enabled = true;
                this.signalHP2StateBtn.enabled = true;
                this.signalWN1StateBtn.enabled = true;
                this.signalClearBtn.enabled = true;
                this.signalHPLightStateBtn.enabled = false;
                this.signalRailcrossStateBtn.enabled = true;
                break;
            }
            case 5: {
                this.signalHP1StateBtn.enabled = true;
                this.signalHP2StateBtn.enabled = true;
                this.signalWN1StateBtn.enabled = true;
                this.signalClearBtn.enabled = true;
                this.signalHPLightStateBtn.enabled = true;
                this.signalRailcrossStateBtn.enabled = false;
                break;
            }
        }
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    private void drawSignSignalsLever() {
        final float SizePercent = 85.0f;
        GL11.glPushMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glTranslatef((float) (this.width / 2), this.height / 4.0f - 60.0f, 50.0f);
        GL11.glScalef(-SizePercent, -SizePercent, -SizePercent);
        GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);

        final float f2 = this.tileESignalsLever.getBlockMetadata() * 360 / 16.0f;
        GL11.glRotatef(f2, 0.0f, 1.0f, 0.0f);
        GL11.glTranslatef(0.0f, -1.5f, 0.0f);
        GL11.glRotatef(-10.0f, 0.0f, 1.0f, 0.0f);


        TileEntityRendererDispatcher.instance.renderTileEntityAt(this.tileESignalsLever, -0.5, -0.5, -0.5, 0.0f);
        GL11.glPopMatrix();
    }
}
