package signalcraft.models.lightSignals.sssr;

import net.minecraft.client.Minecraft;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.PartLibrary;
import signalcraft.models.PartLibraryRegistry;
import signalcraft.signalUtils.SignalState;

public class ModelSSSROPr extends ModelSSSR {
    private final PartLibrary modelLightSignals = PartLibraryRegistry.SSSR_NAV.get();

    @Override
    public void renderStoz(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_SKRINKA);
        this.modelLightSignals.renderPart("stoz_zaklad");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_MAIN);
        // sssr_pr_nove.obj's own "stoz_3_stozar" is unrelated to the plain
        // 3-light variant's part of the same name, so tools/objdedupe split
        // it into its own name (see sssr_nav/parts/sssr_instances.csv).
        this.modelLightSignals.renderPart("stoz_3_stozar__sssr_pr_nove");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_CISLA);
        this.modelLightSignals.renderPart("stoz_opr3_cisla");
    }

    @Override
    public void renderStit(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_MAIN);
        if (Pos.equals("S")) this.modelLightSignals.renderPart("stit_3");
        this.modelLightSignals.renderPart("stit_3" + Pos.toLowerCase());
    }

    //Render Návěstí

    @Override
    public void renderNavest(SignalState SigState, TileLightSignal tileSignal, String Pos, String PNLight) {
        renderNavestFaded(SigState, tileSignal, this.modelLightSignals, state -> state + "_opr3_" + Pos);
    }
}
