package net.superfastscyphozoa.chellsmod.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Optional;

public class ShroomBlock extends VegetationBlock implements BonemealableBlock {
    private static final VoxelShape SHAPE = Block.column(6.0, 0.0, 6.0);

    public ShroomBlock(Properties properties) {
        super(properties.noCollision().randomTicks().instabreak().sound(SoundType.GRASS)
                .hasPostProcess(Blocks::always).pushReaction(PushReaction.DESTROY));
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {return null;}

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.isSolidRender();
    }

    @Override
    protected boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockPos blockBelow = blockPos.below();
        BlockState blockBelowState = levelReader.getBlockState(blockBelow);

        return blockBelowState.is(BlockTags.MUSHROOM_GROW_BLOCK) ||
                levelReader.getRawBrightness(blockPos, 0) < 13 &&
                        this.mayPlaceOn(blockBelowState, levelReader, blockBelow);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return BonemealableBlock.hasSpreadableNeighbourPos(levelReader, blockPos, blockState);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        Optional<BlockPos> neighbourPos = BonemealableBlock.findSpreadableNeighbourPos(serverLevel, blockPos, blockState);

        if (neighbourPos.isPresent()) {
            if (blockState.canSurvive(serverLevel, neighbourPos.get())){
                serverLevel.setBlockAndUpdate(neighbourPos.get(), blockState);
            }
        }
    }
}
