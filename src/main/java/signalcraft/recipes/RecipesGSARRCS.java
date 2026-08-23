package signalcraft.recipes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import signalcraft.ItemBlocks.SCItemBlocks;
import signalcraft.items.SCItems;

import java.util.ArrayList;
import java.util.List;

public enum RecipesGSARRCS {
    BARRIER_FULL4R(new ShapedOreRecipe(new ItemStack(SCItemBlocks.BARRIER_FULL4R.block, 1, 0),
            " b ",
            " bg",
            " is", 'b', SCItems.BARRIER_SHORT.item, 'g', SCItems.METAL_GEAR.item, 'i', Blocks.iron_bars, 's', Blocks.stone)),

    BARRIER_FULL4L(new ShapedOreRecipe(new ItemStack(SCItemBlocks.BARRIER_FULL4L.block, 1, 0),
            " b ",
            "gb ",
            "si ", 'b', SCItems.BARRIER_SHORT.item, 'g', SCItems.METAL_GEAR.item, 'i', Blocks.iron_bars, 's', Blocks.stone)),

    BARRIER_FULL10R(new ShapedOreRecipe(new ItemStack(SCItemBlocks.BARRIER_FULL10R.block, 1, 0),
            " b ",
            " bg",
            " is", 'b', SCItems.BARRIER_LONG.item, 'g', SCItems.METAL_GEAR.item, 'i', Blocks.iron_bars, 's', Blocks.stone)),

    BARRIER_FULL10L(new ShapedOreRecipe(new ItemStack(SCItemBlocks.BARRIER_FULL10L.block, 1, 0),
            " b ",
            "gb ",
            "si ", 'b', SCItems.BARRIER_LONG.item, 'g', SCItems.METAL_GEAR.item, 'i', Blocks.iron_bars, 's', Blocks.stone)),

    BARRIER_HALF_R(new ShapedOreRecipe(new ItemStack(SCItemBlocks.BARRIER_HALF_R.block, 1, 0),
            " b ",
            " bg",
            "  i", 'b', SCItems.BARRIER_SHORT.item, 'g', "ingotIron", 'i', Blocks.iron_block)),

    BARRIER_HALF_L(new ShapedOreRecipe(new ItemStack(SCItemBlocks.BARRIER_HALF_L.block, 1, 0),
            " b ",
            "gb ",
            "i  ", 'b', SCItems.BARRIER_SHORT.item, 'g', "ingotIron", 'i', Blocks.iron_block)),

    BARRIER_MODERN_R(new ShapedOreRecipe(new ItemStack(SCItemBlocks.BARRIER_MODERN_R.block, 1, 0),
            " b ",
            " bg",
            "  i", 'b', SCItems.BARRIER_LONG.item, 'g', "ingotIron", 'i', Blocks.iron_block)),

    BARRIER_MODERN_L(new ShapedOreRecipe(new ItemStack(SCItemBlocks.BARRIER_MODERN_L.block, 1, 0),
            " b ",
            "gb ",
            "i  ", 'b', SCItems.BARRIER_LONG.item, 'g', "ingotIron", 'i', Blocks.iron_block)),

    BARRIER_STOP(new ShapedOreRecipe(new ItemStack(SCItemBlocks.BARRIER_STOP.block, 4, 0),
            "i i",
            " i ",
            " i ", 'i', "ingotIron")),

