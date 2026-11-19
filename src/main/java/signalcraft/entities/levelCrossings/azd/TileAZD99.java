package signalcraft.entities.levelCrossings.azd;

import net.minecraft.nbt.NBTTagCompound;
import signalcraft.entities.levelCrossings.BoomFlex;
import signalcraft.entities.levelCrossings.IBarriers;

import signalcraft.entities.levelCrossings.TileLevelCrossing;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.CrossingDebug;

public class TileAZD99 extends TileLevelCrossing implements IBarriers {
    /**
     * time the arm takes to go down (and up), seconds - the same for every AZD99, so barriers on one crossing come up
     * (and switch off their warning lights) together
     */
    public static final float ARM_TIME = 6f;
    /**
     * the drive turns the arm through a crank: slow at the end positions ((1 - CRANK) of the average speed), fast in
     * the middle (1 + CRANK)
     */
    private static final float CRANK = 0.6f;
    /** vibration of the drive while the arm moves: degrees on the arm, on the boom (at full speed) */
    private static final float SHAKE_ARM = 0.06f, SHAKE_BOOM = 0.2f;
    /**
     * the whole arm knocks on the end stop when it gets there: it bounces back off it a few times, each bounce smaller
     * (|sin|, damped) - degrees for a 4.2 m boom (~ square root of the length), radians per tick, per tick
     */
    private static final float KNOCK = 0.7f, KNOCK_OMEGA = 0.9f, KNOCK_DAMPING = 0.22f;
    private static final int KNOCK_TICKS = 30;

    private int armDownDelayTimer = 0;
    /** part of the next degree the arm has already gone (in the direction lastDir) */
    private float travel = 0f;
    private int lastDir = 0;
    /** ticks since the arm got to an end position (drives the knock), which one: +1 = down, -1 = up */
    private int knockTicks = KNOCK_TICKS, knockEnd = 0;
    /** client only: the bend of the boom */
    private final BoomFlex flex = new BoomFlex();

    public TileAZD99() {
        this.setGuiId(Consts.GuiIDs.AZD99);
        this.setBarrierLength("4,2m");
        this.setArmRotation(0);
    }

    /** Arm movement - the same on the server and every client (see ICrossingRuntime). */
    @Override
    protected void tickMechanics() {
        int dir = this.armDirection();
        if (dir <= 0 && armDownDelayTimer > 0) {
            --armDownDelayTimer;
        }
        if (dir != lastDir) {
            travel = 0f;
            lastDir = dir;
        }
        if (dir != 0) {
            final int armBefore = this.getArmRotation();
            travel += this.armSpeed();
            while (travel >= 1f && this.armDirection() == dir) {
                this.setArmRotation(this.getArmRotation() + dir);
                travel -= 1f;
            }
            if (this.armDirection() != dir) {
                travel = 0f;
            }
            final int arm = this.getArmRotation();
            if (arm != armBefore && (arm == MAX_ARM_ANGLE || arm == MIN_ARM_ANGLE)) {
                knockTicks = 0;                    // just got to the end stop
                knockEnd = dir;
                CrossingDebug.log(this, "arm reached " + (arm == MAX_ARM_ANGLE ? "DOWN" : "UP"));
            } else if (arm != MAX_ARM_ANGLE && arm != MIN_ARM_ANGLE) {
                knockTicks = KNOCK_TICKS;
            }
        } else if (knockTicks < KNOCK_TICKS) {
            ++knockTicks;
        }
        if (this.worldObj != null && this.worldObj.isRemote) {
            this.flex.update(this.getRenderArm(0f), this.boomLength());
        }
    }

    /** +1 = lowering, -1 = raising, 0 = standing (the arm waits for the delay or is in an end position). */
    private int armDirection() {
        int arm = this.getArmRotation();
        if (this.getIsActive()) {
            return arm < MAX_ARM_ANGLE && (armDownDelayTimer <= 0 || arm > 0) ? 1 : 0;
        }
        return arm > 0 ? -1 : 0;
    }

    /** Same steps as tickMechanics() while raising, counted until the arm is up. */
    @Override
    public int ticksUntilArmUp() {
        catchUp();
        int arm = this.getArmRotation();
        float t = lastDir == -1 ? travel : 0f;
        float speed = this.armSpeed();
        int ticks = 0;
        while (arm > 0 && ticks < 100000) {
            ++ticks;
            t += speed;
            while (t >= 1f && arm > 0) {
                --arm;
                t -= 1f;
            }
        }
        return ticks;
    }

