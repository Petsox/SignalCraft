package signalcraft.entities.gsar.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.IGeneric;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class TileLadder extends TileEntity implements IGeneric {
    @Override
    public ResourceLocation getTexture() {
       return TextureRegistry.GSAR_SIGNALS.get();

    }

    @Override
    public IModelCustom getModel() {
        return ModelRegistry.GSAR_LADDER.getModel();
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
