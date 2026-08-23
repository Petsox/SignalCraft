package signalcraft.blocks.gsar.signalsSH;

import signalcraft.proxy.CommonProxy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.signSignals.BlockSignSignal;
import signalcraft.entities.gsar.signalsSH.TileGSARSemiSignalSHFSingle;


public class BlockGSARSignalSHFSingle extends BlockSignSignal
{

    public BlockGSARSignalSHFSingle(String name) {
        super(name);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileGSARSemiSignalSHFSingle();
    }
}
