package signalcraft.entities.levelCrossings;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import signalcraft.entities.IActivatable;
import signalcraft.entities.TileSignal;
import signalcraft.entities.controllers.crossings.TileCrossingReceiver;
import signalcraft.messages.MessageCrossingEvent;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.CrossingDebug;
import signalcraft.signalUtils.LampFade;
import signalcraft.signalUtils.BlinkClock;

public abstract class TileLevelCrossing extends TileSignal implements IActivatable, ILevelCrossing, ICrossingRuntime {
    protected final int MAX_ARM_ANGLE = 85;
    protected final int MIN_ARM_ANGLE = 0;
    /** Ticks the poz (white) light still waits after the crossing switched off. Runtime, simulated on both sides. */
    protected int BellDelayTimer;
    protected int[] angles = {0};
    /** Pending switch-off once given barriers are up (CrossingEvent.OFF_WHEN_UP). Runtime, judged by each side against its own barriers. */
    private final BarrierWait offWait = new BarrierWait();
    /** Server only: world tick up to which this tile's state has been simulated (see catchUp), -1 = not yet. */
    private long simulatedTo = -1;
    private boolean catchingUp = false;
    /** Longest gap catchUp simulates step by step; past that the state has long settled anyway. */
    private static final int MAX_CATCH_UP = 2400;
    /** catchUp logs only gaps longer than this (ticks). */
    private static final int CATCH_UP_LOG_MIN = 20;
    /**
     * The barrier this signal sits on (IOnBarriers) came fully down during this activation - its bell
     * is quiet from then until the signal switches off. Runtime, worked out on both sides from the
     * arm each side simulates itself, so the bell stops exactly when the player sees the arm land.
     */
    private boolean barrierDownSeen = false;
    /** Client-only render state easing the warning lamps' brightness between on/off; never persisted. */
    private final LampFade lampFade = new LampFade();
    /** Client-only: tracks which points of the blink cycle passed since the last tick, to ring the bell on. */
    protected final BlinkClock.Beat beat = new BlinkClock.Beat();
    /** Client-only: when this crossing switched on, so its lamps and bell start their cycle right then. */
    protected final BlinkClock.Activation activation = new BlinkClock.Activation();
    /**
     * String[] crossPropArrLenght - Lenght in Consts.java
     * [0] - isActive
     * [1] - armDownDelay
     * [2] - soundOn
     * [3] - strongSoundOn
     * [4] - SoundType
     * [5] - hasKriz
     * [6] - isKrizJedno
     * [7] - hasKrizNaStozaru
     * [8] - isKrizReflex
     * [9] - hasPozLight
     * [10] - usePozLight
     * [11] - isPozLightShort
     * [12] - hasZebrik
     * [13] - isSlovak
     * [14] - hasPruhy
     * [15] - DistFromSloup
     * [16] - LightPos
     * [17] - isCedule
     * [18] - isOtradovice
     * [19] - headRot
     * [20] - Name
     * [21] - Scale
     * [22] - BarrierLength
     * [23] - armRotation
     * [24] - isKrizVelky
     * [25] - isLightCoverShort
     * [26] - isNewer
     * [27] - doLightsAlter
     * [28] - stozarDelsiCount
     * [29] - yOffset
     * [30] - signalOffDelay
     */
    private final String[] levelCrossingProperties;
    private final boolean Editable;

