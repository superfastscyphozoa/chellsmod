package net.superfastscyphozoa.chellsmod.worldgen.features.configured;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

public class VegetationConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_TALL_GRASS = ChellsmodConfiguredFeatureUtils.createKey("patch_tall_grass");

    private static RandomPatchConfiguration vegetationPatch(BlockStateProvider blockStateProvider, int i) {
        return FeatureUtils.simpleRandomPatchConfiguration(i, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(blockStateProvider)));
    }

    static WeightedList<BlockState> tallGrassBuilder = WeightedList.<BlockState>builder()
            .add(Blocks.TALL_GRASS.defaultBlockState(), 2).add(Blocks.SHORT_GRASS.defaultBlockState(), 1)
            .build();

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> bootstrapContext) {

        ChellsmodConfiguredFeatureUtils.register(bootstrapContext, PATCH_TALL_GRASS, Feature.RANDOM_PATCH,
                vegetationPatch(new WeightedStateProvider(tallGrassBuilder), 64));

    }
}
