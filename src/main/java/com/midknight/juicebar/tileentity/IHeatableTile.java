package com.midknight.juicebar.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public interface IHeatableTile {

    default boolean isHeated(Level world, BlockPos pos) {
        BlockState stateBelow = world.getBlockState(pos.below());
        if (BlockTags.CAMPFIRES.contains(stateBelow.getBlock())) {
            if (stateBelow.hasProperty(BlockStateProperties.LIT)) {
                return stateBelow.getValue(BlockStateProperties.LIT);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
