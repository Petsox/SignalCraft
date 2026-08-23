package signalcraft.models.gsar;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.gsar.signalsHP.TileGSARStativ;

public interface IStativModelGSAR extends IModelCustom {
    @SideOnly(Side.CLIENT)
    void renderStativ(TileGSARStativ tileSignal);
}
