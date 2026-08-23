package signalcraft.blocks.controllers.signals;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import signalcraft.SignalCraft;
import signalcraft.blocks.controllers.BlockController;
import signalcraft.entities.controllers.TileController;
import signalcraft.entities.controllers.signals.lightSignals.TileRedControllerLightSignals;
import signalcraft.packet.SPacketEditorOpen;
import signalcraft.proxy.CommonProxy;
import signalcraft.signalUtils.Consts;

import java.util.LinkedList;
import java.util.List;

public class BlockRedControllerLightSignals extends BlockController {

    public BlockRedControllerLightSignals(String name) {
        super(name);
        this.setCreativeTab(CommonProxy.tabSignals);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int side, final int meta) {
        return Blocks.iron_bars.getBlockTextureFromSide(side);
    }

    @Override
    public void onNeighborBlockChange(final World world, final int x, final int y, final int z, final Block theBlock) {
        // Grab the total indirect power at each neighboring block using ForgeDirection offsets
        final int southPower = world.getStrongestIndirectPower(x + ForgeDirection.SOUTH.offsetX, y, z + ForgeDirection.SOUTH.offsetZ);
        final int northPower = world.getStrongestIndirectPower(x + ForgeDirection.NORTH.offsetX, y, z + ForgeDirection.NORTH.offsetZ);
        final int eastPower  = world.getStrongestIndirectPower(x + ForgeDirection.EAST.offsetX,  y, z + ForgeDirection.EAST.offsetZ);
        final int westPower  = world.getStrongestIndirectPower(x + ForgeDirection.WEST.offsetX,  y, z + ForgeDirection.WEST.offsetZ);

        int totalPower = southPower + northPower + eastPower + westPower;

        TileRedControllerLightSignals controller = (TileRedControllerLightSignals) world.getTileEntity(x, y, z);

        // Ensure the TileEntity exists before updating to prevent unexpected NullPointerExceptions
        if (controller == null) return;

        if (totalPower == 0) {
            controller.setMostRestrictiveOnAll();
        } else if (northPower > 0) {
            controller.changeStateOnAllForSide(Consts.Side.NORTH.ordinal());
        } else if (eastPower > 0) {
            controller.changeStateOnAllForSide(Consts.Side.EAST.ordinal());
        } else if (westPower > 0) {
            controller.changeStateOnAllForSide(Consts.Side.WEST.ordinal());
        } else if (southPower > 0) {
            controller.changeStateOnAllForSide(Consts.Side.SOUTH.ordinal());
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileRedControllerLightSignals();
    }

    @Override
    public boolean openGui(World world, int x, int y, int z, EntityPlayer player) {
        if (world.isRemote) {
            return true;
        }
        final TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof TileController)) {
            // A foreign tile (e.g. RailCraft's TileHidden) can occupy this position; casting
            // it below would throw a ClassCastException that isn't caught by the try/catch.
            return true;
        }
        final SPacketEditorOpen thePacket = new SPacketEditorOpen((TileController) tileEntity);
        try {
            final List<Object> list = new LinkedList<>();
            SignalCraft.proxy.packetPipeline.encode(thePacket, list);
            final FMLProxyPacket pkt = (FMLProxyPacket) list.get(0);
            SignalCraft.proxy.packetPipeline.sendTo(pkt, (EntityPlayerMP) player);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }
}
