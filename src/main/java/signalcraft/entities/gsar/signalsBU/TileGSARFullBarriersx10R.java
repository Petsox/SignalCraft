package signalcraft.entities.gsar.signalsBU;

import net.minecraft.tileentity.TileEntity;
import signalcraft.entities.controllers.crossings.TileCrossingController;
import signalcraft.entities.levelCrossings.IBarriers;
import signalcraft.entities.levelCrossings.ILevelCrossing;
import signalcraft.entities.levelCrossings.IOnBarriers;

public class TileGSARFullBarriersx10R extends TileGSARCrossing implements IBarriers {
    private boolean toBeDeactivated = false;

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (getIsActive() && this.armRotation < 90) {

            ++this.activeReels;

            if (this.activeReels >= this.armDownDelay) {
                ++this.armRotation;
            }

            if (this.activeReels >= 35 && this.activeReels <= 370) {
                ++this.activeBell;

                if (this.activeBell >= 60) {
                    this.activeBell = 0;
                }
            }

        } else if (!getIsActive() && this.armRotation > 0) {

            --this.activeReels;
            --this.armRotation;

            if (this.armRotation == 0) {
                this.activeReels = 0;
                this.activeBell = 0;
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
    protected void handleSounds() {
        if (this.isActive) {
            if ((this.activeReels - this.bellDelay) % this.bellGap == 0) {
                playSound("signalcraft:ring1", 1.0f, 1.0f);
            } else if ((this.activeReels - this.bellDelay + 5) % this.bellGap == 0) {
                playSound("signalcraft:ring2", 1.0f, 1.0f);
            }
            if (this.activeReels == 340) {
                playSound("signalcraft:barrier_closed", 0.1f, 1.0f);
            }

        }
    }

    @Override
    public void setCrossingActive(final Boolean activated) {
        if (activated) this.toBeDeactivated = true;
        super.setCrossingActive(activated);
    }
}
