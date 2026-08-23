package signalcraft.entities.gsar.signalsWN;

import signalcraft.entities.switches.TileSwitch;
import signalcraft.signalUtils.Consts;

public class TileSwitchElectricGSAR extends TileSwitch {
    private final String motorsound;

    public TileSwitchElectricGSAR() {
        this.motorsound = "signalcraft:motor_sound";
        this.setGuiId(Consts.GuiIDs.SWITCH_ELECTRIC);
    }

    @Override
    public void updateEntity() {
        if (this.getIsSwitched() && this.getRotation() <= 22) {
            this.setRotation(this.getRotation() + 1);
        } else if (!this.getIsSwitched() && this.getRotation() > 0) {
            this.setRotation(this.getRotation() - 1);
        }
        if (!this.worldObj.isRemote) {
            if (this.getIsSwitched() && this.getRotation() == 1) {
                this.worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, this.motorsound, 1.0f, 1.0f);
            }
            if (!this.getIsSwitched() && this.getRotation() == 21) {
                this.worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, this.motorsound, 1.0f, 1.0f);
            }
        }
    }
}
