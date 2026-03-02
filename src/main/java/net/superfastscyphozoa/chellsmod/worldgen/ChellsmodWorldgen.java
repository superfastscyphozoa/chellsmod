package net.superfastscyphozoa.chellsmod.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.superfastscyphozoa.chellsmod.Chellsmod;
import net.superfastscyphozoa.chellsmod.worldgen.features.placed.VegetationPlacedFeatures;

public class ChellsmodWorldgen {

    public static void modifyBiomes(){
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                VegetationPlacedFeatures.PATCH_TALL_GRASS
        );

        BiomeModifications.create(Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, "replace_plains_flowers"))
                .add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(Biomes.PLAINS), biomeModificationContext -> {

                    biomeModificationContext.getGenerationSettings()
                            .removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_PLAINS);

                    biomeModificationContext.getGenerationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_SEEDING_DANDELION);
                });


    }
}
