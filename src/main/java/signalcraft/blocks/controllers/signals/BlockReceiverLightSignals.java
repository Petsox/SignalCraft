package signalcraft.blocks.controllers.signals;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.controllers.BlockReceiver;
import signalcraft.entities.controllers.signals.lightSignals.TileReceiverLightSignals;
import signalcraft.proxy.CommonProxy;

public class BlockReceiverLightSignals extends BlockReceiver {
    public BlockReceiverLightSignals(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabSignals);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileReceiverLightSignals();
    }
}
