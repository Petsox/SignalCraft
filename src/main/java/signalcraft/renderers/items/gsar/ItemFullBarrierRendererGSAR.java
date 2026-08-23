package signalcraft.renderers.items.gsar;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsBU.TileGSARCrossing;
import signalcraft.entities.gsar.signalsBU.TileGSARFullBarriersx10L;
import signalcraft.entities.gsar.signalsBU.TileGSARFullBarriersx4L;
import signalcraft.models.gsar.signalsBU.IModelBarriersGSAR;

public class ItemFullBarrierRendererGSAR implements IItemRenderer
{
    private final IModelBarriersGSAR modelFullBarrier;
    private final TileGSARCrossing tileCrossing;

    public ItemFullBarrierRendererGSAR(IModelBarriersGSAR model, TileGSARCrossing tile) {
        this.modelFullBarrier = model;
        this.tileCrossing = tile;
    }
    
    public boolean handleRenderType(final ItemStack item, final ItemRenderType type) {
        return true;
    }
    
    public boolean shouldUseRenderHelper(final ItemRenderType type, final ItemStack item, final ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object... data) {
        String position;
        float offsetX1;
        if (tileCrossing instanceof TileGSARFullBarriersx4L || tileCrossing instanceof TileGSARFullBarriersx10L) {
            position = "_L";
            offsetX1 = -0.31f;
        } else {
            position = "_R";
            offsetX1 = 0.31f;
        }

        if (type == ItemRenderType.EQUIPPED) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.8f, -0.2f, 0.5f);
            this.modelFullBarrier.renderBase(position);
            this.modelFullBarrier.renderBase2(position);
            GL11.glTranslatef(offsetX1, 0.85f, -0.1f);
            this.modelFullBarrier.renderReels(position);
            GL11.glTranslatef(-offsetX1, -0.93f, 0.1f);
            this.modelFullBarrier.renderItem(tileCrossing);
            GL11.glPopMatrix();
        } else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.8f, -0.2f, 0.5f);
            this.modelFullBarrier.renderBase(position);
            this.modelFullBarrier.renderBase2(position);
            GL11.glTranslatef(offsetX1, 0.85f, -0.1f);
            this.modelFullBarrier.renderReels(position);
            GL11.glTranslatef(-offsetX1, -0.93f, 0.1f);
            this.modelFullBarrier.renderItem(tileCrossing);
            GL11.glPopMatrix();
        } else if (type != ItemRenderType.ENTITY) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.8f, -0.2f, 0.5f);
            this.modelFullBarrier.renderBase(position);
            this.modelFullBarrier.renderBase2(position);
            GL11.glTranslatef(offsetX1, 0.85f, -0.1f);
            this.modelFullBarrier.renderReels(position);
            GL11.glTranslatef(-offsetX1, -0.93f, 0.1f);
            this.modelFullBarrier.renderItem(tileCrossing);
            GL11.glPopMatrix();
        } else if (!(item.getItem() instanceof ItemBlock)) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.8f, -0.2f, 0.5f);
            this.modelFullBarrier.renderBase(position);
            this.modelFullBarrier.renderBase2(position);
            GL11.glTranslatef(offsetX1, 0.85f, -0.1f);
            this.modelFullBarrier.renderReels(position);
            GL11.glTranslatef(-offsetX1, -0.93f, 0.1f);
            this.modelFullBarrier.renderItem(tileCrossing);
            GL11.glPopMatrix();
        } else if (item.getItem() instanceof ItemBlock) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.8f, -0.2f, 0.5f);
            this.modelFullBarrier.renderBase(position);
            this.modelFullBarrier.renderBase2(position);
            GL11.glTranslatef(offsetX1, 0.85f, -0.1f);
            this.modelFullBarrier.renderReels(position);
            GL11.glTranslatef(-offsetX1, -0.93f, 0.1f);
            this.modelFullBarrier.renderItem(tileCrossing);
            GL11.glPopMatrix();
        }
    }
}
