package com.midknight.owmix.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;


public class JuiceBlockEntity extends BlockEntity {

    public JuiceBlockEntity(BlockEntityType<?> blockEntityType, BlockPos position, BlockState state) {
        super(blockEntityType, position, state);
    }

    @Override @NotNull
    public final ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override @NotNull
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }
}
