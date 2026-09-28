package signalcraft.entities.controllers.crossings;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import signalcraft.SignalCraft;
import signalcraft.entities.controllers.TileController;
import signalcraft.entities.controllers.TileReceiver;
import signalcraft.entities.levelCrossings.IBarriers;
import signalcraft.entities.levelCrossings.ILevelCrossing;
import signalcraft.entities.levelCrossings.IOnBarriers;
import signalcraft.messages.MessageActiveUpdate;
import signalcraft.models.TextureRegistry;
import signalcraft.signalUtils.BlockPos;

public class TileCrossingReceiver extends TileReceiver {

    private final static ResourceLocation texture = TextureRegistry.RECE_CROSSING.get();

    public TileCrossingReceiver() {
        super(texture);
    }

    private TileEntity tileE;

    /** True from the moment the barrier deactivates until the signals it guards have been turned off. */
    private boolean pendingSignalOff = false;
    /** -1 = not counting yet; counts down once the arm is confirmed fully up. */
    private int signalOffDelayTimer = -1;

    /**
     * Single, server-authoritative place that watches a barrier crossing raise and, once the arm is
     * fully up (plus the configured delay), restores the on-barrier signal's sound and cascades
     * deactivation to the barrier-less signals sharing this crossing's name. Living here (instead of
     * duplicated inside every barrier tile's updateEntity) means clients never make this call off of
     * their own locally-simulated arm rotation - they only ever react to the server's packets - which
     * is what caused the additional signals to drop before the barrier arm visually finished rising
     * on laggy servers.
     */
    @Override
    public void updateEntity() {
        if (worldObj == null || worldObj.isRemote) return;

        ILevelCrossing found = findCrossing();
        if (!(found instanceof IBarriers)) return;

        IBarriers barrier = (IBarriers) found;
        TileEntity barrierTile = (TileEntity) found;

        if (found.isCrossingActive()) {
            pendingSignalOff = true;
            signalOffDelayTimer = -1;
            if (barrier.isArmDown()) {
                TileEntity onTop = worldObj.getTileEntity(barrierTile.xCoord, barrierTile.yCoord + 1, barrierTile.zCoord);
                if (onTop instanceof IOnBarriers) ((IOnBarriers) onTop).setStrongSoundOn(false);
            }
            return;
        }

        if (!pendingSignalOff || !barrier.isArmUp()) return;

        if (signalOffDelayTimer < 0) {
            signalOffDelayTimer = barrier.getSignalOffDelay() * 20;
        }
        if (signalOffDelayTimer > 0) {
            --signalOffDelayTimer;
            return;
        }

        TileEntity onTop = worldObj.getTileEntity(barrierTile.xCoord, barrierTile.yCoord + 1, barrierTile.zCoord);
        if (onTop instanceof IOnBarriers) {
            ((IOnBarriers) onTop).setStrongSoundOn(true);
            updateCrossing((ILevelCrossing) onTop, false, onTop);
        }
        deactivateBarrierlessSiblings();

        pendingSignalOff = false;
        signalOffDelayTimer = -1;
    }

    /**
     * Only raises the barrier-less receivers that share this barrier's own receiver name - a
     * controller can have several independently-named crossing groups paired to it, and raising
     * every barrier-less receiver on the controller would affect unrelated groups too.
     */
    private void deactivateBarrierlessSiblings() {
        String crossingName = this.getName();
        if (crossingName == null) return;
        for (TileCrossingController controller : getControllers()) {
            if (controller == null) continue;
            for (TileReceiver receiver : controller.getReceivers()) {
                if (receiver instanceof TileCrossingReceiver
                        && !((TileCrossingReceiver) receiver).signalHasBarriers()
                        && crossingName.equals(receiver.getName())) {
                    ((TileCrossingReceiver) receiver).setCrossingState(false);
                }
            }
        }
    }

    /**
     * Sets the crossing state (active/deactivated)
     *
     * @param activated - true = barriers up, false = barriers down
     */
    public void setCrossingState(Boolean activated) {
        for (int i = 1; i <= 10; ++i) {
            tileE = worldObj.getTileEntity(xCoord, yCoord + i, zCoord);
            if (tileE instanceof ILevelCrossing) {
                ILevelCrossing crossing = (ILevelCrossing) tileE;
                if (!(tileE instanceof IBarriers)) {
                    updateCrossing(crossing, activated, tileE);
                } else {
                    updateCrossing(crossing, activated, tileE);
                    if (!activated) break;
                }
            }
        }
    }

    private void updateCrossing(ILevelCrossing crossing, boolean activated, TileEntity crossingSignal) {
        crossing.setCrossingActive(activated);
        if (!worldObj.isRemote) {
            SignalCraft.SCNet.sendToAll(new MessageActiveUpdate(crossingSignal.xCoord, crossingSignal.yCoord, crossingSignal.zCoord, activated));
        }
    }

    private ILevelCrossing findCrossing() {
        for (int i = 1; i <= 10; ++i) {
            tileE = worldObj.getTileEntity(xCoord, yCoord + i, zCoord);
            if (tileE instanceof ILevelCrossing) {
                return (ILevelCrossing) tileE;
            }
        }
        return null;
    }

    public boolean isArmDown(){
        ILevelCrossing crossing = findCrossing();
        if (crossing instanceof IBarriers){
            return ((IBarriers) crossing).isArmDown();
        } else if (crossing != null){
            return crossing.isCrossingActive();
        }
        return false;
    }

    public boolean signalHasBarriers() {
        return findCrossing() instanceof IBarriers;
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
