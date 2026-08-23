package signalcraft.models.gsar.signalsHP;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsHP.*;
import signalcraft.models.gsar.ISemiSignalModelGSAR;
import signalcraft.signalUtils.LampFade;
import signalcraft.signalUtils.SignalState;

import java.util.LinkedHashSet;
import java.util.Set;

public class ModelGSARSemiSignalsVR implements ISemiSignalModelGSAR {
    private static final float OVERBRIGHT = 2.0f;

    private final IModelCustom modelSemiSignals = ModelRegistry.GSAR_SEMI_SIGNALS_VR.getModel();
    private final ResourceLocation Texture = TextureRegistry.GSAR_SIGNALS.get();
    private final ResourceLocation TextureLight = TextureRegistry.GSAR_KORONY.get();
    private final ResourceLocation TextureSemiSignals = TextureRegistry.GSAR_SEMI_SIGNALS.get();
    private final ResourceLocation TextureSemiSignalsVR = TextureRegistry.GSAR_SEMI_SIGNALS_VR.get();

    @Override
    public void renderStoz(TileGSARSemiSignal tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignals);
        GL11.glTranslatef(0.0f, -3f, 0.0f);
        this.modelSemiSignals.renderPart("lampa_1");
        this.modelSemiSignals.renderPart("kabel_1");
        this.modelSemiSignals.renderPart("lampa_2");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignalsVR);
        this.modelSemiSignals.renderPart("stit_drzak");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        this.modelSemiSignals.renderPart("stozar_1");
        this.modelSemiSignals.renderPart("stozar_2");
        this.modelSemiSignals.renderPart("stozar_3");
        this.modelSemiSignals.renderPart("cedule_drzaky");
        this.modelSemiSignals.renderPart("cedule_VR");
    }

    @Override
    public void renderNavestidlo(TileGSARSemiSignal tileSignal) {

    }

    @Override
    public void renderRamena(TileGSARSemiSignal tileSignal) {
        GL11.glTranslatef(0.0f, 3.1f, 0.0f);
        GL11.glRotatef((float) (-tileSignal.getArm1Rotation() * 2), 1.0f, 0.0f, 0.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignalsVR);
        this.modelSemiSignals.renderPart("stit");
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(0.0f, 1.55f, 0.0f);
        GL11.glRotatef((float) tileSignal.getArm2Rotation(), 0.0f, 0.0f, 1.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignalsVR);
        this.modelSemiSignals.renderPart("sipka");
    }

    @Override
    public void renderKabel(TileGSARSemiSignal tileSignal) {
        GL11.glTranslatef(0.0f, -tileSignal.getArm2Rotation() / 256.0f, 0.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignals);
        this.modelSemiSignals.renderPart("kabel_2");
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(0.0f, 1.9f, 0.0f);
        GL11.glRotatef((float) (tileSignal.getArm1Rotation() * 2 / 3 * 2), 0.0f, 0.0f, 1.0f);
        GL11.glRotatef((float) (tileSignal.getArm2Rotation() * 2 / 3 * 2), 0.0f, 0.0f, 1.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        this.modelSemiSignals.renderPart("svetlo_2");
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        GL11.glTranslatef(0.0f, 1.18f, 0.0f);
        GL11.glRotatef((float) (tileSignal.getArm1Rotation() * 2 / 3 * 2), 0.0f, 0.0f, 1.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        this.modelSemiSignals.renderPart("svetlo_1");

    }

    @Override
    public void renderItem(TileGSARSemiSignal tileSignal) {
        GL11.glTranslatef(-0.4f, -0.8f, -0.2f);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        this.modelSemiSignals.renderPart("stozar_1");
        this.modelSemiSignals.renderPart("stozar_2");
        this.modelSemiSignals.renderPart("stozar_3");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignals);
        this.modelSemiSignals.renderPart("lampa_1");
        this.modelSemiSignals.renderPart("lampa_2");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignalsVR);
        this.modelSemiSignals.renderPart("stit_drzak");
        GL11.glTranslatef(0.0f, 3.1f, 0.0f);
        this.modelSemiSignals.renderPart("stit");
        GL11.glTranslatef(0.0f, -1.55f, 0.0f);
        this.modelSemiSignals.renderPart("sipka");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        GL11.glTranslatef(0.0f, 0.37f, 0.0f);
        this.modelSemiSignals.renderPart("svetlo_2");
        GL11.glTranslatef(0.0f, -0.77f, 0.0f);
        this.modelSemiSignals.renderPart("svetlo_1");
    }


    @Override
    public void renderNavest(SignalState SigState, TileGSARSemiSignal tileSignal) {
        boolean armsAtRest = (tileSignal.getArm1Rotation() == 0 || tileSignal.getArm1Rotation() == 45)
                && (tileSignal.getArm2Rotation() == 0 || tileSignal.getArm2Rotation() == 45);

        Set<String> wanted = new LinkedHashSet<>();
        if (armsAtRest) {
            if (SigState.equals(SignalState.VOLNO)) {
                wanted.add("zelena");
                wanted.add("zelena_zelena");
            } else if (SigState.equals(SignalState.R40VOLNO)) {
                wanted.add("zelena");
                wanted.add("zluta");
            } else {
                wanted.add("zluta_zluta");
                wanted.add("zluta");
            }
        }

        LampFade fade = tileSignal.getLampFade();
        long dt = fade.beginFrame(Minecraft.getSystemTime());

        Set<String> colors = new LinkedHashSet<>(wanted);
        colors.addAll(fade.fadingKeys());

        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureLight);
        for (String color : colors) {
            float brightness = fade.step(color, wanted.contains(color), dt);
            if (brightness <= 0.0f) {
                continue;
            }
            float boosted = OVERBRIGHT * brightness;
            GL11.glColor4f(boosted, boosted, boosted, boosted);
            this.modelSemiSignals.renderPart(color);
        }
        GL11.glColor4f(OVERBRIGHT, OVERBRIGHT, OVERBRIGHT, OVERBRIGHT);
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public void renderAll() {

    }

    @Override
    public void renderOnly(String... strings) {

    }

    @Override
    public void renderPart(String s) {

    }

    @Override
    public void renderAllExcept(String... strings) {

    }
}
