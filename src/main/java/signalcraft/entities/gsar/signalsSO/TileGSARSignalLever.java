package signalcraft.entities.gsar.signalsSO;

import net.minecraft.nbt.NBTTagCompound;
import signalcraft.SignalCraft;
import signalcraft.entities.IActivatable;
import signalcraft.entities.signals.signSignals.TileSignSignal;
import signalcraft.messages.MessageActiveUpdate;
import signalcraft.signalUtils.Consts;

public class TileGSARSignalLever extends TileSignSignal implements IActivatable {

    public TileGSARSignalLever() {
        super();
        this.setGuiId(Consts.GuiIDs.SIGNAL_LEVER);
        this.setLeverTexture(0);
        this.setSignID("0");
    }

    public void updateEntity() {
        if (this.getIsActive() && this.getRotate() < 40) {
            setRotate(getRotate() + 1);
        } else if (!this.getIsActive() && this.getRotate() > 0) {
            setRotate(getRotate() - 1);
        }
    }

    @Override
    public void setIsActive(Boolean activate) {
        if (this.getIsActive() != activate) {
            if (!this.getIsActive()) {
                this.worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, "signalcraft:lever_sound2", 0.1f, 1.0f);
                SignalCraft.SCNet.sendToAll(new MessageActiveUpdate(this.xCoord, this.yCoord, this.zCoord, true));
            } else {
                this.worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, "signalcraft:lever_sound1", 0.1f, 1.0f);
                SignalCraft.SCNet.sendToAll(new MessageActiveUpdate(this.xCoord, this.yCoord, this.zCoord, false));
            }
        }
        super.setIsActive(activate);
    }

    @Override
    public void writeToNBT(final NBTTagCompound NBTTC) {
        super.writeToNBT(NBTTC);
        NBTTC.setString("leverTexture", String.valueOf(this.getLeverTexture()));
    }

    @Override
    public void readFromNBT(final NBTTagCompound NBTTC) {
        super.readFromNBT(NBTTC);
        this.setLeverTexture(Integer.parseInt(NBTTC.getString("leverTexture")));
    }

    @Override
    public void setBlinkCounter(int counter) {
    }
}
