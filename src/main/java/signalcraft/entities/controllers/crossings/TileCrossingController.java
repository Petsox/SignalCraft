package signalcraft.entities.controllers.crossings;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import signalcraft.entities.controllers.TileController;
import signalcraft.entities.controllers.TileReceiver;
import signalcraft.models.TextureRegistry;
import signalcraft.signalUtils.BlockPos;
import signalcraft.signalUtils.CrossingDebug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Drives a level crossing from redstone. Server side only. All decisions are made here, once, at the
 * moment the power changes:
 * <ul>
 *   <li>power on - every paired crossing switches on;</li>
 *   <li>power off - every barrier starts up; the signals on a barrier wait for that barrier, the
 *       barrier-less signals whose receiver has the same name as barriers' receivers wait for all of
 *       those barriers to be up, then the (longest) signal-off delay. Barrier-less receivers of this
 *       controller with no barrier of their name switch off at once.</li>
 * </ul>
 * "Wait for these barriers" is judged by every client against the barriers it shows, so the signals
 * go dark just when the barriers are up there - nothing has to be sent at that moment.
 */
public class TileCrossingController extends TileController implements ICrossingController {

    private static final ResourceLocation TEXTURE = TextureRegistry.CONT_CROSSING.get();

    /** Last redstone state acted on - block updates that don't change it are ignored. Not saved: after a restart the first update applies it again. */
    private Boolean lastPowered = null;

    public TileCrossingController() {
        super(TEXTURE);
    }

    protected TileCrossingController(ResourceLocation texture) {
        super(texture);
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    /** Redstone input (from the block's neighbour update) - acts only when it actually changes. */
    public void onRedstone(boolean powered) {
        if (lastPowered != null && lastPowered == powered) return;
        lastPowered = powered;
        CrossingDebug.log(this, "controller '" + getName() + "' power " + (powered ? "ON" : "OFF"));
        setBarrierState(powered);
    }

    public void setBarrierState(boolean goDown) {
        List<TileCrossingReceiver> own = crossingReceivers();
        if (goDown) {
            for (TileCrossingReceiver receiver : own) {
                receiver.activateStack();
            }
            return;
        }

        Map<String, List<Integer>> barriersByName = new HashMap<>();
        Map<String, Integer> delayByName = new HashMap<>();
        Set<TileCrossingReceiver> barrierless = new LinkedHashSet<>();
        for (TileCrossingReceiver receiver : own) {
            if (!receiver.signalHasBarriers()) {
                barrierless.add(receiver);
                continue;
            }
            int[] barrier = receiver.raiseBarrier();
            if (barrier == null) continue;
            String name = nameOf(receiver);
            List<Integer> list = barriersByName.computeIfAbsent(name, k -> new ArrayList<>());
            list.add(barrier[0]);
            list.add(barrier[1]);
            list.add(barrier[2]);
            delayByName.merge(name, barrier[3], Math::max);
            // barrier-less signals of the same crossing may hang on another controller of this barrier
            for (TileCrossingController other : receiver.getControllers()) {
                if (other == null || other == this) continue;
                for (TileCrossingReceiver sibling : other.crossingReceivers()) {
                    if (!sibling.signalHasBarriers()) barrierless.add(sibling);
                }
            }
        }

        for (TileCrossingReceiver receiver : barrierless) {
            String name = nameOf(receiver);
            List<Integer> barriers = barriersByName.get(name);
            if (barriers != null) {
                int[] coords = new int[barriers.size()];
                for (int i = 0; i < coords.length; i++) coords[i] = barriers.get(i);
                receiver.deactivateStackWhenUp(coords, delayByName.get(name));
            } else if (own.contains(receiver)) {
                receiver.deactivateStackWhenUp(new int[0], 0);
            }
        }
    }

    /** This controller's paired crossing receivers that are loaded. */
    public List<TileCrossingReceiver> crossingReceivers() {
        List<TileCrossingReceiver> result = new ArrayList<>();
        for (Map.Entry<BlockPos, Integer> entry : getPairings().entrySet()) {
            BlockPos pos = entry.getKey();
            TileEntity tile = worldObj.getTileEntity(pos.getX(), pos.getY(), pos.getZ());
            if (tile instanceof TileCrossingReceiver) {
                result.add((TileCrossingReceiver) tile);
            }
        }
        return result;
    }

    public boolean crossingHasBarriers() {
        for (TileCrossingReceiver receiver : crossingReceivers()) {
            if (receiver.signalHasBarriers()) return true;
        }
        return false;
    }

    private static String nameOf(TileReceiver receiver) {
        return receiver.getName() == null ? "" : receiver.getName();
    }
}
