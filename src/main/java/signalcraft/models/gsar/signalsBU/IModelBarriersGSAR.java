package signalcraft.models.gsar.signalsBU;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import signalcraft.entities.gsar.signalsBU.TileGSARCrossing;

public interface IModelBarriersGSAR {
    @SideOnly(Side.CLIENT)
    void renderBase(String position);
    @SideOnly(Side.CLIENT)
    void renderArmBase(TileGSARCrossing tileCrossing, String position,Boolean isArmLong, Boolean off);
    @SideOnly(Side.CLIENT)
    void renderBase2(String position);
    @SideOnly(Side.CLIENT)
    void renderReels(String position);
    @SideOnly(Side.CLIENT)
    void renderBell(String position);
    @SideOnly(Side.CLIENT)
    void renderItem(TileGSARCrossing tileCrossing);
}
