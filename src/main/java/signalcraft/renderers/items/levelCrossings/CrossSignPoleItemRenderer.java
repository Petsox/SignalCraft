package signalcraft.renderers.items.levelCrossings;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import signalcraft.models.levelCrossing.mech.ModelCrossSignPole;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

/**
 * The stand-alone warning cross pole as an item, with the striped pole. In the inventory only its top with the signs
 * is drawn, enlarged (the whole 2.2 m pole would leave the signs a few pixels small); in the hand and on
 * the ground the whole pole.
 */
public class CrossSignPoleItemRenderer implements IItemRenderer
{
    private final ModelCrossSignPole model;
    private final FloatBuffer matrix = BufferUtils.createFloatBuffer(16);
    private final DoubleBuffer plane = BufferUtils.createDoubleBuffer(4);
    /** the pole is drawn thicker than in the world (there 1.5), it would be about a pixel wide */
    private static final float ICON_POLE_WIDTH = 2.5f;
    /** whole pole: its size in the item's block */
    private static final float WHOLE_SCALE = 1.5f;
    /**
     * inventory icon: the model from a piece of the pole under the POZOR VLAK sign (its holder ends at 0.8173), about
     * as long as the signs are high, to the top of the cross, OBJ units
     */
    private static final float ICON_BOTTOM = 0.35f + ModelCrossSignPole.STANDALONE_RAISE - 0.45f;
    private static final float ICON_TOP = 1.2f + ModelCrossSignPole.STANDALONE_RAISE;
    private static final float ICON_SCALE = 1.75f / (ICON_TOP - ICON_BOTTOM);

    public CrossSignPoleItemRenderer(ModelCrossSignPole model) {
        this.model = model;
    }

    public boolean handleRenderType(final ItemStack itemStack, final ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(final ItemRenderType type, final ItemStack item, final ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(final ItemRenderType type, final ItemStack item, final Object... data) {
        final boolean icon = type == ItemRenderType.INVENTORY;
        GL11.glPushMatrix();
        if (icon) {
            GL11.glTranslatef(0f, -0.04f - (ICON_TOP + ICON_BOTTOM) / 2f * ICON_SCALE, 0f);
            GL11.glScalef(ICON_SCALE, ICON_SCALE, ICON_SCALE);
        } else {
            GL11.glTranslatef(0f, -0.55f, 0f);
            GL11.glScalef(WHOLE_SCALE, WHOLE_SCALE, WHOLE_SCALE);
        }
        if (!icon) {                                                    // the icon shows the front of the signs
            GL11.glRotatef(180f, 0.0f, 1.0f, 0.0f);
        }
        GL11.glTranslatef(-ModelCrossSignPole.POLE_X, 0f, -ModelCrossSignPole.POLE_Z);
        if (icon) {
            // cut the pole off under the signs, it would hang out of the slot
            plane.clear();
            plane.put(0.0).put(1.0).put(0.0).put(-ICON_BOTTOM).flip();
            GL11.glClipPlane(GL11.GL_CLIP_PLANE0, plane);
            GL11.glEnable(GL11.GL_CLIP_PLANE0);
        }
        // the inventory icon is drawn mirrored (Forge scales Z by -1): closed parts like the pole would show their
        // inside, so swap the front faces whenever the current transform mirrors
        final boolean mirrored = isMirrored();
        if (mirrored) {
            ModelCrossSignPole.flipFrontFace();
        }
        this.model.render(true, false, true, false, false, false, false, false, ModelCrossSignPole.STANDALONE_RAISE,
                ICON_POLE_WIDTH, false, true);
        if (mirrored) {
            ModelCrossSignPole.flipFrontFace();
        }
        if (icon) {
            GL11.glDisable(GL11.GL_CLIP_PLANE0);
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
