package net.superfastscyphozoa.chellsmod.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.player.ClientPlayerEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerMixin{

    @WrapOperation(method = "tickMovement()V", at = @At(value = "FIELD",
            target = "Lnet/minecraft/entity/player/ClientPlayerEntity;onGround:Z", opcode = Opcodes.GETFIELD, ordinal = 0))
    private boolean sprintWhileAirborne(ClientPlayerEntity instance, Operation<Boolean> original){
        original.call(instance);
        return true;
    }
}
