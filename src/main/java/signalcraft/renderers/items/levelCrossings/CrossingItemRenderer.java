package signalcraft.renderers.items.levelCrossings;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import signalcraft.models.levelCrossing.ILevelCrossingModel;
import signalcraft.models.levelCrossing.vud.ModelVUD;
import signalcraft.signalUtils.Consts;

public class CrossingItemRenderer implements IItemRenderer
{
    private final ILevelCrossingModel modelCross;

    public CrossingItemRenderer(ILevelCrossingModel model) {
        this.modelCross = model;
    }
    
    public boolean handleRenderType(final ItemStack itemStack, final ItemRenderType type) {
        return true;
    }
    
    public boolean shouldUseRenderHelper(final ItemRenderType type, final ItemStack item, final ItemRendererHelper helper) {
        return true;
    }
    
    public void renderItem(final ItemRenderType type, final ItemStack item, final Object... data) {
        GL11.glPushMatrix();
        GL11.glRotatef(0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
        GL11.glTranslatef(0f, -0.75f, 0f);

        if (modelCross instanceof ModelVUD) GL11.glScalef(0.75f, 0.75f, 0.75f);

        this.modelCross.renderVystraznik(Consts.DistFromPole.DIST_50.Dist, Consts.Position.MIDDLE.Pos, true, false, false, Consts.CeduleState.DOWN);
        this.modelCross.renderZaklad(Consts.DistFromPole.DIST_50.Dist, true, false);
        this.modelCross.renderSloup(Consts.DistFromPole.DIST_50.Dist, false, Consts.CeduleState.DOWN, false);
        this.modelCross.renderStozar(Consts.DistFromPole.DIST_50.Dist, false);
        this.modelCross.renderKriz(Consts.DistFromPole.DIST_50.Dist, true, false, false, false, false);

        GL11.glPopMatrix();
    }
}
