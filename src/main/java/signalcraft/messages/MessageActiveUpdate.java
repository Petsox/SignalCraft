package signalcraft.messages;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import signalcraft.entities.IActivatable;
import signalcraft.entities.levelCrossings.TileLevelCrossing;


public class MessageActiveUpdate implements IMessage
{
    private int x;
    private int y;
    private int z;
    private boolean isActive;

    public MessageActiveUpdate() {}

    public MessageActiveUpdate(final int x, final int y, final int z, final boolean isActive) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.isActive = isActive;
    }

    public void toBytes(final ByteBuf target) {
        target.writeInt(this.x);
        target.writeInt(this.y);
        target.writeInt(this.z);
        target.writeBoolean(this.isActive);
    }

    public void fromBytes(final ByteBuf dat) {
        this.x = dat.readInt();
        this.y = dat.readInt();
        this.z = dat.readInt();
        this.isActive = dat.readBoolean();
    }
    
    public static class Handler implements IMessageHandler<MessageActiveUpdate, IMessage>
    {
        public IMessage onMessage(final MessageActiveUpdate message, final MessageContext ctx) {
            final TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
            if (tileEntity != null) {
                ((IActivatable)tileEntity).setIsActive(message.isActive);
                ((IActivatable)tileEntity).setBlinkCounter(0);
            }
            else {
                System.out.println("USELSD");
            }
            return null;
        }
    }
}
