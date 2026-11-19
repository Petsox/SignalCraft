package signalcraft.renderers.entities.switches;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.switches.cz.TileSwitchCZ;
import signalcraft.entities.switches.cz.TileSwitchElectricCZ;
import signalcraft.entities.switches.cz.TileSwitchManualCZ;
import signalcraft.models.switches.cz.ModelSwitchCZ;

/**
 * Czech point machines: the track runs past the player who placed the block (in the direction they looked), in the
 * neighbouring block on their right - or on their left, the model mirrored.
 */
public class TileSwitchCZRenderer extends TileEntitySpecialRenderer {
    private final ModelSwitchCZ model;

    public TileSwitchCZRenderer(ModelSwitchCZ model) {
        this.model = model;
    }

    @Override
    public void renderTileEntityAt(final TileEntity tileE, final double x, final double y, final double z, final float tick) {
        final TileSwitchCZ tile = (TileSwitchCZ) tileE;
        final int meta = tileE.getWorldObj() == null ? 0 : tileE.getBlockMetadata();
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);
        GL11.glRotatef(-meta * 360 / 16.0f, 0.0f, 1.0f, 0.0f);
        render(this.model, tile, tile.getRenderProgress(tick));
        GL11.glPopMatrix();
    }

    /** the machine of the tile, from the block's frame (also the inventory icon) */
    public static void render(ModelSwitchCZ model, TileSwitchCZ tile, float progress) {
        final boolean mirrored = tile.isTrackLeft();
        GL11.glPushMatrix();
        if (mirrored) {
            GL11.glScalef(-1f, 1f, 1f);
            ModelSwitchCZ.flipFrontFace();
        }
        // the model's track (+Z) to the placing player's right (+X with the block facing them)
        GL11.glRotatef(90f, 0.0f, 1.0f, 0.0f);
        GL11.glTranslatef(tile.getShift() / 16f, 0f, 0f);
        if (tile instanceof TileSwitchElectricCZ) {
            TileSwitchElectricCZ ep = (TileSwitchElectricCZ) tile;
            model.renderElectric(progress, ep.getIsSwitched(), ep.isEp6xx(), ep.hasStand(), ep.getSignal(), ep.isRightTurnout(),
                    ep.getSwitchName(), ep.getScale(), mirrored);
        } else if (tile instanceof TileSwitchManualCZ) {
            TileSwitchManualCZ stand = (TileSwitchManualCZ) tile;
            model.renderManual(progress, stand.getIsSwitched(), stand.getSignal(), stand.isRightTurnout(), stand.isYellowWeight(),
                    stand.getSwitchName(), stand.getScale(), mirrored);
        }
        if (mirrored) {
            ModelSwitchCZ.flipFrontFace();
        }
        GL11.glPopMatrix();
    }
}
