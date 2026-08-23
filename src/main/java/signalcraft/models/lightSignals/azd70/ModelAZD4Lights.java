package signalcraft.models.lightSignals.azd70;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.ModelRegistry;
import signalcraft.signalUtils.Consts;
import signalcraft.signalUtils.SignalState;

public class ModelAZD4Lights extends ModelAZD {
    private final IModelCustom modelLightSignals = ModelRegistry.AZD_4LIGHT.getModel();

    public void renderStoz(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        if (hasStripes) {
            this.modelLightSignals.renderPart("stozar_4svet_" + Pos + "_pruhy");
        } else if (!SpeedSignText.equals(Consts.SpeedSignText.NO_SIGN.SpeedSignTxt) && Pos.equals("S")) {
            this.modelLightSignals.renderPart("stozar_4svet_S_ind");
        } else {
            this.modelLightSignals.renderPart("stozar_4svet_" + Pos);
        }
    }

    public void renderSpeed(String SpeedSignText, String Pos) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Indikatory);
        if (SpeedSignText.equals("50")) {
            this.modelLightSignals.renderPart("ind50_4svet_S");
        } else if (SpeedSignText.equals("30")) {
            this.modelLightSignals.renderPart("ind30_4svet_S");
        }
    }

    public void renderStozNater(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        if (!SpeedSignText.equals("nic")) {
            this.modelLightSignals.renderPart("odjezd_4svet_ind");
        } else {
            this.modelLightSignals.renderPart("odjezd_4svet");
        }
    }

    public void renderSkupinove(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        this.modelLightSignals.renderPart("skupinove_4svet_" + Pos);
    }

    public void renderStit(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        if (hasStripes) {
            this.modelLightSignals.renderPart("stit_4svet_" + Pos + "_pruhy");
        } else {
            this.modelLightSignals.renderPart("stit_4svet_" + Pos);
        }
    }

    //Render Návěstí

    public void renderNavest(SignalState SigState, TileLightSignal tileSignal, String Pos, String PNLight) {
        String pruhy = "";

        if (tileSignal.getHasStripes().toBoolean()) {
            pruhy = "_pruhy";
        }
        final String pruhySuffix = pruhy;

        renderNavestFaded(SigState, tileSignal, this.modelLightSignals,
                state -> state + "_4svet_" + Pos + "_" + tileSignal.getType().toString() + pruhySuffix,
                state -> tileSignal.getHasStripes().toBoolean() || !state.contains("pruh"));
    }
}
