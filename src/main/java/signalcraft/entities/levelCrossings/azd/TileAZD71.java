package signalcraft.entities.levelCrossings.azd;

import net.minecraft.tileentity.TileEntity;
import signalcraft.entities.levelCrossings.ILevelCrossing;
import signalcraft.entities.levelCrossings.IOnBarriers;
import signalcraft.entities.levelCrossings.TileLevelCrossing;
import signalcraft.signalUtils.Consts;

public class TileAZD71 extends TileLevelCrossing {

    public TileAZD71() {
        this.setGuiId(Consts.GuiIDs.AZD71);
        this.setSoundOn(true);
        this.setStrongSoundOn(true);
        this.setSoundType(Consts.SoundType.cinkP3597);
        this.setKrizJedno(true);
        this.setHasKriz(true);
        this.setHasPozLight(true);
        this.setUsePozLight(true);
        this.setDistFromSloup(Consts.DistFromPole.DIST_50);
    }

    @Override
    public void setCrossingActive(Boolean activated) {
        TileEntity tileOnThis = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
        if (!activated && tileOnThis instanceof IOnBarriers) {
            ((ILevelCrossing) tileOnThis).setCrossingActive(false);
        }
        super.setCrossingActive(activated);
    }
}
