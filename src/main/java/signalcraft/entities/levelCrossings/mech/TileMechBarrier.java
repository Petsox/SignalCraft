package signalcraft.entities.levelCrossings.mech;

import net.minecraft.nbt.NBTTagCompound;
import signalcraft.entities.levelCrossings.BoomFlex;
import signalcraft.entities.levelCrossings.IBarriers;
import signalcraft.entities.levelCrossings.TileLevelCrossing;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.CrossingDebug;

/**
 * Mechanical barrier (CSD PZM 1 / PZM 2). Reuses the shared crossing properties:
 * isNewer() = PZM 1 (with the "Trojlistek" pre-ringing wheel and bell), hasPruhy() = hanging lattice.
 * armRotation follows AZD99: 0 = arm up (road open), 85 = arm down - but a hand-cranked barrier moves
 * slower, one degree every STEP_TICKS ticks.
 */
public class TileMechBarrier extends TileLevelCrossing implements IBarriers {
    public static final String[] LENGTHS = {"4,0m", "5,0m", "6,0m", "7,0m", "8,0m"};
    /** ticks per degree of arm travel: 85 degrees * 2 ticks = ~8.5 s */
    public static final int STEP_TICKS = 2;
    /** the pre-ringing wheel turns at a constant speed: the drive wheel's average speed over the arm travel */
    public static final float BELL_WHEEL_SPEED =
            Math.abs(MechBarrierKinematics.wheelTurn(85)) / (85 * STEP_TICKS);
    /** the bell keeps ringing this long after the arm is fully down */
    public static final int RING_AFTER_DOWN_TICKS = 80;
    /** hammer strikes per turn of the pre-ringing wheel (both hammers at once) */
    public static final int STRIKES_PER_REV = 8;
    /** gravity look: the boom speeds up over the last GRAVITY_ZONE degrees of lowering ... */
    private static final float GRAVITY_ZONE = 12f, ARRIVAL_SPEEDUP = 2.5f;
    /** ... then swings past the closed position and settles (damped oscillation, per tick) */
    private static final float SWING_OMEGA = 0.36f, SWING_DAMPING = 0.08f;
    private static final int SETTLE_TICKS = 60;
    /**
     * raised by the crank at constant speed the boom hits the top stop: it cannot go past it, it bounces back down
     * off the stop a few times, each bounce smaller (|sin|, damped) - degrees, radians per tick, per tick
     */
    private static final float UP_BOUNCE = 1f, UP_BOUNCE_OMEGA = 0.5f, UP_BOUNCE_DAMPING = 0.2f;
    private static final int UP_SETTLE_TICKS = 20;
    /** sound of the hammers striking the bell (PZM 1) */
    public static final String BELL_SOUND = "signalcraft:cinkMech";
    /** how far a hammer is drawn back (degrees) before it snaps onto the bell */
    public static final float HAMMER_PULL = 18f;
    /** cam: drawn back over this part of a strike cycle, then snaps onto the bell and rests there */
    private static final float CAM_PULL_END = 0.8f, CAM_REST_FROM = 0.88f;

    private int armDownDelayTimer = 0;
    private int stepCounter = 0;
    /** ticks left of ringing after the arm came back up (same length as the pre-ringing delay) */
    private int postRingTimer = 0;
    /** pre-ringing wheel angle; + = the way the drive wheel turns while lowering, - = while raising */
    private float bellWheelAngle = 0f;
    /** how far the hammer cam turned during the last tick (for smooth rendering) */
    private float lastHammerStep = 0f;
    /** ticks left of ringing after the arm came fully down */
    private int downRingTimer = 0;
    /** ticks since the arm came fully down (drives the visual swing of the boom) */
    private int settleTicks = 0;
    /** ticks since the arm came fully up (drives the bounce on the top stop); starts settled, e.g. after loading */
    private int upSettleTicks = UP_SETTLE_TICKS;
    /** angle the hammer cam has turned; advances while striking and until the hammers are back on the bell */
    private float hammerTravel = 0f;
    /** boom falls to the left (seen from the wheel side): the whole barrier is rendered mirrored */
    private boolean boomLeft = false;
    /** round pole with the "POZOR VLAK" sign in front of the low post (the cross on it: hasKriz() etc.) */
    private boolean signPole = true;
    /** the "POZOR VLAK" sign on that pole */
    private boolean poleSign = true;
    /** client only: the bend of the (wooden) boom */
    private final BoomFlex flex = new BoomFlex();

