package net.superfastscyphozoa.chellsmod.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.superfastscyphozoa.chellsmod.Chellsmod;

import java.util.function.Function;

public class RegisterItems {

    //registry

    public static final Item MAGGOT_ITEM = registerItem("maggot_item", Item::new, new Item.Properties());

    //registry end

    //-----------------------------------------------------------

    public static <GenericItem extends Item> GenericItem registerItem
            (String name, Function<Item.Properties, GenericItem> itemFactory, Item.Properties settings) {
        //create the item key
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, name));

        //create the item instance
        GenericItem item = itemFactory.apply(settings.setId(itemKey));

        //register the item
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    private static Function<Item.Properties, Item> createBlockItemWithCustomItemName(Block block) {
        return (properties) -> new BlockItem(block, properties.useItemDescriptionPrefix());
    }

    //-----------------------------------------------------------

    public static void initChellsmodItems() {
        Chellsmod.LOGGER.info("registering " + Chellsmod.MOD_ID + " items!");

        additionalProperties();
        addToItemGroups();
    }

    //-----------------------------------------------------------

    //additional properties eg. fuel, compost, etc.

    private static void additionalProperties() {
        CompostingChanceRegistry.INSTANCE.add(MAGGOT_ITEM, 0.3f);
    }

    //add items to vanilla item groups

    private static void addToItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT)
                .register((itemGroup -> itemGroup.addBefore(Items.WIND_CHARGE, MAGGOT_ITEM)));
    }
}
