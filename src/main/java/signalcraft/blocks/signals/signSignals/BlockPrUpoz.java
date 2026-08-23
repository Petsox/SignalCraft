package signalcraft.blocks.signals.signSignals;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.entities.signals.signSignals.TilePrUpoz;
import signalcraft.proxy.CommonProxy;

public class BlockPrUpoz extends BlockSignSignal {
    public BlockPrUpoz(String name) {
        super(name);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        this.setCreativeTab(CommonProxy.tabOther);
    }
    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TilePrUpoz();
    }
}
