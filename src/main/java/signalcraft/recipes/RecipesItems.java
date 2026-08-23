package signalcraft.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import signalcraft.ItemBlocks.SCItemBlocks;
import signalcraft.blocks.SCBlocks;
import signalcraft.items.SCItems;

import java.util.ArrayList;
import java.util.List;

public enum RecipesItems {
    SSSR_POLE(new ShapedOreRecipe(new ItemStack(SCItems.SSSR_POLE.item, 1, 0),
            " r ",
            " r ",
            "bcy", 'c', SCItems.CIRCUIT_SSSR.item, 'r', SCItemBlocks.METAL_ROD.block, 'b', new ItemStack(Items.dye, 1, 0), 'y', new ItemStack(Items.dye, 1, 11))),

    SSSR_2LIGHTS(new ShapedOreRecipe(new ItemStack(SCItems.SSSR_2LIGHTS.item, 4, 0),
            "blb",
            "bbb",
            "blb", 'l', Blocks.redstone_lamp, 'b', new ItemStack(Items.dye, 1, 0))),

    SSSR_3LIGHTS(new ShapedOreRecipe(new ItemStack(SCItems.SSSR_3LIGHTS.item, 4, 0),
            "blb",
            "blb",
            "blb", 'l', Blocks.redstone_lamp, 'b', new ItemStack(Items.dye, 1, 0))),

    AZD_POLE(new ShapedOreRecipe(new ItemStack(SCItems.AZD70_POLE.item, 1, 0),
            " r ",
            " r ",
            "ycy", 'c', SCItems.CIRCUIT_AZD.item, 'r', SCItemBlocks.METAL_ROD.block, 'y', new ItemStack(Items.dye, 1, 11))),

    AZD_1LIGHT(new ShapedOreRecipe(new ItemStack(SCItems.AZD70_1LIGHT.item, 4, 0),
            "bbb",
            "blb",
            "bbb", 'l', Blocks.redstone_lamp, 'b', new ItemStack(Items.dye, 1, 0))),

    AZD_2LIGHTS(new ShapedOreRecipe(new ItemStack(SCItems.AZD70_2LIGHTS.item, 1, 0),
            " l ",
            " i ",
            " l ", 'l', SCItems.AZD70_1LIGHT.item, 'i', Items.iron_ingot)),

    AZD_3LIGHTS(new ShapedOreRecipe(new ItemStack(SCItems.AZD70_3LIGHTS.item, 1, 0),
            " k ",
            " i ",
            " l ", 'l', SCItems.AZD70_1LIGHT.item, 'k', SCItems.AZD70_2LIGHTS.item, 'i', Items.iron_ingot)),

    AZD_4LIGHTS(new ShapedOreRecipe(new ItemStack(SCItems.AZD70_4LIGHTS.item, 1, 0),
            " k ",
            " i ",
            " k ", 'k', SCItems.AZD70_2LIGHTS.item, 'i', Items.iron_ingot)),

    AZD_5LIGHTS(new ShapedOreRecipe(new ItemStack(SCItems.AZD70_5LIGHTS.item, 1, 0),
            " k ",
            " i ",
            " l ", 'l', SCItems.AZD70_3LIGHTS.item, 'k', SCItems.AZD70_2LIGHTS.item, 'i', Items.iron_ingot)),

    AZD_6LIGHTS(new ShapedOreRecipe(new ItemStack(SCItems.AZD70_6LIGHTS.item, 1, 0),
            " l ",
            " i ",
            " l ", 'l', SCItems.AZD70_3LIGHTS.item, 'i', Items.iron_ingot)),

    POLE_MAIN(new ShapedOreRecipe(new ItemStack(SCItems.POLE_MAIN.item, 16, 0),
            " i ",
            " i ",
            " i ", 'i', "ingotIron")),

    POLE_SEMI(new ShapedOreRecipe(new ItemStack(SCItems.POLE_SEMI.item, 16, 0),
            " i ",
            " g ",
            " i ", 'i', "ingotIron", 'g', new ItemStack(Items.dye, 1, 2))),

    VYPRAVKA(new ShapedOreRecipe(new ItemStack(SCItems.VYPRAVKA.item, 1, 0),
            " lw",
            " wl",
            "s  ", 'w', new ItemStack(Items.dye, 1, 15), 'l', new ItemStack(Items.dye, 1, 10), 's', Items.stick)),

    WRENCH(new ShapedOreRecipe(new ItemStack(SCItems.WRENCH.item, 1, 0),
            "s s",
            " t ",
            " s ", 's', "ingotSteel", 't', "ingotTin")),

    RENAMER(new ShapedOreRecipe(new ItemStack(SCItems.SIGNAL_RENAMER.item, 1, 0),
            "  p",
            "lpl",
            "p  ", 'p', Items.paper, 'l', new ItemStack(Items.dye, 1, 10))),

