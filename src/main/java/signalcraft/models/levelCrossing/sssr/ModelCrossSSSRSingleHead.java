package signalcraft.models.levelCrossing.sssr;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.models.ModelRegistry;
import signalcraft.models.levelCrossing.ModelCross;
import signalcraft.signalUtils.Consts;

public class ModelCrossSSSRSingleHead extends ModelCross {
    private final IModelCustom modelCrossSSSR = ModelRegistry.SSSR_SINGLE_HEAD.getModel();

    @Override
    public void renderStozar(String Distance, Boolean Stripes) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSR.renderPart("svetlo_ram_" + Distance);
    }

    @Override
    public void renderSloup(String Distance, Boolean hasZebrik, Consts.CeduleState isCedule, Boolean isKrizNaStozaru) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossSSSR.renderPart("sloup_srouby");
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
        // kriz_ram (sssr_vyst_single_head.obj) hinges on the pole axis (z≈0) regardless of
        // Distance or stozar mounting — unlike Vystraznik, this mesh isn't authored per-distance.
        return new float[]{0f, 0f};
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
        // pozor_vlak_*_ram (sssr_vyst_single_head.obj) hinges on the pole axis (z≈0), independent
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

    @Override
    public void renderSvetloR(String Distance, String Pos, Integer angleIndex, Boolean doLightsAlter) {
        if (doLightsAlter && angleIndex % 2 == 0){
            Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Svetlo_C);
            this.modelCrossSSSR.renderPart("svetlo_korona_R_" + Distance);
        }
    }

    @Override
    public void renderSvetloL(String Distance, String Pos, Integer angleIndex, Boolean doLightsAlter) {
        if (doLightsAlter && angleIndex == 1){
            Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Svetlo_C);
            this.modelCrossSSSR.renderPart("svetlo_korona_L_" + Distance);
        } else if (doLightsAlter && angleIndex % 2 != 0) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Svetlo_C);
            this.modelCrossSSSR.renderPart("svetlo_korona_L_" + Distance);
        } else if (!doLightsAlter) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.SSSR_Svetlo_C);
            this.modelCrossSSSR.renderPart("svetlo_korona_L_" + Distance);
        }
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
}
