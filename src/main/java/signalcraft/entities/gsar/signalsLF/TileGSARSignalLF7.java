package signalcraft.entities.gsar.signalsLF;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.TileGeneric;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;
import signalcraft.signalUtils.Consts;

public class TileGSARSignalLF7 extends TileGeneric {

    public TileGSARSignalLF7() {
        this.setGuiId(Consts.GuiIDs.SIGN_LF7);
        this.setYAdjust(3.0f);
    }

    @Override
    public ResourceLocation getTexture() {
        return TextureRegistry.GSAR_LF7.get();
    }

    @Override
    public IModelCustom getModel() {
        return ModelRegistry.GSAR_LF.getModel();
    }

    @Override
    public boolean doesRenderGenericString() {
        return true;
    }

    @Override
    public boolean needsRod() {
        return true;
    }
}
