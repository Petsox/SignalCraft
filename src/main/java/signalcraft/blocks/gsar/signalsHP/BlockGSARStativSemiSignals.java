package signalcraft.blocks.gsar.signalsHP;

import signalcraft.proxy.CommonProxy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.entities.gsar.signalsHP.TileGSARStativSemiSignals;

public class BlockGSARStativSemiSignals extends BlockGSARStativ
{
    
    public BlockGSARStativSemiSignals(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }
    @Override
    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileGSARStativSemiSignals();
    }
}
