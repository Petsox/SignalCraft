package signalcraft.gui.signals.signSignals;

import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import signalcraft.entities.signals.signSignals.TileSpeedSign;

public class GuiSpeedSign extends GuiSignSignal{
    private static GuiTextField textField;
    private static TileSpeedSign thisTile;
    public GuiSpeedSign(TileSpeedSign thisTileE) {
        super(thisTileE);
        thisTile = thisTileE;
    }
    @Override
    public void initGui() {
        textField = new GuiTextField(this.fontRendererObj, this.width / 2 - 125, this.height / 4 - 15, 50, 10);
        super.initGui();
    }
    @Override
    protected void mouseClicked(final int x, final int y, final int buttonClicked) {
        textField.mouseClicked(x, y, buttonClicked);
        super.mouseClicked(x, y, buttonClicked);
    }
    @Override
    protected void keyTyped(final char character, final int code) {
        if (Character.isDigit(character) || code == 14) {
            if (textField.getText().length() <= 2 || code == 14){
                textField.textboxKeyTyped(character, code);
            }
        }
        if (code != 18 || !textField.isFocused()) {
            super.keyTyped(character, code);
        }
    }

    public void updateScreen() {
        textField.updateCursorCounter();
        super.updateScreen();
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float par3) {
        this.drawDefaultBackground();
        textField.drawTextBox();
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.speedSignText"), this.width / 2 - 150, this.height / 4 - 15, 16777200);
        super.drawScreen(mouseX, mouseY, par3);
    }

    @Override
    public void onGuiClosed() {
        if (!textField.getText().isEmpty()) {
            thisTile.setSpeedSignalText(textField.getText());
        }
        super.onGuiClosed();
    }
}
