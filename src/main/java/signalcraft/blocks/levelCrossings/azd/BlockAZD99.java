package signalcraft.blocks.levelCrossings.azd;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.levelCrossings.BlockLevelCrossing;
import signalcraft.entities.levelCrossings.azd.TileAZD99;
import signalcraft.proxy.CommonProxy;

public class BlockAZD99 extends BlockLevelCrossing {
    public BlockAZD99(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabCrossings);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);

    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileAZD99();
    }
}
