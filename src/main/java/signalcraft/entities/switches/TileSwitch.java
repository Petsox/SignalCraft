package signalcraft.entities.switches;

import net.minecraft.nbt.NBTTagCompound;
import signalcraft.SignalCraft;
import signalcraft.entities.IActivatable;
import signalcraft.entities.TileSignal;
import signalcraft.messages.MessageActiveUpdate;
import signalcraft.signalUtils.Consts;


public class TileSwitch extends TileSignal implements IActivatable {

    /**
     * String[] switchPropArrLenght - Lenght in Consts.java
     * [0] - isSwitched
     * [1] - rotation
     * [2] - switchName
     * [3] - switchPos
     * [4] - switchSide
     * [5] - switchMode
     * [6] - isOutputtingRedstone
     * [7] - isInverted
     */
    private final String[] switchProperties;

    public TileSwitch() {
        this.switchProperties = new String[Consts.switchPropArrLenght];
        this.setIsSwitched(false);
        this.setRotation(0);
        this.setSwitchPos(false);
        this.setSwitchSide(false);
        this.setSwitchMode(false);
        this.setSwitchName("");
        this.setOutputRedstone(false);
        this.setIsInverted(false);
    }

    private void setIsSwitched(boolean isSwitched) {
        this.switchProperties[0] = Boolean.toString(isSwitched);
    }

    public void setRotation(int rotation) {
        this.switchProperties[1] = Integer.toString(rotation);
    }

    public void setSwitchName(String switchName) {
        this.switchProperties[2] = switchName == null ? null : switchName.replaceAll("[^0-9]", "");
    }

    public void setSwitchPos(boolean switchPos) {
        this.switchProperties[3] = Boolean.toString(switchPos);
    }

    public void setSwitchSide(boolean switchSide) {
        this.switchProperties[4] = Boolean.toString(switchSide);
    }

    public void setSwitchMode(boolean switchMode) {
        this.switchProperties[5] = Boolean.toString(switchMode);
    }

    public void setOutputRedstone(boolean outputRedstone) {
        this.switchProperties[6] = Boolean.toString(outputRedstone);
    }

    public void setIsInverted(boolean isInverted) {
        this.switchProperties[7] = Boolean.toString(isInverted);
    }

    public boolean getIsSwitched() {
        return Boolean.parseBoolean(this.switchProperties[0]);
    }

    public int getRotation() {
        return Integer.parseInt(this.switchProperties[1]);
    }

    public String getSwitchName() {
        return this.switchProperties[2];
    }

    public boolean getSwitchPos() {
        return Boolean.parseBoolean(this.switchProperties[3]);
    }

    public boolean getSwitchSide() {
        return Boolean.parseBoolean(this.switchProperties[4]);
    }

    public boolean getSwitchMode() {
        return Boolean.parseBoolean(this.switchProperties[5]);
    }

    public boolean isOutputtingRedstone() {
        return Boolean.parseBoolean(this.switchProperties[6]);
    }

    public boolean isInverted() {
        return Boolean.parseBoolean(this.switchProperties[7]);
    }

    @Override
    public void setName(String name) {
        this.setSwitchName(name);
    }

    @Override
    public String getName() {
        return this.getSwitchName();
    }

    public void changeRedstoneExtractStatus(final boolean setStatus) {
        this.setOutputRedstone(setStatus);
        this.worldObj.notifyBlockChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
        this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType());
    }


    @Override
    public void readFromNBT(final NBTTagCompound NBTTC) {
        super.readFromNBT(NBTTC);
        this.switchProperties[0] = NBTTC.getString("isSwitched");
        this.switchProperties[1] = NBTTC.getString("rotation");
        this.switchProperties[2] = NBTTC.getString("switchName");
        this.switchProperties[3] = NBTTC.getString("switchPos");
        this.switchProperties[4] = NBTTC.getString("switchSide");
        this.switchProperties[5] = NBTTC.getString("switchMode");
        this.switchProperties[6] = NBTTC.getString("isOutputtingRedstone");
        this.switchProperties[7] = NBTTC.getString("isInverted");
    }

    @Override
    public void writeToNBT(final NBTTagCompound NBTTC) {
        super.writeToNBT(NBTTC);
        NBTTC.setString("isSwitched", this.switchProperties[0]);
        NBTTC.setString("rotation", this.switchProperties[1]);
        NBTTC.setString("switchName", this.switchProperties[2]);
        NBTTC.setString("switchPos", this.switchProperties[3]);
        NBTTC.setString("switchSide", this.switchProperties[4]);
        NBTTC.setString("switchMode", this.switchProperties[5]);
        NBTTC.setString("isOutputtingRedstone", this.switchProperties[6]);
        NBTTC.setString("isInverted", this.switchProperties[7]);
    }

    @Override
    public void setIsActive(Boolean active) {
        if (getIsSwitched() != active) {
            SignalCraft.SCNet.sendToAll(new MessageActiveUpdate(this.xCoord, this.yCoord, this.zCoord, active));
            this.setIsSwitched(active);
            worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, this.getBlockType());
        }
    }

    @Override
    public Boolean getIsActive() {
        return null;
    }

    @Override
    public void setBlinkCounter(int counter) {
    }

}
