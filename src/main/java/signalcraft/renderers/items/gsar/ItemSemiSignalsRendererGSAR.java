package signalcraft.renderers.items.gsar;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsHP.TileGSARSemiSignal;
import signalcraft.models.gsar.ISemiSignalModelGSAR;

public class ItemSemiSignalsRendererGSAR implements IItemRenderer
{
    private final ISemiSignalModelGSAR modelSemiSignal2Wingsx5;
    private final TileGSARSemiSignal tileSemiSignal;

    public ItemSemiSignalsRendererGSAR(ISemiSignalModelGSAR model, TileGSARSemiSignal tile) {
        this.modelSemiSignal2Wingsx5 = model;
        this.tileSemiSignal = tile;
    }
    
    public boolean handleRenderType(final ItemStack item, final ItemRenderType type) {
        return true;
    }
    
    public boolean shouldUseRenderHelper(final ItemRenderType type, final ItemStack item, final ItemRendererHelper helper) {
        return true;
    }
    
    public void renderItem(final ItemRenderType type, final ItemStack item, final Object... data) {
        //final int i = 15728880;
        //final int j = i % 65536;
        //final int k = i / 65536;
        if (type == ItemRenderType.EQUIPPED) {
            GL11.glPushMatrix();
            GL11.glScalef(0.8f, 0.8f, 0.8f);
            GL11.glRotatef(30.0f, 0.0f, 0.0f, 1.0f);
            GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(1.2f, 0.5f, 1.0f);
            modelSemiSignal2Wingsx5.renderItem(tileSemiSignal);
            GL11.glPopMatrix();
        }
        else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glPushMatrix();
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            GL11.glRotatef(-110.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.7f, 1.5f, 0.2f);
            modelSemiSignal2Wingsx5.renderItem(tileSemiSignal);
            GL11.glPopMatrix();
        }
        else if (type != ItemRenderType.ENTITY) {
            GL11.glPushMatrix();
            GL11.glScalef(0.8f, 0.8f, 0.8f);
            GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.0f, -0.95f, 0.0f);
            modelSemiSignal2Wingsx5.renderItem(tileSemiSignal);
            GL11.glPopMatrix();
        }
        else if (!(item.getItem() instanceof ItemBlock)) {
            GL11.glPushMatrix();
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            modelSemiSignal2Wingsx5.renderItem(tileSemiSignal);
            GL11.glPopMatrix();
        }
        else if (item.getItem() instanceof ItemBlock) {
            GL11.glPushMatrix();
            GL11.glScalef(2.0f, 2.0f, 2.0f);
            modelSemiSignal2Wingsx5.renderItem(tileSemiSignal);
            GL11.glPopMatrix();
        }
    }
}
