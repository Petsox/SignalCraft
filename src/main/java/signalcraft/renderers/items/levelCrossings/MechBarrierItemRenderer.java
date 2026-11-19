package signalcraft.renderers.items.levelCrossings;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import signalcraft.models.levelCrossing.mech.ModelMechBarrier;

public class MechBarrierItemRenderer implements IItemRenderer
{
    private final ModelMechBarrier model;

    public MechBarrierItemRenderer(ModelMechBarrier model) {
        this.model = model;
    }

    public boolean handleRenderType(final ItemStack itemStack, final ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(final ItemRenderType type, final ItemStack item, final ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(final ItemRenderType type, final ItemStack item, final Object... data) {
        GL11.glPushMatrix();
        GL11.glTranslatef(0f, -0.75f, 0f);
        GL11.glScalef(0.55f, 0.55f, 0.55f);
        // PZM 1 with the boom raised keeps the icon compact
        this.model.render(85f, true, 4, false);
        GL11.glPopMatrix();
    }
}
