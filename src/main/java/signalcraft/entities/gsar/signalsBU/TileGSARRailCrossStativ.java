package signalcraft.entities.gsar.signalsBU;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.IGeneric;
import signalcraft.entities.gsar.signalsHP.TileGSARStativ;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class TileGSARRailCrossStativ extends TileGSARStativ implements IGeneric {

    @Override
    public ResourceLocation getTexture() {
        return TextureRegistry.GSAR_BU_STATIV.get();
    }

    @Override
    public IModelCustom getModel() {
        return ModelRegistry.GSAR_BU_STATIV.getModel();
    }

    @Override
    public boolean doesRenderGenericString() {
        return false;
    }

    @Override
    public boolean needsRod() {
        return false;
    }
}
