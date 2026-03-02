package net.superfastscyphozoa.chellsmod.utils;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.level.Level;
import net.superfastscyphozoa.chellsmod.Chellsmod;
import org.jetbrains.annotations.NotNull;

public class DamageTypeUtils {

    public static final ResourceKey<DamageType> MAGGOT_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, "maggot"));

    @NotNull
    public static DamageSource damageSource(Level level, ResourceKey<DamageType> damageType) {
        if (damageType == MAGGOT_DAMAGE) {
            return source(level, MAGGOT_DAMAGE);
        } else {
            Chellsmod.LOGGER.warn("no value selected for damageTypes variable");
            return source(level, DamageTypes.GENERIC);
        }
    }

    private static DamageSource source(Level level, ResourceKey<DamageType> resourceKey) {
        return new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).get(resourceKey).orElseThrow());
    }

    public static void initChellsmodDamageTypes(){
        Chellsmod.LOGGER.info("initialising " + Chellsmod.MOD_ID + " damage types!");
    }
}
