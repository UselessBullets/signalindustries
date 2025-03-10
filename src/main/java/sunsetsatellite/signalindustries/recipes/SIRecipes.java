package sunsetsatellite.signalindustries.recipes;

import net.minecraft.client.gui.guidebook.mobs.MobInfoRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.data.DataLoader;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.item.ItemStack;
import sunsetsatellite.signalindustries.SIBlocks;
import sunsetsatellite.signalindustries.SIItems;
import sunsetsatellite.signalindustries.SignalIndustries;
import sunsetsatellite.signalindustries.api.impl.catalyst.SignalIndustriesFluidPlugin;
import sunsetsatellite.signalindustries.entities.mob.EntityInfernal;
import sunsetsatellite.signalindustries.recipes.container.*;
import sunsetsatellite.signalindustries.recipes.entry.RecipeEntryMachine;
import sunsetsatellite.signalindustries.recipes.entry.RecipeEntryMachineFluid;
import turniplabs.halplibe.util.RecipeEntrypoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SIRecipes implements RecipeEntrypoint {
    public static RecipeNamespaceSI SIGNAL_INDUSTRIES = new RecipeNamespaceSI();
    public static RecipeGroup<RecipeEntryCrafting<?,?>> WORKBENCH;
    public static RecipeGroupSI<RecipeEntryMachineFluid> EXTRACTOR;
    public static RecipeGroupSI<RecipeEntryMachine> CRUSHER;
    public static RecipeGroupSI<RecipeEntryMachine> ALLOY_SMELTER;
    public static RecipeGroupSI<RecipeEntryMachine> PLATE_FORMER;
    public static RecipeGroupSI<RecipeEntryMachineFluid> PUMP;
    public static RecipeGroupSI<RecipeEntryMachine> CRYSTAL_CUTTER;
    public static RecipeGroupSI<RecipeEntryMachine> CRYSTAL_CHAMBER;
    public static RecipeGroupSI<RecipeEntryMachine> INFUSER;
    public static RecipeGroupSI<RecipeEntryMachine> CENTRIFUGE;

    @Override
    public void onRecipesReady() {
        new SignalIndustriesFluidPlugin().initializePlugin(SignalIndustries.LOGGER);
        resetGroups();
        load();
        MobInfoRegistry.register(EntityInfernal.class,"guidebook.section.mob.infernal.name", "guidebook.section.mob.infernal.desc",40,1000,new MobInfoRegistry.MobDrop[]{new MobInfoRegistry.MobDrop(new ItemStack(SIItems.infernalFragment),1,0,2)});

    }

    public void resetGroups(){
        SIGNAL_INDUSTRIES = new RecipeNamespaceSI();
        WORKBENCH = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.workbench)));
        EXTRACTOR = new RecipeGroupSI<>(new RecipeSymbol(Arrays.asList(new ItemStack(SIBlocks.prototypeExtractor), new ItemStack(SIBlocks.basicExtractor), new ItemStack(SIBlocks.reinforcedExtractor))));
        CRUSHER = new RecipeGroupSI<>(new RecipeSymbol(Arrays.asList(new ItemStack(SIBlocks.prototypeCrusher),new ItemStack(SIBlocks.basicCrusher),new ItemStack(SIBlocks.reinforcedCrusher))));
        ALLOY_SMELTER = new RecipeGroupSI<>(new RecipeSymbol(Arrays.asList(new ItemStack(SIBlocks.prototypeAlloySmelter),new ItemStack(SIBlocks.basicAlloySmelter))));
        PLATE_FORMER = new RecipeGroupSI<>(new RecipeSymbol(Arrays.asList(new ItemStack(SIBlocks.prototypePlateFormer),new ItemStack(SIBlocks.basicPlateFormer),new ItemStack(SIBlocks.reinforcedPlateFormer))));
        PUMP = new RecipeGroupSI<>(new RecipeSymbol(Arrays.asList(new ItemStack(SIBlocks.prototypePump),new ItemStack(SIBlocks.basicPump))));
        CRYSTAL_CUTTER = new RecipeGroupSI<>(new RecipeSymbol(Arrays.asList(new ItemStack(SIBlocks.prototypeCrystalCutter),new ItemStack(SIBlocks.basicCrystalCutter),new ItemStack(SIBlocks.reinforcedCrystalCutter))));
        CRYSTAL_CHAMBER = new RecipeGroupSI<>(new RecipeSymbol(Arrays.asList(new ItemStack(SIBlocks.basicCrystalChamber),new ItemStack(SIBlocks.reinforcedCrystalChamber))));
        INFUSER = new RecipeGroupSI<>(new RecipeSymbol(Arrays.asList(new ItemStack(SIBlocks.basicInfuser))));
        CENTRIFUGE = new RecipeGroupSI<>(new RecipeSymbol(Arrays.asList(new ItemStack(SIBlocks.reinforcedCentrifuge))));
    }

    public void load(){
        Registries.RECIPE_TYPES.register("signalindustries:machine", RecipeEntryMachine.class);
        Registries.RECIPE_TYPES.register("signalindustries:machine/fluid", RecipeEntryMachineFluid.class);
        List<ItemStack> abilityGroup = new ArrayList<>();
        abilityGroup.add(SIItems.boostAbilityContainer.getDefaultStack());
        abilityGroup.add(SIItems.projectileAbilityContainer.getDefaultStack());
        Registries.ITEM_GROUPS.register("signalindustries:ability_containers",abilityGroup);
        List<ItemStack> romChipGroup = new ArrayList<>();
        romChipGroup.add(SIItems.romChipBoost.getDefaultStack());
        romChipGroup.add(SIItems.romChipProjectile.getDefaultStack());
        Registries.ITEM_GROUPS.register("signalindustries:rom_chips",romChipGroup);
        Registries.ITEM_GROUPS.register("minecraft:water", SignalIndustries.listOf(new ItemStack(Block.fluidWaterFlowing),new ItemStack(Block.fluidWaterStill)));
        Registries.ITEM_GROUPS.register("minecraft:lava", SignalIndustries.listOf(new ItemStack(Block.fluidLavaFlowing),new ItemStack(Block.fluidLavaStill)));
        new ExtractorRecipes().addRecipes(EXTRACTOR);
        new CrusherRecipes().addRecipes(CRUSHER);
        new AlloySmelterRecipes().addRecipes(ALLOY_SMELTER);
        new PlateFormerRecipes().addRecipes(PLATE_FORMER);
        new PumpRecipes().addRecipes(PUMP);
        new CrystalCutterRecipes().addRecipes(CRYSTAL_CUTTER);
        new CrystalChamberRecipes().addRecipes(CRYSTAL_CHAMBER);
        new InfuserRecipes().addRecipes(INFUSER);
        new CentrifugeRecipes().addRecipes(CENTRIFUGE);
        DataLoader.loadRecipesFromFile("/assets/signalindustries/recipes/workbench.json");
        SignalIndustries.LOGGER.info(SIGNAL_INDUSTRIES.getAllRecipes().size()+" recipes in "+SIGNAL_INDUSTRIES.size()+" groups.");
    }

    @Override
    public void initNamespaces() {
        resetGroups();
        registerNamespaces();
    }

    public void registerNamespaces(){
        SIGNAL_INDUSTRIES.register("workbench",WORKBENCH);
        SIGNAL_INDUSTRIES.register("extractor",EXTRACTOR);
        SIGNAL_INDUSTRIES.register("crusher",CRUSHER);
        SIGNAL_INDUSTRIES.register("alloy_smelter",ALLOY_SMELTER);
        SIGNAL_INDUSTRIES.register("plate_former",PLATE_FORMER);
        SIGNAL_INDUSTRIES.register("pump",PUMP);
        SIGNAL_INDUSTRIES.register("crystal_cutter",CRYSTAL_CUTTER);
        SIGNAL_INDUSTRIES.register("crystal_chamber",CRYSTAL_CHAMBER);
        SIGNAL_INDUSTRIES.register("infuser",INFUSER);
        SIGNAL_INDUSTRIES.register("centrifuge",CENTRIFUGE);
        Registries.RECIPES.register("signalindustries",SIGNAL_INDUSTRIES);
    }

}
