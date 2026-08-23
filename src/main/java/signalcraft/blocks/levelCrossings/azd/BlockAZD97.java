package signalcraft.blocks.levelCrossings.azd;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.levelCrossings.BlockLevelCrossing;
import signalcraft.entities.levelCrossings.azd.TileAZD71;
import signalcraft.entities.levelCrossings.azd.TileAZD97;
import signalcraft.proxy.CommonProxy;

public class BlockAZD97 extends BlockLevelCrossing {
    public BlockAZD97(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabCrossings);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileAZD97();
    }
}
