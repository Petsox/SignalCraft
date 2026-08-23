package signalcraft.gui.signals.lightSignals.sssr;

import signalcraft.entities.signals.lightSignals.sssr.TileSSSR4Lights;
import signalcraft.gui.signals.lightSignals.GuiLightSignals;
import signalcraft.signalUtils.Consts;

public class GuiSSSR4Lights extends GuiLightSignals {
    private TileSSSR4Lights tile;
    public GuiSSSR4Lights(TileSSSR4Lights tileE) {
        super(tileE);
        this.tile = tileE;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.SkupinoveButton.visible = false;
        this.Pruhy3Button.visible = false;
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        this.PruhyButton.visible = tile.getType().equals(Consts.Types.TYPE_2);
    }

}