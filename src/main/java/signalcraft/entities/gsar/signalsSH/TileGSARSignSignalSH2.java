package signalcraft.entities.gsar.signalsSH;

import net.minecraft.nbt.NBTTagCompound;
import signalcraft.entities.signals.signSignals.TileSignSignal;
import signalcraft.signalUtils.Consts;

public class TileGSARSignSignalSH2 extends TileSignSignal {

    public TileGSARSignSignalSH2() {
        this.setGuiId(Consts.GuiIDs.SIGNAL_SH2);
    }

    @Override
    public void writeToNBT(final NBTTagCompound NBTTC) {
        super.writeToNBT(NBTTC);
        NBTTC.setString("hasSH2Lamp", String.valueOf(this.getHasSH2Lamp()));
        NBTTC.setString("hasSH2Stativ", String.valueOf(this.getHasSH2Stativ()));
    }

    @Override
    public void readFromNBT(final NBTTagCompound NBTTC) {
        super.readFromNBT(NBTTC);
        this.setHasSH2Lamp(Boolean.valueOf(NBTTC.getString("hasSH2Lamp")));
        this.setHasSH2Stativ(Boolean.valueOf(NBTTC.getString("hasSH2Stativ")));
    }
}
