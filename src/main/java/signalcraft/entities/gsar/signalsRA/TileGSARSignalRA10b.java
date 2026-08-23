package signalcraft.entities.gsar.signalsRA;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.TileGeneric;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class TileGSARSignalRA10b extends TileGeneric {

    @Override
    public ResourceLocation getTexture() {
        return TextureRegistry.GSAR_RA10B.get();
    }

    @Override
    public IModelCustom getModel() {
        return ModelRegistry.GSAR_RA10.getModel();
    }

    @Override
    public boolean needsRod() {
        return true;
    }
}