    public TileLevelCrossing() {
        this.levelCrossingProperties = new String[Consts.crossPropArrLenght];
        this.Editable = true;
        this.setActive(false);
        this.setArmDownDelay(0);
        this.setSoundOn(false);
        this.setStrongSoundOn(false);
        this.setSoundType(Consts.SoundType.NO_SOUND);
        this.setHasKriz(false);
        this.setKrizJedno(false);
        this.setHasKrizNaStozaru(false);
        this.setKrizReflex(false);
        this.setHasPozLight(false);
        this.setUsePozLight(false);
        this.setPozLightShort(false);
        this.setHasZebrik(false);
        this.setSlovak(false);
        this.setHasPruhy(false);
        this.setDistFromSloup(Consts.DistFromPole.DIST_00);
        this.setLightPos(Consts.Position.MIDDLE);
        this.setCedule(Consts.CeduleState.DOWN);
        this.setOtradovice(false);
        this.setHeadRot(0);
        this.setName("Crossing Name");
        this.levelCrossingProperties[21] = " Scale 0.1 - 2.0";
        this.setBarrierLength("0m");
        this.setArmRotation(0);
        this.setKrizVelky(false);
        this.setLightCoverShort(false);
        this.setNewer(false);
        this.doLightsAlter(false);
        this.setStozarDelsiCount(0);
        this.setYOffset(0f);
        this.setSignalOffDelay(0);
    }

    /**
     * Client: one step per tick, then the sounds. Server: steps up to the world time (catchUp) - a
     * server may tick some chunks' tiles much less often than every tick (seen: 3-6x slower), and the
     * state must not lag behind because of it. Both sides run the very same step(). Subclasses
     * override tickMechanics()/tickClientSounds(), not this.
     */
    @Override
    public void updateEntity() {
        if (worldObj.isRemote) {
            step();
            beat.tick();
            activation.ticksActive(this.getIsActive());
            tickClientSounds();
        } else {
            catchUp();
        }
    }

    /** One tick of the runtime (pending switch-off, poz delay, bell latch) and of the mechanics (arm). */
    private void step() {
        tickRuntime();
        tickMechanics();
    }

    /**
     * Server only: simulates the steps this tile missed up to the current world time. Called before
     * the state is used (events, arm checks, saving), so it is right however seldom the tile ticks.
     */
    protected final void catchUp() {
        if (worldObj == null || worldObj.isRemote || catchingUp) return;
        long now = worldObj.getTotalWorldTime();
        if (simulatedTo < 0 || now - simulatedTo > MAX_CATCH_UP) {
            simulatedTo = now - 1;
        }
        if (simulatedTo >= now) return;
        catchingUp = true;
        try {
            // short gaps are routine on servers that throttle tile ticks - only log the big ones
            if (now - simulatedTo > CATCH_UP_LOG_MIN) {
                CrossingDebug.log(this, "catching up " + (now - simulatedTo) + " ticks (tile wasn't ticked)");
            }
            while (simulatedTo < now) {
                ++simulatedTo;
                step();
            }
        } finally {
            catchingUp = false;
        }
    }

    private void tickRuntime() {
        if (this.BellDelayTimer > 0 && !this.getIsActive()) {
            --BellDelayTimer;
        }
        if (this.getIsActive() && offWait.tick(this)) {
            switchOff();
            CrossingDebug.log(this, "-> OFF");
        }
        if (this.getIsActive() && !barrierDownSeen && this instanceof IOnBarriers) {
            TileEntity below = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
            if (below instanceof IBarriers && ((IBarriers) below).isArmDown()) {
                barrierDownSeen = true;
                CrossingDebug.log(this, "barrier below is down -> bell quiet");
            }
        }
    }

    /** Arm etc. of barriers - runs on both sides, deterministically from the runtime state. */
    protected void tickMechanics() {
    }

    /**
     * The bell is rung by each client from its own BlinkClock, in step with the lamps it renders -
     * the server never plays it (see BlinkClock for why).
     */
    protected void tickClientSounds() {
        if (this.getIsActive() && this.hasSoundOn() && this.isBellArmed()
                && beat.hit(getSoundType().blinkTimer, getSoundType().soundTimer, activation.since())) {
            playLocalSound(getSoundType().SoundLocation);
            CrossingDebug.log(this, "bell");
        }
    }

    /** Signals on a barrier ring until it is down; the others follow their strongSoundOn setting. */
    protected boolean isBellArmed() {
        if (this instanceof IOnBarriers) return !barrierDownSeen;
        return this.isStrongSoundOn();
    }

    /** Called on both sides whenever the crossing switches on or off (event or countdown). */
    protected void onActiveChanged(boolean active) {
    }

