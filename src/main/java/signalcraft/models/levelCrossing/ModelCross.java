package signalcraft.models.levelCrossing;

import net.minecraft.util.ResourceLocation;
import signalcraft.models.TextureRegistry;
import signalcraft.signalUtils.Consts;

public class ModelCross implements ILevelCrossingModel {
    protected final ResourceLocation Skrinka = TextureRegistry.SKRINKA.get();
    protected final ResourceLocation Podklad = TextureRegistry.PODKLAD.get();
    protected final ResourceLocation Zaklad = TextureRegistry.ZAKLAD.get();
    protected final ResourceLocation SkrinZadek = TextureRegistry.SKRIN_ZADEK.get();
    protected final ResourceLocation Stupacka = TextureRegistry.STUPACKA.get();
    protected final ResourceLocation Stozar = TextureRegistry.STOZAR.get();
    protected final ResourceLocation Pruhy = TextureRegistry.PRUHY.get();
    protected final ResourceLocation Svetlo_Zhas_Red = TextureRegistry.SVETLO_ZHAS_RED.get();
    protected final ResourceLocation Svetlo_Zhas_Whi = TextureRegistry.SVETLO_ZHAS_WHI.get();
    protected final ResourceLocation Pozor_Vlak = TextureRegistry.POZOR_VLAK.get();
    protected final ResourceLocation Cerna = TextureRegistry.CERNA.get();
    protected final ResourceLocation Predek = TextureRegistry.PREDEK.get();
    protected final ResourceLocation Svetlo_W = TextureRegistry.SVETLO_W.get();
    protected final ResourceLocation Svetlo_R = TextureRegistry.SVETLO_R.get();
    protected final ResourceLocation KrizSK = TextureRegistry.KRIZ_SK.get();
    protected final ResourceLocation KrizSKVic = TextureRegistry.KRIZ_SK_VIC.get();
    protected final ResourceLocation KrizCZ = TextureRegistry.KRIZ_CZ.get();
    protected final ResourceLocation KrizCZVic = TextureRegistry.KRIZ_CZ_VIC.get();
    protected final ResourceLocation KrizCZRefl = TextureRegistry.KRIZ_CZ_REFL.get();
    protected final ResourceLocation KrizCZReflVelky = TextureRegistry.KRIZ_CZ_REFL_VELKY.get();

    protected final ResourceLocation SSSR_Cerna = TextureRegistry.SSSR_CERNA.get();
    protected final ResourceLocation SSSR_Ksilt = TextureRegistry.SSSR_KSILT.get();
    protected final ResourceLocation SSSR_Zadek = TextureRegistry.SSSR_ZADEK.get();
    protected final ResourceLocation SSSR_Zadek_A = TextureRegistry.SSSR_ZADEK_A.get();
    protected final ResourceLocation SSSR_Zadek_B = TextureRegistry.SSSR_ZADEK_B.get();
    protected final ResourceLocation SSSR_Svetlo_Zhas = TextureRegistry.SSSR_SVETLO_ZHAS.get();
    protected final ResourceLocation SSSR_Svetlo_C = TextureRegistry.SSSR_SVETLO_RED.get();
    protected final ResourceLocation SSSR_Svetlo_B = TextureRegistry.SSSR_SVETLO_WHITE.get();

    protected final ResourceLocation AZD97_HLAVNI = TextureRegistry.AZD97_HLAVNI.get();
    protected final ResourceLocation LED_SVETLO_WHITE = TextureRegistry.LED_SVETLO_WHITE.get();

    protected final ResourceLocation VUD = TextureRegistry.VUD.get();
    protected final ResourceLocation RED_LIGHT_VUD = TextureRegistry.RED_LIGHT_VUD.get();

    @Override
    public void renderZaklad(String Pos, Boolean hasPoz, Boolean isLightCoverShort) {

    }

    @Override
    public void renderStozar(String Distance, Boolean Stripes) {

    }

    @Override
    public void renderVystraznik(String Distance, String Pos, Boolean hasPoz, Boolean isPozLightShort, Boolean isLightCoverShort, Consts.CeduleState isCedule) {

    }

    @Override
    public void renderSloup(String Distance, Boolean hasZebrik, Consts.CeduleState isCedule, Boolean isKrizNaStozaru) {

    }

    @Override
    public void renderKriz(String Distance, Boolean isKrizJedno, Boolean isKrizNaStozaru, Boolean isSlovak, Boolean isReflective, Boolean isKrizVelky) {

    }

    @Override
    public float[] getKrizPivotOffset(Consts.DistFromPole dist, Boolean isKrizNaStozaru) {
        return null;
    }

    @Override
    public void renderPozorVlak(String Distance, Consts.CeduleState isCedule, Boolean hasPoz) {

    }

    @Override
    public float[] getPozorVlakPivotOffset(Consts.DistFromPole dist) {
        return null;
    }

    @Override
    public void renderSvetloL(String Distance, String Pos, Integer angleIndex, Boolean doLightsAlter) {

    }

    @Override
    public void renderSvetloR(String Distance, String Pos, Integer angleIndex, Boolean doLightsAlter) {

    }

    @Override
    public void renderSvetloPoz(String Distance, String Pos, Boolean isNewer) {

    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public void renderAll() {

    }

    @Override
    public void renderOnly(String... strings) {

    }

    @Override
    public void renderPart(String s) {

    }

    @Override
    public void renderAllExcept(String... strings) {

    }
}
