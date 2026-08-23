package signalcraft.ItemBlocks.signSignals;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import signalcraft.ItemBlocks.SCItemBlocks;
import signalcraft.blocks.signals.signSignals.BlockSpeedSign;

public class ItemBlockSpeedSign extends ItemBlock {
    public ItemBlockSpeedSign(Block block) {
        super(block);
        setHasSubtypes(false);
    }

    public boolean onItemUse(final ItemStack itemStack, final EntityPlayer entityPlayer, final World world, int x, int y, int z, final int par7, final float par8, final float par9, final float par10) {
        if (par7 == 0) {
            return false;
        }
        if (!world.getBlock(x, y, z).getMaterial().isSolid()) {
            return false;
        }
        if (par7 == 1) {
            ++y;
        }
        if (par7 == 2) {
            --z;
        }
        if (par7 == 3) {
            ++z;
        }
        if (par7 == 4) {
            --x;
        }
        if (par7 == 5) {
            ++x;
        }
        if (!entityPlayer.canPlayerEdit(x, y, z, par7, itemStack)) {
            return false;
        }
        if (!SCItemBlocks.SPEED_SIGN.block.canPlaceBlockAt(world, x, y, z)) {
            return false;
        }
        if (world.isRemote) {
            return true;
        }
        final int i1 = MathHelper.floor_double((entityPlayer.rotationYaw + 180.0f) * 16.0f / 360.0f + 0.5) & 0xF;
        world.setBlock(x, y, z, SCItemBlocks.SPEED_SIGN.block, i1, 3);
        ((BlockSpeedSign)world.getBlock(x,y,z)).openGui(world,x,y,z,entityPlayer);
        --itemStack.stackSize;
        return true;
    }
}