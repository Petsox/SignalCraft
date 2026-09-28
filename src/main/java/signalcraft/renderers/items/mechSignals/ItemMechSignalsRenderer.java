package signalcraft.renderers.items.mechSignals;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.signals.mechSignals.TileMechSignal;
import signalcraft.models.mechSignals.IMechSignalModel;

public class ItemMechSignalsRenderer implements IItemRenderer
{
    private final IMechSignalModel modelSignal;
    private final TileMechSignal tileSignal;

    public ItemMechSignalsRenderer(IMechSignalModel model, TileMechSignal tile) {
        this.modelSignal = model;
        this.tileSignal = tile;
    }

    public boolean handleRenderType(final ItemStack item, final ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(final ItemRenderType type, final ItemStack item, final ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(final ItemRenderType type, final ItemStack item, final Object... data) {
        if (type == ItemRenderType.EQUIPPED) {
            GL11.glPushMatrix();
            GL11.glScalef(0.4f, 0.4f, 0.4f);
            GL11.glRotatef(30.0f, 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef(1.2f, 0.5f, 1.0f);
            modelSignal.renderItem(tileSignal);
            GL11.glPopMatrix();
        }
        else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glPushMatrix();
            GL11.glScalef(0.3f, 0.3f, 0.3f);
            GL11.glRotatef(-110.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.7f, 1.5f, 0.2f);
            modelSignal.renderItem(tileSignal);
            GL11.glPopMatrix();
        }
        else if (type != ItemRenderType.ENTITY) {
            GL11.glPushMatrix();
            GL11.glScalef(0.5f, 0.4f, 0.5f);
            GL11.glTranslatef(0.0f, -2.5f, 0.0f);
            modelSignal.renderItem(tileSignal);
            GL11.glPopMatrix();
        }
        else if (!(item.getItem() instanceof ItemBlock)) {
            GL11.glPushMatrix();
            modelSignal.renderItem(tileSignal);
            GL11.glPopMatrix();
        }
        else {
            GL11.glPushMatrix();
            GL11.glScalef(2.0f, 2.0f, 2.0f);
            modelSignal.renderItem(tileSignal);
            GL11.glPopMatrix();
        }
    }
}
