package signalcraft.packet.signSignals;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import signalcraft.network.ClientPacket;
import signalcraft.network.NetHandlerPlayServerSide;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.Utils;

import java.io.IOException;

public class CPacketUpdateSignSignals extends ClientPacket {
    private NBTTagCompound mainNBTTC;

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
    private int xCoordinate;
    private int yCoordinate;
    private int zCoordinate;

    public CPacketUpdateSignSignals() {
        this.signSignalProperties = new String[Consts.signPropArrLenght];
    }

    @SideOnly(Side.CLIENT)
    public CPacketUpdateSignSignals(final TileEntity tileEntity) {
        this.signSignalProperties = new String[Consts.signPropArrLenght];
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
        this.signSignalProperties[0] = mainNBTTC.getString("speedSignalText");
        this.signSignalProperties[1] = mainNBTTC.getString("SignID");
        this.signSignalProperties[2] = mainNBTTC.getString("XAdjust");
        this.signSignalProperties[3] = mainNBTTC.getString("YAdjust");
        this.signSignalProperties[4] = mainNBTTC.getString("scaleAdjust");
        this.signSignalProperties[5] = mainNBTTC.getString("rotate");
        this.signSignalProperties[6] = mainNBTTC.getString("stationLabelStativ");
        this.signSignalProperties[7] = mainNBTTC.getString("signalLabelStativ");
        this.signSignalProperties[8] = mainNBTTC.getString("isActive");
        this.signSignalProperties[9] = mainNBTTC.getString("hasSH2Lamp");
        this.signSignalProperties[10] = mainNBTTC.getString("hasSH2Stativ");
        this.signSignalProperties[11] = mainNBTTC.getString("leverTexture");
        this.signSignalProperties[12] = mainNBTTC.getString("showsTextSide");
        this.signSignalProperties[13] = mainNBTTC.getString("fontStyleList");
        this.signSignalProperties[14] = mainNBTTC.getString("modelButtonStatus");
        this.signSignalProperties[15] = mainNBTTC.getString("signTextField");
        this.signSignalProperties[16] = mainNBTTC.getString("signTextColor");
    }

    public void writePacketData(final PacketBuffer buffer) throws IOException {
        buffer.writeNBTTagCompoundToBuffer(this.mainNBTTC);
    }

    public void processPacket(final INetHandler netHandler) {
        this.handleServerSide((NetHandlerPlayServerSide) netHandler);
    }
}
