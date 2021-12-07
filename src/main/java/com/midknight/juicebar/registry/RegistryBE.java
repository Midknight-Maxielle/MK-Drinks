package com.midknight.juicebar.registry;

import com.midknight.juicebar.Juicebar;
import com.midknight.juicebar.blockentity.CrucibleBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryBE {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Juicebar.MOD_ID);

    public static final RegistryObject<BlockEntityType<CrucibleBlockEntity>> CRUCIBLE_TILE =
            BLOCK_ENTITIES.register("crucible_tile",
                    () -> BlockEntityType.Builder.of(
                            CrucibleBlockEntity::new, RegistryBlocks.CRUCIBLE.get())
                            .build(null));

}
