package signalcraft.blocks.levelCrossings.vud;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.levelCrossings.BlockLevelCrossing;
import signalcraft.entities.levelCrossings.vud.TileVUD;
import signalcraft.proxy.CommonProxy;
public class BlockVUD extends BlockLevelCrossing
{
    public BlockVUD(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabCrossings);
    }
    @Override
    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileVUD();
    }
}
