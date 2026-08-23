package signalcraft.models.gsar.signalsSO;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class ModelStationSignGSAR
{
    private final IModelCustom modelStationSigns = ModelRegistry.GSAR_STATION_SIGN.getModel();
    private final ResourceLocation Texture = TextureRegistry.GSAR_STATION_SIGN.get();
    private final ResourceLocation Metal = TextureRegistry.GSAR_ROD.get();

    public void renderBase() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        this.modelStationSigns.renderPart("Station01_ST01");
    }
    
    public void renderBaseWall() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        this.modelStationSigns.renderPart("Station01Wall_ST01W");
    }
    
    public void renderMetalRodLong() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Metal);
        this.modelStationSigns.renderPart("MetalRodLong_MR02");
    }
    
    public void renderMetalRodLongWall() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Metal);
        this.modelStationSigns.renderPart("MetalRodLongWall_MR02W");
    }
}
