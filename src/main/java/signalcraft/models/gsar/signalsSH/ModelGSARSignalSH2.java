package signalcraft.models.gsar.signalsSH;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsSH.TileGSARSignSignalSH2;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class ModelGSARSignalSH2 implements IModelCustom {
    private final IModelCustom modelSignalSH2 = ModelRegistry.GSAR_SH2.getModel();
    private final ResourceLocation Texture = TextureRegistry.GSAR_SH2.get();
    private final ResourceLocation TextureLight = TextureRegistry.GSAR_KORONY.get();
    private final ResourceLocation TextureLamp = TextureRegistry.GSAR_SIGNALS.get();
    private final ResourceLocation Metal = TextureRegistry.GSAR_ROD.get();


    public void renderStoz(TileGSARSignSignalSH2 tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        this.modelSignalSH2.renderPart("Sign01_SI01");

        Minecraft.getMinecraft().renderEngine.bindTexture(this.Metal);
        if (!tileSignal.getHasSH2Stativ()) {
            this.modelSignalSH2.renderPart("MetalRod_MR01");
        } else {
            GL11.glRotatef(90, 0f, 1f, 0f);
            this.modelSignalSH2.renderPart("MetalRodLong_MR02");
            GL11.glRotatef(-90, 0f, 1f, 0f);
        }

        if (tileSignal.getHasSH2Lamp()) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureLamp);
            this.modelSignalSH2.renderPart("RedLightOff_RLOff");
            renderNavest();
        }
    }

    private void renderNavest() {
        final int i1 = 15728880;
        final int j1 = i1 % 65536;
        final int k1 = i1 / 65536;
        GL11.glPushMatrix();
        GL11.glColor4f(2.0f, 2.0f, 2.0f, 2.0f);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j1, k1);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureLight);
        this.modelSignalSH2.renderPart("RedLightOn_RLOn");
        GL11.glPopMatrix();
    }

    public void renderItem(TileGSARSignSignalSH2 tileSignal) {
        renderStoz(tileSignal);
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