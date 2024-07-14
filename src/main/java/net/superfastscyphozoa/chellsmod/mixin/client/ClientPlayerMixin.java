package net.superfastscyphozoa.chellsmod.mixin.client;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.superfastscyphozoa.chellsmod.client.ChellsModKeybinds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerMixin{

    @Inject(method = "tickMovement()V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/HungerManager;getFoodLevel()I",
                    ordinal = 0, shift = At.Shift.AFTER))
    public void keybindSprint(CallbackInfo ci){
        ClientPlayerEntity player = (ClientPlayerEntity) (Object) this;

        boolean notHungry = (float)player.getHungerManager().getFoodLevel() > 6.0F || player.abilities.allowFlying;

        if (ChellsModKeybinds.sprint.wasPressed()){
            if (!player.isSprinting()){
                if(notHungry && !player.isUsingItem() && !player.hasStatusEffect(StatusEffect.BLINDNESS)){
                    player.setSprinting(true);
                }
            } else {
                player.setSprinting(false);
            }
        }
    }

    @Redirect(method = "tickMovement()V", at = @At(value = "FIELD",
            target = "Lnet/minecraft/entity/player/ClientPlayerEntity;onGround:Z", opcode = 0, ordinal = 0))
    private boolean sprintWhileAirborne(ClientPlayerEntity instance){
        return true;
    }
}
