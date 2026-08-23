package signalcraft.blocks.gsar.blocks;

import signalcraft.proxy.CommonProxy;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import signalcraft.blocks.BlockGeneric;
import signalcraft.entities.gsar.blocks.TileBridgeGroundBeams;
import signalcraft.entities.gsar.blocks.TileLadder;


public class BlockLadder extends BlockBridgeBase {

    public BlockLadder(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileLadder();
    }

    @Override
    public boolean isLadder(final IBlockAccess world, final int x, final int y, final int z, final EntityLivingBase entity) {
        return true;
    }
}


