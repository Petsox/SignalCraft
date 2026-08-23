package signalcraft.blocks.gsar.signalsSO;

import signalcraft.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import signalcraft.blocks.signals.signSignals.BlockSignSignal;
import signalcraft.entities.gsar.signalsSO.TileGSARSignalLever;

public class BlockGSARSignalLever extends BlockSignSignal
{
    public static final String[] texturesNames;

    public BlockGSARSignalLever(String name) {
        super(name);
        this.setHardness(4.0f);
        this.setResistance(10.0f);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    @Override
    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileGSARSignalLever();
    }

    @Override
    public void onBlockPlacedBy(final World world, final int x, final int y, final int z, final EntityLivingBase entityPlayer, final ItemStack itemStack) {
        ((TileGSARSignalLever)world.getTileEntity(x, y, z)).setIsActive(false);
    }

    @Override
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer entityPlayer, final int meta, final float hitX, final float hitY, final float hitZ) {
        if (world.isRemote) {
            return true;
        }
        if (world.getTileEntity(x, y, z) != null) {
            final TileGSARSignalLever tileE = (TileGSARSignalLever)world.getTileEntity(x, y, z);

            if (tileE.getRotate() == 0 || tileE.getRotate() == 40) {
                final boolean leverActivated = tileE.getIsActive();
                tileE.setIsActive(!leverActivated);
                world.notifyBlocksOfNeighborChange(x, y, z, this);
                world.notifyBlocksOfNeighborChange(x, y - 1, z, this);
                return true;
            }
        }
        return true;
    }

    @Override
    public void breakBlock(final World world, final int x, final int y, final int z, final Block theBlock, final int meta) {
        if ((meta & 0x8) > 0) {
            world.notifyBlocksOfNeighborChange(x, y - 1, z, this);
            final int i1 = meta & 0x7;
            if (i1 == 1) {
                world.notifyBlocksOfNeighborChange(x - 1, y, z, this);
            }
            else if (i1 == 2) {
                world.notifyBlocksOfNeighborChange(x + 1, y, z, this);
            }
            else if (i1 == 3) {
                world.notifyBlocksOfNeighborChange(x, y, z - 1, this);
            }
            else if (i1 == 4) {
                world.notifyBlocksOfNeighborChange(x, y, z + 1, this);
            }
            else if (i1 == 5) {
                world.notifyBlocksOfNeighborChange(x, y - 1, z, this);
            }
        }
        super.breakBlock(world, x, y, z, theBlock, meta);
    }

    @Override
    public int isProvidingWeakPower(final IBlockAccess world, final int x, final int y, final int z, final int meta) {
        final TileEntity tileE = world.getTileEntity(x, y, z);
        final boolean leverActivated = tileE != null && ((TileGSARSignalLever)tileE).getIsActive();
        return leverActivated ? 15 : 0;
    }

    @Override
    public int isProvidingStrongPower(final IBlockAccess world, final int x, final int y, final int z, final int meta) {
        return this.isProvidingWeakPower(world, x, y, z, meta);
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }
    
    static {
        texturesNames = new String[] { "wool_colored_black", "wool_colored_red", "wool_colored_green", "wool_colored_brown", "wool_colored_blue", "wool_colored_purple", "wool_colored_cyan", "wool_colored_silver", "wool_colored_gray", "wool_colored_pink", "wool_colored_lime", "wool_colored_yellow", "wool_colored_light_blue", "wool_colored_magenta", "wool_colored_orange", "wool_colored_white" };
    }
}
