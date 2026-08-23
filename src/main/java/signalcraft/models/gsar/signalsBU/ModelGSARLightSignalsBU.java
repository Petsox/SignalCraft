package signalcraft.models.gsar.signalsBU;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsBU.TileGSARLightSignalBU0x3;
import signalcraft.entities.gsar.signalsHP.*;
import signalcraft.models.gsar.ILightSignalModelGSAR;
import signalcraft.models.gsar.IStativModelGSAR;
import signalcraft.signalUtils.LampFade;
import signalcraft.signalUtils.SignalState;

public class ModelGSARLightSignalsBU implements ILightSignalModelGSAR, IStativModelGSAR {
    private static final float OVERBRIGHT = 2.0f;

    private final IModelCustom modelLightSignals = ModelRegistry.GSAR_LIGHT_SIGNALS.getModel();
    private final ResourceLocation Texture = TextureRegistry.GSAR_SIGNALS.get();
    private final ResourceLocation TextureLight = TextureRegistry.GSAR_KORONY.get();

    @Override
    public void renderStoz(TileGSARLightSignal tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        if (tileSignal instanceof TileGSARLightSignalBU0x3) {
            GL11.glTranslatef(0.0f, -3.5f, 0.0f);
        } else {
            GL11.glTranslatef(0.0f, -3.0f, 0.0f);
        }
        this.modelLightSignals.renderPart("cedule_BU");
        this.modelLightSignals.renderPart("cedule_HP_drzaky");
    }

    @Override
    public void renderNavestidlo(TileGSARLightSignal tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        if (tileSignal instanceof TileGSARLightSignalBU0x3) {
            GL11.glTranslatef(0.0f, -1.5f, 0.0f);
            this.modelLightSignals.renderPart("stozar_3");
            this.modelLightSignals.renderPart("stozar_4");

        } else {
            this.modelLightSignals.renderPart("stozar_1");
            this.modelLightSignals.renderPart("stozar_2");
            this.modelLightSignals.renderPart("stozar_3");
            this.modelLightSignals.renderPart("stozar_4");
            this.modelLightSignals.renderPart("stozar_uchyty_1");
            this.modelLightSignals.renderPart("stozar_uchyty_2");
        }
        this.modelLightSignals.renderPart("stozar_uchyty_3");
        this.modelLightSignals.renderPart("stozar_uchyty_4");
        this.modelLightSignals.renderPart("stit_BU");
    }


    @Override
    public void renderNavest(SignalState SigState, TileGSARLightSignal tileSignal) {
        LampFade fade = tileSignal.getLampFade();
        long dt = fade.beginFrame(Minecraft.getSystemTime());
        float brightness = fade.step("bila", tileSignal.getState().equals(SignalState.ACTIVATE), dt);

        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureLight);
        if (brightness > 0.0f) {
            float boosted = OVERBRIGHT * brightness;
            GL11.glColor4f(boosted, boosted, boosted, boosted);
            this.modelLightSignals.renderPart("bila_BU");
            GL11.glColor4f(OVERBRIGHT, OVERBRIGHT, OVERBRIGHT, OVERBRIGHT);
        }
        this.modelLightSignals.renderPart("zluta_BU");
    }

    @Override
    public void renderStativ(TileGSARStativ tileSignal) {}

    @Override
    public String getType() {
        return "";
    }

    @Override
    public void renderAll() {}

    @Override
    public void renderOnly(String... strings) {}

    @Override
    public void renderPart(String s) {}

    @Override
    public void renderAllExcept(String... strings) {}
}
