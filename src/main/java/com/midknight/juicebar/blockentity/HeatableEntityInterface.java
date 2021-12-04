package com.midknight.juicebar.blockentity;

import com.midknight.juicebar.util.JuiceTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public interface HeatableEntityInterface {

    default boolean isHeated(Level world, BlockPos position) {
        BlockState stateUnderBlock = world.getBlockState(position.below());
        if (JuiceTags.HEAT_SOURCE.contains(stateUnderBlock.getBlock())) {
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
