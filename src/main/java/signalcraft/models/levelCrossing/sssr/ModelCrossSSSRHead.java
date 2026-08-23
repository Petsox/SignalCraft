package signalcraft.models.levelCrossing.sssr;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.models.ModelRegistry;
import signalcraft.models.levelCrossing.ModelCross;
import signalcraft.signalUtils.Consts;

public class ModelCrossSSSRHead extends ModelCross {
    private final IModelCustom modelCrossSSSRHead = ModelRegistry.SSSR_HEAD.getModel();;

    @Override
    public void renderZaklad(String Pos, Boolean hasPoz, Boolean isLightCoverShort) {
        Pos = resolvePos(Pos);

        if (isLightCoverShort){
            Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Ksilt);
            this.modelCrossSSSRHead.renderPart("zaklad_stinidlo_short_" + Pos);
        } else {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Ksilt);
            this.modelCrossSSSRHead.renderPart("zaklad_stinidlo_long_" + Pos);
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSRHead.renderPart("zaklad_zamek_" + Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Zadek);
        this.modelCrossSSSRHead.renderPart("zaklad_back_" + Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Zadek_B);
        this.modelCrossSSSRHead.renderPart("zaklad_back2_" + Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Zadek_A);
        this.modelCrossSSSRHead.renderPart("zaklad_base_back_" + Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Ksilt);
        this.modelCrossSSSRHead.renderPart("zaklad_base_" + Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Svetlo_Zhas);
        this.modelCrossSSSRHead.renderPart("zaklad_korona_" + Pos);
    }

    @Override
    public void renderVystraznik(String Distance, String Pos, Boolean hasPoz, Boolean isPozLightShort, Boolean isLightCoverShort, Consts.CeduleState isCedule) {
        renderPozor();
        Pos = resolvePos(Pos);
        if (hasPoz) {
            this.renderPoz(Pos);
            if (isPozLightShort) {
                this.renderPozShort(Pos);
            } else {
                this.renderPozLong(Pos);
            }
            this.renderHoukackaUp();
        } else {
            this.renderHoukackaDn();
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
                    this.modelCrossSSSRHead.renderPart("kriz_sk_refl");
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSK);
                    this.modelCrossSSSRHead.renderPart("kriz_sk");
                }
            } else {
                if (isReflective) {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSKVic);
                    this.modelCrossSSSRHead.renderPart("kriz_sk_refl");
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSKVic);
                    this.modelCrossSSSRHead.renderPart("kriz_sk");
                }
            }
        } else {
            if (isReflective) {
                if (isKrizVelky) {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZReflVelky);
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZRefl);
                }
                this.modelCrossSSSRHead.renderPart(IsKrizJedno + "_refl" + IsKrizVelky);
            } else {
                GL11.glDisable(GL11.GL_CULL_FACE);
                Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
                this.modelCrossSSSRHead.renderPart(IsKrizJedno + "_back" + IsKrizVelky);
                if (isKrizJedno) {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZ);
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZVic);
                }
                this.modelCrossSSSRHead.renderPart(IsKrizJedno + "_front" + IsKrizVelky);
            }

            Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
            this.modelCrossSSSRHead.renderPart("kriz_ram");

            GL11.glEnable(GL11.GL_CULL_FACE);
        }
    }

    private void renderHoukackaDn() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SkrinZadek);
        this.modelCrossSSSRHead.renderPart("houkacka_dn_body");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Ksilt);
        this.modelCrossSSSRHead.renderPart("houkacka_dn_inside");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSRHead.renderPart("houkacka_dn_ram");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Cerna);
        this.modelCrossSSSRHead.renderPart("houkacka_dn_cerna");
    }

    private void renderHoukackaUp() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SkrinZadek);
        this.modelCrossSSSRHead.renderPart("houkacka_up_body");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Ksilt);
        this.modelCrossSSSRHead.renderPart("houkacka_up_inside");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSRHead.renderPart("houkacka_up_ram");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Cerna);
        this.modelCrossSSSRHead.renderPart("houkacka_up_cerna");
    }

    private void renderPoz(String Pos) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSRHead.renderPart("pozitivka_ram");
        this.modelCrossSSSRHead.renderPart("pozitivka_zamek_" + Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Svetlo_Zhas);
        this.modelCrossSSSRHead.renderPart("pozitivka_korona_" + Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Zadek_A);
        this.modelCrossSSSRHead.renderPart("pozitivka_base_back_" + Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Ksilt);
        this.modelCrossSSSRHead.renderPart("pozitivka_base_" + Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Zadek_B);
        this.modelCrossSSSRHead.renderPart("pozitivka_back2_" + Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Zadek);
        this.modelCrossSSSRHead.renderPart("pozitivka_back_" + Pos);
    }

    private void renderPozLong(String Pos) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Ksilt);
        this.modelCrossSSSRHead.renderPart("pozitivka_stinidlo_long_" + Pos);
    }

    private void renderPozShort(String Pos) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Ksilt);
        this.modelCrossSSSRHead.renderPart("pozitivka_stinidlo_short_" + Pos);
    }

    private void renderPozor() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSRHead.renderPart("pozor_vlak_ram");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Pozor_Vlak);
        this.modelCrossSSSRHead.renderPart("pozor_vlak_front");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Pozor_Vlak);
        this.modelCrossSSSRHead.renderPart("pozor_vlak_back");
    }

    @Override
    public void renderSvetloL(String Distance, String Pos, Integer angleIndex, Boolean doLightsAlter) {
        Pos = resolvePos(Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Svetlo_C);
        this.modelCrossSSSRHead.renderPart("korona_l_" + Pos);
    }

    @Override
    public void renderSvetloR(String Distance, String Pos, Integer angleIndex, Boolean doLightsAlter) {
        Pos = resolvePos(Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Svetlo_C);
        this.modelCrossSSSRHead.renderPart("korona_p_" + Pos);
    }

    @Override
    public void renderSvetloPoz(String Distance, String Pos, Boolean isNewer) {
        Pos = resolvePos(Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Svetlo_B);
        this.modelCrossSSSRHead.renderPart("korona_ps_" + Pos);
    }

    private String resolvePos(String Pos) {
        switch (Pos){
            case "Stred":
                Pos = "S";
                break;
            case "Vlevo":
                Pos = "L";
                break;
            case "Vpravo":
                Pos = "R";
                break;
            default:
                Pos = "S";
        }
        return Pos;
    }
}
