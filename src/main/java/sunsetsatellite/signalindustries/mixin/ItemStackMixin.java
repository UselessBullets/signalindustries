package sunsetsatellite.signalindustries.mixin;


import com.mojang.nbt.CompoundTag;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import sunsetsatellite.signalindustries.interfaces.IVariableDamageWeapon;

import java.util.Iterator;
import java.util.Map;

@Mixin(
        value = ItemStack.class,
        remap = false
)
public class ItemStackMixin {

    @Shadow public int itemID;


    @Inject(
            method = "getDamageVsEntity",
            at = @At("HEAD"),
            cancellable = true
    )
    public void getDamageVsEntity(Entity entity, CallbackInfoReturnable<Integer> cir) {
        if(Item.itemsList[this.itemID] instanceof IVariableDamageWeapon){
            cir.setReturnValue((((IVariableDamageWeapon) Item.itemsList[this.itemID]).getDamageVsEntity(entity,(ItemStack) ((Object)this))));
        }
    }

}
