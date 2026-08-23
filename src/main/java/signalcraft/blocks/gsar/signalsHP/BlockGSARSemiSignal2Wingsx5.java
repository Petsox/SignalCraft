package signalcraft.blocks.gsar.signalsHP;

import signalcraft.proxy.CommonProxy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.entities.gsar.signalsHP.TileGSARSemiSignal2Wingsx5;


public class BlockGSARSemiSignal2Wingsx5 extends BlockGSARSemiSignals
{
    public BlockGSARSemiSignal2Wingsx5(String name) {
        super(name);
        this.needsStativ = true;
        this.isSemiSignal = true;
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    @Override
    public boolean canPlaceBlockAt(final World world, final int x, final int y, final int z) {
        if (this.needsStativ) {
            return (world.getBlock(x, y - 1, z) instanceof BlockGSARStativSemiSignals);
        }
        return super.canPlaceBlockAt(world, x, y, z);
    }
    
    public TileEntity createNewTileEntity(final World world, final int metaData) {
        return new TileGSARSemiSignal2Wingsx5();
    }
}
