package signalcraft.models.lightSignals.sssr;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.ModelRegistry;
import signalcraft.signalUtils.SignalState;

public class ModelSSSRPosun extends ModelSSSR{
    private final IModelCustom modelLightSignals = ModelRegistry.SSSR_SHUNT.getModel();

    @Override
    public void renderStoz(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_SKRINKA);
        this.modelLightSignals.renderPart("stoz_2se_zaklad");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_MAIN);
        this.modelLightSignals.renderPart("stoz_2se_stozar");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_PRUHY);
        this.modelLightSignals.renderPart("stoz_2se_nater");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_CISLA);
        this.modelLightSignals.renderPart("stoz_2se_cisla");
    }

    @Override
    public void renderStit(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_MAIN);
        if (Pos.equals("S")) this.modelLightSignals.renderPart("stit_2");
        this.modelLightSignals.renderPart("stit_2" + Pos.toLowerCase());
    }

    //Render Návěstí
    @Override
    public void renderNavest(SignalState SigState, TileLightSignal tileSignal, String Pos, String PNLight) {
        renderNavestFaded(SigState, tileSignal, this.modelLightSignals, state -> state + "_posun_" + Pos);
    }
}
