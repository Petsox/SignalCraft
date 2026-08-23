package signalcraft.models.lightSignals.azd70;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.ModelRegistry;
import signalcraft.signalUtils.SignalState;

public class ModelAZD3Lights extends ModelAZD {
    private final IModelCustom modelLightSignals = ModelRegistry.AZD_3LIGHT.getModel();

    public void renderStoz(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        this.modelLightSignals.renderPart("stozar_3svet_" + Pos);
    }

    public void renderStozNater(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        this.modelLightSignals.renderPart("odjezd_3svet_" + Pos);

    }

    public void renderSkupinove(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        this.modelLightSignals.renderPart("skupinove_3svet_" + Pos);
    }

    public void renderStit(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        this.modelLightSignals.renderPart("stit_3svet_" + Pos);
    }

    //Render Návěstí
    public void renderNavest(SignalState SigState, TileLightSignal tileSignal, String Pos, String PNLight) {
        renderNavestFaded(SigState, tileSignal, this.modelLightSignals, state -> state + "_3svet_" + Pos + "_" + tileSignal.getType().toString());
    }
}
