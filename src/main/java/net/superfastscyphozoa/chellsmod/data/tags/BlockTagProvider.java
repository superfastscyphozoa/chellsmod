package net.superfastscyphozoa.chellsmod.data.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.superfastscyphozoa.chellsmod.Chellsmod;
import net.superfastscyphozoa.chellsmod.registry.RegisterBlocks;

import java.util.concurrent.CompletableFuture;

public class BlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public BlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static final TagKey<Block> EXAMPLE_BLOCKS =
            TagKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, "example_blocks")
            );

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(EXAMPLE_BLOCKS)
                .add(RegisterBlocks.EXAMPLE_BLOCK);
    }
}
