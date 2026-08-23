package signalcraft.models.lightSignals.sssr;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.ModelRegistry;
import signalcraft.models.lightSignals.IDwarf;
import signalcraft.signalUtils.SignalState;

public class ModelSSSR5LightsT extends ModelSSSR implements IDwarf {
    private final IModelCustom modelLightSignals = ModelRegistry.SSSR_5LIGHT_T.getModel();

    @Override
    public void renderStoz(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_MAIN_T);
        this.modelLightSignals.renderPart("trpaslik_zaklad_45svet");
        this.modelLightSignals.renderPart("trpaslik_cisla_drzak");
        this.modelLightSignals.renderPart("trpaslik_5svet");
        this.modelLightSignals.renderPart("trpaslik_cisla_zaklad_2");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_CISLA);
        this.modelLightSignals.renderPart("trpaslik_cisla_odjezd");
    }

    //Render Návěstí

    @Override
    public void renderNavest(SignalState SigState, TileLightSignal tileSignal, String Pos, String PNLight) {
        renderNavestFaded(SigState, tileSignal, this.modelLightSignals,
                state -> state + "_trpaslik_hlavni5",
                state -> tileSignal.getHasStripes().toBoolean() || !state.contains("pruh"));
    }
}
