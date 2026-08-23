package signalcraft.packet.mechSignals;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import signalcraft.network.NetHandlerPlayClientSide;
import signalcraft.network.ServerPacket;
import signalcraft.signalUtils.Consts;

import java.io.IOException;

public class SPacketUpdateMechSignals extends ServerPacket {
    private NBTTagCompound mainNBTTC;

    /**
     * String[] mechanicalSignalProperties - Lenght in Consts.java
     * [0] - State
     * [1] - Position
     * [2] - IsDeparture
     * [3] - IsGroupped
     * [4] - Type
     * [5] - Signal Name
     * [6] - Signal Render Scale
     * [7] - Arm 1 Rotation
     * [8] - Arm 2 Rotation
     */
    private final String[] mechanicalSignalProperties;
    private int xCoordinate;
    private int yCoordinate;
    private int zCoordinate;

    public SPacketUpdateMechSignals() {
        this.mechanicalSignalProperties = new String[Consts.mechPropArrLenght];
    }

    @SideOnly(Side.CLIENT)
    public SPacketUpdateMechSignals(final TileEntity tileEntity) {
        this.mechanicalSignalProperties = new String[Consts.mechPropArrLenght];
        tileEntity.writeToNBT(this.mainNBTTC = new NBTTagCompound());
    }

    public SPacketUpdateMechSignals(final int x, final int y, final int z) {
        this.mechanicalSignalProperties = new String[Consts.mechPropArrLenght];
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.zCoordinate = z;
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

    protected void handleClientSide(final NetHandlerPlayClientSide netHandler) {
        netHandler.handleUpdateClient(this);
    }

    public void readPacketData(final PacketBuffer buffer) throws IOException {
        this.mainNBTTC = buffer.readNBTTagCompoundFromBuffer();
        this.xCoordinate = this.mainNBTTC.getInteger("x");
        this.yCoordinate = this.mainNBTTC.getInteger("y");
        this.zCoordinate = this.mainNBTTC.getInteger("z");
        this.mechanicalSignalProperties[0] = this.mainNBTTC.getString("State");
        this.mechanicalSignalProperties[1] = this.mainNBTTC.getString("Position");
        this.mechanicalSignalProperties[2] = this.mainNBTTC.getString("IsDeparture");
        this.mechanicalSignalProperties[3] = this.mainNBTTC.getString("IsGroupped");
        this.mechanicalSignalProperties[4] = this.mainNBTTC.getString("Type");
        this.mechanicalSignalProperties[5] = this.mainNBTTC.getString("Name");
        this.mechanicalSignalProperties[6] = this.mainNBTTC.getString("Scale");
        this.mechanicalSignalProperties[7] = this.mainNBTTC.getString("Arm1Rotation");
        this.mechanicalSignalProperties[8] = this.mainNBTTC.getString("Arm2Rotation");
    }

    public void writePacketData(final PacketBuffer buffer) throws IOException {
        buffer.writeNBTTagCompoundToBuffer(this.mainNBTTC);
    }

    public void processPacket(final INetHandler netHandler) {
        this.handleClientSide((NetHandlerPlayClientSide) netHandler);
    }
}
