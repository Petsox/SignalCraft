package signalcraft.blocks.switches.cz;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import signalcraft.blocks.switches.BlockSwitch;
import signalcraft.entities.switches.cz.TileSwitchCZ;
import signalcraft.proxy.CommonProxy;

/**
 * Czech point machine block: placed beside the TrainCraft switch, it powers it (redstone) once the throw is halfway.
 */
public abstract class BlockSwitchCZ extends BlockSwitch {

    public BlockSwitchCZ(String name, float height) {
        super(name);
        this.setCreativeTab(CommonProxy.tabSignals);
        this.setBlockBounds(0.1f, 0.0f, 0.1f, 0.9f, height, 0.9f);
    }

    @Override
    public void setBlockBoundsBasedOnState(final IBlockAccess world, final int x, final int y, final int z) {
    }

    @Override
    public int isProvidingWeakPower(final IBlockAccess world, final int x, final int y, final int z, final int meta) {
        final TileEntity tileE = world.getTileEntity(x, y, z);
        return tileE instanceof TileSwitchCZ && ((TileSwitchCZ) tileE).isPoweringOut() ? 15 : 0;
    }

    @Override
    public void breakBlock(final World world, final int x, final int y, final int z, final Block theBlock, final int meta) {
        super.breakBlock(world, x, y, z, theBlock, meta);
        world.notifyBlocksOfNeighborChange(x, y, z, this);
        world.notifyBlocksOfNeighborChange(x, y - 1, z, this);
    }
}
