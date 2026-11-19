package signalcraft.renderers.entities.levelCrossings.mech;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.levelCrossings.mech.TileMechBarrier;
import signalcraft.models.levelCrossing.mech.ModelMechBarrier;

public class TileMechBarrierRenderer extends TileEntitySpecialRenderer {
    private static final int MAX_ARM = 85;
    private final ModelMechBarrier model;

    public TileMechBarrierRenderer(ModelMechBarrier model) {
        this.model = model;
    }

    @Override
    public void renderTileEntityAt(final TileEntity tileE, final double x, final double y, final double z, final float tick) {
        final TileMechBarrier tile = (TileMechBarrier) tileE;
        final int meta = tileE.getWorldObj() == null ? 0 : tileE.getBlockMetadata();

        // armRotation: 0 = up (open) ... 85 = down (closed); smoothed between ticks by the tile
        final float theta = MAX_ARM - tile.getRenderArm(tick);   // the model's angle: 0 = boom down, 85 = boom up

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);
        GL11.glRotatef(-meta * 360 / 16.0f, 0.0f, 1.0f, 0.0f);
        GL11.glScalef(ModelMechBarrier.RENDER_SCALE, ModelMechBarrier.RENDER_SCALE, ModelMechBarrier.RENDER_SCALE);
        GL11.glScalef(tile.getScale(), tile.getScale(), tile.getScale());
        final boolean mirrored = tile.isBoomLeft();
        int frontFace = GL11.GL_CCW;
        if (mirrored) {
            // mirrored barrier (boom on +X): the mirror flips the winding of every face
            frontFace = GL11.glGetInteger(GL11.GL_FRONT_FACE);
            GL11.glScalef(-1f, 1f, 1f);
            GL11.glFrontFace(frontFace == GL11.GL_CCW ? GL11.GL_CW : GL11.GL_CCW);
        }
        model.render(theta, tile.isPzm1(), tile.getBoomMetres(), tile.hasLattice(), tile.getRenderBellWheelAngle(tick), tile.getRenderHammerPull(tick),
                tile.hasSignPole(), tile.hasPoleSign(), tile.hasKriz(), tile.isKrizJedno(), tile.isKrizReflex(), tile.isKrizVelky(), tile.isSlovak(), mirrored,
                tile.getRenderFlex(tick));
        if (mirrored) {
            GL11.glFrontFace(frontFace);
        }
        GL11.glPopMatrix();
    }
}
