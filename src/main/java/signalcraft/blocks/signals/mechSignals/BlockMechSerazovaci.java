package signalcraft.blocks.signals.mechSignals;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.lightSignals.BlockLightSignal;
import signalcraft.entities.signals.mechSignals.TileMechSerazovaci;
import signalcraft.proxy.CommonProxy;

public class BlockMechSerazovaci extends BlockLightSignal {
    public BlockMechSerazovaci(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabSignals);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileMechSerazovaci();
    }
}
