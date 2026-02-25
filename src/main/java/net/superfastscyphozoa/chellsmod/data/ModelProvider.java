package net.superfastscyphozoa.chellsmod.data;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.superfastscyphozoa.chellsmod.registry.RegisterBlocks;
import net.superfastscyphozoa.chellsmod.registry.RegisterItems;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

        blockStateModelGenerator.createTrivialCube(RegisterBlocks.EXAMPLE_BLOCK);

//        blockStateModelGenerator.createTrivialBlock(RegisterBlocks.EXAMPLE, TexturedModel.COLUMN_ALT);

//        blockStateModelGenerator.family(RegisterBlocks.EXAMPLE_BLOCK)
//                .stairs(RegisterBlocks.EXAMPLE_STAIRS)
//                .slab(RegisterBlocks.EXAMPLE_SLAB)
//                .fence(RegisterBlocks.EXAMPLE_FENCE);

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(RegisterItems.MAGGOT_ITEM, ModelTemplates.FLAT_ITEM);
    }

    @Override
    public String getName() {
        return "ChellsmodModelProvider";
    }
}
