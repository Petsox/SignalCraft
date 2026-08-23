package signalcraft.models.gsar.signalsWN;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsWN.TileSwitchElectricGSAR;
import signalcraft.entities.gsar.signalsWN.TileSwitchManualGSAR;
import signalcraft.entities.switches.TileSwitch;
import signalcraft.models.ModelRegistry;
import signalcraft.models.TextureRegistry;

public class ModelSwitchesGSAR {
    private final IModelCustom modelSignalsWN = ModelRegistry.GSAR_WN.getModel();
    private final ResourceLocation TextureWN = TextureRegistry.GSAR_WN.get();
    private final ResourceLocation TextureWN_Inverted = TextureRegistry.GSAR_WN_INVERTED.get();
    private final ResourceLocation TextureWN_Zluta = TextureRegistry.GSAR_WN_ZLUTA.get();
    private final ResourceLocation TextureSignals = TextureRegistry.GSAR_SIGNALS.get();
    private final ResourceLocation TextureSignalsLight = TextureRegistry.GSAR_KORONY.get();

    public void renderZaklad(TileSwitch tileSwitch) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureWN);
        this.modelSignalsWN.renderPart("zaklad");

        if (tileSwitch instanceof TileSwitchElectricGSAR) {
            this.modelSignalsWN.renderPart("elektro_prestavnik");
        }
    }

    public void renderPrestavnik(Boolean isInverted, TileSwitch tileSwitch) {
        if (tileSwitch instanceof TileSwitchManualGSAR && !isInverted) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureWN);
        } else if (isInverted) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureWN_Inverted);
        } else {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureWN_Zluta);
        }

        GL11.glTranslatef(0.000019f, 0.196863f, -0.188927f);
        GL11.glRotatef((float) (-tileSwitch.getRotation() * 4), 1.0f, 0.0f, 0.0f);
        GL11.glTranslatef(-0.000019f, -0.196863f, 0.188927f);

        this.modelSignalsWN.renderPart("zavazi_prestavniku");
        this.modelSignalsWN.renderPart("madlo_prestavniku");
        this.modelSignalsWN.renderPart("paka_prestavniku");

    }

    public void renderPojezdy(String switchPos, String switchSide, TileSwitch tileSwitch) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureWN);

        if (switchSide.equals("_P")) {
            GL11.glTranslatef(0.0f, 0.0f, -0.15f);
            GL11.glTranslatef(0.0f, 0.0f, tileSwitch.getRotation() / 256.0f);
        } else {
            GL11.glTranslatef(0.0f, 0.0f, -tileSwitch.getRotation() / 256.0f);
        }

        this.modelSignalsWN.renderPart("pojezdy_vyhybky" + switchPos);
    }

    public void renderHlavaS(TileSwitch tileSwitch) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureWN);

        this.modelSignalsWN.renderPart("uchyt_os");
        this.modelSignalsWN.renderPart("osa_lampy");
        this.modelSignalsWN.renderPart("osa_prestavniku");

        this.modelSignalsWN.renderPart("vyhybka_vrch");
        this.modelSignalsWN.renderPart("zaklad_vyhybky");

        this.modelSignalsWN.renderPart("stinidlo_svetla_1");
        this.modelSignalsWN.renderPart("stinidlo_svetla_2");
        this.modelSignalsWN.renderPart("stinidlo_svetla_3");

        if (tileSwitch.getIsSwitched()) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSignalsLight);
            this.modelSignalsWN.renderPart("bile_svetlo_1");
            this.modelSignalsWN.renderPart("bile_svetlo_2");
            Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSignals);
            this.modelSignalsWN.renderPart("svetlo_3");
        } else {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSignalsLight);
            this.modelSignalsWN.renderPart("bile_svetlo_1");
            this.modelSignalsWN.renderPart("bile_svetlo_3");
            Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureSignals);
            this.modelSignalsWN.renderPart("svetlo_2");
        }
    }

    public void renderHlavaR(String switchSide, TileSwitch tileSwitch) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.TextureWN);

        this.modelSignalsWN.renderPart("uchyt_os");
        this.modelSignalsWN.renderPart("osa_lampy");
        this.modelSignalsWN.renderPart("osa_prestavniku");

        GL11.glTranslatef(0.000225f, 0.0f, -0.18951f);
        GL11.glRotatef((float) (tileSwitch.getRotation() * 4 + 270), 0.0f, 1.0f, 0.0f);
        GL11.glTranslatef(-0.000225f, 0.0f, 0.18951f);

        this.modelSignalsWN.renderPart("sipky" + switchSide);
        this.modelSignalsWN.renderPart("zaklad_vyhybky");
        this.modelSignalsWN.renderPart("vyhybka_vrch");
    }
}
