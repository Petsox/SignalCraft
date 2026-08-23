package signalcraft.models.lightSignals.sssr;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.ModelRegistry;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.SignalState;

public class ModelSSSR4Lights extends ModelSSSR {
    private final IModelCustom modelLightSignals = ModelRegistry.SSSR_4LIGHT.getModel();

    @Override
    public void renderStoz(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_SKRINKA);
        this.modelLightSignals.renderPart("stoz_4_zaklad");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_MAIN);
        this.modelLightSignals.renderPart("stoz_4_stozar");
    }

    @Override
    public void renderStozNater(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_CISLA);
        this.modelLightSignals.renderPart("stoz_4odj_cisla");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_PRUHY);
        this.modelLightSignals.renderPart("stoz_4odj_nater");
    }

    @Override
    public void renderStozVjNater(Boolean hasStripes, Boolean has3Stripes, String SpeedSignText) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_CISLA);
        this.modelLightSignals.renderPart("stoz_4vj_cisla");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_PRUHY);
        this.modelLightSignals.renderPart("stoz_4vj_nater");
    }

    @Override
    public void renderSpeed(String SpeedSignText, String Pos) {
        if (!SpeedSignText.equals(Consts.SpeedSignText.NO_SIGN.SpeedSignTxt)) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_MAIN);
            this.modelLightSignals.renderPart("stoz_4_rychl_zaklad");
            if (SpeedSignText.equals("50")) {
                Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_IND_50);
                this.modelLightSignals.renderPart("stoz_4_50");
            } else if (SpeedSignText.equals("30")) {
                Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_IND_30);
                this.modelLightSignals.renderPart("stoz_4_30");
            }
        }
    }

    @Override
    public void renderStit(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_MAIN);
        if (Pos.equals("S")) this.modelLightSignals.renderPart("stit_4");
        this.modelLightSignals.renderPart("stit_4" + Pos.toLowerCase());
        if (hasStripes) {
            if (Pos.equals("S")) this.modelLightSignals.renderPart("stit_4_pruhy");
            this.modelLightSignals.renderPart("stit_4" + Pos.toLowerCase() + "_pruhy");
        }
    }

    //Render Návěstí
    @Override
    public void renderNavest(SignalState SigState, TileLightSignal tileSignal, String Pos, String PNLight) {
        renderNavestFaded(SigState, tileSignal, this.modelLightSignals,
                state -> state + "_4svet_" + Pos + "_" + tileSignal.getType().Type,
                state -> tileSignal.getHasStripes().toBoolean() || !state.contains("pruh"));
    }
}
