package signalcraft.models.levelCrossing.mech;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.levelCrossings.mech.MechBarrierKinematics;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;
import signalcraft.models.levelCrossing.BoomBend;

/**
 * CSD mechanical barrier PZM 1 / PZM 2 (models/mech/zavora_mech.obj, built in Blender - see
 * modely/Moje Modely/mech/zavora_mech_src/README.txt).
 *
 * The OBJ is modelled with the boom CLOSED (horizontal, pointing to -X). theta is how far the boom is
 * raised: 0 = closed, 85 = open. The boom turns about PIVOT (axis Z). The cranked wheel shaft turns about
 * KOLO and drives the boom through a link (zm_uchyty) between the crank pin and the strap on the boom -
 * a four-bar linkage, so the crank angle is solved from theta.
 * The wheels and the tall post with the bell are on -Z; a barrier with the boom on the other side is
 * this model mirrored in X by TileMechBarrierRenderer.
 * In front of the low post hangs a round pole with the warning cross and the "POZOR VLAK" sign
 * (ModelCrossSignPole, also used by the stand-alone pole block).
 */
public class ModelMechBarrier
{
    // geometry in OBJ units (1 m = 1/1.5) - see MechBarrierKinematics
    public static final float PIVOT_X = MechBarrierKinematics.PIVOT_X, PIVOT_Y = MechBarrierKinematics.PIVOT_Y;
    public static final float KOLO_X = MechBarrierKinematics.KOLO_X, KOLO_Y = MechBarrierKinematics.KOLO_Y;
    public static final double CLAMP = MechBarrierKinematics.CLAMP;
    public static final double CRANK = MechBarrierKinematics.CRANK;
    public static final double PHI0 = MechBarrierKinematics.PHI0;
    public static final float LATTICE_DROP = 0.48f;  // lattice bar length (boom axis -> bottom rail)
    /**
     * lattice bar hinge relative to the bar's place on the boom axis: the top of the eye of zm_mriz_tyc, at the boom's
     * underside - folding up, the eye swings to the boom instead of standing off it
     */
    private static final float LATTICE_EYE_X = 0.006222f, LATTICE_EYE_Y = -0.040f;
    /**
     * the lattice folds (bars vertical) only up to this boom angle: then the bars (0.0187 wide, 0.1 apart) lie
     * against each other and the folded lattice rises with the boom
     */
    public static final float LATTICE_MAX_FOLD = 77f;
    public static final float SCALE = 1.5f;         // metres per OBJ unit (the OBJ is exported at 1 m = 1/1.5)
    public static final float RENDER_SCALE = 1.5f;  // OBJ units -> blocks in the world (1 m = 1 block)
    /** chain texture tiles per radian of wheel turn: chain radius 0.272 m * 20 links per metre */
    /** the boom leaves the cast sleeve at BOOM_ROOT_X (on the axis through PIVOT_Y) and runs to -X; its tip for 4 m */
    private static final float BOOM_ROOT_X = -0.3478f, BOOM_TIP_X_4M = -2.7538f;
    public static final double CHAIN_TILES_PER_RAD = 0.272 * 20.0;
    // bell hammers (PZM 1): pivots at the striking mechanism, swing in the XY plane
    public static final float HAMMER_L_X = -0.01428f, HAMMER_R_X = 0.02672f, HAMMER_Y = 1.28665f;
    /** drive wheel turn with the barrier open (the wheel face texture is the photo of an open barrier) */
    private static final float WHEEL_TURN_OPEN = MechBarrierKinematics.wheelTurn(85);

    private final IModelCustom model = ModelRegistry.MECH_BARRIER.getModel();
    /** one atlas for the parts (the sign and the wheel face included); the chain and the cross faces are separate */
    private final ResourceLocation atlas = TextureRegistry.MECH_BARRIER.get();
    private final ResourceLocation chain = TextureRegistry.MECH_CHAIN.get();
    /** the round pole with the sign and the cross hanging on the low post */
    private final ModelCrossSignPole signPoleModel = new ModelCrossSignPole();

