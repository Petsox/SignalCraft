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

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class ModelGSARSemiSignals implements ISemiSignalModelGSAR
{
    private static final float OVERBRIGHT = 2.0f;

    private final IModelCustom modelSemiSignals = ModelRegistry.GSAR_SEMI_SIGNALS.getModel();
    private final ResourceLocation Texture = TextureRegistry.GSAR_SIGNALS.get();
    private final ResourceLocation TextureLight = TextureRegistry.GSAR_KORONY.get();
    private final ResourceLocation TextureSemiSignals = TextureRegistry.GSAR_SEMI_SIGNALS.get();

    @Override
    public void renderStoz(TileGSARSemiSignal tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignals);
        if (tileSignal instanceof TileGSARSemiSignal1Wingsx3) {
            GL11.glTranslatef(0.0f, -5f, 0.0f);
            this.modelSemiSignals.renderPart("lampa_1");
        } else if (tileSignal instanceof TileGSARSemiSignal2Wingsx3) {
            GL11.glTranslatef(0.0f, -5f, 0.0f);
            this.modelSemiSignals.renderPart("lampa_1");
            this.modelSemiSignals.renderPart("lampa_2");
        } else if (tileSignal instanceof TileGSARSemiSignal1Wingsx5) {
            GL11.glTranslatef(0.0f, -3.0f, 0.0f);
            this.modelSemiSignals.renderPart("lampa_1");
        } else if (tileSignal instanceof TileGSARSemiSignal2Wingsx5) {
            GL11.glTranslatef(0.0f, -3.0f, 0.0f);
            this.modelSemiSignals.renderPart("lampa_1");
            this.modelSemiSignals.renderPart("lampa_2");
        }
    }

    @Override
    public void renderNavestidlo(TileGSARSemiSignal tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        if (tileSignal instanceof TileGSARSemiSignal1Wingsx3 || tileSignal instanceof TileGSARSemiSignal2Wingsx3) {
            GL11.glTranslatef(0.0f, 2f, 0.0f);
            this.modelSemiSignals.renderPart("stozar_1");
            this.modelSemiSignals.renderPart("stozar_2");
            this.modelSemiSignals.renderPart("stozar_3");
            this.modelSemiSignals.renderPart("cedule_drzak_spodni");
            this.modelSemiSignals.renderPart("cedule_mala");

        } else if (tileSignal instanceof TileGSARSemiSignal1Wingsx5 || tileSignal instanceof TileGSARSemiSignal2Wingsx5) {
            this.modelSemiSignals.renderPart("stozar_1");
            this.modelSemiSignals.renderPart("stozar_2");
            this.modelSemiSignals.renderPart("stozar_3");
            this.modelSemiSignals.renderPart("stozar_4");
            this.modelSemiSignals.renderPart("stozar_5");
            this.modelSemiSignals.renderPart("cedule_drzak_spodni");
            this.modelSemiSignals.renderPart("cedule_drzak_horni");
            this.modelSemiSignals.renderPart("cedule_dlouha");
        }
    }

    @Override
    public void renderRamena(TileGSARSemiSignal tileSignal) {
        if (tileSignal instanceof TileGSARSemiSignal1Wingsx3) {
            GL11.glTranslatef(0.0f, 2.87f, 0.136f);
            GL11.glRotatef((float)tileSignal.getArm1Rotation(), 0.0f, 0.0f, 1.0f);
            Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignals);
            this.modelSemiSignals.renderPart("signalArm_1");
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
            this.modelSemiSignals.renderPart("svetlo_1");

        } else if (tileSignal instanceof TileGSARSemiSignal2Wingsx3) {
            GL11.glTranslatef(0.0f, 2.87f, 0.136f);
            GL11.glRotatef((float)tileSignal.getArm1Rotation(), 0.0f, 0.0f, 1.0f);
            Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignals);
            this.modelSemiSignals.renderPart("signalArm_1");
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
            this.modelSemiSignals.renderPart("svetlo_1");
            GL11.glPopMatrix();

            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, 1.26f, 0.136f);
            GL11.glRotatef(-(float)tileSignal.getArm2Rotation(), 0.0f, 0.0f, 1.0f);
            Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignals);
            this.modelSemiSignals.renderPart("signalArm_2");
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
            this.modelSemiSignals.renderPart("svetlo_2");

        } else if (tileSignal instanceof TileGSARSemiSignal1Wingsx5) {
            GL11.glTranslatef(0.0f, 4.87f, 0.136f);
            GL11.glRotatef((float)tileSignal.getArm1Rotation(), 0.0f, 0.0f, 1.0f);
            Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignals);
            this.modelSemiSignals.renderPart("signalArm_1");
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
            this.modelSemiSignals.renderPart("svetlo_1");

        } else if (tileSignal instanceof TileGSARSemiSignal2Wingsx5) {
            GL11.glTranslatef(0.0f, 4.87f, 0.136f);
            GL11.glRotatef((float)tileSignal.getArm1Rotation(), 0.0f, 0.0f, 1.0f);
            Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignals);
            this.modelSemiSignals.renderPart("signalArm_1");
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
            this.modelSemiSignals.renderPart("svetlo_1");
            GL11.glPopMatrix();

            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, 3.26f, 0.136f);
            GL11.glRotatef(-(float)tileSignal.getArm2Rotation(), 0.0f, 0.0f, 1.0f);
            Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignals);
            this.modelSemiSignals.renderPart("signalArm_2");
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
            this.modelSemiSignals.renderPart("svetlo_2");
        }
    }

    @Override
    public void renderKabel(TileGSARSemiSignal tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignals);
        if (tileSignal instanceof TileGSARSemiSignal1Wingsx3 || tileSignal instanceof TileGSARSemiSignal2Wingsx3) {
            GL11.glTranslatef(0.0f, -tileSignal.getArm1Rotation() / 256.0f, 0.0f);
            this.modelSemiSignals.renderPart("cable_2");

        } else if (tileSignal instanceof TileGSARSemiSignal1Wingsx5 || tileSignal instanceof TileGSARSemiSignal2Wingsx5) {
            GL11.glTranslatef(0.0f, -tileSignal.getArm1Rotation() / 256.0f, 0.0f);
            this.modelSemiSignals.renderPart("cable_1");
        }
    }

    @Override
    public void renderItem(TileGSARSemiSignal tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        this.modelSemiSignals.renderPart("stozar_1");
        this.modelSemiSignals.renderPart("stozar_2");
        GL11.glTranslatef(0.0f, 1.87f, 0.136f);
        this.modelSemiSignals.renderPart("svetlo_1");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignals);
        this.modelSemiSignals.renderPart("signalArm_1");
        if (tileSignal instanceof TileGSARSemiSignal2Wingsx3 || tileSignal instanceof TileGSARSemiSignal2Wingsx5) {
            GL11.glTranslatef(0.0f, -1.57f, 0f);
            this.modelSemiSignals.renderPart("signalArm_2");
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
            this.modelSemiSignals.renderPart("svetlo_2");
        }
    }

    @Override
    public void renderNavest(SignalState SigState, TileGSARSemiSignal tileSignal) {
        boolean armAtRest = tileSignal.getArm1Rotation() == 0 || tileSignal.getArm1Rotation() == 45;

        LampFade fade = tileSignal.getLampFade();
        long dt = fade.beginFrame(Minecraft.getSystemTime());

        String[] signals = SigState.signals == null ? new String[0] : SigState.signals;
        Set<String> active = new LinkedHashSet<>(Arrays.asList(signals));
        Set<String> colors = new LinkedHashSet<>(active);
        colors.addAll(fade.fadingKeys());

        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureLight);
        for (String color : colors) {
            float brightness = fade.step(color, armAtRest && active.contains(color), dt);
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
