package signalcraft.entities.gsar.signalsBU;

import net.minecraft.tileentity.TileEntity;
import signalcraft.entities.controllers.crossings.TileCrossingController;
import signalcraft.entities.levelCrossings.IBarriers;
import signalcraft.entities.levelCrossings.ILevelCrossing;
import signalcraft.entities.levelCrossings.IOnBarriers;

public class TileGSARHalfBarrierL extends TileGSARCrossing implements IBarriers {

    private boolean toBeDeactivated = false;

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (getIsActive() && this.armRotation < 90) {

            ++this.activeReels;

            if (this.activeReels >= this.armDownDelay) {
                ++this.armRotation;
            }

        } else if (!getIsActive() && this.armRotation > 0) {

            --this.activeReels;
            --this.armRotation;

            if (this.armRotation == 0) {
                this.activeReels = 0;
            }
        }

        if (this.armRotation == 90 && getIsActive()) {
            TileEntity tile = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
            if (tile instanceof IOnBarriers) ((IOnBarriers) tile).setStrongSoundOn(false);
        }
        if (this.armRotation == 0 && !getIsActive() && toBeDeactivated) {
            TileEntity tile = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
            if (tile instanceof IOnBarriers) ((IOnBarriers) tile).setStrongSoundOn(true);
            if (getReceiverBelow() != null) {
                for (TileCrossingController controller : getReceiverBelow().getControllers()) {
                    if (controller == null) continue;
                    controller.updateReceiversWithoutBarriers(false);
                    if (tile instanceof IOnBarriers) ((ILevelCrossing) tile).setCrossingActive(false);
                }
            }
            toBeDeactivated = false;
        }
    }

    @Override
    protected void handleSounds() {}

    @Override
    public void setCrossingActive(final Boolean activated) {
        if (activated) this.toBeDeactivated = true;
        super.setCrossingActive(activated);
    }
}
