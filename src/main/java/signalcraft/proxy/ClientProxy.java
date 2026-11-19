package signalcraft.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import signalcraft.ItemBlocks.SCItemBlocks;
import signalcraft.entities.SCTEntities;
import signalcraft.gui.GuiItemRenamer;
import signalcraft.messages.MessageCrossingDebug;
import signalcraft.messages.MessageCrossingEvent;
import signalcraft.models.ModelRegistry;
import signalcraft.models.PartLibraryRegistry;
import signalcraft.renderers.entities.ScaledTileRenderer;
import signalcraft.gui.GuiItemScaler;

public class ClientProxy extends CommonProxy {

    @SideOnly(Side.CLIENT)
    private void registerModels() {
        for (ModelRegistry model : ModelRegistry.values()) {
            model.load();
        }
        for (PartLibraryRegistry library : PartLibraryRegistry.values()) {
            library.load();
        }
    }

    @SideOnly(Side.CLIENT)
    private void registerTileRenderers() {
        for (SCTEntities enumEntity : SCTEntities.values()) {
            // wrapped: applies the Scaler item's model scale where the renderer itself doesn't
            ClientRegistry.bindTileEntitySpecialRenderer(enumEntity.tileEntityClass, new ScaledTileRenderer(SCTEntities.Renderers.MAP.get(enumEntity).get()));
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
        CrossingClient crossingClient = new CrossingClient();
        MinecraftForge.EVENT_BUS.register(crossingClient);
        FMLCommonHandler.instance().bus().register(crossingClient);
    }

    @Override
    public void onCrossingEvent(MessageCrossingEvent message) {
        CrossingClient.onEvent(message);
    }

    @Override
    public void onCrossingDebug(MessageCrossingDebug message) {
        CrossingClient.onDebug(message);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void openRenamerGui(ItemStack itemStack) {
        Minecraft.getMinecraft().displayGuiScreen(new GuiItemRenamer(itemStack));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void openScalerGui(ItemStack itemStack) {
        Minecraft.getMinecraft().displayGuiScreen(new GuiItemScaler(itemStack));
    }
}
