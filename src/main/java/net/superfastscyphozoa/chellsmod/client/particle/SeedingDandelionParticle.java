package net.superfastscyphozoa.chellsmod.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

@Environment(EnvType.CLIENT)
public class SeedingDandelionParticle extends SingleQuadParticle {
    private float rotSpeed = (float)Math.toRadians(this.random.nextBoolean() ? -30.0 : 30.0);
    private final float spinAcceleration = (float)Math.toRadians(this.random.nextBoolean() ? -5.0 : 5.0);
    private final double xaFlowScale;
    private final double zaFlowScale;

    protected SeedingDandelionParticle(
            ClientLevel clientLevel, double d, double e, double f, TextureAtlasSprite textureAtlasSprite, float grav, float h, float size, float j
    ) {
        super(clientLevel, d, e, f, textureAtlasSprite);
        this.lifetime = 200;
        this.gravity = grav * 1.2F * 0.0025F;
        float k = size * (this.random.nextBoolean() ? 0.05F : 0.075F);
        this.quadSize = k;
        this.setSize(k, k);
        this.friction = 1.0F;
        this.yd = -j;
        float rand = this.random.nextFloat();
        this.xaFlowScale = Math.cos(Math.toRadians(rand * 60.0F)) * h;
        this.zaFlowScale = Math.sin(Math.toRadians(rand * 60.0F)) * h;
    }

    @Override
    protected Layer getLayer() {
        return SingleQuadParticle.Layer.OPAQUE;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.lifetime-- <= 0) {
            this.remove();
        }

        if (!this.removed) {
            float f = 200 - this.lifetime;
            float g = Math.min(f / 300.0F, 1.0F);
            double d = 0.0;
            double e = 0.0;

            d += this.xaFlowScale * Math.pow(g, 1.25);
            e += this.zaFlowScale * Math.pow(g, 1.25);


            this.xd += d * 0.0025F;
            this.zd += e * 0.0025F;
            this.yd = this.yd - this.gravity;
            this.rotSpeed = this.rotSpeed + this.spinAcceleration / 20.0F;
            this.oRoll = this.roll;
            this.roll = this.roll + this.rotSpeed / 20.0F;

            this.move(this.xd, this.yd * -1, this.zd);

            if (this.lifetime < 199 && (this.xd == 0.0 || this.zd == 0.0)) {
                this.remove();
            }

            if (!this.removed) {
                this.xd = this.xd * this.friction;
                this.yd = this.yd * this.friction;
                this.zd = this.zd * this.friction;
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public static class SeedingDandelionProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public SeedingDandelionProvider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(
                SimpleParticleType simpleParticleType, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i, RandomSource randomSource
        ) {
            return new SeedingDandelionParticle(clientLevel, d, e, f, this.sprites.get(randomSource), 0.075F, 2.0F, 3.0F, 0.0F);
        }
    }
}
