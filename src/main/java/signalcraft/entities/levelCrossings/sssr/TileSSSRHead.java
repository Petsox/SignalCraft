package signalcraft.entities.levelCrossings.sssr;

import net.minecraft.tileentity.TileEntity;
import signalcraft.entities.levelCrossings.ILevelCrossing;
import signalcraft.entities.levelCrossings.IOnBarriers;
import signalcraft.entities.levelCrossings.TileLevelCrossing;
import signalcraft.signalUtils.Consts;

public class TileSSSRHead extends TileLevelCrossing implements IOnBarriers {

    public TileSSSRHead() {
        this.setGuiId(Consts.GuiIDs.SSSR_HEAD);
        this.setSoundOn(true);
        this.setStrongSoundOn(true);
        this.setSoundType(Consts.SoundType.hornSP1318);
        this.setKrizJedno(true);
        this.setKrizVelky(true);
        this.setHasKriz(true);
        this.setHasPozLight(true);
        this.setUsePozLight(true);
        this.setHasZebrik(true);
        this.setLightPos(Consts.Position.MIDDLE);
        this.setCedule(Consts.CeduleState.UP);
        this.setHeadRot(0);
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
