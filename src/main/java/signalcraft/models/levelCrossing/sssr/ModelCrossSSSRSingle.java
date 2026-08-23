package signalcraft.models.levelCrossing.sssr;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.models.ModelRegistry;
import signalcraft.models.levelCrossing.ModelCross;
import signalcraft.signalUtils.Consts;

public class ModelCrossSSSRSingle extends ModelCross {
    private final IModelCustom modelCrossSSSR = ModelRegistry.SSSR_SINGLE.getModel();;

    @Override
    public void renderZaklad(String Pos, Boolean hasPoz, Boolean isLightCoverShort) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Podklad);
        this.modelCrossSSSR.renderPart("podklad");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Skrinka);
        this.modelCrossSSSR.renderPart("podstavec");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Zaklad);
        this.modelCrossSSSR.renderPart("zaklad");
    }

    @Override
    public void renderStozar(String Distance, Boolean Stripes) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSR.renderPart("svetlo_ram_" + Distance);
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
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SkrinZadek);
        this.modelCrossSSSR.renderPart("stupacka_zaklad");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSR.renderPart("stupacka_stozar");
        this.modelCrossSSSR.renderPart("sloup_srouby");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stupacka);
        this.modelCrossSSSR.renderPart("stupacka");
    }

    @Override
    public void renderVystraznik(String Distance, String Pos, Boolean hasPoz, Boolean isPozLightShort, Boolean isLightCoverShort, Consts.CeduleState isCedule) {
        if (isLightCoverShort) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Ksilt);
            this.modelCrossSSSR.renderPart("svetlo_stinidlo_short_" + Distance);
        } else {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Ksilt);
            this.modelCrossSSSR.renderPart("svetlo_stinidlo_long_" + Distance);
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSR.renderPart("svetlo_zamek_" + Distance);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Svetlo_Zhas);
        this.modelCrossSSSR.renderPart("svetlo_korona_" + Distance);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Zadek_A);
        this.modelCrossSSSR.renderPart("svetlo_base_back_" + Distance);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Ksilt);
        this.modelCrossSSSR.renderPart("svetlo_base_" + Distance);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Zadek_B);
        this.modelCrossSSSR.renderPart("svetlo_back2_" + Distance);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Zadek);
        this.modelCrossSSSR.renderPart("svetlo_back_" + Distance);
    }

    @Override
    public float[] getKrizPivotOffset(Consts.DistFromPole dist, Boolean isKrizNaStozaru) {
        return isKrizNaStozaru ? new float[]{0f, 0f} : null;
    }

    @Override
    public void renderPozorVlak(String Distance, Consts.CeduleState isCedule, Boolean hasPoz) {
        switch (isCedule) {
            case UP:
                this.renderPozorUp();
                break;
            case DOWN:
                this.renderPozorDn();
                break;
            case NONE:
            default:
                break;
        }
    }

    @Override
    public float[] getPozorVlakPivotOffset(Consts.DistFromPole dist) {
        // pozor_vlak_*_ram (sssr_vyst_single.obj) hinges on the pole axis (z≈0), independent
        // of the light head's distance from the pole.
        return new float[]{0f, 0f};
    }

    @Override
    public void renderKriz(String Distance, Boolean isKrizJedno, Boolean isKrizNaStozaru, Boolean isSlovak, Boolean isReflective, Boolean isKrizVelky) {
        String IsKrizJedno = isKrizJedno ? "kriz_1k" : "kriz_xk";
        String IsKrizVelky = isKrizVelky ? "_velky" : "_maly";

        GL11.glDisable(GL11.GL_CULL_FACE);

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

        }
        GL11.glEnable(GL11.GL_CULL_FACE);
    }

    private void renderPozorUp() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSR.renderPart("pozor_vlak_up_ram");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Pozor_Vlak);
        this.modelCrossSSSR.renderPart("pozor_vlak_up_front");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Pozor_Vlak);
        this.modelCrossSSSR.renderPart("pozor_vlak_up_back");
    }

    private void renderPozorDn() {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSR.renderPart("pozor_vlak_dn_ram");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Pozor_Vlak);
        this.modelCrossSSSR.renderPart("pozor_vlak_dn_front");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Pozor_Vlak);
        this.modelCrossSSSR.renderPart("pozor_vlak_dn_back");
    }

    @Override
    public void renderSvetloR(String Distance, String Pos, Integer angleIndex, Boolean doLightsAlter) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Svetlo_C);
        this.modelCrossSSSR.renderPart("svetlo_korona_R_" + Distance);
    }

}
