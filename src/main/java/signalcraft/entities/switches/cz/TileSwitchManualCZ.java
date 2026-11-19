package signalcraft.entities.switches.cz;

import net.minecraft.nbt.NBTTagCompound;
import signalcraft.entities.switches.IManual;
import signalcraft.signalUtils.Consts;

/**
 * Hand-operated switch stand (vymenik) with a lever and weight and the switch signal. The weight is black and white
 * (operated on the spot) or yellow (centrally operated / with an electric lock).
 */
public class TileSwitchManualCZ extends TileSwitchCZ implements IManual {
    private boolean yellowWeight = false;

    public TileSwitchManualCZ() {
        this.setGuiId(Consts.GuiIDs.SWITCH_MANUAL_CZ);
    }

    @Override
    protected int travelTicks() {
        return 45;
    }

    public boolean isYellowWeight() {
        return this.yellowWeight;
    }

    public void setYellowWeight(boolean yellowWeight) {
        this.yellowWeight = yellowWeight;
    }

    @Override
    public void readFromNBT(final NBTTagCompound NBTTC) {
        super.readFromNBT(NBTTC);
        this.yellowWeight = NBTTC.getBoolean("czYellowWeight");
    }

    @Override
    public void writeToNBT(final NBTTagCompound NBTTC) {
        super.writeToNBT(NBTTC);
        NBTTC.setBoolean("czYellowWeight", this.yellowWeight);
    }
}
