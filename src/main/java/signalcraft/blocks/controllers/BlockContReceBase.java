package signalcraft.blocks.controllers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import signalcraft.blocks.ISCBlock;
import signalcraft.entities.controllers.TileContReceBase;
import signalcraft.entities.controllers.TileController;

public class BlockContReceBase extends BlockContainer implements ISCBlock {
    public BlockContReceBase(String name) {
        super(Material.iron);
        this.setBlockName(name);
        this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 1F, 0.9F);
    }

    @Override
    public boolean openGui(World world, int x, int y, int z, EntityPlayer player) {
        return false;
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
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileContReceBase();
    }
}
