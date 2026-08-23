package signalcraft.gui.signals.lightSignals.sssr;

import signalcraft.entities.signals.lightSignals.sssr.TileSSSRPosun;
import signalcraft.gui.signals.lightSignals.GuiLightSignals;

public class GuiSSSRPosun extends GuiLightSignals {

    public GuiSSSRPosun(TileSSSRPosun tileE) {
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