package signalcraft.blocks.gsar.signalsSH;

import signalcraft.proxy.CommonProxy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.gsar.signalsRA.BlockGSARStativRA11;
import signalcraft.blocks.signals.signSignals.BlockSignSignal;
import signalcraft.entities.gsar.signalsSH.TileGSARLightSignalSHL;


public class BlockGSARSignalSHL extends BlockSignSignal
{

    public BlockGSARSignalSHL(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    @Override
    public boolean canPlaceBlockAt(final World world, final int x, final int y, final int z) {
        return (world.getBlock(x, y - 1, z) instanceof BlockGSARStativRA11);
    }

    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileGSARLightSignalSHL();
    }
}
