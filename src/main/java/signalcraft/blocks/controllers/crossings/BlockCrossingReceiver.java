package signalcraft.blocks.controllers.crossings;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.controllers.BlockReceiver;
import signalcraft.entities.controllers.crossings.TileCrossingReceiver;
import signalcraft.proxy.CommonProxy;

public class BlockCrossingReceiver extends BlockReceiver {
    public BlockCrossingReceiver(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabCrossings);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileCrossingReceiver();
    }
}
