package signalcraft.models.lightSignals.azd70;

import net.minecraft.client.Minecraft;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.PartLibrary;
import signalcraft.models.PartLibraryRegistry;
import signalcraft.signalUtils.SignalState;

public class ModelAZD2Lights extends ModelAZD {
    private final PartLibrary modelLightSignals = PartLibraryRegistry.AZD70.get();

    @Override
    public void renderStoz(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        this.modelLightSignals.renderPart("stozar_2svet_" + Pos);
    }

    @Override
    public void renderStozNater(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        this.modelLightSignals.renderPart("odjezd_2svet");
    }

    @Override
    public void renderSkupinove(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        this.modelLightSignals.renderPart("skupinove_2svet_" + Pos);
    }

    @Override
    public void renderStit(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Hlavni);
        this.modelLightSignals.renderPart("stit_2svet_" + Pos);
    }

    //Render Návěstí
    public void renderNavest(SignalState SigState, TileLightSignal tileSignal, String pos, String PNLight) {
        renderNavestFaded(SigState, tileSignal, this.modelLightSignals, state -> state + "_2svet_" + pos + "_" + tileSignal.getType());
    }
}
