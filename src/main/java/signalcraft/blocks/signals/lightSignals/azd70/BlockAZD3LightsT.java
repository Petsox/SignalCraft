package signalcraft.blocks.signals.lightSignals.azd70;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.lightSignals.BlockLightSignal;
import signalcraft.entities.signals.lightSignals.azd70.TileAZD3LightsT;
import signalcraft.proxy.CommonProxy;

public class BlockAZD3LightsT extends BlockLightSignal
{
    public BlockAZD3LightsT(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabSignals);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileAZD3LightsT();
    }
}
