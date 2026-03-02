package net.superfastscyphozoa.chellsmod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.superfastscyphozoa.chellsmod.registry.RegisterParticles;

public class SeedingDandelionBlock extends FlowerBlock {
    public static final BooleanProperty BLOWN = BooleanProperty.create("blown");

    public SeedingDandelionBlock(Holder<MobEffect> holder, float f, Properties properties) {
        super(holder, f, properties);
        registerDefaultState(defaultBlockState().setValue(BLOWN, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BLOWN);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        if (!player.getAbilities().mayBuild) {
            return InteractionResult.PASS;
        }

        boolean blown = blockState.getValue(BLOWN);

        if (!blown){
            level.setBlockAndUpdate(blockPos, blockState.setValue(BLOWN, true));

            animateBlow(blockPos, level);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    //-----------------------

    private void animateBlow(BlockPos blockPos, Level level){
        RandomSource randomSource = level.getRandom();

        for (int i = 0; i < 8; i++) {
            double d = blockPos.getX() + (0.5 + randomFloat(randomSource));
            double e = blockPos.getY() + (0.5 + randomFloat(randomSource));
            double f = blockPos.getZ() + (0.5 + randomFloat(randomSource));

            level.addParticle(RegisterParticles.SEEDING_DANDELION, d, e, f, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if (randomSource.nextInt(4) == 1 && !blockState.getValue(BLOWN)) {

            double d = blockPos.getX() + (0.5 + randomFloat(randomSource));
            double e = blockPos.getY() + (0.5 + randomFloat(randomSource));
            double f = blockPos.getZ() + (0.5 + randomFloat(randomSource));

            level.addParticle(RegisterParticles.SEEDING_DANDELION, d, e, f, 0.0, 0.0, 0.0);
        }
    }

    //-----------------------

    private float randomFloat(RandomSource randomSource){
        int fiftyFifty = randomSource.nextInt(2);

        float nextFloat;
        if (fiftyFifty == 1) {
            nextFloat = randomSource.nextFloat() / 5;
        } else {
            nextFloat = -randomSource.nextFloat() / 5;
        }

        return nextFloat;
    }
}
