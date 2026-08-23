package signalcraft.renderers.entities.signSignals;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.signals.signSignals.TilePrUpoz;
import signalcraft.models.signSignals.ModelSignPrUpoz;

public class TilePrUpozRenderer extends TileEntitySpecialRenderer {
    private final ModelSignPrUpoz modelSignPrUpoz = new ModelSignPrUpoz();
     public TilePrUpozRenderer() {
    }

    public void renderTileEntityAt(final TileEntity tileE, final double x, final double y, final double z, final float tick) {
        int meta;
        final TilePrUpoz tile = (TilePrUpoz) tileE;
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
        GL11.glTranslatef((float) x + 0.5f, (float) y + 3.0f, (float) z + 0.5f);
        final float f2 = meta * 360 / 16.0f;
        GL11.glRotatef(-f2, 0.0f, 1.0f, 0.0f);
        GL11.glTranslatef(0.0f, -3.0f, 0.0f);
        GL11.glRotatef(180f, 0.0f, 1.0f, 0.0f);

        modelSignPrUpoz.renderBody(tile.getIsActive());

        GL11.glPopMatrix();
    }
}