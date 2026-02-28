package net.superfastscyphozoa.chellsmod.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ConsumableUtils {

    //------------------

    public static final FoodProperties FLY_MEAT = new FoodProperties.Builder().nutrition(2).saturationModifier(0.0F).build();
    public static final FoodProperties COOKED_FLY_MEAT = new FoodProperties.Builder().nutrition(5).saturationModifier(0.2F).build();

    public static final Consumable FLY_MEAT_CONSUMABLE = defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA, 200, 0), 0.75F))
            .build();

    public static final FoodProperties DANDELION_SALAD = new FoodProperties.Builder().nutrition(5).saturationModifier(0.3F).build();

    //------------------

    public static Consumable.Builder defaultFood() {
        return Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true);
    }

    public static Consumable.Builder defaultDrink() {
        return Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.DRINK).sound(SoundEvents.GENERIC_DRINK).hasConsumeParticles(false);
    }
}