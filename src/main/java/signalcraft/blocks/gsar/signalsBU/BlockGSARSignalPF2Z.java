package signalcraft.blocks.gsar.signalsBU;

import signalcraft.entities.gsar.signalsBU.TileGSARSignalPF2Z;
import signalcraft.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.signSignals.BlockSignSignal;
import signalcraft.entities.gsar.signalsBU.TileGSARSignalBU4Z;


public class BlockGSARSignalPF2Z extends BlockSignSignal
{

    public BlockGSARSignalPF2Z(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }
    @Override
    public boolean openGui(World world, int x, int y, int z, EntityPlayer player) {
        return false;
    }

    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileGSARSignalPF2Z();
    }
}
