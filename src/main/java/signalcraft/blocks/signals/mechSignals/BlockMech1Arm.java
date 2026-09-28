package signalcraft.blocks.signals.mechSignals;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.lightSignals.BlockLightSignal;
import signalcraft.entities.signals.mechSignals.TileMech1Arm;
import signalcraft.proxy.CommonProxy;

public class BlockMech1Arm extends BlockLightSignal {
    public BlockMech1Arm(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabSignals);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileMech1Arm();
    }
}
