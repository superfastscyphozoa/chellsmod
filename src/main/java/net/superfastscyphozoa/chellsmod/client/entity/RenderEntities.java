package net.superfastscyphozoa.chellsmod.client.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.Identifier;
import net.superfastscyphozoa.chellsmod.Chellsmod;
import net.superfastscyphozoa.chellsmod.client.entity.model.FlyModel;
import net.superfastscyphozoa.chellsmod.client.entity.renderer.FlyRenderer;
import net.superfastscyphozoa.chellsmod.registry.RegisterEntities;

@Environment(EnvType.CLIENT)
public class RenderEntities {

    public static final ModelLayerLocation FLY_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, "fly"), "main");

    public static void initEntityModels() {

        EntityRenderers.register(RegisterEntities.FLY, FlyRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(FLY_LAYER, FlyModel::createBodyLayer);

        EntityRenderers.register(RegisterEntities.MAGGOT_PROJECTILE, ThrownItemRenderer::new);

    }
}
