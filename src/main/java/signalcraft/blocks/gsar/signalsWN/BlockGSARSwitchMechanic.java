package signalcraft.blocks.gsar.signalsWN;

import signalcraft.proxy.CommonProxy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.blocks.switches.BlockSwitch;
import signalcraft.entities.gsar.signalsWN.TileSwitchElectricGSAR;

public class BlockGSARSwitchMechanic extends BlockSwitch {

    public BlockGSARSwitchMechanic(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileSwitchElectricGSAR();
    }
}
