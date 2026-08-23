package signalcraft.blocks.switches;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import signalcraft.SignalCraft;
import signalcraft.blocks.ISCBlock;
import signalcraft.entities.controllers.TileController;
import signalcraft.entities.switches.TileSwitch;
import signalcraft.packet.SPacketEditorOpen;

import java.util.LinkedList;
import java.util.List;

public abstract class BlockSwitch extends BlockContainer implements ISCBlock {

    public BlockSwitch(String name) {
        super(Material.iron);
        this.setBlockName(name);
        this.setHardness(2.0f);
        this.setResistance(5.0f);
        this.setBlockBounds(0.5f, 0.0f, 0.0f, 1.0f, 0.8125f, 1.0f);
    }
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int side, final int meta) {
        return Blocks.iron_block.getBlockTextureFromSide(side);
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon("iron_block");
    }
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(final World world, final int x, final int y, final int z) {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public boolean getBlocksMovement(final IBlockAccess blockAccess, final int x, final int y, final int z) {
        return true;
    }

    @Override
    public boolean openGui(World world, int x, int y, int z, EntityPlayer player) {
        if (world.isRemote) {
            return true;
        }
        final TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof TileSwitch)) {
            // A foreign tile (e.g. RailCraft's TileHidden) can occupy this position; casting
            // it below would throw a ClassCastException that isn't caught by the try/catch.
            return true;
        }
        final SPacketEditorOpen thePacket = new SPacketEditorOpen((TileSwitch) tileEntity);
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

    @Override
    public int isProvidingWeakPower(final IBlockAccess world, final int x, final int y, final int z, final int meta) {
        final TileEntity tileE = world.getTileEntity(x, y, z);
        final boolean leverActivated = tileE != null && ((TileSwitch) tileE).getIsSwitched();
        return leverActivated ? 15 : 0;
    }

    @Override
    public int isProvidingStrongPower(final IBlockAccess world, final int x, final int y, final int z, final int meta) {
        return this.isProvidingWeakPower(world, x, y, z, meta);
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }

}
