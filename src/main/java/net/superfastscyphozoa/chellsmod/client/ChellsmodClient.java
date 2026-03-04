package net.superfastscyphozoa.chellsmod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.superfastscyphozoa.chellsmod.client.entity.RenderEntities;
import net.superfastscyphozoa.chellsmod.client.particle.ClientParticles;
import net.superfastscyphozoa.chellsmod.registry.RegisterBlocks;

@Environment(EnvType.CLIENT)
public class ChellsmodClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        RenderEntities.initEntityModels();
        ClientParticles.initParticles();

        BlockRenderLayerMap.putBlock(RegisterBlocks.SEEDING_DANDELION, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(RegisterBlocks.FIREWEED, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(RegisterBlocks.PENNY_BUN, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(RegisterBlocks.ALOE, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(RegisterBlocks.BLOOMING_ALOE, ChunkSectionLayer.CUTOUT);

    }
}
