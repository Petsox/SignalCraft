package signalcraft.blocks.gsar.signalsSO;

import signalcraft.proxy.CommonProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import signalcraft.blocks.signals.signSignals.BlockSignSignal;
import signalcraft.entities.gsar.signalsSO.TileGSARStationSign;


public class BlockGSARStationSign extends BlockSignSignal {
    private final boolean stationSignsStand;

    public BlockGSARStationSign(String name, final boolean stationSignsIsStand) {
        super(name);
        this.stationSignsStand = stationSignsIsStand;
        this.setCreativeTab(CommonProxy.tabGSAR);
    }

    @Override
    public void setBlockBoundsBasedOnState(final IBlockAccess blockAccess, final int x, final int y, final int z) {
        final int l = blockAccess.getBlockMetadata(x, y, z);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        if (this.stationSignsStand) {
            if (l == 2) {
                this.setBlockBounds(0.0f, 0.0f, 0.95f, 1.0f, 1.0f, 1.0f);
            }
            if (l == 3) {
                this.setBlockBounds(0.0f, 0.0f, 0.05f, 1.0f, 1.0f, 0.0f);
            }
            if (l == 4) {
                this.setBlockBounds(0.95f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            }
            if (l == 5) {
                this.setBlockBounds(0.05f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f);
            }
        }
        else {
            if (l == 0) {
                this.setBlockBounds(0.0f, 0.0f, 0.45f, 1.0f, 1.0f, 0.55f);
            }
            if (l == 1) {
                this.setBlockBounds(0.45f, 0.0f, 0.0f, 0.55f, 1.0f, 1.0f);
            }
            if (l == 2) {
                this.setBlockBounds(0.0f, 0.0f, 0.45f, 1.0f, 1.0f, 0.55f);
            }
            if (l == 3) {
                this.setBlockBounds(0.45f, 0.0f, 0.0f, 0.55f, 1.0f, 1.0f);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(final World world, final int x, final int y, final int z) {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    public void registerBlockIcons(final IIconRegister Icon) {
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int side, final int meta) {
        return Blocks.iron_bars.getBlockTextureFromSide(side);
    }

    @Override
    public TileEntity createNewTileEntity(final World world, final int par2) {
        return new TileGSARStationSign();
    }

    @Override
    public boolean getBlocksMovement(final IBlockAccess blockAccess, final int x, final int y, final int z) {
        return true;
    }
}
