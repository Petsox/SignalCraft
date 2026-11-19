package signalcraft.entities.switches.cz;

import net.minecraft.nbt.NBTTagCompound;
import signalcraft.signalUtils.Consts;

/**
 * Electric point machine AZD EP 600 (grey, yellow-framed lid) or PointSWing EP-6xx (yellow lid), with the terminal
 * box and optionally a switch stand with a yellow weight (for throwing the switch by hand). Thrown remotely like the
 * GSAR electric switch.
 */
public class TileSwitchElectricCZ extends TileSwitchCZ {
    private static final String MOTOR_SOUND = "signalcraft:motor_sound";
    private boolean ep6xx = false;
    private boolean stand = false;

    public TileSwitchElectricCZ() {
        this.setGuiId(Consts.GuiIDs.SWITCH_ELECTRIC_CZ);
        this.setSignal(SIGNAL_NONE);
    }

    @Override
    protected int travelTicks() {
        return 70;
    }

    @Override
    protected void onStartMoving() {
        this.worldObj.playSoundEffect(this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5, MOTOR_SOUND, 1.0f, 1.0f);
    }

    public boolean isEp6xx() {
        return this.ep6xx;
    }

    public void setEp6xx(boolean ep6xx) {
        this.ep6xx = ep6xx;
    }

    public boolean hasStand() {
        return this.stand;
    }

    public void setStand(boolean stand) {
        this.stand = stand;
    }

    @Override
    public void readFromNBT(final NBTTagCompound NBTTC) {
        super.readFromNBT(NBTTC);
        if (!NBTTC.hasKey("czSignal")) {
            this.setSignal(SIGNAL_NONE);
        }
        this.ep6xx = NBTTC.getBoolean("czEp6xx");
        this.stand = NBTTC.getBoolean("czStand");
    }

    @Override
    public void writeToNBT(final NBTTagCompound NBTTC) {
        super.writeToNBT(NBTTC);
        NBTTC.setBoolean("czEp6xx", this.ep6xx);
        NBTTC.setBoolean("czStand", this.stand);
    }
}
