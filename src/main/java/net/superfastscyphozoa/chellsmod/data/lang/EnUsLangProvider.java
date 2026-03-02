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

        translationBuilder.add(RegisterItems.FLY_SPAWN_EGG, "Fly Spawn Egg");

        translationBuilder.add(RegisterItems.FLY_EGG_CLUSTER, "Fly Egg Cluster");

        translationBuilder.add(RegisterItems.FLY_MEAT, "Fly Meat");
        translationBuilder.add(RegisterItems.COOKED_FLY_MEAT, "Cooked Fly Meat");

        translationBuilder.add(RegisterItems.MAGGOT_ITEM, "Maggot");

        translationBuilder.add(RegisterBlocks.EXAMPLE_BLOCK, "Test Block");

        translationBuilder.add("death.attack.maggot", "%1$s was parasitized by a maggot");
        translationBuilder.add("death.attack.maggot.player", "%1$s was parasitized by %2$s's maggot");
    }
}