    SIGN_CROSS(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_CROSS.block, 2, 0),
            "rmr",
            " w ",
            "rmr", 'm', SCItemBlocks.METAL_ROD.block, 'r', new ItemStack(Items.dye, 1, 1), 'w', new ItemStack(Items.dye, 1, 15))),

    STATIV_CROSS(new ShapedOreRecipe(new ItemStack(SCItemBlocks.STATIV_CROSS.block, 3, 0),
            "wmw",
            "rmr",
            "wmw", 'm', SCItemBlocks.METAL_ROD.block, 'w', new ItemStack(Items.dye, 1, 1), 'r', new ItemStack(Items.dye, 1, 15))),

    SIGN_BU2(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_BU2.block, 1, 0),
            "   ",
            "bmw",
            "   ", 'm', SCItemBlocks.METAL_ROD.block, 'b', new ItemStack(Items.dye, 1, 0), 'w', new ItemStack(Items.dye, 1, 15))),

    SIGN_BU3(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_BU3.block, 1, 0),
            "   ",
            "wmb",
            "   ", 'm', SCItemBlocks.METAL_ROD.block, 'b', new ItemStack(Items.dye, 1, 0), 'w', new ItemStack(Items.dye, 1, 15))),

    SIGN_BU4(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_BU4.block, 1, 0),
            "   ",
            " i ",
            " m ", 'm', SCItemBlocks.METAL_ROD.block, 'i', "ingotIron")),

    SIGN_BU4Z(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_BU4Z.block, 1, 0),
            "   ",
            " i ",
            " m ", 'm', SCItemBlocks.SIGN_BU4.block, 'i', "ingotIron")),

    SIGN_BU5(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_BU5.block, 1, 0),
            "   ",
            " m ",
            "   ", 'm', SCItemBlocks.SIGN_BU4.block)),

    SIGN_BU5Z(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_BU5Z.block, 1, 0),
            "   ",
            " i ",
            " m ", 'm', SCItemBlocks.SIGN_BU5.block, 'i', "ingotIron")),

    SIGN_PF2(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_PF2.block, 1, 0),
            "   ",
            " m ",
            " m ", 'm', SCItemBlocks.SIGN_BU4.block)),

    SIGN_PF2Z(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGN_PF2Z.block, 1, 0),
            "   ",
            " n ",
            " m ", 'm', SCItemBlocks.SIGN_BU4.block, 'n', SCItemBlocks.SIGN_BU4Z.block)),

    CROSS_LIGHT_S(new ShapedOreRecipe(new ItemStack(SCItemBlocks.CROSS_LIGHT_S.block, 1, 0),
            " l ",
            " c ",
            " n ", 'n', Blocks.noteblock, 'c', SCItemBlocks.SIGN_CROSS.block, 'l', Blocks.redstone_lamp)),

    CROSS_MODERN(new ShapedOreRecipe(new ItemStack(SCItemBlocks.CROSS_MODERN.block, 1, 0),
            " i ",
            "ili",
            " b ", 'b', Blocks.iron_block, 'i', "ingotIron", 'l', Blocks.redstone_lamp)),

    SIGNAL_BU0x3(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGNAL_BU0x3.block, 1, 0),
            "ili",
            "ypy",
            " p ", 'i', "ingotIron", 'y', new ItemStack(Items.dye, 1, 11), 'l', Blocks.redstone_lamp, 'p', SCItems.POLE_MAIN.item)),

    SIGNAL_BU0x5(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGNAL_BU0x5.block, 1, 0),
            "ili",
            "ypy",
            " b ", 'b', Blocks.iron_block, 'i', "ingotIron", 'y', new ItemStack(Items.dye, 1, 11), 'l', Blocks.redstone_lamp, 'p', SCItems.POLE_MAIN.item));

    public final ShapedOreRecipe recipe;

    RecipesGSARRCS(ShapedOreRecipe recipe) {
        this.recipe = recipe;
    }

    public static List<ShapedOreRecipe> constructRecipesGSARRCS() {
        GameRegistry.addShapelessRecipe(new ItemStack(SCItemBlocks.SIGN_CROSS_FLASH.block, 1, 0),
                SCItemBlocks.SIGN_CROSS.block);
        GameRegistry.addShapelessRecipe(new ItemStack(SCItemBlocks.SIGN_CROSS_FENCE.block, 1, 0),
                SCItemBlocks.SIGN_CROSS.block, Blocks.fence);
        GameRegistry.addShapelessRecipe(new ItemStack(SCItemBlocks.CROSS_LIGHT.block, 1, 0),
                SCItemBlocks.SIGN_CROSS.block, Blocks.redstone_lamp);

        List<ShapedOreRecipe> recipes = new ArrayList<>();
        for (RecipesGSARRCS entry : values()) {
            recipes.add(entry.recipe);
        }
        return recipes;
    }
}
