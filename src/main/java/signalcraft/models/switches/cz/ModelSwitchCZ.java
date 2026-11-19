package signalcraft.models.switches.cz;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.switches.cz.TileSwitchCZ;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

/**
 * Czech point machines (models/prestavnik/prestavnik_cz.obj, sources in modely/Moje Modely/prestavnik): the
 * hand-operated switch stand with the lever, the weight and the switch signal, and the electric point machine
 * EP 600 / EP-6xx. OBJ units are blocks (1 m): the block centre at the origin, the track along X with its axis at
 * Z = 1 (the neighbouring block), throw rods reaching over the TrainCraft rails.
 * The machines are drawn scaled down towards the track (scale); the rods and the sleepers keep their ends at the
 * track and are stretched to the machine, so they still fit the TrainCraft rails and sleepers.
 */
public class ModelSwitchCZ
{
    /** the stand: spindle on the Y axis at STAND_Z */
    public static final float STAND_Z = -0.15f;
    /**
     * the shaft (axis along X at SHAFT_Y, STAND_Z); the weight lever turns freely on it, from REST over the top to END
     * (degrees up from towards the track)
     */
    public static final float SHAFT_Y = 0.27f, LEVER_REST = 45f, LEVER_END = 135f;
    /**
     * the T fixed on the shaft (plane T_X): the weight lever rests on one of its arms when it is T_CONTACT degrees
     * off the T's centre line, so the T stands T_CONTACT inside the lever's end positions; the pin at the end of its
     * leg (T_LEG) sits in the forked end of the throw rod
     */
    public static final float T_X = 0.232f, T_CONTACT = 23.6f, T_LEG = 0.24f;
    public static final float T_REST = LEVER_REST + T_CONTACT, T_END = LEVER_END - T_CONTACT;
    /** pin on the shaft hub (along the T's centre line, PIN_X beside the spindle) in the spindle's U-fork at FORK_Y */
    public static final float PIN_X = 0.045f, FORK_Y = 0.385f;
    /**
     * switch number plate: a sheet bent at 90 degrees round the spindle, its faces at PLATE_FACE in +X and +Z, each
     * PLATE_WIDTH wide (centred PLATE_CENTRE off the spindle axis), centre height PLATE_Y
     */
    public static final float PLATE_FACE = 0.019f, PLATE_WIDTH = 0.149f, PLATE_CENTRE = -0.0555f, PLATE_Y = 0.57f;
    /** throw rod travel towards the track; TrainCraft's blades are short, so less than the real 220 mm */
    public static final float ROD_STROKE = 0.05f;
    /** the stand beside the electric machine: the next sleeper bay along the track */
    public static final float STAND_BESIDE_EP = -0.5f;
    /** the machines are scaled about the block edge at the track, on the sleeper tops */
    public static final float ANCHOR_Z = 0.5f, BASE_Y = 0.03f;
    /** track-side ends of the stretched parts (rods beyond the far rail, sleepers at the TrainCraft sleeper ends) */
    private static final float ROD_END = 1.37f, CHECK_ROD_END = 1.33f, SLEEPER_END = 0.53f;
    /** stand's throw rod: modelled from its forked end (at the T's leg pin in the normal position) */
    private static final float STAND_ROD_START = (float) (STAND_Z - T_LEG * Math.cos(Math.toRadians(T_REST))) + 0.02f;

    private final IModelCustom model = ModelRegistry.PRESTAVNIK_CZ.getModel();
    private final ResourceLocation texture = TextureRegistry.PRESTAVNIK_CZ.get();

    private static float ease(float p) {
        p = Math.max(0f, Math.min(1f, p));
        return p * p * (3f - 2f * p);
    }

    /**
     * weight lever angle (up from towards the track): lifted from rest and gaining speed, it falls over the top onto
     * the other side without stopping; thrown: towards END, else back towards REST
     */
    public static float leverAngle(float p, boolean thrown) {
        float travel = LEVER_END - LEVER_REST;
        if (thrown) {
            return LEVER_REST + travel * p * p * (2f - p);
        }
        float t = 1f - p;
        return LEVER_END - travel * t * t * (2f - t);
    }

    /**
     * the T's angle (its centre line, up from towards the track): the weight swings free until it lands on the T's
     * other arm and throws the T over with it
     */
    public static float tAngle(float lever, boolean thrown) {
        return thrown ? Math.max(T_REST, lever - T_CONTACT) : Math.min(T_END, lever + T_CONTACT);
    }

    private static double forkAngle(float t) {
        return Math.atan2(-(FORK_Y - SHAFT_Y) / Math.tan(Math.toRadians(t)), PIN_X);
    }

