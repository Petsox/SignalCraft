package signalcraft.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import signalcraft.ItemBlocks.SCItemBlocks;
import signalcraft.SignalCraft;
import signalcraft.blocks.SCBlocks;
import signalcraft.entities.SCTEntities;
import signalcraft.items.SCItems;
import signalcraft.network.PacketPipeline;
import signalcraft.packet.CustomPacketEventListener;
import signalcraft.packet.SPacketEditorOpen;
import signalcraft.packet.controllers.CPacketUpdateControllers;
import signalcraft.packet.controllers.SPacketUpdateControllers;
import signalcraft.packet.levelCrossings.CPacketUpdateCrossings;
import signalcraft.packet.levelCrossings.SPacketUpdateCrossings;
import signalcraft.packet.lightSignals.CPacketUpdateLightSignals;
import signalcraft.packet.lightSignals.SPacketUpdateLightSignals;
import signalcraft.packet.mechSignals.CPacketUpdateMechSignals;
import signalcraft.packet.mechSignals.SPacketUpdateMechSignals;
import signalcraft.packet.signSignals.CPacketUpdateSignSignals;
import signalcraft.packet.signSignals.SPacketUpdateSignSignals;
import signalcraft.packet.switches.CPacketUpdateSwitches;
import signalcraft.packet.switches.SPacketUpdateSwitches;
import signalcraft.recipes.RecipeHandler;

public class CommonProxy {

    public static CreativeTabs tabOther;
    public static CreativeTabs tabCrossings;
    public static CreativeTabs tabSignals;
    public static CreativeTabs tabGSAR;

    private void registerItems() {
        for (SCItems enumItem : SCItems.values()) {
            GameRegistry.registerItem(enumItem.item, enumItem.item.getUnlocalizedName());
        }
    }
    private void registerBlocks() {
        for (SCBlocks enumBlock : SCBlocks.values()) {
            GameRegistry.registerBlock(enumBlock.block, enumBlock.block.getUnlocalizedName());
        }
        for (SCItemBlocks enumItemBlocks : SCItemBlocks.values()) {
            GameRegistry.registerBlock(enumItemBlocks.block, enumItemBlocks.itemBlockClass, enumItemBlocks.block.getUnlocalizedName());
        }
    }
    private void registerTileEntities() {
        for (SCTEntities enumEntity : SCTEntities.values()) {
            GameRegistry.registerTileEntity(enumEntity.tileEntityClass, enumEntity.Id);
        }
    }

    private void registerCreativeTabs(){
        tabOther = new CreativeTabs(SignalCraft.MOD_ID + "_other") {
            @Override
            public Item getTabIconItem() { return SCItems.VYPRAVKA.item; }
        };
        tabCrossings = new CreativeTabs(SignalCraft.MOD_ID + "_crossings") {
            @Override
            public Item getTabIconItem() { return Item.getItemFromBlock(SCItemBlocks.AZD_71.block); }
        };
        tabSignals = new CreativeTabs(SignalCraft.MOD_ID + "_signals") {
            @Override
            public Item getTabIconItem() { return Item.getItemFromBlock(SCItemBlocks.AZD_5_LIGHTS.block); }
        };
        tabGSAR = new CreativeTabs(SignalCraft.MOD_ID + "_gsar") {
            @Override
            public Item getTabIconItem() { return Item.getItemFromBlock(SCItemBlocks.CROSS_LIGHT.block); }
        };
    }
    public void registerRenderers() {
    }
    public void openRenamerGui(ItemStack itemStack) {
    }
    public PacketPipeline packetPipeline;

    public void preInit(final FMLPreInitializationEvent event) {
        registerCreativeTabs();
        registerItems();
        registerBlocks();
    }

    public void init(final FMLInitializationEvent event) {
        (this.packetPipeline = new PacketPipeline()).registerEventListener(new CustomPacketEventListener(this.packetPipeline.getPacketsList()));
        this.packetPipeline.registerPacket(CPacketUpdateSignSignals.class);
        this.packetPipeline.registerPacket(SPacketUpdateSignSignals.class);
        this.packetPipeline.registerPacket(CPacketUpdateSwitches.class);
        this.packetPipeline.registerPacket(SPacketUpdateSwitches.class);
        this.packetPipeline.registerPacket(CPacketUpdateLightSignals.class);
        this.packetPipeline.registerPacket(SPacketUpdateLightSignals.class);
        this.packetPipeline.registerPacket(CPacketUpdateMechSignals.class);
        this.packetPipeline.registerPacket(SPacketUpdateMechSignals.class);
        this.packetPipeline.registerPacket(CPacketUpdateCrossings.class);
        this.packetPipeline.registerPacket(SPacketUpdateCrossings.class);
        this.packetPipeline.registerPacket(CPacketUpdateControllers.class);
        this.packetPipeline.registerPacket(SPacketUpdateControllers.class);
        this.packetPipeline.registerPacket(SPacketEditorOpen.class);

        registerTileEntities();
    }

    public void postInit(final FMLPostInitializationEvent event) {
        RecipeHandler.registerRecipes();
    }
}
