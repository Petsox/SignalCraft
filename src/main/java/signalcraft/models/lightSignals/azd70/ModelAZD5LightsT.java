package signalcraft.models.lightSignals.azd70;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.ModelRegistry;
import signalcraft.models.lightSignals.IDwarf;
import signalcraft.signalUtils.SignalState;

public class ModelAZD5LightsT extends ModelAZD implements IDwarf {
    private final IModelCustom modelLightSignals = ModelRegistry.AZD_5LIGHT_T.getModel();

    @Override
    public void renderStoz(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.HlavniT);
        this.modelLightSignals.renderPart("zaklad_5svet");
    }

    @Override
    public void renderStit(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.HlavniT);
        this.modelLightSignals.renderPart("stit_5svet");
    }

    //Render Návěstí
    @Override
    public void renderNavest(SignalState SigState, TileLightSignal tileSignal, String Pos, String PNLight) {
        renderNavestFaded(SigState, tileSignal, this.modelLightSignals, state -> state + "_5svet");
    }
}
