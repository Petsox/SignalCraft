package signalcraft.models.gsar.signalsHP;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsHP.*;
import signalcraft.models.gsar.ILightSignalModelGSAR;
import signalcraft.models.gsar.IStativModelGSAR;
import signalcraft.signalUtils.LampFade;
import signalcraft.signalUtils.SignalState;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class ModelGSARLightSignals implements ILightSignalModelGSAR, IStativModelGSAR {
    private static final float OVERBRIGHT = 2.0f;

    private final IModelCustom modelLightSignals = ModelRegistry.GSAR_LIGHT_SIGNALS.getModel();
    private final ResourceLocation Texture = TextureRegistry.GSAR_SIGNALS.get();
    private final ResourceLocation TextureLight = TextureRegistry.GSAR_KORONY.get();

    @Override
    public void renderStoz(TileGSARLightSignal tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        if (tileSignal instanceof TileGSARLightSignalHPx3) {
            GL11.glTranslatef(0.0f, -3.5f, 0.0f);
            this.modelLightSignals.renderPart("cedule_HP");
            this.modelLightSignals.renderPart("cedule_HP_drzaky");
        } else if (tileSignal instanceof TileGSARLightSignalHPx5) {
            GL11.glTranslatef(0.0f, -3.0f, 0.0f);
            this.modelLightSignals.renderPart("cedule_HP");
            this.modelLightSignals.renderPart("cedule_HP_drzaky");
        } else if (tileSignal instanceof TileGSARLightSignalVRx5) {
            GL11.glTranslatef(0.0f, -3.5f, 0.0f);
            this.modelLightSignals.renderPart("cedule_VR");
            this.modelLightSignals.renderPart("cedule_VR_drzaky");
        } else if (tileSignal instanceof TileGSARLightSignalVRx3) {
            GL11.glTranslatef(0.0f, -3.0f, 0.0f);
            this.modelLightSignals.renderPart("cedule_VR");
            this.modelLightSignals.renderPart("cedule_VR_drzaky");
        }
    }

    @Override
    public void renderNavestidlo(TileGSARLightSignal tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        if (tileSignal instanceof TileGSARLightSignalHPx3) {
            GL11.glTranslatef(0.0f, -1.5f, 0.0f);
            this.modelLightSignals.renderPart("stozar_3");
            this.modelLightSignals.renderPart("stozar_4");
            this.modelLightSignals.renderPart("stozar_uchyty_3");
            this.modelLightSignals.renderPart("stozar_uchyty_4");
            this.modelLightSignals.renderPart("stit_HP");

        } else if (tileSignal instanceof TileGSARLightSignalHPx5) {
            this.modelLightSignals.renderPart("stozar_1");
            this.modelLightSignals.renderPart("stozar_2");
            this.modelLightSignals.renderPart("stozar_3");
            this.modelLightSignals.renderPart("stozar_4");
            this.modelLightSignals.renderPart("stozar_uchyty_1");
            this.modelLightSignals.renderPart("stozar_uchyty_2");
            this.modelLightSignals.renderPart("stozar_uchyty_3");
            this.modelLightSignals.renderPart("stozar_uchyty_4");
            this.modelLightSignals.renderPart("stit_HP");
        } else if (tileSignal instanceof TileGSARLightSignalVRx5) {
            GL11.glTranslatef(0.0f, -1.5f, 0.0f);
            this.modelLightSignals.renderPart("stozar_3");
            this.modelLightSignals.renderPart("stozar_4");
            this.modelLightSignals.renderPart("stozar_uchyty_3");
            this.modelLightSignals.renderPart("stozar_uchyty_4");
            this.modelLightSignals.renderPart("stit_VR");

        } else if (tileSignal instanceof TileGSARLightSignalVRx3) {
            this.modelLightSignals.renderPart("stozar_1");
            this.modelLightSignals.renderPart("stozar_2");
            this.modelLightSignals.renderPart("stozar_3");
            this.modelLightSignals.renderPart("stozar_4");
            this.modelLightSignals.renderPart("stozar_uchyty_1");
            this.modelLightSignals.renderPart("stozar_uchyty_2");
            this.modelLightSignals.renderPart("stozar_uchyty_3");
            this.modelLightSignals.renderPart("stozar_uchyty_4");
            this.modelLightSignals.renderPart("stit_VR");
        }
    }


    @Override
    public void renderNavest(SignalState SigState, TileGSARLightSignal tileSignal) {
        String type = "";
        if (tileSignal instanceof TileGSARLightSignalHPx3 || tileSignal instanceof TileGSARLightSignalHPx5) {
            type = "_HP";
        } else if (tileSignal instanceof TileGSARLightSignalVRx3 || tileSignal instanceof TileGSARLightSignalVRx5) {
            type = "_VR";
        }
        final String suffix = type;

        if (SigState.equals(SignalState.ALL)) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureLight);
            for (String state : SignalState.getPossibleColorsFromStates(tileSignal)) {
                this.modelLightSignals.renderPart(state + suffix);
            }
            return;
        }

        LampFade fade = tileSignal.getLampFade();
        long dt = fade.beginFrame(Minecraft.getSystemTime());

        String[] signals = SigState.signals == null ? new String[0] : SigState.signals;
        Set<String> active = new LinkedHashSet<>(Arrays.asList(signals));
        Set<String> colors = new LinkedHashSet<>(active);
        colors.addAll(fade.fadingKeys());

        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureLight);
        for (String color : colors) {
            boolean inAspect = active.contains(color);
            boolean blinkedOff = false;
            if (inAspect && SignalState.getIsStateBlink(color, SigState) && (SigState.blinkSlow != null || SigState.blinkFast != null)) {
                blinkedOff = (SigState.blinkSlow == null)
                        ? tileSignal.getBlinkCounterFast() >= 6
                        : tileSignal.getBlinkCounter() >= 11;
            }
            float brightness = fade.step(color, inAspect && !blinkedOff, dt);
            if (brightness <= 0.0f) {
                continue;
            }
            float boosted = OVERBRIGHT * brightness;
            GL11.glColor4f(boosted, boosted, boosted, boosted);
            this.modelLightSignals.renderPart(color + suffix);
        }
        GL11.glColor4f(OVERBRIGHT, OVERBRIGHT, OVERBRIGHT, OVERBRIGHT);
    }

    @Override
    public void renderStativ(TileGSARStativ tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        this.modelLightSignals.renderPart("stozar_1");
        this.modelLightSignals.renderPart("zavazi");
        if (tileSignal instanceof TileGSARStativLightSignals) {
            this.modelLightSignals.renderPart("stativ_cedule");
        }
    }

    @Override
    public String getType() {
        return "";
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
