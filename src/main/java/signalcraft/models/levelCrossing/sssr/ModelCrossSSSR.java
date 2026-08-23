package signalcraft.models.levelCrossing.sssr;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.models.ModelRegistry;
import signalcraft.models.levelCrossing.ModelCross;
import signalcraft.signalUtils.Consts;

public class ModelCrossSSSR extends ModelCross {
    private final IModelCustom modelCrossSSSR = ModelRegistry.SSSR.getModel();;

    @Override
    public void renderZaklad(String Pos, Boolean hasPoz, Boolean isLightCoverShort) {
        Pos = resolvePos(Pos);

        if (isLightCoverShort){
            Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Ksilt);
            this.modelCrossSSSR.renderPart("zaklad_stinidlo_short_" + Pos);
        } else {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Ksilt);
            this.modelCrossSSSR.renderPart("zaklad_stinidlo_long_" + Pos);
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSR.renderPart("zaklad_zamek_" + Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Zadek);
        this.modelCrossSSSR.renderPart("zaklad_back_" + Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Zadek_B);
        this.modelCrossSSSR.renderPart("zaklad_back2_" + Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Zadek_A);
        this.modelCrossSSSR.renderPart("zaklad_base_back_" + Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Ksilt);
        this.modelCrossSSSR.renderPart("zaklad_base_" + Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Svetlo_Zhas);
        this.modelCrossSSSR.renderPart("zaklad_korona_" + Pos);
    }

    @Override
    public void renderStozar(String Distance, Boolean Stripes) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Podklad);
        this.modelCrossSSSR.renderPart("podklad");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Skrinka);
        this.modelCrossSSSR.renderPart("podstavec");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Zaklad);
        this.modelCrossSSSR.renderPart("zaklad");

        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        if (!Stripes){
            this.modelCrossSSSR.renderPart("stozar");
        } else {
            this.modelCrossSSSR.renderPart("stozar_pruhy");
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Pruhy);
            this.modelCrossSSSR.renderPart("pruhy");
        }
    }

    @Override
    public void renderSloup(String Distance, Boolean hasZebrik, Consts.CeduleState isCedule, Boolean isKrizNaStozaru) {
        if (hasZebrik) {
            this.renderZebrik();
        } else {
            this.renderStupacka();
        }
    }

    @Override
    public void renderVystraznik(String Distance, String Pos, Boolean hasPoz, Boolean isPozLightShort, Boolean isLightCoverShort, Consts.CeduleState isCedule) {
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
    public void renderPozorVlak(String Distance, Consts.CeduleState isCedule, Boolean hasPoz) {
        switch (isCedule) {
            case UP:
                this.renderPozorUp();
                break;
            case DOWN:
                if (hasPoz) {
                    this.renderPozorDn();
                } else {
                    this.renderPozorMid();
                }
                break;
            case NONE:
            default:
                break;
        }
    }

    @Override
    public float[] getPozorVlakPivotOffset(Consts.DistFromPole dist) {
        // pozor_vlak_*_ram (sssrVyst_noveTextury.obj) hinges on the pole axis (z≈0), independent
        // of the light head's distance from the pole.
        return new float[]{0f, 0f};
    }

    @Override
    public void renderKriz(String Distance, Boolean isKrizJedno, Boolean isKrizNaStozaru, Boolean isSlovak, Boolean isReflective, Boolean isKrizVelky) {
        String IsKrizJedno = isKrizJedno ? "kriz_1k" : "kriz_xk";
        String IsKrizVelky = isKrizVelky ? "_velky" : "_maly";

        if (isSlovak) {
            if (isKrizJedno) {
                if (isReflective) {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSK);
                    this.modelCrossSSSR.renderPart("kriz_sk_refl");
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSK);
                    this.modelCrossSSSR.renderPart("kriz_sk");
                }
            } else {
                if (isReflective) {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSKVic);
                    this.modelCrossSSSR.renderPart("kriz_sk_refl");
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSKVic);
                    this.modelCrossSSSR.renderPart("kriz_sk");
                }
            }
        } else {
            if (isReflective) {
                if (isKrizVelky) {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZReflVelky);
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZRefl);
                }
                this.modelCrossSSSR.renderPart(IsKrizJedno + "_refl" + IsKrizVelky);
            } else {
                GL11.glDisable(GL11.GL_CULL_FACE);
                Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
                this.modelCrossSSSR.renderPart(IsKrizJedno + "_back" + IsKrizVelky);
                if (isKrizJedno) {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZ);
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZVic);
                }
                this.modelCrossSSSR.renderPart(IsKrizJedno + "_front" + IsKrizVelky);
            }

            Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
            this.modelCrossSSSR.renderPart("kriz_ram");

            GL11.glEnable(GL11.GL_CULL_FACE);
        }
    }

    private void renderHoukackaDn() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SkrinZadek);
        this.modelCrossSSSR.renderPart("houkacka_dn_body");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Ksilt);
        this.modelCrossSSSR.renderPart("houkacka_dn_inside");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSR.renderPart("houkacka_dn_ram");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Cerna);
        this.modelCrossSSSR.renderPart("houkacka_dn_cerna");
    }

    private void renderHoukackaUp() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SkrinZadek);
        this.modelCrossSSSR.renderPart("houkacka_up_body");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Ksilt);
        this.modelCrossSSSR.renderPart("houkacka_up_inside");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSR.renderPart("houkacka_up_ram");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Cerna);
        this.modelCrossSSSR.renderPart("houkacka_up_cerna");
    }

    private void renderPoz(String Pos) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSR.renderPart("pozitivka_ram");
        this.modelCrossSSSR.renderPart("pozitivka_zamek_" + Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Svetlo_Zhas);
        this.modelCrossSSSR.renderPart("pozitivka_korona_" + Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Zadek_A);
        this.modelCrossSSSR.renderPart("pozitivka_base_back_" + Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Ksilt);
        this.modelCrossSSSR.renderPart("pozitivka_base_" + Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Zadek_B);
        this.modelCrossSSSR.renderPart("pozitivka_back2_" + Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Zadek);
        this.modelCrossSSSR.renderPart("pozitivka_back_" + Pos);
    }

    private void renderPozLong(String Pos) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Ksilt);
        this.modelCrossSSSR.renderPart("pozitivka_stinidlo_long_" + Pos);
    }

    private void renderPozShort(String Pos) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Ksilt);
        this.modelCrossSSSR.renderPart("pozitivka_stinidlo_short_" + Pos);
    }

    private void renderPozorUp() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSR.renderPart("pozor_vlak_up_ram");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Pozor_Vlak);
        this.modelCrossSSSR.renderPart("pozor_vlak_up_front");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Pozor_Vlak);
        this.modelCrossSSSR.renderPart("pozor_vlak_up_back");
    }

    private void renderPozorMid() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSR.renderPart("pozor_vlak_mid_ram");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Pozor_Vlak);
        this.modelCrossSSSR.renderPart("pozor_vlak_mid_front");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Pozor_Vlak);
        this.modelCrossSSSR.renderPart("pozor_vlak_mid_back");
    }

    private void renderPozorDn() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSR.renderPart("pozor_vlak_dn_ram");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Pozor_Vlak);
        this.modelCrossSSSR.renderPart("pozor_vlak_dn_front");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Pozor_Vlak);
        this.modelCrossSSSR.renderPart("pozor_vlak_dn_back");
    }

    private void renderStupacka() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SkrinZadek);
        this.modelCrossSSSR.renderPart("stupacka_zaklad");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSR.renderPart("stupacka_stozar");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stupacka);
        this.modelCrossSSSR.renderPart("stupacka");
    }

    private void renderZebrik() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSR.renderPart("zebrik");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SkrinZadek);
        this.modelCrossSSSR.renderPart("zebrik_srouby");
    }

    @Override
    public void renderSvetloL(String Distance, String Pos, Integer angleIndex, Boolean doLightsAlter) {
        Pos = resolvePos(Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Svetlo_C);
        this.modelCrossSSSR.renderPart("korona_l_" + Pos);
    }

    @Override
    public void renderSvetloR(String Distance, String Pos, Integer angleIndex, Boolean doLightsAlter) {
        Pos = resolvePos(Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Svetlo_C);
        this.modelCrossSSSR.renderPart("korona_p_" + Pos);
    }

    @Override
    public void renderSvetloPoz(String Distance, String Pos, Boolean isNewer) {
        Pos = resolvePos(Pos);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Svetlo_B);
        this.modelCrossSSSR.renderPart("korona_ps_" + Pos);
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
