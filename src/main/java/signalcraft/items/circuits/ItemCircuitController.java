package signalcraft.items.circuits;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import signalcraft.SignalCraft;
import signalcraft.proxy.CommonProxy;

import java.util.List;

public class ItemCircuitController extends Item {

    public ItemCircuitController(final String itemName) {
        this.setUnlocalizedName(itemName);
        this.setTextureName(SignalCraft.MOD_ID + ":circuitController");
        this.setCreativeTab(CommonProxy.tabOther);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(I18n.format("gui.general.text.crafting"));
    }
}
