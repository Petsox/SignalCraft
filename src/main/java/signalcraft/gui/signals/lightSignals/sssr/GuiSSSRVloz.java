package signalcraft.gui.signals.lightSignals.sssr;

import signalcraft.entities.signals.lightSignals.sssr.TileSSSRVloz;
import signalcraft.gui.signals.lightSignals.GuiLightSignals;

public class GuiSSSRVloz extends GuiLightSignals {

    public GuiSSSRVloz(TileSSSRVloz tileE) {
        super(tileE);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.SkupinoveButton.visible = false;
        this.PruhyButton.visible = false;
        this.Pruhy3Button.visible = false;
        this.SpeedButton.visible = false;
        this.OdjezdButton.visible = false;
        this.TypeButton.visible = false;
    }
}