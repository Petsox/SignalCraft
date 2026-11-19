package signalcraft.models.levelCrossing.azd;

import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;
import signalcraft.models.levelCrossing.BoomBend;

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
    /**
     * the boom (raised) leaves the arm at BOOM_ROOT, leaning BOOM_TILT in x per unit of y; its tip at BOOM_TIP_Y per
     * length, in the order of LENGTHS
     */
    private static final float BOOM_ROOT_X = -0.163f, BOOM_ROOT_Y = 1.03f, BOOM_TILT = -0.095f;
    private static final float BOOM_DX = BOOM_TILT / (float) Math.sqrt(1 + BOOM_TILT * BOOM_TILT),
            BOOM_DY = 1f / (float) Math.sqrt(1 + BOOM_TILT * BOOM_TILT);
    private static final String[] LENGTHS = {"42", "50", "55", "60", "65", "75"};
    private static final float[] BOOM_TIP_Y = {3.389f, 3.841f, 4.143f, 4.444f, 4.745f, 5.348f};

    public void renderZavora(String length) {
        this.renderZavora(length, 0f);
    }

    /** flex: the boom's bend, degrees at its tip, + = further down (in the direction the arm turns when lowering) */
    public void renderZavora(String length, float flex) {
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
        this.modelCrossSSSR.renderPart("azd99_zavazi_" + length);
        this.modelCrossSSSR.renderPart("azd99_zavazi_drzaky_" + length);
        int index = Arrays.asList(LENGTHS).indexOf(length);
        final String boom = length;
        if (index < 0) {
            this.renderBoom(boom);
            return;
        }
        BoomBend.render(BOOM_ROOT_X, BOOM_ROOT_Y, BOOM_DX, BOOM_DY, (BOOM_TIP_Y[index] - BOOM_ROOT_Y) / BOOM_DY, flex,
                () -> this.renderBoom(boom));
    }

    private void renderBoom(String length) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.AZD99_MAIN);
        this.modelCrossSSSR.renderPart("azd99_odrazky_" + length);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.AZD99_BARRIER);
        this.modelCrossSSSR.renderPart("azd99_zavora_" + length);
    }

    // azd99_stozar_delsi (azd99.obj) is a pole-extension segment sitting directly on top of
    // azd99_motor_zavory; stacking N copies extends the pole by N times its own height.
    private static final float STOZAR_DELSI_HEIGHT = 0.278318f;

    public void renderStozarDelsi(int count) {
        if (count <= 0) return;
        Minecraft.getMinecraft().renderEngine.bindTexture(this.AZD99_MAIN);
        GL11.glPushMatrix();
        for (int i = 0; i < count; i++) {
            this.modelCrossSSSR.renderPart("azd99_stozar_delsi");
            GL11.glTranslatef(0f, STOZAR_DELSI_HEIGHT, 0f);
        }
        GL11.glPopMatrix();
    }
}
