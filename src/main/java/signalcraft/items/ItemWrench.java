package signalcraft.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;
import signalcraft.SignalCraft;
import signalcraft.blocks.ISCBlock;
import signalcraft.entities.controllers.TileController;
import signalcraft.entities.controllers.TileReceiver;
import signalcraft.entities.levelCrossings.IAnglesAddable;
import signalcraft.messages.WrenchModeMessage;
import signalcraft.messages.WrenchOwnerMessage;
import signalcraft.proxy.CommonProxy;
import signalcraft.signalUtils.Utils;

import java.util.Arrays;
import java.util.List;


public class ItemWrench extends ItemSword {
    private IIcon defaultIcon;
    private IIcon specialIcon;

    public ItemWrench(final String itemName) {
        super(ToolMaterial.GOLD);
        this.setNoRepair();
        this.setUnlocalizedName(itemName);
        this.maxStackSize = 1;
        this.setCreativeTab(CommonProxy.tabOther);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(EnumChatFormatting.YELLOW + String.valueOf(EnumChatFormatting.BOLD) + I18n.format("gui.item.wrench.use"));
        list.add(EnumChatFormatting.GOLD + String.valueOf(EnumChatFormatting.BOLD) + I18n.format("gui.item.wrench.mode") + " " + getMode(stack));
        list.add(EnumChatFormatting.DARK_PURPLE + String.valueOf(EnumChatFormatting.ITALIC) + "This ain't no Hammer it's a Wrench");
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.defaultIcon = iconRegister.registerIcon("signalcraft:wrench");
        this.specialIcon = iconRegister.registerIcon("signalcraft:wrench_dev");
    }

    @Override
    public IIcon getIconIndex(ItemStack stack) {
        return getIcon(stack, 0);
    }

    // Mode lives on the ItemStack's own NBT, not on this (per-item-type singleton, shared across
    // all players/sides on a dedicated server) instance — a mutable instance field here previously
    // let the client and server disagree about the active mode, causing a single right-click to be
    // handled as two different actions (see wrench mode/GUI mismatch bug).
    private String getMode(ItemStack stack) {
        if (!stack.hasTagCompound() || !stack.getTagCompound().hasKey("Mode")) {
            return "Adding";
        }
        return stack.getTagCompound().getString("Mode");
    }

    private void toggleMode(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        String newMode = getMode(stack).equals("Adding") ? "Editing" : "Adding";
        stack.getTagCompound().setString("Mode", newMode);
    }

    @Override
    public IIcon getIcon(ItemStack stack, int renderPass) {
        if (stack.hasTagCompound()) {
            NBTTagCompound tag = stack.getTagCompound();
            if (tag.hasKey("Owner")) {
                String owner = tag.getString("Owner");
                if (Arrays.asList(SignalCraft.Devs).contains(owner)) {
                    return this.specialIcon;
                }
            }
        }
        return this.defaultIcon;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound tag = stack.getTagCompound();
        tag.setString("Owner", player.getDisplayName());

        if (world.isRemote) {
            SignalCraft.SCNet.sendToServer(new WrenchOwnerMessage(stack, player.getDisplayName()));
        }

        if (!world.isRemote && Utils.isPlayerLookingAtAir(player, world) && player.isSneaking()) {
            toggleMode(stack);
            player.addChatMessage(new ChatComponentTranslation("gui.item.wrench.changedMode")
                    .setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD))
                    .appendText(" " + getMode(stack)));
            return stack;
        } else if (world.isRemote) {
            SignalCraft.SCNet.sendToServer(new WrenchModeMessage(stack));
        }
        super.onItemRightClick(stack, world, player);
        return stack;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        TileEntity tileE = world.getTileEntity(x, y, z);
        Block blockE = world.getBlock(x, y, z);

        String mode = getMode(itemStack);
        if (mode.equals("Adding")) {
            if (tileE instanceof IAnglesAddable) {
                if (player.isSneaking()) {
                    ((IAnglesAddable) tileE).removeLastAngle();
                    return true;
                } else {
                    int angle = (int) player.rotationYaw;
                    ((IAnglesAddable) tileE).addAngle(-angle + 90);
                    return true;
                }
            }
        } else if (mode.equals("Editing")) {
            if (tileE instanceof TileReceiver && player.isSneaking()) {
                if (!world.isRemote) ((TileReceiver) tileE).listControllers(player);
                return true;
            } else if (tileE instanceof TileController && player.isSneaking()) {
                if (!world.isRemote) ((TileController) tileE).listReceivers(player);
                return true;
            } else if (blockE instanceof ISCBlock) {
                ((ISCBlock) blockE).openGui(world, x, y, z, player);
                return true;
            }
        }
        return false;
    }
}
