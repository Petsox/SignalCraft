package signalcraft.models.gsar.signalsRA;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsHP.TileGSARLightSignal;
import signalcraft.entities.gsar.signalsRA.TileGSARSignalRA11W;
import signalcraft.entities.gsar.signalsRA.TileGSARSignalRA11WL;
import signalcraft.entities.gsar.signalsRA.TileGSARSignalRA11Y;
import signalcraft.entities.gsar.signalsRA.TileGSARSignalRA11YL;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;
import signalcraft.models.gsar.ILightSignalModelGSAR;
import signalcraft.signalUtils.LampFade;
import signalcraft.signalUtils.SignalState;

public class ModelGSARSignalRA11 implements ILightSignalModelGSAR {
    private static final float OVERBRIGHT = 2.0f;

    private final IModelCustom modelSignalRA11 = ModelRegistry.GSAR_RA11.getModel();
    private final ResourceLocation Texture = TextureRegistry.GSAR_SIGNALS.get();
    private final ResourceLocation TextureLight = TextureRegistry.GSAR_KORONY.get();

    @Override
    public void renderStoz(TileGSARLightSignal tileSignal) {
        GL11.glTranslatef(0.0f, -3.0f, 0.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        this.modelSignalRA11.renderPart("Mast011_MA011");
        this.modelSignalRA11.renderPart("Mast02_MA02");
        this.modelSignalRA11.renderPart("Mast03_MA03");
    }

    @Override
    public void renderNavestidlo(TileGSARLightSignal tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        this.modelSignalRA11.renderPart("RA11Blech02_RA11Bl02");
        this.modelSignalRA11.renderPart("RA11LampOff_RA11LOff");

        if (tileSignal instanceof TileGSARSignalRA11WL || tileSignal instanceof TileGSARSignalRA11YL) {
            this.modelSignalRA11.renderPart("RA11LightOff_RA10LOff");
        }

        final int i1 = 15728880;
        final int j1 = i1 % 65536;
        final int k1 = i1 / 65536;
        GL11.glPushMatrix();
        GL11.glColor4f(2.0f, 2.0f, 2.0f, 2.0f);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j1, k1);
        if (tileSignal instanceof TileGSARSignalRA11WL || tileSignal instanceof TileGSARSignalRA11W) {
            this.modelSignalRA11.renderPart("RA11aBlech01_RA11aBl01");
        } else if (tileSignal instanceof TileGSARSignalRA11YL || tileSignal instanceof TileGSARSignalRA11Y) {
            this.modelSignalRA11.renderPart("RA11Blech01_RA11Bl01");
        }
        GL11.glPopMatrix();
    }

    @Override
    public void renderNavest(SignalState SigState, TileGSARLightSignal tileSignal) {

        GL11.glBlendFunc(1, 1);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDepthMask(true);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        LampFade fade = tileSignal.getLampFade();
        long dt = fade.beginFrame(Minecraft.getSystemTime());
        float brightness = fade.step("RA11LightOn", tileSignal.getState().equals(SignalState.ACTIVATE), dt);
        if (brightness > 0.0f) {
            float boosted = OVERBRIGHT * brightness;
            GL11.glColor4f(boosted, boosted, boosted, boosted);
            this.modelSignalRA11.renderPart("RA11LightOn_RA10LOn");
            GL11.glColor4f(OVERBRIGHT, OVERBRIGHT, OVERBRIGHT, OVERBRIGHT);
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureLight);
        this.modelSignalRA11.renderPart("NightLight01_NL01");

        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        GL11.glColor4d(0.25, 0.25, 0.25, 1.0);
        this.modelSignalRA11.renderPart("NightLight02_NL02");


        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glDepthMask(true);
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
