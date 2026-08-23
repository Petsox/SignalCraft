package signalcraft.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import signalcraft.ItemBlocks.SCItemBlocks;

import java.util.ArrayList;
import java.util.List;

public enum RecipesGSARSS {
    SIGN_LF1(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_LF1.block, 1, 0),
            "yyy",
            "gy ",
            " rg", 'r', SCItemBlocks.METAL_ROD.block, 'y', new ItemStack(Items.dye, 1, 11), 'g', Items.glowstone_dust)),

    SIGN_LF2(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_LF2.block, 1, 0),
            " y ",
            " y ",
            " r ", 'r', SCItemBlocks.METAL_ROD.block, 'y', new ItemStack(Items.dye, 1, 11))),

    SIGN_LF3(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_LF3.block, 1, 0),
            " w ",
            " w ",
            " r ", 'r', SCItemBlocks.METAL_ROD.block, 'w', new ItemStack(Items.dye, 1, 15))),

    SIGN_LF6(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_LF6.block, 1, 0),
            "yyy",
            " s ",
            " r ", 'r', SCItemBlocks.METAL_ROD.block, 'y', new ItemStack(Items.dye, 1, 11), 's', Items.sign)),

    SIGN_LF7(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_LF7.block, 1, 0),
            " w ",
            " s ",
            " r ", 'r', SCItemBlocks.METAL_ROD.block, 'w', new ItemStack(Items.dye, 1, 15), 's', Items.sign));

    public final ShapedOreRecipe recipe;

    RecipesGSARSS(ShapedOreRecipe recipe) {
        this.recipe = recipe;
    }

    public static List<ShapedOreRecipe> constructRecipesGSARSS() {
        List<ShapedOreRecipe> recipes = new ArrayList<>();
        for (RecipesGSARSS entry : values()) {
            recipes.add(entry.recipe);
        }
        return recipes;
    }
}
