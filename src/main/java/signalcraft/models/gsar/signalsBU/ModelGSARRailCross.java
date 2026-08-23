package signalcraft.models.gsar.signalsBU;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.entities.gsar.signalsBU.TileGSARCrossing;
import signalcraft.entities.gsar.signalsBU.TileGSARRailCrossLightS;
import signalcraft.entities.gsar.signalsBU.TileGSARRailCrossModern;

public class ModelGSARRailCross
{
    private final IModelCustom modelRailCross = ModelRegistry.GSAR_RAILCROSS_LIGHT.getModel();
    private final ResourceLocation Texture = TextureRegistry.GSAR_RAILCROSS_LIGHT.get();
    private final ResourceLocation TextureModern = TextureRegistry.GSAR_SEMI_STATIV.get();
    private final ResourceLocation TextureLight = TextureRegistry.GSAR_KORONY.get();

    public void renderBaseLight(TileGSARCrossing tile) {
        if (tile instanceof TileGSARRailCrossModern){
            Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureModern);
            this.modelRailCross.renderPart("BUBase01_BB01");
            return;
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        this.modelRailCross.renderPart("RailCrossLight_RCL");
        this.modelRailCross.renderPart("SignalSchirm_SS01");
        this.modelRailCross.renderPart("LightOff_LO01");

        if (tile instanceof TileGSARRailCrossLightS){
            this.modelRailCross.renderPart("Klingel01_KL01");
            this.modelRailCross.renderPart("Klingel02_KL02");
        }
    }
    
    public void renderStativLight() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        this.modelRailCross.renderPart("RailCrossStativ02_RCS02");
    }
    
    public void renderLightOn(TileGSARCrossing tile) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureLight);
        if (tile instanceof TileGSARRailCrossModern){
            this.modelRailCross.renderPart("BULightOn_BULO01");
            return;
        }
        this.modelRailCross.renderPart("LightOn_LO02");
    }

}
