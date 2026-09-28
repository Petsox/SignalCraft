package signalcraft.gui.levelCrossings.azd;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.levelCrossings.azd.TileAZD99;
import signalcraft.gui.ScalableGuiScreen;
import signalcraft.signalUtils.Network;

import java.awt.*;

public class GuiAZD99 extends ScalableGuiScreen {
    private GuiButton doneButton;
    private GuiButton LengthButton;
    private String LenghtText;
    private GuiButton isNewButton;
    private String isNewText;
    private GuiButton StozarDelsiButton;
    private String StozarDelsiText;
    private final TileAZD99 thisTileE;
    private GuiTextField textFieldArmDownDelay;
    private GuiTextField textFieldSignalOffDelay;
    protected GuiTextField Scale;


    public GuiAZD99(final TileAZD99 thisTileE) {
        Keyboard.enableRepeatEvents(true);
        this.allowUserInput = true;
        this.thisTileE = thisTileE;
    }

    @Override
    protected int getDesignWidth() {
        return 440;
    }

    @Override
    protected int getDesignHeight() {
        return 260;
    }

    public void initGui() {
        this.loadValuesFromTile();
        this.buttonList.add(this.doneButton = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 140, I18n.format("gui.done")));
        this.buttonList.add(this.LengthButton = new GuiButton(1, this.width / 2 - 120, this.height / 4 + 5, 30, 20, this.LenghtText));
        this.buttonList.add(this.isNewButton = new GuiButton(2, this.width / 2 - 25, this.height / 4 + 5, 30, 20, this.isNewText));
        this.buttonList.add(this.StozarDelsiButton = new GuiButton(3, this.width / 2 + 95, this.height / 4 + 5, 30, 20, this.StozarDelsiText));
        this.Scale = new GuiTextField(this.fontRendererObj, this.width / 2 + 30, this.height / 4 - 27, 80, 15);
        this.textFieldArmDownDelay = new GuiTextField(this.fontRendererObj, this.width / 2 - 75, this.height / 4 + 40, 50, 10);
        this.textFieldSignalOffDelay = new GuiTextField(this.fontRendererObj, this.width / 2 - 75, this.height / 4 + 65, 50, 10);
        this.Scale.setText(this.thisTileE.getScaleString());
        this.textFieldArmDownDelay.setText(Integer.toString(this.thisTileE.getArmDownDelay()));
        this.textFieldSignalOffDelay.setText(Integer.toString(this.thisTileE.getSignalOffDelay()));
    }

    public void drawScreen(final int mouseX, final int mouseY, final float par3) {
        this.beginContentScale();
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(true);
            this.fontRendererObj.setBidiFlag(true);
        }
        this.drawDefaultBackground();
        this.drawHorizontalLine(0, this.width, this.height / 32 * 4 + 4, new Color(255, 255, 255, 128).getRGB());
        this.drawModel();
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.arm.lenght.text"), this.width / 2 - 150, this.height / 4 + 10, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.arm.down.delay.text"), this.width / 2 - 150, this.height / 4 + 40, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.signal.off.delay.text"), this.width / 2 - 150, this.height / 4 + 65, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.pedestal"), this.width / 2 - 55, this.height / 4 + 10, 16777200);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.levelcross.azd99.stozardelsi.text"), this.width / 2 + 55, this.height / 4 + 10, 16777200);
        this.textFieldArmDownDelay.drawTextBox();
        this.textFieldSignalOffDelay.drawTextBox();
        this.Scale.drawTextBox();
        super.drawScreen(mouseX, mouseY, par3);
        this.endContentScale();
    }

    protected void actionPerformed(final GuiButton button) {
        switch (button.id) {
            case 0: {
                if (!this.textFieldArmDownDelay.getText().isEmpty()) thisTileE.setArmDownDelay(Integer.parseInt(textFieldArmDownDelay.getText()));
                if (!this.textFieldSignalOffDelay.getText().isEmpty()) thisTileE.setSignalOffDelay(Integer.parseInt(textFieldSignalOffDelay.getText()));
                this.thisTileE.markDirty();
                this.mc.displayGuiScreen(null);
                break;
            }
            case 1: {
                if (this.thisTileE.getBarrierLength().equals("4,2m")) {
                    this.LengthButton.displayString = I18n.format("gui.levelcross.arm.lenght.50.text");
                    this.thisTileE.setBarrierLength("5,0m");
                    break;
                } else if (this.thisTileE.getBarrierLength().equals("5,0m")) {
                    this.LengthButton.displayString = I18n.format("gui.levelcross.arm.lenght.55.text");
                    this.thisTileE.setBarrierLength("5,5m");
                    break;
                } else if (this.thisTileE.getBarrierLength().equals("5,5m")) {
                    this.LengthButton.displayString = I18n.format("gui.levelcross.arm.lenght.60.text");
                    this.thisTileE.setBarrierLength("6,0m");
                    break;
                } else if (this.thisTileE.getBarrierLength().equals("6,0m")) {
                    this.LengthButton.displayString = I18n.format("gui.levelcross.arm.lenght.65.text");
                    this.thisTileE.setBarrierLength("6,5m");
                    break;
                } else if (this.thisTileE.getBarrierLength().equals("6,5m")) {
                    this.LengthButton.displayString = I18n.format("gui.levelcross.arm.lenght.75.text");
                    this.thisTileE.setBarrierLength("7,5m");
                    break;
                } else if (this.thisTileE.getBarrierLength().equals("7,5m")) {
                    this.LengthButton.displayString = I18n.format("gui.levelcross.arm.lenght.42.text");
                    this.thisTileE.setBarrierLength("4,2m");
                    break;
                }
            }
            case 2: {
                if (this.thisTileE.isNewer()) {
                    this.isNewButton.displayString = I18n.format("gui.levelcross.isnew.false");
                    this.thisTileE.setNewer(false);
                    break;
                } else {
                    this.isNewButton.displayString = I18n.format("gui.levelcross.isnew.true");
                    this.thisTileE.setNewer(true);
                    break;
                }
            }
            case 3: {
                int next = (this.thisTileE.getStozarDelsiCount() + 1) % 6;
                this.thisTileE.setStozarDelsiCount(next);
                this.StozarDelsiButton.displayString = String.valueOf(next);
                break;
            }
        }
    }

    protected void mouseClicked(final int x, final int y, final int buttonClicked) {
        this.Scale.mouseClicked(x, y, buttonClicked);
        this.textFieldArmDownDelay.mouseClicked(x, y, buttonClicked);
        this.textFieldSignalOffDelay.mouseClicked(x, y, buttonClicked);
        super.mouseClicked(x, y, buttonClicked);
    }

    protected void keyTyped(final char character, final int code) {
        if (code == 1) {
            this.actionPerformed(this.doneButton);
            return;
        }
        if (this.Scale.getText().length() <= 3 || code == 14) {
            this.Scale.textboxKeyTyped(character, code);
        }
        if (Character.isDigit(character) || code == 14){
            this.textFieldArmDownDelay.textboxKeyTyped(character, code);
            this.textFieldSignalOffDelay.textboxKeyTyped(character, code);
        }
    }

    public void updateScreen() {
        this.Scale.updateCursorCounter();
        this.textFieldArmDownDelay.updateCursorCounter();
        this.textFieldSignalOffDelay.updateCursorCounter();
    }

    public void onGuiClosed() {
        try {
            float scale = Float.parseFloat(this.Scale.getText());
            if (scale < 0.1f) scale = 0.1f;
            if (scale > 2.0f) scale = 2.0f;
            thisTileE.setScale(scale);
        } catch (NumberFormatException e) {
            thisTileE.setScale(1.0f);
        }
        Keyboard.enableRepeatEvents(false);
        if (!this.mc.gameSettings.forceUnicodeFont) {
            this.fontRendererObj.setUnicodeFlag(false);
            this.fontRendererObj.setBidiFlag(false);
        }
        if (!this.textFieldArmDownDelay.getText().isEmpty()){
            this.thisTileE.setArmDownDelay(Integer.parseInt(this.textFieldArmDownDelay.getText()));
        }
        if (!this.textFieldSignalOffDelay.getText().isEmpty()){
            this.thisTileE.setSignalOffDelay(Integer.parseInt(this.textFieldSignalOffDelay.getText()));
        }
        Network.updateCrossings(this.thisTileE);
    }

    private void drawModel() {
        final float SizePercent = 50.0f;
        GL11.glPushMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glTranslatef((this.width / 5.0f) * 4, (this.height / 5.0f) * 4, 50.0f);
        GL11.glScalef(-SizePercent, -SizePercent, -SizePercent);
        final float angle = this.thisTileE.getBlockMetadata() * 360 / 16.0f;
        GL11.glRotatef(angle, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(180f, 0.0f, 1.0f, 0.0f);
        TileEntityRendererDispatcher.instance.renderTileEntityAt(this.thisTileE, -0.5, -0.5, -0.5, 0.0f);
        GL11.glPopMatrix();
    }

    private void loadValuesFromTile(){
        if (thisTileE.getBarrierLength().equals("4,2m")) {
            this.LenghtText = I18n.format("gui.levelcross.arm.lenght.42.text");
        } else if (thisTileE.getBarrierLength().equals("5,0m")) {
            this.LenghtText = I18n.format("gui.levelcross.arm.lenght.50.text");
        } else if (thisTileE.getBarrierLength().equals("5,5m")) {
            this.LenghtText = I18n.format("gui.levelcross.arm.lenght.55.text");
        } else if (thisTileE.getBarrierLength().equals("6,0m")) {
            this.LenghtText = I18n.format("gui.levelcross.arm.lenght.60.text");
        } else if (thisTileE.getBarrierLength().equals("6,5m")) {
            this.LenghtText = I18n.format("gui.levelcross.arm.lenght.65.text");
        } else if (thisTileE.getBarrierLength().equals("7,5m")) {
            this.LenghtText = I18n.format("gui.levelcross.arm.lenght.75.text");
        }

        if (thisTileE.isNewer()) {
            this.isNewText = I18n.format("gui.levelcross.isnew.true");
        } else {
            this.isNewText = I18n.format("gui.levelcross.isnew.false");
        }

        this.StozarDelsiText = String.valueOf(thisTileE.getStozarDelsiCount());
    }

    public boolean doesGuiPauseGame() {
        return false;
    }
}
