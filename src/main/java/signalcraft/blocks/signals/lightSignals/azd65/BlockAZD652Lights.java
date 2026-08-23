package signalcraft.blocks.signals.lightSignals.azd65;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.lightSignals.BlockLightSignal;
import signalcraft.entities.signals.lightSignals.azd65.TileAZD652Lights;
import signalcraft.proxy.CommonProxy;

public class BlockAZD652Lights extends BlockLightSignal {
    public BlockAZD652Lights(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabSignals);
    }
    @Override
    public boolean renderAsNormalBlock() {
        return true;
    }
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileAZD652Lights();
    }
}
