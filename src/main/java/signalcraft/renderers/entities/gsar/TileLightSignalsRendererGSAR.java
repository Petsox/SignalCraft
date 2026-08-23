package signalcraft.renderers.entities.gsar;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsHP.TileGSARLightSignal;
import signalcraft.models.gsar.ILightSignalModelGSAR;
import signalcraft.signalUtils.SignalState;

public class TileLightSignalsRendererGSAR extends TileEntitySpecialRenderer {
    private final ILightSignalModelGSAR modelSignal;

    public TileLightSignalsRendererGSAR(ILightSignalModelGSAR model) {
        this.modelSignal = model;
    }

    public void renderTileEntityAt(final TileEntity tileE, final double x, final double y, final double z, final float tick) {
        final TileGSARLightSignal thisTileE = (TileGSARLightSignal) tileE;
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

        SignalState state = thisTileE.getState();

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y + 3.0f, (float) z + 0.5f);
        final float f2 = meta * 360 / 16.0f;
        GL11.glRotatef(-f2, 0.0f, 1.0f, 0.0f);

        this.modelSignal.renderStoz(thisTileE);
        this.modelSignal.renderNavestidlo(thisTileE);

        final int i1 = 15728880;
        final int j1 = i1 % 65536;
        final int k1 = i1 / 65536;
        GL11.glPushMatrix();
        GL11.glColor4f(2.0f, 2.0f, 2.0f, 2.0f);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j1, k1);
        this.modelSignal.renderNavest(state, thisTileE);
        GL11.glPopMatrix();
        GL11.glPopMatrix();

    }
}
