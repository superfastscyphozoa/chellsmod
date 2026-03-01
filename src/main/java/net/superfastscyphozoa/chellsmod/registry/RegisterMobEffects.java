package net.superfastscyphozoa.chellsmod.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.superfastscyphozoa.chellsmod.Chellsmod;
import net.superfastscyphozoa.chellsmod.effect.FesteringEffect;

public class RegisterMobEffects {
    public static final Holder<MobEffect> FESTERING =
            Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, "festering"), new FesteringEffect());

    public static void initChellsmodMobEffects(){
        Chellsmod.LOGGER.info("registering " + Chellsmod.MOD_ID + " effects!");
    }
}
