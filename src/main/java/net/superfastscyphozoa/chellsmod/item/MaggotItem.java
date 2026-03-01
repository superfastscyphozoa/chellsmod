package net.superfastscyphozoa.chellsmod.item;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import net.superfastscyphozoa.chellsmod.entity.projectile.MaggotProjectileEntity;

public class MaggotItem extends Item implements ProjectileItem {
    public static float PROJECTILE_SHOOT_POWER = 1.5F;

    public MaggotItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);

        playThrowSounds(level, player, SoundEvents.EGG_THROW, 0.5f);
        playThrowSounds(level, player, SoundEvents.HONEY_BLOCK_PLACE,0.3f);

        if (level instanceof ServerLevel serverLevel) {
            Projectile.spawnProjectileFromRotation(MaggotProjectileEntity::new, serverLevel, itemStack, player, 0.0F, PROJECTILE_SHOOT_POWER, 1.0F);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        itemStack.consume(1, player);
        return InteractionResult.SUCCESS;
    }

    private void playThrowSounds(Level level, Player player, SoundEvent soundEvent, float volume){
        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                soundEvent,
                SoundSource.NEUTRAL,
                volume,
                0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );
    }

    @Override
    public Projectile asProjectile(Level level, Position position, ItemStack itemStack, Direction direction) {
        return new MaggotProjectileEntity(level, position.x(), position.y(), position.z(), itemStack);
    }
}
