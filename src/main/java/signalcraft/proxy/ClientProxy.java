package signalcraft.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import signalcraft.ItemBlocks.SCItemBlocks;
import signalcraft.entities.SCTEntities;
import signalcraft.gui.GuiItemRenamer;
import signalcraft.models.ModelRegistry;

public class ClientProxy extends CommonProxy {

    @SideOnly(Side.CLIENT)
    private void registerModels() {
        for (ModelRegistry model : ModelRegistry.values()) {
            model.load();
        }
    }

    @SideOnly(Side.CLIENT)
    private void registerTileRenderers() {
        for (SCTEntities enumEntity : SCTEntities.values()) {
            ClientRegistry.bindTileEntitySpecialRenderer(enumEntity.tileEntityClass, SCTEntities.Renderers.MAP.get(enumEntity).get());
        }
    }

    @SideOnly(Side.CLIENT)
    private void registerItemRenderers() {
        for (SCItemBlocks itemBlock : SCItemBlocks.values()) {
            MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(itemBlock.block), SCItemBlocks.Renderers.MAP.get(itemBlock).get());
        }
    }

    @Override
    public void registerRenderers() {
        registerModels();
        registerTileRenderers();
        registerItemRenderers();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void openRenamerGui(ItemStack itemStack) {
        Minecraft.getMinecraft().displayGuiScreen(new GuiItemRenamer(itemStack));
    }
}
