package signalcraft.renderers.items.gsar;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsHP.TileGSARStativ;
import signalcraft.models.gsar.ILightSignalModelGSAR;
import signalcraft.models.gsar.IStativModelGSAR;

public class ItemStativSignalsRendererGSAR implements IItemRenderer
{
    private final IStativModelGSAR modelStativ;
    private final TileGSARStativ tileSignal;

    public ItemStativSignalsRendererGSAR(IStativModelGSAR model, TileGSARStativ tile) {
        this.modelStativ = model;
        this.tileSignal = tile;
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
            GL11.glScalef(1.8f, 1.8f, 1.8f);
            GL11.glRotatef(30.0f, 0.0f, 0.0f, 1.0f);
            GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.5f, -0.1f, 0.5f);
            this.modelStativ.renderStativ(tileSignal);
            GL11.glPopMatrix();
        }
        else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glPushMatrix();
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            GL11.glRotatef(-110.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.7f, 0.8f, 0.2f);
            this.modelStativ.renderStativ(tileSignal);
            GL11.glPopMatrix();
        }
        else if (type != ItemRenderType.ENTITY) {
            GL11.glPushMatrix();
            GL11.glScalef(1.4f, 1.4f, 1.4f);
            GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.0f, -0.5f, 0.0f);
            this.modelStativ.renderStativ(tileSignal);
            GL11.glPopMatrix();
        }
        else if (!(item.getItem() instanceof ItemBlock)) {
            GL11.glPushMatrix();
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            this.modelStativ.renderStativ(tileSignal);
            GL11.glPopMatrix();
        }
        else if (item.getItem() instanceof ItemBlock) {
            GL11.glPushMatrix();
            GL11.glScalef(2.0f, 2.0f, 2.0f);
            this.modelStativ.renderStativ(tileSignal);
            GL11.glPopMatrix();
        }
    }
}
