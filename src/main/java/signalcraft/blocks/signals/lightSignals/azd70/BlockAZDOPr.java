package signalcraft.blocks.signals.lightSignals.azd70;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.lightSignals.BlockLightSignal;
import signalcraft.entities.signals.lightSignals.azd70.TileAZDOPr;
import signalcraft.proxy.CommonProxy;

public class BlockAZDOPr extends BlockLightSignal
{
    public BlockAZDOPr(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabSignals);
    }
    @Override
    public TileEntity createNewTileEntity(final World world, final int metaData) {
        return new TileAZDOPr();
    }
}
