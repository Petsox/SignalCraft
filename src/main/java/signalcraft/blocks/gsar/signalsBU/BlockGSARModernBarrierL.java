package signalcraft.blocks.gsar.signalsBU;

import signalcraft.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.levelCrossings.BlockLevelCrossing;
import signalcraft.entities.gsar.signalsBU.TileGSARModernBarrierL;


public class BlockGSARModernBarrierL extends BlockLevelCrossing
{

    public BlockGSARModernBarrierL(String name) {
        super(name);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileGSARModernBarrierL();
    }
}
