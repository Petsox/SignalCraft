package signalcraft.gui.signals.lightSignals.azd70;

import signalcraft.entities.signals.lightSignals.azd70.TileAZDPosun;
import signalcraft.gui.signals.lightSignals.GuiLightSignals;

public class GuiAZDPosun extends GuiLightSignals {
    public GuiAZDPosun(TileAZDPosun tileE) {
        super(tileE);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.TypeButton.visible = false;
        this.PruhyButton.visible = false;
        this.Pruhy3Button.visible = false;
        this.OdjezdButton.visible = false;
        this.SpeedButton.visible = false;
    }
}