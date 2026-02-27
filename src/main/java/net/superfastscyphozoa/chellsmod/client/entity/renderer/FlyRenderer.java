package net.superfastscyphozoa.chellsmod.client.entity.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.superfastscyphozoa.chellsmod.Chellsmod;
import net.superfastscyphozoa.chellsmod.client.entity.RenderEntities;
import net.superfastscyphozoa.chellsmod.client.entity.model.FlyModel;
import net.superfastscyphozoa.chellsmod.client.entity.render_state.FlyRenderState;
import net.superfastscyphozoa.chellsmod.entity.FlyEntity;

@Environment(EnvType.CLIENT)
public class FlyRenderer extends MobRenderer<FlyEntity, FlyRenderState, FlyModel> {

    public FlyRenderer(EntityRendererProvider.Context context) {
        super(context, new FlyModel(context.bakeLayer(RenderEntities.FLY_LAYER)), 0.4f);
    }

    @Override
    public Identifier getTextureLocation(FlyRenderState flyRenderState) {
        return Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, "textures/entity/fly/fly.png");
    }

    @Override
    public FlyRenderState createRenderState() {
        return new FlyRenderState();
    }

    public void extractRenderState(FlyEntity fly, FlyRenderState flyRenderState, float f) {
        super.extractRenderState(fly, flyRenderState, f);

        flyRenderState.idleAnimationState.copyFrom(fly.idleAnimationState);
        flyRenderState.shootAnimationState.copyFrom(fly.shootAnimationState);
    }

}
