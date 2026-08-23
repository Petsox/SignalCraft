package signalcraft.gui.signals.lightSignals.azd70;

import signalcraft.entities.signals.lightSignals.azd70.TileAZD4Lights;
import signalcraft.gui.signals.lightSignals.GuiLightSignals;
import signalcraft.signalUtils.Consts;

public class GuiAZD4Lights extends GuiLightSignals {
        private TileAZD4Lights tile;
        public GuiAZD4Lights(TileAZD4Lights tileE) {
                super(tileE);
                this.tile = tileE;
        }

        @Override
        public void initGui() {
                super.initGui();
                this.Pruhy3Button.visible = false;
        }

        @Override
        public void updateScreen() {
                super.updateScreen();
                this.PruhyButton.visible = tile.getType().equals(Consts.Types.TYPE_2);
        }
}