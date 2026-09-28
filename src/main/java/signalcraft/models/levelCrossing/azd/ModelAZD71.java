package signalcraft.models.levelCrossing.azd;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.models.ModelRegistry;
import signalcraft.models.levelCrossing.ModelCross;
import signalcraft.signalUtils.Consts;

public class ModelAZD71 extends ModelCross {
    public final IModelCustom modelCrossAZD71 = ModelRegistry.AZD_71.getModel();

    @Override
    public void renderZaklad(String Pos, Boolean hasPoz, Boolean isLightCoverShort) {
        this.beginFixedPole();
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Podklad);
        this.modelCrossAZD71.renderPart("azd71_podklad");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Zaklad);
        this.modelCrossAZD71.renderPart("azd71_zaklad");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Skrinka);
        this.modelCrossAZD71.renderPart("azd71_podstavec");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stupacka);
        this.modelCrossAZD71.renderPart("azd71_stupacka");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SkrinZadek);
        this.modelCrossAZD71.renderPart("azd71_stupacka_zaklad");
        this.endFixedPole();
    }

    // azd71_stozar_delsi (azd71_vyst.obj) is a pole-extension segment sitting directly on top of
    // the (always short) pole; stacking N copies extends the pole by N times its own height.
    private static final float STOZAR_DELSI_HEIGHT = 0.514245f;

    @Override
    public void renderStozar(String Distance, Boolean Stripes) {
        this.beginFixedPole();
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        if (Stripes) {
            this.modelCrossAZD71.renderPart("azd71_stozar_kratky_pruhy");
        } else {
            this.modelCrossAZD71.renderPart("azd71_stozar_kratky");
        }
        if (Stripes) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Pruhy);
            this.modelCrossAZD71.renderPart("azd71_stozar_pruhy");
        }
        this.endFixedPole();
    }

    @Override
    public void renderStozarDelsi(int count) {
        if (count <= 0) return;
        this.beginFixedPole();
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        for (int i = 0; i < count; i++) {
            this.modelCrossAZD71.renderPart("azd71_stozar_delsi");
            GL11.glTranslatef(0f, STOZAR_DELSI_HEIGHT, 0f);
        }
        this.endFixedPole();
    }

    @Override
    public void renderVystraznik(String Distance, String Pos, Boolean hasPoz, Boolean isPozLightShort, Boolean isLightCoverShort, Consts.CeduleState isCedule) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Svetlo_Zhas_Red);
        this.modelCrossAZD71.renderPart("azd71_korona_" + Distance);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Svetlo_Zhas_Whi);
        this.modelCrossAZD71.renderPart("azd71_korona_poz_" + Distance);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Pozor_Vlak);
        this.modelCrossAZD71.renderPart("azd71_pozor_vlak_" + Distance);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.SkrinZadek);
        this.modelCrossAZD71.renderPart("azd71_skrin_" + Distance);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Cerna);
        this.modelCrossAZD71.renderPart("azd71_stinidlo_" + Distance);
        if (hasPoz) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Predek);
            this.modelCrossAZD71.renderPart("azd71_predek_poz_" + Distance);
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Cerna);
            this.modelCrossAZD71.renderPart("azd71_stinidlo_poz_" + Distance);
        } else {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Predek);
            this.modelCrossAZD71.renderPart("azd71_predek_" + Distance);
        }
    }

    @Override
    public void renderSloup(String Distance, Boolean hasZebrik, Consts.CeduleState isCedule, Boolean isKrizNaStozaru, Boolean hasKriz) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossAZD71.renderPart("azd71_sloup_" + Distance);
    }

    @Override
    public void renderKriz(String Distance, Boolean isKrizJedno, Boolean isKrizNaStozaru, Boolean isSlovak, Boolean isReflective, Boolean isKrizVelky) {
        String IsKrizJedno = isKrizJedno ? "kriz_1k" : "kriz_xk";
        String IsKrizVelky = isKrizVelky ? "_velky" : "_maly";

        if (isSlovak) {
            if (isKrizJedno) {
                if (isReflective) {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSK);
                    this.modelCrossAZD71.renderPart("kriz_sk_refl_" + Distance);
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSK);
                    this.modelCrossAZD71.renderPart("kriz_sk_" + Distance);
                }
            } else {
                if (isReflective) {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSKVic);
                    this.modelCrossAZD71.renderPart("kriz_sk_refl_" + Distance);
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizSKVic);
                    this.modelCrossAZD71.renderPart("kriz_sk_" + Distance);
                }
            }
        } else {
            if (Distance.equals("00")) {
                if (isReflective) {
                    if (isKrizJedno){
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZRefl);
                    } else if (isKrizVelky){
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZReflVelky);
                    } else {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizXkCZRefl);
                    }
                    this.modelCrossAZD71.renderPart(IsKrizJedno + "_refl_00" + IsKrizVelky);
                } else {
                    GL11.glDisable(GL11.GL_CULL_FACE);
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
                    this.modelCrossAZD71.renderPart(IsKrizJedno + "_back_00" + IsKrizVelky);

                    if (isKrizJedno) {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZ);
                    } else {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZVic);
                    }
                    this.modelCrossAZD71.renderPart(IsKrizJedno + "_front_00" + IsKrizVelky);
                }
                Minecraft.getMinecraft().renderEngine.bindTexture(this.SkrinZadek);
                this.modelCrossAZD71.renderPart("kriz_1k_ram_00");
                GL11.glEnable(GL11.GL_CULL_FACE);
            } else if (isKrizNaStozaru) {
                if (isReflective) {
                    if (isKrizJedno){
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZRefl);
                    } else if (isKrizVelky){
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZReflVelky);
                    } else {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizXkCZRefl);
                    }
                    this.modelCrossAZD71.renderPart(IsKrizJedno + "_refl_stozar" + IsKrizVelky);
                } else {
                    GL11.glDisable(GL11.GL_CULL_FACE);
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
                    this.modelCrossAZD71.renderPart(IsKrizJedno + "_back_stozar" + IsKrizVelky);
                    if (isKrizJedno) {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZ);
                    } else {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZVic);
                    }
                    this.modelCrossAZD71.renderPart(IsKrizJedno + "_front_stozar" + IsKrizVelky);
                }
                Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
                this.modelCrossAZD71.renderPart("kriz_stozar");
                GL11.glEnable(GL11.GL_CULL_FACE);
            } else {
                if (isReflective) {
                    if (isKrizJedno){
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZRefl);
                    } else if (isKrizVelky){
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZReflVelky);
                    } else {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizXkCZRefl);
                    }
                    this.modelCrossAZD71.renderPart(IsKrizJedno + "_refl_" + Distance + IsKrizVelky);
                } else {
                    Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
                    this.modelCrossAZD71.renderPart(IsKrizJedno + "_back_" + Distance + IsKrizVelky);
                    if (isKrizJedno) {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZ);
                    } else {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZVic);
                    }
                    this.modelCrossAZD71.renderPart(IsKrizJedno + "_front_" + Distance + IsKrizVelky);
                }
                GL11.glDisable(GL11.GL_CULL_FACE);
                Minecraft.getMinecraft().renderEngine.bindTexture(this.SkrinZadek);
                this.modelCrossAZD71.renderPart(IsKrizJedno + "_ram_" + Distance);
                GL11.glEnable(GL11.GL_CULL_FACE);
            }
        }
    }

    @Override
    public float[] getKrizPivotOffset(Consts.DistFromPole dist, Boolean isKrizNaStozaru) {
        // kriz_stozar (azd71_vyst.obj) sits on the pole axis (z≈0), not at the vystraznik's
        // per-distance Z offset — only override the stozar-mounted case.
        return isKrizNaStozaru ? new float[]{0f, 0f} : null;
    }

    @Override
    public void renderSvetloL(String Distance, String Pos, Integer angleIndex, Boolean doLightsAlter) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Svetlo_R);
        this.modelCrossAZD71.renderPart("korona_l_" + Distance);
    }

    @Override
    public void renderSvetloR(String Distance, String Pos, Integer angleIndex, Boolean doLightsAlter) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Svetlo_R);
        this.modelCrossAZD71.renderPart("korona_p_" + Distance);
    }

    @Override
    public void renderSvetloPoz(String Distance, String Pos, Boolean isNewer) {
        if (isNewer){
            Minecraft.getMinecraft().renderEngine.bindTexture(this.LED_SVETLO_WHITE);
            this.modelCrossAZD71.renderPart("korona_poz_led_" + Distance);
        }else{
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Svetlo_W);
            this.modelCrossAZD71.renderPart("korona_poz_" + Distance);
        }
    }

}
