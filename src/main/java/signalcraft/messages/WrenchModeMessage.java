package signalcraft.messages;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

// Packet for syncing the mode
public class WrenchModeMessage implements IMessage {
    private ItemStack itemStack;

    public WrenchModeMessage() {}

    public WrenchModeMessage(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.itemStack = ByteBufUtils.readItemStack(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeItemStack(buf, this.itemStack);
    }

    public static class Handler implements IMessageHandler<WrenchModeMessage, IMessage> {
        @Override
        public IMessage onMessage(WrenchModeMessage message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            if (player != null) {
                ItemStack stack = message.itemStack;
                if (stack != null && stack.hasTagCompound()) {
                    NBTTagCompound tag = stack.getTagCompound();
                    String mode = tag.getString("Mode");
                    String newMode = mode.equals("Adding") ? "Editing" : "Adding";
                    tag.setString("Mode", newMode);
                }
            }
            return null;
        }
    }
}