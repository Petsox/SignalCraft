package signalcraft.entities.controllers.crossings;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import signalcraft.entities.controllers.TileController;
import signalcraft.entities.controllers.TileReceiver;
import signalcraft.entities.levelCrossings.IBarriers;
import signalcraft.entities.levelCrossings.ILevelCrossing;
import signalcraft.models.TextureRegistry;
import signalcraft.signalUtils.BlockPos;
import signalcraft.signalUtils.CrossingDebug;

/**
 * Receives the crossing controller's events and passes them to the crossing tiles stacked above it
 * (up to 10 blocks). Server side only; it has no state and no tick of its own - every timing (arm,
 * signals going dark once the arms are up) is a countdown the crossing tiles run themselves, on the
 * server and on every client alike (see ICrossingRuntime).
 */
public class TileCrossingReceiver extends TileReceiver {

    private final static ResourceLocation texture = TextureRegistry.RECE_CROSSING.get();
    private static final int STACK_HEIGHT = 10;

    public TileCrossingReceiver() {
        super(texture);
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    /** Switches every crossing tile above on. */
    public void activateStack() {
        CrossingDebug.log(this, "receiver '" + getName() + "': ON");
        for (int i = 1; i <= STACK_HEIGHT; ++i) {
            TileEntity tile = worldObj.getTileEntity(xCoord, yCoord + i, zCoord);
            if (tile instanceof ILevelCrossing) {
                ((ILevelCrossing) tile).setCrossingActive(true);
            }
        }
    }

    /**
     * Raises the barrier above this receiver now, and lets the signals mounted on it switch off once
     * it is up (plus its signal-off delay) - judged by every side against its own barrier.
     * @return {x, y, z, delayTicks} of the barrier, for the barrier-less signals of the same
     *         crossing to wait for too; null if there's no barrier
     */
    public int[] raiseBarrier() {
        IBarriers barrier = null;
        int barrierLevel = 0;
        for (int i = 1; i <= STACK_HEIGHT; ++i) {
            TileEntity tile = worldObj.getTileEntity(xCoord, yCoord + i, zCoord);
            if (tile instanceof IBarriers) {
                barrier = (IBarriers) tile;
                barrierLevel = i;
                break;
            }
        }
        if (barrier == null) return null;

        barrier.setCrossingActive(false);
        int[] pos = {xCoord, yCoord + barrierLevel, zCoord};
        int delay = Math.max(0, barrier.getSignalOffDelay()) * 20;
        CrossingDebug.log(this, "receiver '" + getName() + "': barrier UP (server expects it up in "
                + barrier.ticksUntilArmUp() + " ticks), its signals off when it is up + " + delay + " ticks");
        for (int i = barrierLevel + 1; i <= STACK_HEIGHT; ++i) {
            TileEntity tile = worldObj.getTileEntity(xCoord, yCoord + i, zCoord);
            if (tile instanceof ILevelCrossing) {
                ((ILevelCrossing) tile).deactivateWhenBarriersUp(pos, delay);
            }
        }
        return new int[]{pos[0], pos[1], pos[2], delay};
    }

    /** Switches every crossing tile above off once the given barriers (x, y, z triples) are up, plus delay ticks; none = now. */
    public void deactivateStackWhenUp(int[] barriers, int delayTicks) {
        CrossingDebug.log(this, "receiver '" + getName() + "': signals off when " + (barriers.length / 3)
                + " barrier(s) up + " + delayTicks + " ticks");
        for (int i = 1; i <= STACK_HEIGHT; ++i) {
            TileEntity tile = worldObj.getTileEntity(xCoord, yCoord + i, zCoord);
            if (tile instanceof ILevelCrossing) {
                ((ILevelCrossing) tile).deactivateWhenBarriersUp(barriers, delayTicks);
            }
        }
    }

    /**
     * The receiver's old one-call API, kept for addons (SignalCraft-Integrations' OpenComputers and
     * ProjectRed controllers drive receivers one by one with it).
     * <ul>
     *   <li>true - switches the crossing on;</li>
     *   <li>false with a barrier - raises it; its signals and the barrier-less receivers of the same
     *       name on this receiver's controllers switch off once it is up (+ its delay). Calling this
     *       for several barriers of one name makes those signals wait for all of them.</li>
     *   <li>false without a barrier - switches the crossing off now.</li>
     * </ul>
     */
    public void setCrossingState(Boolean activated) {
        if (activated) {
            activateStack();
            return;
        }
        int[] barrier = raiseBarrier();
        if (barrier == null) {
            deactivateStackWhenUp(new int[0], 0);
            return;
        }
        String name = getName();
        if (name == null) return;
        int[] pos = {barrier[0], barrier[1], barrier[2]};
        for (TileCrossingController controller : getControllers()) {
            if (controller == null) continue;
            for (TileCrossingReceiver sibling : controller.crossingReceivers()) {
                if (!sibling.signalHasBarriers() && name.equals(sibling.getName())) {
                    sibling.deactivateStackWhenUp(pos, barrier[3]);
                }
            }
        }
    }

    private ILevelCrossing findCrossing() {
        for (int i = 1; i <= STACK_HEIGHT; ++i) {
            TileEntity tile = worldObj.getTileEntity(xCoord, yCoord + i, zCoord);
            if (tile instanceof ILevelCrossing) {
                return (ILevelCrossing) tile;
            }
        }
        return null;
    }

    public boolean isArmDown() {
        ILevelCrossing crossing = findCrossing();
        if (crossing instanceof IBarriers) {
            return ((IBarriers) crossing).isArmDown();
        } else if (crossing != null) {
            return crossing.isCrossingActive();
        }
        return false;
    }

    public boolean signalHasBarriers() {
        for (int i = 1; i <= STACK_HEIGHT; ++i) {
            if (worldObj.getTileEntity(xCoord, yCoord + i, zCoord) instanceof IBarriers) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isControllerValid(TileController controller) {
        return controller instanceof ICrossingController;
    }

    @Override
    public TileCrossingController[] getControllers() {
        TileCrossingController[] controllers = new TileCrossingController[pairings.size()];
        int index = 0;
        for (BlockPos pos : pairings.keySet()) {
            TileEntity tileE = worldObj.getTileEntity(pos.getX(), pos.getY(), pos.getZ());
            if (tileE instanceof TileCrossingController) {
                controllers[index++] = (TileCrossingController) tileE;
            }
        }
        return controllers;
    }
}
