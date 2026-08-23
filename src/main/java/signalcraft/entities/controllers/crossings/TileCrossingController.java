package signalcraft.entities.controllers.crossings;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import signalcraft.entities.controllers.TileController;
import signalcraft.models.TextureRegistry;
import signalcraft.signalUtils.BlockPos;

import java.util.Map;

public class TileCrossingController extends TileController implements ICrossingController {

    private static final ResourceLocation TEXTURE = TextureRegistry.CONT_CROSSING.get();

    public TileCrossingController() {
        super(TEXTURE);
    }

    protected TileCrossingController(ResourceLocation texture) {
        super(texture);
    }

    public void updateReceiversWithoutBarriers(boolean goDown) {
        for (Map.Entry<BlockPos, Integer> entry : getPairings().entrySet()) {
            BlockPos pos = entry.getKey();
            TileEntity tile = worldObj.getTileEntity(pos.getX(), pos.getY(), pos.getZ());

            if (tile instanceof TileCrossingReceiver) {
                TileCrossingReceiver receiver = (TileCrossingReceiver) tile;
                if (!receiver.signalHasBarriers()) {
                    receiver.setCrossingState(goDown);
                }
            }
        }
    }

    public void updateReceiversWithBarriers(boolean goDown) {
        for (Map.Entry<BlockPos, Integer> entry : getPairings().entrySet()) {
            BlockPos pos = entry.getKey();
            TileEntity tile = worldObj.getTileEntity(pos.getX(), pos.getY(), pos.getZ());

            if (tile instanceof TileCrossingReceiver) {
                TileCrossingReceiver receiver = (TileCrossingReceiver) tile;
                if (receiver.signalHasBarriers()) {
                    receiver.setCrossingState(goDown);
                }
            }
        }
    }

    public void updateAllReceivers(boolean goDown) {
        for (Map.Entry<BlockPos, Integer> entry : getPairings().entrySet()) {
            BlockPos pos = entry.getKey();
            TileEntity tile = worldObj.getTileEntity(pos.getX(), pos.getY(), pos.getZ());

            if (tile instanceof TileCrossingReceiver) {
                ((TileCrossingReceiver) tile).setCrossingState(goDown);
            }
        }
    }

    public boolean crossingHasBarriers() {
        for (Map.Entry<BlockPos, Integer> entry : getPairings().entrySet()) {
            BlockPos pos = entry.getKey();
            TileEntity tile = worldObj.getTileEntity(pos.getX(), pos.getY(), pos.getZ());

            if (tile instanceof TileCrossingReceiver) {
                if (((TileCrossingReceiver) tile).signalHasBarriers()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setBarrierState(boolean goDown) {
        if (goDown) {
            // always drop all barriers
            updateAllReceivers(true);
            return;
        }

        // raising barriers
        if (crossingHasBarriers()) {
            // only raise those that actually have barriers
            updateReceiversWithBarriers(false);
        } else {
            // no barriers present -> update all
            updateAllReceivers(false);
        }
    }
}
