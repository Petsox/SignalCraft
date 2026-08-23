package signalcraft.models.lightSignals.sssr;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.ModelRegistry;
import signalcraft.signalUtils.SignalState;

public class ModelSSSR2Lights extends ModelSSSR{
    private final IModelCustom modelLightSignals = ModelRegistry.SSSR_2LIGHT.getModel();

    @Override
    public void renderStoz(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_SKRINKA);
        this.modelLightSignals.renderPart("stoz_2_zaklad");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_MAIN);
        this.modelLightSignals.renderPart("stoz_2_stozar");
    }

    @Override
    public void renderStozNater(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_CISLA);
        this.modelLightSignals.renderPart("stoz_2odj_cisla");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_PRUHY);
        this.modelLightSignals.renderPart("stoz_2odj_nater");
    }

    @Override
    public void renderStozVjNater(Boolean hasStripes, Boolean has3Stripes, String SpeedSignText) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_CISLA);
        this.modelLightSignals.renderPart("stoz_2vj_cisla");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_PRUHY);
        this.modelLightSignals.renderPart("stoz_2vj_nater");
    }

    @Override
    public void renderStit(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_MAIN);
        if (Pos.equals("S")) this.modelLightSignals.renderPart("stit_2");
        this.modelLightSignals.renderPart("stit_2" + Pos.toLowerCase());
    }

    //Render Návěstí

    @Override
    public void renderNavest(SignalState SigState, TileLightSignal tileSignal, String Pos, String PNLight) {
        renderNavestFaded(SigState, tileSignal, this.modelLightSignals, state -> state + "_2svet_" + Pos + "_" + tileSignal.getType().Type);
    }
}
