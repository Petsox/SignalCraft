package signalcraft.blocks.signals.mechSignals;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.lightSignals.BlockLightSignal;
import signalcraft.entities.signals.mechSignals.TileMechPredvest;
import signalcraft.proxy.CommonProxy;

public class BlockMechPredvest extends BlockLightSignal {
    public BlockMechPredvest(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabSignals);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileMechPredvest();
    }
}
