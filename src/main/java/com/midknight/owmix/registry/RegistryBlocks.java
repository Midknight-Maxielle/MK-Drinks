package com.midknight.owmix.registry;

import com.midknight.owmix.OverworldMixology;
import com.midknight.owmix.block.CrucibleBlock;
import com.midknight.owmix.util.JuiceTab;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RegistryBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, OverworldMixology.MOD_ID);

    // - - - - -  Tile Entity Blocks - - - - - //
    // - - - - - - - - - - - - - - - - - - - - //

    public static final RegistryObject<CrucibleBlock> CRUCIBLE = registerBlock("crucible_block", CrucibleBlock::new);


    // - - - - -  Register Methods - - - - - //
    // - - - - - - - - - - - - - - - - - - - //

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registration = BLOCKS.register(name, block);
        registerBlockItem(name, registration);
        return registration;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        RegistryMiscItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(JuiceTab.JUICEBAR)));
    }

    private static boolean isntSolid(BlockState state, BlockGetter reader, BlockPos pos) {
        return false;
    }
}
