package net.superfastscyphozoa.chellsmod.mixin;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerMixin{

    @Inject(method = "tickMovement()V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/HungerManager;getFoodLevel()I",
                    ordinal = 0, shift = At.Shift.AFTER))
    public void inject(CallbackInfo ci){
        ClientPlayerEntity player = (ClientPlayerEntity) (Object) this;


    }

}