    private void switchOff() {
        boolean wasActive = this.getIsActive();
        this.setActive(false);
        this.offWait.clear();
        this.barrierDownSeen = false;
        if (wasActive) {
            BellDelayTimer = this.getArmDownDelay() * 20;
        }
        onActiveChanged(false);
    }

    /**
     * Whether this crossing is driven by a crossing receiver below it (the receiver scans up to 10
     * blocks up). Such a crossing has no redstone of its own, so block updates must not touch it.
     */
    public boolean hasReceiverBelow() {
        if (worldObj == null) return false;
        for (int i = 1; i <= 10; i++) {
            if (worldObj.getTileEntity(xCoord, yCoord - i, zCoord) instanceof TileCrossingReceiver) {
                return true;
            }
        }
        return false;
    }

    /** Plays a sound at this crossing for this client only - client side only. */
    protected void playLocalSound(String sound) {
        playLocalSound(sound, 0.5, 0.75f);
    }

    /** Plays a sound at this crossing (yOffset blocks up) for this client only - client side only. */
    protected void playLocalSound(String sound, double yOffset, float volume) {
        this.worldObj.playSound(this.xCoord + 0.5, this.yCoord + yOffset, this.zCoord + 0.5, sound, volume, 1.0f, false);
    }

    /**
     * Blink phase for the lamps. Lamps and bell are client-side only and both run from
     * BlinkClock (real time), so they stay steady and in step however the server lags. The
     * server doesn't use the phase for anything; it falls back to world time there.
     */
    protected int computeBlinkPhase(int period) {
        if (period <= 0 || worldObj == null) return 0;
        if (worldObj.isRemote) return BlinkClock.getPhase(period);
        return (int) (worldObj.getTotalWorldTime() % period);
    }

    /**
     * Like computeBlinkPhase, but for the warning lamps that only blink while active - on the
     * client their cycle starts the moment the crossing switches on, so the first flash is
     * immediate instead of waiting for a shared cycle to come round.
     */
    protected int computeActiveBlinkPhase(int period) {
        if (period <= 0 || worldObj == null) return 0;
        if (worldObj.isRemote) return activation.getPhase(this.getIsActive(), period);
        return (int) (worldObj.getTotalWorldTime() % period);
    }

    public boolean isEditable() {
        return Editable;
    }

    public Boolean getIsActive() {
        return Boolean.parseBoolean(this.levelCrossingProperties[0]);
    }

    public Integer getArmDownDelay() {
        return Integer.parseInt(this.levelCrossingProperties[1]);
    }

    public Boolean hasSoundOn() {
        return Boolean.parseBoolean(this.levelCrossingProperties[2]);
    }

    public Boolean isStrongSoundOn() {
        return Boolean.parseBoolean(this.levelCrossingProperties[3]);
    }

    public Consts.SoundType getSoundType() {
        return Consts.SoundType.fromString(this.levelCrossingProperties[4]);
    }

    public Boolean hasKriz() {
        return Boolean.parseBoolean(this.levelCrossingProperties[5]);
    }

    public Boolean isKrizJedno() {
        return Boolean.parseBoolean(this.levelCrossingProperties[6]);
    }

    public Boolean hasKrizNaStozaru() {
        return Boolean.parseBoolean(this.levelCrossingProperties[7]);
    }

    public Boolean isKrizReflex() {
        return Boolean.parseBoolean(this.levelCrossingProperties[8]);
    }

    public Boolean hasPozLight() {
        return Boolean.parseBoolean(this.levelCrossingProperties[9]);
    }

    public Boolean usePozLight() {
        return Boolean.parseBoolean(this.levelCrossingProperties[10]);
    }

    public Boolean isPozLightShort() {
        return Boolean.parseBoolean(this.levelCrossingProperties[11]);
    }

    public Boolean hasZebrik() {
        return Boolean.parseBoolean(this.levelCrossingProperties[12]);
    }

    public Boolean isSlovak() {
        return Boolean.parseBoolean(this.levelCrossingProperties[13]);
    }

