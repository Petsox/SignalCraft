package signalcraft.signalUtils;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import net.minecraft.tileentity.TileEntity;
import signalcraft.SignalCraft;
import signalcraft.entities.controllers.TileContReceBase;
import signalcraft.network.ClientPacket;
import signalcraft.network.ServerPacket;
import signalcraft.packet.controllers.CPacketUpdateControllers;
import signalcraft.packet.controllers.SPacketUpdateControllers;
import signalcraft.packet.levelCrossings.CPacketUpdateCrossings;
import signalcraft.packet.levelCrossings.SPacketUpdateCrossings;
import signalcraft.packet.lightSignals.CPacketUpdateLightSignals;
import signalcraft.packet.lightSignals.SPacketUpdateLightSignals;
import signalcraft.packet.signSignals.CPacketUpdateSignSignals;
import signalcraft.packet.signSignals.SPacketUpdateSignSignals;
import signalcraft.packet.switches.CPacketUpdateSwitches;
import signalcraft.packet.switches.SPacketUpdateSwitches;

import java.util.LinkedList;
import java.util.List;

public class Network {

    public static void updateControllers(TileContReceBase tile) {
        if (tile.getWorldObj().isRemote) {
            sendToServer(new CPacketUpdateControllers(tile), tile);
        } else {
            sendToAll(new SPacketUpdateControllers(tile), tile);
        }
    }

    public static void updateCrossings(TileEntity tile) {
        if (tile.getWorldObj().isRemote) {
            sendToServer(new CPacketUpdateCrossings(tile), tile);
        } else {
            sendToAll(new SPacketUpdateCrossings(tile), tile);
        }
    }

    public static void updateLightSignals(TileEntity tile) {
        if (tile.getWorldObj().isRemote) {
            sendToServer(new CPacketUpdateLightSignals(tile), tile);
        } else {
            sendToAll(new SPacketUpdateLightSignals(tile), tile);
        }
    }

    public static void updateSignSignals(TileEntity tile) {
        if (tile.getWorldObj().isRemote) {
            sendToServer(new CPacketUpdateSignSignals(tile), tile);
        } else {
            sendToAll(new SPacketUpdateSignSignals(tile), tile);
        }
    }

    public static void updateSwitches(TileEntity tile) {
        if (tile.getWorldObj().isRemote) {
            sendToServer(new CPacketUpdateSwitches(tile), tile);
        } else {
            sendToAll(new SPacketUpdateSwitches(tile), tile);
        }
    }

    // Client -> server only: telling the server "I changed this" is only meaningful from the
    // client, and CPacket*'s TileEntity constructor is @SideOnly(Side.CLIENT) (stripped on
    // dedicated servers), so this must never be reached with tile.getWorldObj().isRemote == false.
    private static void sendToServer(ClientPacket thePacket, TileEntity tile) {
        tile.getWorldObj().markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
        try {
            final List<Object> list = new LinkedList<>();
            SignalCraft.proxy.packetPipeline.encode(thePacket, list);
            SignalCraft.proxy.packetPipeline.sendToServer((FMLProxyPacket) list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Server -> all clients only: sendToAll needs the running MinecraftServer instance to
    // enumerate players, which doesn't exist on a remote client, so this must only run server-side.
    private static void sendToAll(ServerPacket thePacket, TileEntity tile) {
        tile.getWorldObj().markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
        try {
            final List<Object> list = new LinkedList<>();
            SignalCraft.proxy.packetPipeline.encode(thePacket, list);
            SignalCraft.proxy.packetPipeline.sendToAll((FMLProxyPacket) list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
