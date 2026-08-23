package signalcraft.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import signalcraft.ItemBlocks.SCItemBlocks;
import signalcraft.blocks.SCBlocks;
import signalcraft.items.SCItems;

import java.util.ArrayList;
import java.util.List;

public enum RecipesGSARBlocks {
    METAL_ROD(new ShapedOreRecipe(new ItemStack(SCItemBlocks.METAL_ROD.block, 2, 0),
            "   ",
            " b ",
            " b ", 'b', Blocks.iron_bars)),

    BRIDGE_BEAMS(new ShapedOreRecipe(new ItemStack(SCItemBlocks.BRIDGE_BEAMS.block, 4, 0),
            "rbr",
            "bbb",
            "rbr", 'b', Blocks.iron_bars, 'r', SCItemBlocks.METAL_ROD.block)),

    BRIDGE_GROUND(new ShapedOreRecipe(new ItemStack(SCItemBlocks.BRIDGE_GROUND.block, 4, 0),
            "rrr",
            "rbr",
            "rrr", 'r', SCItemBlocks.METAL_ROD.block, 'b', Blocks.iron_bars)),

    RAILING(new ShapedOreRecipe(new ItemStack(SCItemBlocks.RAILING.block, 2, 0),
            "rbr",
            " b ",
            "rbr", 'r', SCItemBlocks.METAL_ROD.block, 'b', Blocks.iron_bars)),

    RAILING_RODS(new ShapedOreRecipe(new ItemStack(SCItemBlocks.RAILING_RODS.block, 2, 0),
            "rrr",
            " r ",
            "rrr", 'r', SCItemBlocks.METAL_ROD.block)),

    RAILING_2(new ShapedOreRecipe(new ItemStack(SCItemBlocks.RAILING_2.block, 3, 0),
            "rbr",
            "rbr",
            "rbr", 'r', SCItemBlocks.METAL_ROD.block, 'b', SCItemBlocks.RAILING.block)),

    LADDER(new ShapedOreRecipe(new ItemStack(SCItemBlocks.LADDER.block, 6, 0),
            "rpr",
            "rpr",
            "rpr", 'r', SCItemBlocks.METAL_ROD.block, 'p', Blocks.heavy_weighted_pressure_plate)),

    BRIDGE_BEAMS_CROSS(new ShapedOreRecipe(new ItemStack(SCItemBlocks.BRIDGE_BEAMS_CROSS.block, 6, 0),
            " s ",
            "sbs",
            " s ", 's', SCItemBlocks.BRIDGE_GROUND.block, 'b', SCItemBlocks.BRIDGE_BEAMS.block)),

    BRIDGE_GROUND_BEAMS(new ShapedOreRecipe(new ItemStack(SCItemBlocks.BRIDGE_GROUND_BEAMS.block, 2, 0),
            " s ",
            " b ",
            " s ", 's', SCItemBlocks.BRIDGE_BEAMS.block, 'b', SCItemBlocks.BRIDGE_BEAMS_CROSS.block)),

    BRIDGE_BEAMS_CORNER(new ShapedOreRecipe(new ItemStack(SCItemBlocks.BRIDGE_BEAMS_CORNER.block, 2, 0),
            "   ",
            " bs",
            " s ", 's', SCItemBlocks.BRIDGE_BEAMS.block, 'b', SCItemBlocks.BRIDGE_BEAMS_CROSS.block)),

    BRIDGE_BEAMS_TRIPLE(new ShapedOreRecipe(SCItemBlocks.BRIDGE_BEAMS_TRIPLE.block,
            "   ",
            " b ",
            " s ", 's', SCItemBlocks.BRIDGE_BEAMS.block, 'b', SCItemBlocks.BRIDGE_BEAMS_CROSS.block)),

    TRACK_GRAVEL(new ShapedOreRecipe(new ItemStack(SCBlocks.TRACK_GRAVEL.block, 4, 0),
            " g ",
            "gbg",
            " g ", 'g', Blocks.gravel, 'b', new ItemStack(Items.dye, 1, 0))),

    SIGNAL_LEVER(new ShapedOreRecipe(SCItemBlocks.SIGNAL_LEVER.block,
            " r ",
            "gr ",
            "sib", 's', Blocks.stone, 'b', new ItemStack(Items.dye, 1, 0), 'r', SCItemBlocks.METAL_ROD.block, 'i', Blocks.iron_bars, 'g', SCItems.METAL_GEAR.item));

    public final ShapedOreRecipe recipe;

    RecipesGSARBlocks(ShapedOreRecipe recipe) {
        this.recipe = recipe;
    }

    public static List<ShapedOreRecipe> constructRecipesGSARBlocks() {
        List<ShapedOreRecipe> recipes = new ArrayList<>();
        for (RecipesGSARBlocks entry : values()) {
            recipes.add(entry.recipe);
        }
        return recipes;
    }
}
