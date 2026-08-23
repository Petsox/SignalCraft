package signalcraft.blocks.gsar.signalsRA;

import signalcraft.proxy.CommonProxy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.gsar.signalsHP.BlockGSARLightSignal;
import signalcraft.entities.gsar.signalsRA.TileGSARSignalRA11Y;


public class BlockGSARSignalRA11Y extends BlockGSARLightSignal
{

    public BlockGSARSignalRA11Y(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    @Override
    public boolean canPlaceBlockAt(final World world, final int x, final int y, final int z) {
        return (world.getBlock(x, y - 1, z) instanceof BlockGSARStativRA11);
    }

    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileGSARSignalRA11Y();
    }
}
