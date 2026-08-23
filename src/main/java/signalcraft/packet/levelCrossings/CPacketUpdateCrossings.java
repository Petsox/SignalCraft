package signalcraft.packet.levelCrossings;

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

public class CPacketUpdateCrossings extends ClientPacket {
    private NBTTagCompound mainNBTTC;
    private final String[] crossPropArr;
    private int xCoordinate;
    private int yCoordinate;
    private int zCoordinate;

    public CPacketUpdateCrossings() {
        this.crossPropArr = new String[Consts.crossPropArrLenght];
    }

    @SideOnly(Side.CLIENT)
    public CPacketUpdateCrossings(final TileEntity tileEntity) {
        this.crossPropArr = new String[Consts.crossPropArrLenght];
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
        this.crossPropArr[0] = this.mainNBTTC.getString("getIsActive");
        this.crossPropArr[1] = this.mainNBTTC.getString("armDownDelay");
        this.crossPropArr[2] = this.mainNBTTC.getString("soundOn");
        this.crossPropArr[3] = this.mainNBTTC.getString("strongSoundOn");
        this.crossPropArr[4] = this.mainNBTTC.getString("SoundType");
        this.crossPropArr[5] = this.mainNBTTC.getString("hasKriz");
        this.crossPropArr[6] = this.mainNBTTC.getString("isKrizJedno");
        this.crossPropArr[7] = this.mainNBTTC.getString("hasKrizNaStozaru");
        this.crossPropArr[8] = this.mainNBTTC.getString("isKrizReflex");
        this.crossPropArr[9] = this.mainNBTTC.getString("hasPozLight");
        this.crossPropArr[10] = this.mainNBTTC.getString("usePozLight");
        this.crossPropArr[11] = this.mainNBTTC.getString("isPozLightShort");
        this.crossPropArr[12] = this.mainNBTTC.getString("hasZebrik");
        this.crossPropArr[13] = this.mainNBTTC.getString("isSlovak");
        this.crossPropArr[14] = this.mainNBTTC.getString("hasPruhy");
        this.crossPropArr[15] = this.mainNBTTC.getString("DistFromSloup");
        this.crossPropArr[16] = this.mainNBTTC.getString("LightPos");
        this.crossPropArr[17] = this.mainNBTTC.getString("isCedule");
        this.crossPropArr[18] = this.mainNBTTC.getString("isOtradovice");
        this.crossPropArr[19] = this.mainNBTTC.getString("headRot");
        this.crossPropArr[20] = this.mainNBTTC.getString("Name");
        this.crossPropArr[22] = this.mainNBTTC.getString("BarrierLength");
        this.crossPropArr[23] = this.mainNBTTC.getString("armRotation");
        this.crossPropArr[24] = this.mainNBTTC.getString("isKrizVelky");
        this.crossPropArr[25] = this.mainNBTTC.getString("isLightCoverShort");
        this.crossPropArr[26] = this.mainNBTTC.getString("isNewer");
    }

    public void writePacketData(final PacketBuffer buffer) throws IOException {
        buffer.writeNBTTagCompoundToBuffer(this.mainNBTTC);
    }

    public void processPacket(final INetHandler netHandler) {
        this.handleServerSide((NetHandlerPlayServerSide) netHandler);
    }
}
