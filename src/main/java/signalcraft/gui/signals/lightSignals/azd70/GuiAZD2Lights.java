package signalcraft.gui.signals.lightSignals.azd70;

import signalcraft.entities.signals.lightSignals.azd70.TileAZD2Lights;
import signalcraft.gui.signals.lightSignals.GuiLightSignals;

public class GuiAZD2Lights extends GuiLightSignals {
        public GuiAZD2Lights(TileAZD2Lights tileE) {
                super(tileE);
        }

        @Override
        public void initGui() {
                super.initGui();
                this.PruhyButton.visible = false;
                this.Pruhy3Button.visible = false;
                this.SpeedButton.visible = false;
        }
}