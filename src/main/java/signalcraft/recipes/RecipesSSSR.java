package signalcraft.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import signalcraft.ItemBlocks.SCItemBlocks;
import signalcraft.items.SCItems;

import java.util.ArrayList;
import java.util.List;

public enum RecipesSSSR {
    //Main Signals--------------------
    SSSR_5_LIGHTS(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR_5_LIGHTS.block, 1, 0),
            " q ",
            " x ",
            "wpr", 'p', SCItems.SSSR_POLE.item, 'x', SCItems.SSSR_2LIGHTS.item, 'q', SCItems.SSSR_3LIGHTS.item, 'w', new ItemStack(Items.dye, 1, 15), 'r', new ItemStack(Items.dye, 1, 1))),

    SSSR_4_LIGHTS(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR_4_LIGHTS.block, 1, 0),
            " x ",
            " x ",
            "wpr", 'p', SCItems.SSSR_POLE.item, 'x', SCItems.SSSR_2LIGHTS.item, 'w', new ItemStack(Items.dye, 1, 15), 'r', new ItemStack(Items.dye, 1, 1))),

    SSSR_3_LIGHTS(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR_3_LIGHTS.block, 1, 0),
            "   ",
            " q ",
            "wpr", 'p', SCItems.SSSR_POLE.item, 'q', SCItems.SSSR_3LIGHTS.item, 'w', new ItemStack(Items.dye, 1, 15), 'r', new ItemStack(Items.dye, 1, 1))),

    SSSR_2_LIGHTS(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR_2_LIGHTS.block, 1, 0),
            "   ",
            " x ",
            "wpr", 'p', SCItems.SSSR_POLE.item, 'x', SCItems.SSSR_2LIGHTS.item, 'w', new ItemStack(Items.dye, 1, 15), 'r', new ItemStack(Items.dye, 1, 1))),

    //Distant Signal--------------------
    SSSR_DISTANT(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR_DISTANT.block, 1, 0),
            "   ",
            " x ",
            " pg", 'p', SCItems.SSSR_POLE.item, 'x', SCItems.SSSR_2LIGHTS.item, 'g', new ItemStack(Items.dye, 1, 8))),

    SSSR_DISTANT_REPEATING(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR_DISTANT_REPEATING.block, 1, 0),
            "   ",
            " q ",
            " pg", 'p', SCItems.SSSR_POLE.item, 'q', SCItems.SSSR_3LIGHTS.item, 'g', new ItemStack(Items.dye, 1, 8))),

    //AutoBlock Signals--------------------
    SSSR_AB3(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR_AB3.block, 1, 0),
            "   ",
            " q ",
            " pw", 'p', SCItems.SSSR_POLE.item, 'q', SCItems.SSSR_3LIGHTS.item, 'w', new ItemStack(Items.dye, 1, 15))),

    SSSR_AB4(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR_AB4.block, 1, 0),
            " x ",
            " x ",
            " pw", 'p', SCItems.SSSR_POLE.item, 'x', SCItems.SSSR_2LIGHTS.item, 'w', new ItemStack(Items.dye, 1, 15))),

    //Shunt Signal--------------------
    SSSR_SHUNT(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR_SHUNT.block, 1, 0),
            "   ",
            " x ",
            "bpw", 'p', SCItems.SSSR_POLE.item, 'x', SCItems.SSSR_2LIGHTS.item, 'w', new ItemStack(Items.dye, 1, 15), 'b', new ItemStack(Items.dye, 1, 12))),

    //Inserted Signal--------------------
    SSSR_INSERTED(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR_INSERTED.block, 1, 0),
            " q ",
            " r ",
            "bpw", 'p', SCItems.SSSR_POLE.item, 'q', SCItems.SSSR_3LIGHTS.item, 'w', new ItemStack(Items.dye, 1, 15), 'b', new ItemStack(Items.dye, 1, 12), 'r', new ItemStack(Items.dye, 1, 1))),

    //Dwarf Signals--------------------------------
    SSSR_5_LIGHTS_T(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR_5_LIGHTS_T.block, 1, 0),
            "   ",
            " xq",
            " sc", 'c', SCItems.CIRCUIT_SSSR.item, 's', new ItemStack(Blocks.stone_slab, 1, 0), 'q', SCItems.SSSR_3LIGHTS.item, 'x', SCItems.SSSR_2LIGHTS.item)),

    SSSR_4_LIGHTS_T(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR_4_LIGHTS_T.block, 1, 0),
            "   ",
            " xx",
            " sc", 'c', SCItems.CIRCUIT_SSSR.item, 's', new ItemStack(Blocks.stone_slab, 1, 0), 'x', SCItems.SSSR_2LIGHTS.item)),

    SSSR_3_LIGHTS_T(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR_3_LIGHTS_T.block, 1, 0),
            "   ",
            " q ",
            " sc", 'c', SCItems.CIRCUIT_SSSR.item, 's', new ItemStack(Blocks.stone_slab, 1, 0), 'q', SCItems.SSSR_3LIGHTS.item)),

    SSSR_3_LIGHTS_MECH_T(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR_3_LIGHTS_MECH_T.block, 1, 0),
            "  r",
            " qw",
            " sc", 'c', SCItems.CIRCUIT_SSSR.item, 's', new ItemStack(Blocks.stone_slab, 1, 0), 'q', SCItems.SSSR_3LIGHTS.item, 'w', new ItemStack(Items.dye, 1, 15), 'r', new ItemStack(Items.dye, 1, 1))),

    SSSR_2_LIGHTS_T(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR_2_LIGHTS_T.block, 1, 0),
            "   ",
            " x ",
            " sc", 'c', SCItems.CIRCUIT_SSSR.item, 's', new ItemStack(Blocks.stone_slab, 1, 0), 'x', SCItems.SSSR_2LIGHTS.item)),

    SSSR_2_LIGHTS_MECH_T(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR_2_LIGHTS_MECH_T.block, 1, 0),
            "  r",
            " xw",
            " sc", 'c', SCItems.CIRCUIT_SSSR.item, 's', new ItemStack(Blocks.stone_slab, 1, 0), 'x', SCItems.SSSR_2LIGHTS.item, 'w', new ItemStack(Items.dye, 1, 15), 'r', new ItemStack(Items.dye, 1, 1))),

    SSSR_SHUNT_T(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SSSR_SHUNT_T.block, 1, 0),
            "  b",
            " xw",
            " sc", 'c', SCItems.CIRCUIT_SSSR.item, 's', new ItemStack(Blocks.stone_slab, 1, 0), 'x', SCItems.SSSR_2LIGHTS.item, 'b', new ItemStack(Items.dye, 1, 12), 'w', new ItemStack(Items.dye, 1, 15)));

    public final ShapedOreRecipe recipe;

    RecipesSSSR(ShapedOreRecipe recipe) {
        this.recipe = recipe;
    }

    public static List<ShapedOreRecipe> constructRecipesSSSR() {
        List<ShapedOreRecipe> recipes = new ArrayList<>();
        for (RecipesSSSR entry : values()) {
            recipes.add(entry.recipe);
        }
        return recipes;
    }
}
