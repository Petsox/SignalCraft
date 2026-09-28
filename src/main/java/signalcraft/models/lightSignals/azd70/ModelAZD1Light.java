package signalcraft.models.lightSignals.azd70;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.PartLibrary;
import signalcraft.models.PartLibraryRegistry;
import signalcraft.signalUtils.SignalState;

public class ModelAZD1Light extends ModelAZD {
    private final PartLibrary modelLightSignals = PartLibraryRegistry.AZD70.get();
    @Override
    public void renderStoz(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        this.modelLightSignals.renderPart("stozar_1svet_S");
    }

    @Override
    public void renderStit(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        this.modelLightSignals.renderPart("stit_1svet_S");
    }

    //Render Návěstí
    @Override
    public void renderNavest(SignalState SigState, TileLightSignal tileSignal, String Pos, String PNLight) {
        renderNavestFaded(SigState, tileSignal, this.modelLightSignals, state -> state + "_1svet_" + Pos);
    }
}
