package signalcraft.blocks.switches.cz;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.entities.switches.cz.TileSwitchElectricCZ;

/** Electric point machine: thrown remotely (controllers, like the GSAR electric switch). */
public class BlockSwitchElectricCZ extends BlockSwitchCZ {

    public BlockSwitchElectricCZ(String name) {
        super(name, 0.4f);
    }

    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileSwitchElectricCZ();
    }
}
