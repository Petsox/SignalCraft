package signalcraft.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import signalcraft.SignalCraft;
import signalcraft.entities.TileSignal;
import signalcraft.proxy.CommonProxy;
import signalcraft.signalUtils.Utils;

import java.util.List;
import java.util.Locale;

/**
 * Scales blocks of the mod, works like the Signal Renamer: Shift + right click into the air opens a GUI to set the
 * scale kept in the item, a right click on a block applies it - limited to what that block allows (if it is out of
 * range, the nearest allowed value is set and the player told so), Shift + right click on a block copies its scale.
 */
public class ItemScaler extends ItemSword {
    private static final String TAG = "scale";

    public ItemScaler(String itemName) {
        super(ToolMaterial.GOLD);
        this.setNoRepair();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(itemName);
        this.setTextureName(SignalCraft.MOD_ID + ":scaler");
        this.setCreativeTab(CommonProxy.tabOther);
    }

    /** Whether the player holds a Scaler - blocks that act on a right click step aside for it. */
    public static boolean isHeldBy(EntityPlayer player) {
        ItemStack held = player.getHeldItem();
        return held != null && held.getItem() instanceof ItemScaler;
    }

    public static boolean hasScale(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().hasKey(TAG);
    }

    public static float getScale(ItemStack stack) {
        return hasScale(stack) ? stack.getTagCompound().getFloat(TAG) : 1f;
    }

    /** Ignores values that can't be a scale (not a number, zero or less). */
    public static void setScale(ItemStack stack, float scale) {
        if (Float.isNaN(scale) || Float.isInfinite(scale) || scale <= 0f) return;
        if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setFloat(TAG, scale);
    }

    /** "1.5", "0.75", "2" - for chat and tooltips. */
    public static String format(float scale) {
        String s = String.format(Locale.ROOT, "%.3f", scale);
        s = s.replaceAll("0+$", "");
        return s.endsWith(".") ? s.substring(0, s.length() - 1) : s;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
        list.add(EnumChatFormatting.YELLOW + String.valueOf(EnumChatFormatting.BOLD) + I18n.format("gui.scaler.use"));
        if (hasScale(stack)) {
            list.add(I18n.format("gui.scaler.value") + " " + EnumChatFormatting.AQUA + format(getScale(stack)));
        } else {
            list.add(EnumChatFormatting.GRAY + I18n.format("gui.scaler.noValue"));
        }
        list.add(I18n.format("gui.scaler.openGui"));
        list.add(I18n.format("gui.scaler.copy"));
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof TileSignal) || !((TileSignal) tileEntity).isScalable()) {
            if (!world.isRemote) player.addChatMessage(new ChatComponentTranslation("gui.item.scaler.notValid"));
            return false;
        }
        if (world.isRemote) return true;
        TileSignal tile = (TileSignal) tileEntity;

        if (player.isSneaking()) {
            setScale(stack, tile.getModelScale());
            player.inventoryContainer.detectAndSendChanges();
            player.addChatMessage(new ChatComponentTranslation("gui.item.scaler.copied", format(tile.getModelScale())));
            return true;
        }
        if (!hasScale(stack)) {
            player.addChatMessage(new ChatComponentTranslation("gui.item.scaler.noValue"));
            return true;
        }

        float wanted = getScale(stack);
        float applied = tile.clampModelScale(wanted);
        tile.setModelScale(applied);
        tile.markDirty();
        world.markBlockForUpdate(x, y, z);
        if (applied != wanted) {
            player.addChatMessage(new ChatComponentTranslation("gui.item.scaler.clamped", format(wanted),
                    format(tile.getMinModelScale()), format(tile.getMaxModelScale()), format(applied)));
        } else {
            player.addChatMessage(new ChatComponentTranslation("gui.item.scaler.scaled", format(applied)));
        }
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (world.isRemote && player.isSneaking() && Utils.isPlayerLookingAtAir(player, world)) {
            SignalCraft.proxy.openScalerGui(stack);
        }
        return stack;
    }
}
