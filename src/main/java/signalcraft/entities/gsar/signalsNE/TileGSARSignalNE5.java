package signalcraft.entities.gsar.signalsNE;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.TileGeneric;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class TileGSARSignalNE5 extends TileGeneric {

    @Override
    public ResourceLocation getTexture() {
        return TextureRegistry.GSAR_NE5.get();
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