    public TileMechBarrier() {
        this.setGuiId(Consts.GuiIDs.MECH_BARRIER);
        this.setBarrierLength("6,0m");
        this.setArmRotation(0);
        this.setNewer(true);
        this.setHasPruhy(false);
        this.setHasKriz(true);              // warning cross on the sign pole, for more tracks
        this.setKrizJedno(false);
    }

    /** Arm, wheel and hammers - the same on the server and every client (see ICrossingRuntime). */
    @Override
    protected void tickMechanics() {
        final boolean hammersMove = isStriking() || !hammerAtRest();
        bellWheelAngle = (bellWheelAngle + getBellWheelSpeed()) % 360f;

        if (this.getIsActive() && this.getArmRotation() == MAX_ARM_ANGLE) {
            if (settleTicks < SETTLE_TICKS) ++settleTicks;
        } else {
            settleTicks = 0;
        }
        if (!this.getIsActive() && this.getArmRotation() == 0) {
            if (upSettleTicks < UP_SETTLE_TICKS) ++upSettleTicks;
        } else {
            upSettleTicks = UP_SETTLE_TICKS;
        }
        if (downRingTimer > 0) {
            if (this.getIsActive() && this.getArmRotation() == MAX_ARM_ANGLE) {
                --downRingTimer;
            } else {
                downRingTimer = 0;
            }
        }
        if (postRingTimer > 0 && (this.getIsActive() || this.getArmRotation() != 0)) {
            postRingTimer = 0;
        } else if (postRingTimer > 0) {
            --postRingTimer;
        }
        stepArm();

        // the hammer cam is driven by the pre-ringing wheel; once striking stops the wheel turns on only
        // until the hammers are back on the bell
        lastHammerStep = hammersMove ? BELL_WHEEL_SPEED : 0f;
        final float phaseBefore = camPhase(hammerTravel);
        hammerTravel = (hammerTravel + lastHammerStep) % 3600f;
        // the hammers just snapped onto the bell - played by each client in step with the hammers it shows
        if (lastHammerStep > 0f && phaseBefore < CAM_REST_FROM && camPhase(hammerTravel) >= CAM_REST_FROM
                && this.isPzm1() && this.worldObj != null && this.worldObj.isRemote) {
            playLocalSound(BELL_SOUND, 2.0, 1.0f);
        }
        if (this.worldObj != null && this.worldObj.isRemote) {
            this.flex.update(this.getRenderArm(0f), this.getBoomMetres());
        }
    }

    private void stepArm() {
        int dir = 0;
        if (this.getIsActive() && this.getArmRotation() < MAX_ARM_ANGLE && armDownDelayTimer <= 0) {
            dir = 1;
        } else if (this.getArmRotation() > 0 && this.getArmRotation() < MAX_ARM_ANGLE && getIsActive() && armDownDelayTimer > 0) {
            dir = 1;
        } else if (armDownDelayTimer > 0) {
            --armDownDelayTimer;
        }
        if (!getIsActive() && this.getArmRotation() > 0) {
            dir = -1;
        }
        if (dir == 0) {
            stepCounter = 0;
            return;
        }
        if (++stepCounter >= STEP_TICKS) {
            stepCounter = 0;
            this.setArmRotation(this.getArmRotation() + dir);
            if (dir < 0 && this.getArmRotation() == 0) {
                postRingTimer = this.getArmDownDelay() * 20;       // turn as long after raising as before lowering
                upSettleTicks = 0;                                 // just hit the top stop
                CrossingDebug.log(this, "arm reached UP");
            }
            if (dir > 0 && this.getArmRotation() == MAX_ARM_ANGLE) {
                downRingTimer = RING_AFTER_DOWN_TICKS;             // keep ringing a little after the arm is down
                CrossingDebug.log(this, "arm reached DOWN");
            }
        }
    }

    /**
     * Pre-ringing wheel speed, degrees per tick, always the same magnitude:
     * + while the bell is struck (before lowering, lowering, just after) and until the hammers rest on the bell,
     * - while the arm goes up and for the same delay after raising, 0 otherwise.
     */
    private float getBellWheelSpeed() {
        if (isStriking()) return BELL_WHEEL_SPEED;
        if (!this.getIsActive() && (this.getArmRotation() > 0 || isPostRinging())) return -BELL_WHEEL_SPEED;
        if (!hammerAtRest()) return BELL_WHEEL_SPEED;
        return 0f;
    }

