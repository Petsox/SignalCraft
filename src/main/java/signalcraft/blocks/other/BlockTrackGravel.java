package signalcraft.blocks.other;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import signalcraft.proxy.CommonProxy;

public class BlockTrackGravel extends Block
{
    public BlockTrackGravel(final String name) {
        super(Material.wood);
        this.setBlockName(name);
        this.setHardness(2.0f);
        this.setResistance(5.0f);
        this.setStepSound(Block.soundTypeGravel);
        this.setCreativeTab(CommonProxy.tabOther);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon("signalcraft:gravel");
    }

    public Item getItem(final World world, final int x, final int y, final int z) {
        return Item.getItemFromBlock(this);
    }
}
