package signalcraft.blocks.controllers.crossings;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.controllers.BlockController;
import signalcraft.entities.controllers.crossings.TileCrossingController;
import signalcraft.proxy.CommonProxy;

public class BlockCrossingController extends BlockController {
    public BlockCrossingController(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabCrossings);
    }
    @Override
    public void onNeighborBlockChange(final World world, final int x, final int y, final int z, final Block theBlock) {
        final TileEntity tileE = world.getTileEntity(x, y, z);
        if (tileE instanceof TileCrossingController) {
            ((TileCrossingController) tileE).setBarrierState(world.isBlockIndirectlyGettingPowered(x, y, z));
        }
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public boolean openGui(World world, int x, int y, int z, EntityPlayer player) {
        return false;
    }
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileCrossingController();
    }
}
