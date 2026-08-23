package signalcraft.models.levelCrossing.azd;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.models.ModelRegistry;
import signalcraft.models.levelCrossing.ModelCross;
import signalcraft.signalUtils.Consts;

public class ModelAZD71Head extends ModelCross {
    private final IModelCustom modelAZD71Head = ModelRegistry.AZD_71_HEAD.getModel();;
    @Override
    public void renderVystraznik(String Distance, String Pos, Boolean hasPoz, Boolean isPozLightShort, Boolean isLightCoverShort, Consts.CeduleState isCedule) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Svetlo_Zhas_Red);
        this.modelAZD71Head.renderPart("azd71_korona_" + Distance);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Svetlo_Zhas_Whi);
        this.modelAZD71Head.renderPart("azd71_korona_poz_" + Distance);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Pozor_Vlak);
        this.modelAZD71Head.renderPart("azd71_pozor_vlak_" + Distance);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SkrinZadek);
        this.modelAZD71Head.renderPart("azd71_skrin_" + Distance);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Cerna);
        this.modelAZD71Head.renderPart("azd71_stinidlo_" + Distance);
        if (hasPoz) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Predek);
            this.modelAZD71Head.renderPart("azd71_predek_poz_" + Distance);
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Cerna);
            this.modelAZD71Head.renderPart("azd71_stinidlo_poz_" + Distance);
        } else {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Predek);
            this.modelAZD71Head.renderPart("azd71_predek_" + Distance);
        }
    }

    @Override
    public void renderSloup(String Distance, Boolean hasZebrik, Consts.CeduleState isCedule, Boolean isKrizNaStozaru) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelAZD71Head.renderPart("azd71_sloup_" + Distance);
    }

    @Override
    public void renderKriz(String Distance, Boolean isKrizJedno, Boolean isKrizNaStozaru, Boolean isSlovak, Boolean isReflective, Boolean isKrizVelky) {
        String IsKrizJedno = isKrizJedno ? "kriz_1k" : "kriz_xk";
        String IsKrizVelky = isKrizVelky ? "_velky" : "_maly";

        if (isSlovak) {
            if (isKrizJedno) {
                if (isReflective) {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSK);
                    this.modelAZD71Head.renderPart("kriz_sk_refl_" + Distance);
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSK);
                    this.modelAZD71Head.renderPart("kriz_sk_" + Distance);
                }
            } else {
                if (isReflective) {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSKVic);
                    this.modelAZD71Head.renderPart("kriz_sk_refl_" + Distance);
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSKVic);
                    this.modelAZD71Head.renderPart("kriz_sk_" + Distance);
                }
            }
        } else {
            GL11.glDisable(GL11.GL_CULL_FACE);
            if (isKrizNaStozaru) {
                if (isReflective) {
                    if (isKrizVelky){
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZReflVelky);
                    } else {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZRefl);
                    }
                    this.modelAZD71Head.renderPart(IsKrizJedno + "_refl_stozar"+ IsKrizVelky);
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
                    this.modelAZD71Head.renderPart(IsKrizJedno + "_back_stozar" + IsKrizVelky);
                    if (isKrizJedno) {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZ);
                    } else {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZVic);
                    }
                    this.modelAZD71Head.renderPart(IsKrizJedno + "_front_stozar" + IsKrizVelky);
                }
                Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
                this.modelAZD71Head.renderPart("kriz_stozar");
            } else {
                if (isReflective) {
                    if (isKrizVelky){
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZReflVelky);
                    } else {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZRefl);
                    }
                    this.modelAZD71Head.renderPart(IsKrizJedno + "_refl_" + Distance + IsKrizVelky);
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
                    this.modelAZD71Head.renderPart(IsKrizJedno + "_back_" + Distance + IsKrizVelky);
                    if (isKrizJedno) {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZ);
                    } else {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZVic);
                    }
                    this.modelAZD71Head.renderPart(IsKrizJedno + "_front_" + Distance + IsKrizVelky);
                }

                Minecraft.getMinecraft().renderEngine.bindTexture(this.SkrinZadek);
                this.modelAZD71Head.renderPart(IsKrizJedno + "_ram_" + Distance);
            }
            GL11.glEnable(GL11.GL_CULL_FACE);
        }
    }

    @Override
    public float[] getKrizPivotOffset(Consts.DistFromPole dist, Boolean isKrizNaStozaru) {
        // kriz_stozar (azd71_vyst_zav.obj) sits on the pole axis (z≈0), not at the vystraznik's
        // per-distance Z offset — only override the stozar-mounted case.
        return isKrizNaStozaru ? new float[]{0f, 0f} : null;
    }

    @Override
    public void renderSvetloL(String Distance, String Pos, Integer angleIndex, Boolean doLightsAlter) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Svetlo_R);
        this.modelAZD71Head.renderPart("korona_l_" + Distance);
    }

    @Override
    public void renderSvetloR(String Distance, String Pos, Integer angleIndex, Boolean doLightsAlter) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Svetlo_R);
        this.modelAZD71Head.renderPart("korona_p_" + Distance);
    }

    @Override
    public void renderSvetloPoz(String Distance, String Pos, Boolean isNewer) {
        if (isNewer){
            Minecraft.getMinecraft().renderEngine.bindTexture(this.LED_SVETLO_WHITE);
            this.modelAZD71Head.renderPart("korona_poz_led_" + Distance);
        }else{
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Svetlo_W);
            this.modelAZD71Head.renderPart("korona_poz_" + Distance);
        }
    }
}
