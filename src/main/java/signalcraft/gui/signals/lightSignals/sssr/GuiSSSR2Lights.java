package signalcraft.gui.signals.lightSignals.sssr;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import signalcraft.entities.signals.lightSignals.sssr.TileSSSR2Lights;
import signalcraft.gui.signals.lightSignals.GuiLightSignals;
import signalcraft.signalUtils.Consts;

public class GuiSSSR2Lights extends GuiLightSignals {

    private TileSSSR2Lights tile;

    public GuiSSSR2Lights(TileSSSR2Lights tileE) {
        super(tileE);
        this.tile = tileE;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.SkupinoveButton.visible = false;
        this.PruhyButton.visible = false;
        this.Pruhy3Button.visible = false;
        this.SpeedButton.visible = false;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 7) {
            if (this.tile.getType().equals(Consts.Types.TYPE_1)) {
                this.TypeButton.displayString = I18n.format("gui.lightsignal.type2.text");
                this.tile.setType(Consts.Types.TYPE_2);

            } else {
                this.TypeButton.displayString = I18n.format("gui.lightsignal.type1.text");
                this.tile.setType(Consts.Types.TYPE_1);

            }
        } else {
            super.actionPerformed(button);
        }
    }
}