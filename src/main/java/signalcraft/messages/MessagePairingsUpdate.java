package signalcraft.messages;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import signalcraft.entities.controllers.TileController;
import signalcraft.entities.controllers.TileReceiver;
import signalcraft.signalUtils.BlockPos;


public class MessagePairingsUpdate implements IMessage
{
    private int Cx;
    private int Cy;
    private int Cz;
    private boolean addPairing; // true to add, false to remove
    private int Rx;
    private int Ry;
    private int Rz;
    private int ReceiverID;

    public MessagePairingsUpdate() {}

    public MessagePairingsUpdate(final int Cx, final int Cy, final int Cz, final int Rx, final int Ry, final int Rz, final boolean addPairing, final int ReceiverID) {
        this.Cx = Cx;
        this.Cy = Cy;
        this.Cz = Cz;
        this.Rx = Rx;
        this.Ry = Ry;
        this.Rz = Rz;
        this.addPairing = addPairing;
        this.ReceiverID = ReceiverID;
    }

    public void toBytes(final ByteBuf target) {
        target.writeInt(this.Cx);
        target.writeInt(this.Cy);
        target.writeInt(this.Cz);
        target.writeInt(this.Rx);
        target.writeInt(this.Ry);
        target.writeInt(this.Rz);
        target.writeBoolean(this.addPairing);
        target.writeInt(this.ReceiverID);
    }

    public void fromBytes(final ByteBuf dat) {
        this.Cx = dat.readInt();
        this.Cy = dat.readInt();
        this.Cz = dat.readInt();
        this.Rx = dat.readInt();
        this.Ry = dat.readInt();
        this.Rz = dat.readInt();
        this.addPairing = dat.readBoolean();
        this.ReceiverID = dat.readInt();
    }
    
    public static class Handler implements IMessageHandler<MessagePairingsUpdate, IMessage>
    {
        public IMessage onMessage(final MessagePairingsUpdate message, final MessageContext ctx) {
            final TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.Cx, message.Cy, message.Cz);
            if (tileEntity != null && message.addPairing && tileEntity instanceof TileController) {
                ((TileController)tileEntity).addPairing(new int[] {message.Rx, message.Ry, message.Rz});
            } else if (!message.addPairing && tileEntity instanceof TileController) {
                ((TileController)tileEntity).removePairing(new BlockPos(message.Rx, message.Ry, message.Rz));
            } else if (tileEntity instanceof TileReceiver) {
                TileReceiver receiver = (TileReceiver) tileEntity;
                if (message.addPairing) {
                    receiver.getPairings().put(new BlockPos(message.Cx, message.Cy, message.Cz), message.ReceiverID);
                } else {
                    receiver.getPairings().remove(new BlockPos(message.Cx, message.Cy, message.Cz));
                }

            } else {
                System.out.println("USELSD");
            }
            return null;
        }
    }
}
