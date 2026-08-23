package signalcraft.blocks.signals.lightSignals.sssr;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.lightSignals.BlockLightSignal;
import signalcraft.entities.signals.lightSignals.sssr.TileSSSR3LightsT;
import signalcraft.proxy.CommonProxy;

public class BlockSSSR3LightsT extends BlockLightSignal {
    public BlockSSSR3LightsT(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabSignals);
    }
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileSSSR3LightsT();
    }
}
