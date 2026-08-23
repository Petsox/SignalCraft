package signalcraft.ItemBlocks.lightSignals;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

import net.minecraft.world.World;
import signalcraft.ItemBlocks.SCItemBlock;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.SignalState;

public class ItemBlockLightSignal extends SCItemBlock {
    public ItemBlockLightSignal(Block block) {
        super(block);
        setHasSubtypes(false);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        boolean end = super.onItemUse(itemStack, entityPlayer, world, x, y, z, par7, par8, par9, par10);
        TileEntity tileE = world.getTileEntity(x, y, z);
        if (tileE instanceof TileLightSignal) {
            TileLightSignal tileSignal = (TileLightSignal) tileE;
            if (tileSignal.getGuiId().equals(Consts.GuiIDs.NOGUI)) {
                entityPlayer.addChatMessage(new ChatComponentTranslation("gui.lightsignal.validstates.text"));
                for (SignalState state : tileSignal.getValidStatesForTile()) {
                    if (state == SignalState.ALL || state == SignalState.ACTIVATE) continue;
                    entityPlayer.addChatMessage(new ChatComponentText(state.StateToString()));
                }
                tileSignal.setStateToMostRestrictive();
            }
        }
        return end;
    }
}
