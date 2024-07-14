package net.superfastscyphozoa.chellsmod.mixin;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerMixin {

    @Inject(method = "travel", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/player/PlayerAbilities;getFlySpeed()F", ordinal = 0, shift = At.Shift.BY, by = 3))
    protected void creativeFlightControl(CallbackInfo ci){
        PlayerEntity player = (PlayerEntity) (Object) this;

        float flySpeed = player.abilities.getFlySpeed();

        if (player.isSprinting()){
            player.flyingSpeed = flySpeed * 2.25F;
        } else {
            player.flyingSpeed = flySpeed * 1.25F;
        }

        double yFlySpeed = player.velocityY;

        player.velocityY = yFlySpeed * 1.25;
    }
}
