package signalcraft.entities.gsar.signalsNE;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.TileGeneric;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class TileGSARSignalNE3_2 extends TileGeneric {

    @Override
    public ResourceLocation getTexture() {
        return TextureRegistry.GSAR_NE3_2.get();
    }

    @Override
    public IModelCustom getModel() {
        return ModelRegistry.GSAR_NE3.getModel();
    }

    @Override
    public boolean needsRod() {
        return true;
    }
}
