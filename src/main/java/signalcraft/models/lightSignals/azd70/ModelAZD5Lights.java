package signalcraft.models.lightSignals.azd70;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.ModelRegistry;
import signalcraft.signalUtils.SignalState;

public class ModelAZD5Lights extends ModelAZD {
    private final IModelCustom modelLightSignals = ModelRegistry.AZD_5LIGHT.getModel();
    @Override
    public void renderStoz(Boolean hasPruhy, Boolean hasPruhy3, String pos, String speed, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        if (hasPruhy){
            this.modelLightSignals.renderPart("stozar_5svet_" + pos + "_2pruhy");
        } else if (hasPruhy3) {
            this.modelLightSignals.renderPart("stozar_5svet_" + pos + "_3pruhy");
        } else if (!speed.equals("nic") && pos.equals("S")) {
            this.modelLightSignals.renderPart("stozar_5svet_S_ind");
        } else {
            this.modelLightSignals.renderPart("stozar_5svet_" + pos);
        }
    }
    @Override
    public void renderSpeed(String speed, String pos) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Indikatory);
        if (speed.equals("50") && !pos.equals("S")){
            this.modelLightSignals.renderPart("ind50_5svet_RL");
        } else if (speed.equals("50")) {
            this.modelLightSignals.renderPart("ind50_5svet_S");
        } else if (speed.equals("30") && !pos.equals("S")) {
            this.modelLightSignals.renderPart("ind30_5svet_RL");
        } else if (speed.equals("30")) {
            this.modelLightSignals.renderPart("ind30_5svet_S");
        }
    }
    @Override
    public void renderStozNater(Boolean hasPruhy, Boolean hasPruhy3, String Pos, String speed) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
         if (hasPruhy3 || (hasPruhy && !speed.equals("nic"))) {
            this.modelLightSignals.renderPart("odjezd_5svet_3pruhy_2pruhyind");
        } else if (!speed.equals("nic") || hasPruhy) {
            this.modelLightSignals.renderPart("odjezd_5svet_2pruhy_ind");
        } else {
            this.modelLightSignals.renderPart("odjezd_5svet");
        }
    }
    @Override
    public void renderSkupinove(Boolean hasPruhy, Boolean hasPruhy3, String pos, String PNLight){
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        if (hasPruhy){
            this.modelLightSignals.renderPart("skupinove_5svet_" + pos + "_2pruhy");
        } else if (hasPruhy3) {
            this.modelLightSignals.renderPart("skupinove_5svet_" + pos + "_3pruhy");
        } else {
            this.modelLightSignals.renderPart("skupinove_5svet_" + pos);
        }
    }
    @Override
    public void renderStit(Boolean hasPruhy, Boolean hasPruhy3, String pos, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        if (hasPruhy){
            this.modelLightSignals.renderPart("stit_5svet_" + pos + "_2pruhy");
        } else if (hasPruhy3) {
            this.modelLightSignals.renderPart("stit_5svet_" + pos + "_3pruhy");
        } else {
            this.modelLightSignals.renderPart("stit_5svet_" + pos);
        }
    }

    //Render Návěstí
    @Override
    public void renderNavest(SignalState SigState, TileLightSignal tileSignal, String pos, String PNLight) {
        String pruhy = "";

        if (tileSignal.getHasStripes().toBoolean()) {
            pruhy = "_2pruhy";
        } else if (tileSignal.getHas3Stripes().toBoolean()) {
            pruhy = "_3pruhy";
        }
        final String pruhySuffix = pruhy;

        renderNavestFaded(SigState, tileSignal, this.modelLightSignals, state -> state + "_5svet_" + pos + pruhySuffix);
    }
}
