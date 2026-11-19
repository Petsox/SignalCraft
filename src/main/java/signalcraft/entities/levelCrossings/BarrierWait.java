package signalcraft.entities.levelCrossings;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.signalUtils.CrossingDebug;

/**
 * "Switch off once these barriers are up, then after a delay" - the pending switch-off of a
 * crossing signal (see CrossingEvent.OFF_WHEN_UP). Judged every tick against the barriers as THIS
 * side has them, so on each client the lamps go out exactly when the barriers it shows are up.
 */
public final class BarrierWait {
    /** x, y, z of each barrier still to wait for; null = not waiting for barriers. */
    private int[] barriers = null;
    private int delay = 0;
    /** Ticks left after all barriers came up; -1 = not counting. */
    private int countdown = -1;

    /**
     * Starts waiting for the barriers. While already waiting, the new barriers are added to those
     * (and the longer delay kept) - several receivers may each name their own barrier for the same
     * signal. Both sides get the same events in the same order, so they end up with the same list.
     */
    public void start(int[] barriers, int delay) {
        if (this.barriers != null) {
            this.barriers = union(this.barriers, barriers);
            this.delay = Math.max(this.delay, Math.max(0, delay));
        } else {
            this.barriers = barriers.clone();
            this.delay = Math.max(0, delay);
        }
        this.countdown = -1;
    }

    private static int[] union(int[] a, int[] b) {
        int[] out = java.util.Arrays.copyOf(a, a.length + b.length);
        int n = a.length;
        outer:
        for (int i = 0; i + 2 < b.length; i += 3) {
            for (int j = 0; j + 2 < n; j += 3) {
                if (out[j] == b[i] && out[j + 1] == b[i + 1] && out[j + 2] == b[i + 2]) continue outer;
            }
            out[n++] = b[i];
            out[n++] = b[i + 1];
            out[n++] = b[i + 2];
        }
        return java.util.Arrays.copyOf(out, n);
    }

    public void clear() {
        this.barriers = null;
        this.countdown = -1;
    }

    public boolean isRunning() {
        return this.barriers != null || this.countdown >= 0;
    }

    /** One tick of the owner. @return true when the owner should switch off now. */
    public boolean tick(TileEntity owner) {
        if (this.barriers != null) {
            if (!allUp(owner.getWorldObj())) return false;
            this.barriers = null;
            this.countdown = this.delay;
            CrossingDebug.log(owner, "all barriers up -> off in " + this.delay + " ticks");
        } else if (this.countdown > 0) {
            --this.countdown;
        } else {
            return false;
        }
        if (this.countdown == 0) {
            this.countdown = -1;
            return true;
        }
        return false;
    }

    /** A barrier that isn't loaded on this side (or is gone) doesn't hold the signal on. */
    private boolean allUp(World world) {
        for (int i = 0; i + 2 < this.barriers.length; i += 3) {
            int x = this.barriers[i], y = this.barriers[i + 1], z = this.barriers[i + 2];
            if (!world.blockExists(x, y, z)) continue;
            TileEntity tile = world.getTileEntity(x, y, z);
            if (tile instanceof IBarriers && !((IBarriers) tile).isArmUp()) return false;
        }
        return true;
    }

    public void write(NBTTagCompound tag) {
        if (this.barriers != null) tag.setIntArray("offWaitBarriers", this.barriers);
        tag.setInteger("offWaitDelay", this.delay);
        tag.setInteger("offWaitCountdown", this.countdown);
    }

    public void read(NBTTagCompound tag) {
        this.barriers = tag.hasKey("offWaitBarriers") ? tag.getIntArray("offWaitBarriers") : null;
        this.delay = tag.getInteger("offWaitDelay");
        this.countdown = tag.hasKey("offWaitCountdown") ? tag.getInteger("offWaitCountdown") : -1;
    }

    public String describe() {
        if (this.barriers != null) return "offWhen " + (this.barriers.length / 3) + " barrier(s) up +" + this.delay;
        if (this.countdown >= 0) return "offIn=" + this.countdown;
        return "offWait=none";
    }
}
