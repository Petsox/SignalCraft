package signalcraft.renderers.entities;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.TileSignal;

/**
 * Wraps every tile renderer of the mod: scales the whole model by the tile's model scale (the Scaler item) about the
 * centre of the block's bottom, for the tiles whose own renderer doesn't scale it (TileSignal.isScaledByWrapper).
 * Everything else is passed straight to the wrapped renderer.
 */
public class ScaledTileRenderer extends TileEntitySpecialRenderer {
    private final TileEntitySpecialRenderer inner;

    public ScaledTileRenderer(TileEntitySpecialRenderer inner) {
        this.inner = inner;
    }

    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTicks) {
        float scale = 1f;
        if (tile instanceof TileSignal && ((TileSignal) tile).isScaledByWrapper()) {
            scale = ((TileSignal) tile).getModelScale();
        }
        if (scale == 1f) {
            this.inner.renderTileEntityAt(tile, x, y, z, partialTicks);
            return;
        }
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y, z + 0.5);
        GL11.glScalef(scale, scale, scale);
        GL11.glTranslated(-(x + 0.5), -y, -(z + 0.5));
        this.inner.renderTileEntityAt(tile, x, y, z, partialTicks);
        GL11.glPopMatrix();
    }

    /** The wrapped renderer binds its textures through the dispatcher - hand it over. */
    @Override
    public void func_147497_a(TileEntityRendererDispatcher dispatcher) {
        super.func_147497_a(dispatcher);
        this.inner.func_147497_a(dispatcher);
    }

    @Override
    public void func_147496_a(World world) {
        this.inner.func_147496_a(world);
    }
}
