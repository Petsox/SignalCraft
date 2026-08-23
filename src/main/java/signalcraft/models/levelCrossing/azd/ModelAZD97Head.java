package signalcraft.models.levelCrossing.azd;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.models.ModelRegistry;
import signalcraft.models.levelCrossing.ModelCross;
import signalcraft.signalUtils.Consts;

public class ModelAZD97Head extends ModelCross {
    public final IModelCustom modelCrossAZD97 = ModelRegistry.AZD_97_HEAD.getModel();

    @Override
    public void renderStozar(String Distance, Boolean Stripes) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.AZD97_HLAVNI);
        this.modelCrossAZD97.renderPart("vystraznik_drzak_horni");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossAZD97.renderPart("vystraznik_drzak_spodni");

        if (Stripes) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
            this.modelCrossAZD97.renderPart("azd97_stozar_dlouhy_pruhy");
        } else {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
            this.modelCrossAZD97.renderPart("azd97_stozar_dlouhy");
        }
        if (Stripes) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Pruhy);
            this.modelCrossAZD97.renderPart("azd97_stozar_pruhy");
        }
    }

    @Override
    public void renderSloup(String Distance, Boolean hasZebrik, Consts.CeduleState isCedule, Boolean isKrizNaStozaru) {
        if(!isKrizNaStozaru){
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
            this.modelCrossAZD97.renderPart("nosnik");
        }
    }

    @Override
    public void renderVystraznik(String Distance, String Pos, Boolean hasPoz, Boolean isPozLightShort, Boolean isLightCoverShort, Consts.CeduleState isCedule) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.AZD97_HLAVNI);
        this.modelCrossAZD97.renderPart("korona_p_off");
        this.modelCrossAZD97.renderPart("korona_l_off");
        if (hasPoz) this.modelCrossAZD97.renderPart("korona_poz_off");
        this.modelCrossAZD97.renderPart("vystraznik");
        if (hasPoz) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Cerna);
            this.modelCrossAZD97.renderPart("stinidlo_poz");
        }
    }

    @Override
    public void renderKriz(String Distance, Boolean isKrizJedno, Boolean isKrizNaStozaru, Boolean isSlovak, Boolean isReflective, Boolean isKrizVelky) {
        String IsKrizJedno = isKrizJedno ? "kriz_1k" : "kriz_xk";
        String IsKrizVelky = isKrizVelky ? "_velky" : "_maly";
        String IsKrizNaStozaru = isKrizNaStozaru ? "_stozar" : "";

        if (isSlovak) {
            if (isKrizNaStozaru){
                Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
                this.modelCrossAZD97.renderPart("kriz_drzak_stozar");
            } else {
                Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
                this.modelCrossAZD97.renderPart("kriz_drzak");
            }
            if (isKrizJedno) {
                if (isReflective) {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSK);
                    this.modelCrossAZD97.renderPart("kriz_sk_refl" + IsKrizNaStozaru);
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSK);
                    this.modelCrossAZD97.renderPart("kriz_sk" + IsKrizNaStozaru);
                }
            } else {
                if (isReflective) {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSKVic);
                    this.modelCrossAZD97.renderPart("kriz_sk_refl" + IsKrizNaStozaru);
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSKVic);
                    this.modelCrossAZD97.renderPart("kriz_sk" + IsKrizNaStozaru);
                }
            }
        } else {
            if (isKrizNaStozaru) {
                if (isReflective) {
                    if (isKrizVelky) {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZReflVelky);
                    } else {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZRefl);
                    }
                    this.modelCrossAZD97.renderPart(IsKrizJedno + "_refl_stozar" + IsKrizVelky);
                } else {
                    GL11.glDisable(GL11.GL_CULL_FACE);
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
                    this.modelCrossAZD97.renderPart(IsKrizJedno + "_back_stozar" + IsKrizVelky);

                    if (isKrizJedno) {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZ);
                    } else {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZVic);
                    }
                    this.modelCrossAZD97.renderPart(IsKrizJedno + "_front_stozar" + IsKrizVelky);
                }
                Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
                this.modelCrossAZD97.renderPart("kriz_drzak_stozar");
            } else {
                if (isReflective) {
                    if (isKrizVelky) {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZReflVelky);
                    } else {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZRefl);
                    }
                    this.modelCrossAZD97.renderPart(IsKrizJedno + "_refl" + IsKrizVelky);
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
                    this.modelCrossAZD97.renderPart(IsKrizJedno + "_back" + IsKrizVelky);
                    if (isKrizJedno) {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZ);
                    } else {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZVic);
                    }
                    this.modelCrossAZD97.renderPart(IsKrizJedno + "_front" + IsKrizVelky);
                }
                Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
                this.modelCrossAZD97.renderPart("kriz_drzak");
                GL11.glEnable(GL11.GL_CULL_FACE);
            }
        }
    }

    @Override
    public float[] getKrizPivotOffset(Consts.DistFromPole dist, Boolean isKrizNaStozaru) {
        // Y is inert for a Y-axis rotation; only Z (kriz_drzak / kriz_drzak_stozar center in
        // azd97_vyst.obj) matters — the shared vystraznik pivot sits at the wrong Z for AZD97's Kriz.
        return isKrizNaStozaru ? new float[]{0f, 0f} : new float[]{0f, 0.256f};
    }

    @Override
    public void renderSvetloL(String Distance, String Pos, Integer angleIndex, Boolean doLightsAlter) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Svetlo_R);
        this.modelCrossAZD97.renderPart("korona_l");
    }

    @Override
    public void renderSvetloR(String Distance, String Pos, Integer angleIndex, Boolean doLightsAlter) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Svetlo_R);
        this.modelCrossAZD97.renderPart("korona_p");
    }

    @Override
    public void renderSvetloPoz(String Distance, String Pos, Boolean isNewer) {
        if (isNewer){
            Minecraft.getMinecraft().renderEngine.bindTexture(this.LED_SVETLO_WHITE);
            this.modelCrossAZD97.renderPart("korona_poz_led");
        } else {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Svetlo_W);
            this.modelCrossAZD97.renderPart("korona_poz");
        }
    }
}
