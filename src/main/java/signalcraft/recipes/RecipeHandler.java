package signalcraft.recipes;

import net.minecraft.item.crafting.CraftingManager;

import java.util.List;

public class RecipeHandler {
    private static final List CManager = CraftingManager.getInstance().getRecipeList();
    public static void registerRecipes() {
        //GSAR Recipes
        CManager.addAll(RecipesGSARBlocks.constructRecipesGSARBlocks());
        CManager.addAll(RecipesItems.constructRecipesGSARItems());
        CManager.addAll(RecipesGSARRCS.constructRecipesGSARRCS());
        CManager.addAll(RecipesGSARSS.constructRecipesGSARSS());
        CManager.addAll(RecipesGSARSH.constructRecipesGSARSH());
        CManager.addAll(RecipesGSARMADS.constructRecipesGSARMADS());
        CManager.addAll(RecipesGSARBS.constructRecipesGSARBS());
        CManager.addAll(RecipesGSARPS.constructRecipesGSARPS());
        CManager.addAll(RecipesGSARO.constructRecipesGSARO());
        CManager.addAll(RecipesControllers.constructRecipesControllers());
        //SignalCraft Recipes
        //CZ
        CManager.addAll(RecipesSSSR.constructRecipesSSSR());
        CManager.addAll(RecipesAZD70.constructRecipesAZD70());
        CManager.addAll(RecipesOtherStuffCZ.constructRecipesOtherStuffCZ());
        CManager.addAll(RecipesCrossingsCZ.constructRecipesCrossingsCZ());
    }
}
