package signalcraft.blocks.gsar.signalsLF;

import signalcraft.proxy.CommonProxy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.signSignals.BlockSignSignal;
import signalcraft.entities.gsar.signalsLF.TileGSARSignalLF1;


public class BlockGSARSignalLF1 extends BlockSignSignal {

    public BlockGSARSignalLF1(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    @Override
    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileGSARSignalLF1();
    }
}
