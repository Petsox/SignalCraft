package signalcraft.entities.switches.cz;

import net.minecraft.nbt.NBTTagCompound;
import signalcraft.entities.switches.TileSwitch;

/**
 * Czech point machine standing beside a TrainCraft switch (model prestavnik_cz.obj, ModelSwitchCZ): the throw rod
 * runs into the neighbouring block where the track is - beside the player who placed it, on the right (or the left).
 * The switch is thrown over travelTicks(); the redstone output (for the TrainCraft switch next to it) changes halfway
 * through the throw, when the blades would go over.
 * switchSide: the switch turns off to the right (the switch signal arrows).
 */
public abstract class TileSwitchCZ extends TileSwitch {
    /** switch signal on the stand: none, without the "straight" arrow (1987 - 1997), with it (since 1997) */
    public static final int SIGNAL_NONE = 0, SIGNAL_1987 = 1, SIGNAL_1997 = 2;
    /** the model can be shifted along the track by up to this many 1/16 of a block either way */
    public static final int MAX_SHIFT = 8;

    private int signal = SIGNAL_1997;
    private int shift = 0;
    /** the machines are drawn smaller than 1 block = 1 m to suit TrainCraft's track */
    public static final float DEFAULT_SCALE = 0.7f;
    private float scale = DEFAULT_SCALE;
    /** the track on the left of the player who placed the block (the model mirrored) */
    private boolean trackLeft = false;
    /** 0 = normal position, 1 = thrown; -1 until the first tick (then it starts where the switch is) */
    private float progress = -1f;
    private float prevProgress = 0f;
    private boolean poweringOut = false;

    /** how long one throw takes */
    protected abstract int travelTicks();

    /** a throw starts (server side) */
    protected void onStartMoving() {
    }

    @Override
    public void updateEntity() {
        float target = this.getIsSwitched() ? 1f : 0f;
        if (this.progress < 0f) {
            this.progress = target;
            this.poweringOut = target > 0.5f;
        }
        this.prevProgress = this.progress;
        if (this.progress != target) {
            if (!this.worldObj.isRemote && (this.progress == 0f || this.progress == 1f)) {
                this.onStartMoving();
            }
            float step = 1f / this.travelTicks();
            this.progress = target > this.progress ? Math.min(target, this.progress + step) : Math.max(target, this.progress - step);
        }
        if (!this.worldObj.isRemote) {
            boolean out = this.progress >= 0.5f;
            if (out != this.poweringOut) {
                this.poweringOut = out;
                this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType());
                this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType());
            }
        }
    }

    /** redstone output of the block: the switch is (more than halfway) thrown */
    public boolean isPoweringOut() {
        return this.poweringOut;
    }

    /** throw progress 0 .. 1 between ticks */
    public float getRenderProgress(float partialTicks) {
        if (this.progress < 0f) {
            return this.getIsSwitched() ? 1f : 0f;
        }
        return this.prevProgress + (this.progress - this.prevProgress) * partialTicks;
    }

    public int getSignal() {
        return this.signal;
    }

    public void setSignal(int signal) {
        this.signal = signal;
    }

    public int getShift() {
        return this.shift;
    }

    public void setShift(int shift) {
        this.shift = Math.max(-MAX_SHIFT, Math.min(MAX_SHIFT, shift));
    }

    public float getScale() {
        return this.scale;
    }

    public void setScale(float scale) {
        this.scale = Math.max(0.3f, Math.min(2.0f, scale));
    }

    public boolean isTrackLeft() {
        return this.trackLeft;
    }

    public void setTrackLeft(boolean trackLeft) {
        this.trackLeft = trackLeft;
    }

    public boolean isRightTurnout() {
        return this.getSwitchSide();
    }

    @Override
    public void readFromNBT(final NBTTagCompound NBTTC) {
        super.readFromNBT(NBTTC);
        this.signal = NBTTC.hasKey("czSignal") ? NBTTC.getInteger("czSignal") : SIGNAL_1997;
        this.setShift(NBTTC.getInteger("czShift"));
        this.setScale(NBTTC.hasKey("czScale") ? NBTTC.getFloat("czScale") : DEFAULT_SCALE);
        this.trackLeft = NBTTC.getBoolean("czTrackLeft");
    }

    @Override
    public void writeToNBT(final NBTTagCompound NBTTC) {
        super.writeToNBT(NBTTC);
        NBTTC.setInteger("czSignal", this.signal);
        NBTTC.setInteger("czShift", this.shift);
        NBTTC.setFloat("czScale", this.scale);
        NBTTC.setBoolean("czTrackLeft", this.trackLeft);
    }

    /** The Scaler and the GUI share this tile's own scale setting. */
    @Override
    public float getModelScale() {
        return this.getScale();
    }

    @Override
    public void setModelScale(float scale) {
        this.setScale(this.clampModelScale(scale));
    }

    @Override
    public float getMinModelScale() {
        return 0.3f;
    }

    /** TileSwitchCZRenderer scales the machines itself. */
    @Override
    public boolean isScaledByWrapper() {
        return false;
    }
}
