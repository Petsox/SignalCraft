package signalcraft.models.lightSignals.azd70;

import net.minecraft.client.Minecraft;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.PartLibrary;
import signalcraft.models.PartLibraryRegistry;
import signalcraft.signalUtils.SignalState;

public class ModelAZD3Lights extends ModelAZD {
    private final PartLibrary modelLightSignals = PartLibraryRegistry.AZD70.get();

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
