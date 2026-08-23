package signalcraft.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import signalcraft.ItemBlocks.SCItemBlocks;

import java.util.ArrayList;
import java.util.List;

public enum RecipesGSARO {
    SIGN_HECTO(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_HECTO.block, 1, 0),
            " s ",
            " sw",
            " r ", 's', Items.sign, 'r', SCItemBlocks.METAL_ROD.block, 'w', new ItemStack(Items.dye, 1, 15))),

    STATION_SIGN(new ShapedOreRecipe(new ItemStack(SCItemBlocks.STATION_SIGN.block, 1, 0),
            " b ",
            "bsb",
            " b ", 's', Items.sign, 'b', new ItemStack(Items.dye, 1, 4))),

    SWITCH_MANUAL(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SWITCH_MANUAL.block, 1, 0),
            "   ",
            "lr ",
            "hhh", 'h', Blocks.heavy_weighted_pressure_plate, 'r', SCItemBlocks.METAL_ROD.block, 'l', Blocks.lever)),

    SWITCH_ELECTRIC(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SWITCH_ELECTRIC.block, 1, 0),
            "   ",
            "lrb",
            "hhh", 'h', Blocks.heavy_weighted_pressure_plate, 'r', SCItemBlocks.METAL_ROD.block, 'l', Blocks.lever, 'b', Blocks.iron_block));

    public final ShapedOreRecipe recipe;

    RecipesGSARO(ShapedOreRecipe recipe) {
        this.recipe = recipe;
    }

    public static List<ShapedOreRecipe> constructRecipesGSARO() {
        List<ShapedOreRecipe> recipes = new ArrayList<>();
        for (RecipesGSARO entry : values()) {
            recipes.add(entry.recipe);
        }
        return recipes;
    }
}
