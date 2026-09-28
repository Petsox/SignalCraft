package signalcraft.models.lightSignals.sssr;

import net.minecraft.client.Minecraft;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.PartLibrary;
import signalcraft.models.PartLibraryRegistry;
import signalcraft.signalUtils.SignalState;

public class ModelSSSRAB4 extends ModelSSSR {
    private final PartLibrary modelLightSignals = PartLibraryRegistry.SSSR_NAV.get();

    @Override
    public void renderStoz(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_AB);
        this.modelLightSignals.renderPart("stoz_4ab");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_CISLA);
        this.modelLightSignals.renderPart("stoz_4ab_cisla");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_PRUHY);
        this.modelLightSignals.renderPart("stoz_4ab_nater");
    }

    //This is trully trully ugly way how to do it, there is a way to do it better, but i am too lazy to do it.
    @Override
    public void renderSkupinove(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_AB_TERC);
        // AB4's mount point for this marker sits ~18cm from AB3's -- a real
        // difference, not export noise, so tools/objdedupe split it into its
        // own name instead of merging it (see sssr_nav/parts/sssr_instances.csv).
        this.modelLightSignals.renderPart("znac_abpredvest__sssr_4ab_nove");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_MAIN);
        this.modelLightSignals.renderPart("znac_abpredvest_zaklad__sssr_4ab_nove");
    }

    @Override
    public void renderStit(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_MAIN);
        // AB4's shield sits ~9-12mm off from the plain 4-light variant's --
        // real, not export noise, so tools/objdedupe split it into its own
        // name instead of merging it (see sssr_nav/parts/sssr_instances.csv).
        if (Pos.equals("S")) this.modelLightSignals.renderPart("stit_4__sssr_4ab_nove");
        this.modelLightSignals.renderPart("stit_4" + Pos.toLowerCase() + "__sssr_4ab_nove");
    }


    //Render Návěstí

    @Override
    public void renderNavest(SignalState SigState, TileLightSignal tileSignal, String Pos, String PNLight) {
        renderNavestFaded(SigState, tileSignal, this.modelLightSignals, state -> state + "_ab4_" + Pos);
    }
}