    /** degrees of armRotation per tick */
    private float armSpeed() {
        return MAX_ARM_ANGLE / (ARM_TIME * 20f);
    }

    /** Arm angle shaped by the drive's crank, from the linear armRotation (both 0..MAX_ARM_ANGLE). */
    private float driveAngle(float arm) {
        float u = arm / MAX_ARM_ANGLE;
        return MAX_ARM_ANGLE * (u - CRANK * (float) Math.sin(2 * Math.PI * u) / (float) (2 * Math.PI));
    }

    /** linear arm position including the part of a degree gone, partialTicks ahead */
    private float armPosition(float partialTicks) {
        int dir = this.armDirection();
        float gone = dir == lastDir ? travel : 0f;
        float arm = this.getArmRotation() + dir * (gone + this.armSpeed() * partialTicks);
        return Math.max(MIN_ARM_ANGLE, Math.min(MAX_ARM_ANGLE, arm));
    }

    /** boom length in metres from the barrier length setting ("4,2m") */
    private float boomLength() {
        try {
            return Float.parseFloat(this.getBarrierLength().replace(',', '.').replace("m", ""));
        } catch (NumberFormatException e) {
            return 4.2f;
        }
    }

    /** Arm angle for rendering (0 = up .. MAX_ARM_ANGLE = down): smooth between ticks, with the drive's vibration. */
    public float getRenderArm(float partialTicks) {
        return this.driveAngle(this.armPosition(partialTicks)) + SHAKE_ARM * this.shake(partialTicks) - this.knock(partialTicks);
    }

    /** How far the arm has bounced back off the end stop it just got to, degrees (towards the other end). */
    private float knock(float partialTicks) {
        if (knockTicks >= KNOCK_TICKS || this.armDirection() != 0) {
            return 0f;
        }
        float t = knockTicks + partialTicks;
        float size = KNOCK * (float) Math.sqrt(this.boomLength() / 4.2f);
        return knockEnd * size * (float) (Math.exp(-KNOCK_DAMPING * t) * Math.abs(Math.sin(KNOCK_OMEGA * t)));
    }

    /** Bend of the boom for rendering, degrees at its tip, + = further down. */
    public float getRenderFlex(float partialTicks) {
        return flex.get(partialTicks) + SHAKE_BOOM * this.shake(partialTicks);
    }

    /** Drive vibration, about -1..1 at full speed, 0 when standing. */
    private float shake(float partialTicks) {
        float speed = flex.getLastSpeed();
        if (this.worldObj == null || speed == 0f) {
            return 0f;
        }
        double t = this.worldObj.getTotalWorldTime() + partialTicks + (this.xCoord * 7 + this.zCoord * 13) % 100;
        float intensity = Math.min(1f, Math.abs(speed) / ((1f + CRANK) * this.armSpeed()));
        return intensity * (float) (Math.sin(2.3 * t) + 0.5 * Math.sin(3.9 * t + 1.3) + 0.3 * Math.sin(5.3 * t + 0.4)) / 1.8f;
    }

    @Override
    protected void onActiveChanged(boolean active) {
        this.armDownDelayTimer = active ? this.getArmDownDelay() * 20 : 0;
    }

    @Override
    public void writeRuntime(NBTTagCompound tag) {
        super.writeRuntime(tag);
        tag.setInteger("armDownDelayTimer", this.armDownDelayTimer);
        tag.setFloat("travel", this.travel);
        tag.setInteger("lastDir", this.lastDir);
        tag.setInteger("knockTicks", this.knockTicks);
        tag.setInteger("knockEnd", this.knockEnd);
    }

    @Override
    public void readRuntime(NBTTagCompound tag) {
        super.readRuntime(tag);
        this.armDownDelayTimer = tag.getInteger("armDownDelayTimer");
        this.travel = tag.getFloat("travel");
        this.lastDir = tag.getInteger("lastDir");
        this.knockTicks = tag.hasKey("knockTicks") ? tag.getInteger("knockTicks") : KNOCK_TICKS;
        this.knockEnd = tag.getInteger("knockEnd");
    }

    @Override
    public String describeRuntime() {
        return super.describeRuntime() + String.format(" travel=%.2f dir=%d delay=%d", this.travel, this.armDirection(), this.armDownDelayTimer);
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
