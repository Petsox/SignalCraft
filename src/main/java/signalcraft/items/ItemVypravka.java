package signalcraft.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import signalcraft.proxy.CommonProxy;

import java.util.List;

public class ItemVypravka extends ItemSword {

    public ItemVypravka(final String itemName) {
        super(ToolMaterial.GOLD);
        this.setNoRepair();
        this.setUnlocalizedName(itemName);
        this.maxStackSize = 1;
        this.setTextureName("signalcraft:vypravka");
        this.setCreativeTab(CommonProxy.tabOther);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(EnumChatFormatting.DARK_PURPLE + String.valueOf(EnumChatFormatting.ITALIC) + "Texture by: seba_456");

    }
}
