package com.midknight.juicebar.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class BottleGlassBlock extends GlassBlock {
    public BottleGlassBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override @ParametersAreNonnullByDefault
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }


    @Override @ParametersAreNonnullByDefault
    public float getShadeBrightness(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 1.0F;
    }

    @Override @Nonnull @ParametersAreNonnullByDefault
    public VoxelShape getVisualShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }
}
