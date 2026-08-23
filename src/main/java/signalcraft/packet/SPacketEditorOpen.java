package signalcraft.packet;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.network.INetHandler;
import net.minecraft.network.PacketBuffer;
import signalcraft.entities.TileSignal;
import signalcraft.network.NetHandlerPlayClientSide;
import signalcraft.network.ServerPacket;

import java.io.IOException;

public class SPacketEditorOpen extends ServerPacket
{
    private int xCoordinate;
    private int yCoordinate;
    private int zCoordinate;
    private int guiId;

    public SPacketEditorOpen() {}

    public SPacketEditorOpen(final TileSignal tileEntity) {
        this.xCoordinate = tileEntity.xCoord;
        this.yCoordinate = tileEntity.yCoord;
        this.zCoordinate = tileEntity.zCoord;
        this.guiId = tileEntity.getGuiId().getId();
    }
    
    @SideOnly(Side.CLIENT)
    public int getXCoordinate() {
        return this.xCoordinate;
    }
    
    @SideOnly(Side.CLIENT)
    public int getYCoordinate() {
        return this.yCoordinate;
    }
    
    @SideOnly(Side.CLIENT)
    public int getZCoordinate() {
        return this.zCoordinate;
    }
    @SideOnly(Side.CLIENT)
    public int getGuiId() {
        return guiId;
    }

    protected void handleClientSide(final NetHandlerPlayClientSide netHandler) {
        netHandler.handleEditorOpen(this);
    }
    
    public void readPacketData(final PacketBuffer buffer) throws IOException {
        this.xCoordinate = buffer.readInt();
        this.yCoordinate = buffer.readInt();
        this.zCoordinate = buffer.readInt();
        this.guiId = buffer.readInt();
    }
    
    public void writePacketData(final PacketBuffer buffer) throws IOException {
        buffer.writeInt(this.xCoordinate);
        buffer.writeInt(this.yCoordinate);
        buffer.writeInt(this.zCoordinate);
        buffer.writeInt(this.guiId);
    }
    
    public void processPacket(final INetHandler netHandler) {
        this.handleClientSide((NetHandlerPlayClientSide)netHandler);
    }
}
