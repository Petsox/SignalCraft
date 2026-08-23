package signalcraft.renderers.items.signSignals;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import signalcraft.models.lightSignals.IDwarf;
import signalcraft.models.lightSignals.ILightSignalModel;
import signalcraft.models.signSignals.ModelSignPrUpoz;

public class PrUpozItemRenderer implements IItemRenderer {
    private final ModelSignPrUpoz model = new ModelSignPrUpoz();

    public boolean handleRenderType(final ItemStack item, final ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(final ItemRenderType type, final ItemStack item, final ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(final ItemRenderType type, final ItemStack item, final Object... data) {
        GL11.glPushMatrix();
        GL11.glRotatef(0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(180f, 0.0f, 1.0f, 0.0f);
        GL11.glTranslatef(0f, -1f, 0f);

        this.model.renderBody(false);

        GL11.glPopMatrix();
    }
}