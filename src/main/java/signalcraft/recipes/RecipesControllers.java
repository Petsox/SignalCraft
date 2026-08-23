package signalcraft.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import signalcraft.ItemBlocks.SCItemBlocks;
import signalcraft.items.SCItems;

import java.util.ArrayList;
import java.util.List;

public enum RecipesControllers {
    CONT_RECE_BASE(new ShapedOreRecipe(new ItemStack(SCItemBlocks.CONT_RECE_BASE.block, 1, 0),
            "iii",
            "ici",
            "iii", 'i', "ingotIron", 'c', SCItems.CIRCUIT_EMPTY.item));

    public final ShapedOreRecipe recipe;

    RecipesControllers(ShapedOreRecipe recipe) {
        this.recipe = recipe;
    }

    public static List<ShapedOreRecipe> constructRecipesControllers() {
        GameRegistry.addShapelessRecipe(new ItemStack(SCItemBlocks.CONT_UNIVERSAL.block, 1, 0),
                SCItemBlocks.CONT_RECE_BASE.block, SCItems.CIRCUIT_CONTROLLER.item);
        GameRegistry.addShapelessRecipe(new ItemStack(SCItemBlocks.RECE_UNIVERSAL.block, 1, 0),
                SCItemBlocks.CONT_RECE_BASE.block, SCItems.CIRCUIT_RECEIVER.item);

        GameRegistry.addShapelessRecipe(new ItemStack(SCItemBlocks.CONT_CROSSINGS.block, 1, 0),
                SCItemBlocks.CONT_RECE_BASE.block, SCItems.CIRCUIT_CONTROLLER.item, SCItems.METAL_GEAR.item);
        GameRegistry.addShapelessRecipe(new ItemStack(SCItemBlocks.RECE_CROSSINGS.block, 1, 0),
                SCItemBlocks.CONT_RECE_BASE.block, SCItems.CIRCUIT_RECEIVER.item, SCItems.METAL_GEAR.item);

        GameRegistry.addShapelessRecipe(new ItemStack(SCItemBlocks.CONT_REDSTONE_LIGHT_SIGNALS.block, 1, 0),
                SCItemBlocks.CONT_RECE_BASE.block, SCItems.CIRCUIT_CONTROLLER.item, Items.redstone);
        GameRegistry.addShapelessRecipe(new ItemStack(SCItemBlocks.RECE_REDSTONE_SIGNALS.block, 1, 0),
                SCItemBlocks.CONT_RECE_BASE.block, SCItems.CIRCUIT_RECEIVER.item, Items.redstone);

        GameRegistry.addShapelessRecipe(new ItemStack(SCItemBlocks.RECE_LIGHT_SIGNALS.block, 1, 0),
                SCItemBlocks.CONT_RECE_BASE.block, SCItems.CIRCUIT_RECEIVER.item, Blocks.redstone_lamp);

        List<ShapedOreRecipe> recipes = new ArrayList<>();
        for (RecipesControllers entry : values()) {
            recipes.add(entry.recipe);
        }
        return recipes;
    }
}
