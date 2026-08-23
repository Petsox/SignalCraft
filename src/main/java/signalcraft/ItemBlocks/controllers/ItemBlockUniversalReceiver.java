package signalcraft.ItemBlocks.controllers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import signalcraft.ItemBlocks.SCItemBlock;

import java.util.List;

public class ItemBlockUniversalReceiver extends SCItemBlock
{
    public ItemBlockUniversalReceiver(Block block) {
        super(block);
        setHasSubtypes(false);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        list.add(EnumChatFormatting.BOLD + I18n.format("gui.item.univRece.use1"));
        list.add(EnumChatFormatting.BOLD + I18n.format("gui.item.univRece.use2"));
        list.add(I18n.format("gui.item.controlsTiles"));
        list.add(I18n.format("gui.item.redstoneActivated"));
        list.add(EnumChatFormatting.RED + I18n.format("gui.item.pairWith") + " " + I18n.format("tile.UniversalCont.name") + " " + I18n.format("gui.item.anyKind"));
    }
}
