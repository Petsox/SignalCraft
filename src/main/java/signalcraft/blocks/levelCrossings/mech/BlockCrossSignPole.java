package signalcraft.blocks.levelCrossings.mech;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.signals.signSignals.BlockSignSignal;
import signalcraft.entities.levelCrossings.mech.TileCrossSignPole;
import signalcraft.proxy.CommonProxy;

/** Stand-alone round pole with the "POZOR VLAK" sign and the warning cross. */
public class BlockCrossSignPole extends BlockSignSignal {
    public BlockCrossSignPole(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabCrossings);
    }

    @Override
    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileCrossSignPole();
    }
}
