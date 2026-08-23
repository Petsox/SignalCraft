package signalcraft.blocks.signals.lightSignals.sssr;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.lightSignals.BlockLightSignal;
import signalcraft.entities.signals.lightSignals.sssr.TileSSSR4Lights;
import signalcraft.proxy.CommonProxy;

public class BlockSSSR4Lights extends BlockLightSignal {
    public BlockSSSR4Lights(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabSignals);
    }
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileSSSR4Lights();
    }
}
