package com.midknight.owmix.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public interface HeatableEntityInterface {

    static boolean isSource(Block block) {
        if((block == Blocks.LAVA) ||
            (block == Blocks.CAMPFIRE) ||
            (block == Blocks.SOUL_CAMPFIRE) ||
            (block == Blocks.FIRE)) {
            return true;
        } else {
            return false;
        }
    }

    default boolean isHeated(Level world, BlockPos position) {
        BlockState stateUnderBlock = world.getBlockState(position.below());
        if(isSource(stateUnderBlock.getBlock())) {
            if (stateUnderBlock.hasProperty(BlockStateProperties.LIT)) {
                return stateUnderBlock.getValue(BlockStateProperties.LIT);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
