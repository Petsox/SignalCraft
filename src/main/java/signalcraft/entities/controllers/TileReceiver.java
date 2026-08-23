package signalcraft.entities.controllers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import signalcraft.SignalCraft;
import signalcraft.entities.TileSignal;
import signalcraft.messages.MessagePairingsUpdate;
import signalcraft.signalUtils.BlockPos;
import signalcraft.signalUtils.Utils;

import java.util.HashMap;
import java.util.Map;

public abstract class TileReceiver extends TileContReceBase implements IContReceBase  {

    public ResourceLocation texture;
    protected final Map<BlockPos, Integer> pairings = new HashMap<>();

    public TileReceiver(ResourceLocation texture) {
        super(texture);
        this.setName("Receiver");
    }

    public TileReceiver() {
    }

    public Map<BlockPos, Integer> getPairings() {
        return pairings;
    }

    public void addPairing(BlockPos controllerPos, int controllerID) {
        if (worldObj == null || worldObj.isRemote) return;
        if (controllerPos == null) return;

        TileEntity controller = worldObj.getTileEntity(controllerPos.getX(), controllerPos.getY(), controllerPos.getZ());

        if (!(controller instanceof TileController)) return;

        pairings.put(controllerPos, controllerID);

        SignalCraft.SCNet.sendToAll(new MessagePairingsUpdate(controllerPos.getX(), controllerPos.getY(), controllerPos.getZ(), xCoord, yCoord, zCoord, true, controllerID));
    }

    public void unPairFromAllControllers() {
        for (BlockPos pos : pairings.keySet()) {
            TileEntity tileE = worldObj.getTileEntity(pos.getX(), pos.getY(), pos.getZ());
            if (tileE instanceof TileController) {
                ((TileController) tileE).removePairingByID(pairings.get(pos));
            }
        }
        pairings.clear();
    }
    public void listControllers(EntityPlayer player) {
        Utils.addLocalizedChatMessage(player, "message.wrench.receiverPairedTo");
        for (TileController controller : this.getControllers()) {
            if (controller != null){
                Utils.addChatMessage(player, new ChatComponentText(controller.getName() + " ")
                        .appendSibling(new ChatComponentTranslation("message.at.coords"))
                        .appendText(" X: " + controller.xCoord + ", Y: " + controller.yCoord + ", Z: " + controller.zCoord));
            }
        }
    }

    public TileController getController(BlockPos pos) {
        TileEntity tileE = worldObj.getTileEntity(pos.getX(), pos.getY(), pos.getZ());
        if (tileE instanceof TileController) {
            return (TileController) tileE;
        }
        return null;
    }

    public TileController getController(int receiverID) {
        for (BlockPos pos : pairings.keySet()) {
            if (pairings.get(pos) == receiverID) {
                return getController(pos);
            }
        }
        return null;
    }

    public TileController[] getControllers() {
        TileController[] controllers = new TileController[pairings.size()];
        int i = 0;
        for (BlockPos pos : pairings.keySet()) {
            controllers[i++] = getController(pos);
        }
        return controllers;
    }

    public int getFirstControllerID() {
        if (pairings.isEmpty()) return -1;
        return pairings.values().iterator().next();
    }

    public void removePairingByID(int id) {
        BlockPos toRemove = null;
        for (Map.Entry<BlockPos, Integer> entry : pairings.entrySet()) {
            if (entry.getValue() == id) {
                toRemove = entry.getKey();
                break;
            }
        }
        if (toRemove != null) {
            pairings.remove(toRemove);
            if (!worldObj.isRemote) {
                SignalCraft.SCNet.sendToAll(new MessagePairingsUpdate(toRemove.getX(), toRemove.getY(), toRemove.getZ(), xCoord, yCoord, zCoord, false, id));
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        NBTTagList list = new NBTTagList();

        for (Map.Entry<BlockPos, Integer> entry : pairings.entrySet()) {
            BlockPos pos = entry.getKey();
            int id = entry.getValue();

            NBTTagCompound e = new NBTTagCompound();
            e.setInteger("x", pos.getX());
            e.setInteger("y", pos.getY());
            e.setInteger("z", pos.getZ());
            e.setInteger("id", id);

            list.appendTag(e);
        }

        tag.setTag("controllers", list);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        pairings.clear();

        if (!tag.hasKey("controllers")) return;

        NBTTagList list = tag.getTagList("controllers", 10);

        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound e = list.getCompoundTagAt(i);

            int x = e.getInteger("x");
            int y = e.getInteger("y");
            int z = e.getInteger("z");
            int id = e.getInteger("id");

            pairings.put(new BlockPos(x, y, z), id);
        }
    }
}
