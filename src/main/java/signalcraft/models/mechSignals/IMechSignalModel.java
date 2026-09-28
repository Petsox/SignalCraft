package signalcraft.models.mechSignals;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import signalcraft.entities.signals.mechSignals.TileMechSignal;

public interface IMechSignalModel {
    @SideOnly(Side.CLIENT)
    void renderStoz(TileMechSignal tileSignal);
    @SideOnly(Side.CLIENT)
    void renderRamena(TileMechSignal tileSignal);
    @SideOnly(Side.CLIENT)
    void renderItem(TileMechSignal tileSignal);
}
