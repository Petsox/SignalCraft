package signalcraft.models.levelCrossing.mech;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.models.BrightTexture;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

/**
 * Round pole with the "POZOR VLAK" sign and the warning cross (parts of models/mech/zavora_mech.obj). Drawn by the
 * mechanical barrier (hanging on its low post, with the clamps) and by the stand-alone pole block (centred, no
 * clamps). Cross: Czech for one / more tracks, painted / reflective, small / large, or Slovak (one plate for one and
 * more tracks, only the texture differs). The cross faces use the AZD 71 textures, the rest the barrier atlas.
 * The stand-alone pole can also carry the road sign P 6 "Stuj, dej prednost v jizde" (STOP) under the others; its
 * plate is drawn here (not in the OBJ) with textures/nove/mech/stop.png, a photograph of the sign (see STOP_PX);
 * its back is the pole paint of the barrier atlas.
 * Its pole can be striped red and white: then the pole is drawn here too, with textures/nove/mech/stozar_pruhy.png
 * (the bands run down the image, which spans the whole pole).
 */
public class ModelCrossSignPole
{
    /** pole axis in the barrier OBJ (X, Z); the whole assembly is symmetric about the axis in X */
    public static final float POLE_X = 0.03f, POLE_Z = 0.26f;
    /** bottom and top of the pole (zm_stozar) in the OBJ */
    public static final float POLE_BOTTOM = 0.01402f, POLE_TOP = 1.11402f;
    /**
     * the stand-alone pole is taller than the one on the barrier: 0.3 m under the cross on a pole of the AZD 71
     * warning signal (azd71_vyst.obj: kriz_stozar at 1.497, zm_kriz_drzak here at 0.9594) - cross from 1.9 m, pole
     * top at 2.2 m
     */
    public static final float STANDALONE_RAISE = 1.497f - 0.9594f - 0.3f / 1.5f;
    /** and thicker: 90 mm instead of the 60 mm tube on the barrier */
    public static final float STANDALONE_POLE_WIDTH = 1.5f;
    /**
     * STOP sign: 650 mm across the flats; its top edge under the POZOR VLAK sign, without that sign under the cross
     * (the large Czech crosses reach lower)
     */
    public static final float STOP_SIZE = 0.65f / 1.5f, STOP_TOP = 0.835f, STOP_TOP_NO_SIGN = 0.935f,
            STOP_TOP_LARGE_CROSS = 0.875f;
    /** pole radius (60 mm tube) and the sides of the striped pole drawn here */
    private static final float POLE_RADIUS = 0.02f;
    private static final int POLE_SIDES = 16;
    /** the plate hangs on a copy of the POZOR VLAK holder (zm_pv_sign_drzak: centre height, front face) */
    private static final float HOLDER_Y = 0.8715f, HOLDER_FRONT = 0.3176f, STOP_THICKNESS = 0.0014f;
    /**
     * stop.png: its size and the plate corners in it (pixels), anticlockwise from the upper right-hand one, then the
     * centre. The photograph is not quite square-on; mapping corner to corner straightens it. Re-measure after
     * replacing or re-cropping the picture.
     */
    private static final float STOP_PX_W = 692f, STOP_PX_H = 679f;
    private static final float[][] STOP_PX = {
            {658.2f, 216.1f}, {476.1f, 33.5f}, {216.8f, 38.1f}, {41.7f, 229.0f},
            {52.4f, 486.6f}, {235.5f, 658.6f}, {485.1f, 652.0f}, {659.3f, 471.9f},
            {353.1f, 348.2f}};
    /** the back of the plate: this spot of the atlas (plain grey paint of the pole), centre and width in U */
    private static final float BACK_U = 0.125f, BACK_V = 0.25f, BACK_FILL_U = 0.2f;

