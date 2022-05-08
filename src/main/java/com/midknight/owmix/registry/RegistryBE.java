package com.midknight.owmix.registry;

import com.midknight.owmix.OverworldMixology;
import com.midknight.owmix.blockentity.CrucibleBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryBE {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, OverworldMixology.MOD_ID);

    public static final RegistryObject<BlockEntityType<CrucibleBlockEntity>> CRUCIBLE_TILE =
            BLOCK_ENTITIES.register("crucible_tile",
                    () -> BlockEntityType.Builder.of(
                            CrucibleBlockEntity::new, RegistryBlocks.CRUCIBLE.get())
                            .build(null));

}
