package net.superfastscyphozoa.chellsmod.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

@Environment(EnvType.CLIENT)
public class FesteringParticle extends SimpleAnimatedParticle {
    private final float uo;
    private final float vo;

    protected FesteringParticle(ClientLevel clientLevel, double d, double e, double f, SpriteSet spriteSet) {
        super(clientLevel, d, e, f, spriteSet, 0.0f);
        this.gravity = 1.0F;
        this.quadSize /= 2.0F;
        this.uo = this.random.nextFloat() * 3.0F;
        this.vo = this.random.nextFloat() * 3.0F;
    }

    @Override
    protected float getU0() {
        return this.sprite.getU((this.uo + 1.0F) / 4.0F);
    }

    @Override
    protected float getU1() {
        return this.sprite.getU(this.uo / 4.0F);
    }

    @Override
    protected float getV0() {
        return this.sprite.getV(this.vo / 4.0F);
    }

    @Override
    protected float getV1() {
        return this.sprite.getV((this.vo + 1.0F) / 4.0F);
    }

    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) { this.sprites = spriteSet; }

        public Particle createParticle(
                SimpleParticleType simpleParticleType, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i, RandomSource randomSource
        ) {
            return new FesteringParticle(clientLevel, d, e, f, this.sprites);
        }
    }
}