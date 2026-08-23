package signalcraft.gui.gsar;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import org.apache.commons.lang3.text.StrBuilder;
import org.lwjgl.input.Keyboard;
import signalcraft.entities.gsar.signalsSO.TileGSARStationSign;
import signalcraft.gui.gsar.buttons.GuiButtonModeStates;
import signalcraft.gui.gsar.buttons.GuiColorButton;
import signalcraft.gui.gsar.buttons.GuiSliderButton;
import signalcraft.signalUtils.Network;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuiStationSignGSAR extends GuiScreen
{
    Minecraft MC;
    private final TileGSARStationSign tileE;
    protected int hasTextButtonStatus;
    private int modelButtonStatus;
    protected boolean isVisible;
    private boolean nightLight;
    private boolean side;
    private int fontStyleList;
    private final String guiName;
    private final String editMsg_TITLE;
    private final String editMsg_ST_DESC;
    private final String editMsg_ST_DESC1;
    private final String editMsg_ST_yes;
    private final String editMsg_ST_no;
    private final String editMsg_ST_normal;
    private final String editMsg_ST_hanging;
    private final String editMsg_ST_notvisible;
    private final String editMsg_ST_Color_TITLE;
    private final String editMsg_ST_Styles_TITLE;
    private final String editMsg_ST_Styles_DESC;
    private final String editMsg_ST_Styles_reset;
    private final String editMsg_ST_Styles_resetAll;
    private final String editMsg_ST_Slider_TITLE;
    private final String editMsg_ST_Slider_xPos;
    private final String editMsg_ST_Slider_yPos;
    private final String editMsg_ST_Slider_scale;
    private final String editMsg_ST_Slider_reset;
    private final String editMsg_ST_Light_TITLE;
    private final String editMsg_ST_Light;
    private final String editMsg_ST_Side_TITLE;
    private final String editMsg_ST_Side;
    private FontStyles currentFontStyle;
    private static final String[] OnOffStr;
    private static final String[] SideStr;
    private static final String[] FontStyleStr;
    private int[] RGB;
    Color TextColorObj;
    private GuiSliderButton xPosSlider;
    private GuiSliderButton yPosSlider;
    private GuiSliderButton scaleSlider;
    float[] adjust;
    private GuiButton boldBtn;
    private GuiButton strikethroughBtn;
    private GuiButton underlineBtn;
    private GuiButton italicBtn;
    private GuiButton resetBtn;
    private GuiButton resetAllBtn;
    private GuiButton resetSlider;
    private GuiButton lightBtn;
    private GuiButton sideBtn;
    private GuiButton fontStyleBtn;
    private GuiButton doneBtn;
    private GuiButton normalBtn;
    private GuiButton hangingBtn;
    private GuiButton notVisibleBtn;
    private GuiButton hasTextYes;
    private GuiButton hasTextNo;
    private GuiButton redBtnPos;
    private GuiButton redBtnNeg;
    private GuiButton greenBtnPos;
    private GuiButton greenBtnNeg;
    private GuiButton blueBtnPos;
    private GuiButton blueBtnNeg;
    private final List<GuiTextField> textFieldList;
    GuiTextField editTextField;
    GuiTextField textFieldRed;
    GuiTextField textFieldGreen;
    GuiTextField textFieldBlue;
    private GuiColorPicker guiColorPicker;

    public GuiStationSignGSAR(final TileGSARStationSign tileE) {
        this.MC = Minecraft.getMinecraft();
        this.hasTextButtonStatus = 0;
        this.modelButtonStatus = 0;
        this.isVisible = false;
        this.nightLight = false;
        this.side = false;
        this.fontStyleList = 0;
        this.guiName = this.getClass().getSimpleName();
        this.editMsg_TITLE = GuiModI18.gui(this.guiName, "editMsg_TITLE");
        this.editMsg_ST_DESC = GuiModI18.gui(this.guiName, "editMsg_ST_DESC");
        this.editMsg_ST_DESC1 = GuiModI18.gui(this.guiName, "editMsg_ST_DESC1");
        this.editMsg_ST_yes = GuiModI18.gui(this.guiName, "editMsg_ST_yes");
        this.editMsg_ST_no = GuiModI18.gui(this.guiName, "editMsg_ST_no");
        this.editMsg_ST_normal = GuiModI18.gui(this.guiName, "editMsg_ST_normal");
        this.editMsg_ST_hanging = GuiModI18.gui(this.guiName, "editMsg_ST_hanging");
        this.editMsg_ST_notvisible = GuiModI18.gui(this.guiName, "editMsg_ST_notvisible");
        this.editMsg_ST_Color_TITLE = GuiModI18.gui(this.guiName, "editMsg_ST_Color_TITLE");
        this.editMsg_ST_Styles_TITLE = GuiModI18.gui(this.guiName, "editMsg_ST_Styles_TITLE");
        this.editMsg_ST_Styles_DESC = GuiModI18.gui(this.guiName, "editMsg_ST_Styles_DESC");
        this.editMsg_ST_Styles_reset = GuiModI18.gui(this.guiName, "editMsg_ST_Styles_reset");
        this.editMsg_ST_Styles_resetAll = GuiModI18.gui(this.guiName, "editMsg_ST_Styles_resetAll");
        this.editMsg_ST_Slider_TITLE = GuiModI18.gui(this.guiName, "editMsg_ST_Slider_TITLE");
        this.editMsg_ST_Slider_xPos = GuiModI18.gui(this.guiName, "editMsg_ST_Slider_xPos");
        this.editMsg_ST_Slider_yPos = GuiModI18.gui(this.guiName, "editMsg_ST_Slider_yPos");
        this.editMsg_ST_Slider_scale = GuiModI18.gui(this.guiName, "editMsg_ST_Slider_scale");
        this.editMsg_ST_Slider_reset = GuiModI18.gui(this.guiName, "editMsg_ST_Styles_reset");
        this.editMsg_ST_Light_TITLE = GuiModI18.gui(this.guiName, "editMsg_ST_Light_TITLE");
        this.editMsg_ST_Light = GuiModI18.gui(this.guiName, "editMsg_ST_Light");
        this.editMsg_ST_Side_TITLE = GuiModI18.gui(this.guiName, "editMsg_ST_Side_TITLE");
        this.editMsg_ST_Side = GuiModI18.gui(this.guiName, "editMsg_ST_Side");
        this.RGB = new int[] { 225, 225, 225 };
        this.adjust = new float[] { 0.0f, 0.0f, 0.0f };
        this.tileE = tileE;
        this.textFieldList = new ArrayList<>();
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float par3) {
        Keyboard.enableRepeatEvents(this.allowUserInput = true);
        if (!this.MC.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(true);
            this.fontRendererObj.setBidiFlag(true);
        }
        this.drawDefaultBackground();
        this.drawHorizontalLine(0, this.width, this.height / 32 * 4 + 4, new Color(255, 255, 255, 128).getRGB());
        this.drawCenteredString(this.fontRendererObj, this.editMsg_TITLE, this.width / 2, this.height / 4 - 64, 16777215);
        this.drawString(this.fontRendererObj, this.editMsg_ST_DESC, this.width / 2 - 220, this.height / 4 - 20, 16777215);
        if (this.isVisible) {
            this.drawString(this.fontRendererObj, this.editMsg_ST_Color_TITLE, this.width / 2 + 120, this.height / 4 + 70, 16777215);
            this.editTextField.drawTextBox();
            this.textFieldRed.drawTextBox();
            this.textFieldGreen.drawTextBox();
            this.textFieldBlue.drawTextBox();
            this.drawString(this.fontRendererObj, this.editMsg_ST_Styles_TITLE, this.width / 2 + 145, this.height / 4 - 20, 16777215);
            this.drawString(this.fontRendererObj, this.editMsg_ST_Styles_DESC, this.width / 2 - 130, this.height / 4 - 5, 16777215);
            this.drawString(this.fontRendererObj, this.editMsg_ST_Slider_TITLE, this.width / 2 - 220, this.height / 4 + 90, 16777215);
            this.drawString(this.fontRendererObj, this.editMsg_ST_Light_TITLE, this.width / 2 + 145, this.height / 4 + 40, 16777215);
            this.drawString(this.fontRendererObj, this.editMsg_ST_Side_TITLE, this.width / 2 + 145, this.height / 4 + 10, 16777215);
        }
        else {
            this.drawString(this.fontRendererObj, this.editMsg_ST_DESC1, this.width / 2 - 220, this.height / 4 + 5, 16777215);
        }
        super.drawScreen(mouseX, mouseY, par3);
    }
    
    protected void keyTyped(char character, final int code) {
        if (!Character.isISOControl(character) && !Character.isDigit(character) && !this.editTextField.isFocused()) {
            character = '0';
        }
        this.textFieldRed.textboxKeyTyped(character, code);
        this.textFieldGreen.textboxKeyTyped(character, code);
        this.textFieldBlue.textboxKeyTyped(character, code);
        if (Character.isDigit(character)) {
            final String[] strText = { this.textFieldRed.getText(), this.textFieldGreen.getText(), this.textFieldBlue.getText() };
            for (int i = 0; i < 3; ++i) {
                if (strText[i] != null) {
                    if (Integer.parseInt(strText[i]) > 255) {
                        strText[i] = "255";
                        this.RGB[i] = 255;
                    }
                    else {
                        strText[i] = String.valueOf(Integer.parseInt(strText[i]));
                        this.RGB[i] = Integer.parseInt(strText[i]);
                    }
                }
            }
            this.textFieldRed.setText(strText[0]);
            this.textFieldGreen.setText(strText[1]);
            this.textFieldBlue.setText(strText[2]);
            this.TextColorObj = new Color(this.RGB[0], this.RGB[1], this.RGB[2]);
        }
        this.editTextField.textboxKeyTyped(character, code);
        if (code != 18 || !this.editTextField.isFocused()) {
            super.keyTyped(character, code);
        }
        if (code == 1) {
            this.actionPerformed(this.doneBtn);
        }
    }
    
    protected void mouseClicked(final int x, final int y, final int buttonClicked) {
        this.editTextField.mouseClicked(x, y, buttonClicked);
        if (buttonClicked == 0) {
            this.textFieldRed.mouseClicked(x, y, buttonClicked);
            this.textFieldGreen.mouseClicked(x, y, buttonClicked);
            this.textFieldBlue.mouseClicked(x, y, buttonClicked);
        }
        super.mouseClicked(x, y, buttonClicked);
    }
    
    protected void mouseClickMove(final int mouseX, final int mouseY, final int lastButtonClicked, final long timeSinceMouseClick) {
        if (this.xPosSlider.isPressed) {
            this.adjust[0] = this.xPosSlider.getValue();
        }
        if (this.yPosSlider.isPressed) {
            this.adjust[1] = this.yPosSlider.getValue();
        }
        if (this.scaleSlider.isPressed) {
            this.adjust[2] = this.scaleSlider.getValue();
        }
    }
    
    public void initGui() {
        super.initGui();
        this.buttonList.add(this.doneBtn = new GuiButton(0, this.width / 2 - 50, this.height / 4 + 140, 80, 20, I18n.format("gui.done")));
        this.buttonList.add(this.hasTextYes = new GuiButtonModeStates(1, this.width / 2 - 89, this.height / 4 - 25, 25, 20, this.editMsg_ST_yes));
        this.buttonList.add(this.hasTextNo = new GuiButtonModeStates(2, this.width / 2 - 55, this.height / 4 - 25, 25, 20, this.editMsg_ST_no));
        this.buttonList.add(this.normalBtn = new GuiButtonModeStates(3, this.width / 2 - 155, this.height / 4, 60, 20, this.editMsg_ST_normal));
        this.buttonList.add(this.hangingBtn = new GuiButtonModeStates(4, this.width / 2 - 90, this.height / 4, 60, 20, this.editMsg_ST_hanging));
        this.buttonList.add(this.notVisibleBtn = new GuiButtonModeStates(5, this.width / 2 - 25, this.height / 4, 60, 20, this.editMsg_ST_notvisible));
        this.buttonList.add(this.redBtnPos = new GuiButtonModeStates(6, this.width / 2 + 58, this.height / 4 + 78, 20, 20, "R-8"));
        this.buttonList.add(this.redBtnNeg = new GuiButtonModeStates(7, this.width / 2 + 100, this.height / 4 + 78, 20, 20, "R+8"));
        this.buttonList.add(this.greenBtnPos = new GuiButtonModeStates(8, this.width / 2 + 58, this.height / 4 + 98, 20, 20, "G-8"));
        this.buttonList.add(this.greenBtnNeg = new GuiButtonModeStates(9, this.width / 2 + 100, this.height / 4 + 98, 20, 20, "G+8"));
        this.buttonList.add(this.blueBtnPos = new GuiButtonModeStates(10, this.width / 2 + 58, this.height / 4 + 118, 20, 20, "B-8"));
        this.buttonList.add(this.blueBtnNeg = new GuiButtonModeStates(11, this.width / 2 + 100, this.height / 4 + 118, 20, 20, "B+8"));
        this.buttonList.add(this.fontStyleBtn = new GuiButtonModeStates(30, this.width / 2 + 145, this.height / 4 - 10, 55, 20, FontStyleStr[0]));
        this.buttonList.add(this.boldBtn = new GuiButtonModeStates(31, this.width / 2 - 220, this.height / 4 + 5, 40, 20, "�l[B]�r " + FontStyles.BOLD.styleDisplayCode));
        this.buttonList.add(this.strikethroughBtn = new GuiButtonModeStates(32, this.width / 2 - 180, this.height / 4 + 5, 40, 20, "�m[S]�r " + FontStyles.STRIKETHROUGH.styleDisplayCode));
        this.buttonList.add(this.underlineBtn = new GuiButtonModeStates(33, this.width / 2 - 220, this.height / 4 + 25, 40, 20, "�n[U]�r " + FontStyles.UNDERLINE.styleDisplayCode));
        this.buttonList.add(this.italicBtn = new GuiButtonModeStates(34, this.width / 2 - 180, this.height / 4 + 25, 40, 20, "�o[I]�r " + FontStyles.ITALIC.styleDisplayCode));
        this.buttonList.add(this.resetBtn = new GuiButtonModeStates(35, this.width / 2 - 200, this.height / 4 + 45, 40, 20, this.editMsg_ST_Styles_reset + " " + FontStyles.RESET.styleDisplayCode));
        this.buttonList.add(this.resetAllBtn = new GuiButtonModeStates(36, this.width / 2 - 220, this.height / 4 + 65, 80, 20, this.editMsg_ST_Styles_resetAll));
        this.buttonList.add(this.xPosSlider = new GuiSliderButton(37, this.width / 2 - 220, this.height / 4 + 98, 80, this.editMsg_ST_Slider_xPos, 1.0f, -32.0f, 32.0f, 0.0f));
        this.buttonList.add(this.yPosSlider = new GuiSliderButton(38, this.width / 2 - 220, this.height / 4 + 118, 80, this.editMsg_ST_Slider_yPos, 1.0f, -32.0f, 32.0f, 0.0f));
        this.buttonList.add(this.scaleSlider = new GuiSliderButton(39, this.width / 2 - 220, this.height / 4 + 138, 80, this.editMsg_ST_Slider_scale, 2.0f, -64.0f, 64.0f, 0.0f));
        this.buttonList.add(this.resetSlider = new GuiButtonModeStates(40, this.width / 2 - 140, this.height / 4 + 118, 30, 20, this.editMsg_ST_Slider_reset));
        this.buttonList.add(this.lightBtn = new GuiButtonModeStates(41, this.width / 2 + 145, this.height / 4 + 50, 55, 20, this.editMsg_ST_Light + " " + OnOffStr[0]));
        this.buttonList.add(this.sideBtn = new GuiButtonModeStates(42, this.width / 2 + 145, this.height / 4 + 20, 55, 20, this.editMsg_ST_Side + " " + SideStr[0]));
        this.guiColorPicker = new GuiColorPicker(buttonList, 14, this.width / 2 + 120, this.height / 4 + 78);
        guiColorPicker.addPalette();
        (this.editTextField = new GuiTextField(this.fontRendererObj, this.width / 2 - 130, this.height / 4 + 5, 270, 10)).setMaxStringLength(256);
        this.TextColorObj = new Color(this.RGB[0], this.RGB[1], this.RGB[2]);
        (this.textFieldRed = new GuiTextField(this.fontRendererObj, this.width / 2 + 79, this.height / 4 + 80, 20, 15)).setMaxStringLength(3);
        (this.textFieldGreen = new GuiTextField(this.fontRendererObj, this.width / 2 + 79, this.height / 4 + 100, 20, 15)).setMaxStringLength(3);
        (this.textFieldBlue = new GuiTextField(this.fontRendererObj, this.width / 2 + 79, this.height / 4 + 120, 20, 15)).setMaxStringLength(3);
        this.textFieldRed.setText(String.valueOf(this.RGB[0]));
        this.textFieldGreen.setText(String.valueOf(this.RGB[1]));
        this.textFieldBlue.setText(String.valueOf(this.RGB[2]));
        this.currentFontStyle = FontStyles.RESET;
        this.modeChange(this.hasTextButtonStatus);
        this.modeChangeModel(this.modelButtonStatus);
    }
    
    protected void actionPerformed(final GuiButton button) {
        switch (button.id) {
            case 0: {
                this.tileE.markDirty();
                this.mc.displayGuiScreen(null);
                break;
            }
            case 1: {
                this.modeChange(this.hasTextButtonStatus = 1);
                break;
            }
            case 2: {
                this.modeChange(this.hasTextButtonStatus = 0);
                break;
            }
            case 3: {
                this.tileE.setModelButtonStatus(0);
                this.modeChangeModel(this.modelButtonStatus = 0);
                break;
            }
            case 4: {
                this.tileE.setModelButtonStatus(1);
                this.modeChangeModel(this.modelButtonStatus = 1);
                break;
            }
            case 5: {
                this.tileE.setModelButtonStatus(2);
                this.modeChangeModel(this.modelButtonStatus = 2);
                break;
            }
            case 6: {
                final int[] rgb = this.RGB;
                final int n = 0;
                rgb[n] -= 8;
                break;
            }
            case 7: {
                final int[] rgb2 = this.RGB;
                final int n2 = 0;
                rgb2[n2] += 8;
                break;
            }
            case 8: {
                final int[] rgb3 = this.RGB;
                final int n3 = 1;
                rgb3[n3] -= 8;
                break;
            }
            case 9: {
                final int[] rgb4 = this.RGB;
                final int n4 = 1;
                rgb4[n4] += 8;
                break;
            }
            case 10: {
                final int[] rgb5 = this.RGB;
                final int n5 = 2;
                rgb5[n5] -= 8;
                break;
            }
            case 11: {
                final int[] rgb6 = this.RGB;
                final int n6 = 2;
                rgb6[n6] += 8;
                break;
            }
            case 30: {
                if (this.fontStyleBtn.displayString.contains(FontStyleStr[0])) {
                    this.fontStyleBtn.displayString = this.fontStyleBtn.displayString.replace(FontStyleStr[0], FontStyleStr[1]);
                    this.fontStyleList = 1;
                    break;
                }
                if (this.fontStyleBtn.displayString.contains(FontStyleStr[1])) {
                    this.fontStyleBtn.displayString = this.fontStyleBtn.displayString.replace(FontStyleStr[1], FontStyleStr[2]);
                    this.fontStyleList = 2;
                    break;
                }
                if (this.fontStyleBtn.displayString.contains(FontStyleStr[2])) {
                    this.fontStyleBtn.displayString = this.fontStyleBtn.displayString.replace(FontStyleStr[2], FontStyleStr[3]);
                    this.fontStyleList = 3;
                    break;
                }
                if (this.fontStyleBtn.displayString.contains(FontStyleStr[3])) {
                    this.fontStyleBtn.displayString = this.fontStyleBtn.displayString.replace(FontStyleStr[3], FontStyleStr[4]);
                    this.fontStyleList = 4;
                    break;
                }
                if (this.fontStyleBtn.displayString.contains(FontStyleStr[4])) {
                    this.fontStyleBtn.displayString = this.fontStyleBtn.displayString.replace(FontStyleStr[4], FontStyleStr[0]);
                    this.fontStyleList = 0;
                    break;
                }
                break;
            }
            case 31: {
                this.currentFontStyle = FontStyles.BOLD;
                this.editTextField.writeText(this.currentFontStyle.styleDisplayCode);
                this.editTextField.setFocused(true);
                break;
            }
            case 32: {
                this.currentFontStyle = FontStyles.STRIKETHROUGH;
                this.editTextField.writeText(this.currentFontStyle.styleDisplayCode);
                this.editTextField.setFocused(true);
                break;
            }
            case 33: {
                this.currentFontStyle = FontStyles.UNDERLINE;
                this.editTextField.writeText(this.currentFontStyle.styleDisplayCode);
                this.editTextField.setFocused(true);
                break;
            }
            case 34: {
                this.currentFontStyle = FontStyles.ITALIC;
                this.editTextField.writeText(this.currentFontStyle.styleDisplayCode);
                this.editTextField.setFocused(true);
                break;
            }
            case 35: {
                this.currentFontStyle = FontStyles.RESET;
                this.editTextField.writeText(this.currentFontStyle.styleDisplayCode);
                this.editTextField.setFocused(true);
                break;
            }
            case 36: {
                this.currentFontStyle = FontStyles.RESETALL;
                this.editTextField.setText(this.formatStringClear(this.editTextField.getText()));
                this.nightLight = false;
                if (this.lightBtn.displayString.contains(OnOffStr[1])) {
                    this.lightBtn.displayString = this.lightBtn.displayString.replace(OnOffStr[1], OnOffStr[0]);
                }
                if (this.sideBtn.displayString.contains(SideStr[1])) {
                    this.sideBtn.displayString = this.sideBtn.displayString.replace(SideStr[1], SideStr[0]);
                    break;
                }
                break;
            }
            case 40: {
                this.xPosSlider.setValue(this.adjust[0] = 0.0f);
                this.yPosSlider.setValue(this.adjust[1] = 0.0f);
                this.scaleSlider.setValue(this.adjust[2] = 0.0f);
                break;
            }
            case 41: {
                if (this.lightBtn.displayString.contains(OnOffStr[0])) {
                    this.lightBtn.displayString = this.lightBtn.displayString.replace(OnOffStr[0], OnOffStr[1]);
                    this.nightLight = true;
                    break;
                }
                this.lightBtn.displayString = this.lightBtn.displayString.replace(OnOffStr[1], OnOffStr[0]);
                this.nightLight = false;
                break;
            }
            case 42: {
                if (this.sideBtn.displayString.contains(SideStr[0])) {
                    this.sideBtn.displayString = this.sideBtn.displayString.replace(SideStr[0], SideStr[1]);
                    this.side = true;
                    break;
                }
                this.sideBtn.displayString = this.sideBtn.displayString.replace(SideStr[1], SideStr[0]);
                this.side = false;
                break;
            }
        }
        if (button.id >= 14 && button.id <= 29) {
            this.RGB[0] = ((GuiColorButton)button).getColor().getRed();
            this.RGB[1] = ((GuiColorButton)button).getColor().getGreen();
            this.RGB[2] = ((GuiColorButton)button).getColor().getBlue();
        }
        for (int i = 0; i < 3; ++i) {
            if (this.RGB[i] < 0) {
                this.RGB[i] = 0;
            }
            if (this.RGB[i] > 255) {
                this.RGB[i] = 255;
            }
        }
        this.textFieldRed.setText(String.valueOf(this.RGB[0]));
        this.textFieldGreen.setText(String.valueOf(this.RGB[1]));
        this.textFieldBlue.setText(String.valueOf(this.RGB[2]));
        this.TextColorObj = new Color(this.RGB[0], this.RGB[1], this.RGB[2]);
    }
    
    public void updateScreen() {
        this.editTextField.updateCursorCounter();
        this.tileE.setSignTextColor(this.TextColorObj.getRGB());
        this.tileE.setSignTextField(this.editTextField.getText());
        this.tileE.setSignTextField(this.formatStringChange(this.editTextField.getText()));
        this.tileE.setIsActive(this.nightLight);
        this.tileE.setShowsTextSide(this.side);
        this.tileE.setFontStyleList(this.fontStyleList);
        this.tileE.setXAdjust(this.adjust[0]);
        this.tileE.setYAdjust(this.adjust[1]);
        this.tileE.setScaleAdjust(this.adjust[2]);
        this.redBtnPos.enabled = (this.RGB[0] > 0);
        this.redBtnNeg.enabled = (this.RGB[0] < 255);
        this.greenBtnPos.enabled = (this.RGB[1] > 0);
        this.greenBtnNeg.enabled = (this.RGB[1] < 255);
        this.blueBtnPos.enabled = (this.RGB[2] > 0);
        this.blueBtnNeg.enabled = (this.RGB[2] < 255);
        this.textFieldRed.updateCursorCounter();
        this.textFieldGreen.updateCursorCounter();
        this.textFieldBlue.updateCursorCounter();
        Network.updateSignSignals(tileE);
    }
    
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        if (!this.MC.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(false);
            this.fontRendererObj.setBidiFlag(false);
        }
        //Network.updateSignSignals(tileE); moved up to updateScreen() to ensure text is being rendered while editing and not just when closing the GUI
    }
    
    private void modeChange(final int mode) {
        switch (mode) {
            case 0: {
                this.hasTextYes.enabled = true;
                this.hasTextNo.enabled = false;
                this.normalBtn.visible = true;
                this.hangingBtn.visible = true;
                this.notVisibleBtn.visible = true;
                this.setVisible(false);
                break;
            }
            case 1: {
                this.hasTextYes.enabled = false;
                this.hasTextNo.enabled = true;
                this.normalBtn.visible = false;
                this.hangingBtn.visible = false;
                this.notVisibleBtn.visible = false;
                this.setVisible(true);
                break;
            }
        }
    }
    
    private void modeChangeModel(final int model) {
        switch (model) {
            case 0: {
                this.normalBtn.enabled = false;
                this.hangingBtn.enabled = true;
                this.notVisibleBtn.enabled = true;
                break;
            }
            case 1: {
                this.normalBtn.enabled = true;
                this.hangingBtn.enabled = false;
                this.notVisibleBtn.enabled = true;
                break;
            }
            case 2: {
                this.normalBtn.enabled = true;
                this.hangingBtn.enabled = true;
                this.notVisibleBtn.enabled = false;
                break;
            }
        }
    }
    
    public void setVisible(final boolean isVisible) {
        this.isVisible = isVisible;
        this.adjust = new float[] { 0.0f, 0.0f, 0.0f };
        if (isVisible) {
            this.editTextField.setVisible(isVisible);
            this.editTextField.setEnabled(isVisible);
            this.editTextField.setFocused(isVisible);
            this.RGB = new int[] { 225, 225, 225 };
            this.textFieldRed.setText(Integer.toString(this.RGB[0]));
            this.textFieldGreen.setText(Integer.toString(this.RGB[1]));
            this.textFieldBlue.setText(Integer.toString(this.RGB[2]));
            this.redBtnPos.visible = true;
            this.redBtnNeg.visible = true;
            this.greenBtnPos.visible = true;
            this.greenBtnNeg.visible = true;
            this.blueBtnPos.visible = true;
            this.blueBtnNeg.visible = true;
            this.boldBtn.visible = true;
            this.strikethroughBtn.visible = true;
            this.underlineBtn.visible = true;
            this.italicBtn.visible = true;
            this.resetBtn.visible = true;
            this.resetAllBtn.visible = true;
            this.lightBtn.visible = true;
            this.sideBtn.visible = true;
            this.fontStyleBtn.visible = true;
            this.xPosSlider.visible = true;
            this.yPosSlider.visible = true;
            this.scaleSlider.visible = true;
            this.resetSlider.visible = true;
            for (GuiColorButton paletteButton : this.guiColorPicker.getPaletteButtons()) {
                paletteButton.visible = true;
            }
        }
        else {
            this.editTextField.setText("");
            this.redBtnPos.visible = false;
            this.redBtnNeg.visible = false;
            this.greenBtnPos.visible = false;
            this.greenBtnNeg.visible = false;
            this.blueBtnPos.visible = false;
            this.blueBtnNeg.visible = false;
            this.boldBtn.visible = false;
            this.strikethroughBtn.visible = false;
            this.underlineBtn.visible = false;
            this.italicBtn.visible = false;
            this.resetBtn.visible = false;
            this.resetAllBtn.visible = false;
            this.lightBtn.visible = false;
            this.sideBtn.visible = false;
            this.fontStyleBtn.visible = false;
            this.xPosSlider.visible = false;
            this.yPosSlider.visible = false;
            this.scaleSlider.visible = false;
            this.resetSlider.visible = false;
            for (GuiColorButton paletteButton : this.guiColorPicker.getPaletteButtons()) {
                paletteButton.visible = false;
            }
        }
        for (GuiTextField guiTextField : this.textFieldList) {
            guiTextField.setVisible(isVisible);
            guiTextField.setEnabled(isVisible);
        }
    }
    
    protected String formatStringChange(final String str) {
        final String[] displaycodes = { FontStyles.BOLD.styleDisplayCode, FontStyles.ITALIC.styleDisplayCode, FontStyles.RESET.styleDisplayCode, FontStyles.STRIKETHROUGH.styleDisplayCode, FontStyles.UNDERLINE.styleDisplayCode };
        final String[] formatcodes = { FontStyles.BOLD.styleCode, FontStyles.ITALIC.styleCode, FontStyles.RESET.styleCode, FontStyles.STRIKETHROUGH.styleCode, FontStyles.UNDERLINE.styleCode };
        final StrBuilder strb = new StrBuilder(str);
        for (int i = 0; i < displaycodes.length; ++i) {
            strb.replaceAll(displaycodes[i], formatcodes[i]);
        }
        return strb.toString();
    }
    
    private String formatStringClear(final String str) {
        final String[] displaycodes = { FontStyles.BOLD.styleDisplayCode, FontStyles.ITALIC.styleDisplayCode, FontStyles.RESET.styleDisplayCode, FontStyles.STRIKETHROUGH.styleDisplayCode, FontStyles.UNDERLINE.styleDisplayCode };
        final StrBuilder strb = new StrBuilder(str);
        if (this.editTextField.getText() != null) {
            for (final String c : displaycodes) {
                strb.deleteAll(c);
            }
        }
        return strb.toString();
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    static {
        OnOffStr = new String[] { "[off]", "[on]" };
        SideStr = new String[] { "[front]", "[all]" };
        FontStyleStr = new String[] { "Ascii", "BatangChe", "FaithCollapse", "TimesNewRoman", "Glasgow" };
    }
    
    public enum FontStyles
    {
        BOLD("�l", "<b:>"), 
        ITALIC("�o", "<i:>"), 
        RESET("�r", "</r>"), 
        RESETALL, 
        STRIKETHROUGH("�m", "<s:>"), 
        UNDERLINE("�n", "<u:>");
        
        public final String styleCode;
        public final String styleDisplayCode;
        public final boolean enable;
        
        FontStyles() {
            this.enable = false;
            this.styleCode = "";
            this.styleDisplayCode = "";
        }
        
        FontStyles(final boolean enable) {
            this.styleCode = "";
            this.styleDisplayCode = "";
            this.enable = enable;
        }
        
        FontStyles(final String code, final String displayCode) {
            this.enable = false;
            this.styleCode = code;
            this.styleDisplayCode = displayCode;
        }
    }
}
