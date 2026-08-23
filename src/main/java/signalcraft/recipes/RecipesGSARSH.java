package signalcraft.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import signalcraft.ItemBlocks.SCItemBlocks;

import java.util.ArrayList;
import java.util.List;

public enum RecipesGSARSH {
    SIGN_RA10A(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_RA10A.block, 1, 0),
            " w ",
            "www",
            " r ", 'r', SCItemBlocks.METAL_ROD.block, 'w', new ItemStack(Items.dye, 1, 15))),

    SIGN_RA11W(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_RA11W.block, 1, 0),
            " w ",
            " r ",
            " r ", 'r', SCItemBlocks.METAL_ROD.block, 'w', new ItemStack(Items.dye, 1, 15))),

    SIGN_RA11Y(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_RA11Y.block, 1, 0),
            " y ",
            " r ",
            " r ", 'r', SCItemBlocks.METAL_ROD.block, 'y', new ItemStack(Items.dye, 1, 11))),

    SIGN_RA11A(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_RA11A.block, 1, 0),
            "   ",
            " y ",
            " r ", 'r', SCItemBlocks.METAL_ROD.block, 'y', new ItemStack(Items.dye, 1, 11))),

    SIGN_RA11B(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_RA11B.block, 1, 0),
            "   ",
            " w ",
            " r ", 'r', SCItemBlocks.METAL_ROD.block, 'w', new ItemStack(Items.dye, 1, 15))),

    STATIV_RA11(new ShapedOreRecipe(new ItemStack(SCItemBlocks.STATIV_RA11.block, 1, 0),
            " r ",
            " i ",
            "ibi", 'r', SCItemBlocks.METAL_ROD.block, 'b', Blocks.iron_block, 'i', Items.iron_ingot));

    public final ShapedOreRecipe recipe;

    RecipesGSARSH(ShapedOreRecipe recipe) {
        this.recipe = recipe;
    }

    public static List<ShapedOreRecipe> constructRecipesGSARSH() {
        GameRegistry.addShapelessRecipe(new ItemStack(SCItemBlocks.SIGN_RA10B.block, 1, 0),
                SCItemBlocks.SIGN_RA10A.block);
        GameRegistry.addShapelessRecipe(new ItemStack(SCItemBlocks.SIGNAL_RA11WL.block, 1, 0),
                SCItemBlocks.SIGN_RA11W.block, Items.glowstone_dust);
        GameRegistry.addShapelessRecipe(new ItemStack(SCItemBlocks.SIGNAL_RA11YL.block, 1, 0),
                SCItemBlocks.SIGN_RA11Y.block, Items.glowstone_dust);

        List<ShapedOreRecipe> recipes = new ArrayList<>();
        for (RecipesGSARSH entry : values()) {
            recipes.add(entry.recipe);
        }
        return recipes;
    }
}
