package signalcraft.gui.signals.lightSignals.sssr;

import signalcraft.entities.signals.lightSignals.sssr.TileSSSR3Lights;
import signalcraft.gui.signals.lightSignals.GuiLightSignals;

public class GuiSSSR3Lights extends GuiLightSignals {
    public GuiSSSR3Lights(TileSSSR3Lights tileE) {
        super(tileE);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.SkupinoveButton.visible = false;
        this.PruhyButton.visible = false;
        this.Pruhy3Button.visible = false;
        this.SpeedButton.visible = false;
        this.TypeButton.visible = false;
    }
}