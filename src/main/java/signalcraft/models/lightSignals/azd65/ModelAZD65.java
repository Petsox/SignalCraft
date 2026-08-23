package signalcraft.models.lightSignals.azd65;

import net.minecraft.util.ResourceLocation;
import signalcraft.entities.signals.lightSignals.TileLightSignal;
import signalcraft.models.TextureRegistry;
import signalcraft.models.lightSignals.ILightSignalModel;
import signalcraft.signalUtils.SignalState;

public class ModelAZD65 implements ILightSignalModel {

    public final ResourceLocation Hlavni = TextureRegistry.AZD65_HLAVNI.get();
    public final ResourceLocation Konec = TextureRegistry.AZD_KONEC.get();
    public final ResourceLocation Indikatory = TextureRegistry.AZD_INDIKATORY.get();
    public final ResourceLocation Cocky = TextureRegistry.COCKY.get();
    public final ResourceLocation CockyOff = TextureRegistry.COCKY_OFF.get();


    @Override
    public void renderStoz(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText, String PNLight) {

    }

    @Override
    public void renderStozNater(Boolean hasStripes, Boolean has3Stripes, String Pos, String SpeedSignText) {

    }

    @Override
    public void renderStozVjNater(Boolean hasStripes, Boolean has3Stripes, String SpeedSignText) {

    }

    @Override
    public void renderSkupinove(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {

    }

    @Override
    public void renderStit(Boolean hasStripes, Boolean has3Stripes, String Pos, String PNLight) {

    }

    @Override
    public void renderSpeed(String SpeedSignText, String Pos) {

    }

    @Override
    public void renderNavest(SignalState SigState, TileLightSignal tileSignal, String Pos, String PNLight) {

    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public void renderAll() {

    }

    @Override
    public void renderOnly(String... strings) {

    }

    @Override
    public void renderPart(String s) {

    }

    @Override
    public void renderAllExcept(String... strings) {

    }
}
