package signalcraft.blocks.signals.mechSignals;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.lightSignals.BlockLightSignal;
import signalcraft.entities.signals.mechSignals.TileMech2Arms;
import signalcraft.proxy.CommonProxy;

public class BlockMech2Arms extends BlockLightSignal {
    public BlockMech2Arms(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabSignals);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileMech2Arms();
    }
}
