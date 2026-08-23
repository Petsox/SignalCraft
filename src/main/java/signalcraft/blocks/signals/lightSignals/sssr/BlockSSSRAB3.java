package signalcraft.blocks.signals.lightSignals.sssr;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.lightSignals.BlockLightSignal;
import signalcraft.entities.signals.lightSignals.azd70.TileAZDAB3;
import signalcraft.entities.signals.lightSignals.sssr.TileSSSRAB3;
import signalcraft.proxy.CommonProxy;

public class BlockSSSRAB3 extends BlockLightSignal
{
    public BlockSSSRAB3(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabSignals);
    }
    @Override
    public TileEntity createNewTileEntity(final World world, final int metaData) {
        return new TileSSSRAB3();
    }
}
