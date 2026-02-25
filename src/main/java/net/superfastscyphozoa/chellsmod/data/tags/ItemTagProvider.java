package net.superfastscyphozoa.chellsmod.data.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.superfastscyphozoa.chellsmod.Chellsmod;
import net.superfastscyphozoa.chellsmod.registry.RegisterItems;

import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static final TagKey<Item> FLY_FOOD =
            TagKey.create(
                    Registries.ITEM,
                    Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, "fly_food")
            );

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {

        valueLookupBuilder(FLY_FOOD)
                .add(Items.ROTTEN_FLESH)
                .add(Items.CHICKEN);

        valueLookupBuilder(ItemTags.WOLF_FOOD)
                .add(RegisterItems.MAGGOT_ITEM);
    }
}
