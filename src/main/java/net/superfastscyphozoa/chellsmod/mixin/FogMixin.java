package net.superfastscyphozoa.chellsmod.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.GameRenderer;
import net.superfastscyphozoa.chellsmod.client.ChellsModClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class FogMixin{

    @Unique
    public int fogLevel = 0;

    @Shadow private float viewDistance;

    @Inject(method = "renderFog", at = @At(value = "INVOKE",
            target = "Lcom/mojang/blaze3d/platform/GlStateManager;fogEnd(F)V", ordinal = 3, shift = At.Shift.BY, by = 2))
    public void thickenFog(int i, float tickDelta, CallbackInfo ci){
        float fx = this.viewDistance;

        if(ChellsModClient.fog.wasPressed()){
            if(fogLevel < 2){
                fogLevel++;
            }
            else {
                fogLevel = 0;
            }
        }

        if (i == -1) {
            GlStateManager.fogStart(0.0F);
            GlStateManager.fogEnd(fx);
        } else {
            if
            (fogLevel == 0){
                GlStateManager.fogStart(fx * 0.4F);
                GlStateManager.fogEnd(fx);
            } else if
            (fogLevel == 1){
                GlStateManager.fogStart(fx * 0.2F);
                GlStateManager.fogEnd(fx);
            } else if
            (fogLevel == 2){
                GlStateManager.fogStart(fx * 0.025F);
                GlStateManager.fogEnd(fx * 0.2F);
            }
        }
    }
}
