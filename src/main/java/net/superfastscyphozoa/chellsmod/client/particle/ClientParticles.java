package net.superfastscyphozoa.chellsmod.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.superfastscyphozoa.chellsmod.registry.RegisterParticles;

@Environment(EnvType.CLIENT)
public class ClientParticles {

    public static void initParticles() {
        ParticleFactoryRegistry.getInstance().register(RegisterParticles.FESTERING_PARTICLE, FesteringParticle.Provider::new);

        ParticleFactoryRegistry.getInstance().register(RegisterParticles.SEEDING_DANDELION, SeedingDandelionParticle.SeedingDandelionProvider::new);
    }
}
