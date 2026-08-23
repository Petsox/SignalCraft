package signalcraft.blocks.gsar.blocks;

import signalcraft.proxy.CommonProxy;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import signalcraft.blocks.BlockGeneric;
import signalcraft.entities.gsar.blocks.TileBridgeGroundBeams;
import signalcraft.entities.gsar.blocks.TileRailing;


public class BlockRailing extends BlockBridgeBase {

    public BlockRailing(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileRailing();
    }
}


