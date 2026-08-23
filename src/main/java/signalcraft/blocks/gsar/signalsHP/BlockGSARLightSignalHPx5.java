package signalcraft.blocks.gsar.signalsHP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.entities.gsar.signalsHP.TileGSARLightSignalHPx5;
import signalcraft.proxy.CommonProxy;

public class BlockGSARLightSignalHPx5 extends BlockGSARLightSignal
{
    public BlockGSARLightSignalHPx5(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    @Override
    public boolean canPlaceBlockAt(final World world, final int x, final int y, final int z) {
        return (world.getBlock(x, y - 1, z) instanceof BlockGSARStativLightSignals);
    }

    @Override
    public TileEntity createNewTileEntity(final World world, final int metaData) {
        return new TileGSARLightSignalHPx5();
    }
}
