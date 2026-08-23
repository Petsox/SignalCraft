package signalcraft.blocks.levelCrossings;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import signalcraft.SignalCraft;
import signalcraft.blocks.ISCBlock;
import signalcraft.entities.TileSignal;
import signalcraft.entities.levelCrossings.TileLevelCrossing;
import signalcraft.messages.MessageActiveUpdate;
import signalcraft.packet.SPacketEditorOpen;

import java.util.LinkedList;
import java.util.List;

public abstract class BlockLevelCrossing extends BlockContainer implements ISCBlock {
    public BlockLevelCrossing(String name) {
        super(Material.iron);
        this.setBlockName(name);
        this.setHardness(4.0f);
        this.setResistance(10.0f);
        this.setBlockBounds(0.4375f, 0.0f, 0.4375f, 0.5625f, 1.0f, 0.5625f);
    }
    @Override
    public boolean openGui(World world, int x, int y, int z, EntityPlayer player) {
        if (world.isRemote) {
            return true;
        }
        final SPacketEditorOpen thePacket = new SPacketEditorOpen((TileSignal) world.getTileEntity(x, y, z));
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
    public void onNeighborBlockChange(final World world, final int x, final int y, final int z, final Block theBlock) {
        final TileEntity tileE = world.getTileEntity(x, y , z);
        if (tileE instanceof TileLevelCrossing) {
            ((TileLevelCrossing) tileE).setCrossingActive(world.isBlockIndirectlyGettingPowered(x, y, z));
            if (!world.isRemote) {
                SignalCraft.SCNet.sendToAll(new MessageActiveUpdate(x, y, z, world.isBlockIndirectlyGettingPowered(x, y, z)));
            }
        }
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
    public int getRenderType() {
        return -1;
    }
    public boolean isOpaqueCube() {
        return false;
    }
    public boolean renderAsNormalBlock() {
        return false;
    }
}
