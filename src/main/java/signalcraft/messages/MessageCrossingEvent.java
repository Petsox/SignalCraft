package signalcraft.messages;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.SignalCraft;
import signalcraft.entities.levelCrossings.CrossingEvent;
import signalcraft.entities.levelCrossings.ICrossingRuntime;
import signalcraft.signalUtils.CrossingDebug;

/**
 * Server -> clients: an event the server applied to one crossing tile (see {@link CrossingEvent}).
 * The clients apply the same event to their own tile. The only crossing message.
 */
public class MessageCrossingEvent implements IMessage {
    public int dimension;
    public int x;
    public int y;
    public int z;
    public long serverTick;
    public CrossingEvent event;
    /** The server's state of the tile right after the event - only filled while the server debug log is on, for comparing. */
    public String serverState;

    public MessageCrossingEvent() {}

    private MessageCrossingEvent(TileEntity tile, CrossingEvent event, String serverState) {
        this.dimension = tile.getWorldObj().provider.dimensionId;
        this.x = tile.xCoord;
        this.y = tile.yCoord;
        this.z = tile.zCoord;
        this.serverTick = tile.getWorldObj().getTotalWorldTime();
        this.event = event;
        this.serverState = serverState;
    }

    /** Sends an event the server has just applied to the tile to every player in its dimension. Server side only. */
    public static void send(TileEntity tile, CrossingEvent event) {
        World world = tile.getWorldObj();
        if (world == null || world.isRemote || !(tile instanceof ICrossingRuntime)) return;
        tile.markDirty();
        String state = CrossingDebug.isServerOn() ? ((ICrossingRuntime) tile).describeRuntime() : "";
        SignalCraft.SCNet.sendToDimension(new MessageCrossingEvent(tile, event, state), world.provider.dimensionId);
        CrossingDebug.log(tile, "EVENT " + event + " -> " + state);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(dimension);
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeLong(serverTick);
        event.write(buf);
        ByteBufUtils.writeUTF8String(buf, serverState == null ? "" : serverState);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        dimension = buf.readInt();
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
        serverTick = buf.readLong();
        event = CrossingEvent.read(buf);
        serverState = ByteBufUtils.readUTF8String(buf);
    }

    /** Handled by the client proxy - nothing in here may touch client-only classes (dedicated server). */
    public static class Handler implements IMessageHandler<MessageCrossingEvent, IMessage> {
        @Override
        public IMessage onMessage(MessageCrossingEvent message, MessageContext ctx) {
            SignalCraft.proxy.onCrossingEvent(message);
            return null;
        }
    }
}
