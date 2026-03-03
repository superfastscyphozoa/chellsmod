package net.superfastscyphozoa.chellsmod.data.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.superfastscyphozoa.chellsmod.registry.RegisterBlocks;

import java.util.concurrent.CompletableFuture;

public class BlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public BlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

//    public static final TagKey<Block> EXAMPLE_BLOCKS =
//            TagKey.create(
//                    Registries.BLOCK,
//                    Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, "example_blocks")
//            );

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(BlockTags.FLOWERS)
                .add(RegisterBlocks.SEEDING_DANDELION)
                .add(RegisterBlocks.FIREWEED);

        valueLookupBuilder(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(RegisterBlocks.CHARRED_LOG);

        valueLookupBuilder(BlockTags.LOGS_THAT_BURN)
                .add(RegisterBlocks.CHARRED_LOG);
    }
}
