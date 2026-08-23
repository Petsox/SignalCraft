package signalcraft.entities.levelCrossings.azd;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import signalcraft.entities.levelCrossings.IAnglesAddable;
import signalcraft.entities.levelCrossings.ILevelCrossing;
import signalcraft.entities.levelCrossings.IOnBarriers;
import signalcraft.entities.levelCrossings.TileLevelCrossing;
import signalcraft.signalUtils.Consts;

public class TileAZD71Head extends TileLevelCrossing implements IOnBarriers, IAnglesAddable {

    public TileAZD71Head() {
        this.setGuiId(Consts.GuiIDs.AZD71_HEAD);
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

    @Override
    public int[] getAngles() {
        return angles;
    }

    @Override
    public void addAngle(int newAngle) {
        int[] newArray = new int[angles.length + 1];
        System.arraycopy(angles, 0, newArray, 0, angles.length);
        newArray[angles.length] = newAngle;
        this.angles = newArray;
    }

    @Override
    public void removeLastAngle() {
        if (angles.length > 1) {
            int[] newArray = new int[angles.length - 1];
            System.arraycopy(angles, 0, newArray, 0, angles.length - 1);
            this.angles = newArray;
        }
    }

    @Override
    public void readFromNBT(final NBTTagCompound compound) {
        super.readFromNBT(compound);

        if (compound.getIntArray("angles").length == 0){
            this.addAngle(0);
        } else {
            this.angles = compound.getIntArray("angles");
        }
    }

    @Override
    public void writeToNBT(final NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setIntArray("angles", this.angles);
    }
}