    private static void bind(ResourceLocation tex) {
        Minecraft.getMinecraft().renderEngine.bindTexture(tex);
    }

    /** Crank angle (radians, from +X towards +Y) for a boom raised by thetaDeg. */
    public static double crankAngle(double thetaDeg) {
        return MechBarrierKinematics.crankAngle(thetaDeg);
    }

    /** Everything, in OBJ units, for a boom raised by theta degrees. */
    public void render(float theta, boolean pzm1, int boomMetres, boolean lattice) {
        render(theta, pzm1, boomMetres, lattice, 0f, 0f);
    }

    /**
     * bellWheelAngle: pre-ringing wheel (PZM 1) angle, degrees (turns at its own constant speed);
     * hammerPull: how far both hammers are drawn back from the bell, degrees (0 = on the bell).
     */
    public void render(float theta, boolean pzm1, int boomMetres, boolean lattice, float bellWheelAngle, float hammerPull) {
        render(theta, pzm1, boomMetres, lattice, bellWheelAngle, hammerPull, false, false, false, false, false, false, false, false);
    }

    /**
     * signPole: the round pole; poleSign: the "POZOR VLAK" sign on it; cross: the warning cross on it, for one track
     * (crossOneTrack) or more tracks, reflective / painted, large / small, Slovak (see ModelCrossSignPole);
     * mirrored: the caller renders the barrier mirrored in X - the pole is mirrored back so the sign stays readable.
     */
    public void render(float theta, boolean pzm1, int boomMetres, boolean lattice, float bellWheelAngle, float hammerPull,
                       boolean signPole, boolean poleSign, boolean cross, boolean crossOneTrack, boolean crossReflective, boolean crossLarge,
                       boolean crossSlovak, boolean mirrored) {
        render(theta, pzm1, boomMetres, lattice, bellWheelAngle, hammerPull, signPole, poleSign, cross, crossOneTrack,
                crossReflective, crossLarge, crossSlovak, mirrored, 0f);
    }

