package sunsetsatellite.signalindustries.recipes.legacy;


import net.minecraft.core.block.BlockFluid;
import sunsetsatellite.catalyst.fluids.util.FluidStack;
import sunsetsatellite.signalindustries.SIBlocks;
import sunsetsatellite.signalindustries.SIItems;

@Deprecated
public class BasicExtractorRecipes extends ExtractorRecipes {
    public static final BasicExtractorRecipes instance = new BasicExtractorRecipes();

    protected BasicExtractorRecipes(){
        this.addRecipe(SIItems.rawSignalumCrystal.id,new FluidStack((BlockFluid) SIBlocks.energyFlowing,240));
    }
}
