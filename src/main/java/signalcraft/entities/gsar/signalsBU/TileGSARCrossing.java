package signalcraft.entities.gsar.signalsBU;


import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import signalcraft.entities.IActivatable;
import signalcraft.entities.TileSignal;
import signalcraft.entities.controllers.crossings.TileCrossingReceiver;
import signalcraft.entities.levelCrossings.BarrierWait;
import signalcraft.entities.levelCrossings.CrossingEvent;
import signalcraft.entities.levelCrossings.ICrossingRuntime;
import signalcraft.entities.levelCrossings.ILevelCrossing;
import signalcraft.messages.MessageCrossingEvent;
import signalcraft.signalUtils.CrossingDebug;
import signalcraft.signalUtils.LampFade;
import signalcraft.signalUtils.BlinkClock;

public abstract class TileGSARCrossing extends TileSignal implements ILevelCrossing, IActivatable, ICrossingRuntime {

    protected boolean isActive;
    protected final int MAX_ARM_ANGLE = 90;
    protected final int MIN_ARM_ANGLE = 0;
    protected int armRotation;
    protected int activeReels;
    protected int activeBell;
    /** Client-only render state easing the warning lamp's brightness between on/off; never persisted. */
    private final LampFade lampFade = new LampFade();
    /** Client-only: tracks which points of the blink cycle passed since the last tick, to ring bells on. */
    protected final BlinkClock.Beat beat = new BlinkClock.Beat();
    /** Client-only: when this crossing switched on, so its lamps and bells start their cycle right then. */
    protected final BlinkClock.Activation activation = new BlinkClock.Activation();

    private String barrierLength = "0";
    protected String armDownDelayString = "1";

    protected int armDownDelay = 281;
    protected final int bellGap = 60;
    protected final int bellDelay = 30;
    protected boolean soundOn = true;
    protected int signalOffDelay = 0;
    /** Pending switch-off once given barriers are up (CrossingEvent.OFF_WHEN_UP). Runtime, judged by each side against its own barriers. */
    protected final BarrierWait offWait = new BarrierWait();

    @Override
    public void updateEntity() {
        if (this.isActive && offWait.tick(this)) {
            this.isActive = false;
            CrossingDebug.log(this, "-> OFF");
        }
        // Sounds are played by each client in step with what it renders - the server never plays
        // them (see BlinkClock for why). Barrier sounds follow activeReels, which the client
        // counts up itself alongside the arm.
        if (this.worldObj.isRemote) {
            beat.tick();
            activation.ticksActive(this.isActive);
            if (this.soundOn) {
                handleSounds();
            }
        }
    }

    /**
     * Blink phase for the lamps - client side runs from BlinkClock, with the cycle starting the
     * moment the crossing switches on (see TileLevelCrossing.computeActiveBlinkPhase()).
     */
    public int getBlinkCounter() {
        if (worldObj == null) return 0;
        if (worldObj.isRemote) return activation.getPhase(this.isActive, BLINK_PERIOD);
        return (int) (worldObj.getTotalWorldTime() % BLINK_PERIOD);
    }

    protected static final int BLINK_PERIOD = 61;

    /** Called on the client only, every tick while sound is on. */
    protected abstract void handleSounds();

    /** Plays a sound at this crossing for this client only. */
    protected void playSound(String sound, float volume, float pitch) {
        this.worldObj.playSound(this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5, sound, volume, pitch, false);
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

    public int getSignalOffDelay() {
        return signalOffDelay;
    }

    public void setSignalOffDelay(int signalOffDelay) {
        this.signalOffDelay = signalOffDelay;
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
        this.signalOffDelay = compound.getInteger("signalOffDelay");
        this.offWait.read(compound);
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
        compound.setInteger("signalOffDelay", this.signalOffDelay);
        this.offWait.write(compound);
    }

    @Override
    public void writeRuntime(NBTTagCompound tag) {
        tag.setBoolean("isActive", this.isActive);
        tag.setInteger("armRotation", this.armRotation);
        tag.setInteger("activeReels", this.activeReels);
        tag.setInteger("activeBell", this.activeBell);
        this.offWait.write(tag);
    }

    @Override
    public void readRuntime(NBTTagCompound tag) {
        this.isActive = tag.getBoolean("isActive");
        this.armRotation = tag.getInteger("armRotation");
        this.activeReels = tag.getInteger("activeReels");
        this.activeBell = tag.getInteger("activeBell");
        this.offWait.read(tag);
    }

    @Override
    public String describeRuntime() {
        return "active=" + this.isActive + " arm=" + this.armRotation + " reels=" + this.activeReels + " " + this.offWait.describe();
    }

    /** GSAR barriers go up one degree per tick. */
    public int ticksUntilArmUp() {
        return Math.max(0, this.armRotation);
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

    public boolean isArmUp() {
        return this.getArmRotation() == this.MIN_ARM_ANGLE;
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
        fire(activated ? CrossingEvent.on() : CrossingEvent.off());
    }

    @Override
    public void deactivateWhenBarriersUp(int[] barriers, int delayTicks) {
        fire(CrossingEvent.offWhenUp(barriers, delayTicks));
    }

    private void fire(CrossingEvent event) {
        if (worldObj == null || worldObj.isRemote) return;     // only the server decides events
        if (applyCrossingEvent(event)) {
            MessageCrossingEvent.send(this, event);
        }
    }

    @Override
    public boolean applyCrossingEvent(CrossingEvent event) {
        switch (event.type) {
            case CrossingEvent.ON:
                if (this.isActive && !offWait.isRunning()) return false;
                this.isActive = true;
                this.offWait.clear();
                return true;
            case CrossingEvent.OFF:
                if (!this.isActive) return false;
                this.isActive = false;
                this.offWait.clear();
                return true;
            case CrossingEvent.OFF_WHEN_UP:
                if (!this.isActive) return false;
                if (event.barriers.length == 0 && event.delay <= 0) {
                    this.isActive = false;
                    this.offWait.clear();
                } else {
                    this.offWait.start(event.barriers, event.delay);
                }
                return true;
            default:
                return false;
        }
    }
}