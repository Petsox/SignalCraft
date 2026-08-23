package signalcraft.models.lightSignals.azd70;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.TextureRegistry;
import signalcraft.models.lightSignals.ILightSignalModel;
import signalcraft.signalUtils.LampFade;
import signalcraft.signalUtils.SignalState;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class ModelAZD implements ILightSignalModel {

    public final ResourceLocation Hlavni = TextureRegistry.AZD70_HLAVNI.get();
    public final ResourceLocation HlavniT = TextureRegistry.AZD70_HLAVNI_T.get();
    public final ResourceLocation Konec = TextureRegistry.AZD_KONEC.get();
    public final ResourceLocation Indikatory = TextureRegistry.AZD_INDIKATORY.get();
    public final ResourceLocation Cocky = TextureRegistry.COCKY.get();
    public final ResourceLocation CockyOff = TextureRegistry.COCKY_OFF.get();

    /** Matches the overbright color multiplier {@link signalcraft.renderers.entities.lightSignals.TileLightSignalsRenderer} sets before calling renderNavest. */
    private static final float OVERBRIGHT = 2.0f;

    /**
     * Shared lamp-rendering loop for the AZD70 family: eases each lamp's brightness
     * toward on/off (via the tile's {@link LampFade}) instead of snapping the colored
     * overlay mesh in and out instantly, so aspect changes and blink transitions dim
     * like an incandescent bulb rather than an LED. {@code keyMapper} lets a family
     * substitute the raw signal string with the lamp it's actually rendered as (e.g.
     * a signal head with no red lamp lighting yellow instead); {@code partNameFor}
     * builds the OBJ part name for a (mapped) lamp key; {@code eligible} lets a family
     * exclude keys it can't physically light (e.g. stripe indicators when absent).
     */
    protected void renderNavestFaded(SignalState sigState, TileLightSignal tileSignal, IModelCustom modelLightSignals,
                                      Function<String, String> keyMapper, Function<String, String> partNameFor, Predicate<String> eligible) {
        if (sigState.equals(SignalState.ALL)) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Cocky);
            for (String state : SignalState.getPossibleColorsFromStates(tileSignal)) {
                String mapped = keyMapper.apply(state);
                if (!eligible.test(mapped)) {
                    continue;
                }
                modelLightSignals.renderPart(partNameFor.apply(mapped));
            }
            return;
        }

        LampFade fade = tileSignal.getLampFade();
        long dt = fade.beginFrame(Minecraft.getSystemTime());

        String[] signals = sigState.signals == null ? new String[0] : sigState.signals;
        Set<String> active = new LinkedHashSet<>();
        for (String raw : signals) {
            active.add(keyMapper.apply(raw));
        }
        Set<String> colors = new LinkedHashSet<>(active);
        colors.addAll(fade.fadingKeys());

        Minecraft.getMinecraft().renderEngine.bindTexture(this.Cocky);
        for (String color : colors) {
            boolean inAspect = active.contains(color) && eligible.test(color);
            boolean blinkedOff = false;
            if (inAspect && SignalState.getIsStateBlink(color, sigState) && (sigState.blinkSlow != null || sigState.blinkFast != null)) {
                blinkedOff = (sigState.blinkSlow == null)
                        ? tileSignal.getBlinkCounterFast() >= 6
                        : tileSignal.getBlinkCounter() >= 11;
            }
            float brightness = fade.step(color, inAspect && !blinkedOff, dt);
            if (brightness <= 0.0f) {
                continue;
            }
            float boosted = OVERBRIGHT * brightness;
            GL11.glColor4f(boosted, boosted, boosted, boosted);
            modelLightSignals.renderPart(partNameFor.apply(color));
        }
        GL11.glColor4f(OVERBRIGHT, OVERBRIGHT, OVERBRIGHT, OVERBRIGHT);
    }

    protected void renderNavestFaded(SignalState sigState, TileLightSignal tileSignal, IModelCustom modelLightSignals,
                                      Function<String, String> partNameFor, Predicate<String> eligible) {
        renderNavestFaded(sigState, tileSignal, modelLightSignals, key -> key, partNameFor, eligible);
    }

    protected void renderNavestFaded(SignalState sigState, TileLightSignal tileSignal, IModelCustom modelLightSignals,
                                      Function<String, String> partNameFor) {
        renderNavestFaded(sigState, tileSignal, modelLightSignals, key -> key, partNameFor, key -> true);
    }

    @Override
    public void renderStoz(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText, String PNLight) {

    }

    @Override
    public void renderStozNater(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText) {

    }

    @Override
    public void renderStozVjNater(Boolean hasStripes, Boolean has3Stripes, String SpeedSignText) {

    }

    @Override
    public void renderSkupinove(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {

    }

    @Override
    public void renderStit(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {

    }

    @Override
    public void renderSpeed(String SpeedSignText, String Pos) {

    }

    @Override
    public void renderNavest(SignalState SigState, TileLightSignal tileSignal, String Pos, String PNLight) {

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
