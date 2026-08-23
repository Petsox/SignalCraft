package signalcraft.blocks.levelCrossings.sssr;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.levelCrossings.BlockLevelCrossing;
import signalcraft.entities.levelCrossings.sssr.TileSSSRHead;
import signalcraft.proxy.CommonProxy;

public class BlockCrossSSSRHead extends BlockLevelCrossing {
    public BlockCrossSSSRHead(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabCrossings);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileSSSRHead();
    }
}
