package signalcraft.blocks.signals.lightSignals.azd70;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.lightSignals.BlockLightSignal;
import signalcraft.entities.signals.lightSignals.azd70.TileAZD2LightsT;
import signalcraft.proxy.CommonProxy;

public class BlockAZD2LightsT extends BlockLightSignal
{
    public BlockAZD2LightsT(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabSignals);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileAZD2LightsT();
    }
}