    @Override
    protected void onActiveChanged(boolean active) {
        if (active) {
            this.armDownDelayTimer = this.getArmDownDelay() * 20;
            this.postRingTimer = 0;
        } else {
            this.armDownDelayTimer = 0;
            this.downRingTimer = 0;
        }
    }

    /** Same steps as stepArm() while raising, counted until the arm is up. */
    @Override
    public int ticksUntilArmUp() {
        catchUp();
        int arm = this.getArmRotation();
        int counter = this.stepCounter;
        int ticks = 0;
        while (arm > 0 && ticks < 100000) {
            ++ticks;
            if (++counter >= STEP_TICKS) {
                counter = 0;
                --arm;
            }
        }
        return ticks;
    }

    @Override
    public void writeRuntime(NBTTagCompound tag) {
        super.writeRuntime(tag);
        tag.setInteger("armDownDelayTimer", this.armDownDelayTimer);
        tag.setInteger("stepCounter", this.stepCounter);
        tag.setInteger("postRingTimer", this.postRingTimer);
        tag.setInteger("downRingTimer", this.downRingTimer);
        tag.setInteger("settleTicks", this.settleTicks);
        tag.setInteger("upSettleTicks", this.upSettleTicks);
        tag.setFloat("bellWheelAngle", this.bellWheelAngle);
        tag.setFloat("hammerTravel", this.hammerTravel);
        tag.setFloat("lastHammerStep", this.lastHammerStep);
    }

    @Override
    public void readRuntime(NBTTagCompound tag) {
        super.readRuntime(tag);
        this.armDownDelayTimer = tag.getInteger("armDownDelayTimer");
        this.stepCounter = tag.getInteger("stepCounter");
        this.postRingTimer = tag.getInteger("postRingTimer");
        this.downRingTimer = tag.getInteger("downRingTimer");
        this.settleTicks = tag.getInteger("settleTicks");
        this.upSettleTicks = tag.hasKey("upSettleTicks") ? tag.getInteger("upSettleTicks") : UP_SETTLE_TICKS;
        this.bellWheelAngle = tag.getFloat("bellWheelAngle");
        this.hammerTravel = tag.getFloat("hammerTravel");
        this.lastHammerStep = tag.getFloat("lastHammerStep");
    }

    @Override
    public String describeRuntime() {
        return super.describeRuntime() + " step=" + this.stepCounter + " delay=" + this.armDownDelayTimer
                + " downRing=" + this.downRingTimer + " postRing=" + this.postRingTimer + " striking=" + this.isStriking();
    }

    /** Active, arm still up and the pre-ringing delay is running. */
    public boolean isPreRinging() {
        return this.getIsActive() && this.getArmRotation() == 0 && armDownDelayTimer > 0;
    }

    /** Arm back up and the same delay is running again after raising. */
    public boolean isPostRinging() {
        return !this.getIsActive() && this.getArmRotation() == 0 && postRingTimer > 0;
    }

    /** Bell is struck: before lowering, while lowering and for a while after the arm is down. */
    public boolean isStriking() {
        if (!this.getIsActive()) return false;
        int arm = this.getArmRotation();
        boolean lowering = arm < MAX_ARM_ANGLE && (armDownDelayTimer <= 0 || arm > 0);
        return isPreRinging() || lowering || downRingTimer > 0;
    }

    /** Position in the current strike cycle, 0..1. */
    private static float camPhase(float travel) {
        float period = 360f / STRIKES_PER_REV;
        return ((travel % period) + period) % period / period;
    }

    /** Hammers are resting on the bell (start of a cycle or after the snap). */
    private boolean hammerAtRest() {
        float p = camPhase(hammerTravel);
        return p >= CAM_REST_FROM || p < 1e-3f;
    }

    /** Hammer swing in degrees for rendering: 0 = on the bell, HAMMER_PULL = fully drawn back. */
    public float getRenderHammerPull(float partialTicks) {
        float p = camPhase(hammerTravel + lastHammerStep * partialTicks);
        if (p < CAM_PULL_END) return HAMMER_PULL * p / CAM_PULL_END;
        if (p < CAM_REST_FROM) return HAMMER_PULL * (1f - (p - CAM_PULL_END) / (CAM_REST_FROM - CAM_PULL_END));
        return 0f;
    }

