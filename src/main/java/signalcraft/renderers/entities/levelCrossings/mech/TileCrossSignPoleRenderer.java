package signalcraft.renderers.entities.levelCrossings.mech;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.levelCrossings.mech.TileCrossSignPole;
import signalcraft.models.levelCrossing.mech.ModelCrossSignPole;
import signalcraft.models.levelCrossing.mech.ModelMechBarrier;

public class TileCrossSignPoleRenderer extends TileEntitySpecialRenderer {
    private final ModelCrossSignPole model;

    public TileCrossSignPoleRenderer(ModelCrossSignPole model) {
        this.model = model;
    }

    @Override
    public void renderTileEntityAt(final TileEntity tileE, final double x, final double y, final double z, final float tick) {
        final TileCrossSignPole tile = (TileCrossSignPole) tileE;
        final int meta = tileE.getWorldObj() == null ? 0 : tileE.getBlockMetadata();
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);
        GL11.glRotatef(-meta * 360 / 16.0f, 0.0f, 1.0f, 0.0f);
        GL11.glScalef(ModelMechBarrier.RENDER_SCALE, ModelMechBarrier.RENDER_SCALE, ModelMechBarrier.RENDER_SCALE);
        GL11.glTranslatef(-ModelCrossSignPole.POLE_X, 0f, -ModelCrossSignPole.POLE_Z);   // pole in the block centre
        model.render(tile.hasSign(), false, tile.hasCross(), tile.isCrossOneTrack(), tile.isCrossReflective(),
                tile.isCrossLarge(), tile.isCrossSlovak(), false, ModelCrossSignPole.STANDALONE_RAISE,
                ModelCrossSignPole.STANDALONE_POLE_WIDTH, tile.hasStop(),
                tile.hasStripes());
        GL11.glPopMatrix();
    }
}
