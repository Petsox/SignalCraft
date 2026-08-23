package signalcraft.ItemBlocks.gsar.signalsSO;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import signalcraft.ItemBlocks.SCItemBlock;
import signalcraft.ItemBlocks.SCItemBlocks;
import signalcraft.blocks.ISCBlock;
import signalcraft.blocks.SCBlocks;

public class ItemBlockGSARStationSign extends SCItemBlock {
    public ItemBlockGSARStationSign(Block block) {
        super(block);
        setHasSubtypes(false);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!world.getBlock(x, y, z).getMaterial().isSolid()) {
            return false;
        }
        switch (side) {
            case 0:
                --y;
                break;
            case 1:
                ++y;
                break;
            case 2:
                --z;
                break;
            case 3:
                ++z;
                break;
            case 4:
                --x;
                break;
            case 5:
                ++x;
                break;
        }

        if (!player.canPlayerEdit(x, y, z, side, itemStack)) {
            return false;
        }

        if (!this.field_150939_a.canPlaceBlockAt(world, x, y, z)) {
            return false;
        }

        if (world.isRemote) {
            return true;
        }

        int rotation = MathHelper.floor_double(
                (player.rotationYaw + 180.0F) * 4.0F / 360.0F + 0.5D) & 3;

        boolean standingSign = (side == 0 || side == 1);

        if (standingSign) {
            world.setBlock(x, y, z, SCItemBlocks.STATION_SIGN.block, rotation, 2);
        } else {
            int checkX = x;
            int checkY = y;
            int checkZ = z;

            switch (side) {
                case 2:
                    ++checkZ;
                    break;
                case 3:
                    --checkZ;
                    break;
                case 4:
                    ++checkX;
                    break;
                case 5:
                    --checkX;
                    break;
            }

            Block adjacent = world.getBlock(checkX, checkY, checkZ);

            if (isStationSign(adjacent)) {
                world.setBlock(x, y, z, SCItemBlocks.STATION_SIGN.block, rotation, 2);
            } else {
                world.setBlock(x, y, z, SCItemBlocks.GSAR_STATION_SIGN_WALL.block, side, 2);
            }
        }

        ((ISCBlock) world.getBlock(x, y, z))
                .openGui(world, x, y, z, player);

        itemStack.stackSize--;

        return true;
    }

    private boolean isStationSign(Block block) {
        return block == SCItemBlocks.STATION_SIGN.block
                || block == SCItemBlocks.GSAR_STATION_SIGN_WALL.block;
    }
}
