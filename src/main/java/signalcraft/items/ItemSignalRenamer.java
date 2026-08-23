package signalcraft.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import signalcraft.SignalCraft;
import signalcraft.entities.TileSignal;
import signalcraft.messages.MessageNameUpdate;
import signalcraft.proxy.CommonProxy;
import signalcraft.signalUtils.Utils;

import java.util.List;

public class ItemSignalRenamer extends Item {
    public ItemSignalRenamer(String itemName) {
        this.setMaxStackSize(1);
        this.setUnlocalizedName(itemName);
        this.setTextureName(SignalCraft.MOD_ID + ":renamer");
        this.setCreativeTab(CommonProxy.tabOther);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(EnumChatFormatting.YELLOW + String.valueOf(EnumChatFormatting.BOLD) + I18n.format("gui.signalRenamer.use"));
        list.add(I18n.format("gui.signalRenamer.openGui"));
        if (GuiScreen.isShiftKeyDown()) {
            list.add(EnumChatFormatting.DARK_PURPLE + String.valueOf(EnumChatFormatting.ITALIC) + I18n.format("gui.signalRenamer.railcraft"));
        }
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (!world.isRemote) {
            if (itemStack.hasDisplayName()) {
                if (itemStack.getDisplayName().length() > 12) {
                    player.addChatMessage(new ChatComponentTranslation("gui.item.signalRenamer.nameTooLong"));

                } else if (world.getTileEntity(x, y, z) instanceof TileSignal) {
                    TileSignal signal = (TileSignal) world.getTileEntity(x, y, z);
                    signal.setName(itemStack.getDisplayName());

                    SignalCraft.SCNet.sendToAll(new MessageNameUpdate(x, y, z, itemStack.getDisplayName()));

                    player.addChatMessage(new ChatComponentTranslation("gui.item.signalRenamer.renamed")
                            .appendText(" " + itemStack.getDisplayName()));

                } else {
                    player.addChatMessage(new ChatComponentTranslation("gui.item.signalRenamer.notValid"));
                    return false;
                }

            } else if (player.isSneaking() && world.getTileEntity(x, y, z) instanceof TileSignal) {
                itemStack.setStackDisplayName(((TileSignal) world.getTileEntity(x, y, z)).getName());
                player.addChatMessage(new ChatComponentTranslation("gui.item.signalRenamer.copied"));

            } else if (world.getTileEntity(x, y, z) instanceof TileSignal) {
                if (((TileSignal) world.getTileEntity(x, y, z)).getName().equals("Signal Name")) {
                    player.addChatMessage(new ChatComponentTranslation("gui.item.signalRenamer.rename"));
                }
            }
        }
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (world.isRemote) {
            if (Utils.isPlayerLookingAtAir(player, world) && player.isSneaking()) {
                SignalCraft.proxy.openRenamerGui(itemStack);
                return itemStack;
            }
        }
        return itemStack;
    }
}