package signalcraft.blocks.gsar.signalsHP;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import signalcraft.blocks.signals.lightSignals.BlockLightSignal;

public abstract class BlockGSARLightSignal extends BlockLightSignal
{
    public BlockGSARLightSignal(String Name) {
        super(Name);
    }

    @Override
    public boolean canPlaceBlockAt(final World world, final int x, final int y, final int z) {
        return false;
    }

    @Override
    public void onNeighborBlockChange(final World world, final int x, final int y, final int z, final Block theBlock) {
        boolean flag = !world.getBlock(x, y - 1, z).getMaterial().isSolid();
        if (flag) {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
        super.onNeighborBlockChange(world, x, y, z, theBlock);
    }
}
