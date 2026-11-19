package signalcraft.renderers.items.switches;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.switches.cz.TileSwitchCZ;
import signalcraft.models.switches.cz.ModelSwitchCZ;
import signalcraft.renderers.entities.switches.TileSwitchCZRenderer;

import java.nio.FloatBuffer;

public class SwitchCZItemRenderer implements IItemRenderer
{
    private final ModelSwitchCZ model;
    private final TileSwitchCZ tile;
    private final FloatBuffer matrix = BufferUtils.createFloatBuffer(16);

    public SwitchCZItemRenderer(ModelSwitchCZ model, TileSwitchCZ tile) {
        this.model = model;
        this.tile = tile;
    }

    public boolean handleRenderType(final ItemStack itemStack, final ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(final ItemRenderType type, final ItemStack item, final ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(final ItemRenderType type, final ItemStack item, final Object... data) {
        GL11.glPushMatrix();
        GL11.glTranslatef(0f, -0.45f, 0f);
        GL11.glScalef(1.1f, 1.1f, 1.1f);
        // the render below turns the model by 90 degrees: the track to the right of the icon
        GL11.glRotatef(-180f, 0.0f, 1.0f, 0.0f);
        GL11.glTranslatef(-0.45f, 0f, 0f);
        // the inventory icon is drawn mirrored (Forge scales Z by -1): swap the front faces whenever the transform mirrors
        final boolean mirrored = isMirrored();
        if (mirrored) {
            ModelSwitchCZ.flipFrontFace();
        }
        TileSwitchCZRenderer.render(this.model, this.tile, 0f);
        if (mirrored) {
            ModelSwitchCZ.flipFrontFace();
        }
        GL11.glPopMatrix();
    }

    private boolean isMirrored() {
        matrix.clear();
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, matrix);
        float a = matrix.get(0), b = matrix.get(4), c = matrix.get(8);
        float d = matrix.get(1), e = matrix.get(5), f = matrix.get(9);
        float g = matrix.get(2), h = matrix.get(6), i = matrix.get(10);
        return a * (e * i - f * h) - b * (d * i - f * g) + c * (d * h - e * g) < 0f;
    }
}
