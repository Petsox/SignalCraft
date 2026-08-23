package signalcraft.blocks.signals.lightSignals.azd65;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.lightSignals.BlockLightSignal;
import signalcraft.entities.signals.lightSignals.azd70.TileAZDPosun;
import signalcraft.proxy.CommonProxy;

public class BlockAZD65Posun extends BlockLightSignal
{
    public BlockAZD65Posun(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabSignals);
    }
    @Override
    public TileEntity createNewTileEntity(final World world, final int metaData) {
        return new TileAZDPosun();
    }
}
