package net.superfastscyphozoa.chellsmod.mixin.client;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.superfastscyphozoa.chellsmod.client.ChellsModKeybinds;
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

        boolean notHungry = (float)player.getHungerManager().getFoodLevel() > 6.0F || player.abilities.allowFlying;

        if (ChellsModKeybinds.sprint.pressed){
            if (!player.isSprinting() && notHungry && !player.isUsingItem()
                    && !player.hasStatusEffect(StatusEffect.BLINDNESS)) {
                player.setSprinting(true);
            }
        }
    }

}
