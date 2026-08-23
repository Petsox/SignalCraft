package signalcraft.models.gsar.signalsSH;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsHP.TileGSARLightSignal;
import signalcraft.entities.gsar.signalsSH.TileGSARLightSignalSHL;
import signalcraft.entities.gsar.signalsSH.TileGSARLightSignalSHLSingle;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;
import signalcraft.models.gsar.ILightSignalModelGSAR;
import signalcraft.signalUtils.LampFade;
import signalcraft.signalUtils.SignalState;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class ModelGSARSignalSHL implements ILightSignalModelGSAR {
    private static final float OVERBRIGHT = 2.0f;

    private final IModelCustom modelSignalSH = ModelRegistry.GSAR_SH.getModel();
    private final ResourceLocation Texture = TextureRegistry.GSAR_SIGNALS.get();
    private final ResourceLocation TextureLight = TextureRegistry.GSAR_KORONY.get();

    @Override
    public void renderStoz(TileGSARLightSignal tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);

        if (tileSignal instanceof TileGSARLightSignalSHLSingle) {
            GL11.glTranslatef(0.0f, -5.0f, 0.0f);
            this.modelSignalSH.renderPart("MastSchild01_MS01");
            this.modelSignalSH.renderPart("SignalBox01Stativ_SB01S");
        }
        if (tileSignal instanceof TileGSARLightSignalSHL) {
            GL11.glTranslatef(0.0f, -3.0f, 0.0f);
            this.modelSignalSH.renderPart("Mast011_MA011");
            this.modelSignalSH.renderPart("Mast02_MA02");
            this.modelSignalSH.renderPart("Mast03_MA03");
            this.modelSignalSH.renderPart("MastSchild02_MS02");
        }
        this.modelSignalSH.renderPart("SignalBox01_SB01");
        this.modelSignalSH.renderPart("SignalSchirm01_SS01");
        this.modelSignalSH.renderPart("SignalLight01_SG01");
    }

    @Override
    public void renderNavestidlo(TileGSARLightSignal tileSignal) {
    }

    @Override
    public void renderNavest(SignalState SigState, TileGSARLightSignal tileSignal) {
        LampFade fade = tileSignal.getLampFade();
        long dt = fade.beginFrame(Minecraft.getSystemTime());

        String[] signals = SigState.signals == null ? new String[0] : SigState.signals;
        Set<String> active = new LinkedHashSet<>(Arrays.asList(signals));
        Set<String> colors = new LinkedHashSet<>(active);
        colors.addAll(fade.fadingKeys());

        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureLight);
        for (String color : colors) {
            float brightness = fade.step(color, active.contains(color), dt);
            if (brightness <= 0.0f) {
                continue;
            }
            float boosted = OVERBRIGHT * brightness;
            GL11.glColor4f(boosted, boosted, boosted, boosted);
            this.modelSignalSH.renderPart(color);
        }
        GL11.glColor4f(OVERBRIGHT, OVERBRIGHT, OVERBRIGHT, OVERBRIGHT);
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
