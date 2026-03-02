package net.superfastscyphozoa.chellsmod.registry;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.superfastscyphozoa.chellsmod.Chellsmod;

public class RegisterParticles {

    public static final SimpleParticleType FESTERING_PARTICLE = FabricParticleTypes.simple();
    public static final SimpleParticleType SEEDING_DANDELION = FabricParticleTypes.simple();

    public static void initChellsmodParticles() {
        Chellsmod.LOGGER.info("registering " + Chellsmod.MOD_ID + " particles!");

        Registry.register(BuiltInRegistries.PARTICLE_TYPE,
                Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, "festering_particle"), FESTERING_PARTICLE);

        Registry.register(BuiltInRegistries.PARTICLE_TYPE,
                Identifier.fromNamespaceAndPath(Chellsmod.MOD_ID, "seeding_dandelion"), SEEDING_DANDELION);
    }
}
