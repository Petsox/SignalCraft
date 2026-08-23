package signalcraft.packet;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import signalcraft.SignalCraft;

import java.util.List;

public class CustomPacketEventListener
{
    protected final List<Object> packetList;

    public CustomPacketEventListener(final List<Object> packetList) {
        this.packetList = packetList;
    }
    
    @SubscribeEvent
    public void listener_ClientCustomPacketEvent(final FMLNetworkEvent.ClientCustomPacketEvent e) throws Exception {
        SignalCraft.proxy.packetPipeline.decode(e.packet, this.packetList);
    }
    
    @SubscribeEvent
    public void listener_ServerCustomPacketEvent(final FMLNetworkEvent.ServerCustomPacketEvent e) throws Exception {
        SignalCraft.proxy.packetPipeline.decode(e.packet, this.packetList);
    }
}
