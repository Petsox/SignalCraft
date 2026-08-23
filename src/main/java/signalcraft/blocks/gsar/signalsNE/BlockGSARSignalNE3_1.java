package signalcraft.blocks.gsar.signalsNE;

import signalcraft.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.signSignals.BlockSignSignal;
import signalcraft.entities.gsar.signalsNE.TileGSARSignalNE3_1;

public class BlockGSARSignalNE3_1 extends BlockSignSignal
{

    public BlockGSARSignalNE3_1(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }
    @Override
    public boolean openGui(World world, int x, int y, int z, EntityPlayer player) {
        return false;
    }


    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileGSARSignalNE3_1();
    }
}
