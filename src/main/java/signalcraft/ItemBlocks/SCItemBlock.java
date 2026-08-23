package signalcraft.ItemBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import signalcraft.blocks.ISCBlock;
import signalcraft.entities.signals.ISignal;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.SignalState;

import java.util.List;

public class SCItemBlock extends ItemBlock
{
    public SCItemBlock(Block block) {
        super(block);
        setHasSubtypes(false);
    }
    public boolean onItemUse(final ItemStack itemStack, final EntityPlayer entityPlayer, final World world, int x, int y, int z, final int par7, final float par8, final float par9, final float par10) {
        if (par7 == 0) {
            return false;
        }
        if (!world.getBlock(x, y, z).getMaterial().isSolid()) {
            return false;
        }
        if (par7 == 1) {
            ++y;
        }
        if (par7 == 2) {
            --z;
        }
        if (par7 == 3) {
            ++z;
        }
        if (par7 == 4) {
            --x;
        }
        if (par7 == 5) {
            ++x;
        }
        if (!entityPlayer.canPlayerEdit(x, y, z, par7, itemStack)) {
            return false;
        }
        if (!Block.getBlockFromItem(entityPlayer.getHeldItem().getItem()).canPlaceBlockAt(world, x, y, z)) {
            return false;
        }
        if (world.isRemote) {
            return true;
        }
        final int i1 = MathHelper.floor_double((entityPlayer.rotationYaw + 180.0f) * 16.0f / 360.0f + 0.5) & 0xF;
        world.setBlock(x, y, z, Block.getBlockFromItem(entityPlayer.getHeldItem().getItem()), i1, 3);
        ((ISCBlock)world.getBlock(x,y,z)).openGui(world,x,y,z,entityPlayer);
        TileEntity tileE = world.getTileEntity(x, y, z);
        if (tileE instanceof ISignal) {
            if (((ISignal) tileE).getGuiId().equals(Consts.GuiIDs.NOGUI)) ((ISignal) tileE).setStateToMostRestrictive();
        }
        --itemStack.stackSize;
        return true;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean advanced) {
        if (!(this.field_150939_a instanceof BlockContainer)) {
            return;
        }
        final TileEntity tileE = ((BlockContainer) this.field_150939_a).createNewTileEntity(null, itemStack.getItemDamage());
        if (!(tileE instanceof ISignal)) {
            return;
        }
        if (!((ISignal) tileE).getGuiId().equals(Consts.GuiIDs.NOGUI)) {
            return;
        }
        final SignalState[] states = ((ISignal) tileE).getValidStatesForTile();
        boolean hasStates = false;
        for (SignalState state : states) {
            if (state != SignalState.ALL) {
                hasStates = true;
                break;
            }
        }
        if (!hasStates) {
            return;
        }
        if (!GuiScreen.isShiftKeyDown()) {
            list.add(I18n.format("gui.lightsignal.validstates.hint"));
            return;
        }
        list.add(I18n.format("gui.lightsignal.validstates.text"));
        for (SignalState state : states) {
            if (state == SignalState.ALL) {
                continue;
            }
            list.add(state.StateToString());
        }
    }
}
