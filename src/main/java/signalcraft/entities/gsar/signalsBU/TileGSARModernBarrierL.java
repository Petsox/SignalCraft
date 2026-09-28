package signalcraft.entities.gsar.signalsBU;

import signalcraft.entities.levelCrossings.IBarriers;
import signalcraft.signalUtils.Consts;

public class TileGSARModernBarrierL extends TileGSARCrossing implements IBarriers {

    public int armDownDelay = 4;

    public TileGSARModernBarrierL() {
        this.setBarrierLength(1);
        this.setGuiId(Consts.GuiIDs.MODERN_BARRIERS);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (getIsActive() && this.armRotation < 90) {

            ++this.activeReels;

            if (this.activeReels >= this.armDownDelay * 20) {
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
