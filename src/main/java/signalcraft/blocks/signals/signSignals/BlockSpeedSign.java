package signalcraft.blocks.signals.signSignals;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.entities.signals.signSignals.TileSpeedSign;
import signalcraft.proxy.CommonProxy;

public class BlockSpeedSign extends BlockSignSignal {
    public BlockSpeedSign(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabOther);
    }
    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileSpeedSign();
    }
}
