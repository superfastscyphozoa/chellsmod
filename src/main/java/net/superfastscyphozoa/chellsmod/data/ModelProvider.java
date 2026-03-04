package net.superfastscyphozoa.chellsmod.data;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import net.superfastscyphozoa.chellsmod.registry.RegisterBlocks;
import net.superfastscyphozoa.chellsmod.registry.RegisterItems;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

        blockStateModelGenerator.createTrivialCube(RegisterBlocks.EXAMPLE_BLOCK);

        blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(RegisterBlocks.CHARRED_LOG, TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);

        blockStateModelGenerator.createCrossBlockWithDefaultItem(RegisterBlocks.PENNY_BUN, BlockModelGenerators.PlantType.NOT_TINTED);

        blockStateModelGenerator.createCrossBlockWithDefaultItem(RegisterBlocks.ALOE, BlockModelGenerators.PlantType.NOT_TINTED);
        blockStateModelGenerator.createDoublePlantWithDefaultItem(RegisterBlocks.BLOOMING_ALOE, BlockModelGenerators.PlantType.NOT_TINTED);

//        blockStateModelGenerator.createTrivialBlock(RegisterBlocks.CHARRED_LOG, TexturedModel.COLUMN_ALT);

//        blockStateModelGenerator.family(RegisterBlocks.EXAMPLE_BLOCK)
//                .stairs(RegisterBlocks.EXAMPLE_STAIRS)
//                .slab(RegisterBlocks.EXAMPLE_SLAB)
//                .fence(RegisterBlocks.EXAMPLE_FENCE);

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {

        itemModelGenerator.generateFlatItem(RegisterItems.MAGGOT_ITEM, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(RegisterItems.FLY_EGG_CLUSTER, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(RegisterItems.FLY_MEAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(RegisterItems.COOKED_FLY_MEAT, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(RegisterItems.FLY_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
    }

    @Override
    public String getName() {
        return "ChellsmodModelProvider";
    }
}
