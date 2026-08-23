package signalcraft.blocks.controllers.universal;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.controllers.BlockController;
import signalcraft.entities.controllers.universal.TileReceiverUniversal;
import signalcraft.proxy.CommonProxy;


public class BlockUniversalReceiver extends BlockController {

    public BlockUniversalReceiver(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabOther);
    }

    @Override
    public void onNeighborBlockChange(final World world, final int x, final int y, final int z, final Block theBlock) {
        final TileEntity tileE = world.getTileEntity(x, y, z);
        if (tileE instanceof TileReceiverUniversal) {
            ((TileReceiverUniversal) tileE).activate(world.isBlockIndirectlyGettingPowered(x, y, z));
        }
    }

    @Override
    public boolean openGui(World world, int x, int y, int z, EntityPlayer player) {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileReceiverUniversal();
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }
}
