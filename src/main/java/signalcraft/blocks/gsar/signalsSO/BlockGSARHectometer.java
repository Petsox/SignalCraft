package signalcraft.blocks.gsar.signalsSO;

import signalcraft.proxy.CommonProxy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.signSignals.BlockSignSignal;
import signalcraft.entities.gsar.signalsSO.TileGSARHectometer;

public class BlockGSARHectometer extends BlockSignSignal
{

    public BlockGSARHectometer(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    @Override
    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileGSARHectometer();
    }
}
