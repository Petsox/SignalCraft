package signalcraft.renderers.items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import signalcraft.models.ModelSpeedSign;

public class SpeedSignItemRenderer implements IItemRenderer
{
    private final ModelSpeedSign modelSpeedSign;

    public SpeedSignItemRenderer() {
        this.modelSpeedSign = new ModelSpeedSign();
    }
    
    public boolean handleRenderType(final ItemStack itemStack, final ItemRenderType type) {
        return true;
    }
    
    public boolean shouldUseRenderHelper(final ItemRenderType type, final ItemStack item, final ItemRendererHelper helper) {
        return true;
    }
    
    public void renderItem(final ItemRenderType type, final ItemStack item, final Object... data) {
        GL11.glPushMatrix();
        GL11.glRotatef(0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
        GL11.glTranslatef(0f, -0.75f, 0f);
        this.modelSpeedSign.renderRychlostnik();

        GL11.glPopMatrix();
    }
}
