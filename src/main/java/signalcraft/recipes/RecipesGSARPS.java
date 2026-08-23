package signalcraft.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import signalcraft.ItemBlocks.SCItemBlocks;
import signalcraft.items.SCItems;

import java.util.ArrayList;
import java.util.List;

public enum RecipesGSARPS {
    SIGNAL_SHF_SINGLE(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGNAL_SHF_SINGLE.block, 1, 0),
            " b ",
            "wgw",
            " i ", 'i', Blocks.iron_block, 'g', SCItems.METAL_GEAR.item, 'b', new ItemStack(Items.dye, 1, 0), 'w', new ItemStack(Items.dye, 1, 15))),

    SIGNAL_SHL_SINGLE(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGNAL_SHL_SINGLE.block, 1, 0),
            "lll",
            "lib",
            " i ", 'i', Blocks.iron_block, 'b', new ItemStack(Items.dye, 1, 0), 'l', Blocks.redstone_lamp)),

    SIGNAL_SHF(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGNAL_SHF.block, 1, 0),
            " s ",
            " t ",
            " t ", 's', SCItemBlocks.SIGNAL_SHF_SINGLE.block, 't', SCItemBlocks.METAL_ROD.block)),

    SIGNAL_SHL(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGNAL_SHL.block, 1, 0),
            " s ",
            " t ",
            " t ", 's', SCItemBlocks.SIGNAL_SHL_SINGLE.block, 't', SCItemBlocks.METAL_ROD.block)),

    SIGNAL_SH2(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGNAL_SH2.block, 1, 0),
            "rlr",
            "rrr",
            " m ", 'm', SCItemBlocks.METAL_ROD.block, 'r', new ItemStack(Items.dye, 1, 1), 'l', Blocks.redstone_lamp));

    public final ShapedOreRecipe recipe;

    RecipesGSARPS(ShapedOreRecipe recipe) {
        this.recipe = recipe;
    }

    public static List<ShapedOreRecipe> constructRecipesGSARPS() {
        List<ShapedOreRecipe> recipes = new ArrayList<>();
        for (RecipesGSARPS entry : values()) {
            recipes.add(entry.recipe);
        }
        return recipes;
    }
}
