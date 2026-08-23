package signalcraft.entities.gsar.signalsBU;


import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import signalcraft.entities.IActivatable;
import signalcraft.entities.TileSignal;
import signalcraft.entities.controllers.crossings.TileCrossingReceiver;
import signalcraft.entities.levelCrossings.ILevelCrossing;
import signalcraft.signalUtils.LampFade;

public abstract class TileGSARCrossing extends TileSignal implements ILevelCrossing, IActivatable {

    protected boolean isActive;
    protected final int MAX_ARM_ANGLE = 90;
    protected int armRotation;
    protected int activeReels;
    protected int activeBell;
    /** Client-only render state easing the warning lamp's brightness between on/off; never persisted. */
    private final LampFade lampFade = new LampFade();

    private String barrierLength = "0";
    protected String armDownDelayString = "1";

    protected int armDownDelay = 281;
    protected final int bellGap = 60;
    protected final int bellDelay = 30;
    protected boolean soundOn = true;

    public int blinkCounter;

    @Override
    public void updateEntity() {

        ++this.blinkCounter;

        if (this.blinkCounter >= 61) {
            this.blinkCounter = 0;
        }

        if (!this.worldObj.isRemote && this.soundOn) {
            handleSounds();
        }
    }

    protected abstract void handleSounds();

    protected void playSound(String sound, float volume, float pitch) {
        this.worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, sound, volume, pitch);
    }

    public int getBarrierLength() {
        return Integer.parseInt(barrierLength);
    }

    public void setBarrierLength(int barrierLength) {
        this.barrierLength = String.valueOf(barrierLength);
    }

    public void setArmDownDelay(int armDownDelay) {
        this.armDownDelayString = String.valueOf(armDownDelay);
    }

    public int getArmDownDelay() {
        return Integer.parseInt(armDownDelayString);
    }

    @Override
    public void readFromNBT(final NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.isActive = compound.getBoolean("isActive");
        this.armRotation = compound.getInteger("armRotation");
        this.activeReels = compound.getInteger("activeReels");
        this.activeBell = compound.getInteger("activeBell");
        this.barrierLength = compound.getString("BarrierLength");
        this.armDownDelayString = compound.getString("armDownDelay");
    }

    @Override
    public void writeToNBT(final NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setBoolean("isActive", this.isActive);
        compound.setInteger("armRotation", this.armRotation);
        compound.setInteger("activeReels", this.activeReels);
        compound.setInteger("activeBell", this.activeBell);
        compound.setString("BarrierLength", this.barrierLength);
        compound.setString("armDownDelay", this.armDownDelayString);
    }

    @Override
    public TileCrossingReceiver getReceiverBelow() {
        for (int i = 1; i < 10; i++) {
            TileEntity tile = worldObj.getTileEntity(xCoord, yCoord - i, zCoord);
            if (tile instanceof TileCrossingReceiver) {
                return (TileCrossingReceiver) tile;
            }
        }
        return new TileCrossingReceiver();
    }

    public boolean isArmDown() {
        return this.getArmRotation() == this.MAX_ARM_ANGLE;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getArmRotation() {
        return armRotation;
    }

    public int getActiveReels() {
        return activeReels;
    }

    public int getActiveBell() {
        return activeBell;
    }

    public LampFade getLampFade() {
        return lampFade;
    }

    @Override
    public void setIsActive(Boolean active) {
        this.setCrossingActive(active);
    }

    @Override
    public boolean isCrossingActive() {
        return isActive;
    }

    @Override
    public Boolean getIsActive() {
        return this.isActive;
    }

    @Override
    public void setSoundOn(Boolean soundOn) {
        this.soundOn = soundOn;
    }

    @Override
    public void setBlinkCounter(int counter) {}

    @Override
    public void setCrossingActive(Boolean activated) {
        this.isActive = activated;
    }
}