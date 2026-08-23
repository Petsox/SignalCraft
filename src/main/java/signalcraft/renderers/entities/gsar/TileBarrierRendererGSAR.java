package signalcraft.renderers.entities.gsar;


import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsBU.*;
import signalcraft.models.gsar.signalsBU.IModelBarriersGSAR;

public class TileBarrierRendererGSAR extends TileEntitySpecialRenderer {
    private final IModelBarriersGSAR modelFullBarrier;

    public TileBarrierRendererGSAR(IModelBarriersGSAR model) {
        this.modelFullBarrier = model;
    }

    public void renderTileEntityAt(final TileEntity tileE, final double x, final double y, final double z, final float f) {
        final TileGSARCrossing tileBarriers = (TileGSARCrossing) tileE;
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

        boolean longArm = tileE instanceof TileGSARFullBarriersx10R || tileE instanceof TileGSARFullBarriersx10L || tileE instanceof TileGSARModernBarrierL || tileE instanceof TileGSARModernBarrierR;

        int c = tileBarriers.blinkCounter;
        boolean onoff = (c >= 1 && c < 15) || (c >= 30 && c < 45);

        String position;
        float offsetX1 = 0.0f;
        float offsetX2 = 0.0f;
        if (tileE.getClass().getSimpleName().charAt(tileE.getClass().getSimpleName().length() - 1) == 'L') {
            position = "_L";
            if (tileE instanceof TileGSARFullBarriersx4L || tileE instanceof TileGSARFullBarriersx10L) {
                offsetX1 = -0.31f;
                offsetX2 = -0.235f;
            }
        } else {
            position = "_R";
            offsetX1 = 0.31f;
            offsetX2 = 0.235f;
        }

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y + 0.0f, (float) z + 0.5f);
        final float f2 = meta * 360 / 16.0f;
        GL11.glRotatef(-f2, 0.0f, 1.0f, 0.0f);


        modelFullBarrier.renderBase(position);

        modelFullBarrier.renderBase2(position);

        GL11.glPushMatrix();
        GL11.glTranslatef(offsetX1, 0.85f, -0.1f);
        GL11.glRotatef((float) (-tileBarriers.getActiveReels()), 1.0f, 0.0f, 0.0f);
        modelFullBarrier.renderReels(position);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(0.0f, 1.08f, 0.0f);
        GL11.glRotatef((float) (-tileBarriers.getArmRotation()), 1.0f, 0.0f, 0.0f);
        modelFullBarrier.renderArmBase(tileBarriers, position, longArm, onoff);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(offsetX2, 1.2f, 0.02f);
        GL11.glRotatef(20.0f, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef((float) (-tileBarriers.getActiveBell()), 1.0f, 0.0f, 0.0f);
        modelFullBarrier.renderBell(position);
        GL11.glPopMatrix();

        GL11.glPopMatrix();
    }
}
