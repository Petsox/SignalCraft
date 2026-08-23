package signalcraft.blocks.gsar.signalsRA;

import signalcraft.proxy.CommonProxy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.gsar.signalsHP.BlockGSARStativ;
import signalcraft.entities.gsar.signalsRA.TileGSARStativRA11;


public class BlockGSARStativRA11 extends BlockGSARStativ {

    public BlockGSARStativRA11(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return true;
    }

    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileGSARStativRA11();
    }
}
