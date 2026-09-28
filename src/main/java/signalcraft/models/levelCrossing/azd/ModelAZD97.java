package signalcraft.models.levelCrossing.azd;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import signalcraft.models.ModelRegistry;
import signalcraft.models.levelCrossing.ModelCross;
import signalcraft.signalUtils.Consts;

public class ModelAZD97 extends ModelCross {
    public final IModelCustom modelCrossAZD97 = ModelRegistry.AZD_97.getModel();

    @Override
    public void renderZaklad(String Pos, Boolean hasPoz, Boolean isLightCoverShort) {
        this.beginFixedPole();
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Podklad);
        this.modelCrossAZD97.renderPart("azd97_podklad");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Zaklad);
        this.modelCrossAZD97.renderPart("azd97_zaklad");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Skrinka);
        this.modelCrossAZD97.renderPart("azd97_podstavec");
        this.endFixedPole();
    }

    // azd97_stozar_delsi (azd97_vyst.obj) is a pole-extension segment sitting directly on top of
    // the (always short) pole; stacking N copies extends the pole by N times its own height.
    private static final float STOZAR_DELSI_HEIGHT = 0.425960f;

    @Override
    public void renderStozar(String Distance, Boolean Stripes) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.AZD97_HLAVNI);
        this.modelCrossAZD97.renderPart("vystraznik_drzak_horni");
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        this.modelCrossAZD97.renderPart("vystraznik_drzak_spodni");

        // the holders above belong to the head and move with yOffset, only the mast stays
        this.beginFixedPole();
        if (Stripes) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
            this.modelCrossAZD97.renderPart("azd97_stozar_kratky_pruhy");
        } else {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
            this.modelCrossAZD97.renderPart("azd97_stozar_kratky");
        }
        if (Stripes) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.Pruhy);
            this.modelCrossAZD97.renderPart("azd97_stozar_pruhy");
        }
        this.endFixedPole();
    }

    @Override
    public void renderStozarDelsi(int count) {
        if (count <= 0) return;
        this.beginFixedPole();
        Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
        for (int i = 0; i < count; i++) {
            this.modelCrossAZD97.renderPart("azd97_stozar_delsi");
            GL11.glTranslatef(0f, STOZAR_DELSI_HEIGHT, 0f);
        }
        this.endFixedPole();
    }

    @Override
    public void renderSloup(String Distance, Boolean hasZebrik, Consts.CeduleState isCedule, Boolean isKrizNaStozaru, Boolean hasKriz) {
        if (!isKrizNaStozaru) {
            if (hasKriz) {
                Minecraft.getMinecraft().renderEngine.bindTexture(this.Stozar);
                this.modelCrossAZD97.renderPart("nosnik");
            }
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
            if (isKrizNaStozaru) {
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
                    if (isKrizJedno) {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZRefl);
                    } else if (isKrizVelky) {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZReflVelky);
                    } else {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizXkCZRefl);
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
                    if (isKrizJedno) {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZRefl);
                    } else if (isKrizVelky) {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizCZReflVelky);
                    } else {
                        Minecraft.getMinecraft().renderEngine.bindTexture(this.KrizXkCZRefl);
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
        Minecraft.getMinecraft().renderEngine.bindTexture(this.AZD97_RED_LIGHT);
        this.modelCrossAZD97.renderPart("korona_l");
    }

    @Override
    public void renderSvetloR(String Distance, String Pos, Integer angleIndex, Boolean doLightsAlter) {
        Minecraft.getMinecraft().renderEngine.bindTexture(this.AZD97_RED_LIGHT);
        this.modelCrossAZD97.renderPart("korona_p");
    }

    @Override
    public void renderSvetloPoz(String Distance, String Pos, Boolean isNewer) {
        if (isNewer) {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.LED_SVETLO_WHITE);
            this.modelCrossAZD97.renderPart("korona_poz_led");
        } else {
            Minecraft.getMinecraft().renderEngine.bindTexture(this.AZD97_WHTIE_LIGHT);
            this.modelCrossAZD97.renderPart("korona_poz");
        }
    }

}
