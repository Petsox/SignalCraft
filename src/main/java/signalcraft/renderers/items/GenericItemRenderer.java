package signalcraft.renderers.items;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.IGeneric;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class GenericItemRenderer implements IItemRenderer
{
    private final IGeneric tileSignal;
    private final ResourceLocation metal = TextureRegistry.GSAR_ROD.get();
    private final IModelCustom modelMetalRod = ModelRegistry.GSAR_ROD.getModel();

    public GenericItemRenderer(IGeneric tile) {
        this.tileSignal = tile;
    }

    public boolean handleRenderType(final ItemStack itemStack, final ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(final ItemRenderType type, final ItemStack item, final ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(final ItemRenderType type, final ItemStack item, final Object... data) {
        if (type == ItemRenderType.EQUIPPED) {
            GL11.glPushMatrix();
            GL11.glScalef(1.8f, 1.8f, 1.8f);
            GL11.glRotatef(30.0f, 0.0f, 0.0f, 1.0f);
            GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.5f, -0.1f, 0.5f);
            renderItem(tileSignal);
            GL11.glPopMatrix();
        }
        else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glPushMatrix();
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            GL11.glRotatef(-110.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.7f, 0.8f, 0.2f);
            renderItem(tileSignal);
            GL11.glPopMatrix();
        }
        else if (type != ItemRenderType.ENTITY) {
            GL11.glPushMatrix();
            GL11.glScalef(1.1f, 1.1f, 1.1f);
            GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.0f, -0.5f, 0.0f);
            renderItem(tileSignal);
            GL11.glPopMatrix();
        }
        else if (!(item.getItem() instanceof ItemBlock)) {
            GL11.glPushMatrix();
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            renderItem(tileSignal);
            GL11.glPopMatrix();
        }
        else if (item.getItem() instanceof ItemBlock) {
            GL11.glPushMatrix();
            GL11.glScalef(2.0f, 2.0f, 2.0f);
            renderItem(tileSignal);
            GL11.glPopMatrix();
        }
    }

    //THIS NEEDS TO BE IN EVERY ITEM RENDERER TO SAVE LINES
    private void renderItem(IGeneric tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(tileSignal.getTexture());
        tileSignal.getModel().renderAll();
        if (tileSignal.needsRod()){
            Minecraft.getMinecraft().renderEngine.bindTexture(this.metal);
            this.modelMetalRod.renderAll();
        }
    }
}
