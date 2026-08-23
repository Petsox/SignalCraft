package signalcraft.blocks.gsar.signalsHP;

import signalcraft.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import signalcraft.blocks.signals.lightSignals.BlockLightSignal;
import signalcraft.entities.gsar.signalsHP.TileGSARStativ;

public class BlockGSARStativ extends BlockLightSignal
{

    public BlockGSARStativ(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }
    
    public boolean getBlocksMovement(final IBlockAccess blockAccess, final int x, final int y, final int z) {
        return true;
    }

    public void onNeighborBlockChange(final World world, final int x, final int y, final int z, final Block theBlock) {
        boolean flag = !world.getBlock(x, y - 1, z).getMaterial().isSolid();
        if (flag) {
            final TileGSARStativ tileE = (TileGSARStativ)world.getTileEntity(x, y, z);
            if (tileE != null) {
                this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
                world.setBlockToAir(x, y, z);
            }
        }
        super.onNeighborBlockChange(world, x, y, z, theBlock);
    }
    
    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileEntity();
    }
}
