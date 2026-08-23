package signalcraft.entities.levelCrossings.azd;

import net.minecraft.tileentity.TileEntity;
import signalcraft.entities.controllers.TileReceiver;
import signalcraft.entities.controllers.crossings.TileCrossingController;
import signalcraft.entities.controllers.crossings.TileCrossingReceiver;
import signalcraft.entities.levelCrossings.IBarriers;
import signalcraft.entities.levelCrossings.ILevelCrossing;
import signalcraft.entities.levelCrossings.IOnBarriers;

import signalcraft.entities.levelCrossings.TileLevelCrossing;
import signalcraft.signalUtils.Consts;

public class TileAZD99 extends TileLevelCrossing implements IBarriers {
    private int armDownDelayTimer = 0;
    public boolean toBeDeactivated = false;
    public TileAZD99() {
        this.setGuiId(Consts.GuiIDs.AZD99);
        this.setBarrierLength("4,2m");
        this.setArmRotation(0);
    }
    @Override
    public void updateEntity() {
        if (this.getIsActive() && this.getArmRotation() < 85 && armDownDelayTimer == 0) {
            this.setArmRotation(this.getArmRotation() + 1);
        } else if (this.getArmRotation() > 0 && this.getArmRotation() < 85 && getIsActive() && armDownDelayTimer > 0) {
            this.setArmRotation(this.getArmRotation() + 1);
        } else {
            --armDownDelayTimer;
        }
        if (!getIsActive() && this.getArmRotation() > 0) {
            this.setArmRotation(this.getArmRotation() - 1);
        }
        if (this.getArmRotation() == 85 && getIsActive()) {
            TileEntity tile = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
            if (tile instanceof IOnBarriers) ((IOnBarriers) tile).setStrongSoundOn(false);
        }
        if (this.getArmRotation() == 0 && !getIsActive() && toBeDeactivated) {
            TileEntity tile = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
            if (tile instanceof IOnBarriers) ((IOnBarriers) tile).setStrongSoundOn(true);
            TileCrossingReceiver below = getReceiverBelow();
            if (below != null) {
                // only raise the barrier-less receivers that share this barrier's own receiver name -
                // a controller can have several independently-named crossing groups paired to it, and
                // raising every barrier-less receiver on the controller would affect unrelated groups too
                String belowName = below.getName();
                for (TileCrossingController controller : below.getControllers()) {
                    if (controller == null) continue;
                    for (TileReceiver receiver : controller.getReceivers()) {
                        if (receiver instanceof TileCrossingReceiver
                                && !((TileCrossingReceiver) receiver).signalHasBarriers()
                                && belowName != null && belowName.equals(receiver.getName())) {
                            ((TileCrossingReceiver) receiver).setCrossingState(false);
                        }
                    }
                    if (tile instanceof IOnBarriers) ((ILevelCrossing) tile).setCrossingActive(false);
                }
            }
            toBeDeactivated = false;
        }
    }

    @Override
    public void setCrossingActive(final Boolean activated) {
        if (activated) {
            this.armDownDelayTimer = this.getArmDownDelay() * 20;
            toBeDeactivated = true;
        } else {
            this.armDownDelayTimer = 0;
        }
        super.setCrossingActive(activated);
    }

    @Override
    public boolean isArmDown() {
        return this.getArmRotation() == this.MAX_ARM_ANGLE;
    }
}
