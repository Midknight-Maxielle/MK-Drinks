package com.midknight.mkdrinks.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IHeatableTile {

    default boolean isHeated(World world, BlockPos pos) {
        BlockState stateBelow = world.getBlockState(pos.down());
        if (BlockTags.CAMPFIRES.contains(stateBelow.getBlock())) {
            if (stateBelow.hasProperty(BlockStateProperties.LIT)) {
                return stateBelow.get(BlockStateProperties.LIT);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
