package com.midknight.juicebar.tileentity;

import com.midknight.juicebar.Juicebar;
import com.midknight.juicebar.util.JuiceTags;
import net.minecraft.core.BlockPos;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.Tags;

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
