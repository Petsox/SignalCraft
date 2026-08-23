package signalcraft.models.lightSignals.sssr;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.ModelRegistry;
import signalcraft.models.lightSignals.IDwarf;
import signalcraft.signalUtils.SignalState;

public class ModelSSSR3LightsMechT extends ModelSSSR implements IDwarf {
    private final IModelCustom modelLightSignals = ModelRegistry.SSSR_3LIGHT_T_MECH.getModel();

    @Override
    public void renderStoz(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_MAIN_T);
        this.modelLightSignals.renderPart("trpaslik_zaklad_23svet");
        this.modelLightSignals.renderPart("trpaslik_cisla_drzak");
        this.modelLightSignals.renderPart("trpaslik_3svet");
        this.modelLightSignals.renderPart("trpaslik_jenvlak");
        this.modelLightSignals.renderPart("trpaslik_cisla_zaklad_2");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_CISLA);
        this.modelLightSignals.renderPart("trpaslik_cisla_odjezd");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_PRUHY);
        this.modelLightSignals.renderPart("trpaslik_jenvlak_zaklad");
    }

    //Render Návěstí

    @Override
    public void renderNavest(SignalState SigState, TileLightSignal tileSignal, String Pos, String PNLight) {
        renderNavestFaded(SigState, tileSignal, this.modelLightSignals, state -> state + "_trpaslik_hlavni_mech");
    }
}