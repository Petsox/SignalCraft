package signalcraft.entities.gsar.signalsSO;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.TileGeneric;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;
import signalcraft.signalUtils.Consts;

public class TileGSARHectometer extends TileGeneric {

    public TileGSARHectometer() {
        this.setGuiId(Consts.GuiIDs.HECTOMETER_SIGN);
    }

    @Override
    public ResourceLocation getTexture() {
        return TextureRegistry.GSAR_HECTOSIGN.get();
    }

    @Override
    public IModelCustom getModel() {
        return ModelRegistry.GSAR_HECTOSIGN.getModel();
    }

    @Override
    public boolean needsRod() {
        return true;
    }
}
