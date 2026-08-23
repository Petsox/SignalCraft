package signalcraft.entities.gsar.signalsSO;

import net.minecraft.nbt.NBTTagCompound;
import signalcraft.entities.signals.signSignals.TileSignSignal;
import signalcraft.signalUtils.Consts;

public class TileGSARStationSign extends TileSignSignal
{
    public TileGSARStationSign() {
        this.setGuiId(Consts.GuiIDs.STATION_SIGN);
        this.setHasSH2Lamp(false);
        this.setIsActive(false);
        this.setShowsTextSide(false);
        this.setFontStyleList(0);
        this.setSignTextField("");
        this.setSignTextColor(-2039584);
        this.setModelButtonStatus(0);
    }
    
    public void updateEntity() {
        if (this.worldObj.isDaytime() && this.getIsActive()) {
            if (!this.worldObj.isRemote) {
                this.setHasSH2Lamp(false);
            }
        }
        else if (!this.worldObj.isDaytime() && this.getIsActive() && !this.worldObj.isRemote) {
            this.setHasSH2Lamp(true);
        }
    }

    public void readFromNBT(final NBTTagCompound NBTTC) {
        super.readFromNBT(NBTTC);
        this.setModelButtonStatus(NBTTC.getInteger("modelButtonStatus"));
        this.setSignTextField(NBTTC.getString("signTextField"));
        this.setSignTextColor(NBTTC.getInteger("signTextColor"));
        this.setShowsTextSide(NBTTC.getBoolean("showsTextSide"));
        this.setFontStyleList(NBTTC.getInteger("fontStyleList"));
    }
    
    public void writeToNBT(final NBTTagCompound NBTTC) {
        super.writeToNBT(NBTTC);
        NBTTC.setInteger("modelButtonStatus", this.getModelButtonStatus());
        NBTTC.setString("signTextField", this.getSignTextField());
        NBTTC.setInteger("signTextColor", this.getSignTextColor());
        NBTTC.setBoolean("showsTextSide", this.getShowsTextSide());
        NBTTC.setInteger("fontStyleList", this.getFontStyleList());
    }
}
