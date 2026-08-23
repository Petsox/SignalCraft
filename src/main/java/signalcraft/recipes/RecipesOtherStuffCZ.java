package signalcraft.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import signalcraft.ItemBlocks.SCItemBlocks;

import java.util.ArrayList;
import java.util.List;

public enum RecipesOtherStuffCZ {
    PR_UPOZ_SIGN(new ShapedOreRecipe(new ItemStack(SCItemBlocks.PR_UPOZ_SIGN.block, 1, 0),
            "wbw",
            "bbb",
            "rbr", 'r', SCItemBlocks.METAL_ROD.block, 'b', new ItemStack(Items.dye, 1, 0), 'w', new ItemStack(Items.dye, 1, 15))),

    SPEED_SIGN(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SPEED_SIGN.block, 1, 0),
            "wsw",
                    " p ",
                    " p ", 'p', SCItemBlocks.METAL_ROD.block, 's', Items.sign, 'w', new ItemStack(Items.dye, 1, 15)));

    public final ShapedOreRecipe recipe;

    RecipesOtherStuffCZ(ShapedOreRecipe recipe) {
        this.recipe = recipe;
    }

    public static List<ShapedOreRecipe> constructRecipesOtherStuffCZ() {
        List<ShapedOreRecipe> recipes = new ArrayList<>();
        for (RecipesOtherStuffCZ entry : values()) {
            recipes.add(entry.recipe);
        }
        return recipes;
    }
}
