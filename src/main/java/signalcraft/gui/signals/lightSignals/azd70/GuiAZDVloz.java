package signalcraft.gui.signals.lightSignals.azd70;

import signalcraft.entities.signals.lightSignals.azd70.TileAZDVloz;
import signalcraft.gui.signals.lightSignals.GuiLightSignals;

public class GuiAZDVloz extends GuiLightSignals {
    public GuiAZDVloz(TileAZDVloz tileE) {
        super(tileE);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.TypeButton.visible = false;
        this.PruhyButton.visible = false;
        this.Pruhy3Button.visible = false;
        this.OdjezdButton.visible = false;
        this.SkupinoveButton.visible = false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float par3) {
        super.drawScreen(mouseX, mouseY, par3);
    }

    @Override
    protected void mouseClicked(int x, int y, int buttonClicked) {
        super.mouseClicked(x, y, buttonClicked);
    }

    @Override
    protected void keyTyped(char character, int code) {
        super.keyTyped(character, code);
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }
}