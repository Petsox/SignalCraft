package signalcraft.entities.gsar.signalsBU;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.TileGeneric;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class TileGSARSignalBU5 extends TileGeneric {

    @Override
    public ResourceLocation getTexture() {
        return TextureRegistry.GSAR_BU5.get();
    }

    @Override
    public IModelCustom getModel() {
        return ModelRegistry.GSAR_BU.getModel();
    }

    @Override
    public boolean needsRod() {
        return true;
    }
}
