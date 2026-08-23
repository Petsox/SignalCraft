package signalcraft.renderers.entities.lightSignals;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.lightSignals.ILightSignalModel;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.SignalState;
import signalcraft.signalUtils.Utils;

public class TileLightSignalsRenderer extends TileEntitySpecialRenderer {
    private final ILightSignalModel model;

    public TileLightSignalsRenderer(ILightSignalModel model) {
        this.model = model;
    }

    public void renderTileEntityAt(final TileEntity tileE, final double x, final double y, final double z, final float tick) {
        final TileLightSignal thisTileE = (TileLightSignal) tileE;
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
        GL11.glTranslatef((float) x + 0.5f, (float) y + 3.0f, (float) z + 0.5f);
        final float f2 = meta * 360 / 16.0f;
        GL11.glRotatef(-f2, 0.0f, 1.0f, 0.0f);
        GL11.glTranslatef(0.0f, -3f, 0.0f);

        GL11.glScalef(thisTileE.getScale(), thisTileE.getScale(), thisTileE.getScale());

        String position = thisTileE.getPosition().toString();
        Boolean hasStripes = thisTileE.getHasStripes().toBoolean();
        Boolean has3Stripes = thisTileE.getHas3Stripes().toBoolean();
        String speedSignText = thisTileE.getSpeedSignText().toString();
        Boolean isDeparture = thisTileE.getIsDeparture().toBoolean();
        Boolean isGroupped = thisTileE.getIsGrupped().toBoolean();
        String PNLight = thisTileE.hasPNLight().Boo ? "PN" : "";
        SignalState state = thisTileE.getState();

        String pos;
        if (position.equals(Consts.Position.MIDDLE.toString())) {
            pos = "S";
        } else if (position.equals(Consts.Position.LEFT.toString())) {
            pos = "L";
        } else {
            pos = "R";
        }

        this.model.renderStoz(hasStripes, has3Stripes, pos, speedSignText, PNLight);
        if (isDeparture) {
            this.model.renderStozNater(hasStripes, has3Stripes, pos, speedSignText);
        } else {
            this.model.renderStozVjNater(hasStripes, has3Stripes, speedSignText);
        }
        if (isGroupped) {
            this.model.renderSkupinove(hasStripes, has3Stripes, pos, PNLight);
        }
        this.model.renderStit(hasStripes, has3Stripes, pos, PNLight);
        this.model.renderSpeed(speedSignText, pos);
        final int i1 = 15728880;
        final int j1 = i1 % 65536;
        final int k1 = i1 / 65536;
        GL11.glPushMatrix();
        GL11.glColor4f(2.0f, 2.0f, 2.0f, 2.0f);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j1, k1);
        this.model.renderNavest(state, thisTileE, pos, PNLight);
        GL11.glPopMatrix();
        GL11.glTranslatef(0.0f, -1.5f, 0.0f);
        GL11.glPopMatrix();

        EntityLivingBase player = RenderManager.instance.livingPlayer;
        if (player != null) {
            final float viewDist = 8f;
            double dist = player.getDistanceSq(tileE.xCoord + 0.5, tileE.yCoord + 0.5, tileE.zCoord + 0.5);
            if (dist <= (double) (viewDist * viewDist)) {
                MovingObjectPosition mop = player.rayTrace(8, tick);
                if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && player.worldObj.getTileEntity(mop.blockX, mop.blockY, mop.blockZ) == tileE) {
                    if (thisTileE.getName() != null) {
                        Utils.renderString(thisTileE.getName(), x + 0.5, y + 1.5, z + 0.5);
                    }
                }
            }
        }

    }
}
