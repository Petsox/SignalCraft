package signalcraft.entities.gsar.signalsBU;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.IGeneric;
import signalcraft.entities.TileGeneric;
import signalcraft.entities.gsar.signalsHP.TileGSARStativ;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class TileGSARRailCrossFence extends TileGeneric {

    @Override
    public ResourceLocation getTexture() {
        return TextureRegistry.GSAR_RAILCROSS.get();
    }

    @Override
    public IModelCustom getModel() {
        return ModelRegistry.GSAR_RAILCROSS_FENCE.getModel();
    }

    @Override
    public boolean needsRod() {
        return true;
    }
}
