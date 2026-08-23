package signalcraft.blocks.levelCrossings.azd;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.levelCrossings.BlockLevelCrossing;
import signalcraft.entities.levelCrossings.azd.TileAZD71Head;
import signalcraft.proxy.CommonProxy;

public class BlockAZD71Head extends BlockLevelCrossing {
    public BlockAZD71Head(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabCrossings);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileAZD71Head();
    }
}
