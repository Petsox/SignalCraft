package signalcraft.renderers.items.gsar;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import signalcraft.models.gsar.signalsSO.ModelStationSignGSAR;

public class StationSignItemRendererGSAR implements IItemRenderer
{
    private final ModelStationSignGSAR modelStationSign;
    public StationSignItemRendererGSAR(ModelStationSignGSAR model) {
        this.modelStationSign = model;
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
            GL11.glScalef(1.5f, 1.5f, 1.5f);
            GL11.glRotatef(30.0f, 0.0f, 0.0f, 1.0f);
            GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.2f, 0.0f, 0.5f);
            this.modelStationSign.renderBase();
            GL11.glPopMatrix();
        }
        else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glPushMatrix();
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            GL11.glRotatef(-110.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.5f, 0.3f, 0.0f);
            this.modelStationSign.renderBase();
            GL11.glPopMatrix();
        }
        else if (type != ItemRenderType.ENTITY) {
            GL11.glPushMatrix();
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.0f, -0.5f, 0.0f);
            this.modelStationSign.renderBase();
            GL11.glPopMatrix();
        }
        else if (!(item.getItem() instanceof ItemBlock)) {
            GL11.glPushMatrix();
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            this.modelStationSign.renderBase();
            GL11.glPopMatrix();
        }
        else if (item.getItem() instanceof ItemBlock) {
            GL11.glPushMatrix();
            GL11.glScalef(2.0f, 2.0f, 2.0f);
            this.modelStationSign.renderBase();
            GL11.glPopMatrix();
        }
    }
}
