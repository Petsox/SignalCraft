package signalcraft.blocks.switches.cz;

import signalcraft.items.ItemScaler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.entities.switches.cz.TileSwitchManualCZ;

/** Hand-operated switch stand: right click throws the switch. */
public class BlockSwitchManualCZ extends BlockSwitchCZ {

    public BlockSwitchManualCZ(String name) {
        super(name, 1.0f);
    }

    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileSwitchManualCZ();
    }

    @Override
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer entityPlayer, final int meta, final float hitX, final float hitY, final float hitZ) {
        if (ItemScaler.isHeldBy(entityPlayer)) {
            return false;               // let the Scaler scale it instead of throwing it
        }
        if (world.isRemote) {
            return true;
        }
        final TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileSwitchManualCZ) {
            final TileSwitchManualCZ tileE = (TileSwitchManualCZ) tileEntity;
            tileE.setIsActive(!tileE.getIsSwitched());
        }
        return true;
    }
}
