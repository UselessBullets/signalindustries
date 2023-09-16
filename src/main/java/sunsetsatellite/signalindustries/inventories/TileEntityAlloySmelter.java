package sunsetsatellite.signalindustries.inventories;


import net.minecraft.core.block.BlockFluid;
import net.minecraft.core.item.ItemStack;
import sunsetsatellite.signalindustries.SignalIndustries;
import sunsetsatellite.signalindustries.blocks.BlockContainerTiered;
import sunsetsatellite.signalindustries.interfaces.IBoostable;
import sunsetsatellite.signalindustries.recipes.AlloySmelterRecipes;
import sunsetsatellite.signalindustries.recipes.BasicAlloySmelterRecipes;
import sunsetsatellite.signalindustries.recipes.MachineRecipesBase;


public class TileEntityAlloySmelter extends TileEntityTieredMachine implements IBoostable {

    public MachineRecipesBase<Integer[], ItemStack> recipes = AlloySmelterRecipes.instance;

    public TileEntityAlloySmelter(){
        cost = 40;
        itemContents = new ItemStack[3];
        fluidCapacity[0] = 2000;
        acceptedFluids.get(0).add((BlockFluid) SignalIndustries.energyFlowing);
    }
    @Override
    public String getInvName() {
        return "Alloy Smelter";
    }

    @Override
    public void updateEntity() {
        worldObj.markBlocksDirty(xCoord,yCoord,zCoord,xCoord,yCoord,zCoord);
        extractFluids();
        BlockContainerTiered block = (BlockContainerTiered) getBlockType();
        if(block != null) {
            tier = block.tier;
            switch (block.tier) {
                case PROTOTYPE:
                    recipes = AlloySmelterRecipes.instance;
                    break;
                case BASIC:
                    recipes = BasicAlloySmelterRecipes.instance;
                    break;
                case REINFORCED:
                case AWAKENED:
                    break;
            }
            speedMultiplier = block.tier.ordinal() + 1;
            cost = 40 * (block.tier.ordinal()+1);
        }
        boolean update = false;
        if(fuelBurnTicks > 0){
            fuelBurnTicks--;
        }
        if(itemContents[0] == null){
            progressTicks = 0;
        } else if(canProcess()) {
            progressMaxTicks = 200 / speedMultiplier;
        }
        if(!worldObj.isClientSide){
            if (progressTicks == 0 && canProcess()){
                update = fuel();
            }
            if(isBurning() && canProcess()){
                progressTicks++;
                if(progressTicks >= progressMaxTicks){
                    progressTicks = 0;
                    processItem();
                    update = true;
                }
            } else if(canProcess()){
                fuel();
                if(fuelBurnTicks > 0){
                    fuelBurnTicks++;
                }
            }
        }

        if(update) {
            this.onInventoryChanged();
        }

    }

    public boolean fuel(){
        int burn = SignalIndustries.getEnergyBurnTime(fluidContents[0]);
        if(burn > 0 && canProcess() && fluidContents[0].amount >= cost){
            progressMaxTicks = 200 / speedMultiplier;//(itemContents[0].getItemData().getInteger("saturation") / speedMultiplier) == 0 ? 200 : (itemContents[0].getItemData().getInteger("saturation") / speedMultiplier);
            fuelMaxBurnTicks = fuelBurnTicks = burn;
            fluidContents[0].amount -= cost;
            if(fluidContents[0].amount == 0) {
                fluidContents[0] = null;
            }
            return true;
        }
        return false;
    }

    public void processItem(){
        if(canProcess()){
            ItemStack stack = recipes.getResult(new Integer[]{this.itemContents[2].itemID, this.itemContents[0].itemID});
            if(itemContents[1] == null){
                setInventorySlotContents(1, stack);
            } else if(itemContents[1].isItemEqual(stack)) {
                itemContents[1].stackSize++;
            }
            if(this.itemContents[0].getItem().hasContainerItem()) {
                this.itemContents[0] = new ItemStack(this.itemContents[0].getItem().getContainerItem());
            } else if(this.itemContents[2].getItem().hasContainerItem()){
                this.itemContents[2] = new ItemStack(this.itemContents[2].getItem().getContainerItem());
            } else {
                --this.itemContents[0].stackSize;
                --this.itemContents[2].stackSize;
            }
            if(this.itemContents[0].stackSize <= 0) {
                this.itemContents[0] = null;
            }
            if(this.itemContents[2].stackSize <= 0) {
                this.itemContents[2] = null;
            }
        }
    }

    private boolean canProcess() {
        if(itemContents[0] == null || itemContents[2] == null) {
            return false;
        } else {
            ItemStack stack = recipes.getResult(new Integer[]{this.itemContents[2].itemID, this.itemContents[0].itemID});
            return stack != null && (itemContents[1] == null || (itemContents[1].isItemEqual(stack) && (itemContents[1].stackSize < getInventoryStackLimit() && itemContents[1].stackSize < itemContents[1].getMaxStackSize() || itemContents[1].stackSize < stack.getMaxStackSize())));
        }
    }
}
