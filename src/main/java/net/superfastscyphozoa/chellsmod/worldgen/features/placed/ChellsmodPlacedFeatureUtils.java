package net.superfastscyphozoa.chellsmod.worldgen.features.placed;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.superfastscyphozoa.chellsmod.Chellsmod;

import java.util.List;

public class ChellsmodPlacedFeatureUtils {
    public static void bootstrap(BootstrapContext<PlacedFeature> bootstrapContext) {
        VegetationPlacedFeatures.bootstrap(bootstrapContext);
    }

    public static ResourceKey<PlacedFeature> createKey(String string) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, string));
    }

    public static void register
            (BootstrapContext<PlacedFeature> bootstrapContext, ResourceKey<PlacedFeature> resourceKey, Holder<ConfiguredFeature<?, ?>> holder, PlacementModifier... placementModifiers) {
        PlacementUtils.register(bootstrapContext, resourceKey, holder, List.of(placementModifiers));
    }
}
