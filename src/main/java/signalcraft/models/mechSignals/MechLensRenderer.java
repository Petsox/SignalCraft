package signalcraft.models.mechSignals;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

/**
 * Renders the fixed lamps and semi-transparent colored lenses of mechanical signals.
 * The lamp is always lit. Only the lens currently in front of a lamp should look lit - it's
 * rendered full-bright and at full color, while a lens moved away from the lamp keeps the
 * ambient light and a darker tint. "litAmount" (0..1) blends between the two, so a lens sliding
 * across the lamp while the arm moves lights up gradually instead of snapping.
 * <p>
 * Every full-bright override restores the lightmap that was active before it (Forge's
 * OpenGlHelper.lastBrightnessX/Y) rather than one looked up from the world - that is the block's
 * light when placed and the player's/GUI light when held or in the inventory, where the tile has
 * no world. Leaving the override in place would leak onto the rest of the model drawn after it
 * (see the crossing-signal angle-loop lightmap bug for exactly this failure mode).
 */
public final class MechLensRenderer {
    /** Color multiplier for a lens that isn't in front of its lamp - plain unlit colored glass. */
    private static final float UNLIT_COLOR = 0.6f;
    private static final float FULL_BRIGHT = 240f;

    private MechLensRenderer() {}

    /** Renders an opaque fixed lamp part at full brightness. */
    public static void renderLamp(IModelCustom model, ResourceLocation texture, String part) {
        float ambientX = OpenGlHelper.lastBrightnessX;
        float ambientY = OpenGlHelper.lastBrightnessY;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, FULL_BRIGHT, FULL_BRIGHT);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        model.renderPart(part);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, ambientX, ambientY);
    }

    /**
     * Enables real alpha blending instead of this mod's default alpha-test cutoff (which can only
     * fully show or fully discard a fragment, never blend it partially). Pair with {@link #end}.
     */
    public static void begin() {
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }

    public static void renderLens(IModelCustom model, ResourceLocation texture, String part, float litAmount) {
        float lit = Math.max(0f, Math.min(1f, litAmount));
        float ambientX = OpenGlHelper.lastBrightnessX;
        float ambientY = OpenGlHelper.lastBrightnessY;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,
                ambientX + (FULL_BRIGHT - ambientX) * lit,
                ambientY + (FULL_BRIGHT - ambientY) * lit);
        float color = UNLIT_COLOR + (1f - UNLIT_COLOR) * lit;
        GL11.glColor4f(color, color, color, 1f);

        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        model.renderPart(part);

        GL11.glColor4f(1f, 1f, 1f, 1f);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, ambientX, ambientY);
    }

    public static void end() {
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
    }
}
