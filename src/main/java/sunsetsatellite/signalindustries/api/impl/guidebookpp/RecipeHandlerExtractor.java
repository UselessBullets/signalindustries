package sunsetsatellite.signalindustries.api.impl.guidebookpp;

import net.minecraft.src.ContainerGuidebookRecipeBase;
import net.minecraft.src.ItemStack;
import net.minecraft.src.StringTranslate;
import sunsetsatellite.fluidapi.api.FluidStack;
import sunsetsatellite.guidebookpp.IRecipeHandlerBase;
import sunsetsatellite.signalindustries.SignalIndustries;
import sunsetsatellite.signalindustries.recipes.ExtractorRecipes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class RecipeHandlerExtractor
    implements IRecipeHandlerBase {
    public ContainerGuidebookRecipeBase getContainer(Object o) {
        RecipeFluid recipe = (RecipeFluid) o;
        return new ContainerGuidebookExtractorRecipe(new ItemStack(SignalIndustries.prototypeExtractor),recipe.itemInputs,recipe.fluidInputs,recipe.itemOutputs,recipe.fluidOutputs);
    }


    public int getRecipeAmount() {
        return getRecipes().size();
    }

    public ArrayList<?> getRecipes() {
        HashMap<Integer, FluidStack> rawRecipes = new HashMap<>(ExtractorRecipes.getInstance().getRecipeList());
        ArrayList<RecipeFluid> recipes = new ArrayList<>();
        rawRecipes.forEach((I,O)->{
            ArrayList<ItemStack> singletonList = new ArrayList<>(Collections.singleton(new ItemStack(I, 1, 0)));
            ArrayList<FluidStack> singletonList2 = new ArrayList<>(Collections.singleton(O));
            recipes.add(new RecipeFluid(singletonList,null,null, singletonList2));
        });
        return recipes;
    }

    public ArrayList<?> getRecipesFiltered(ItemStack filter, boolean usage) {
        HashMap<Integer,FluidStack> rawRecipes = new HashMap<>(ExtractorRecipes.getInstance().getRecipeList());
        ArrayList<RecipeFluid> recipes = new ArrayList<>();
        rawRecipes.forEach((I,O)->{

            if(usage){
                if(new ItemStack(I,1,0).isItemEqual(filter)){
                    ArrayList<ItemStack> singletonList = new ArrayList<>(Collections.singleton(new ItemStack(I, 1, 0)));
                    ArrayList<FluidStack> singletonList2 = new ArrayList<>(Collections.singleton(O));
                    recipes.add(new RecipeFluid(singletonList,null,null, singletonList2));
                }
            }
        });
        return recipes;
    }

    @Override
    public ArrayList<?> getRecipesFiltered(String name) {
        if(name.equals("")){
            return getRecipes();
        }
        HashMap<Integer,FluidStack> rawRecipes = new HashMap<>(ExtractorRecipes.getInstance().getRecipeList());
        ArrayList<RecipeFluid> recipes = new ArrayList<>();
        rawRecipes.forEach((I,O)->{
            ArrayList<ItemStack> singletonList = new ArrayList<>(Collections.singleton(new ItemStack(I, 1, 0)));
            ArrayList<FluidStack> singletonList2 = new ArrayList<>(Collections.singleton(O));
            recipes.add(new RecipeFluid(singletonList,null,null, singletonList2));
        });
        recipes.removeIf((R)->!getNameOfRecipeOutput(R).contains(name.toLowerCase()));
        return recipes;
    }

    @Override
    public String getNameOfRecipeOutput(Object recipe){
        StringTranslate trans = StringTranslate.getInstance();
        return trans.translateKey(((RecipeFluid)recipe).fluidOutputs.get(0).getFluidName()+".name").toLowerCase();
    }

    @Override
    public String getHandlerName() {
        return "signalum extractor";
    }
}
