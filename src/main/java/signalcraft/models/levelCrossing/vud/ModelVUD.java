package signalcraft.models.levelCrossing.vud;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.models.ModelRegistry;
import signalcraft.models.levelCrossing.ModelCross;

public class ModelVUD extends ModelCross {
    private final IModelCustom modelVUD = ModelRegistry.VUD.getModel();

    @Override
    public void renderZaklad(String Pos, Boolean hasPoz, Boolean isLightCoverShort) {

        Minecraft.getMinecraft().renderEngine.bindTexture(this.Podklad);
        this.modelVUD.renderPart("podklad");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Zaklad);
        this.modelVUD.renderPart("zaklad");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Skrinka);
        this.modelVUD.renderPart("podstavec");

        if (hasPoz) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.VUD);
            this.modelVUD.renderPart("vud_poz");
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Pozor_Vlak);
            this.modelVUD.renderPart("pozor_vlak");
        } else {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.VUD);
            this.modelVUD.renderPart("vud_bez_poz");
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Pozor_Vlak);
            this.modelVUD.renderPart("pozor_vlak_up");
        }
    }

    @Override
    public void renderStozar(String Distance, Boolean Stripes) {
        if (Stripes) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
            this.modelVUD.renderPart("stozar_dlouhy_pruhy");
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Pruhy);
            this.modelVUD.renderPart("stozar_pruhy");
        } else {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
            this.modelVUD.renderPart("stozar_dlouhy");
        }
    }

    @Override
    public void renderKriz(String Distance, Boolean isKrizJedno, Boolean isKrizNaStozaru, Boolean isSlovak, Boolean isReflective, Boolean isKrizVelky) {
        String IsKrizJedno = isKrizJedno ? "kriz_1k" : "kriz_xk";
        String IsKrizVelky = isKrizVelky ? "_velky" : "_maly";

        if (isSlovak) {
            if (isKrizJedno) {
                if (isReflective) {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSK);
                    this.modelVUD.renderPart("kriz_sk_refl");
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSK);
                    this.modelVUD.renderPart("kriz_sk");
                }
            } else {
                if (isReflective) {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSKVic);
                    this.modelVUD.renderPart("kriz_sk_refl");
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSKVic);
                    this.modelVUD.renderPart("kriz_sk");
                }
            }
        } else {
            if (isReflective) {
                if (isKrizVelky) {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZReflVelky);
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZRefl);
                }
                this.modelVUD.renderPart(IsKrizJedno + "_refl" + IsKrizVelky);
            } else {
                Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
                this.modelVUD.renderPart(IsKrizJedno + "_back" + IsKrizVelky);
                if (isKrizJedno) {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZ);
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZVic);
                }
                this.modelVUD.renderPart(IsKrizJedno + "_front" + IsKrizVelky);
            }
            GL11.glDisable(GL11.GL_CULL_FACE);

            Minecraft.getMinecraft().renderEngine.bindTexture(this.SkrinZadek);
            this.modelVUD.renderPart("kriz_ram");

            GL11.glEnable(GL11.GL_CULL_FACE);
        }
    }

    @Override
    public void renderSvetloL(String Distance, String Pos, Integer angleIndex, Boolean doLightsAlter) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.RED_LIGHT_VUD);
        this.modelVUD.renderPart("korona_l");
    }

    @Override
    public void renderSvetloR(String Distance, String Pos, Integer angleIndex, Boolean doLightsAlter) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.RED_LIGHT_VUD);
        this.modelVUD.renderPart("korona_p");
    }

    @Override
    public void renderSvetloPoz(String Distance, String Pos, Boolean isNewer) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Svetlo_W);
        this.modelVUD.renderPart("korona_poz");
    }
}
