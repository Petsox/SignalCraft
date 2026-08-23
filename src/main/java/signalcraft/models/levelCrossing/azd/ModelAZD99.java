package signalcraft.models.levelCrossing.azd;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class ModelAZD99
{
    private final IModelCustom modelCrossSSSR = ModelRegistry.AZD_99.getModel();;
    private final ResourceLocation AZD99_MAIN = TextureRegistry.AZD99_MAIN.get();
    private final ResourceLocation AZD99_PODKLAD = TextureRegistry.AZD99_PODKLAD.get();;
    private final ResourceLocation AZD99_SKRINKA = TextureRegistry.AZD99_SKRINKA.get();;
    private final ResourceLocation AZD99_ZAKLAD = TextureRegistry.AZD99_ZAKLAD.get();;
    private final ResourceLocation AZD99_BARRIER = TextureRegistry.AZD99_BARRIER.get();;

    public ModelAZD99() {
    }

    public void renderZaklad(Boolean isNew) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.AZD99_MAIN);
        this.modelCrossSSSR.renderPart("azd99_motor_zavory");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.AZD99_PODKLAD);
        this.modelCrossSSSR.renderPart("azd99_podklad");
        if (isNew) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.AZD99_SKRINKA);
            this.modelCrossSSSR.renderPart("azd99_podstavec_new");
            Minecraft.getMinecraft().renderEngine.bindTexture(this.AZD99_ZAKLAD);
            this.modelCrossSSSR.renderPart("azd99_zaklad_new");
        } else {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.AZD99_SKRINKA);
            this.modelCrossSSSR.renderPart("azd99_podstavec_old");
            Minecraft.getMinecraft().renderEngine.bindTexture(this.AZD99_ZAKLAD);
            this.modelCrossSSSR.renderPart("azd99_zaklad_old");
        }
    }
    public void renderZavora(String length){
        switch (length){
            case "4,2m": length = "42";  break;
            case "5,0m": length = "50";  break;
            case "5,5m": length = "55";  break;
            case "6,0m": length = "60";  break;
            case "6,5m": length = "65";  break;
            case "7,5m": length = "75";  break;
        }
        Minecraft.getMinecraft().renderEngine.bindTexture(this.AZD99_MAIN);
        this.modelCrossSSSR.renderPart("azd99_rameno");
        this.modelCrossSSSR.renderPart("azd99_spojnice");
        this.modelCrossSSSR.renderPart("azd99_odrazky_" + length);
        this.modelCrossSSSR.renderPart("azd99_zavazi_" + length);
        this.modelCrossSSSR.renderPart("azd99_zavazi_drzaky_" + length);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.AZD99_BARRIER);
        this.modelCrossSSSR.renderPart("azd99_zavora_" + length);
    }
}
