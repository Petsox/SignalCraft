package signalcraft.network;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import signalcraft.SignalCraft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@ChannelHandler.Sharable
public class PacketPipeline
{
    private static final Logger logger;
    private final FMLEventChannel channel;
    private boolean isPostInitialised;
    private final List<Class<? extends Packet>> packetsList;
    
    public PacketPipeline() {
        this.isPostInitialised = false;
        this.packetsList = new ArrayList<>();
        this.channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(SignalCraft.MOD_ID);
    }
    
    public void decode(final FMLProxyPacket proxyPacket, final List<Object> out) throws Exception {
        final ByteBuf payload = proxyPacket.payload();
        final byte discriminator = payload.readByte();
        final Class<? extends Packet> theClass = this.packetsList.get(discriminator);
        if (theClass == null) {
            throw new NullPointerException("No packet registered for discriminator: " + discriminator);
        }
        final Packet thePacket = theClass.newInstance();
        thePacket.readPacketData(new PacketBuffer(payload.slice()));
        switch (FMLCommonHandler.instance().getEffectiveSide()) {
            case CLIENT: {
                final NetHandlerPlayClientSide NHPCS = new NetHandlerPlayClientSide(Minecraft.getMinecraft(), Minecraft.getMinecraft().currentScreen, Minecraft.getMinecraft().getNetHandler().getNetworkManager());
                thePacket.processPacket(NHPCS);
                break;
            }
            case SERVER: {
                final NetHandlerPlayServer netHandler = (NetHandlerPlayServer)NetworkRegistry.INSTANCE.getChannel(SignalCraft.MOD_ID, Side.SERVER).attr(NetworkRegistry.NET_HANDLER).get();
                final NetHandlerPlayServerSide NHPSS = new NetHandlerPlayServerSide(MinecraftServer.getServer(), netHandler.playerEntity, netHandler.netManager);
                thePacket.processPacket(NHPSS);
                break;
            }
        }
        out.add(thePacket);
    }
    
    public void encode(final Packet thePacket, final List<Object> out) throws Exception {
        final PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
        final Class<? extends Packet> theClass = thePacket.getClass();
        if (!this.packetsList.contains(thePacket.getClass())) {
            throw new NullPointerException("No Packet Registered for: " + thePacket.getClass().getCanonicalName());
        }
        final byte discriminator = (byte)this.packetsList.indexOf(theClass);
        packetBuffer.writeByte(discriminator);
        thePacket.writePacketData(packetBuffer);
        final FMLProxyPacket proxyPacket = new FMLProxyPacket(packetBuffer.copy(), SignalCraft.MOD_ID);
        out.add(proxyPacket);
    }
    
    public List<Object> getPacketsList() {
        return new ArrayList<Object>(this.packetsList);
    }
    
    public void postInitialise() {
        if (this.isPostInitialised) {
            return;
        }
        this.isPostInitialised = true;
        Collections.sort(this.packetsList, new Comparator<Class<? extends Packet>>() {
            @Override
            public int compare(final Class<? extends Packet> theClass1, final Class<? extends Packet> theClass2) {
                int com = String.CASE_INSENSITIVE_ORDER.compare(theClass1.getCanonicalName(), theClass2.getCanonicalName());
                if (com == 0) {
                    com = theClass1.getCanonicalName().compareTo(theClass2.getCanonicalName());
                }
                return com;
            }
        });
    }
    
    public void registerEventListener(final Object obj) {
        this.channel.register(obj);
    }
    
    public boolean registerPacket(final Class<? extends Packet> theClass) {
        if (this.packetsList.size() > 256) {
            PacketPipeline.logger.debug("packetsList.size() > 256");
            return false;
        }
        if (this.packetsList.contains(theClass)) {
            PacketPipeline.logger.debug("packetsList.contains(theClass)");
            return false;
        }
        if (this.isPostInitialised) {
            PacketPipeline.logger.debug("isPostInitialised");
            return false;
        }
        this.packetsList.add(theClass);
        return true;
    }
    
    public void sendTo(final FMLProxyPacket pkt, final EntityPlayerMP player) {
        this.channel.sendTo(pkt, player);
    }
    
    public void sendToAll(final FMLProxyPacket pkt) {
        this.channel.sendToAll(pkt);
    }
    
    public void sendToAllAround(final FMLProxyPacket pkt, final NetworkRegistry.TargetPoint point) {
        this.channel.sendToAllAround(pkt, point);
    }
    
    public void sendToDimension(final FMLProxyPacket pkt, final int dimensionId) {
        this.channel.sendToDimension(pkt, dimensionId);
    }
    
    public void sendToServer(final FMLProxyPacket pkt) {
        this.channel.sendToServer(pkt);
    }
    
    static {
        logger = LogManager.getLogger();
    }
}