    /** the spindle: turned by the pin (with the T) in the fork - a quarter turn while the T goes over */
    public static float spindleAngle(float t) {
        return (float) Math.toDegrees(forkAngle(t) - forkAngle(T_REST));
    }

    /** throw rod of the stand: follows the pin at the end of the T's leg */
    public static float standRodTravel(float t) {
        return (float) (T_LEG * (Math.cos(Math.toRadians(T_REST)) - Math.cos(Math.toRadians(t))));
    }

    /** throw rods of the electric machine: the blades go over around the middle of the throw */
    public static float rodTravel(float p) {
        return ROD_STROKE * ease((p - 0.15f) / 0.7f);
    }

    /**
     * hand-operated stand; progress 0 .. 1, thrown: going (or being) thrown; number: switch number on the plate;
     * mirrored: the caller mirrors in X (the lantern arrows and the number are kept right)
     */
    public void renderManual(float progress, boolean thrown, int signal, boolean rightTurnout, boolean yellowWeight,
                             String number, float scale, boolean mirrored) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
        this.renderStand(progress, thrown, false, signal, rightTurnout, yellowWeight, number, scale, mirrored);
    }

    /** electric point machine, optionally with the stand (yellow weight) in the next sleeper bay along the track */
    public void renderElectric(float progress, boolean thrown, boolean ep6xx, boolean stand, int signal, boolean rightTurnout,
                               String number, float scale, boolean mirrored) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
        float rod = rodTravel(progress);
        GL11.glPushMatrix();
        GL11.glTranslatef(0f, 0f, rod);
        stretch(0.40f, ROD_END, scale);
        this.model.renderPart("ep_tahlo");
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(0f, 0f, rod);
        stretch(0.40f, CHECK_ROD_END, scale);
        this.model.renderPart("ep_kontrolni");
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        machine(scale, 0f);
        this.model.renderPart("ep_ram");
        this.model.renderPart("ep_skrin");
        this.model.renderPart(ep6xx ? "ep_viko_6xx" : "ep_viko_600");
        this.model.renderPart("ep_svorkovnice");
        this.model.renderPart("ep_svorkovnice_viko");
        this.model.renderPart("ep_kabel");
        GL11.glPopMatrix();
        if (stand) {
            GL11.glPushMatrix();
            GL11.glTranslatef(STAND_BESIDE_EP, 0f, 0f);
            this.renderStand(progress, thrown, true, signal, rightTurnout, true, number, scale, mirrored);
            GL11.glPopMatrix();
        }
    }

    /**
     * scales the machine towards the track, keeping X = anchorX (its throw rod's line, so the rod stays between
     * TrainCraft's sleepers and in the machine's fork)
     */
    private static void machine(float scale, float anchorX) {
        GL11.glTranslatef(anchorX, BASE_Y, ANCHOR_Z);
        GL11.glScalef(scale, scale, scale);
        GL11.glTranslatef(-anchorX, -BASE_Y, -ANCHOR_Z);
    }

    /** a part modelled from z0 (at the machine) to z1 (at the track): z1 stays, z0 goes with the scaled machine */
    private static void stretch(float z0, float z1, float scale) {
        float z0Scaled = ANCHOR_Z + (z0 - ANCHOR_Z) * scale;
        GL11.glTranslatef(0f, 0f, z0Scaled);
        GL11.glScalef(1f, 1f, (z1 - z0Scaled) / (z1 - z0));
        GL11.glTranslatef(0f, 0f, -z0);
    }

    /**
     * motor: the electric machine drives the T (and the shaft) evenly and the weight lever goes over with it, from
     * resting on one arm to the other; by hand the weight is thrown and throws the T
     */
    private void renderStand(float progress, boolean thrown, boolean motor, int signal, boolean rightTurnout,
                             boolean yellowWeight, String number, float scale, boolean mirrored) {
        float lever, t;
        if (motor) {
            float e = ease(progress);
            lever = LEVER_REST + (LEVER_END - LEVER_REST) * e;
            t = T_REST + (T_END - T_REST) * e;
        } else {
            lever = leverAngle(progress, thrown);
            t = tAngle(lever, thrown);
        }
        // the rod follows the (scaled) T's leg: travel in the machine's frame, rod in the block's
        float spindle = spindleAngle(t), travel = standRodTravel(t), rod = travel * scale;
        GL11.glPushMatrix();
        stretch(-0.45f, SLEEPER_END, scale);
        GL11.glTranslatef(T_X, 0f, 0f);                    // as wide as the scaled stand on it
        GL11.glScalef(scale, 1f, 1f);
        GL11.glTranslatef(-T_X, 0f, 0f);
        this.model.renderPart("vm_prazce");
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(0f, 0f, rod);
        stretch(STAND_ROD_START, ROD_END, scale);
        this.model.renderPart("vm_tahlo");
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        machine(scale, T_X);
        this.model.renderPart("vm_stojan");
        GL11.glPushMatrix();
        GL11.glTranslatef(0f, 0f, travel);
        this.model.renderPart("vm_tahlo_vidlice");
        GL11.glPopMatrix();
        // weight lever (free on the shaft) and the T (fixed on it): both turn about the shaft (X axis)
        GL11.glPushMatrix();
        GL11.glTranslatef(0f, SHAFT_Y, STAND_Z);
        GL11.glRotatef(-lever, 1f, 0f, 0f);
        GL11.glTranslatef(0f, -SHAFT_Y, -STAND_Z);
        this.model.renderPart("vm_paka");
        this.model.renderPart(yellowWeight ? "vm_zavazi_zlute" : "vm_zavazi");
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(0f, SHAFT_Y, STAND_Z);
        GL11.glRotatef(-t, 1f, 0f, 0f);
        GL11.glTranslatef(0f, -SHAFT_Y, -STAND_Z);
        this.model.renderPart("vm_prevod");
        GL11.glPopMatrix();
        // spindle with the number plate and the switch signal
        GL11.glPushMatrix();
        GL11.glTranslatef(0f, 0f, STAND_Z);
        GL11.glRotatef(spindle, 0f, 1f, 0f);
        GL11.glTranslatef(0f, 0f, -STAND_Z);
        this.model.renderPart("vm_vreteno");
        this.model.renderPart("vm_stitek");
        if (signal != TileSwitchCZ.SIGNAL_NONE) {
            this.renderSignal(signal, rightTurnout != mirrored);
        }
        this.renderNumber(number, mirrored);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    /** the lantern: modelled for a switch turning off to the left (arrows up-left), mirrored across its faces otherwise */
    private void renderSignal(int signal, boolean rightTurnout) {
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT | GL11.GL_LIGHTING_BIT);
        if (rightTurnout) {
            GL11.glScalef(-1f, 1f, 1f);
            flipFrontFace();
        }
        this.model.renderPart("vm_nav_skrin");
        // lit from inside: the signal faces at full brightness
        float lastX = OpenGlHelper.lastBrightnessX, lastY = OpenGlHelper.lastBrightnessY;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
        GL11.glDisable(GL11.GL_LIGHTING);
        this.model.renderPart("vm_nav_ctverec");
        this.model.renderPart(signal == TileSwitchCZ.SIGNAL_1997 ? "vm_nav_bok_sipka" : "vm_nav_bok_cerny");
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastX, lastY);
        if (rightTurnout) {
            flipFrontFace();
        }
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    /** switch number, black on both faces of the bent plate (in the spindle's frame) */
    private void renderNumber(String number, boolean mirrored) {
        if (number == null || number.isEmpty()) {
            return;
        }
        FontRenderer font = Minecraft.getMinecraft().fontRenderer;
        int width = font.getStringWidth(number);
        float scale = Math.min(0.085f / 8f, 0.85f * PLATE_WIDTH / Math.max(width, 1));
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT | GL11.GL_LIGHTING_BIT | GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_CURRENT_BIT);
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_LIGHTING);
        for (int face = 0; face < 2; face++) {
            // face 0 looks along +X (its width along Z), face 1 along +Z (its width along X)
            GL11.glPushMatrix();
            if (face == 0) {
                GL11.glTranslatef(PLATE_FACE + 0.001f, PLATE_Y, STAND_Z + PLATE_CENTRE);
                GL11.glRotatef(90f, 0f, 1f, 0f);
            } else {
                GL11.glTranslatef(PLATE_CENTRE, PLATE_Y, STAND_Z + PLATE_FACE + 0.001f);
            }
            GL11.glScalef(mirrored ? -scale : scale, -scale, scale);
            GL11.glNormal3f(0f, 0f, 1f);
            font.drawString(number, -width / 2, -4, 0x000000);
            GL11.glPopMatrix();
        }
        GL11.glPopAttrib();
        GL11.glColor4f(1f, 1f, 1f, 1f);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
    }

    /** Swaps the front face winding (call before and after drawing under a mirroring transform). */
    public static void flipFrontFace() {
        GL11.glFrontFace(GL11.glGetInteger(GL11.GL_FRONT_FACE) == GL11.GL_CCW ? GL11.GL_CW : GL11.GL_CCW);
    }
}
