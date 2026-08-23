package signalcraft.gui.signals.lightSignals.azd70;

import signalcraft.entities.signals.lightSignals.azd70.TileAZDAB4;
import signalcraft.gui.signals.lightSignals.GuiLightSignals;

public class GuiAZDAB4 extends GuiLightSignals {
    public GuiAZDAB4(TileAZDAB4 tileE) {
        super(tileE);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.TypeButton.visible = false;
        this.PruhyButton.visible = false;
        this.OdjezdButton.visible = false;
        this.Pruhy3Button.visible = false;
        this.SpeedButton.visible = false;
    }
}