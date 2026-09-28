package signalcraft.models.mechSignals;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.signals.mechSignals.TileMechSignal;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class ModelMech1Arm implements IMechSignalModel {
    private final IModelCustom model = ModelRegistry.MECH_1ARM.getModel();
    private final ResourceLocation TEXTURE_MAIN = TextureRegistry.MECH_MAIN.get();
    private final ResourceLocation TEXTURE_RED = TextureRegistry.MECH_RED.get();
    private final ResourceLocation TEXTURE_GREEN = TextureRegistry.MECH_GREEN.get();
    private final ResourceLocation TEXTURE_LAMP = TextureRegistry.MECH_LAMP.get();
    private final ResourceLocation TEXTURE_CHAIN = TextureRegistry.MECH_CHAIN.get();

    private static final float RAMENO_1_PIVOT_X = 0.000687f, RAMENO_1_PIVOT_Y = 4.27096f, RAMENO_1_PIVOT_Z = -0.06424f;

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
        float arm1Progress = arm1Angle / 45f;

        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_MAIN);

        // svetlo simulates the lamp's fixed light source - unlike the arm and its lenses, the
        // real lamp box doesn't rotate, only the colored glass in front of it does, so this is
        // rendered here in the unrotated frame rather than inside the arm's push/pop below.
        // Opaque, drawn before the semi-transparent lenses so it blends correctly underneath them.
        MechLensRenderer.renderLamp(this.model, TEXTURE_LAMP, "svetlo");
        Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_MAIN);

        GL11.glPushMatrix();
        GL11.glTranslatef(RAMENO_1_PIVOT_X, RAMENO_1_PIVOT_Y, RAMENO_1_PIVOT_Z);
        GL11.glRotatef(arm1Angle, 0f, 0f, 1f);
        GL11.glTranslatef(-RAMENO_1_PIVOT_X, -RAMENO_1_PIVOT_Y, -RAMENO_1_PIVOT_Z);
        this.model.renderPart("rameno_1");

        // zelena_1/cervena_1 are semi-transparent colored glass lenses over that light. At rest
        // cervena_1 covers svetlo, at 45° the arm swings zelena_1 in front of it instead - only
        // the lens in front is lit.
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
    }

    @Override
    public void renderItem(TileMechSignal tileSignal) {
        renderStoz(tileSignal);
        renderRamena(tileSignal);
    }
}
