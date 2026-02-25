package net.superfastscyphozoa.chellsmod.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.superfastscyphozoa.chellsmod.Chellsmod;

import java.util.function.Function;

public class RegisterBlocks {

    //registry

    public static final Block EXAMPLE_BLOCK = registerBlock(
            "example_block",
            Block::new,
            BlockBehaviour.Properties.of().sound(SoundType.TUFF),
            true
    );

    //registry end

    //-----------------------------------------------------------

    public static Block registerBlock
            (String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem) {

        //create the block key
        ResourceKey<Block> blockKey = keyOfBlock(name);
        //create the block instance
        Block block = blockFactory.apply(settings.setId(blockKey));

        if (shouldRegisterItem) {
            ResourceKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, name));
    }

    //-----------------------------------------------------------

    public static void initChellsmodBlocks() {
        Chellsmod.LOGGER.info("registering " + Chellsmod.MOD_ID + " blocks!");

        addToItemGroups();
    }

    //-----------------------------------------------------------

    //add block items to vanilla item groups

    private static void addToItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS)
                .register((itemGroup -> itemGroup.accept(EXAMPLE_BLOCK)));
    }
}
