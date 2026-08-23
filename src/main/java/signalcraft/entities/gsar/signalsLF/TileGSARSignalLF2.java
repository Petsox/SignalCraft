package signalcraft.entities.gsar.signalsLF;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.TileGeneric;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class TileGSARSignalLF2 extends TileGeneric {

    @Override
    public ResourceLocation getTexture() {
        return TextureRegistry.GSAR_LF2.get();
    }

    @Override
    public IModelCustom getModel() {
        return ModelRegistry.GSAR_LF.getModel();
    }

    @Override
    public boolean needsRod() {
        return true;
    }
}
