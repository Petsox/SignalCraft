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

    /** Otradovice: one of three random sounds on each beat instead of the bell. */
    @Override
    protected void tickClientSounds() {
        if (!this.isOtradovice()) {
            super.tickClientSounds();
            return;
        }
        if (this.getIsActive() && beat.hit(Consts.SoundType.OTRADOVICE.blinkTimer, Consts.SoundType.OTRADOVICE.soundTimer, activation.since())) {
            int numRand = (int) Math.floor(Math.random() * 10);
            if (numRand < 7) {
                playLocalSound(Consts.SoundType.OTRADOVICE.SoundLocation);
            } else if (numRand < 9) {
                playLocalSound(Consts.SoundType.OTRADOVICE2.SoundLocation);
            } else {
                playLocalSound(Consts.SoundType.OTRADOVICE3.SoundLocation);
            }
        }
    }

    @Override
    public int getBlinkCounter() {
        if (this.isOtradovice()) return computeActiveBlinkPhase(Consts.SoundType.OTRADOVICE.blinkTimer);
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
