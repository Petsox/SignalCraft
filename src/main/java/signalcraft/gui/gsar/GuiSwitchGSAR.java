package signalcraft.gui.gsar;

import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import signalcraft.blocks.switches.BlockSwitch;
import signalcraft.entities.switches.TileSwitch;
import signalcraft.signalUtils.Network;

import java.awt.*;


public class GuiSwitchGSAR extends GuiScreen {
    private final String guiName;
    private final String editMsg_TITLE;
    private final String editMsg_WN_left;
    private final String editMsg_WN_right;
    private final String editMsg_WN_yes;
    private final String editMsg_WN_no;
    private final String editMsg_WN_return;
    private final String editMsg_WN;
    private final String editMsg_WN_2;
    private final String editMsg_WN_3;
    private final String editMsg_WN_4;
    private final String editMsg_WN_5;
    private final String[] LeftRightStr;
    private final String[] YesNoStr;
    private GuiButton doneButton;
    private GuiButton switchPosButton;
    private GuiButton switchSideButton;
    private GuiButton switchModeButton;
    private final TileSwitch thisTileE;
    private GuiTextField switchNameMech;
    private String switchPosButtonText;
    private String switchSideButtonText;
    private String switchModeButtonText;

    public GuiSwitchGSAR(final TileSwitch thisTileE) {
        this.guiName = this.getClass().getSimpleName();
        this.editMsg_TITLE = GuiModI18.gui(this.guiName, "editMsg_TITLE");
        this.editMsg_WN_left = GuiModI18.gui(this.guiName, "editMsg_WN_left");
        this.editMsg_WN_right = GuiModI18.gui(this.guiName, "editMsg_WN_right");
        this.editMsg_WN_yes = GuiModI18.gui(this.guiName, "editMsg_WN_yes");
        this.editMsg_WN_no = GuiModI18.gui(this.guiName, "editMsg_WN_no");
        this.editMsg_WN_return = GuiModI18.gui(this.guiName, "editMsg_WN_return");
        this.editMsg_WN = GuiModI18.gui(this.guiName, "editMsg_WN");
        this.editMsg_WN_2 = GuiModI18.gui(this.guiName, "editMsg_WN_2");
        this.editMsg_WN_3 = GuiModI18.gui(this.guiName, "editMsg_WN_3");
        this.editMsg_WN_4 = GuiModI18.gui(this.guiName, "editMsg_WN_4");
        this.editMsg_WN_5 = GuiModI18.gui(this.guiName, "editMsg_WN_5");
        this.LeftRightStr = new String[]{this.editMsg_WN_right, this.editMsg_WN_left};
        this.YesNoStr = new String[]{this.editMsg_WN_no, this.editMsg_WN_yes};
        Keyboard.enableRepeatEvents(true);
        this.allowUserInput = true;
        this.thisTileE = thisTileE;
    }

