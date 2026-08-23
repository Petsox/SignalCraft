package signalcraft.models.gsar;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.gsar.signalsHP.TileGSARSemiSignal;
import signalcraft.signalUtils.SignalState;

public interface ISemiSignalModelGSAR extends IModelCustom {
    @SideOnly(Side.CLIENT)
    void renderStoz(TileGSARSemiSignal tileSignal);
    @SideOnly(Side.CLIENT)
    void renderNavestidlo(TileGSARSemiSignal tileSignal);
    @SideOnly(Side.CLIENT)
    void renderRamena(TileGSARSemiSignal tileSignal);
    @SideOnly(Side.CLIENT)
    void renderKabel(TileGSARSemiSignal tileSignal);
    @SideOnly(Side.CLIENT)
    void renderNavest(SignalState SigState, TileGSARSemiSignal tileSignal);
    @SideOnly(Side.CLIENT)
    void renderItem(TileGSARSemiSignal tileSignal);

}
