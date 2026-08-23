package signalcraft.models.gsar.signalsBU;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsBU.TileGSARCrossing;

public class ModelGSARFullBarriers implements IModelBarriersGSAR {
    private final IModelCustom modelFullBarrier = ModelRegistry.GSAR_FULL_BARRIER.getModel();
    private final ResourceLocation TextureSemiSignals = TextureRegistry.GSAR_SEMI_SIGNALS.get();
    private final ResourceLocation textureStativ = TextureRegistry.GSAR_SEMI_STATIV.get();
    private final ResourceLocation textureLever = TextureRegistry.GSAR_LEVER.get();

    @Override
    public void renderBase(String position) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.textureStativ);
        this.modelFullBarrier.renderPart("Base01_B01");
        this.modelFullBarrier.renderPart("Kasten01_KA01" + position);
    }

    @Override
    public void renderBase2(String position) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.textureLever);
        this.modelFullBarrier.renderPart("Achse01_A01" + position);
        this.modelFullBarrier.renderPart("Achse02_A02" + position);
        this.modelFullBarrier.renderPart("KlingelSet01_KL01" + position);
        this.modelFullBarrier.renderPart("KlingelSet02_KL02" + position);
        this.modelFullBarrier.renderPart("Seil01_SE01" + position);
    }

    @Override
    public void renderArmBase(TileGSARCrossing tileCrossing, String position, Boolean isArmLong, Boolean off) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignals);
        if (isArmLong) {
            this.modelFullBarrier.renderPart("Arm01_AR01");
            this.modelFullBarrier.renderPart("Arm02_AR02");
            this.modelFullBarrier.renderPart("Arm03_AR03");
            this.modelFullBarrier.renderPart("Arm04_AR04");
            this.modelFullBarrier.renderPart("Arm05_AR05");
            this.modelFullBarrier.renderPart("Arm06_AR06");
            this.modelFullBarrier.renderPart("Seil02_SE02");
            this.modelFullBarrier.renderPart("Achse03_A03");
        } else {
            this.modelFullBarrier.renderPart("Arm01_AR01");
            this.modelFullBarrier.renderPart("Arm02_AR02");
            this.modelFullBarrier.renderPart("Arm03_AR03");
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(this.textureStativ);
        this.modelFullBarrier.renderPart("Beton01_BE01");
    }

    @Override
    public void renderReels(String position) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.textureLever);
        this.modelFullBarrier.renderPart("Hebelwerk01_H01" + position);
    }

    @Override
    public void renderBell(String position) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.textureLever);
        this.modelFullBarrier.renderPart("Klingel01_K01" + position);
    }

    @Override
    public void renderItem(TileGSARCrossing tileCrossing) {
        GL11.glRotatef(20.0f, 1.0f, 0.0f, 0.0f);
        GL11.glTranslatef(0.0f, -0.3f, -0.2f);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignals);
        this.modelFullBarrier.renderPart("Arm02_AR02");
    }
}
