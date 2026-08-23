package signalcraft.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import signalcraft.entities.TileSignal;
import signalcraft.entities.controllers.TileController;
import signalcraft.entities.controllers.TileReceiver;
import signalcraft.proxy.CommonProxy;
import signalcraft.signalUtils.BlockPos;
import signalcraft.signalUtils.Network;

import java.util.List;


public class ItemSignalBonder extends Item {
    private int Cx;
    private int Cy;
    private int Cz;

    public ItemSignalBonder(String itemName) {
        this.setMaxStackSize(1);
        this.setUnlocalizedName(itemName);
        this.setTextureName("signalcraft:signalBonder");
        this.setCreativeTab(CommonProxy.tabOther);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        list.add(getBoundedTile(player, itemStack));
        list.add(EnumChatFormatting.YELLOW + I18n.format("gui.item.signalBonder.use"));
        if (GuiScreen.isShiftKeyDown()) {
            list.add(EnumChatFormatting.DARK_PURPLE + String.valueOf(EnumChatFormatting.ITALIC) + I18n.format("gui.signalBonder.Bender"));
        }
    }

    private String getBoundedTile(EntityPlayer player, ItemStack itemStack) {
        NBTTagCompound data = itemStack.getTagCompound();
        if (data != null && data.hasKey("controllerX") && data.hasKey("controllerY") && data.hasKey("controllerZ")) {
            Cx = data.getInteger("controllerX");
            Cy = data.getInteger("controllerY");
            Cz = data.getInteger("controllerZ");
            TileSignal tile = (TileSignal) player.worldObj.getTileEntity(Cx, Cy, Cz);
            if (tile == null) {
                return EnumChatFormatting.YELLOW + String.valueOf(EnumChatFormatting.ITALIC) + I18n.format("gui.item.signalBonder.nullTile");
            }
            return EnumChatFormatting.GREEN + I18n.format("gui.item.signalBonder.Bonded") + " " + tile.getName() + " " + I18n.format("gui.item.signalBonder.atCoords")+ " [" + Cx + ", " + Cy + ", " + Cz + "]";
        }
        return EnumChatFormatting.YELLOW + String.valueOf(EnumChatFormatting.ITALIC) + I18n.format("gui.item.signalBonder.Binding");
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (!world.isRemote) {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            if (tileEntity instanceof TileController && player.isSneaking()) {
                TileController controller = (TileController) tileEntity;
                BlockPos pos = controller.getPosition();

                NBTTagCompound data = new NBTTagCompound();
                data.setInteger("controllerX", pos.getX());
                data.setInteger("controllerY", pos.getY());
                data.setInteger("controllerZ", pos.getZ());
                player.addChatMessage(new ChatComponentTranslation("gui.item.signalBonder.BondedToController")
                        .appendText(" " + controller.getName() + " at [" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + "]"));
                itemStack.setTagCompound(data);

            } else if (tileEntity instanceof TileReceiver && !player.isSneaking()) {
                NBTTagCompound data = itemStack.getTagCompound();
                if (data == null) {
                    player.addChatMessage(new ChatComponentTranslation("gui.item.signalBonder.NotBonded"));
                    player.addChatMessage(new ChatComponentTranslation("gui.item.signalBonder.Binding"));
                    return true;
                }
                Cx = data.getInteger("controllerX");
                Cy = data.getInteger("controllerY");
                Cz = data.getInteger("controllerZ");
                TileEntity boundController = world.getTileEntity(Cx, Cy, Cz);
                TileReceiver receiver = (TileReceiver) tileEntity;
                if (boundController instanceof TileController) {
                    TileController controller = (TileController) boundController;

                    if (!receiver.isControllerValid(controller)) {
                        player.addChatMessage(new ChatComponentTranslation("gui.item.signalBonder.ControllerAtCoords")
                                .appendText(" [" + Cx + ", " + Cy + ", " + Cz + "] ")
                                .appendSibling(new ChatComponentTranslation("gui.item.signalBonder.ControllerNotValid")));
                        return true;
                    }

                    controller.addPairing(new int[]{x, y, z});
                    Network.updateControllers(controller);

                    player.addChatMessage(new ChatComponentTranslation("gui.item.signalBonder.ReceiverPaired")
                            .appendText(" " + receiver.getName() + " ")
                            .appendSibling(new ChatComponentTranslation("gui.item.signalBonder.atCoords"))
                            .appendText(" [" + x + ", " + y + ", " + z + "] ")
                            .appendSibling(new ChatComponentTranslation("gui.item.signalBonder.ToController"))
                            .appendText(" " + controller.getName() + " ")
                            .appendSibling(new ChatComponentTranslation("gui.item.signalBonder.atCoords"))
                            .appendText(" [" + Cx + ", " + Cy + ", " + Cz + "]"));
                } else {
                    player.addChatMessage(new ChatComponentTranslation("gui.item.signalBonder.NotBonded"));
                    player.addChatMessage(new ChatComponentTranslation("gui.item.signalBonder.Binding"));
                }
            } else {
                NBTTagCompound data = new NBTTagCompound();
                itemStack.setTagCompound(data);
                player.addChatMessage(new ChatComponentTranslation("gui.item.signalBonder.Cleared"));
                player.addChatMessage(new ChatComponentTranslation("gui.item.signalBonder.Binding"));
                player.addChatMessage(new ChatComponentTranslation("gui.item.signalBonder.Pairing"));
            }
        }
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }
        return true;
    }
}
