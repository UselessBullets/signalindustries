package sunsetsatellite.signalindustries.util;


import com.mojang.nbt.CompoundTag;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import sunsetsatellite.fluidapi.api.FluidStack;
import sunsetsatellite.fluidapi.api.IFluidInventory;
import sunsetsatellite.signalindustries.interfaces.mixins.INBTCompound;

public class NBTHelper {

    public static void saveInvToNBT(ItemStack source_item, IInventory inv){
        CompoundTag itemData = source_item.getData().getCompound("inventory");
        for(int i = 0; i < inv.getSizeInventory();i++){
            ItemStack item = inv.getStackInSlot(i);
            CompoundTag itemNBT = new CompoundTag();
            if(item != null){
                item.writeToNBT(itemNBT);
                itemData.putCompound(String.valueOf(i),itemNBT);
            } else {
                ((INBTCompound)itemData).removeTag(String.valueOf(i));
            }
        }
        source_item.getData().putCompound("inventory",itemData);
        if(inv instanceof IFluidInventory){
            IFluidInventory fluidInv = (IFluidInventory) inv;
            CompoundTag fluidData = source_item.getData().getCompound("fluidInventory");
            for(int i = 0; i < fluidInv.getFluidInventorySize();i++){
                FluidStack fluid = ((IFluidInventory) inv).getFluidInSlot(i);
                CompoundTag fluidNBT = new CompoundTag();
                if(fluid != null){
                    fluid.writeToNBT(fluidNBT);
                    fluidData.putCompound(String.valueOf(i),fluidNBT);
                } else {
                    ((INBTCompound)fluidData).removeTag(String.valueOf(i));
                }
            }
            source_item.getData().putCompound("fluidInventory",fluidData);
        }
    }

    /*public static CompoundTag savePureNBT(CompoundTag nbt, IInventory inv){
        for(int i = 0; i < inv.getSizeInventory();i++){
            ItemStack item = inv.getStackInSlot(i);
            CompoundTag itemNBT = new CompoundTag();
            if(item != null){
                itemNBT.putByte("Count", (byte)item.stackSize);
                itemNBT.putShort("id", (short)item.itemID);
                itemNBT.putShort("Damage", (short)item.getMetadata());
                itemNBT.putCompound("Data", (CompoundTag)item.tag);
                nbt.putCompound(String.valueOf(i),itemNBT);
            } else {
                ((INBTCompound)nbt).removeTag(String.valueOf(i));
            }
        }
        return nbt;
    }*/

    public static void loadInvFromNBT(ItemStack source_item, IInventory inv, int amount, int fluidAmount){
        CompoundTag itemNBT = source_item.getData().getCompound("inventory");
        CompoundTag fluidNBT = source_item.getData().getCompound("fluidInventory");
        for(int i = 0; i < amount;i++){
            if(itemNBT.containsKey(String.valueOf(i))){
                ItemStack item = ItemStack.readItemStackFromNbt(itemNBT.getCompound(String.valueOf(i)));
                inv.setInventorySlotContents(i,item);
            }
        }
        for (int i = 0; i < fluidAmount; i++) {
            if(inv instanceof IFluidInventory){
                IFluidInventory fluidInv = (IFluidInventory) inv;
                FluidStack fluid = new FluidStack(fluidNBT.getCompound(String.valueOf(i)));
                fluidInv.setFluidInSlot(i,fluid);
            }
        }
    }

    /*public static void loadPureNBT(CompoundTag nbt, IInventory inv, int amount){
        for(int i = 0; i < amount;i++){
            if(nbt.hasKey(String.valueOf(i))){
                ItemStack item = new ItemStack(nbt.getCompound(String.valueOf(i)));
                inv.setInventorySlotContents(i,item);
            }
        }
    }*/

    /*public static NBTBase loadNBTFile(String name) {
        try {
            RandomAccessFile file = new RandomAccessFile(name, "r");
            NBTBase nbt = NBTBase.readTag(file);
            return nbt;
        } catch(IOException e){
            e.printStackTrace();
        }
        return new CompoundTag();
    }

    public static void saveNBTFile(String name, NBTBase nbt) {
        try {
            RandomAccessFile file = new RandomAccessFile(name, "rw");
            NBTBase.writeTag(nbt, file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }*/
}
