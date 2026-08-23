package signalcraft.entities.gsar.signalsLF;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.TileGeneric;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;
import signalcraft.signalUtils.Consts;

public class TileGSARSignalLF6 extends TileGeneric {

    public TileGSARSignalLF6() {
        this.setGuiId(Consts.GuiIDs.SIGN_LF6);
        this.setYAdjust(1.5f);
    }

    @Override
    public ResourceLocation getTexture() {
        return TextureRegistry.GSAR_LF1_LF6.get();
    }

    @Override
    public IModelCustom getModel() {
        return ModelRegistry.GSAR_LF6.getModel();
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
