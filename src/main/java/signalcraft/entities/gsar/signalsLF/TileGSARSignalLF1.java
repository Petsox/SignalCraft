package signalcraft.entities.gsar.signalsLF;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.TileGeneric;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;
import signalcraft.signalUtils.Consts;

public class TileGSARSignalLF1 extends TileGeneric {

    public TileGSARSignalLF1() {
        this.setGuiId(Consts.GuiIDs.SIGN_LF1);
        this.setYAdjust(-4f);
    }

    @Override
    public ResourceLocation getTexture() {
        return TextureRegistry.GSAR_LF1_LF6.get();
    }

    @Override
    public IModelCustom getModel() {
        return ModelRegistry.GSAR_LF1.getModel();
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
