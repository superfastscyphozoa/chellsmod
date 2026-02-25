package net.superfastscyphozoa.chellsmod.client.entity.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.superfastscyphozoa.chellsmod.Chellsmod;
import net.superfastscyphozoa.chellsmod.client.entity.RenderEntities;
import net.superfastscyphozoa.chellsmod.client.entity.model.FlyModel;
import net.superfastscyphozoa.chellsmod.entity.FlyEntity;

@Environment(EnvType.CLIENT)
public class FlyRenderer extends MobRenderer<FlyEntity, LivingEntityRenderState, FlyModel> {

    public FlyRenderer(EntityRendererProvider.Context context) {
        super(context, new FlyModel(context.bakeLayer(RenderEntities.FLY_LAYER)), 0.4f);
    }

    @Override
    public Identifier getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        return Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, "textures/entity/fly/fly.png");
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

}