    private final IModelCustom model = ModelRegistry.MECH_BARRIER.getModel();
    private final ResourceLocation atlas = TextureRegistry.MECH_BARRIER.get();
    private final ResourceLocation cross1 = TextureRegistry.KRIZ_CZ.get();
    private final ResourceLocation crossX = TextureRegistry.KRIZ_CZ_VIC.get();
    private final ResourceLocation cross1Refl = TextureRegistry.KRIZ_CZ_REFL.get();
    private final ResourceLocation crossXRefl = TextureRegistry.KRIZ_CZ_XK_REFL.get();
    private final ResourceLocation crossXReflLarge = TextureRegistry.KRIZ_CZ_REFL_VELKY.get();
    private final ResourceLocation crossSk = TextureRegistry.KRIZ_SK.get();
    private final ResourceLocation crossSkX = TextureRegistry.KRIZ_SK_VIC.get();
    private final ResourceLocation stopTex = TextureRegistry.MECH_STOP.get();
    private final ResourceLocation stripesTex = TextureRegistry.MECH_POLE_STRIPES.get();

    /** how much wider than modelled the holders are drawn (set by render), and how far that pushes the plates forward */
    private float holderWidth = 1f, forward = 0f;

    /** A sign holder (clamp round the pole) moved up by dy and widened with the pole, about the pole axis. */
    private void holder(String part, float dy) {
        GL11.glPushMatrix();
        GL11.glTranslatef(POLE_X, dy, POLE_Z - forward);                // the caller has moved everything forward
        GL11.glScalef(holderWidth, 1f, holderWidth);
        GL11.glTranslatef(-POLE_X, 0f, -POLE_Z);
        model.renderPart(part);
        GL11.glPopMatrix();
    }

    private static void bind(ResourceLocation tex) {
        Minecraft.getMinecraft().renderEngine.bindTexture(tex);
    }

    /** The photographed faces (crosses, STOP) are bound brightened, see BrightTexture. */
    private static void bindBright(ResourceLocation tex) {
        bind(BrightTexture.of(tex));
    }

    /** Swaps the front face winding (call before and after drawing under a mirroring transform). */
    public static void flipFrontFace() {
        GL11.glFrontFace(GL11.glGetInteger(GL11.GL_FRONT_FACE) == GL11.GL_CCW ? GL11.GL_CW : GL11.GL_CCW);
    }

    /** On the barrier: where it hangs on the low post, with the clamps (see the full render). */
    public void render(boolean sign, boolean clamps, boolean cross, boolean oneTrack, boolean reflective,
                       boolean large, boolean slovak, boolean mirrored) {
        render(sign, clamps, cross, oneTrack, reflective, large, slovak, mirrored, 0f);
    }

    /**
     * In barrier OBJ units, where it hangs on the barrier. clamps: the clamps to the barrier post;
     * mirrored: the caller mirrors in X - the pole is mirrored back so the sign and the cross stay readable;
     * raise (OBJ units): everything is lifted by this much and the pole is stretched down to the ground.
     * Leaves the atlas bound.
     */
    public void render(boolean sign, boolean clamps, boolean cross, boolean oneTrack, boolean reflective,
                       boolean large, boolean slovak, boolean mirrored, float raise) {
        render(sign, clamps, cross, oneTrack, reflective, large, slovak, mirrored, raise, 1f);
    }

    /**
     * poleWidth: the pole drawn this many times thicker (the stand-alone pole, more so its inventory icon). The
     * holders of the signs are widened with it and the signs move forward.
     */
    public void render(boolean sign, boolean clamps, boolean cross, boolean oneTrack, boolean reflective,
                       boolean large, boolean slovak, boolean mirrored, float raise, float poleWidth) {
        render(sign, clamps, cross, oneTrack, reflective, large, slovak, mirrored, raise, poleWidth, false);
    }

