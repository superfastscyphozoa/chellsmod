package net.superfastscyphozoa.chellsmod.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.superfastscyphozoa.chellsmod.registry.RegisterItems;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected net.minecraft.data.recipes.RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
        return new net.minecraft.data.recipes.RecipeProvider(registryLookup, exporter) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

//                shaped(RecipeCategory.MISC, Items.CRAFTING_TABLE, 4)
//                        .pattern("eee")
//                        .pattern("eee")
//                        .pattern("eee")
//                        .define('e', ItemTags.EXAMPLE)
//                        .group("example")
//                        .unlockedBy(getHasName(Items.EXAMPLE), has(Items.EXAMPLE))
//                        .save(output, "example_recipe");

                shapeless(RecipeCategory.MISC, Items.SLIME_BALL, 1)
                        .requires(RegisterItems.MAGGOT_ITEM)
                        .group("slime_ball")
                        .unlockedBy(getHasName(RegisterItems.MAGGOT_ITEM), has(RegisterItems.MAGGOT_ITEM))
                        .save(output, "slime_ball_from_maggot");
            }
        };
    }

    @Override
    public String getName() {
        return "ChellsmodRecipeProvider";
    }
}