    /** flex: the boom's bend, degrees at its tip, + = further down (see BoomFlex) */
    public void render(float theta, boolean pzm1, int boomMetres, boolean lattice, float bellWheelAngle, float hammerPull,
                       boolean signPole, boolean poleSign, boolean cross, boolean crossOneTrack, boolean crossReflective, boolean crossLarge,
                       boolean crossSlovak, boolean mirrored, float flex) {
        double phi = crankAngle(Math.max(0f, theta));          // the crank stays at its end while the boom swings past
        float wheelTurn = (float) Math.toDegrees(phi - PHI0);
        int len = Math.max(4, Math.min(8, boomMetres));
        bind(atlas);
        model.renderPart("zm_sloup");
        model.renderPart("zm_sloupek");
        model.renderPart("zm_lozisko");
        model.renderPart("zm_kladka");
        if (pzm1) {
            GL11.glPushMatrix();
            GL11.glTranslatef(KOLO_X, KOLO_Y, 0f);
            GL11.glRotatef(bellWheelAngle, 0f, 0f, 1f);
            GL11.glTranslatef(-KOLO_X, -KOLO_Y, 0f);
            if (mirrored) {
                // keep the cast lettering readable: mirror the (round) wheel back about its own axis
                GL11.glTranslatef(KOLO_X, 0f, 0f);
                GL11.glScalef(-1f, 1f, 1f);
                GL11.glTranslatef(-KOLO_X, 0f, 0f);
                ModelCrossSignPole.flipFrontFace();
            }
            model.renderPart("zm_kolo2");
            if (mirrored) {
                ModelCrossSignPole.flipFrontFace();
            }
            GL11.glPopMatrix();
            GL11.glDisable(GL11.GL_CULL_FACE);                       // thin hood shell
            model.renderPart("zm_kryt2");
            GL11.glEnable(GL11.GL_CULL_FACE);
            model.renderPart("zm_kladka2");
            GL11.glDisable(GL11.GL_CULL_FACE);                       // thin umbrella shell, seen from below
            model.renderPart("zm_zvonek");
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glPushMatrix();                                      // left hammer: drawn back = clockwise
            GL11.glTranslatef(HAMMER_L_X, HAMMER_Y, 0f);
            GL11.glRotatef(-hammerPull, 0f, 0f, 1f);
            GL11.glTranslatef(-HAMMER_L_X, -HAMMER_Y, 0f);
            model.renderPart("zm_kladivko_l");
            GL11.glPopMatrix();
            GL11.glPushMatrix();                                      // right hammer: drawn back = anticlockwise
            GL11.glTranslatef(HAMMER_R_X, HAMMER_Y, 0f);
            GL11.glRotatef(hammerPull, 0f, 0f, 1f);
            GL11.glTranslatef(-HAMMER_R_X, -HAMMER_Y, 0f);
            model.renderPart("zm_kladivko_r");
            GL11.glPopMatrix();
        } else {
            GL11.glDisable(GL11.GL_CULL_FACE);                       // thin hood shell
            model.renderPart("zm_kryt");
            GL11.glEnable(GL11.GL_CULL_FACE);
        }

        // arm: cast sleeve, counterweight, boom
        GL11.glPushMatrix();
        GL11.glTranslatef(PIVOT_X, PIVOT_Y, 0f);
        GL11.glRotatef(-theta, 0f, 0f, 1f);
        GL11.glTranslatef(-PIVOT_X, -PIVOT_Y, 0f);
        model.renderPart("zm_objimka");
        GL11.glDisable(GL11.GL_CULL_FACE);
        model.renderPart("zm_zavazi");
        GL11.glEnable(GL11.GL_CULL_FACE);
        BoomBend.render(BOOM_ROOT_X, PIVOT_Y, -1f, 0f, boomLength(len), flex, () -> model.renderPart("zm_brevno_" + len + "0"));
        GL11.glPopMatrix();

        // wheel + cranked shaft
        GL11.glPushMatrix();
        GL11.glTranslatef(KOLO_X, KOLO_Y, 0f);
        GL11.glRotatef(wheelTurn, 0f, 0f, 1f);
        GL11.glTranslatef(-KOLO_X, -KOLO_Y, 0f);
        model.renderPart("zm_kolo");
        // painted face with the "UZAVRIT" sign: in a mirrored barrier mirror it back about the wheel axis and
        // turn it so it still looks like the photo with the barrier open
        GL11.glPushMatrix();
        if (mirrored) {
            GL11.glTranslatef(KOLO_X, KOLO_Y, 0f);
            GL11.glRotatef(-2f * WHEEL_TURN_OPEN, 0f, 0f, 1f);
            GL11.glScalef(-1f, 1f, 1f);
            GL11.glTranslatef(-KOLO_X, -KOLO_Y, 0f);
            ModelCrossSignPole.flipFrontFace();
        }
        model.renderPart("zm_kolo_celo");
        if (mirrored) {
            ModelCrossSignPole.flipFrontFace();
        }
        GL11.glPopMatrix();
        GL11.glPopMatrix();

        // link: strap end follows the boom, crank end follows the crank pin
        double t = Math.toRadians(theta);
        double cx = PIVOT_X - CLAMP * Math.cos(t), cy = PIVOT_Y + CLAMP * Math.sin(t);
        double c0x = PIVOT_X - CLAMP, c0y = PIVOT_Y;
        double kx = KOLO_X + CRANK * Math.cos(phi), ky = KOLO_Y + CRANK * Math.sin(phi);
        double k0x = KOLO_X + CRANK * Math.cos(PHI0), k0y = KOLO_Y + CRANK * Math.sin(PHI0);
        double alpha = Math.atan2(ky - cy, kx - cx), alpha0 = Math.atan2(k0y - c0y, k0x - c0x);
        GL11.glPushMatrix();
        GL11.glTranslated(cx, cy, 0);
        GL11.glRotatef((float) Math.toDegrees(alpha - alpha0), 0f, 0f, 1f);
        GL11.glTranslated(-c0x, -c0y, 0);
        model.renderPart("zm_uchyty");
        GL11.glPopMatrix();

        if (lattice) {
            renderLattice(theta, len, flex);
        }
        if (signPole) {
            signPoleModel.render(poleSign, true, cross, crossOneTrack, crossReflective, crossLarge, crossSlovak, mirrored);
        }

        // chains run round the wheels: scroll their texture with the wheel turn (left strands have flipped V,
        // so the same offset moves one side up and the other down)
        bind(chain);
        GL11.glDisable(GL11.GL_CULL_FACE);
        renderChain("zm_retezy", wheelTurn);
        if (pzm1) {
            renderChain("zm_retezy2", bellWheelAngle);
        }
        GL11.glEnable(GL11.GL_CULL_FACE);
    }

