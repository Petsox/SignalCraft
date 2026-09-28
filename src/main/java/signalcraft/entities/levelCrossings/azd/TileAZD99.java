package signalcraft.entities.levelCrossings.azd;

import signalcraft.entities.levelCrossings.IBarriers;

import signalcraft.entities.levelCrossings.TileLevelCrossing;
import signalcraft.signalUtils.Consts;

public class TileAZD99 extends TileLevelCrossing implements IBarriers {
    private int armDownDelayTimer = 0;
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
    }

    @Override
    public void setCrossingActive(final Boolean activated) {
        if (activated) {
            this.armDownDelayTimer = this.getArmDownDelay() * 20;
        } else {
            this.armDownDelayTimer = 0;
        }
        super.setCrossingActive(activated);
    }

    @Override
    public boolean isArmDown() {
        return this.getArmRotation() == this.MAX_ARM_ANGLE;
    }

    @Override
    public boolean isArmUp() {
        return this.getArmRotation() == this.MIN_ARM_ANGLE;
    }
}
