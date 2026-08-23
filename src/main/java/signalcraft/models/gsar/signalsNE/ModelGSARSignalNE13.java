package signalcraft.models.gsar.signalsNE;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsHP.TileGSARLightSignal;
import signalcraft.models.gsar.ILightSignalModelGSAR;
import signalcraft.signalUtils.LampFade;
import signalcraft.signalUtils.SignalState;

public class ModelGSARSignalNE13 implements ILightSignalModelGSAR {
    private static final float OVERBRIGHT = 2.0f;

    private final IModelCustom modelSignalNE13 = ModelRegistry.GSAR_NE13.getModel();
    private final ResourceLocation Texture = TextureRegistry.GSAR_SIGNALS.get();
    private final ResourceLocation TextureLight = TextureRegistry.GSAR_KORONY.get();

    @Override
    public void renderStoz(TileGSARLightSignal tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        GL11.glTranslatef(0.0f, -3f, 0.0f);
        this.modelSignalNE13.renderPart("Mast011_MA011");
        this.modelSignalNE13.renderPart("Mast02_MA02");
        this.modelSignalNE13.renderPart("MastSchild03_MS03");
        this.modelSignalNE13.renderPart("Mast03_MA03");
    }

    @Override
    public void renderNavestidlo(TileGSARLightSignal tileSignal) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Texture);
        this.modelSignalNE13.renderPart("SignalBox03_SB03");
        this.modelSignalNE13.renderPart("SignalSchirm02_SS02");
        this.modelSignalNE13.renderPart("SignalLight02_SG02");
    }

    @Override
    public void renderNavest(SignalState SigState, TileGSARLightSignal tileSignal) {
        LampFade fade = tileSignal.getLampFade();
        long dt = fade.beginFrame(Minecraft.getSystemTime());
        float brightness = fade.step("W02", tileSignal.getState().equals(SignalState.ACTIVATE), dt);
        if (brightness > 0.0f) {
            float boosted = OVERBRIGHT * brightness;
            Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureLight);
            GL11.glColor4f(boosted, boosted, boosted, boosted);
            this.modelSignalNE13.renderPart("WhiteLight02_W02");
            GL11.glColor4f(OVERBRIGHT, OVERBRIGHT, OVERBRIGHT, OVERBRIGHT);
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
