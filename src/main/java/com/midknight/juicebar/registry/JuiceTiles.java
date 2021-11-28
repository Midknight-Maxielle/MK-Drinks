package com.midknight.juicebar.registry;

import com.midknight.juicebar.Juicebar;
import com.midknight.juicebar.registry.JuiceBlocks;
import com.midknight.juicebar.tileentity.CrucibleTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JuiceTiles {

    public static final DeferredRegister<TileEntityType<?>> TILES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Juicebar.MOD_ID);

    public static final RegistryObject<TileEntityType<CrucibleTile>> CRUCIBLE_TILE =
            TILES.register("crucible_tile", () -> TileEntityType.Builder.create(
                    CrucibleTile::new, JuiceBlocks.CRUCIBLE.get()).build(null));

}
