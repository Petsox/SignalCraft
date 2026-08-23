package signalcraft.ItemBlocks.controllers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import signalcraft.ItemBlocks.SCItemBlock;

import java.util.List;

public class ItemBlockLightSignalsRedController extends SCItemBlock
{
    public ItemBlockLightSignalsRedController(Block block) {
        super(block);
        setHasSubtypes(false);
    }
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        list.add(EnumChatFormatting.BOLD + I18n.format("gui.item.lightSignalRedCont.use"));
        list.add(I18n.format("gui.item.redstoneActivated"));
        list.add(EnumChatFormatting.RED + I18n.format("gui.item.pairWith") + " " + I18n.format("tile.LightRece.name") + " " + I18n.format("gui.item.anyKind"));
    }
}
