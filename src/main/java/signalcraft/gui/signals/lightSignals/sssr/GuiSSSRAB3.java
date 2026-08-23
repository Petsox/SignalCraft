package signalcraft.gui.signals.lightSignals.sssr;

import signalcraft.entities.signals.lightSignals.sssr.TileSSSRAB3;
import signalcraft.gui.signals.lightSignals.GuiLightSignals;

public class GuiSSSRAB3 extends GuiLightSignals {

    public GuiSSSRAB3(TileSSSRAB3 tileE) {
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