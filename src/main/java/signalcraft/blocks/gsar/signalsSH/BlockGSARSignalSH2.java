package signalcraft.blocks.gsar.signalsSH;

import signalcraft.proxy.CommonProxy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.signSignals.BlockSignSignal;
import signalcraft.entities.gsar.signalsSH.TileGSARSignSignalSH2;


public class BlockGSARSignalSH2 extends BlockSignSignal
{

    public BlockGSARSignalSH2(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileGSARSignSignalSH2();
    }
}
