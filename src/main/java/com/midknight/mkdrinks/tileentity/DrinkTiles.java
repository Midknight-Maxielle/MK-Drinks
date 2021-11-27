package com.midknight.mkdrinks.tileentity;

import com.midknight.mkdrinks.MKDrinks;
import com.midknight.mkdrinks.block.DrinkBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DrinkTiles {

    public static final DeferredRegister<TileEntityType<?>> TILES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MKDrinks.MOD_ID);

    public static final RegistryObject<TileEntityType<CrucibleTile>> CRUCIBLE_TILE =
            TILES.register("crucible_tile", () -> TileEntityType.Builder.create(
                    CrucibleTile::new, DrinkBlocks.CRUCIBLE.get()).build(null));

}
