package signalcraft.models.lightSignals.sssr;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.ModelRegistry;
import signalcraft.models.lightSignals.IDwarf;
import signalcraft.signalUtils.SignalState;

public class ModelSSSRPosunT extends ModelSSSR implements IDwarf {
    private final IModelCustom modelLightSignals = ModelRegistry.SSSR_SHUNT_T.getModel();

    @Override
    public void renderStoz(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText, String PNLight) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_MAIN_T);
        this.modelLightSignals.renderPart("trpaslik_zaklad_23svet");
        this.modelLightSignals.renderPart("trpaslik_cisla_drzak");
        this.modelLightSignals.renderPart("trpaslik_2svet");
        this.modelLightSignals.renderPart("trpaslik_cisla_zaklad_2");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_CISLA);
        this.modelLightSignals.renderPart("trpaslik_cisla_posun");
    }

    @Override
    public void renderNavest(SignalState SigState, TileLightSignal tileSignal, String Pos, String PNLight) {
        renderNavestFaded(SigState, tileSignal, this.modelLightSignals, state -> state + "_trpaslik_posun");
    }
}
