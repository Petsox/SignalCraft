package signalcraft.messages;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import signalcraft.SignalCraft;
import signalcraft.entities.levelCrossings.ICrossingRuntime;
import signalcraft.signalUtils.CrossingDebug;

/** Server -> client: switch the client's crossing debug log on/off, or write its state of the crossings around into it. */
public class MessageCrossingDebug implements IMessage {
    public static final byte OFF = 0;
    public static final byte ON = 1;
    public static final byte DUMP = 2;

    public byte action;
    public int radius;

    public MessageCrossingDebug() {}

    public MessageCrossingDebug(byte action, int radius) {
        this.action = action;
        this.radius = radius;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeByte(action);
        buf.writeInt(radius);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        action = buf.readByte();
        radius = buf.readInt();
    }

    /** Handled by the client proxy - nothing in here may touch client-only classes (dedicated server). */
    public static class Handler implements IMessageHandler<MessageCrossingDebug, IMessage> {
        @Override
        public IMessage onMessage(MessageCrossingDebug message, MessageContext ctx) {
            SignalCraft.proxy.onCrossingDebug(message);
            return null;
        }
    }

    /** Writes the state of every crossing tile within radius into the debug log of the world's side. */
    public static int dump(World world, double px, double py, double pz, int radius) {
        boolean client = world.isRemote;
        CrossingDebug.log(client, "--- DUMP (" + (client ? "client" : "server") + ") tick=" + world.getTotalWorldTime()
                + " around " + (int) px + "," + (int) py + "," + (int) pz + " r=" + radius + " ---");
        int count = 0;
        for (Object o : world.loadedTileEntityList) {
            TileEntity tile = (TileEntity) o;
            if (!(tile instanceof ICrossingRuntime)) continue;
            double dx = tile.xCoord - px, dy = tile.yCoord - py, dz = tile.zCoord - pz;
            if (dx * dx + dy * dy + dz * dz > (double) radius * radius) continue;
            CrossingDebug.log(client, "  " + CrossingDebug.describe(tile) + " " + ((ICrossingRuntime) tile).describeRuntime());
            count++;
        }
        CrossingDebug.log(client, "--- END DUMP (" + count + " tiles) ---");
        return count;
    }
}
