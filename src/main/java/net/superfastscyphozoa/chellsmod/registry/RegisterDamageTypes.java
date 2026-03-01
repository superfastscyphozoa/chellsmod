package net.superfastscyphozoa.chellsmod.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.superfastscyphozoa.chellsmod.Chellsmod;

public class RegisterDamageTypes {
    public static final ResourceKey<DamageType> MAGGOT_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, "maggot"));

    public static void initChellsmodDamageTypes(){
        Chellsmod.LOGGER.info("registering " + Chellsmod.MOD_ID + " damage types!");
    }
}
