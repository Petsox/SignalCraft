package signalcraft.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import signalcraft.ItemBlocks.SCItemBlocks;

import java.util.ArrayList;
import java.util.List;

public enum RecipesGSARBS {
    SIGN_NE1(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_NE1.block, 1, 0),
            " w ",
            "wrw",
            " r ", 'r', SCItemBlocks.METAL_ROD.block, 'w', new ItemStack(Items.dye, 1, 15))),

    SIGN_NE2(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_NE2.block, 1, 0),
            "wbw",
            "brb",
            " r ", 'r', SCItemBlocks.METAL_ROD.block, 'w', new ItemStack(Items.dye, 1, 15), 'b', new ItemStack(Items.dye, 1, 0))),

    SIGN_NE3_1(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_NE3_1.block, 1, 0),
            " w ",
            " b ",
            " r ", 'r', SCItemBlocks.METAL_ROD.block, 'w', new ItemStack(Items.dye, 1, 15), 'b', new ItemStack(Items.dye, 1, 0))),

    SIGN_NE4(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_NE4.block, 1, 0),
            " wb",
            " bw",
            "rwb", 'r', SCItemBlocks.METAL_ROD.block, 'w', new ItemStack(Items.dye, 1, 15), 'b', new ItemStack(Items.dye, 1, 0))),

    SIGN_NE6(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_NE6.block, 1, 0),
            " r ",
            "bwb",
            " r ", 'r', SCItemBlocks.METAL_ROD.block, 'w', new ItemStack(Items.dye, 1, 15), 'b', new ItemStack(Items.dye, 1, 0))),

    SIGN_NE7(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_NE7.block, 1, 0),
            " y ",
            "yry",
            " r ", 'r', SCItemBlocks.METAL_ROD.block, 'y', new ItemStack(Items.dye, 1, 11))),

    SIGN_NE12(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_NE12.block, 1, 0),
            " y ",
            " w ",
            " r ", 'r', SCItemBlocks.METAL_ROD.block, 'w', new ItemStack(Items.dye, 1, 15), 'y', new ItemStack(Items.dye, 1, 11))),

    SIGNAL_NE13(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGNAL_NE13.block, 1, 0),
            "lyl",
            "wtw",
            " t ", 'l', Blocks.redstone_lamp, 'w', new ItemStack(Items.dye, 1, 15), 'y', new ItemStack(Items.dye, 1, 11), 't', SCItemBlocks.METAL_ROD.block));

    public final ShapedOreRecipe recipe;

    RecipesGSARBS(ShapedOreRecipe recipe) {
        this.recipe = recipe;
    }

    public static List<ShapedOreRecipe> constructRecipesGSARBS() {
        GameRegistry.addShapelessRecipe(new ItemStack(SCItemBlocks.SIGN_NE3_2.block, 1, 0),
                SCItemBlocks.SIGN_NE3_1.block, new ItemStack(Items.dye, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(SCItemBlocks.SIGN_NE3_3.block, 1, 0),
                SCItemBlocks.SIGN_NE3_2.block, new ItemStack(Items.dye, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(SCItemBlocks.SIGN_NE5.block, 1, 0),
                SCItemBlocks.SIGN_NE2.block);

        List<ShapedOreRecipe> recipes = new ArrayList<>();
        for (RecipesGSARBS entry : values()) {
            recipes.add(entry.recipe);
        }
        return recipes;
    }
}
