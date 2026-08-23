package signalcraft.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import signalcraft.ItemBlocks.SCItemBlocks;
import signalcraft.items.SCItems;

import java.util.ArrayList;
import java.util.List;

public enum RecipesGSARMADS {
    STATIV_SEMI_HP(new ShapedOreRecipe(new ItemStack(SCItemBlocks.STATIV_SEMI_HP.block, 1, 0),
            " t ",
            "st ",
            "bt ", 's', Items.sign, 'b', Blocks.stone, 't', SCItems.POLE_SEMI.item)),

    STATIV_SEMI_VR(new ShapedOreRecipe(new ItemStack(SCItemBlocks.STATIV_SEMI_VR.block, 1, 0),
            " t ",
            " t ",
            "bt ", 'b', Blocks.stone, 't', SCItems.POLE_SEMI.item)),

    SEMI_SIGNAL_1W_HPx3(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SEMI_SIGNAL_1W_HPx3.block, 1, 0),
            "   ",
            "ta ",
            "s  ", 'a', SCItems.SEMAPHORE_ARM.item, 't', SCItems.POLE_SEMI.item, 's', SCItemBlocks.STATIV_SEMI_HP.block)),

    SEMI_SIGNAL_2W_HPx3(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SEMI_SIGNAL_2W_HPx3.block, 1, 0),
            "   ",
            "ta ",
            "sa ", 'a', SCItems.SEMAPHORE_ARM.item, 't', SCItems.POLE_SEMI.item, 's', SCItemBlocks.STATIV_SEMI_HP.block)),

    SEMI_SIGNAL_1W_HPx5(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SEMI_SIGNAL_1W_HPx5.block, 1, 0),
            "ta ",
            "t  ",
            "s  ", 'a', SCItems.SEMAPHORE_ARM.item, 't', SCItems.POLE_SEMI.item, 's', SCItemBlocks.STATIV_SEMI_HP.block)),

    SEMI_SIGNAL_2W_HPx5(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SEMI_SIGNAL_2W_HPx5.block, 1, 0),
            "ta ",
            "ta ",
            "s  ", 'a', SCItems.SEMAPHORE_ARM.item, 't', SCItems.POLE_SEMI.item, 's', SCItemBlocks.STATIV_SEMI_HP.block)),

    SEMI_SIGNAL_VRx3(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SEMI_SIGNAL_VRx3.block, 1, 0),
            "   ",
            " a ",
            " s ", 'a', SCItems.DISTANT_SIGN.item, 's', SCItemBlocks.STATIV_SEMI_VR.block)),

    STATIV_LIGHT_HP(new ShapedOreRecipe(new ItemStack(SCItemBlocks.STATIV_LIGHT_HP.block, 1, 0),
            " t ",
            " ts",
            " tb", 's', Items.sign, 'b', Blocks.stone, 't', SCItems.POLE_MAIN.item)),

    STATIV_LIGHT_VR(new ShapedOreRecipe(new ItemStack(SCItemBlocks.STATIV_LIGHT_VR.block, 1, 0),
            " t ",
            " t ",
            " tb", 'b', Blocks.stone, 't', SCItems.POLE_MAIN.item)),

    SIGNAL_HP3(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGNAL_HP3.block, 1, 0),
            "   ",
            "lll",
            " s ", 'l', Blocks.redstone_lamp, 's', SCItemBlocks.STATIV_LIGHT_HP.block)),

    SIGNAL_VR3(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGNAL_VR3.block, 1, 0),
            "   ",
            "ltl",
            " s ", 'l', Blocks.redstone_lamp, 't', SCItems.POLE_MAIN.item, 's', SCItemBlocks.STATIV_LIGHT_HP.block)),

    SIGNAL_HP5(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGNAL_HP5.block, 1, 0),
            "lll",
            " t ",
            " s ", 'l', Blocks.redstone_lamp, 't', SCItems.POLE_MAIN.item, 's', SCItemBlocks.STATIV_LIGHT_HP.block)),

    SIGNAL_VR5(new ShapedOreRecipe(new ItemStack(SCItemBlocks.SIGNAL_VR5.block, 1, 0),
            "ltl",
            " t ",
            " s ", 'l', Blocks.redstone_lamp, 't', SCItems.POLE_MAIN.item, 's', SCItemBlocks.STATIV_LIGHT_HP.block));

    public final ShapedOreRecipe recipe;

    RecipesGSARMADS(ShapedOreRecipe recipe) {
        this.recipe = recipe;
    }

    public static List<ShapedOreRecipe> constructRecipesGSARMADS() {
        List<ShapedOreRecipe> recipes = new ArrayList<>();
        for (RecipesGSARMADS entry : values()) {
            recipes.add(entry.recipe);
        }
        return recipes;
    }
}
