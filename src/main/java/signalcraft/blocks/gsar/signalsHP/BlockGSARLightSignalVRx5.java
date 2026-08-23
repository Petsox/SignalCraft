package signalcraft.blocks.gsar.signalsHP;

import signalcraft.proxy.CommonProxy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.entities.gsar.signalsHP.TileGSARLightSignalVRx5;

public class BlockGSARLightSignalVRx5 extends BlockGSARLightSignal
{
    public BlockGSARLightSignalVRx5(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    @Override
    public boolean canPlaceBlockAt(final World world, final int x, final int y, final int z) {
        return world.getBlock(x, y - 1, z) instanceof BlockGSARStativLightSignalsVR;
    }

    @Override
    public TileEntity createNewTileEntity(final World world, final int metaData) {
        return new TileGSARLightSignalVRx5();
    }
}
