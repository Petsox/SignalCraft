package signalcraft.renderers.entities.gsar;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsSH.TileGSARSignSignalSH2;
import signalcraft.models.gsar.signalsSH.ModelGSARSignalSH2;

public class TileSignalSH2RendererGSAR extends TileEntitySpecialRenderer {
    private final ModelGSARSignalSH2 modelSignalSH2;

    public TileSignalSH2RendererGSAR(ModelGSARSignalSH2 model) {
        this.modelSignalSH2 = model;
    }

    public void renderTileEntityAt(final TileEntity tileE, final double x, final double y, final double z, final float tick) {
        final Block block = tileE.getBlockType();
        final TileGSARSignSignalSH2 tileESignalSH2 = (TileGSARSignSignalSH2) tileE;
        int meta;
        if (tileE.getWorldObj() == null) {
            meta = 0;
        } else {
            meta = tileE.getBlockMetadata();
            if (block != null && meta == 0) {
                meta = tileE.getBlockMetadata();
            }
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y + 0.0f, (float) z + 0.5f);
        final float f2 = meta * 360 / 16.0f;
        GL11.glRotatef(-f2, 0.0f, 1.0f, 0.0f);

        this.modelSignalSH2.renderStoz(tileESignalSH2);

        GL11.glPopMatrix();
    }
}
