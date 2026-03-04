package net.superfastscyphozoa.chellsmod.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.superfastscyphozoa.chellsmod.registry.RegisterBlocks;
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

                shapeless(RecipeCategory.MISC, Items.CHARCOAL, 4)
                        .requires(RegisterBlocks.CHARRED_LOG)
                        .group("charcoal")
                        .unlockedBy(getHasName(RegisterBlocks.CHARRED_LOG), has(RegisterBlocks.CHARRED_LOG))
                        .save(output, "charcoal_from_charred_log");


                oneToOneConversionRecipe(Items.MAGENTA_DYE, RegisterBlocks.FIREWEED, "magenta_dye", 2);
                oneToOneConversionRecipe(Items.ORANGE_DYE, RegisterBlocks.BLOOMING_ALOE, "orange_dye", 2);

                //smelting and cooking

                createCookRecipe(RegisterItems.FLY_MEAT, RegisterItems.COOKED_FLY_MEAT, RecipeCategory.FOOD, cookingTypes.SMELTING, 0.35f);
                createCookRecipe(RegisterItems.FLY_MEAT, RegisterItems.COOKED_FLY_MEAT, RecipeCategory.FOOD, cookingTypes.SMOKING, 0.35f);
                createCookRecipe(RegisterItems.FLY_MEAT, RegisterItems.COOKED_FLY_MEAT, RecipeCategory.FOOD, cookingTypes.CAMPFIRE, 0.35f);

            }

            private void createCookRecipe(ItemLike ingredient, ItemLike result, RecipeCategory category, cookingTypes type, float xpGain) {
                String recipeName = cookRecipeName(result, type);
                cookRecipeBuilder(ingredient, result, category, type, xpGain, this).save(this.output, recipeName);
            }
        };
    }

    //----------------------------------------

    private enum cookingTypes {
        SMELTING,
        BLASTING,
        SMOKING,
        CAMPFIRE
    }

    private String cookRecipeName(ItemLike result, cookingTypes type) {
        return result.toString() + "_from_" + type.toString().toLowerCase();
    }

    private SimpleCookingRecipeBuilder cookRecipeBuilder(
            ItemLike ingredient, ItemLike result,
            RecipeCategory category, cookingTypes type, float xpGain ,
            net.minecraft.data.recipes.RecipeProvider provider
    ) {
        if (type == cookingTypes.SMELTING) {
            return SimpleCookingRecipeBuilder
                    .smelting(Ingredient.of(ingredient), category, result, xpGain, 200)
                    .unlockedBy("has_" + ingredient, provider.has(ingredient));
        } else if (type == cookingTypes.BLASTING) {
            return SimpleCookingRecipeBuilder
                    .blasting(Ingredient.of(ingredient), category, result, xpGain, 100)
                    .unlockedBy("has_" + ingredient, provider.has(ingredient));
        } else if (type == cookingTypes.SMOKING) {
            return SimpleCookingRecipeBuilder
                    .smoking(Ingredient.of(ingredient), category, result, xpGain, 100)
                    .unlockedBy("has_" + ingredient, provider.has(ingredient));
        } else if (type == cookingTypes.CAMPFIRE) {
            return SimpleCookingRecipeBuilder
                    .campfireCooking(Ingredient.of(ingredient), category, result, xpGain, 600)
                    .unlockedBy("has_" + ingredient, provider.has(ingredient));
        } else {
            return null;
        }
    }

    //----------------------------------------

    @Override
    public String getName() {
        return "ChellsmodRecipeProvider";
    }
}
