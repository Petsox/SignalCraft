package signalcraft.blocks.gsar.signalsHP;

import signalcraft.proxy.CommonProxy;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.entities.gsar.signalsHP.TileGSARStativLightSignals;

public class BlockGSARStativLightSignals extends BlockGSARStativ
{

    public BlockGSARStativLightSignals(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    
    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileGSARStativLightSignals();
    }
}
