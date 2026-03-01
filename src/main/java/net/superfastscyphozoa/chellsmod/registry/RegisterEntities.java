package net.superfastscyphozoa.chellsmod.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.superfastscyphozoa.chellsmod.Chellsmod;
import net.superfastscyphozoa.chellsmod.entity.FlyEntity;
import net.superfastscyphozoa.chellsmod.entity.projectile.MaggotProjectileEntity;

public class RegisterEntities {

    //registry

    public static final EntityType<FlyEntity> FLY =
            registerEntity("fly", EntityType.Builder.of(FlyEntity::new, MobCategory.CREATURE)
                    .sized(0.6F, 0.8F).clientTrackingRange(10));

    public static final EntityType<MaggotProjectileEntity> MAGGOT_PROJECTILE =
            registerEntity("maggot_projectile", EntityType.Builder.<MaggotProjectileEntity>of(MaggotProjectileEntity::new, MobCategory.MISC)
                    .noLootTable().sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10));

    private static void registerAttributes(){
        FabricDefaultAttributeRegistry.register(RegisterEntities.FLY, FlyEntity.createAttributes());
    }

    //registry end

    //---------------------------------------------------------------------

    public static <T extends Entity> EntityType<T> registerEntity(String name, EntityType.Builder<T> builder) {
        //create the entity key
        ResourceKey<EntityType<?>> entityKey = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, name));

        //create the item instance
        EntityType<T> entity = builder.build(entityKey);

        //register the item
        Registry.register(BuiltInRegistries.ENTITY_TYPE, entityKey, entity);

        return entity;
    }

    //---------------------------------------------------------------------

    public static void initChellsmodEntities(){
        Chellsmod.LOGGER.info("registering " + Chellsmod.MOD_ID + " entities!");

        registerAttributes();
    }
}
