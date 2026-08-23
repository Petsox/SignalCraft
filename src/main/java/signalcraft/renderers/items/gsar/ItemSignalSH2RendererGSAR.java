package signalcraft.renderers.items.gsar;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.IGeneric;
import signalcraft.entities.gsar.signalsSH.TileGSARSignSignalSH2;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;
import signalcraft.models.gsar.signalsSH.ModelGSARSignalSH2;

public class ItemSignalSH2RendererGSAR implements IItemRenderer
{

    private final TileGSARSignSignalSH2 tileSignal;
    private final ModelGSARSignalSH2 modelSignal;

    public ItemSignalSH2RendererGSAR(ModelGSARSignalSH2 model, TileGSARSignSignalSH2 tile) {
        this.tileSignal = tile;
        this.modelSignal = model;
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
            GL11.glScalef(1.4f, 1.4f, 1.4f);
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
    private void renderItem(TileGSARSignSignalSH2 tileSignal) {
        modelSignal.renderStoz(tileSignal);
    }
}
