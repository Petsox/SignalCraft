package signalcraft.blocks.gsar.signalsHP;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import signalcraft.blocks.signals.lightSignals.BlockLightSignal;

public abstract class BlockGSARSemiSignals extends BlockLightSignal
{
    public boolean needsStativ;
    public boolean isSemiSignal;

    public BlockGSARSemiSignals(String Name) {
        super(Name);
    }
    
    public boolean canPlaceBlockAt(final World world, final int x, final int y, final int z) {
        return false;
    }
    
    public void onNeighborBlockChange(final World world, final int x, final int y, final int z, final Block theBlock) {
        boolean flag = false;
        if (this.isSemiSignal) {
            if (!world.getBlock(x, y - 1, z).getMaterial().isSolid()) {
                flag = true;
            }
        }
        else {
            final int l = world.getBlockMetadata(x, y, z);
            flag = l != 2 || !world.getBlock(x, y, z + 1).getMaterial().isSolid();
            if (l == 3 && world.getBlock(x, y, z - 1).getMaterial().isSolid()) {
                flag = false;
            }
            if (l == 4 && world.getBlock(x + 1, y, z).getMaterial().isSolid()) {
                flag = false;
            }
            if (l == 5 && world.getBlock(x - 1, y, z).getMaterial().isSolid()) {
                flag = false;
            }
        }
        if (flag) {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
        super.onNeighborBlockChange(world, x, y, z, theBlock);
    }
}
