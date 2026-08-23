package signalcraft.models.gsar.signalsHP;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.gsar.signalsHP.*;
import signalcraft.models.gsar.IStativModelGSAR;

public class ModelGSARStativSemiSignals implements IStativModelGSAR {
    private final IModelCustom modelStativSemiSignals = ModelRegistry.GSAR_SEMI_STATIV.getModel();
    private final ResourceLocation Texture = TextureRegistry.GSAR_SEMI_STATIV.get();


    @Override
    public void renderStativ(TileGSARStativ tileSignal) {
        String signal = "_HP";

        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        if (tileSignal instanceof TileGSARStativSemiSignals) {
            modelStativSemiSignals.renderPart("cedule_HP");
            modelStativSemiSignals.renderPart("cedule_drzak_HP");
        } else if (tileSignal instanceof TileGSARStativSemiSignalsVR) {
            signal = "_VR";
        }

        modelStativSemiSignals.renderPart("stozar_1");
        modelStativSemiSignals.renderPart("zavazi" + signal);
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