    /** boom length from the sleeve to the tip, OBJ units */
    private static float boomLength(int len) {
        return BOOM_ROOT_X - (BOOM_TIP_X_4M - (len - 4) / SCALE);
    }

    private void renderChain(String part, float wheelTurnDeg) {
        float shift = (float) ((Math.toRadians(wheelTurnDeg) * CHAIN_TILES_PER_RAD) % 1.0);
        GL11.glMatrixMode(GL11.GL_TEXTURE);
        GL11.glPushMatrix();
        GL11.glTranslatef(0f, shift, 0f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        model.renderPart(part);
        GL11.glMatrixMode(GL11.GL_TEXTURE);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    /**
     * Hanging lattice, drawn in the boom's frame: the bars swing on their eyes under the boom, the bottom rail stays
     * parallel to the boom. The bars hang vertical until the lattice is folded (LATTICE_MAX_FOLD), then turn with it.
     */
    private void renderLattice(float theta, int len, float flex) {
        float fold = Math.min(theta, LATTICE_MAX_FOLD);
        double f = Math.toRadians(fold);
        GL11.glPushMatrix();
        GL11.glTranslatef(PIVOT_X, PIVOT_Y, 0f);
        GL11.glRotatef(-theta, 0f, 0f, 1f);
        GL11.glTranslatef(-PIVOT_X, -PIVOT_Y, 0f);
        // the rail (LATTICE_DROP under the boom axis) hangs on the bars: turned by the fold about their eyes
        double eyeToRail = LATTICE_DROP + LATTICE_EYE_Y;
        BoomBend.render(BOOM_ROOT_X, PIVOT_Y, -1f, 0f, boomLength(len), flex, () -> {
            GL11.glPushMatrix();
            GL11.glTranslated(eyeToRail * Math.sin(f), eyeToRail * (1 - Math.cos(f)), 0);
            model.renderPart("zm_mriz_spodek_" + len + "0");
            GL11.glPopMatrix();
        });
        for (double d = 0.70; d <= len - 0.45 + 1e-6; d += 0.15) {
            GL11.glPushMatrix();
            // each bar hangs on the bent boom
            BoomBend.follow(BOOM_ROOT_X, PIVOT_Y, -1f, 0f, boomLength(len), flex, BOOM_ROOT_X - (PIVOT_X - (float) d / SCALE));
            GL11.glTranslated(PIVOT_X - d / SCALE + LATTICE_EYE_X, PIVOT_Y + LATTICE_EYE_Y, 0);
            GL11.glRotatef(fold, 0f, 0f, 1f);
            GL11.glTranslatef(-LATTICE_EYE_X, -LATTICE_EYE_Y, 0f);
            model.renderPart("zm_mriz_tyc");
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }
}
