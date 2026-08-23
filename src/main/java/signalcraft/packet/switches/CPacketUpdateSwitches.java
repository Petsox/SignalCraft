package signalcraft.packet.switches;

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

public class CPacketUpdateSwitches extends ClientPacket {
    private NBTTagCompound mainNBTTC;

    /**
     * String[] switchPropArrLenght - Lenght in Consts.java
     * [0] - isSwitched
     * [1] - rotation
     * [2] - switchName
     * [3] - switchPos
     * [4] - switchSide
     * [5] - switchMode
     * [6] - isOutputtingRedstone
     * [7] - isInverted
     */
    private final String[] switchProperties;
    private int xCoordinate;
    private int yCoordinate;
    private int zCoordinate;

    public CPacketUpdateSwitches() {
        this.switchProperties = new String[Consts.switchPropArrLenght];
    }

    @SideOnly(Side.CLIENT)
    public CPacketUpdateSwitches(final TileEntity tileEntity) {
        this.switchProperties = new String[Consts.switchPropArrLenght];
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
        this.switchProperties[0] = mainNBTTC.getString("isSwitched");
        this.switchProperties[1] = mainNBTTC.getString("rotation");
        this.switchProperties[2] = mainNBTTC.getString("switchName");
        this.switchProperties[3] = mainNBTTC.getString("switchPos");
        this.switchProperties[4] = mainNBTTC.getString("switchSide");
        this.switchProperties[5] = mainNBTTC.getString("switchMode");
        this.switchProperties[6] = mainNBTTC.getString("isOutputtingRedstone");
        this.switchProperties[7] = mainNBTTC.getString("isInverted");
    }

    public void writePacketData(final PacketBuffer buffer) throws IOException {
        buffer.writeNBTTagCompoundToBuffer(this.mainNBTTC);
    }

    public void processPacket(final INetHandler netHandler) {
        this.handleServerSide((NetHandlerPlayServerSide) netHandler);
    }
}
