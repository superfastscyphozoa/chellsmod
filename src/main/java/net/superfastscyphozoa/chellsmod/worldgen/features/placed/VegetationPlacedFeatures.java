package net.superfastscyphozoa.chellsmod.worldgen.features.placed;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.superfastscyphozoa.chellsmod.worldgen.features.configured.VegetationConfiguredFeatures;

public class VegetationPlacedFeatures {
    public static final ResourceKey<PlacedFeature> PATCH_TALL_GRASS = ChellsmodPlacedFeatureUtils.createKey("patch_tall_grass");

    public static void bootstrap(BootstrapContext<PlacedFeature> bootstrapContext) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = bootstrapContext.lookup(Registries.CONFIGURED_FEATURE);

        Holder.Reference<ConfiguredFeature<?, ?>> patch_tall_grass = holderGetter.getOrThrow(VegetationConfiguredFeatures.PATCH_TALL_GRASS);

        ChellsmodPlacedFeatureUtils.register(bootstrapContext, PATCH_TALL_GRASS, patch_tall_grass,
                RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    }
}
