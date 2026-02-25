package net.superfastscyphozoa.chellsmod.worldgen.features.configured;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.superfastscyphozoa.chellsmod.Chellsmod;

public class ChellsmodConfiguredFeatureUtils {
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> bootstrapContext) {
        VegetationConfiguredFeatures.bootstrap(bootstrapContext);
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String string) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, string));
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register
            (BootstrapContext<ConfiguredFeature<?, ?>> bootstrapContext, ResourceKey<ConfiguredFeature<?, ?>> resourceKey, F feature, FC featureConfiguration) {
        bootstrapContext.register(resourceKey, new ConfiguredFeature<>(feature, featureConfiguration));
    }
}
