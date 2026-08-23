package signalcraft.models.gsar;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.gsar.signalsHP.TileGSARLightSignal;
import signalcraft.signalUtils.SignalState;

public interface ILightSignalModelGSAR extends IModelCustom {
    @SideOnly(Side.CLIENT)
    void renderStoz(TileGSARLightSignal tileSignal);
    @SideOnly(Side.CLIENT)
    void renderNavestidlo(TileGSARLightSignal tileSignal);
    @SideOnly(Side.CLIENT)
    void renderNavest(SignalState SigState, TileGSARLightSignal tileSignal);
}
