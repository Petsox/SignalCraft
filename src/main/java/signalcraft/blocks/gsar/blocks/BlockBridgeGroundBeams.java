package signalcraft.blocks.gsar.blocks;

import signalcraft.proxy.CommonProxy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.BlockGeneric;
import signalcraft.entities.gsar.blocks.TileBridgeGround;
import signalcraft.entities.gsar.blocks.TileBridgeGroundBeams;


public class BlockBridgeGroundBeams extends BlockGeneric {

    public BlockBridgeGroundBeams(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileBridgeGroundBeams();
    }
}


