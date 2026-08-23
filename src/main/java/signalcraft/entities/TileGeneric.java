package signalcraft.entities;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.signals.signSignals.TileSignSignal;

public class TileGeneric extends TileSignSignal implements IGeneric {

    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation("");
    }

    @Override
    public IModelCustom getModel() {
        return new IModelCustom() {

            @Override
            public String getType() {
                return "";
            }

            @Override
            public void renderAll() {

            }

            @Override
            public void renderOnly(String... groupNames) {

            }

            @Override
            public void renderPart(String s) {

            }

            @Override
            public void renderAllExcept(String... groupNames) {

            }
        };
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