    /** Arm angle (0 = up .. 85 = down) smoothed between ticks for rendering. */
    public float getRenderArm(float partialTicks) {
        float arm = this.getArmRotation();
        boolean moving;
        int dir;
        if (this.getIsActive()) {
            dir = 1;
            moving = arm < MAX_ARM_ANGLE && (armDownDelayTimer <= 0 || arm > 0);
        } else {
            dir = -1;
            moving = arm > 0;
        }
        if (moving) {
            arm += dir * (stepCounter + partialTicks) / STEP_TICKS;
        }
        arm = Math.max(MIN_ARM_ANGLE, Math.min(MAX_ARM_ANGLE, arm));
        if (dir > 0 && arm > MAX_ARM_ANGLE - GRAVITY_ZONE) {
            // falling: same start speed, ARRIVAL_SPEEDUP times faster at the end, arrives at the same moment
            float f = (arm - (MAX_ARM_ANGLE - GRAVITY_ZONE)) / GRAVITY_ZONE;
            float g = f + (ARRIVAL_SPEEDUP - 1f) * (f * f * f - f * f);
            arm = MAX_ARM_ANGLE - GRAVITY_ZONE + GRAVITY_ZONE * g;
        }
        if (dir > 0 && this.getArmRotation() == MAX_ARM_ANGLE && settleTicks < SETTLE_TICKS) {
            // swing below the closed position, back up a little, settle
            float t = settleTicks + partialTicks;
            float v0 = ARRIVAL_SPEEDUP / STEP_TICKS;                    // arrival speed, degrees per tick
            arm += (float) (v0 / SWING_OMEGA * Math.exp(-SWING_DAMPING * t) * Math.sin(SWING_OMEGA * t));
        }
        if (dir < 0 && this.getArmRotation() == 0 && upSettleTicks < UP_SETTLE_TICKS) {
            // bounce back down off the top stop, smaller each time
            float t = upSettleTicks + partialTicks;
            arm += (float) (UP_BOUNCE * Math.exp(-UP_BOUNCE_DAMPING * t) * Math.abs(Math.sin(UP_BOUNCE_OMEGA * t)));
        }
        return arm;
    }

    /** Bend of the boom for rendering, degrees at its tip, + = further down. */
    public float getRenderFlex(float partialTicks) {
        return flex.get(partialTicks);
    }

    /** Pre-ringing wheel angle for rendering, degrees. */
    public float getRenderBellWheelAngle(float partialTicks) {
        return bellWheelAngle + getBellWheelSpeed() * partialTicks;
    }



    public boolean isPzm1() {
        return this.isNewer();
    }

    public void setPzm1(boolean pzm1) {
        this.setNewer(pzm1);
    }

    public boolean isBoomLeft() {
        return this.boomLeft;
    }

    public void setBoomLeft(boolean boomLeft) {
        this.boomLeft = boomLeft;
    }

    public boolean hasSignPole() {
        return this.signPole;
    }

    public void setSignPole(boolean signPole) {
        this.signPole = signPole;
    }

    public boolean hasPoleSign() {
        return this.poleSign;
    }

    public void setPoleSign(boolean poleSign) {
        this.poleSign = poleSign;
    }

    @Override
    public void readFromNBT(final NBTTagCompound NBTTC) {
        super.readFromNBT(NBTTC);
        this.boomLeft = NBTTC.getBoolean("boomLeft");
        // saves from before the separate pole switch: the pole was shown together with the cross
        this.signPole = NBTTC.hasKey("signPole") ? NBTTC.getBoolean("signPole") : this.hasKriz();
        this.poleSign = !NBTTC.hasKey("poleSign") || NBTTC.getBoolean("poleSign");
    }

    @Override
    public void writeToNBT(final NBTTagCompound NBTTC) {
        super.writeToNBT(NBTTC);
        NBTTC.setBoolean("boomLeft", this.boomLeft);
        NBTTC.setBoolean("signPole", this.signPole);
        NBTTC.setBoolean("poleSign", this.poleSign);
    }

    public boolean hasLattice() {
        return this.hasPruhy();
    }

    public void setLattice(boolean lattice) {
        this.setHasPruhy(lattice);
    }

    /** Boom length in whole metres (4..8) parsed from the "N,0m" barrier length string. */
    public int getBoomMetres() {
        String len = this.getBarrierLength();
        if (len != null && !len.isEmpty() && Character.isDigit(len.charAt(0))) {
            int m = len.charAt(0) - '0';
            if (m >= 4 && m <= 8) return m;
        }
        return 6;
    }

    @Override
    public boolean isArmDown() {
        catchUp();
        return this.getArmRotation() == this.MAX_ARM_ANGLE;
    }

    @Override
    public boolean isArmUp() {
        catchUp();
        return this.getArmRotation() == this.MIN_ARM_ANGLE;
    }
}
