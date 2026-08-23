package signalcraft.blocks.gsar.signalsBU;

import signalcraft.proxy.CommonProxy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.gsar.signalsHP.BlockGSARLightSignal;
import signalcraft.blocks.gsar.signalsHP.BlockGSARStativLightSignalsVR;
import signalcraft.entities.gsar.signalsBU.TileGSARLightSignalBU0x3;
import signalcraft.entities.gsar.signalsBU.TileGSARLightSignalBU0x5;
import signalcraft.entities.gsar.signalsBU.TileGSARSignalBU2;


public class BlockGSARLightSignalBU0x5 extends BlockGSARLightSignal
{

    public BlockGSARLightSignalBU0x5(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    @Override
    public boolean canPlaceBlockAt(final World world, final int x, final int y, final int z) {
        return (world.getBlock(x, y - 1, z) instanceof BlockGSARStativLightSignalsVR);
    }

    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileGSARLightSignalBU0x5();
    }
}
