package signalcraft.models.signSignals;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class ModelSignPrUpoz implements IModelCustom {
    private final IModelCustom modelPrUpoz = ModelRegistry.PR_UPOZ.getModel();
    private final ResourceLocation AZD_KONEC = TextureRegistry.AZD_KONEC.get();
    private final ResourceLocation AZD70_HLAVNI = TextureRegistry.AZD70_HLAVNI.get();

    public void renderBody(Boolean isOddilove) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.AZD70_HLAVNI);
        this.modelPrUpoz.renderPart("body_prupoz");

        if (isOddilove){
            Minecraft.getMinecraft().renderEngine.bindTexture(this.AZD_KONEC);
            this.modelPrUpoz.renderPart("oddil_prupoz");
        } else {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.AZD_KONEC);
            this.modelPrUpoz.renderPart("vjezd_prupoz");
        }
    }

    @Override
    public String getType() {
        return "";
    }

    @Override
    public void renderAll() {

    }

    @Override
    public void renderOnly(String... strings) {

    }

    @Override
    public void renderPart(String s) {

    }

    @Override
    public void renderAllExcept(String... strings) {

    }
}