    public Boolean hasPruhy() {
        return Boolean.parseBoolean(this.levelCrossingProperties[14]);
    }

    public Consts.DistFromPole getDistFromSloup() {
        return Consts.DistFromPole.fromString(this.levelCrossingProperties[15]);
    }

    public Consts.Position getLightPos() {
        return Consts.Position.fromString(this.levelCrossingProperties[16]);
    }

    public Consts.CeduleState isCedule() {
        return Consts.CeduleState.fromString(this.levelCrossingProperties[17]);
    }

    public Boolean isOtradovice() {
        return Boolean.parseBoolean(this.levelCrossingProperties[18]);
    }

    public Integer getHeadRot() {
        return Integer.parseInt(this.levelCrossingProperties[19]);
    }

    public int getBlinkCounter() {
        return computeActiveBlinkPhase(getSoundType().blinkTimer);
    }

    public int getPozitBlinkCounter() {
        return computeBlinkPhase(getSoundType().pozitBlinkTimer);
    }

    public LampFade getLampFade() {
        return this.lampFade;
    }

    @Override
    public String getName() {
        return this.levelCrossingProperties[20];
    }

    public Float getScale() {
        try {
            return Float.parseFloat(this.levelCrossingProperties[21]);
        } catch (NumberFormatException e) {
            return 1f;
        }
    }

    public int getPozLightDelayTimer() {
        return BellDelayTimer;
    }

    public String getScaleString() {
        return this.levelCrossingProperties[21];
    }

    public String getBarrierLength() {
        return this.levelCrossingProperties[22];
    }

    public int getArmRotation() {
        return Integer.parseInt(this.levelCrossingProperties[23]);
    }

    public boolean isKrizVelky() {
        return Boolean.parseBoolean(this.levelCrossingProperties[24]);
    }

    public boolean isLightCoverShort() {
        return Boolean.parseBoolean(this.levelCrossingProperties[25]);
    }

    public boolean isNewer() {
        return Boolean.parseBoolean(this.levelCrossingProperties[26]);
    }
    public boolean doLightsAlter(){
        return Boolean.parseBoolean(this.levelCrossingProperties[27]);
    }