    public void initGui() {
        this.loadValuesFromTile();
        this.buttonList.add(this.doneButton = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 140, I18n.format("gui.done")));
        this.buttonList.add(this.switchPosButton = new GuiButton(1, this.width / 2 - 105, this.height / 4 - 5, 30, 20, this.switchPosButtonText));
        this.buttonList.add(this.switchSideButton = new GuiButton(2, this.width / 2 - 105, this.height / 4 + 30, 30, 20, this.switchSideButtonText));
        this.buttonList.add(this.switchModeButton = new GuiButton(3, this.width / 2 - 105, this.height / 4 + 65, 30, 20, this.switchModeButtonText));
        (this.switchNameMech = new GuiTextField(this.fontRendererObj, this.width / 2 + 50, this.height / 4, 26, 10)).setMaxStringLength(3);
        this.switchNameMech.setText(this.thisTileE.getSwitchName());
    }

    public void drawScreen(final int mouseX, final int mouseY, final float par3) {
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(true);
            this.fontRendererObj.setBidiFlag(true);
        }
        this.drawDefaultBackground();
        this.drawSignalWNR();
        this.drawHorizontalLine(0, this.width, this.height / 32 * 4 + 4, new Color(255, 255, 255, 128).getRGB());
        this.drawCenteredString(this.fontRendererObj, this.editMsg_TITLE, this.width / 2, this.height / 4 - 64, 16777215);
        this.drawString(this.fontRendererObj, this.editMsg_WN, this.width / 2 - 220, this.height / 4 - 25, 16777215);
        this.drawString(this.fontRendererObj, this.editMsg_WN_2, this.width / 2 - 220, this.height / 4 - 15, 16777215);
        this.drawString(this.fontRendererObj, this.editMsg_WN_3, this.width / 2 - 220, this.height / 4 + 20, 16777215);
        this.drawString(this.fontRendererObj, this.editMsg_WN_4, this.width / 2 - 220, this.height / 4 + 55, 16777215);
        this.drawString(this.fontRendererObj, this.editMsg_WN_5, this.width / 2 + 30, this.height / 4 - 15, 16777215);
        this.switchNameMech.drawTextBox();
        super.drawScreen(mouseX, mouseY, par3);
    }

    protected void actionPerformed(final GuiButton button) {
        switch (button.id) {
            case 0: {
                this.thisTileE.markDirty();
                this.mc.displayGuiScreen(null);
                break;
            }
            case 1: {
                if (this.switchPosButton.displayString.contains(this.LeftRightStr[0])) {
                    this.switchPosButton.displayString = this.switchPosButton.displayString.replace(this.LeftRightStr[0], this.LeftRightStr[1]);
                    this.thisTileE.setSwitchPos(true);
                    break;
                }
                this.switchPosButton.displayString = this.switchPosButton.displayString.replace(this.LeftRightStr[1], this.LeftRightStr[0]);
                this.thisTileE.setSwitchPos(false);
                break;
            }
            case 2: {
                if (this.switchSideButton.displayString.contains(this.LeftRightStr[0])) {
                    this.switchSideButton.displayString = this.switchSideButton.displayString.replace(this.LeftRightStr[0], this.LeftRightStr[1]);
                    this.thisTileE.setSwitchSide(true);
                    break;
                }
                this.switchSideButton.displayString = this.switchSideButton.displayString.replace(this.LeftRightStr[1], this.LeftRightStr[0]);
                this.thisTileE.setSwitchSide(false);
                break;
            }
            case 3: {
                if (this.switchModeButton.displayString.contains(this.YesNoStr[0])) {
                    this.switchModeButton.displayString = this.switchModeButton.displayString.replace(this.YesNoStr[0], this.YesNoStr[1]);
                    this.thisTileE.setSwitchMode(true);
                    break;
                }
                this.switchModeButton.displayString = this.switchModeButton.displayString.replace(this.YesNoStr[1], this.YesNoStr[0]);
                this.thisTileE.setSwitchMode(false);
                break;
            }
        }
    }

    protected void mouseClicked(final int x, final int y, final int buttonClicked) {
        this.switchNameMech.mouseClicked(x, y, buttonClicked);
        super.mouseClicked(x, y, buttonClicked);
    }

    protected void keyTyped(final char character, final int code) {
        if (Character.isDigit(character) || code == 14) {
            this.switchNameMech.textboxKeyTyped(character, code);
        }
        if (code != 18 || !this.switchNameMech.isFocused()) {
            super.keyTyped(character, code);
        }
        if (code == 1) {
            this.actionPerformed(this.doneButton);
        }
    }

    public void updateScreen() {
        this.switchNameMech.updateCursorCounter();
        this.thisTileE.setSwitchName(this.switchNameMech.getText());
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(false);
            this.fontRendererObj.setBidiFlag(false);
        }
        Network.updateSwitches(thisTileE);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    private void loadValuesFromTile() {
        this.switchPosButtonText = this.LeftRightStr[this.thisTileE.getSwitchPos() ? 1 : 0];
        this.switchSideButtonText = this.LeftRightStr[this.thisTileE.getSwitchSide() ? 1 : 0];
        this.switchModeButtonText = this.YesNoStr[this.thisTileE.getSwitchMode() ? 1 : 0];
    }

    private void drawSignalWNR() {
        final float SizePercent = 85.0f;
        final int k = this.mc.theWorld.getBlockMetadata(this.thisTileE.xCoord, this.thisTileE.yCoord, this.thisTileE.zCoord);
        GL11.glPushMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glTranslatef((float) (this.width / 2), (float) this.height / 4 - 60.0f, 50.0f);
        GL11.glScalef(-SizePercent, -SizePercent, -SizePercent);
        GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
        final Block getBlockType = this.thisTileE.getBlockType();
        if (getBlockType instanceof BlockSwitch) {
            float angle = 0.0f;
            float translateX = 1.5f;
            float translateY = -1.0f;
            float translateZ = 0.0f;
            if (k == 0) {
                angle = 0.0f;
            }
            if (k == 1) {
                angle = 90.0f;
            }
            if (k == 2) {
                angle = 180.0f;
            }
            if (k == 3) {
                angle = -90.0f;
            }
            final float f2 = this.thisTileE.getBlockMetadata() * 16.0f / 360.0f;
            GL11.glRotatef(f2 + angle, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(translateX, translateY, translateZ);
            GL11.glRotatef(50.0f, 0.0f, 1.0f, 0.0f);
        } else {
            float angle2 = 0.0f;
            if (k == 2) {
                angle2 = 180.0f;
            }
            if (k == 4) {
                angle2 = 90.0f;
            }
            if (k == 5) {
                angle2 = -90.0f;
            }
            GL11.glRotatef(angle2, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(1.5f, -1.0f, 0.0f);
            GL11.glRotatef(50.0f, 0.0f, 1.0f, 0.0f);
        }
        TileEntityRendererDispatcher.instance.renderTileEntityAt(this.thisTileE, -0.5, -0.5, -0.5, 0.0f);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
