package com.midknight.juicebar.registry;

import com.midknight.juicebar.Juicebar;
import com.midknight.juicebar.tileentity.CrucibleTile;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JuiceTiles {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Juicebar.MOD_ID);

    public static final RegistryObject<BlockEntityType<CrucibleTile>> CRUCIBLE_TILE =
            BLOCK_ENTITIES.register("crucible_tile",
                    () -> BlockEntityType.Builder.of(
                            CrucibleTile::new, JuiceBlocks.CRUCIBLE.get())
                            .build(null));

}
