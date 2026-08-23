package signalcraft.blocks.signals.lightSignals.azd65;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.lightSignals.BlockLightSignal;
import signalcraft.entities.signals.lightSignals.azd70.TileAZD4Lights;
import signalcraft.proxy.CommonProxy;

public class BlockAZD654Lights extends BlockLightSignal {
    public BlockAZD654Lights(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabSignals);
    }
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileAZD4Lights();
    }
}
