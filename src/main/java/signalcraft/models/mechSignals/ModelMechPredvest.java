package signalcraft.models.mechSignals;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.signals.mechSignals.TileMechSignal;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class ModelMechPredvest implements IMechSignalModel {
    private final IModelCustom model = ModelRegistry.MECH_PREDVEST.getModel();
    private final ResourceLocation TEXTURE_MAIN = TextureRegistry.MECH_PREDVEST.get();

    /**
     * "stit" tips about a horizontal axis running through its own centre, from face-on
     * (0 deg, Vystraha, board visible) to flat/edge-on (90 deg, Volno, board hidden) -
     * confirmed with the model author. Pivot is estimated from the exported geometry's
     * bounding-box centre; nudge it if it doesn't line up with the real hinge in-game.
     */
    private static final float STIT_PIVOT_X = 0f, STIT_PIVOT_Y = 2.6505f, STIT_PIVOT_Z = 0.0674f;

    /**
     * "zavazi" swings on the same linkage as "stit". Axis/pivot/range are a first guess
     * from the reference stills (not yet confirmed) - tune once the real values are known.
     */
    private static final float ZAVAZI_PIVOT_X = 0.055981f, ZAVAZI_PIVOT_Y = 1.97259f, ZAVAZI_PIVOT_Z = -0.080067f;
    private static final float ZAVAZI_MAX_ANGLE = 50f;

    /**
     * "tahlo_1/2/3" slide rather than rotate. Directions below are each rod's own long axis
     * as measured from the exported geometry; travel distance is a placeholder to tune.
     */
    private static final float TAHLO_1_DIR_X = 0f, TAHLO_1_DIR_Y = -5.5f, TAHLO_1_DIR_Z = -0.3f;
    private static final float TAHLO_2_DIR_X = 0f, TAHLO_2_DIR_Y = 5f, TAHLO_2_DIR_Z = 0.6f;
    private static final float TAHLO_3_DIR_X = 0f, TAHLO_3_DIR_Y = -2f, TAHLO_3_DIR_Z = -0.2f;
    private static final float TAHLO_MAX_TRAVEL = 0.05f;

    @Override
    public void renderStoz(TileMechSignal tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_MAIN);
        this.model.renderPart("sloup");
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

        GL11.glPushMatrix();
        GL11.glTranslatef(ZAVAZI_PIVOT_X, ZAVAZI_PIVOT_Y, ZAVAZI_PIVOT_Z);
        GL11.glRotatef(progress * ZAVAZI_MAX_ANGLE, 1f, 0f, 0f);
        GL11.glTranslatef(-ZAVAZI_PIVOT_X, -ZAVAZI_PIVOT_Y, -ZAVAZI_PIVOT_Z);
        this.model.renderPart("zavazi");
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(TAHLO_1_DIR_X * TAHLO_MAX_TRAVEL * progress, TAHLO_1_DIR_Y * TAHLO_MAX_TRAVEL * progress, TAHLO_1_DIR_Z * TAHLO_MAX_TRAVEL * progress);
        this.model.renderPart("tahlo_1");
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(TAHLO_2_DIR_X * TAHLO_MAX_TRAVEL * progress, TAHLO_2_DIR_Y * TAHLO_MAX_TRAVEL * progress, TAHLO_2_DIR_Z * TAHLO_MAX_TRAVEL * progress);
        this.model.renderPart("tahlo_2");
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(TAHLO_3_DIR_X * TAHLO_MAX_TRAVEL * progress, TAHLO_3_DIR_Y * TAHLO_MAX_TRAVEL * progress, TAHLO_3_DIR_Z * TAHLO_MAX_TRAVEL * progress);
        this.model.renderPart("tahlo_3");
        GL11.glPopMatrix();
    }

    @Override
    public void renderItem(TileMechSignal tileSignal) {
        renderStoz(tileSignal);
        renderRamena(tileSignal);
    }
}
