package net.superfastscyphozoa.chellsmod.mixin.client;

import net.minecraft.entity.player.ClientPlayerEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerMixin{
    @Redirect(method = "tickMovement()V", at = @At(value = "FIELD",
            target = "Lnet/minecraft/entity/player/ClientPlayerEntity;onGround:Z", opcode = Opcodes.GETFIELD, ordinal = 0))
    private boolean sprintWhileAirborne(ClientPlayerEntity instance){
        return true;
    }
}