    BONDER(new ShapedOreRecipe(new ItemStack(SCItems.SIGNAL_BONDER.item, 1, 0),
            " i ",
            " i ",
            "ric", 'i', "ingotIron", 'r', SCItems.CIRCUIT_RECEIVER.item, 'c', SCItems.CIRCUIT_CONTROLLER.item)),

    BARRIER_SHORT(new ShapedOreRecipe(new ItemStack(SCItems.BARRIER_SHORT.item, 6, 0),
            "   ",
            "rwr",
            "ppp", 'p', "plankTreatedWood", 'r', new ItemStack(Items.dye, 1, 1), 'w', new ItemStack(Items.dye, 1, 15))),

    BARRIER_LONG(new ShapedOreRecipe(new ItemStack(SCItems.BARRIER_LONG.item, 2, 0),
            "b  ",
            " b ",
            "  b", 'b', SCItems.BARRIER_SHORT.item)),

    METAL_GEAR(new ShapedOreRecipe(new ItemStack(SCItems.METAL_GEAR.item, 2, 0),
            " i ",
            "isi",
            " i ", 's', "ingotSteel", 'i', "ingotIron")),

    SEMAPHORE_ARM(new ShapedOreRecipe(new ItemStack(SCItems.SEMAPHORE_ARM.item, 4, 0),
            "   ",
            "ppo",
            "rwr", 'o', "plankTreatedWood", 'p', "plateTreatedWood", 'r', new ItemStack(Items.dye, 1, 1), 'w', new ItemStack(Items.dye, 1, 15))),

    DISTANT_SIGN(new ShapedOreRecipe(new ItemStack(SCItems.DISTANT_SIGN.item, 4, 0),
            " y ",
            "ygy",
            " y ", 'g', SCItems.METAL_GEAR.item, 'y', new ItemStack(Items.dye, 1, 11))),

    TREATED_WOOD_PLANK(new ShapedOreRecipe(new ItemStack(SCBlocks.TREATED_PLANKS.block, 4, 0),
            "cwc",
            "wcw",
            "cwc", 'w', "plankWood", 'c', Items.coal)),

    TREATED_WOOD_PLATE(new ShapedOreRecipe(new ItemStack(SCItems.TREATED_WOOD_PLATE.item, 16, 0),
            "ww ",
            "ww ",
            "   ", 'w', "plankTreatedWood")),

    CIRCUIT_EMPTY(new ShapedOreRecipe(new ItemStack(SCItems.CIRCUIT_EMPTY.item, 8, 0),
            "www",
            "wdw",
            "www", 'w', SCItems.TREATED_WOOD_PLATE.item, 'd', new ItemStack(Items.dye, 1, 2))),

    CIRCUIT_AZD(new ShapedOreRecipe(new ItemStack(SCItems.CIRCUIT_AZD.item, 1, 0),
            " s ",
            "cec",
            " s ", 'c', "nuggetCopper", 's', "nuggetTin", 'e',SCItems.CIRCUIT_EMPTY.item)),

    CIRCUIT_AZD97(new ShapedOreRecipe(new ItemStack(SCItems.CIRCUIT_AZD97.item, 1, 0),
            " g ",
            "gcg",
            " g ", 'g', "nuggetGold", 'c', SCItems.CIRCUIT_AZD.item)),

    CIRCUIT_SSSR(new ShapedOreRecipe(new ItemStack(SCItems.CIRCUIT_SSSR.item, 1, 0),
            " c ",
            "ses",
            " c ", 'c', "nuggetCopper", 's', "nuggetTin", 'e', SCItems.CIRCUIT_EMPTY.item)),

    CIRCUIT_RECEIVER(new ShapedOreRecipe(new ItemStack(SCItems.CIRCUIT_RECEIVER.item, 1, 0),
            "rgr",
                    "gcg",
                    "rgr", 'g', "nuggetGold", 'c', SCItems.CIRCUIT_EMPTY.item, 'r', new ItemStack(Items.dye, 1, 1))),

    CIRCUIT_CONTROLLER(new ShapedOreRecipe(new ItemStack(SCItems.CIRCUIT_CONTROLLER.item, 1, 0),
            "bgb",
            "gcg",
            "bgb", 'g', "nuggetGold", 'c', SCItems.CIRCUIT_EMPTY.item, 'b', new ItemStack(Items.dye, 1, 4))),
    ;
    public final ShapedOreRecipe recipe;

    RecipesItems(ShapedOreRecipe recipe) {
        this.recipe = recipe;
    }

    public static List<ShapedOreRecipe> constructRecipesGSARItems() {
        OreDictionary.registerOre("plankTreatedWood", new ItemStack(SCBlocks.TREATED_PLANKS.block));
        OreDictionary.registerOre("plateTreatedWood", new ItemStack(SCItems.TREATED_WOOD_PLATE.item));
        OreDictionary.registerOre("plankWood", new ItemStack(Blocks.planks, 1, OreDictionary.WILDCARD_VALUE));

        List<ShapedOreRecipe> recipes = new ArrayList<>();
        for (RecipesItems entry : values()) {
            recipes.add(entry.recipe);
        }
        return recipes;
    }
}
