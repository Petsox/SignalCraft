package signalcraft.gui.signals.lightSignals.sssr;

import signalcraft.entities.signals.lightSignals.sssr.TileSSSR5Lights;
import signalcraft.gui.signals.lightSignals.GuiLightSignals;

public class GuiSSSR5Lights extends GuiLightSignals {
    public GuiSSSR5Lights(TileSSSR5Lights tileE) {
        super(tileE);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.SkupinoveButton.visible = false;
        this.Pruhy3Button.visible = false;
        this.TypeButton.visible = false;
    }
}