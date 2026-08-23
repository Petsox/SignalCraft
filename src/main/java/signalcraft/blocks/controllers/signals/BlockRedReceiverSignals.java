package signalcraft.blocks.controllers.signals;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import signalcraft.SignalCraft;
import signalcraft.blocks.controllers.BlockReceiver;
import signalcraft.entities.controllers.TileReceiver;
import signalcraft.entities.controllers.signals.TileRedReceiverSignals;
import signalcraft.packet.SPacketEditorOpen;
import signalcraft.proxy.CommonProxy;

import java.util.LinkedList;
import java.util.List;

public class BlockRedReceiverSignals extends BlockReceiver {

    public BlockRedReceiverSignals(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabSignals);
    }
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int side, final int meta) {
        return Blocks.iron_bars.getBlockTextureFromSide(side);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileRedReceiverSignals();
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
        TileEntity tileEntity = iBlockAccess.getTileEntity(x, y, z);

        if (tileEntity instanceof TileRedReceiverSignals) {
            TileRedReceiverSignals receiver = (TileRedReceiverSignals) tileEntity;
            byte[] sides = receiver.getOutputtingSide();
            int index = 0;

            //find the index of the side that is outputting power
            for (int i = 0; i < sides.length; i++) {
                if (sides[i] == 1) {
                    index = i + 1;
                    break;
                }
            }

            // If the side that is outputting power matches the side being queried, return 15 (full power)
            if (index == (side - 1)) {
                return 15;
            }
        }
        return 0;
    }

    @Override
    public boolean openGui(World world, int x, int y, int z, EntityPlayer player) {
        if (world.isRemote) {
            return true;
        }
        final SPacketEditorOpen thePacket = new SPacketEditorOpen((TileReceiver) world.getTileEntity(x, y, z));
        try {
            final List<Object> list = new LinkedList<>();
            SignalCraft.proxy.packetPipeline.encode(thePacket, list);
            final FMLProxyPacket pkt = (FMLProxyPacket) list.get(0);
            SignalCraft.proxy.packetPipeline.sendTo(pkt, (EntityPlayerMP)player);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
