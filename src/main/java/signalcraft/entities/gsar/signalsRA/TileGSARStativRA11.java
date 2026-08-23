package signalcraft.entities.gsar.signalsRA;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.TileGeneric;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class TileGSARStativRA11 extends TileGeneric {

    @Override
    public ResourceLocation getTexture() {
        return TextureRegistry.GSAR_SIGNALS.get();
    }

    @Override
    public IModelCustom getModel() {
        return ModelRegistry.GSAR_RA_STATIV.getModel();
    }
}