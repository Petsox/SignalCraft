package signalcraft.renderers.items.controllers;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.controllers.TileContReceBase;

public class ControllerItemRenderer implements IItemRenderer
{
    private final IModelCustom modelController;

    private final ResourceLocation texture;
    public ControllerItemRenderer(final TileContReceBase tile) {
        texture = tile.getTexture();
        this.modelController = AdvancedModelLoader.loadModel(new ResourceLocation("signalcraft:models/controllerBox.obj"));
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
            GL11.glScalef(1.1f, 1.1f, 1.1f);
            GL11.glRotatef(0.0f, 0.0f, 0.0f, 1.0f);
            GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.5f, -0.1f, 0.5f);
            Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
            this.modelController.renderAll();
            GL11.glPopMatrix();
        }
        else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glPushMatrix();
            GL11.glScalef(0.8f, 0.8f, 0.8f);
            GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(-0.5f, 0.65f, 0.75f);
            Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
            this.modelController.renderAll();
            GL11.glPopMatrix();
        }
        else if (type != ItemRenderType.ENTITY) {
            GL11.glPushMatrix();
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.0f, -0.5f, 0.0f);
            Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
            this.modelController.renderAll();
            GL11.glPopMatrix();
        }
        else if (!(item.getItem() instanceof ItemBlock)) {
            GL11.glPushMatrix();
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
            this.modelController.renderAll();
            GL11.glPopMatrix();
        }
        else if (item.getItem() instanceof ItemBlock) {
            GL11.glPushMatrix();
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
            this.modelController.renderAll();
            GL11.glPopMatrix();
        }
    }
}
