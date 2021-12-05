package com.midknight.juicebar.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class JuiceBlockEntity extends BlockEntity {

    public JuiceBlockEntity(BlockEntityType<?> blockEntityType, BlockPos position, BlockState state) {
        super(blockEntityType, position, state);
    }

    public void writeUpdateTag(CompoundTag tag) {}

    @Override
    public final @NotNull ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create((BlockEntity) this, JuiceBlockEntity::createUpdateTag);
    }

    private CompoundTag createUpdateTag() {
        BlockEntity self = (BlockEntity) this;
        return createUpdateTag(self);
    }

    private static CompoundTag createUpdateTag(BlockEntity blockEntity) {
        var tag = new CompoundTag();
        if(blockEntity instanceof JuiceBlockEntity juiceBlockEntity) {
            juiceBlockEntity.writeUpdateTag(tag);
        }
        return tag;
    }

    @Override
    public CompoundTag getUpdateTag() {
        return save(new CompoundTag());
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        load(pkt.getTag());
    }

}
