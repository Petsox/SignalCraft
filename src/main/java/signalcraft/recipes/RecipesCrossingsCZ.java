package signalcraft.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import signalcraft.ItemBlocks.SCItemBlocks;
import signalcraft.items.SCItems;

import java.util.ArrayList;
import java.util.List;

public enum RecipesCrossingsCZ {
    VUD(new ShapedOreRecipe(new ItemStack(SCItemBlocks.VUD.block, 1, 0),
            "l l",
            " r ",
            " w ", 'r', SCItemBlocks.METAL_ROD.block, 'w', Blocks.iron_block, 'l', Blocks.redstone_lamp)),

    AZD_71(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_71.block, 1, 0),
            "lcl",
            "plp",
            " b ", 'p', SCItemBlocks.METAL_ROD.block, 'b', Blocks.iron_block, 'c', SCItems.CIRCUIT_AZD.item, 'l', Blocks.redstone_lamp)),

    AZD_71_HEAD(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_71_HEAD.block, 1, 0),
            "lcl",
            "plp",
            "   ", 'p', SCItemBlocks.METAL_ROD.block, 'c', SCItems.CIRCUIT_AZD.item, 'l', Blocks.redstone_lamp)),

    AZD_97(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_97.block, 1, 0),
            "lcl",
            "plp",
            " b ", 'p', SCItemBlocks.METAL_ROD.block, 'b', Blocks.iron_block, 'c', SCItems.CIRCUIT_AZD97.item, 'l', Blocks.redstone_lamp)),

    AZD_97_HEAD(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_97_HEAD.block, 1, 0),
            "lcl",
            "plp",
            "   ", 'p', SCItemBlocks.METAL_ROD.block, 'c', SCItems.CIRCUIT_AZD97.item, 'l', Blocks.redstone_lamp)),

    AZD_99(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_99.block, 1, 0),
            "  p",
            "wwb",
            " cb", 'c', SCItems.CIRCUIT_AZD.item, 'p', SCItemBlocks.METAL_ROD.block, 'b', Blocks.iron_block, 'w', SCItems.BARRIER_LONG.item)),

    SSSR(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR.block, 1, 0),
            "lcl",
            "plp",
            " b ", 'c', SCItems.CIRCUIT_SSSR.item, 'p', SCItemBlocks.METAL_ROD.block, 'b', Blocks.iron_block, 'l', Blocks.redstone_lamp)),

    SSSR_SINGLE(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR_SINGLE.block, 1, 0),
            " l ",
            " b ",
            " c ", 'c', SCItems.CIRCUIT_SSSR.item, 'b', Blocks.iron_block, 'l', Blocks.redstone_lamp)),

    SSSR_HEAD(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR_HEAD.block, 1, 0),
            "lcl",
            "plp",
            "   ", 'c', SCItems.CIRCUIT_SSSR.item, 'p', SCItemBlocks.METAL_ROD.block, 'l', Blocks.redstone_lamp)),

    SSSR_SINGLE_HEAD(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR_SINGLE_HEAD.block, 1, 0),
            " l ",
                    " r ",
                    " c ", 'c', SCItems.CIRCUIT_SSSR.item, 'r', SCItemBlocks.METAL_ROD.block, 'l', Blocks.redstone_lamp)),
    ;
    public final ShapedOreRecipe recipe;

    RecipesCrossingsCZ(ShapedOreRecipe recipe) {
        this.recipe = recipe;
    }

    public static List<ShapedOreRecipe> constructRecipesCrossingsCZ() {
        List<ShapedOreRecipe> recipes = new ArrayList<>();
        for (RecipesCrossingsCZ entry : values()) {
            recipes.add(entry.recipe);
        }
        return recipes;
    }
}
