package signalcraft.models.mechSignals;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.signals.mechSignals.TileMechSignal;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class ModelMech2Arms implements IMechSignalModel {
    private final IModelCustom model = ModelRegistry.MECH_2ARMS.getModel();
    private final ResourceLocation TEXTURE_MAIN = TextureRegistry.MECH_MAIN.get();
    private final ResourceLocation TEXTURE_RED = TextureRegistry.MECH_RED.get();
    private final ResourceLocation TEXTURE_GREEN = TextureRegistry.MECH_GREEN.get();
    private final ResourceLocation TEXTURE_LAMP = TextureRegistry.MECH_LAMP.get();
    private final ResourceLocation TEXTURE_CHAIN = TextureRegistry.MECH_CHAIN.get();

    private static final float RAMENO_1_PIVOT_X = 0.000687f, RAMENO_1_PIVOT_Y = 4.27096f, RAMENO_1_PIVOT_Z = -0.06424f;
    private static final float RAMENO_2_PIVOT_X = 0.040964f, RAMENO_2_PIVOT_Y = 3.63854f, RAMENO_2_PIVOT_Z = -0.065195f;
    private static final float DRZAK_CLONEK_2_PIVOT_X = 0.07135f, DRZAK_CLONEK_2_PIVOT_Y = 3.3845f, DRZAK_CLONEK_2_PIVOT_Z = -0.052095f;

    private static final float DRZAK_CLONEK_2_MAX_ANGLE = 67f;
    private static final float TAHLO_3_DIR_X = -0.7071f, TAHLO_3_DIR_Y = 0.7071f;
    private static final float TAHLO_3_MAX_TRAVEL = 0.17f;

    @Override
    public void renderStoz(TileMechSignal tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_MAIN);
        this.model.renderPart("stozar");
        this.model.renderPart("tahlo_1");
        this.model.renderPart("tahlo_2");

        if (tileSignal.getIsGrupped().toBoolean()) {
            this.model.renderPart("skupinove");
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_CHAIN);
        this.model.renderPart("retezy");
    }

    @Override
    public void renderRamena(TileMechSignal tileSignal) {
        float arm1Angle = tileSignal.getArm1Rotation();
        float arm2Angle = tileSignal.getArm2Rotation();
        float arm1Progress = arm1Angle / 45f;
        float arm2Progress = arm2Angle / 45f;

        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_MAIN);

        // svetlo/svetlo_2 simulate the two fixed lamp light sources - unlike the arms and their
        // lenses, the real lamp boxes don't rotate, only the colored glass in front of them does,
        // so both are rendered here in the unrotated frame rather than inside an arm's push/pop.
        MechLensRenderer.renderLamp(this.model, TEXTURE_LAMP, "svetlo");
        MechLensRenderer.renderLamp(this.model, TEXTURE_LAMP, "svetlo_2");
        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_MAIN);

        GL11.glPushMatrix();
        GL11.glTranslatef(RAMENO_1_PIVOT_X, RAMENO_1_PIVOT_Y, RAMENO_1_PIVOT_Z);
        GL11.glRotatef(arm1Angle, 0f, 0f, 1f);
        GL11.glTranslatef(-RAMENO_1_PIVOT_X, -RAMENO_1_PIVOT_Y, -RAMENO_1_PIVOT_Z);
        this.model.renderPart("rameno_1");
        // at rest cervena_1 covers svetlo, at 45° the arm swings zelena_1 in front of it instead
        MechLensRenderer.begin();
        MechLensRenderer.renderLens(this.model, TEXTURE_GREEN, "zelena_1", arm1Progress);
        MechLensRenderer.renderLens(this.model, TEXTURE_RED, "cervena_1", 1f - arm1Progress);
        MechLensRenderer.end();
        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_MAIN);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(
                TAHLO_3_DIR_X * TAHLO_3_MAX_TRAVEL * arm1Progress,
                TAHLO_3_DIR_Y * TAHLO_3_MAX_TRAVEL * arm1Progress,
                0f);
        this.model.renderPart("tahlo_3");
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(RAMENO_2_PIVOT_X, RAMENO_2_PIVOT_Y, RAMENO_2_PIVOT_Z);
        GL11.glRotatef(-arm2Angle, 0f, 0f, 1f);
        GL11.glTranslatef(-RAMENO_2_PIVOT_X, -RAMENO_2_PIVOT_Y, -RAMENO_2_PIVOT_Z);
        this.model.renderPart("rameno_2");
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(DRZAK_CLONEK_2_PIVOT_X, DRZAK_CLONEK_2_PIVOT_Y, DRZAK_CLONEK_2_PIVOT_Z);
        GL11.glRotatef(arm2Progress * DRZAK_CLONEK_2_MAX_ANGLE, 0f, 0f, 1f);
        GL11.glTranslatef(-DRZAK_CLONEK_2_PIVOT_X, -DRZAK_CLONEK_2_PIVOT_Y, -DRZAK_CLONEK_2_PIVOT_Z);
        this.model.renderPart("drzak_clonek_2");
        // zelena_2 sits below svetlo_2 at rest and only swings in front of it with the second arm
        MechLensRenderer.begin();
        MechLensRenderer.renderLens(this.model, TEXTURE_GREEN, "zelena_2", arm2Progress);
        MechLensRenderer.end();
        GL11.glPopMatrix();
    }

    @Override
    public void renderItem(TileMechSignal tileSignal) {
        renderStoz(tileSignal);
        renderRamena(tileSignal);
    }
}
