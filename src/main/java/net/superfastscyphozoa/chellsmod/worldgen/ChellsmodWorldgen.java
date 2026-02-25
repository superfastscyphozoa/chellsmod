package net.superfastscyphozoa.chellsmod.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.superfastscyphozoa.chellsmod.worldgen.features.placed.VegetationPlacedFeatures;

public class ChellsmodWorldgen {

    public static void modifyBiomes(){
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                VegetationPlacedFeatures.PATCH_TALL_GRASS
        );
    }
}
