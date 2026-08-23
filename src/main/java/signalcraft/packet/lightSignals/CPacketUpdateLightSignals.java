package signalcraft.packet.lightSignals;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import signalcraft.network.ClientPacket;
import signalcraft.network.NetHandlerPlayServerSide;
import signalcraft.signalUtils.Consts;

import java.io.IOException;

public class CPacketUpdateLightSignals extends ClientPacket {
    private NBTTagCompound mainNBTTC;

    /**
     * String[] lightSignalProperties - Lenght in Consts.java
     * [0] - State
     * [1] - Position
     * [2] - HasStripes
     * [3] - Has3Stripes
     * [4] - SpeedSignText
     * [5] - IsDeparture
     * [6] - IsGroupped
     * [7] - Type
     * [8] - Signal Name
     * [9] - Signal Render Scale
     * [10] - HasPNLight
     */
    private final String[] lightSignalProperties;
    private int xCoordinate;
    private int yCoordinate;
    private int zCoordinate;

    public CPacketUpdateLightSignals() {
        this.lightSignalProperties = new String[Consts.lightPropArrLenght];
    }

    @SideOnly(Side.CLIENT)
    public CPacketUpdateLightSignals(final TileEntity tileEntity) {
        this.lightSignalProperties = new String[Consts.lightPropArrLenght];
        tileEntity.writeToNBT(this.mainNBTTC = new NBTTagCompound());
    }

    @Override
    public int getXCoordinate() {
        return this.xCoordinate;
    }

    @Override
    public int getYCoordinate() {
        return this.yCoordinate;
    }

    @Override
    public int getZCoordinate() {
        return this.zCoordinate;
    }

    @Override
    public NBTTagCompound getMainNBTTC() {
        return mainNBTTC;
    }

    protected void handleServerSide(final NetHandlerPlayServerSide netHandler) {
        netHandler.handleUpdateServer(this);
    }

    public void readPacketData(final PacketBuffer buffer) throws IOException {
        this.mainNBTTC = buffer.readNBTTagCompoundFromBuffer();
        this.xCoordinate = this.mainNBTTC.getInteger("x");
        this.yCoordinate = this.mainNBTTC.getInteger("y");
        this.zCoordinate = this.mainNBTTC.getInteger("z");
        this.lightSignalProperties[0] = this.mainNBTTC.getString("State");
        this.lightSignalProperties[1] = this.mainNBTTC.getString("Position");
        this.lightSignalProperties[2] = this.mainNBTTC.getString("HasStripes");
        this.lightSignalProperties[3] = this.mainNBTTC.getString("Has3Stripes");
        this.lightSignalProperties[4] = this.mainNBTTC.getString("SpeedSignText");
        this.lightSignalProperties[5] = this.mainNBTTC.getString("IsDeparture");
        this.lightSignalProperties[6] = this.mainNBTTC.getString("IsGroupped");
        this.lightSignalProperties[7] = this.mainNBTTC.getString("Type");
        this.lightSignalProperties[8] = this.mainNBTTC.getString("Name");
        this.lightSignalProperties[9] = this.mainNBTTC.getString("Scale");
        this.lightSignalProperties[10] = this.mainNBTTC.getString("HasPNLight");

    }

    public void writePacketData(final PacketBuffer buffer) throws IOException {
        buffer.writeNBTTagCompoundToBuffer(this.mainNBTTC);
    }

    public void processPacket(final INetHandler netHandler) {
        this.handleServerSide((NetHandlerPlayServerSide) netHandler);
    }
}
