package net.superfastscyphozoa.chellsmod.data.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.superfastscyphozoa.chellsmod.registry.RegisterBlocks;
import net.superfastscyphozoa.chellsmod.registry.RegisterItems;

import java.util.concurrent.CompletableFuture;

public class EnUsLangProvider extends FabricLanguageProvider {
    public EnUsLangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {
        translationBuilder.add("itemGroup.chellsmod.items", "Chellsmod Items");
        translationBuilder.add("itemGroup.chellsmod.blocks", "Chellsmod Blocks");

        translationBuilder.add(RegisterItems.MAGGOT_ITEM, "Maggot");
        translationBuilder.add(RegisterBlocks.EXAMPLE_BLOCK, "Example Block");
    }
}
