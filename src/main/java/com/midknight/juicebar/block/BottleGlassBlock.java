package com.midknight.juicebar.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class BottleGlassBlock extends GlassBlock {
    public BottleGlassBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    @Override @ParametersAreNonnullByDefault
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }


    @Override @ParametersAreNonnullByDefault
    public float getShadeBrightness(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1.0F;
    }

    @Override @Nonnull @ParametersAreNonnullByDefault
    public VoxelShape getVisualShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }
}
