package signalcraft.entities.levelCrossings.mech;

import net.minecraft.nbt.NBTTagCompound;
import signalcraft.entities.signals.signSignals.TileSignSignal;
import signalcraft.signalUtils.Consts;

/**
 * Stand-alone round pole with the "POZOR VLAK" sign and the warning cross - the same pole as on the mechanical
 * barrier (ModelCrossSignPole), placed on its own.
 */
public class TileCrossSignPole extends TileSignSignal {
    private boolean sign = true;
    private boolean cross = true;
    private boolean crossOneTrack = false;
    private boolean crossReflective = false;
    private boolean crossLarge = false;
    private boolean crossSlovak = false;
    private boolean stop = false;
    private boolean stripes = false;

    public TileCrossSignPole() {
        this.setGuiId(Consts.GuiIDs.CROSS_SIGN_POLE);
    }

    public boolean hasSign() {
        return this.sign;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
    }

    public boolean hasCross() {
        return this.cross;
    }

    public void setCross(boolean cross) {
        this.cross = cross;
    }

    public boolean isCrossOneTrack() {
        return this.crossOneTrack;
    }

    public void setCrossOneTrack(boolean crossOneTrack) {
        this.crossOneTrack = crossOneTrack;
    }

    public boolean isCrossReflective() {
        return this.crossReflective;
    }

    public void setCrossReflective(boolean crossReflective) {
        this.crossReflective = crossReflective;
    }

    public boolean isCrossLarge() {
        return this.crossLarge;
    }

    public void setCrossLarge(boolean crossLarge) {
        this.crossLarge = crossLarge;
    }

    public boolean isCrossSlovak() {
        return this.crossSlovak;
    }

    public void setCrossSlovak(boolean crossSlovak) {
        this.crossSlovak = crossSlovak;
    }

    public boolean hasStop() {
        return this.stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public boolean hasStripes() {
        return this.stripes;
    }

    public void setStripes(boolean stripes) {
        this.stripes = stripes;
    }

    @Override
    public void readFromNBT(final NBTTagCompound NBTTC) {
        super.readFromNBT(NBTTC);
        if (NBTTC.hasKey("poleSign")) {
            this.sign = NBTTC.getBoolean("poleSign");
            this.cross = NBTTC.getBoolean("poleCross");
            this.crossOneTrack = NBTTC.getBoolean("poleCrossOneTrack");
            this.crossReflective = NBTTC.getBoolean("poleCrossReflective");
            this.crossLarge = NBTTC.getBoolean("poleCrossLarge");
            this.crossSlovak = NBTTC.getBoolean("poleCrossSlovak");
            this.stop = NBTTC.getBoolean("poleStop");
            this.stripes = NBTTC.getBoolean("poleStripes");
        }
    }

    @Override
    public void writeToNBT(final NBTTagCompound NBTTC) {
        super.writeToNBT(NBTTC);
        NBTTC.setBoolean("poleSign", this.sign);
        NBTTC.setBoolean("poleCross", this.cross);
        NBTTC.setBoolean("poleCrossOneTrack", this.crossOneTrack);
        NBTTC.setBoolean("poleCrossReflective", this.crossReflective);
        NBTTC.setBoolean("poleCrossLarge", this.crossLarge);
        NBTTC.setBoolean("poleCrossSlovak", this.crossSlovak);
        NBTTC.setBoolean("poleStop", this.stop);
        NBTTC.setBoolean("poleStripes", this.stripes);
    }
}
