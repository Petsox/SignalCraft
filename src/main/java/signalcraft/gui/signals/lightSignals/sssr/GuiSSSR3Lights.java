package signalcraft.gui.signals.lightSignals.sssr;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import signalcraft.entities.signals.lightSignals.sssr.TileSSSR3Lights;
import signalcraft.gui.signals.lightSignals.GuiLightSignals;
import signalcraft.signalUtils.Consts;

public class GuiSSSR3Lights extends GuiLightSignals {
    private final TileSSSR3Lights thisTileE;

    public GuiSSSR3Lights(TileSSSR3Lights tileE) {
        super(tileE);
        this.thisTileE = tileE;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.SkupinoveButton.visible = false;
        this.PruhyButton.visible = false;
        this.Pruhy3Button.visible = false;
        this.SpeedButton.visible = false;
    }

    // SSSR3Lights only has Type 1 (current, single-slot-per-color arrangement) and
    // Type 2 (yellow top / red middle / white bottom) -- unlike the AZD family this
    // shared GUI otherwise serves, it doesn't have Type 3/4, so the generic 4-way
    // cycle in the base class is overridden here to a plain toggle between the two.
    @Override
    protected void actionPerformed(final GuiButton button) {
        if (button.id == 7) {
            if (this.thisTileE.getType().equals(Consts.Types.TYPE_1)) {
                this.TypeButton.displayString = I18n.format("gui.lightsignal.type2.text");
                this.thisTileE.setType(Consts.Types.TYPE_2);
            } else {
                this.TypeButton.displayString = I18n.format("gui.lightsignal.type1.text");
                this.thisTileE.setType(Consts.Types.TYPE_1);
            }
            return;
        }
        super.actionPerformed(button);
    }
}
