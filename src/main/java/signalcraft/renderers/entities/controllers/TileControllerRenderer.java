package signalcraft.renderers.entities.controllers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.controllers.TileContReceBase;
import signalcraft.signalUtils.Utils;

public class TileControllerRenderer extends TileEntitySpecialRenderer
{
    final IModelCustom modelController;

    final ResourceLocation texture;

    private TileContReceBase TileBase;
    public TileControllerRenderer(final TileContReceBase tile) {
        texture = tile.getTexture();
        TileBase = tile;
        this.modelController = AdvancedModelLoader.loadModel(new ResourceLocation("signalcraft:models/controllerBox.obj"));
    }
    public void renderTileEntityAt(final TileEntity tileEntity, final double x, final double y, final double z, final float tick) {
        TileBase = (TileContReceBase) tileEntity;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5f, (float)y + 0.0f, (float)z + 0.5f);
        GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
        this.modelController.renderAll();
        GL11.glPopMatrix();

        EntityLivingBase player = RenderManager.instance.livingPlayer;
        if (player != null) {
            final float viewDist = 8f;
            double dist = player.getDistanceSq(TileBase.xCoord + 0.5, TileBase.yCoord + 0.5, TileBase.zCoord + 0.5);
            if (dist <= (double) (viewDist * viewDist)) {
                MovingObjectPosition mop = player.rayTrace(8, tick);
                if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && player.worldObj.getTileEntity(mop.blockX, mop.blockY, mop.blockZ) == TileBase) {
                    if (TileBase.getName() != null) {
                        Utils.renderString(TileBase.getName(), x + 0.5, y + 1.5, z + 0.5);
                    }
                }
            }
        }
    }
}
