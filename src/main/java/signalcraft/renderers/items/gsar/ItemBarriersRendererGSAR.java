package signalcraft.renderers.items.gsar;


import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsBU.TileGSARCrossing;
import signalcraft.models.gsar.signalsBU.IModelBarriersGSAR;

public class ItemBarriersRendererGSAR implements IItemRenderer
{
    private final IModelBarriersGSAR modelBarriers;
    private final TileGSARCrossing tileCrossing;

    public ItemBarriersRendererGSAR(IModelBarriersGSAR model, TileGSARCrossing tile) {
        this.modelBarriers = model;
        this.tileCrossing = tile;
    }
    
    public boolean handleRenderType(final ItemStack item, final ItemRenderType type) {
        return true;
    }
    
    public boolean shouldUseRenderHelper(final ItemRenderType type, final ItemStack item, final ItemRendererHelper helper) {
        return true;
    }
    
    public void renderItem(final ItemRenderType type, final ItemStack item, final Object... data) {
        if (type == ItemRenderType.EQUIPPED) {
            GL11.glPushMatrix();
            GL11.glScalef(1.2f, 1.2f, 1.2f);
            GL11.glRotatef(30.0f, 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef(0.8f, -0.3f, 0.8f);
            this.modelBarriers.renderItem(tileCrossing);
            GL11.glPopMatrix();
        }
        else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glPushMatrix();
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            GL11.glRotatef(-110.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.7f, 0.5f, 0.2f);
            this.modelBarriers.renderItem(tileCrossing);
            GL11.glPopMatrix();
        }
        else if (type != ItemRenderType.ENTITY) {
            GL11.glPushMatrix();
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            GL11.glRotatef(20.0f, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(20.0f, 0.0f, 0.0f, 1.0f);
            GL11.glRotatef(30.0f, 1.0f, 0.0f, 0.0f);
            GL11.glTranslatef(0.0f, -1.9f, 0.0f);
            this.modelBarriers.renderItem(tileCrossing);
            GL11.glPopMatrix();
        }
        else if (!(item.getItem() instanceof ItemBlock)) {
            GL11.glPushMatrix();
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            this.modelBarriers.renderItem(tileCrossing);
            GL11.glPopMatrix();
        }
        else if (item.getItem() instanceof ItemBlock) {
            GL11.glPushMatrix();
            GL11.glScalef(2.0f, 2.0f, 2.0f);
            this.modelBarriers.renderItem(tileCrossing);
            GL11.glPopMatrix();
        }
    }
}
