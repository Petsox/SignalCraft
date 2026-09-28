package signalcraft.entities.gsar.signalsBU;

import signalcraft.entities.levelCrossings.IBarriers;

public class TileGSARFullBarriersx4R extends TileGSARCrossing implements IBarriers {

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
}
