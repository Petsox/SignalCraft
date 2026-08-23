package signalcraft.models;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.SignalCraft;

public class ModelSpeedSign
{
    private final IModelCustom modelSpeedSign = ModelRegistry.SPEED_SIGN.getModel();
    private final ResourceLocation texture = TextureRegistry.SPEED_SIGN.get();

    public ModelSpeedSign() {
    }

    public IModelCustom getModelSpeedSign() {
        return modelSpeedSign;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public void renderRychlostnik() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.texture);
        this.modelSpeedSign.renderPart("rychlostnik");
    }
}
