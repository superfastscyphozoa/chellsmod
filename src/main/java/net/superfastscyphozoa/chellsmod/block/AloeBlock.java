package net.superfastscyphozoa.chellsmod.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.superfastscyphozoa.chellsmod.registry.RegisterBlocks;
import org.jspecify.annotations.Nullable;

public class AloeBlock extends DoublePlantBlock implements BonemealableBlock {
    private static final VoxelShape SHAPE = Block.column(16.0, 0.0, 13.0);
    public static final BooleanProperty BLOOMING = BooleanProperty.create("blooming");

    public AloeBlock(Properties properties, Boolean blooming) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(BLOOMING, blooming));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BLOOMING);
    }

    @Override
    public MapCodec<? extends DoublePlantBlock> codec() {return null;}

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        if (blockState.getValue(BLOOMING)) {
            return super.getShape(blockState, blockGetter, blockPos, collisionContext);
        } else {
            return SHAPE;
        }
    }

    //------------------

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return canBonemealAloe(levelReader, blockPos, blockState);
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
       growAloe(serverLevel, blockPos, blockState);
    }

    private void growAloe(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState){
        if (!blockState.getValue(BLOOMING)) {
            if (serverLevel.getBlockState(blockPos.above()).isAir()) {

                serverLevel.setBlockAndUpdate(blockPos, RegisterBlocks.BLOOMING_ALOE.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER));
                serverLevel.setBlockAndUpdate(blockPos.above(), RegisterBlocks.BLOOMING_ALOE.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER));

            } else {
                BonemealableBlock.findSpreadableNeighbourPos(serverLevel, blockPos, blockState)
                        .ifPresent(blockPosx -> serverLevel.setBlockAndUpdate(blockPosx, RegisterBlocks.ALOE.defaultBlockState()));
            }
        } else {
            BlockPos blockPos2 = blockPos;
            BlockState blockState2 = blockState;

            if (blockState.getValue(HALF).equals(DoubleBlockHalf.UPPER)) {
                blockPos2 = blockPos.below();
                blockState2 = serverLevel.getBlockState(blockPos2);
            }

            BonemealableBlock.findSpreadableNeighbourPos(serverLevel, blockPos2, blockState2)
                    .ifPresent(blockPosx -> serverLevel.setBlockAndUpdate(blockPosx, RegisterBlocks.ALOE.defaultBlockState()));
        }
    }

    private boolean canBonemealAloe(LevelReader levelReader, BlockPos blockPos, BlockState blockState){
        if (!blockState.getValue(BLOOMING)) {
            if (levelReader.getBlockState(blockPos.above()).isAir()){return true;}
            else {return BonemealableBlock.hasSpreadableNeighbourPos(levelReader, blockPos, blockState);}
        } else {
            BlockPos blockPos2 = blockPos; BlockState blockState2 = blockState;

            if (blockState.getValue(HALF).equals(DoubleBlockHalf.UPPER))
            {blockPos2 = blockPos.below(); blockState2 = levelReader.getBlockState(blockPos2);}

            return BonemealableBlock.hasSpreadableNeighbourPos(levelReader, blockPos2, blockState2);
        }
    }

    //------------------

    @Override
    protected BlockState updateShape(BlockState blockState, LevelReader levelReader, ScheduledTickAccess scheduledTickAccess,
                                     BlockPos blockPos, Direction direction, BlockPos blockPos2, BlockState blockState2, RandomSource randomSource)
    {
        if (blockState.getValue(BLOOMING)) {
            return super.updateShape(blockState, levelReader, scheduledTickAccess, blockPos, direction, blockPos2, blockState2, randomSource);
        } else {
            return !blockState.canSurvive(levelReader, blockPos)
                    ? Blocks.AIR.defaultBlockState()
                    : blockState;
        }
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        if (blockPlaceContext.getItemInHand().is(RegisterBlocks.BLOOMING_ALOE.asItem())) {
            return super.getStateForPlacement(blockPlaceContext);
        } else {
            return this.defaultBlockState();
        }
    }

    @Override
    public void setPlacedBy(Level level, BlockPos blockPos, BlockState blockState, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        if (blockState.getValue(BLOOMING)) {
            BlockPos upperPos = blockPos.above();
            level.setBlock(upperPos, copyWaterloggedFrom(level, upperPos, this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER)), 3);
        }
    }

    @Override
    protected boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        if (blockState.getValue(BLOOMING)) {
            return super.canSurvive(blockState, levelReader, blockPos);
        } else {
            BlockPos blockPos2 = blockPos.below();
            return this.mayPlaceOn(levelReader.getBlockState(blockPos2), levelReader, blockPos2);
        }
    }
}
