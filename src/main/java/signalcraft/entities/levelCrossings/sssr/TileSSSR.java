package signalcraft.entities.levelCrossings.sssr;

import net.minecraft.tileentity.TileEntity;
import signalcraft.entities.levelCrossings.ILevelCrossing;
import signalcraft.entities.levelCrossings.IOnBarriers;
import signalcraft.entities.levelCrossings.TileLevelCrossing;
import signalcraft.signalUtils.Consts;

public class TileSSSR extends TileLevelCrossing {

    public TileSSSR() {
        this.setGuiId(Consts.GuiIDs.SSSR);
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
        this.setStozarDelsiCount(1);
    }

    @Override
    public void updateEntity() {
        if (this.isOtradovice()){
            if (this.BellDelayTimer > 0 && !getIsActive()) {
                --BellDelayTimer;
            }
            if (this.getIsActive() && this.getBlinkCounter() == Consts.SoundType.OTRADOVICE.soundTimer && !worldObj.isRemote) {
                int numRand = (int) Math.floor(Math.random() * 10);
                if (numRand < 7) {
                    this.worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, Consts.SoundType.OTRADOVICE.SoundLocation, 0.75f, 1.0f);
                } else if (numRand < 9) {
                    this.worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, Consts.SoundType.OTRADOVICE2.SoundLocation, 0.75f, 1.0f);
                } else {
                    this.worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, Consts.SoundType.OTRADOVICE3.SoundLocation, 0.75f, 1.0f);
                }
            }
        } else {
            super.updateEntity();
        }
    }

    @Override
    public int getBlinkCounter() {
        if (this.isOtradovice()) return computeBlinkPhase(Consts.SoundType.OTRADOVICE.blinkTimer);
        return super.getBlinkCounter();
    }

    @Override
    public int getPozitBlinkCounter() {
        if (this.isOtradovice()) return computeBlinkPhase(Consts.SoundType.OTRADOVICE.pozitBlinkTimer);
        return super.getPozitBlinkCounter();
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
