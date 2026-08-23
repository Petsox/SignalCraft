package signalcraft.packet.controllers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import signalcraft.network.NetHandlerPlayClientSide;
import signalcraft.network.ServerPacket;

import java.io.IOException;

public class SPacketUpdateControllers extends ServerPacket {
    private NBTTagCompound mainNBTTC;
    private final int[] receiverCoordinates = new int[3];
    private final int[] signalStates = new int[16];
    private String Name;
    private int xCoordinate;
    private int yCoordinate;
    private int zCoordinate;

    public SPacketUpdateControllers() {
    }

    public SPacketUpdateControllers(final TileEntity tileEntity) {
        tileEntity.writeToNBT(this.mainNBTTC = new NBTTagCompound());
    }

    public SPacketUpdateControllers(final int x, final int y, final int z) {
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.zCoordinate = z;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public NBTTagCompound getMainNBTTC() {
        return this.mainNBTTC;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getXCoordinate() {
        return this.xCoordinate;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getYCoordinate() {
        return this.yCoordinate;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getZCoordinate() {
        return this.zCoordinate;
    }

    protected void handleClientSide(final NetHandlerPlayClientSide netHandler) {
        netHandler.handleUpdateClient(this);
    }

    public void readPacketData(final PacketBuffer buffer) throws IOException {
        this.mainNBTTC = buffer.readNBTTagCompoundFromBuffer();
        this.xCoordinate = this.mainNBTTC.getInteger("x");
        this.yCoordinate = this.mainNBTTC.getInteger("y");
        this.zCoordinate = this.mainNBTTC.getInteger("z");
        this.Name = this.mainNBTTC.getString("Name");

        NBTTagList list = this.mainNBTTC.getTagList("receivers", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound receiverTag = list.getCompoundTagAt(i);
            this.receiverCoordinates[0] = receiverTag.getInteger("receiverX");
            this.receiverCoordinates[1] = receiverTag.getInteger("receiverY");
            this.receiverCoordinates[2] = receiverTag.getInteger("receiverZ");
        }
    }

    public void writePacketData(final PacketBuffer buffer) throws IOException {
        buffer.writeNBTTagCompoundToBuffer(this.mainNBTTC);
    }

    public void processPacket(final INetHandler netHandler) {
        this.handleClientSide((NetHandlerPlayClientSide) netHandler);
    }
}
