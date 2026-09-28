package signalcraft.models.mechSignals;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.signals.mechSignals.TileMechSignal;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

/**
 * Mechanical shunting signal (serazovaci.obj). Pivots are the object origins from seraz.blend,
 * converted to OBJ space (Blender x, z, -y). The .blend has no keyframes, so the motion ranges
 * below are worked out from the geometry - the ones marked as guesses are safe to tune.
 */
public class ModelMechSerazovaci implements IMechSignalModel {
    private final IModelCustom model = ModelRegistry.MECH_SERAZOVACI.getModel();
    private final ResourceLocation TEXTURE_MAIN = TextureRegistry.MECH_SERAZOVACI.get();
    private final ResourceLocation TEXTURE_BLUE = TextureRegistry.MECH_BLUE.get();
    private final ResourceLocation TEXTURE_WHITE = TextureRegistry.MECH_WHITE.get();
    private final ResourceLocation TEXTURE_LAMP = TextureRegistry.MECH_LAMP.get();
    private final ResourceLocation TEXTURE_CHAIN = TextureRegistry.MECH_CHAIN.get();

    /**
     * "stit" tips back about a horizontal axis through its own origin, from face-on (0 deg,
     * Posun zakázán) to flat (90 deg, Posun dovolen) - its crank arm reaches back and up to
     * tahlo_3's top, so tahlo_3 is pulled down as the board tips.
     */
    private static final float STIT_PIVOT_X = 0.00159f, STIT_PIVOT_Y = 2.79786f, STIT_PIVOT_Z = 0.06624f;
    /** Where tahlo_3's top end joins the stit crank - the rod follows this point. */
    private static final float TAHLO_3_JOINT_Y = 2.8811f, TAHLO_3_JOINT_Z = 0.0075f;

    /**
     * "drzak_clonek" (with the "modra"/"bila" lenses on it) swings about its origin. At rest the
     * blue lens sits in front of the lantern; 76.7 deg is exactly the angle between the two lens
     * centres around this pivot, so at full travel the white lens takes its place.
     */
    private static final float DRZAK_PIVOT_X = 0.08916f, DRZAK_PIVOT_Y = 2.26093f, DRZAK_PIVOT_Z = 0.07563f;
    private static final float DRZAK_MAX_ANGLE = 76.7f;

    /**
     * "tahlo_5" hangs from the stit crank like tahlo_3 (its top joint follows the board), and
     * "tahlo_4" links its bottom (left end) to the lens holder (right end, the hook's tip).
     * tahlo_4 turns about its left end to keep pointing at where the holder has swung that
     * point to, and stretches along its length (~10% at full travel) so it still reaches it.
     */
    private static final float TAHLO_5_JOINT_Y = 2.8312f, TAHLO_5_JOINT_Z = 0.0126f;
    private static final float TAHLO_4_LEFT_X = -0.104f, TAHLO_4_LEFT_Y = 2.223f;
    private static final float TAHLO_4_HOOK_X = 0.1614f, TAHLO_4_HOOK_Y = 2.25f;

    /**
     * "zavazi" is the counterweight lever the pull wires hang from. Its swing angle is a guess
     * (no reference for the real value) - the wires follow whatever it is set to.
     */
    private static final float ZAVAZI_PIVOT_X = -0.05665f, ZAVAZI_PIVOT_Y = 1.90148f, ZAVAZI_PIVOT_Z = 0.00735f;
    private static final float ZAVAZI_MAX_ANGLE = 20f;
    /** Top ends of the pull wires, where they hang from the zavazi lever. */
    private static final float TAHLO_1_TOP_Y = 1.8151f, TAHLO_1_TOP_Z = -0.363f;
    private static final float TAHLO_2_TOP_Y = 1.9772f, TAHLO_2_TOP_Z = 0.217f;

