package signalcraft.entities.controllers.crossings;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import signalcraft.SignalCraft;
import signalcraft.entities.controllers.TileController;
import signalcraft.entities.controllers.TileReceiver;
import signalcraft.entities.levelCrossings.IBarriers;
import signalcraft.entities.levelCrossings.ILevelCrossing;
import signalcraft.messages.MessageActiveUpdate;
import signalcraft.models.TextureRegistry;
import signalcraft.signalUtils.BlockPos;

public class TileCrossingReceiver extends TileReceiver {

    private final static ResourceLocation texture = TextureRegistry.RECE_CROSSING.get();

    public TileCrossingReceiver() {
        super(texture);
    }

    private TileEntity tileE;

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
