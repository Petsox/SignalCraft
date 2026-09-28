package signalcraft.entities.gsar.signalsBU;

import signalcraft.entities.levelCrossings.IBarriers;

public class TileGSARHalfBarrierR extends TileGSARCrossing implements IBarriers {

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
    }

    @Override
    protected void handleSounds() {}
}
