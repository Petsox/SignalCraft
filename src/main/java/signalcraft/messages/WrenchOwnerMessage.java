package signalcraft.messages;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class WrenchOwnerMessage implements IMessage {
    private ItemStack itemStack;
    private String owner;

    public WrenchOwnerMessage() {}

    public WrenchOwnerMessage(ItemStack itemStack, String owner) {
        this.itemStack = itemStack;
        this.owner = owner;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.itemStack = ByteBufUtils.readItemStack(buf);
        this.owner = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeItemStack(buf, this.itemStack);
        ByteBufUtils.writeUTF8String(buf, this.owner);
    }

    public static class Handler implements IMessageHandler<WrenchOwnerMessage, IMessage> {
        @Override
        public IMessage onMessage(WrenchOwnerMessage message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            if (player != null) {
                ItemStack stack = message.itemStack;
                if (stack != null && stack.hasTagCompound()) {
                    stack.getTagCompound().setString("Owner", message.owner);
                }
            }
            return null;
        }
    }
}
