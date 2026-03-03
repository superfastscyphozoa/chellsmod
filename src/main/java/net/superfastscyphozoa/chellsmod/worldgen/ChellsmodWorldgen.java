package net.superfastscyphozoa.chellsmod.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.superfastscyphozoa.chellsmod.Chellsmod;
import net.superfastscyphozoa.chellsmod.worldgen.features.placed.VegetationPlacedFeatures;

public class ChellsmodWorldgen {

    public static void generateAndModifyFeatures(){
        addFeatures();
        replaceFeatures();
        removeFeatures();
    }

    //---------------------------

    private static void addFeatures(){

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                VegetationPlacedFeatures.PATCH_TALL_GRASS
        );

    }

    //---------------------------

    private static void replaceFeatures(){

        BiomeModifications.create(Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, "replace_plains_flowers"))
                .add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(Biomes.PLAINS), biomeModificationContext -> {

                    biomeModificationContext.getGenerationSettings()
                            .removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_PLAINS);

                    biomeModificationContext.getGenerationSettings()
                            .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_SEEDING_DANDELION);
                });

    }

    //---------------------------

    private static void removeFeatures(){

        BiomeModifications.create(Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, "remove_default_flowers"))
                .add(ModificationPhase.REMOVALS, BiomeSelectors.all(), biomeModificationContext -> {

                    biomeModificationContext.getGenerationSettings()
                            .removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_DEFAULT);

                    biomeModificationContext.getGenerationSettings()
                            .removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_WARM);
                });

    }
}
