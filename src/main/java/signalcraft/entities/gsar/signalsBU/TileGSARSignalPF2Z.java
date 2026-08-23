package signalcraft.entities.gsar.signalsBU;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.TileGeneric;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class TileGSARSignalPF2Z extends TileGeneric {

    @Override
    public ResourceLocation getTexture() {
        return TextureRegistry.GSAR_BU4.get();
    }

    @Override
    public IModelCustom getModel() {
        return ModelRegistry.GSAR_PF2Z.getModel();
    }

    @Override
    public boolean needsRod() {
        return true;
    }
}
