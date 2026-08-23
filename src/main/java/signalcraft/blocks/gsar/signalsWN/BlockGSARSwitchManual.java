package signalcraft.blocks.gsar.signalsWN;

import signalcraft.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.switches.BlockSwitch;
import signalcraft.entities.gsar.signalsWN.TileSwitchManualGSAR;


public class BlockGSARSwitchManual extends BlockSwitch {
    public BlockGSARSwitchManual(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileSwitchManualGSAR();
    }

    @Override
    public void onBlockDestroyedByPlayer(final World world, final int x, final int y, final int z, final int meta) {
        world.notifyBlocksOfNeighborChange(x, y, z, this);
        world.notifyBlocksOfNeighborChange(x, y - 1, z, this);
    }

    @Override
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer entityPlayer, final int meta, final float hitX, final float hitY, final float hitZ) {
        if (world.isRemote) {
            return true;
        }
        final TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof TileSwitchManualGSAR)) {
            // A foreign tile (e.g. RailCraft's TileHidden) can occupy this position; casting
            // it unguarded would throw an uncaught ClassCastException.
            return true;
        }
        final TileSwitchManualGSAR tileE = (TileSwitchManualGSAR) tileEntity;
        final boolean leverActivated = tileE.getIsSwitched();
        tileE.setIsActive(!leverActivated);
        return true;

    }

    public void breakBlock(final World world, final int x, final int y, final int z, final Block theBlock, final int meta) {
        if ((meta & 0x8) > 0) {
            world.notifyBlocksOfNeighborChange(x, y - 1, z, this);
            final int i1 = meta & 0x7;
            if (i1 == 1) {
                world.notifyBlocksOfNeighborChange(x - 1, y, z, this);
            } else if (i1 == 2) {
                world.notifyBlocksOfNeighborChange(x + 1, y, z, this);
            } else if (i1 == 3) {
                world.notifyBlocksOfNeighborChange(x, y, z - 1, this);
            } else if (i1 == 4) {
                world.notifyBlocksOfNeighborChange(x, y, z + 1, this);
            } else if (i1 == 5) {
                world.notifyBlocksOfNeighborChange(x, y - 1, z, this);
            }
        }
        super.breakBlock(world, x, y, z, theBlock, meta);
    }
}
