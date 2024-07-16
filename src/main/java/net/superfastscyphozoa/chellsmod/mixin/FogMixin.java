package net.superfastscyphozoa.chellsmod.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.mob.SkeletonEntity;
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
    private int fogLevel = 0;
    @Unique
    private float fogStartDepth;
    @Unique
    private float prevFogStartDepth;
    @Unique
    private float fogEndDepth;
    @Unique
    private float prevFogEndDepth;
    @Unique
    private float fs;
    @Unique
    private float fe;

    @Shadow private float viewDistance;

    @Unique
    private void setFogLevel(){
        if(ChellsModClient.fog.wasPressed()){
            if(fogLevel < 2){
                fogLevel++;
            }
            else {
                fogLevel = 0;
            }
        }
    }

    @Inject(method = "updateFog", at = @At(value = "INVOKE",
            target = "Lcom/mojang/blaze3d/platform/GlStateManager;clearColor(FFFF)V", shift = At.Shift.BEFORE))
    private void updateDeepFog(float tickDelta, CallbackInfo ci){
        float fx = this.viewDistance;

        setFogLevel();

        if
        (fogLevel == 0){
            fogStartDepth = (Math.min(fx, 192.0F) * 0.4F);
            fogEndDepth = (Math.min(fx, 192.0F));
        } else if
        (fogLevel == 1){
            fogStartDepth = (Math.min(fx, 192.0F) * 0.2F);
            fogEndDepth = (Math.min(fx, 192.0F) * 0.75F);
        } else if
        (fogLevel == 2){
            fogStartDepth = (Math.min(fx, 192.0F) * 0.1F);
            fogEndDepth = (Math.min(fx, 192.0F) * 0.5F);
        }

        fs = fogStartDepth;
        fe = fogEndDepth;

    }

    @Inject(method = "renderFog", at = @At(value = "INVOKE",
            target = "Lcom/mojang/blaze3d/platform/GlStateManager;fogEnd(F)V", ordinal = 3, shift = At.Shift.BY, by = 2))
    private void renderDeepFog(int i, float tickDelta, CallbackInfo ci){
        float fx = this.viewDistance;

        if (i == -1) {
            GlStateManager.fogStart(0.0F);
            GlStateManager.fogEnd(fx);
        } else {
            GlStateManager.fogStart(fs);
            GlStateManager.fogEnd(fe);
        }
    }
}
