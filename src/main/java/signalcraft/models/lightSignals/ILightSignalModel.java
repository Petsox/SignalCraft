package signalcraft.models.lightSignals;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.signalUtils.SignalState;

public interface ILightSignalModel extends IModelCustom {
    @SideOnly(Side.CLIENT)
    void renderStoz(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText, String PNLight);
    @SideOnly(Side.CLIENT)
    void renderStozNater(Boolean hasStripes, Boolean has3Stripes, String Pos , String SpeedSignText);
    @SideOnly(Side.CLIENT)
    void renderStozVjNater(Boolean hasStripes, Boolean has3Stripes, String SpeedSignText);
    @SideOnly(Side.CLIENT)
    void renderSkupinove(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight);
    @SideOnly(Side.CLIENT)
    void renderStit(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight);
    @SideOnly(Side.CLIENT)
    void renderSpeed(String SpeedSignText, String Pos);
    @SideOnly(Side.CLIENT)
    void renderNavest(SignalState SigState, TileLightSignal tileSignal, String Pos, String PNLight);
}
