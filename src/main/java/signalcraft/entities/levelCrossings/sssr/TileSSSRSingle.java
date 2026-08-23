package signalcraft.entities.levelCrossings.sssr;

import net.minecraft.tileentity.TileEntity;
import signalcraft.entities.levelCrossings.ILevelCrossing;
import signalcraft.entities.levelCrossings.IOnBarriers;
import signalcraft.entities.levelCrossings.TileLevelCrossing;
import signalcraft.signalUtils.Consts;

public class TileSSSRSingle extends TileLevelCrossing {

    public TileSSSRSingle() {
        this.setGuiId(Consts.GuiIDs.SSSR_SINGLE);
        this.setSoundType(Consts.SoundType.hornSP1318);
        this.setKrizJedno(true);
        this.setSoundOn(true);
        this.setStrongSoundOn(true);
        this.setHasKrizNaStozaru(true);
        this.setHasKriz(true);
        this.setCedule(Consts.CeduleState.UP);
        this.setDistFromSloup(Consts.DistFromPole.DIST_30);
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
