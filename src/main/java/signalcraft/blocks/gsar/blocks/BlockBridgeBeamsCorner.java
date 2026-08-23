package signalcraft.blocks.gsar.blocks;

import signalcraft.proxy.CommonProxy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.BlockGeneric;
import signalcraft.entities.gsar.blocks.TileBridgeBeams;
import signalcraft.entities.gsar.blocks.TileBridgeBeamsCorner;


public class BlockBridgeBeamsCorner extends BlockGeneric {

    public BlockBridgeBeamsCorner(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileBridgeBeamsCorner();
    }
}


