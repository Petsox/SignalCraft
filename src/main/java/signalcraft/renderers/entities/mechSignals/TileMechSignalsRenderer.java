package signalcraft.renderers.entities.mechSignals;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.signals.mechSignals.TileMechSignal;
import signalcraft.models.mechSignals.IMechSignalModel;

public class TileMechSignalsRenderer extends TileEntitySpecialRenderer {
    private final IMechSignalModel modelSignal;

    public TileMechSignalsRenderer(IMechSignalModel model) {
        this.modelSignal = model;
    }

    @Override
    public void renderTileEntityAt(final TileEntity tileE, final double x, final double y, final double z, final float tick) {
        final TileMechSignal thisTileE = (TileMechSignal) tileE;
        int meta;
        if (tileE.getWorldObj() == null) {
            meta = 0;
        } else {
            final Block block = tileE.getBlockType();
            meta = tileE.getBlockMetadata();
            if (block != null && meta == 0) {
                meta = tileE.getBlockMetadata();
            }
        }
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);
        final float f2 = meta * 360 / 16.0f;
        GL11.glRotatef(-f2, 0.0f, 1.0f, 0.0f);

        this.modelSignal.renderStoz(thisTileE);

        GL11.glPushMatrix();
        this.modelSignal.renderRamena(thisTileE);
        GL11.glPopMatrix();

        GL11.glPopMatrix();
    }
}
