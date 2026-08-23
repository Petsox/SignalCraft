package signalcraft.messages;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import signalcraft.items.ItemSignalRenamer;

public class RenamerNameMessage implements IMessage {
    private String name;
    private int slot;

    public RenamerNameMessage() {
    }

    public RenamerNameMessage(String name, int slot) {
        this.name = name;
        this.slot = slot;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.slot = buf.readInt();
        this.name = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.slot);
        ByteBufUtils.writeUTF8String(buf, this.name);
    }

    public static class Handler implements IMessageHandler<RenamerNameMessage, IMessage> {
        @Override
        public IMessage onMessage(final RenamerNameMessage message, final MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            int slot = message.slot;
            ItemStack stack = player.inventory.getStackInSlot(slot);
            if (stack != null && stack.getItem() instanceof ItemSignalRenamer) {
                String name = message.name;
                if (name != null) {
                    stack.setStackDisplayName(name);
                }
                player.inventoryContainer.detectAndSendChanges();
            }
            return null;
        }
    }
}
