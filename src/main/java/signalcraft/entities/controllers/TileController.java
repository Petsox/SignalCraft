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
import signalcraft.messages.MessagePairingsUpdate;
import signalcraft.signalUtils.BlockPos; // your custom 1.7.10 value object
import signalcraft.signalUtils.Network;
import signalcraft.signalUtils.Utils;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class TileController extends TileContReceBase {

    // Safe value-keyed pairing map
    private final Map<BlockPos, Integer> pairings = new HashMap<>();

    public ResourceLocation texture;

    public TileController(ResourceLocation texture) {
        super(texture);
        this.texture = texture;
        this.setName("Controller");
    }

    public Map<BlockPos, Integer> getPairings() {
        return pairings;
    }

    public void unPairFromAllReceivers() {
        for (BlockPos pos : pairings.keySet()) {
            TileEntity tileE = worldObj.getTileEntity(pos.getX(), pos.getY(), pos.getZ());
            if (tileE instanceof TileReceiver) {
                ((TileReceiver) tileE).removePairingByID(pairings.get(pos));
            }
        }
        pairings.clear();
    }

    public void addPairing(int[] receiverCoordinates) {
        if (worldObj == null || worldObj.isRemote) return;
        if (receiverCoordinates == null || receiverCoordinates.length != 3) return;

        BlockPos pos = new BlockPos(receiverCoordinates[0], receiverCoordinates[1], receiverCoordinates[2]);

        // prevent duplicates
        if (pairings.containsKey(pos)) return;

        TileEntity receiver = worldObj.getTileEntity(pos.getX(), pos.getY(), pos.getZ());

        if (!(receiver instanceof TileReceiver)) return;

        int id = generateNextID();

        pairings.put(pos, id);

        ((TileReceiver) receiver).addPairing(this.getPosition(), id);
        Network.updateControllers((TileReceiver)receiver);

        SignalCraft.SCNet.sendToAll(new MessagePairingsUpdate(pos.getX(), pos.getY(), pos.getZ(), xCoord, yCoord, zCoord, true, id));
    }

    private int generateNextID() {
        int max = 0;
        for (int id : pairings.values()) {
            if (id > max) max = id;
        }
        return max + 1;
    }

    public void removePairing(BlockPos pos) {
        if (pos == null) return;

        Integer id = pairings.remove(pos);

        if (id != null && !worldObj.isRemote) {
            SignalCraft.SCNet.sendToAll(new MessagePairingsUpdate(pos.getX(), pos.getY(), pos.getZ(), xCoord, yCoord, zCoord, false, id));
        }
    }

    public void removePairingByID(int receiverID) {
        Iterator<Map.Entry<BlockPos, Integer>> it = pairings.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<BlockPos, Integer> e = it.next();
            if (e.getValue() == receiverID) {
                BlockPos pos = e.getKey();
                it.remove();

                if (!worldObj.isRemote) {
                    SignalCraft.SCNet.sendToAll(new MessagePairingsUpdate(pos.getX(), pos.getY(), pos.getZ(), xCoord, yCoord, zCoord, false, receiverID));
                }
                return;
            }
        }
    }

    public TileReceiver getReceiverByID(int id) {
        for (Map.Entry<BlockPos, Integer> entry : pairings.entrySet()) {
            if (entry.getValue() == id) {
                BlockPos pos = entry.getKey();
                TileEntity tile = worldObj.getTileEntity(pos.getX(), pos.getY(), pos.getZ());
                if (tile instanceof TileReceiver) {
                    return (TileReceiver) tile;
                }
            }
        }
        return null;
    }

    public TileReceiver[] getReceivers() {
        TileReceiver[] receivers = new TileReceiver[pairings.size()];
        int i = 0;
        for (BlockPos pos : pairings.keySet()) {
            TileEntity tileE = worldObj.getTileEntity(pos.getX(), pos.getY(), pos.getZ());
            if (tileE instanceof TileReceiver) {
                receivers[i++] = (TileReceiver) tileE;
            }
        }
        return receivers;
    }

    public TileReceiver getReceiverByName(String Name){
        for (BlockPos pos : pairings.keySet()) {
            TileEntity tileE = worldObj.getTileEntity(pos.getX(), pos.getY(), pos.getZ());
            if (tileE instanceof TileReceiver) {
                if (((TileReceiver) tileE).getName().equals(Name)) return (TileReceiver) tileE;
            }
        }
        return null;
    }

    public void listReceivers(EntityPlayer player) {
        Utils.addLocalizedChatMessage(player, "message.wrench.controllerPairedTo");
        for (TileReceiver receiver : this.getReceivers()) {
            if (receiver != null){
                Utils.addChatMessage(player, new ChatComponentText(receiver.getName() + " ")
                        .appendSibling(new ChatComponentTranslation("message.at.coords"))
                        .appendText(" X: " + receiver.xCoord + ", Y: " + receiver.yCoord + ", Z: " + receiver.zCoord));
            }
        }
    }

    public BlockPos getPosition() {
        return new BlockPos(xCoord, yCoord, zCoord);
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

        tag.setTag("receivers", list);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        pairings.clear();

        if (!tag.hasKey("receivers")) return;

        NBTTagList list = tag.getTagList("receivers", 10);

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
