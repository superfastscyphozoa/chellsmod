package net.superfastscyphozoa.chellsmod.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.superfastscyphozoa.chellsmod.Chellsmod;
import net.superfastscyphozoa.chellsmod.block.ShroomBlock;
import net.superfastscyphozoa.chellsmod.block.SeedingDandelionBlock;

import java.util.function.Function;

public class RegisterBlocks {

    //registry

    public static final Block EXAMPLE_BLOCK = registerBlock(
            "example_block",
            Block::new,
            BlockBehaviour.Properties.of().sound(SoundType.TUFF),
            true
    );

    public static final Block CHARRED_LOG = registerBlock(
            "charred_log",
            RotatedPillarBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLACK)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            true
    );

    public static final Block FIREWEED = registerBlock(
            "fireweed",
            TallFlowerBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XYZ)
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY),
            true
    );

    public static final Block PENNY_BUN = registerBlock(
            "penny_bun",
            ShroomBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN),
            true
    );

    public static final Block SEEDING_DANDELION = registerBlock(
            "seeding_dandelion",
            properties -> new SeedingDandelionBlock(MobEffects.SATURATION, 0.35F, properties),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY),
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

        additionalProperties();
        addToItemGroups();
    }

    //-----------------------------------------------------------

    //additional properties eg. fuel, compost, etc.

    private static void additionalProperties() {
        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(CHARRED_LOG, 30 * 20);
        });
    }

    //add block items to vanilla item groups

    private static void addToItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS)
                .register((itemGroup -> itemGroup.addAfter(Blocks.DANDELION, SEEDING_DANDELION)));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS)
                .register((itemGroup -> itemGroup.addAfter(Blocks.PEONY, FIREWEED)));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS)
                .register((itemGroup -> itemGroup.addAfter(Blocks.RED_MUSHROOM, PENNY_BUN)));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS)
                .register((itemGroup -> itemGroup.addBefore(Blocks.MUSHROOM_STEM, CHARRED_LOG)));
    }
}
