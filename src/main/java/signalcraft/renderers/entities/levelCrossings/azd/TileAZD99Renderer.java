package signalcraft.renderers.entities.levelCrossings.azd;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.levelCrossings.azd.TileAZD99;
import signalcraft.models.levelCrossing.azd.ModelAZD99;

public class TileAZD99Renderer extends TileEntitySpecialRenderer {
    private final ModelAZD99 modelAZD99;

    public TileAZD99Renderer(ModelAZD99 modelAZD99) {
        this.modelAZD99 = modelAZD99;
    }

    public void renderTileEntityAt(final TileEntity tileE, final double x, final double y, final double z, final float tick) {
        final Block block = tileE.getBlockType();
        final TileAZD99 thisCrossingTile = (TileAZD99) tileE;
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
        GL11.glScalef(1.5f, 1.5f, 1.5f);
        GL11.glScalef(thisCrossingTile.getScale(), thisCrossingTile.getScale(), thisCrossingTile.getScale());
        modelAZD99.renderZaklad(thisCrossingTile.isNewer());

        GL11.glPushMatrix();
        GL11.glTranslatef(0.087725f, 0.650061f, 0.0f);
        GL11.glRotatef((float)(thisCrossingTile.getArmRotation()), 0.0f, 0.0f, 1.0f);
        GL11.glTranslatef(-0.087725f, -0.650061f, 0.0f);
        modelAZD99.renderZavora(thisCrossingTile.getBarrierLength());
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
