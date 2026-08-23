package signalcraft.gui.signals.lightSignals.azd70;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.entities.signals.lightSignals.azd70.TileAZD2LightsT;
import signalcraft.gui.signals.lightSignals.GuiLightSignals;
import signalcraft.signalUtils.Consts;

public class GuiAZD2LightsT extends GuiLightSignals {
    TileLightSignal thisTileE;

    public GuiAZD2LightsT(TileAZD2LightsT tileE) {
        super(tileE);
        this.thisTileE = tileE;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.PruhyButton.visible = false;
        this.PoziceButton.visible = false;
        this.Pruhy3Button.visible = false;
        this.SpeedButton.visible = false;
        this.SkupinoveButton.visible = false;
    }
    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0: {
                this.thisTileE.markDirty();
                this.mc.displayGuiScreen(null);
                break;
            }
            case 1: {
                if (this.thisTileE.getIsDeparture().toBoolean()) {
                    this.OdjezdButton.displayString = I18n.format("gui.general.text.no");
                    this.thisTileE.setIsDeparture(Consts.BooleanSTR.NO);
                    break;
                }
                this.OdjezdButton.displayString = I18n.format("gui.general.text.yes");
                this.thisTileE.setIsDeparture(Consts.BooleanSTR.YES);
                break;
            }
            case 7: {
                if (this.thisTileE.getType().equals(Consts.Types.TYPE_1)) {
                    this.TypeButton.displayString = I18n.format("gui.lightsignal.type2.text");
                    this.thisTileE.setType(Consts.Types.TYPE_2);
                    break;
                } else {
                    this.TypeButton.displayString = I18n.format("gui.lightsignal.type1.text");
                    this.thisTileE.setType(Consts.Types.TYPE_1);
                    break;
                }
            }
        }
    }
}