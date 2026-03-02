package net.superfastscyphozoa.chellsmod.registry;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.superfastscyphozoa.chellsmod.Chellsmod;
import net.superfastscyphozoa.chellsmod.item.LootContainerItem;
import net.superfastscyphozoa.chellsmod.utils.ConsumableUtils;
import net.superfastscyphozoa.chellsmod.item.MaggotItem;

import java.util.function.Function;

public class RegisterItems {

    //registry

    public static final Item MAGGOT_ITEM = registerItem("maggot_item", MaggotItem::new, new Item.Properties().useCooldown(0.5F));

    public static final Item FLY_EGG_CLUSTER = registerItem("fly_egg_cluster",
            properties -> new LootContainerItem(MAGGOT_ITEM, SoundEvents.HONEY_BLOCK_PLACE, properties), new Item.Properties());

    public static final Item FLY_SPAWN_EGG = registerSpawnEgg(RegisterEntities.FLY);

    public static final Item FLY_MEAT = registerItem("fly_meat", Item::new,
            new Item.Properties().food(ConsumableUtils.FLY_MEAT, ConsumableUtils.FLY_MEAT_CONSUMABLE));
    public static final Item COOKED_FLY_MEAT = registerItem("cooked_fly_meat", Item::new,
            new Item.Properties().food(ConsumableUtils.COOKED_FLY_MEAT, ConsumableUtils.FLY_MEAT_CONSUMABLE));

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

    private static Item registerSpawnEgg(EntityType<?> entityType) {
        //removes the entity namespace from the item name
        String entityWithNamespace = (entityType + "_spawn_egg");
        String entity = entityWithNamespace.substring(17);

        return registerItem(entity, SpawnEggItem::new, (new Item.Properties()).spawnEgg(entityType));
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

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((itemGroup -> itemGroup.addBefore(Items.ENDER_PEARL, FLY_EGG_CLUSTER)));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS)
                .register((itemGroup -> itemGroup.addAfter(Items.ROTTEN_FLESH, FLY_MEAT)));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS)
                .register((itemGroup -> itemGroup.addAfter(FLY_MEAT, COOKED_FLY_MEAT)));

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS)
                .register((itemGroup -> itemGroup.addAfter(Items.SPIDER_SPAWN_EGG, FLY_SPAWN_EGG)));
    }
}
