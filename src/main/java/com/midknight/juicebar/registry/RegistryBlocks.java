package com.midknight.juicebar.registry;

import com.midknight.juicebar.Juicebar;
import com.midknight.juicebar.block.BottleGlassBlock;
import com.midknight.juicebar.block.CrucibleBlock;
import com.midknight.juicebar.util.JuiceTab;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RegistryBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Juicebar.MOD_ID);

    // - - - - - Simple Blocks - - - - - //
    // - - - - - - - - - - - - - - - - - //

    public static final RegistryObject<Block> BOTTLE_GLASS = registerBlock("bottle_glass",
            () -> new BottleGlassBlock(BlockBehaviour
                    .Properties.of(Material.GLASS)
                    .sound(SoundType.GLASS)
                    .strength(1.0F)
                    .noOcclusion()
                    .isRedstoneConductor(RegistryBlocks::isntSolid)
                    .isViewBlocking(RegistryBlocks::isntSolid)
            ));

    public static final RegistryObject<Block> BOTTLE_GLASS_PANE = registerBlock("bottle_glass_pane",
            () -> new IronBarsBlock(BlockBehaviour.Properties.of(Material.GLASS)
                    .sound(SoundType.GLASS)
                    .strength(1.0F)
                    .noOcclusion()
            ));

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