    public int getStozarDelsiCount() {
        try {
            return Integer.parseInt(this.levelCrossingProperties[28]);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /** Vertical render offset (in blocks) of the crossing head - head-only units move whole, full AZD71/AZD97/SSSR/SSSRSingle keep their pole on the ground. */
    public Float getYOffset() {
        try {
            return Float.parseFloat(this.levelCrossingProperties[29]);
        } catch (NumberFormatException e) {
            return 0f;
        }
    }

    public String getYOffsetString() {
        return this.levelCrossingProperties[29];
    }

    public int getSignalOffDelay() {
        try {
            return Integer.parseInt(this.levelCrossingProperties[30]);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void setActive(Boolean isActive) {
        this.levelCrossingProperties[0] = isActive.toString();
    }

    @Override
    public void setIsActive(Boolean active) {
        setCrossingActive(active);
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

    /** Event, server side: switches the crossing on/off now, and the clients too. */
    @Override
    public void setCrossingActive(Boolean activated) {
        fire(activated ? CrossingEvent.on() : CrossingEvent.off());
    }

    /** Event, server side: switch off once the barriers are up (see ILevelCrossing), on every side by its own barriers. */
    @Override
    public void deactivateWhenBarriersUp(int[] barriers, int delayTicks) {
        fire(CrossingEvent.offWhenUp(barriers, delayTicks));
    }

    private void fire(CrossingEvent event) {
        if (worldObj == null || worldObj.isRemote) return;     // only the server decides events
        catchUp();
        if (applyCrossingEvent(event)) {
            MessageCrossingEvent.send(this, event);
        }
    }

    @Override
    public boolean applyCrossingEvent(CrossingEvent event) {
        switch (event.type) {
            case CrossingEvent.ON:
                if (this.getIsActive() && !offWait.isRunning()) return false;
                this.setActive(true);
                this.offWait.clear();
                this.barrierDownSeen = false;
                onActiveChanged(true);
                return true;
            case CrossingEvent.OFF:
                if (!this.getIsActive()) return false;
                switchOff();
                return true;
            case CrossingEvent.OFF_WHEN_UP:
                if (!this.getIsActive()) return false;
                if (event.barriers.length == 0 && event.delay <= 0) {
                    switchOff();
                } else {
                    this.offWait.start(event.barriers, event.delay);
                }
                return true;
            default:
                return false;
        }
    }

    @Override
    public void writeRuntime(NBTTagCompound tag) {
        catchUp();
        tag.setBoolean("active", this.getIsActive());
        this.offWait.write(tag);
        tag.setInteger("pozDelay", this.BellDelayTimer);
        tag.setBoolean("barrierDown", this.barrierDownSeen);
        tag.setInteger("arm", this.getArmRotation());
    }

    @Override
    public void readRuntime(NBTTagCompound tag) {
        this.setActive(tag.getBoolean("active"));
        this.offWait.read(tag);
        this.BellDelayTimer = tag.getInteger("pozDelay");
        this.barrierDownSeen = tag.getBoolean("barrierDown");
        this.setArmRotation(tag.getInteger("arm"));
    }

    @Override
    public String describeRuntime() {
        catchUp();
        return "active=" + this.getIsActive() + " arm=" + this.getArmRotation() + " " + this.offWait.describe()
                + " pozDelay=" + this.BellDelayTimer + (this instanceof IOnBarriers ? " bellQuiet=" + this.barrierDownSeen : "");
    }


    @Override
    public boolean isCrossingActive() {
        return this.getIsActive();
    }

    public void setArmDownDelay(Integer armDownDelay) {
        this.levelCrossingProperties[1] = String.valueOf(armDownDelay);
    }

    public void setSoundOn(Boolean soundOn) {
        this.levelCrossingProperties[2] = soundOn.toString();
    }

    public void setStrongSoundOn(Boolean strongSoundOn) {
        this.levelCrossingProperties[3] = strongSoundOn.toString();
    }

    public void setSoundType(Consts.SoundType soundType) {
        this.levelCrossingProperties[4] = soundType.SoundLocation;
    }

    public void setHasKriz(Boolean hasKriz) {
        this.levelCrossingProperties[5] = hasKriz.toString();
    }

    public void setKrizJedno(Boolean isKrizJedno) {
        this.levelCrossingProperties[6] = isKrizJedno.toString();
    }

    public void setHasKrizNaStozaru(Boolean hasKrizNaStozaru) {
        this.levelCrossingProperties[7] = hasKrizNaStozaru.toString();
    }

    public void setKrizReflex(Boolean isKrizReflex) {
        this.levelCrossingProperties[8] = isKrizReflex.toString();
    }

    public void setHasPozLight(Boolean hasPozLight) {
        this.levelCrossingProperties[9] = hasPozLight.toString();
    }

    public void setUsePozLight(Boolean usePozLight) {
        this.levelCrossingProperties[10] = usePozLight.toString();
    }

    public void setPozLightShort(Boolean isPozLightShort) {
        this.levelCrossingProperties[11] = isPozLightShort.toString();
    }

    public void setHasZebrik(Boolean hasZebrik) {
        this.levelCrossingProperties[12] = hasZebrik.toString();
    }

    public void setSlovak(Boolean isSlovak) {
        this.levelCrossingProperties[13] = isSlovak.toString();
    }

    public void setHasPruhy(Boolean hasPruhy) {
        this.levelCrossingProperties[14] = hasPruhy.toString();
    }

    public void setDistFromSloup(Consts.DistFromPole distFromSloup) {
        this.levelCrossingProperties[15] = distFromSloup.Dist;
    }

    public void setLightPos(Consts.Position lightPos) {
        this.levelCrossingProperties[16] = lightPos.Pos;
    }

    public void setCedule(Consts.CeduleState isCedule) {
        this.levelCrossingProperties[17] = isCedule.State;
    }

    public void setOtradovice(Boolean isOtradovice) {
        this.levelCrossingProperties[18] = isOtradovice.toString();
    }

    public void setHeadRot(Integer headRot) {
        this.levelCrossingProperties[19] = String.valueOf(headRot);
    }

    @Override
    public void setName(String crossingName) {
        this.levelCrossingProperties[20] = crossingName;
    }

    public void setScale(Float scale) {
        this.levelCrossingProperties[21] = String.valueOf(scale);
    }

    public void setBarrierLength(String barrierLength) {
        this.levelCrossingProperties[22] = barrierLength;
    }

    public void setArmRotation(int armRotation) {
        this.levelCrossingProperties[23] = String.valueOf(armRotation);
    }

    @Override
    public void setBlinkCounter(int blinkCounter) {
        // Blink phase is derived from world time (see getBlinkCounter()); nothing to store.
        // Kept only to satisfy IActivatable, matching TileGSARCrossing/TileMechSignal's stubs.
    }

    public void setKrizVelky(Boolean isKrizVelky) {
        this.levelCrossingProperties[24] = isKrizVelky.toString();
    }

    public void setLightCoverShort(Boolean isLightCoverShort) {
        this.levelCrossingProperties[25] = isLightCoverShort.toString();
    }

    public void setNewer(Boolean isNewer) {
        this.levelCrossingProperties[26] = isNewer.toString();
    }

    public void doLightsAlter(Boolean alterLights) {
        this.levelCrossingProperties[27] = alterLights.toString();
    }

    public void setStozarDelsiCount(int stozarDelsiCount) {
        this.levelCrossingProperties[28] = String.valueOf(stozarDelsiCount);
    }

    public void setYOffset(Float yOffset) {
        this.levelCrossingProperties[29] = String.valueOf(yOffset);
    }

    public void setSignalOffDelay(Integer signalOffDelay) {
        this.levelCrossingProperties[30] = String.valueOf(signalOffDelay);
    }

    public void readFromNBT(final NBTTagCompound NBTTC) {
        super.readFromNBT(NBTTC);
        this.levelCrossingProperties[0] = NBTTC.getString("isActive");
        this.levelCrossingProperties[1] = NBTTC.getString("armDownDelay");
        this.levelCrossingProperties[2] = NBTTC.getString("soundOn");
        this.levelCrossingProperties[3] = NBTTC.getString("strongSoundOn");
        this.levelCrossingProperties[4] = NBTTC.getString("SoundType");
        this.levelCrossingProperties[5] = NBTTC.getString("hasKriz");
        this.levelCrossingProperties[6] = NBTTC.getString("isKrizJedno");
        this.levelCrossingProperties[7] = NBTTC.getString("hasKrizNaStozaru");
        this.levelCrossingProperties[8] = NBTTC.getString("isKrizReflex");
        this.levelCrossingProperties[9] = NBTTC.getString("hasPozLight");
        this.levelCrossingProperties[10] = NBTTC.getString("usePozLight");
        this.levelCrossingProperties[11] = NBTTC.getString("isPozLightShort");
        this.levelCrossingProperties[12] = NBTTC.getString("hasZebrik");
        this.levelCrossingProperties[13] = NBTTC.getString("isSlovak");
        this.levelCrossingProperties[14] = NBTTC.getString("hasPruhy");
        this.levelCrossingProperties[15] = NBTTC.getString("DistFromSloup");
        this.levelCrossingProperties[16] = NBTTC.getString("LightPos");
        this.levelCrossingProperties[17] = NBTTC.getString("isCedule");
        this.levelCrossingProperties[18] = NBTTC.getString("isOtradovice");
        this.levelCrossingProperties[19] = NBTTC.getString("headRot");
        this.levelCrossingProperties[20] = NBTTC.getString("Name");
        this.levelCrossingProperties[21] = NBTTC.getString("Scale");
        this.levelCrossingProperties[22] = NBTTC.getString("BarrierLength");
        this.levelCrossingProperties[23] = NBTTC.getString("armRotation");
        this.levelCrossingProperties[24] = NBTTC.getString("isKrizVelky");
        this.levelCrossingProperties[25] = NBTTC.getString("isLightCoverShort");
        this.levelCrossingProperties[26] = NBTTC.getString("isNewer");
        this.levelCrossingProperties[27] = NBTTC.getString("doLightsAlter");
        // older saves lack this tag - keep the default set by the subclass constructor (AZD71, AZD97, SSSR)
        if (NBTTC.hasKey("stozarDelsiCount")) {
            this.levelCrossingProperties[28] = NBTTC.getString("stozarDelsiCount");
        }
        this.levelCrossingProperties[29] = NBTTC.getString("yOffset");
        this.levelCrossingProperties[30] = NBTTC.getString("signalOffDelay");
        if (NBTTC.hasKey("runtime")) {
            this.readRuntime(NBTTC.getCompoundTag("runtime"));
        }
    }

    public void writeToNBT(final NBTTagCompound NBTTC) {
        super.writeToNBT(NBTTC);
        NBTTC.setString("isActive", this.levelCrossingProperties[0]);
        NBTTC.setString("armDownDelay", this.levelCrossingProperties[1]);
        NBTTC.setString("soundOn", this.levelCrossingProperties[2]);
        NBTTC.setString("strongSoundOn", this.levelCrossingProperties[3]);
        NBTTC.setString("SoundType", this.levelCrossingProperties[4]);
        NBTTC.setString("hasKriz", this.levelCrossingProperties[5]);
        NBTTC.setString("isKrizJedno", this.levelCrossingProperties[6]);
        NBTTC.setString("hasKrizNaStozaru", this.levelCrossingProperties[7]);
        NBTTC.setString("isKrizReflex", this.levelCrossingProperties[8]);
        NBTTC.setString("hasPozLight", this.levelCrossingProperties[9]);
        NBTTC.setString("usePozLight", this.levelCrossingProperties[10]);
        NBTTC.setString("isPozLightShort", this.levelCrossingProperties[11]);
        NBTTC.setString("hasZebrik", this.levelCrossingProperties[12]);
        NBTTC.setString("isSlovak", this.levelCrossingProperties[13]);
        NBTTC.setString("hasPruhy", this.levelCrossingProperties[14]);
        NBTTC.setString("DistFromSloup", this.levelCrossingProperties[15]);
        NBTTC.setString("LightPos", this.levelCrossingProperties[16]);
        NBTTC.setString("isCedule", this.levelCrossingProperties[17]);
        NBTTC.setString("isOtradovice", this.levelCrossingProperties[18]);
        NBTTC.setString("headRot", this.levelCrossingProperties[19]);
        NBTTC.setString("Name", this.levelCrossingProperties[20]);
        NBTTC.setString("Scale", this.levelCrossingProperties[21]);
        NBTTC.setString("BarrierLength", this.levelCrossingProperties[22]);
        NBTTC.setString("armRotation", this.levelCrossingProperties[23]);
        NBTTC.setString("isKrizVelky", this.levelCrossingProperties[24]);
        NBTTC.setString("isLightCoverShort", this.levelCrossingProperties[25]);
        NBTTC.setString("isNewer", this.levelCrossingProperties[26]);
        NBTTC.setString("doLightsAlter", this.levelCrossingProperties[27]);
        NBTTC.setString("stozarDelsiCount", this.levelCrossingProperties[28]);
        NBTTC.setString("yOffset", this.levelCrossingProperties[29]);
        NBTTC.setString("signalOffDelay", this.levelCrossingProperties[30]);
        NBTTagCompound runtime = new NBTTagCompound();
        this.writeRuntime(runtime);
        NBTTC.setTag("runtime", runtime);
    }

    /** The Scaler and the GUI share this tile's own scale setting. */
    @Override
    public float getModelScale() {
        return this.getScale();
    }

    @Override
    public void setModelScale(float scale) {
        this.setScale((Float) this.clampModelScale(scale));
    }

    /** The crossing renderers scale the model themselves. */
    @Override
    public boolean isScaledByWrapper() {
        return false;
    }
}
