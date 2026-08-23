package signalcraft.entities.gsar.signalsBU;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.IGeneric;
import signalcraft.entities.TileGeneric;
import signalcraft.entities.gsar.signalsHP.TileGSARStativ;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class TileGSARBarrierStop extends TileGeneric {

    @Override
    public ResourceLocation getTexture() {
        return TextureRegistry.GSAR_SEMI_SIGNALS_VR.get();
    }

    @Override
    public IModelCustom getModel() {
        return ModelRegistry.GSAR_BARRIER_STOP.getModel();
    }
}
