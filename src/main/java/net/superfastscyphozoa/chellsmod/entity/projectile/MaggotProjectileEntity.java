package net.superfastscyphozoa.chellsmod.entity.projectile;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.superfastscyphozoa.chellsmod.entity.FlyEntity;
import net.superfastscyphozoa.chellsmod.registry.RegisterItems;
import net.superfastscyphozoa.chellsmod.registry.RegisterMobEffects;

public class MaggotProjectileEntity extends ThrowableItemProjectile {
    public MaggotProjectileEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public MaggotProjectileEntity(Level level, LivingEntity livingEntity, ItemStack itemStack) {
        super(EntityType.SNOWBALL, livingEntity, level, itemStack);
    }

    public MaggotProjectileEntity(Level level, double d, double e, double f, ItemStack itemStack) {
        super(EntityType.SNOWBALL, d, e, f, level, itemStack);
    }

    @Override
    protected Item getDefaultItem() {
        return RegisterItems.MAGGOT_ITEM;
    }

    //---------------------------

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (this.level() instanceof ServerLevel serverLevel) {
            Entity entity = entityHitResult.getEntity();

            DamageSource damageSource = this.damageSources().thrown(this, this.getOwner());
            if (entity.hurtServer(serverLevel, damageSource, 1.0F) && entity instanceof LivingEntity livingEntity) {
                EnchantmentHelper.doPostAttackEffects(serverLevel, livingEntity, damageSource);

                if (livingEntity.isAffectedByPotions() && !(livingEntity instanceof FlyEntity)) {
                    MobEffectInstance festeringEffect = new MobEffectInstance(
                            RegisterMobEffects.FESTERING, 5 * 20, 0, false, true, true
                    );
                    if (!festeringEffect.endsWithin(20)) {
                        livingEntity.addEffect(festeringEffect, this.getEffectSource());
                    }
                }
            }
        }
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide()) {

            this.level().broadcastEntityEvent(this, (byte)3);
            hitSounds(SoundEvents.HONEY_BLOCK_BREAK,0.4f);

            this.discard();
        }
    }

    //-----------------------------

    @Override
    public void handleEntityEvent(byte b) {
        if (b == 3) {
            for (int i = 0; i < 8; i++) {
                this.level().addParticle(
                        new ItemParticleOption(ParticleTypes.ITEM, this.getItem()),
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        (this.random.nextFloat() - 0.5) * 0.08,
                        (this.random.nextFloat() - 0.5) * 0.08,
                        (this.random.nextFloat() - 0.5) * 0.08
                );
            }
        }
    }

    private void hitSounds(SoundEvent soundEvent, float volume){
        Level level = this.level();

        level.playSound(
                this, this.getX(), this.getY(), this.getZ(),
                soundEvent, SoundSource.NEUTRAL, volume,
                0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );
    }

}
