package signalcraft.messages;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import signalcraft.entities.signals.ISignal;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.signalUtils.SignalState;

import java.nio.charset.StandardCharsets;


public class MessageStateUpdate implements IMessage
{
    private int x;
    private int y;
    private int z;
    private String state;

    public MessageStateUpdate() {}

    public MessageStateUpdate(final int x, final int y, final int z, final String state) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.state = state;
    }

    public void toBytes(final ByteBuf target) {
        target.writeInt(this.x);
        target.writeInt(this.y);
        target.writeInt(this.z);

        byte[] stringBytes = this.state.getBytes(StandardCharsets.UTF_8);
        target.writeInt(stringBytes.length);
        target.writeBytes(stringBytes);
    }

    public void fromBytes(final ByteBuf dat) {
        this.x = dat.readInt();
        this.y = dat.readInt();
        this.z = dat.readInt();

        int length = dat.readInt();
        byte[] stringBytes = new byte[length];
        dat.readBytes(stringBytes);
        this.state = new String(stringBytes, StandardCharsets.UTF_8);
    }
    
    public static class Handler implements IMessageHandler<MessageStateUpdate, IMessage>
    {
        public IMessage onMessage(final MessageStateUpdate message, final MessageContext ctx) {
            final TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
            if (tileEntity instanceof ISignal) {
                ((ISignal)tileEntity).setState(SignalState.fromOrdinal(message.state));
            }
            return null;
        }
    }
}
