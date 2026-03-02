package net.superfastscyphozoa.chellsmod.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.superfastscyphozoa.chellsmod.Chellsmod;

public class RegisterItemGroups {

    public static final ResourceKey<CreativeModeTab> CHELLSMOD_ITEMS_TAB_KEY =
            ResourceKey.create(
                    BuiltInRegistries.CREATIVE_MODE_TAB.key(),
                    Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, "chellsmod_items_tab")
            );

    public static final ResourceKey<CreativeModeTab> CHELLSMOD_BLOCKS_TAB_KEY =
            ResourceKey.create(
                    BuiltInRegistries.CREATIVE_MODE_TAB.key(),
                    Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, "chellsmod_blocks_tab")
            );

    //------------------------------------------------

    public static final CreativeModeTab CHELLSMOD_ITEMS_TAB = FabricItemGroup.builder()
            .icon(() -> new ItemStack(RegisterItems.FLY_MEAT))
            .title(Component.translatable("itemGroup.chellsmod.items"))
            .displayItems((params, output) -> {

                output.accept(RegisterItems.MAGGOT_ITEM);

                output.accept(RegisterItems.FLY_EGG_CLUSTER);

                output.accept(RegisterItems.FLY_SPAWN_EGG);

                output.accept(RegisterItems.FLY_MEAT);
                output.accept(RegisterItems.COOKED_FLY_MEAT);

            })
            .build();

    public static final CreativeModeTab CHELLSMOD_BLOCKS_TAB = FabricItemGroup.builder()
            .icon(() -> new ItemStack(RegisterBlocks.EXAMPLE_BLOCK))
            .title(Component.translatable("itemGroup.chellsmod.blocks"))
            .displayItems((params, output) -> {

                output.accept(RegisterBlocks.SEEDING_DANDELION);

            })
            .build();

    //------------------------------------------------

    public static void initChellsmodItemGroups() {
        Chellsmod.LOGGER.info("registering " + Chellsmod.MOD_ID + " item groups!");

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CHELLSMOD_ITEMS_TAB_KEY, CHELLSMOD_ITEMS_TAB);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CHELLSMOD_BLOCKS_TAB_KEY, CHELLSMOD_BLOCKS_TAB);
    }
}
