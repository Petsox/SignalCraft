package signalcraft.renderers.items.levelCrossings;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import signalcraft.models.levelCrossing.azd.ModelAZD99;

public class AZD99ItemRenderer implements IItemRenderer
{
    private final ModelAZD99 model;

    public AZD99ItemRenderer(ModelAZD99 modelAZD99) {
        this.model = modelAZD99;
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

        this.model.renderZaklad(false);
        this.model.renderZavora("4,2m");

        GL11.glPopMatrix();
    }
}
