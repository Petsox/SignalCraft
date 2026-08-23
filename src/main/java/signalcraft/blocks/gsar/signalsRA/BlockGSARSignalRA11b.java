package signalcraft.blocks.gsar.signalsRA;

import signalcraft.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.signSignals.BlockSignSignal;
import signalcraft.entities.gsar.signalsRA.TileGSARSignalRA11b;


public class BlockGSARSignalRA11b extends BlockSignSignal
{

    public BlockGSARSignalRA11b(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }
    @Override
    public boolean openGui(World world, int x, int y, int z, EntityPlayer player) {
        return false;
    }

    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileGSARSignalRA11b();
    }
}
