package signalcraft.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import signalcraft.ItemBlocks.SCItemBlocks;
import signalcraft.items.SCItems;

import java.util.ArrayList;
import java.util.List;

public enum RecipesAZD70 {
    //Main Signals--------------------
    AZD_1_LIGHT(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_1_LIGHT.block, 1, 0),
            "   ",
            " l ",
            "wpr", 'p', SCItems.AZD70_POLE.item, 'l', SCItems.AZD70_1LIGHT.item, 'w', new ItemStack(Items.dye, 1, 15), 'r', new ItemStack(Items.dye, 1, 1))),

    AZD_2_LIGHTS(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_2_LIGHTS.block, 1, 0),
            "   ",
            " l ",
            "wpr", 'p', SCItems.AZD70_POLE.item, 'l', SCItems.AZD70_2LIGHTS.item, 'w', new ItemStack(Items.dye, 1, 15), 'r', new ItemStack(Items.dye, 1, 1))),

    AZD_3_LIGHTS(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_3_LIGHTS.block, 1, 0),
            "   ",
            " l ",
            "wpr", 'p', SCItems.AZD70_POLE.item, 'l', SCItems.AZD70_3LIGHTS.item, 'w', new ItemStack(Items.dye, 1, 15), 'r', new ItemStack(Items.dye, 1, 1))),

    AZD_4_LIGHTS(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_4_LIGHTS.block, 1, 0),
            "   ",
            " l ",
            "wpr", 'p', SCItems.AZD70_POLE.item, 'l', SCItems.AZD70_4LIGHTS.item, 'w', new ItemStack(Items.dye, 1, 15), 'r', new ItemStack(Items.dye, 1, 1))),

    AZD_5_LIGHTS(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_5_LIGHTS.block, 1, 0),
            "   ",
            " l ",
            "wpr", 'p', SCItems.AZD70_POLE.item, 'l', SCItems.AZD70_5LIGHTS.item, 'w', new ItemStack(Items.dye, 1, 15), 'r', new ItemStack(Items.dye, 1, 1))),

    AZD_6_LIGHTS(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_6_LIGHTS.block, 1, 0),
            "   ",
            " l ",
            "wpr", 'p', SCItems.AZD70_POLE.item, 'l', SCItems.AZD70_6LIGHTS.item, 'w', new ItemStack(Items.dye, 1, 15), 'r', new ItemStack(Items.dye, 1, 1))),

    //Distant Signal--------------------
    AZD_DIST(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_DIST.block, 1, 0),
            "   ",
            " l ",
            " pg", 'p', SCItems.AZD70_POLE.item, 'l', SCItems.AZD70_2LIGHTS.item, 'g', new ItemStack(Items.dye, 1, 8))),

    AZD_RE_DIST(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_RE_DIST.block, 1, 0),
            "   ",
            " l ",
            " pg", 'p', SCItems.AZD70_POLE.item, 'l', SCItems.AZD70_3LIGHTS.item, 'g', new ItemStack(Items.dye, 1, 8))),

    //AutoBlock Signals--------------------
    AZD_AB3(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_AB3.block, 1, 0),
            "   ",
            " l ",
            " pw", 'p', SCItems.AZD70_POLE.item, 'l', SCItems.AZD70_3LIGHTS.item, 'w', new ItemStack(Items.dye, 1, 15))),

    AZD_AB4(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_AB4.block, 1, 0),
            "   ",
            " l ",
            " pw", 'p', SCItems.AZD70_POLE.item, 'l', SCItems.AZD70_4LIGHTS.item, 'w', new ItemStack(Items.dye, 1, 15))),

    //Shunt Signal--------------------
    AZD_SHUNT(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_SHUNT.block, 1, 0),
            "   ",
            " l ",
            "bpw", 'p', SCItems.AZD70_POLE.item, 'l', SCItems.AZD70_2LIGHTS.item, 'w', new ItemStack(Items.dye, 1, 15), 'b', new ItemStack(Items.dye, 1, 12))),

    //Inserted Signal--------------------
    AZD_INSERTED(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_INSERTED.block, 1, 0),
            " l ",
            " r ",
            "bpw", 'p', SCItems.AZD70_POLE.item, 'l', SCItems.AZD70_3LIGHTS.item, 'w', new ItemStack(Items.dye, 1, 15), 'b', new ItemStack(Items.dye, 1, 12), 'r', new ItemStack(Items.dye, 1, 1))),

    //Dwarf Signals--------------------------------
    AZD_5_LIGHTS_T(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_5_LIGHTS_T.block, 1, 0),
            "   ",
            " lk",
            " sc", 'c', SCItems.CIRCUIT_AZD.item, 's', new ItemStack(Blocks.stone_slab, 1, 0), 'l', SCItems.AZD70_3LIGHTS.item, 'k', SCItems.AZD70_2LIGHTS.item)),

    AZD_4_LIGHTS_T(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_4_LIGHTS_T.block, 1, 0),
            "   ",
            " ll",
            " sc", 'c', SCItems.CIRCUIT_AZD.item, 's', new ItemStack(Blocks.stone_slab, 1, 0), 'l', SCItems.AZD70_2LIGHTS.item)),

    AZD_3_LIGHTS_T(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_3_LIGHTS_T.block, 1, 0),
            "   ",
            " l ",
            " sc", 'c', SCItems.CIRCUIT_AZD.item, 's', new ItemStack(Blocks.stone_slab, 1, 0), 'l', SCItems.AZD70_3LIGHTS.item)),

    AZD_2_LIGHTS_T(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_2_LIGHTS_T.block, 1, 0),
            "   ",
            " l ",
            " sc", 'c', SCItems.CIRCUIT_AZD.item, 's', new ItemStack(Blocks.stone_slab, 1, 0), 'l', SCItems.AZD70_2LIGHTS.item)),

    AZD_SHUNT_T(new ShapedOreRecipe(new ItemStack(SCItemBlocks.AZD_SHUNT_T.block, 1, 0),
            "  b",
            " lw",
            " sc", 'c', SCItems.CIRCUIT_AZD.item, 's', new ItemStack(Blocks.stone_slab, 1, 0), 'l', SCItems.AZD70_2LIGHTS.item, 'b', new ItemStack(Items.dye, 1, 12), 'w', new ItemStack(Items.dye, 1, 15)));

    public final ShapedOreRecipe recipe;

    RecipesAZD70(ShapedOreRecipe recipe) {
        this.recipe = recipe;
    }

    public static List<ShapedOreRecipe> constructRecipesAZD70() {
        List<ShapedOreRecipe> recipes = new ArrayList<>();
        for (RecipesAZD70 entry : values()) {
            recipes.add(entry.recipe);
        }
        return recipes;
    }
}
