package signalcraft.renderers.items.gsar;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.switches.TileSwitch;
import signalcraft.models.gsar.signalsWN.ModelSwitchesGSAR;

public class ItemSwitchRendererGSAR implements IItemRenderer
{
    private final ModelSwitchesGSAR modelSignalsWN;
    private final TileSwitch tileSwitch;

    public ItemSwitchRendererGSAR(ModelSwitchesGSAR model ,TileSwitch tileSwitch) {
        this.modelSignalsWN = model;
        this.tileSwitch = tileSwitch;
    }
    
    public boolean handleRenderType(final ItemStack itemStack, final ItemRenderType type) {
        return true;
    }
    
    public boolean shouldUseRenderHelper(final ItemRenderType type, final ItemStack item, final ItemRendererHelper helper) {
        return true;
    }
    
    public void renderItem(final ItemRenderType type, final ItemStack item, final Object... data) {
        if (type == ItemRenderType.EQUIPPED) {
            GL11.glPushMatrix();
            GL11.glScalef(1.8f, 1.8f, 1.8f);
            GL11.glRotatef(30.0f, 0.0f, 0.0f, 1.0f);
            GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.5f, -0.1f, 0.5f);
            modelSignalsWN.renderZaklad(tileSwitch);
            modelSignalsWN.renderPrestavnik(false, tileSwitch);
            modelSignalsWN.renderHlavaR("_P",tileSwitch);
            GL11.glPopMatrix();
        }
        else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glPushMatrix();
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            GL11.glRotatef(-110.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.7f, 0.8f, 0.2f);
            modelSignalsWN.renderZaklad(tileSwitch);
            modelSignalsWN.renderPrestavnik(false, tileSwitch);
            modelSignalsWN.renderHlavaR("_P",tileSwitch);
            GL11.glPopMatrix();
        }
        else if (type != ItemRenderType.ENTITY) {
            GL11.glPushMatrix();
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.0f, -0.5f, 0.0f);
            modelSignalsWN.renderZaklad(tileSwitch);
            modelSignalsWN.renderPrestavnik(false, tileSwitch);
            modelSignalsWN.renderHlavaR("_P",tileSwitch);
            GL11.glPopMatrix();
        }
        else if (!(item.getItem() instanceof ItemBlock)) {
            GL11.glPushMatrix();
            GL11.glScalef(1.0f, 1.0f, 1.0f);
            modelSignalsWN.renderZaklad(tileSwitch);
            modelSignalsWN.renderPrestavnik(false, tileSwitch);
            modelSignalsWN.renderHlavaR("_P",tileSwitch);
            GL11.glPopMatrix();
        }
        else if (item.getItem() instanceof ItemBlock) {
            GL11.glPushMatrix();
            GL11.glScalef(2.0f, 2.0f, 2.0f);
            modelSignalsWN.renderZaklad(tileSwitch);
            modelSignalsWN.renderPrestavnik(false, tileSwitch);
            modelSignalsWN.renderHlavaR("_P",tileSwitch);
            GL11.glPopMatrix();
        }
    }
}
