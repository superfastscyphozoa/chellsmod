package net.superfastscyphozoa.chellsmod.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

public class LootContainerItem extends Item {
    private final ItemLike containedItem;
    private final SoundEvent openingSound;

    public LootContainerItem(ItemLike containedItem, SoundEvent openingSound, Properties properties) {
        super(properties.useCooldown(0.5f).stacksTo(16));
        this.containedItem = containedItem;
        this.openingSound = openingSound;
    }

    private ItemLike getContainedItem() {
        return this.containedItem;
    }

    private SoundEvent getOpeningSound() {
        return this.openingSound;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        int amountToAdd = 1 + level.getRandom().nextInt(5);
        ItemStack itemToAdd = new ItemStack(getContainedItem(), amountToAdd);

        if (!level.isClientSide()) {
            if (!player.getInventory().add(itemToAdd)) {
                player.drop(itemToAdd, false);
            }

            playOpeningSound(level, player);
        }

        ItemStack itemInHand = player.getItemInHand(interactionHand);
        itemInHand.consume(1, player);

        return InteractionResult.SUCCESS;
    }

    private void playOpeningSound(Level level, Player player){
        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                getOpeningSound(),
                SoundSource.NEUTRAL,
                0.5f,
                0.4f / (level.getRandom().nextFloat() * 0.4f + 0.8f)
        );
    }
}
