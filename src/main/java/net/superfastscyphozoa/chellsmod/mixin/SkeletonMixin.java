package net.superfastscyphozoa.chellsmod.mixin;

import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SkeletonEntity.class)
public class SkeletonMixin {
	@Inject(at = @At("TAIL"), method = "initEquipment")
	private void inject(CallbackInfo info) {
		SkeletonEntity skelly = (SkeletonEntity) (Object) this;

		if (skelly.getRandom().nextFloat() > 0.75f){
			if (skelly.getRandom().nextBoolean()){
				skelly.setArmorSlot(0, new ItemStack(Items.STONE_AXE));
			} else skelly.setArmorSlot(0, new ItemStack(Items.STONE_SWORD));
		}
	}
}
