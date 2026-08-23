package signalcraft.renderers.items.lightSignals;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import signalcraft.models.lightSignals.IDwarf;
import signalcraft.models.lightSignals.ILightSignalModel;

public class LightSignalItemRenderer implements IItemRenderer {
    private final ILightSignalModel model;
    private final float scale;
    private final float offset;

    public LightSignalItemRenderer(ILightSignalModel model) {
        this.model = model;
        scale = model instanceof IDwarf ? 1.5f : 0.45f;
        offset = model instanceof IDwarf ? 0.25f : -1.6f;
    }

    public boolean handleRenderType(final ItemStack item, final ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(final ItemRenderType type, final ItemStack item, final ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(final ItemRenderType type, final ItemStack item, final Object... data) {
        GL11.glPushMatrix();
        GL11.glScalef(scale, scale, scale);
        GL11.glRotatef(0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
        GL11.glTranslatef(1f, offset, 0.8f);

        this.model.renderStoz(false, false, "S", "nic", "");
        this.model.renderStozNater(false, false,"S", "nic");
        this.model.renderStit(false, false, "S", "");
        this.model.renderSpeed("nic", "S");

        GL11.glPopMatrix();
    }
}