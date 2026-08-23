package signalcraft.entities.gsar.signalsWN;

import signalcraft.entities.switches.IManual;
import signalcraft.entities.switches.TileSwitch;
import signalcraft.signalUtils.Consts;

public class TileSwitchManualGSAR extends TileSwitch implements IManual {

    public TileSwitchManualGSAR() {
        this.setGuiId(Consts.GuiIDs.SWITCH_MANUAL);
    }

    @Override
    public void updateEntity() {
        if (this.getIsSwitched() && this.getRotation() <= 22) {
            this.setRotation(this.getRotation() + 1);
        } else if (!this.getIsSwitched() && this.getRotation() > 0) {
            this.setRotation(this.getRotation() - 1);
        }
    }
}
