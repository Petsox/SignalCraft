package signalcraft.network;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;

public abstract class ClientPacket extends Packet
{
    protected abstract void handleServerSide(final NetHandlerPlayServerSide p0);

    public int getXCoordinate() {
        return 0;
    }

    public int getYCoordinate() {
        return 0;
    }

    public int getZCoordinate() {
        return 0;
    }

    public NBTTagCompound getMainNBTTC() {
        return new NBTTagCompound();
    }

}
