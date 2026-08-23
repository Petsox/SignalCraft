package signalcraft.models.lightSignals.azd70;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.ModelRegistry;
import signalcraft.signalUtils.SignalState;

public class ModelAZD6Lights extends ModelAZD {
    private final IModelCustom modelLightSignals = ModelRegistry.AZD_6LIGHT.getModel();
    @Override
    public void renderStoz(Boolean hasPruhy, Boolean hasPruhy3, String pos, String speed, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        if (hasPruhy) {
            this.modelLightSignals.renderPart("stozar_6svet_" + pos + "_pruhy");
        } else if (!speed.equals("nic") && pos.equals("S")) {
            this.modelLightSignals.renderPart("stozar_6svet_ind_S");
        } else {
            this.modelLightSignals.renderPart("stozar_6svet_" + pos);
        }
    }
    @Override
    public void renderSpeed(String speed, String pos) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Indikatory);
        if (speed.equals("50")) {
            this.modelLightSignals.renderPart("ind50_6svet_S");
        } else if (speed.equals("30")) {
            this.modelLightSignals.renderPart("ind30_6svet_S");
        }
    }
    @Override
    public void renderStozNater(Boolean hasPruhy, Boolean hasPruhy3, String Pos, String speed) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        if (!speed.equals("nic") && !speed.equals("30S")) {
            this.modelLightSignals.renderPart("odjezd_6svet_ind_S");
        } else if (hasPruhy) {
            this.modelLightSignals.renderPart("odjezd_6svet_S_pruhy");
        } else {
            this.modelLightSignals.renderPart("odjezd_6svet_S");
        }
    }
    @Override
    public void renderSkupinove(Boolean hasPruhy, Boolean hasPruhy3, String pos, String PNLight){
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        if (hasPruhy) {
            this.modelLightSignals.renderPart("skupinove_6svet_" + pos + "_pruhy");
        } else {
            this.modelLightSignals.renderPart("skupinove_6svet_" + pos);
        }
    }
    @Override
    public void renderStit(Boolean hasPruhy, Boolean hasPruhy3, String pos, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        if (hasPruhy) {
            this.modelLightSignals.renderPart("stit_6svet_" + pos + "_pruhy");
        } else {
            this.modelLightSignals.renderPart("stit_6svet_" + pos);
        }
    }

    //Render Návěstí
    @Override
    public void renderNavest(SignalState SigState, TileLightSignal tileSignal, String pos, String PNLight) {
        String pruhy = "";

        if (tileSignal.getHasStripes().toBoolean()) {
            pruhy = "_pruhy";
        }
        final String pruhySuffix = pruhy;

        renderNavestFaded(SigState, tileSignal, this.modelLightSignals, state -> state + "_6svet_" + pos + pruhySuffix);
    }
}
