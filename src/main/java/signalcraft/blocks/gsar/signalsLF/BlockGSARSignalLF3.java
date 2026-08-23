package signalcraft.blocks.gsar.signalsLF;

import signalcraft.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.signSignals.BlockSignSignal;
import signalcraft.entities.gsar.signalsLF.TileGSARSignalLF3;


public class BlockGSARSignalLF3 extends BlockSignSignal
{

    public BlockGSARSignalLF3(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    @Override
    public boolean openGui(World world, int x, int y, int z, EntityPlayer player) {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileGSARSignalLF3();
    }
}
