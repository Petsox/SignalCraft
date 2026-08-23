package signalcraft.models.lightSignals.azd70;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.ModelRegistry;
import signalcraft.models.lightSignals.IDwarf;
import signalcraft.signalUtils.SignalState;

public class ModelAZD2LightsT extends ModelAZD implements IDwarf {
    private final IModelCustom modelLightSignals = ModelRegistry.AZD_2LIGHT_T.getModel();

    @Override
    public void renderStoz(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.HlavniT);
        this.modelLightSignals.renderPart("zaklad_2svet");
    }

    @Override
    public void renderStit(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.HlavniT);
        this.modelLightSignals.renderPart("stit_2svet");
    }

    @Override
    public void renderStozVjNater(Boolean hasStripes, Boolean has3Stripes, String SpeedSignText) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.HlavniT);
        this.modelLightSignals.renderPart("vjezd_2svet");
    }

    //Render Návěstí
    @Override
    public void renderNavest(SignalState SigState, TileLightSignal tileSignal, String Pos, String PNLight) {
        renderNavestFaded(SigState, tileSignal, this.modelLightSignals, state -> state + "_2svet_" + tileSignal.getType());
    }
}
