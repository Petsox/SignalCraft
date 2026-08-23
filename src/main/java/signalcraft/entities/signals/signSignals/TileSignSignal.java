package signalcraft.entities.signals.signSignals;

import net.minecraft.nbt.NBTTagCompound;
import signalcraft.entities.TileSignal;
import signalcraft.signalUtils.Consts;

public class TileSignSignal extends TileSignal {
    /**
     * String[] signSignalProperties
     * [0] - Speed signal text
     * [1] - Sign ID
     * [2] - X Adjust
     * [3] - Y Adjust
     * [4] - Scale Adjust
     * [5] - Rotate
     * [6] - Station label stativ
     * [7] - Signal label stativ
     * [8] - Is Active
     * [9] - SH2 Lamp
     * [10] - SH2 Stativ
     * [11] - Lever Texture
     * [12] - Is Text on both sides
     * [13] - Font Style
     * [14] - Model Button Status
     * [15] - Sign Text Field
     * [16] - Sign Text Color
     */

    private final String[] signSignalProperties;

    public TileSignSignal() {
        this.signSignalProperties = new String[Consts.signPropArrLenght];
        this.setSpeedSignalText("");
        this.setSignID(this.getClass().getSimpleName());
        this.setXAdjust(0f);
        this.setYAdjust(0f);
        this.setScaleAdjust(0f);
        this.setRotate(0);
        this.setStationLabelStativ("");
        this.setSignalLabelStativ("");
        this.setIsActive(false);
    }

    public String getSpeedSignalText() {
        return this.signSignalProperties[0];
    }

    public String getSignID() {
        return this.signSignalProperties[1];
    }

    public float getXAdjust() {
        return Float.parseFloat(this.signSignalProperties[2]);
    }

    public float getYAdjust() {
        return Float.parseFloat(this.signSignalProperties[3]);
    }

    public float getScaleAdjust() {
        return Float.parseFloat(this.signSignalProperties[4]);
    }

    public int getRotate() {
        return Integer.parseInt(this.signSignalProperties[5]);
    }

    public String getStationLabelStativ() {
        return this.signSignalProperties[6];
    }

    public String getSignalLabelStativ() {
        return this.signSignalProperties[7];
    }

    public Boolean getIsActive() {
        return Boolean.parseBoolean(this.signSignalProperties[8]);
    }

    public boolean getHasSH2Lamp() {
        return Boolean.parseBoolean(this.signSignalProperties[9]);
    }

    public boolean getHasSH2Stativ() {
        return Boolean.parseBoolean(this.signSignalProperties[10]);
    }

    public int getLeverTexture() {
        return Integer.parseInt(this.signSignalProperties[11]);
    }

    public boolean getShowsTextSide() {
        return Boolean.parseBoolean(this.signSignalProperties[12]);
    }

    public int getFontStyleList() {
        return Integer.parseInt(this.signSignalProperties[13]);
    }

    public int getModelButtonStatus() {
        return Integer.parseInt(this.signSignalProperties[14]);
    }

    public String getSignTextField() {
        return this.signSignalProperties[15];
    }

    public int getSignTextColor() {
        return Integer.parseInt(this.signSignalProperties[16]);
    }

    public void setSpeedSignalText(final String speedSignalText) {
        this.signSignalProperties[0] = speedSignalText;
    }

    public void setSignID(final String ID) {
        this.signSignalProperties[1] = ID;
    }

    public void setXAdjust(final float XAdjust) {
        this.signSignalProperties[2] = String.valueOf(XAdjust);
    }

    public void setYAdjust(final float YAdjust) {
        this.signSignalProperties[3] = String.valueOf(YAdjust);
    }

    public void setScaleAdjust(final float scaleAdjust) {
        this.signSignalProperties[4] = String.valueOf(scaleAdjust);
    }

    public void setRotate(final int rotate) {
        this.signSignalProperties[5] = String.valueOf(rotate);
    }

    public void setStationLabelStativ(final String stationLabelStativ) {
        this.signSignalProperties[6] = stationLabelStativ;
    }

    public void setSignalLabelStativ(final String signalLabelStativ) {
        this.signSignalProperties[7] = signalLabelStativ;
    }

    public void setIsActive(Boolean active) {
        this.signSignalProperties[8] = active.toString();
    }

    public void setHasSH2Lamp(Boolean hasSH2Lamp) {
        this.signSignalProperties[9] = hasSH2Lamp.toString();
    }

    public void setHasSH2Stativ(Boolean hasSH2Stativ) {
        this.signSignalProperties[10] = hasSH2Stativ.toString();
    }

    public void setLeverTexture(int leverTexture) {
        this.signSignalProperties[11] = String.valueOf(leverTexture);
    }

    public void setShowsTextSide(Boolean showsTextSide) {
        this.signSignalProperties[12] = showsTextSide.toString();
    }

    public void setFontStyleList(int fontStyleList) {
        this.signSignalProperties[13] = String.valueOf(fontStyleList);
    }

    public void setModelButtonStatus(int modelButtonStatus) {
        this.signSignalProperties[14] = String.valueOf(modelButtonStatus);
    }

    public void setSignTextField(String signTextField) {
        this.signSignalProperties[15] = signTextField;
    }

    public void setSignTextColor(int signTextColor) {
        this.signSignalProperties[16] = String.valueOf(signTextColor);
    }

    @Override
    public void readFromNBT(final NBTTagCompound NBTTC) {
        super.readFromNBT(NBTTC);
        this.signSignalProperties[0] = NBTTC.getString("speedSignalText");
        this.signSignalProperties[1] = NBTTC.getString("SignID");
        this.signSignalProperties[2] = NBTTC.getString("XAdjust");
        this.signSignalProperties[3] = NBTTC.getString("YAdjust");
        this.signSignalProperties[4] = NBTTC.getString("scaleAdjust");
        this.signSignalProperties[5] = NBTTC.getString("rotate");
        this.signSignalProperties[6] = NBTTC.getString("stationLabelStativ");
        this.signSignalProperties[7] = NBTTC.getString("signalLabelStativ");
        this.signSignalProperties[8] = NBTTC.getString("isActive");
        this.signSignalProperties[9] = NBTTC.getString("hasSH2Lamp");
        this.signSignalProperties[10] = NBTTC.getString("hasSH2Stativ");
        this.signSignalProperties[11] = NBTTC.getString("leverTexture");
        this.signSignalProperties[12] = NBTTC.getString("showsTextSide");
        this.signSignalProperties[13] = NBTTC.getString("fontStyleList");
        this.signSignalProperties[14] = NBTTC.getString("modelButtonStatus");
        this.signSignalProperties[15] = NBTTC.getString("signTextField");
        this.signSignalProperties[16] = NBTTC.getString("signTextColor");
    }

    @Override
    public void writeToNBT(final NBTTagCompound NBTTC) {
        super.writeToNBT(NBTTC);
        NBTTC.setString("speedSignalText", this.signSignalProperties[0]);
        NBTTC.setString("SignID", this.signSignalProperties[1]);
        NBTTC.setString("XAdjust", this.signSignalProperties[2]);
        NBTTC.setString("YAdjust", this.signSignalProperties[3]);
        NBTTC.setString("scaleAdjust", this.signSignalProperties[4]);
        NBTTC.setString("rotate", this.signSignalProperties[5]);
        NBTTC.setString("stationLabelStativ", this.signSignalProperties[6]);
        NBTTC.setString("signalLabelStativ", this.signSignalProperties[7]);
        NBTTC.setString("isActive", this.signSignalProperties[8]);
    }
}
