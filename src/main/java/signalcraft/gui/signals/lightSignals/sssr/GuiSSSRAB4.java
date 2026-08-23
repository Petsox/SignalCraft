package signalcraft.gui.signals.lightSignals.sssr;

import signalcraft.entities.signals.lightSignals.sssr.TileSSSRAB4;
import signalcraft.gui.signals.lightSignals.GuiLightSignals;

public class GuiSSSRAB4 extends GuiLightSignals {

    public GuiSSSRAB4(TileSSSRAB4 tileE) {
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