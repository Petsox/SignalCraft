package signalcraft.models.levelCrossing;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.IModelCustom;
import signalcraft.signalUtils.Consts;

public interface ILevelCrossingModel extends IModelCustom {
    @SideOnly(Side.CLIENT)
    void renderZaklad(String Pos, Boolean hasPoz, Boolean isLightCoverShort);
    @SideOnly(Side.CLIENT)
    void renderStozar(String Distance, Boolean Stripes);
    @SideOnly(Side.CLIENT)
    void renderVystraznik(String Distance, String Pos,Boolean hasPoz, Boolean isPozLightShort, Boolean isLightCoverShort, Consts.CeduleState isCedule);
    @SideOnly(Side.CLIENT)
    void renderSloup(String Distance, Boolean hasZebrik, Consts.CeduleState isCedule, Boolean isKrizNaStozaru);
    @SideOnly(Side.CLIENT)
    void renderKriz(String Distance, Boolean isKrizJedno, Boolean isKrizNaStozaru, Boolean isSlovak, Boolean isReflective, Boolean isKrizVelky);
    @SideOnly(Side.CLIENT)
    // {y, z} pivot Kriz rotates around with headRot; null falls back to the vystraznik pivot.
    float[] getKrizPivotOffset(Consts.DistFromPole dist, Boolean isKrizNaStozaru);
    @SideOnly(Side.CLIENT)
    void renderPozorVlak(String Distance, Consts.CeduleState isCedule, Boolean hasPoz);
    @SideOnly(Side.CLIENT)
    // {y, z} pivot the Pozor Vlak sign rotates around with headRot; null falls back to the vystraznik pivot.
    float[] getPozorVlakPivotOffset(Consts.DistFromPole dist);
    @SideOnly(Side.CLIENT)
    void renderSvetloL(String Distance, String Pos, Integer angleIndex, Boolean doLightsAlter);
    @SideOnly(Side.CLIENT)
    void renderSvetloR(String Distance, String Pos, Integer angleIndex, Boolean doLightsAlter);
    @SideOnly(Side.CLIENT)
    void renderSvetloPoz(String Distance, String Pos, Boolean isNewer);
}

