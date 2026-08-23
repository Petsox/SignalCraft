package signalcraft.blocks.gsar.signalsBU;

import signalcraft.proxy.CommonProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.levelCrossings.BlockLevelCrossing;
import signalcraft.entities.gsar.signalsBU.TileGSARFullBarriersx4L;
import signalcraft.entities.gsar.signalsBU.TileGSARFullBarriersx4R;


public class BlockGSARBarrierFull4R extends BlockLevelCrossing
{

    public BlockGSARBarrierFull4R(String name) {
        super(name);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }
    @Override
    public boolean openGui(World world, int x, int y, int z, EntityPlayer player) {
        return false;
    }

    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileGSARFullBarriersx4R();
    }
}
