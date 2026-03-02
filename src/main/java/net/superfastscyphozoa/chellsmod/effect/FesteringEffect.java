package net.superfastscyphozoa.chellsmod.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.superfastscyphozoa.chellsmod.entity.FlyEntity;
import net.superfastscyphozoa.chellsmod.registry.RegisterParticles;
import net.superfastscyphozoa.chellsmod.utils.DamageTypeUtils;
import net.superfastscyphozoa.chellsmod.registry.RegisterEntities;
import org.joml.Vector3f;

public class FesteringEffect extends MobEffect {
    public FesteringEffect() {
        super(MobEffectCategory.HARMFUL, 0, RegisterParticles.FESTERING_PARTICLE);
    }

    @Override
    public void onEffectRemoved(MobEffectInstance effectInstance, LivingEntity entity) {
        if (!entity.isDeadOrDying()) {
            Level level = entity.level();

            if (level instanceof ServerLevel serverLevel && !level.isClientSide()) {
                FlyEntity fly = RegisterEntities.FLY.create(serverLevel, EntitySpawnReason.TRIGGERED);
                if (fly != null) {

                    RandomSource randomSource = entity.getRandom();
                    float h = Mth.randomBetween(randomSource, (float) (-Math.PI / 2), (float) (Math.PI / 2));
                    Vector3f vector3f = entity.getLookAngle().toVector3f().mul(0.3F).mul(1.0F, 1.5F, 1.0F).rotateY(h);

                    fly.snapTo(entity.getX(), entity.getY() + (entity.getEyeHeight() / 2), entity.getZ(), level.getRandom().nextFloat() * 360.0F, 0.0F);
                    fly.setDeltaMovement(new Vec3(vector3f));
                    serverLevel.addFreshEntity(fly);

                    entity.hurtServer(serverLevel, DamageTypeUtils.damageSource(level, DamageTypeUtils.MAGGOT_DAMAGE), 2);

                    fly.playSound(SoundEvents.HONEY_BLOCK_PLACE);
                }
            }
        }
    }
}