    /** stop: the STOP sign under the POZOR VLAK sign (moved up under the cross when there is no such sign). */
    public void render(boolean sign, boolean clamps, boolean cross, boolean oneTrack, boolean reflective,
                       boolean large, boolean slovak, boolean mirrored, float raise, float poleWidth, boolean stop) {
        render(sign, clamps, cross, oneTrack, reflective, large, slovak, mirrored, raise, poleWidth, stop, false);
    }

    /** stripes: the pole painted in red and white bands. */
    public void render(boolean sign, boolean clamps, boolean cross, boolean oneTrack, boolean reflective,
                       boolean large, boolean slovak, boolean mirrored, float raise, float poleWidth, boolean stop,
                       boolean stripes) {
        GL11.glPushMatrix();
        if (mirrored) {
            GL11.glTranslatef(POLE_X, 0f, 0f);
            GL11.glScalef(-1f, 1f, 1f);
            GL11.glTranslatef(-POLE_X, 0f, 0f);
            flipFrontFace();
        }
        bind(atlas);
        if (clamps) {
            model.renderPart("zm_stozar_uchyt");
        }
        // pole: with a raise from the ground to its raised top
        GL11.glPushMatrix();
        GL11.glTranslatef(POLE_X, 0f, POLE_Z);
        GL11.glScalef(poleWidth, raise > 0f ? (POLE_TOP + raise) / (POLE_TOP - POLE_BOTTOM) : 1f, poleWidth);
        GL11.glTranslatef(-POLE_X, raise > 0f ? -POLE_BOTTOM : 0f, -POLE_Z);
        if (stripes) {
            bind(stripesTex);
            stripedPole();
            bind(atlas);
        } else {
            model.renderPart("zm_stozar");
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        holderWidth = poleWidth;
        forward = (HOLDER_FRONT - POLE_Z) * (holderWidth - 1f);
        GL11.glTranslatef(0f, raise, forward);
        if (sign) {
            holder("zm_pv_sign_drzak", 0f);
            GL11.glDisable(GL11.GL_CULL_FACE);                           // the sign is a single plate
            model.renderPart("zm_pozor_vlak");
            GL11.glEnable(GL11.GL_CULL_FACE);
        }
        if (cross) {
            renderCross(oneTrack, reflective, large, slovak);
        }
        if (stop) {
            float top = sign ? STOP_TOP : (cross && large && !slovak ? STOP_TOP_LARGE_CROSS : STOP_TOP_NO_SIGN);
            renderStop(top - STOP_SIZE / 2f);
        }
        GL11.glPopMatrix();
        if (mirrored) {
            flipFrontFace();
        }
        GL11.glPopMatrix();
    }

    /** The pole tube (as zm_stozar) with the stripe texture wrapped round it, its top at the top of the image. */
    private static void stripedPole() {
        Tessellator tess = Tessellator.instance;
        tess.startDrawingQuads();
        for (int i = 0; i < POLE_SIDES; i++) {
            double a0 = 2 * Math.PI * i / POLE_SIDES, a1 = 2 * Math.PI * (i + 1) / POLE_SIDES;
            float c0 = (float) Math.cos(a0), s0 = (float) Math.sin(a0), c1 = (float) Math.cos(a1), s1 = (float) Math.sin(a1);
            double u0 = (double) i / POLE_SIDES, u1 = (double) (i + 1) / POLE_SIDES;
            tess.setNormal(c1, 0f, s1);
            tess.addVertexWithUV(POLE_X + POLE_RADIUS * c1, POLE_BOTTOM, POLE_Z + POLE_RADIUS * s1, u1, 1);
            tess.setNormal(c0, 0f, s0);
            tess.addVertexWithUV(POLE_X + POLE_RADIUS * c0, POLE_BOTTOM, POLE_Z + POLE_RADIUS * s0, u0, 1);
            tess.addVertexWithUV(POLE_X + POLE_RADIUS * c0, POLE_TOP, POLE_Z + POLE_RADIUS * s0, u0, 0);
            tess.setNormal(c1, 0f, s1);
            tess.addVertexWithUV(POLE_X + POLE_RADIUS * c1, POLE_TOP, POLE_Z + POLE_RADIUS * s1, u1, 0);
        }
        tess.draw();
        // top cap, in the colour of the top band
        tess.startDrawing(GL11.GL_TRIANGLE_FAN);
        tess.setNormal(0f, 1f, 0f);
        tess.addVertexWithUV(POLE_X, POLE_TOP, POLE_Z, 0.5, 0.01);
        for (int i = POLE_SIDES; i >= 0; i--) {
            double a = 2 * Math.PI * i / POLE_SIDES;
            tess.addVertexWithUV(POLE_X + POLE_RADIUS * Math.cos(a), POLE_TOP, POLE_Z + POLE_RADIUS * Math.sin(a), 0.5, 0.01);
        }
        tess.draw();
    }

    /** The STOP octagon centred on the pole at this height, on a copy of the POZOR VLAK holder. Leaves the atlas bound. */
    private void renderStop(float centreY) {
        holder("zm_pv_sign_drzak", centreY - HOLDER_Y);
        stopFace(centreY, HOLDER_FRONT, false);                         // atlas
        bindBright(stopTex);
        stopFace(centreY, HOLDER_FRONT + STOP_THICKNESS, true);
        bind(atlas);
    }

    private static void stopFace(float centreY, float z, boolean front) {
        float radius = STOP_SIZE / 2f / (float) Math.cos(Math.toRadians(22.5));
        Tessellator tess = Tessellator.instance;
        tess.startDrawing(GL11.GL_TRIANGLE_FAN);
        tess.setNormal(0f, 0f, front ? 1f : -1f);
        if (front) {
            tess.addVertexWithUV(POLE_X, centreY, z, STOP_PX[8][0] / STOP_PX_W, STOP_PX[8][1] / STOP_PX_H);
        } else {
            tess.addVertexWithUV(POLE_X, centreY, z, BACK_U, BACK_V);
        }
        for (int i = 0; i <= 8; i++) {
            int corner = (front ? i : 8 - i) % 8;
            double angle = Math.toRadians(22.5 + 45 * corner);
            float dx = radius * (float) Math.cos(angle), dy = radius * (float) Math.sin(angle);
            if (front) {
                tess.addVertexWithUV(POLE_X + dx, centreY + dy, z,
                        STOP_PX[corner][0] / STOP_PX_W, STOP_PX[corner][1] / STOP_PX_H);
            } else {                                                    // the atlas is twice as wide as high
                tess.addVertexWithUV(POLE_X + dx, centreY + dy, z,
                        BACK_U + dx / STOP_SIZE * BACK_FILL_U, BACK_V - dy / STOP_SIZE * BACK_FILL_U * 2f);
            }
        }
        tess.draw();
    }

    /** Same parts as the AZD 71 cross on a pole (ModelAZD71.renderKriz); the clamp and the backs from the atlas. */
    private void renderCross(boolean oneTrack, boolean reflective, boolean large, boolean slovak) {
        holder("zm_kriz_drzak", 0f);
        if (slovak) {
            bindBright(oneTrack ? crossSk : crossSkX);
            model.renderPart(reflective ? "zm_kriz_sk_refl" : "zm_kriz_sk");
        } else {
            String prefix = oneTrack ? "zm_kriz_1k_" : "zm_kriz_xk_";
            String size = large ? "_velky" : "_maly";
            if (reflective) {
                bindBright(oneTrack ? cross1Refl : (large ? crossXReflLarge : crossXRefl));
                model.renderPart(prefix + "refl" + size);
            } else {
                GL11.glDisable(GL11.GL_CULL_FACE);                       // thin plates
                model.renderPart(prefix + "zadek" + size);
                bindBright(oneTrack ? cross1 : crossX);
                model.renderPart(prefix + "predek" + size);
                GL11.glEnable(GL11.GL_CULL_FACE);
            }
        }
        bind(atlas);
    }
}
