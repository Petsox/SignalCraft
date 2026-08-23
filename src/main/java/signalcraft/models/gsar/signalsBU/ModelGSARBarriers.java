package signalcraft.models.gsar.signalsBU;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import signalcraft.entities.gsar.signalsBU.*;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.signalUtils.LampFade;

public class ModelGSARBarriers implements IModelBarriersGSAR {
    private final IModelCustom modelBarrier = ModelRegistry.GSAR_BARRIER.getModel();
    private final ResourceLocation TextureSemiSignals = TextureRegistry.GSAR_SEMI_SIGNALS.get();
    private final ResourceLocation TextureLight = TextureRegistry.GSAR_KORONY.get();

    @Override
    public void renderBase(String position) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignals);
        this.modelBarrier.renderPart("BarrierStativ_BS01");
    }

    @Override
    public void renderArmBase(TileGSARCrossing tileCrossing, String position, Boolean isArmLong, Boolean off) {
        GL11.glTranslatef(0.0f, 0.01f, 0.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignals);
        this.modelBarrier.renderPart("BarrierArm01_BA01" + position);

        if (isArmLong) {
            for (int i = 0; i < tileCrossing.getBarrierLength(); i++) {
                this.modelBarrier.renderPart("Arm" + i);
            }
        } else {
            this.modelBarrier.renderPart("BarrierArm01_BA01");
            this.modelBarrier.renderPart("BarrierArm02_BA02");
            this.modelBarrier.renderPart("BarrierArm03_BA03");
            this.modelBarrier.renderPart("BarrierArm04_BA04");
            this.modelBarrier.renderPart("LampRed01_LR01" + position);
            this.modelBarrier.renderPart("LampRed02_LR02" + position);
            this.modelBarrier.renderPart("LampRed03_LR03" + position);
            this.modelBarrier.renderPart("LampYellow01_LY01" + position);
            boolean eligible = (tileCrossing.isActive() && tileCrossing.getArmRotation() <= 90) || (!tileCrossing.isActive() && tileCrossing.getArmRotation() > 0);
            LampFade fade = tileCrossing.getLampFade();
            long dt = fade.beginFrame(Minecraft.getSystemTime());
            float brightness = fade.step("barrierLights", eligible && !off, dt);
            renderLights(position, brightness);
        }
    }

    private void renderLights(String position, float brightness) {
        if (brightness <= 0.0f) {
            return;
        }
        final int i1 = 15728880;
        final int j1 = i1 % 65536;
        final int k1 = i1 / 65536;
        GL11.glBlendFunc(1, 1);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDepthMask(true);
        GL11.glColor4f(brightness, brightness, brightness, brightness);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j1, k1);

        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureLight);
        this.modelBarrier.renderPart("LampRed01On_LRO01" + position);
        this.modelBarrier.renderPart("LampRed02On_LRO02" + position);
        this.modelBarrier.renderPart("LampRed03On_LRO03" + position);
        this.modelBarrier.renderPart("LampYellow01On_LYO01" + position);

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_CULL_FACE);
    }

    @Override
    public void renderBase2(String position) {
    }

    @Override
    public void renderReels(String position) {
    }

    @Override
    public void renderBell(String position) {
    }

    @Override
    public void renderItem(TileGSARCrossing tileCrossing) {
        String position;
        if (tileCrossing instanceof TileGSARModernBarrierL || tileCrossing instanceof TileGSARHalfBarrierL) {
            position = "_L";
        } else {
            position = "_R";
        }
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSemiSignals);
        this.modelBarrier.renderPart("BarrierArm01_BA01" + position);

        if (tileCrossing instanceof TileGSARHalfBarrierL || tileCrossing instanceof TileGSARHalfBarrierR) {
            this.modelBarrier.renderPart("BarrierArm01_BA01");
            this.modelBarrier.renderPart("BarrierArm02_BA02");
            this.modelBarrier.renderPart("BarrierArm03_BA03");
            this.modelBarrier.renderPart("BarrierArm04_BA04");
            this.modelBarrier.renderPart("LampRed01_LR01" + position);
            this.modelBarrier.renderPart("LampRed02_LR02" + position);
            this.modelBarrier.renderPart("LampRed03_LR03" + position);
            this.modelBarrier.renderPart("LampYellow01_LY01" + position);
        } else {
            for (int i = 0; i < 4; i++) {
                this.modelBarrier.renderPart("Arm" + i);
            }
        }
    }
}
