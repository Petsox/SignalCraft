package signalcraft.gui.signals.lightSignals.azd70;

import net.minecraft.client.resources.I18n;
import signalcraft.entities.signals.lightSignals.azd70.TileAZDAB3;
import signalcraft.gui.signals.lightSignals.GuiLightSignals;

public class GuiAZDAB3 extends GuiLightSignals {

    public GuiAZDAB3(TileAZDAB3 tileE) {
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