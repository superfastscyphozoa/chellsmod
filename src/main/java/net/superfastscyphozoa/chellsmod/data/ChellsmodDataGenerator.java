package net.superfastscyphozoa.chellsmod.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.superfastscyphozoa.chellsmod.data.lang.EnUsLangProvider;
import net.superfastscyphozoa.chellsmod.data.loot_tables.BlockLootTableProvider;
import net.superfastscyphozoa.chellsmod.data.tags.BlockTagProvider;
import net.superfastscyphozoa.chellsmod.data.tags.EntityTagProvider;
import net.superfastscyphozoa.chellsmod.data.tags.ItemTagProvider;
import net.superfastscyphozoa.chellsmod.worldgen.features.configured.ChellsmodConfiguredFeatureUtils;
import net.superfastscyphozoa.chellsmod.worldgen.features.placed.ChellsmodPlacedFeatureUtils;

public class ChellsmodDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(EnUsLangProvider::new);

        pack.addProvider(ModelProvider::new);
        pack.addProvider(BlockLootTableProvider::new);
        pack.addProvider(RecipeProvider::new);

        pack.addProvider(ItemTagProvider::new);
        pack.addProvider(BlockTagProvider::new);
        pack.addProvider(EntityTagProvider::new);

        pack.addProvider(DynamicRegistryProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, ChellsmodConfiguredFeatureUtils::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, ChellsmodPlacedFeatureUtils::bootstrap);
    }
}
