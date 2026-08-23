package signalcraft.entities.gsar.signalsRA;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.TileGeneric;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class TileGSARSignalRA11b extends TileGeneric {

    @Override
    public ResourceLocation getTexture() {
        return TextureRegistry.GSAR_RA11B.get();
    }

    @Override
    public IModelCustom getModel() {
        return ModelRegistry.GSAR_RA11A.getModel();
    }

    @Override
    public boolean needsRod() {
        return true;
    }
}
