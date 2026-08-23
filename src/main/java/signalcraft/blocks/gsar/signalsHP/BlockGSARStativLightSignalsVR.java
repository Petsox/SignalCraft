package signalcraft.blocks.gsar.signalsHP;

import signalcraft.proxy.CommonProxy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.entities.gsar.signalsHP.TileGSARStativLightSignalsVR;

public class BlockGSARStativLightSignalsVR extends BlockGSARStativ
{
    public BlockGSARStativLightSignalsVR(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileGSARStativLightSignalsVR();
    }
}
