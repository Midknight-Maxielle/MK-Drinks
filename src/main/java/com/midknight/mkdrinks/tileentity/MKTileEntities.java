package com.midknight.mkdrinks.tileentity;

import com.midknight.mkdrinks.MKDrinks;
import com.midknight.mkdrinks.block.MKBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MKTileEntities {

    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MKDrinks.MOD_ID);

    public static RegistryObject<TileEntityType<CrucibleTile>> CRUCIBLE_TILE =
            TILE_ENTITIES.register("crucible_tile", () -> TileEntityType.Builder.create(
                    CrucibleTile::new, MKBlocks.CRUCIBLE.get()).build(null));

    public static void register(IEventBus eventBus) { TILE_ENTITIES.register(eventBus); }
}
