package signalcraft.blocks.signals.lightSignals.azd70;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.lightSignals.BlockLightSignal;
import signalcraft.entities.signals.lightSignals.azd70.TileAZDAB4;
import signalcraft.proxy.CommonProxy;

public class BlockAZDAB4 extends BlockLightSignal
{
    public BlockAZDAB4(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabSignals);
    }
    @Override
    public TileEntity createNewTileEntity(final World world, final int metaData) {
        return new TileAZDAB4();
    }
}

