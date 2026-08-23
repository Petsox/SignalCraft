package signalcraft.messages;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import signalcraft.entities.TileSignal;

import java.nio.charset.StandardCharsets;


public class MessageNameUpdate implements IMessage
{
    private int x;
    private int y;
    private int z;
    private String Name;

    public MessageNameUpdate() {}

    public MessageNameUpdate(final int x, final int y, final int z, final String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.Name = name;
    }

    public void toBytes(final ByteBuf target) {
        target.writeInt(this.x);
        target.writeInt(this.y);
        target.writeInt(this.z);

        byte[] stringBytes = this.Name.getBytes(StandardCharsets.UTF_8);
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
        this.Name = new String(stringBytes, StandardCharsets.UTF_8);
    }
    
    public static class Handler implements IMessageHandler<MessageNameUpdate, IMessage>
    {
        public IMessage onMessage(final MessageNameUpdate message, final MessageContext ctx) {
            final TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
            if (tileEntity != null) {
                ((TileSignal)tileEntity).setName(message.Name);
            }
            else {
                System.out.println("USELSD");
            }
            return null;
        }
    }
}
