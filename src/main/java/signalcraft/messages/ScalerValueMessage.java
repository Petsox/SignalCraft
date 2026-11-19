package signalcraft.messages;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import signalcraft.items.ItemScaler;

/** Client -> server: the scale typed into the Scaler's GUI, stored in the Scaler in the given hotbar slot. */
public class ScalerValueMessage implements IMessage {
    private float scale;
    private int slot;

    public ScalerValueMessage() {
    }

    public ScalerValueMessage(float scale, int slot) {
        this.scale = scale;
        this.slot = slot;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.slot = buf.readInt();
        this.scale = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.slot);
        buf.writeFloat(this.scale);
    }

    public static class Handler implements IMessageHandler<ScalerValueMessage, IMessage> {
        @Override
        public IMessage onMessage(final ScalerValueMessage message, final MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().playerEntity;
            if (message.slot < 0 || message.slot >= 9) return null;
            ItemStack stack = player.inventory.getStackInSlot(message.slot);
            if (stack != null && stack.getItem() instanceof ItemScaler) {
                ItemScaler.setScale(stack, message.scale);
                player.inventoryContainer.detectAndSendChanges();
            }
            return null;
        }
    }
}
