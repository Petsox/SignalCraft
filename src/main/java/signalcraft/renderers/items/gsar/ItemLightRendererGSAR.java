package signalcraft.renderers.items.gsar;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import signalcraft.entities.gsar.signalsBU.TileGSARLightSignalBU0x3;
import signalcraft.entities.gsar.signalsHP.TileGSARLightSignalHPx3;
import signalcraft.entities.gsar.signalsHP.TileGSARLightSignalVRx5;
import signalcraft.entities.gsar.signalsHP.TileGSARLightSignal;
import signalcraft.entities.gsar.signalsSH.TileGSARLightSignalSHL;
import signalcraft.entities.gsar.signalsSH.TileGSARLightSignalSHLSingle;
import signalcraft.models.gsar.ILightSignalModelGSAR;

public class ItemLightRendererGSAR implements IItemRenderer {
    private final ILightSignalModelGSAR modelLightSignals;
    private final TileGSARLightSignal tileSignal;

    public ItemLightRendererGSAR(ILightSignalModelGSAR model, TileGSARLightSignal tile) {
        this.modelLightSignals = model;
        this.tileSignal = tile;
    }

    public boolean handleRenderType(final ItemStack item, final ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(final ItemRenderType type, final ItemStack item, final ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object... data) {
        float offsetSign;
        float offsetY = -2f;
        float scale = 0f;
        if (tileSignal instanceof TileGSARLightSignalHPx3 || tileSignal instanceof TileGSARLightSignalVRx5 || tileSignal instanceof TileGSARLightSignalBU0x3) {
            offsetSign = 5.5f;
        } else {
            offsetSign = 3f;
        }

        if (tileSignal instanceof TileGSARLightSignalSHL || tileSignal instanceof TileGSARLightSignalSHLSingle){
            scale = 0.25f;
            offsetY = -1f;
        }

        if (type == ItemRenderType.EQUIPPED) {
            GL11.glPushMatrix();
            GL11.glRotatef(30.0f, 0.0f, 0.0f, 1.0f);
            GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
            this.render(offsetY, offsetSign, 1.0f + scale);
            GL11.glPopMatrix();
        } else if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glPushMatrix();
            GL11.glRotatef(-110.0f, 0.0f, 1.0f, 0.0f);
            this.render(offsetY, offsetSign, 0.8f + scale);
            GL11.glPopMatrix();
        } else if (type != ItemRenderType.ENTITY) {
            GL11.glPushMatrix();
            GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
            this.render(offsetY, offsetSign, 0.45f + scale);
            GL11.glPopMatrix();
        } else if (!(item.getItem() instanceof ItemBlock)) {
            GL11.glPushMatrix();
            this.render(offsetY, offsetSign, 1.0f + scale);
            GL11.glPopMatrix();
        } else if (item.getItem() instanceof ItemBlock) {
            GL11.glPushMatrix();
            this.render(offsetY, offsetSign, 2.0f + scale);
            GL11.glPopMatrix();
        }
    }

    private void render(float offsetY, float offsetSign, float scale) {
        GL11.glScalef(scale, scale, scale);
        GL11.glTranslatef(0.0f, offsetY, 0.0f);
        this.modelLightSignals.renderNavestidlo(tileSignal);
        GL11.glTranslatef(0.0f, offsetSign, 0.0f);
        this.modelLightSignals.renderStoz(tileSignal);
    }
}