    @Override
    public void renderStoz(TileMechSignal tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_MAIN);
        this.model.renderPart("sloup");
        if (tileSignal.getIsGrupped().toBoolean()) {
            this.model.renderPart("skupinove");
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_CHAIN);
        this.model.renderPart("retez");
    }

    @Override
    public void renderRamena(TileMechSignal tileSignal) {
        float stitAngle = tileSignal.getArm1Rotation();
        float progress = stitAngle / 90f;

        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_MAIN);

        GL11.glPushMatrix();
        GL11.glTranslatef(STIT_PIVOT_X, STIT_PIVOT_Y, STIT_PIVOT_Z);
        GL11.glRotatef(-stitAngle, 1f, 0f, 0f);
        GL11.glTranslatef(-STIT_PIVOT_X, -STIT_PIVOT_Y, -STIT_PIVOT_Z);
        this.model.renderPart("stit");
        GL11.glPopMatrix();

        float[] tahlo3 = rotateAboutX(TAHLO_3_JOINT_Y - STIT_PIVOT_Y, TAHLO_3_JOINT_Z - STIT_PIVOT_Z, -stitAngle);
        GL11.glPushMatrix();
        GL11.glTranslatef(0f, tahlo3[0], tahlo3[1]);
        this.model.renderPart("tahlo_3");
        GL11.glPopMatrix();

        float zavaziAngle = progress * ZAVAZI_MAX_ANGLE;
        GL11.glPushMatrix();
        GL11.glTranslatef(ZAVAZI_PIVOT_X, ZAVAZI_PIVOT_Y, ZAVAZI_PIVOT_Z);
        GL11.glRotatef(zavaziAngle, 1f, 0f, 0f);
        GL11.glTranslatef(-ZAVAZI_PIVOT_X, -ZAVAZI_PIVOT_Y, -ZAVAZI_PIVOT_Z);
        this.model.renderPart("zavazi");
        GL11.glPopMatrix();

        float[] tahlo1 = rotateAboutX(TAHLO_1_TOP_Y - ZAVAZI_PIVOT_Y, TAHLO_1_TOP_Z - ZAVAZI_PIVOT_Z, zavaziAngle);
        GL11.glPushMatrix();
        GL11.glTranslatef(0f, tahlo1[0], tahlo1[1]);
        this.model.renderPart("tahlo_1");
        GL11.glPopMatrix();

        float[] tahlo2 = rotateAboutX(TAHLO_2_TOP_Y - ZAVAZI_PIVOT_Y, TAHLO_2_TOP_Z - ZAVAZI_PIVOT_Z, zavaziAngle);
        GL11.glPushMatrix();
        GL11.glTranslatef(0f, tahlo2[0], tahlo2[1]);
        this.model.renderPart("tahlo_2");
        GL11.glPopMatrix();

        float drzakAngle = progress * DRZAK_MAX_ANGLE;
        renderLinkage(stitAngle, drzakAngle);

        // svetlo is the lantern's fixed light source - it doesn't swing with the lens holder, and
        // is opaque, so it's drawn before the semi-transparent lenses to blend correctly under them
        MechLensRenderer.renderLamp(this.model, TEXTURE_LAMP, "svetlo");

        GL11.glPushMatrix();
        GL11.glTranslatef(DRZAK_PIVOT_X, DRZAK_PIVOT_Y, DRZAK_PIVOT_Z);
        GL11.glRotatef(drzakAngle, 0f, 0f, 1f);
        GL11.glTranslatef(-DRZAK_PIVOT_X, -DRZAK_PIVOT_Y, -DRZAK_PIVOT_Z);
        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_MAIN);
        this.model.renderPart("drzak_clonek");
        // only the lens currently in front of svetlo is lit
        MechLensRenderer.begin();
        MechLensRenderer.renderLens(this.model, TEXTURE_BLUE, "modra", 1f - progress);
        MechLensRenderer.renderLens(this.model, TEXTURE_WHITE, "bila", progress);
        MechLensRenderer.end();
        GL11.glPopMatrix();

        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_MAIN);
    }

    /** tahlo_5 + tahlo_4 - see the TAHLO_5_* / TAHLO_4_* constants. */
    private void renderLinkage(float stitAngle, float drzakAngle) {
        float[] tahlo5 = rotateAboutX(TAHLO_5_JOINT_Y - STIT_PIVOT_Y, TAHLO_5_JOINT_Z - STIT_PIVOT_Z, -stitAngle);

        double rad = Math.toRadians(drzakAngle);
        double hookDx = TAHLO_4_HOOK_X - DRZAK_PIVOT_X, hookDy = TAHLO_4_HOOK_Y - DRZAK_PIVOT_Y;
        double hookX = DRZAK_PIVOT_X + hookDx * Math.cos(rad) - hookDy * Math.sin(rad);
        double hookY = DRZAK_PIVOT_Y + hookDx * Math.sin(rad) + hookDy * Math.cos(rad);
        double restDx = TAHLO_4_HOOK_X - TAHLO_4_LEFT_X, restDy = TAHLO_4_HOOK_Y - TAHLO_4_LEFT_Y;
        double dx = hookX - TAHLO_4_LEFT_X, dy = hookY - (TAHLO_4_LEFT_Y + tahlo5[0]);
        float restAngle = (float) Math.toDegrees(Math.atan2(restDy, restDx));
        float linkAngle = (float) Math.toDegrees(Math.atan2(dy, dx)) - restAngle;
        float stretch = (float) (Math.sqrt(dx * dx + dy * dy) / Math.sqrt(restDx * restDx + restDy * restDy));

        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_MAIN);
        GL11.glPushMatrix();
        GL11.glTranslatef(0f, tahlo5[0], tahlo5[1]);
        this.model.renderPart("tahlo_5");
        GL11.glTranslatef(TAHLO_4_LEFT_X, TAHLO_4_LEFT_Y, 0f);
        GL11.glRotatef(linkAngle + restAngle, 0f, 0f, 1f);
        GL11.glScalef(stretch, 1f, 1f);
        GL11.glRotatef(-restAngle, 0f, 0f, 1f);
        GL11.glTranslatef(-TAHLO_4_LEFT_X, -TAHLO_4_LEFT_Y, 0f);
        this.model.renderPart("tahlo_4");
        GL11.glPopMatrix();
    }

    /** Offset {dy, dz} that a point at {y, z} from a pivot gets when rotated about X like glRotatef(angle, 1, 0, 0). */
    private static float[] rotateAboutX(float y, float z, float angle) {
        double rad = Math.toRadians(angle);
        float cos = (float) Math.cos(rad), sin = (float) Math.sin(rad);
        return new float[]{y * cos - z * sin - y, y * sin + z * cos - z};
    }

    @Override
    public void renderItem(TileMechSignal tileSignal) {
        renderStoz(tileSignal);
        renderRamena(tileSignal);
    }
}
