package com.midknight.juicebar.registry;

import com.midknight.juicebar.Juicebar;
import com.midknight.juicebar.block.BottleGlassBlock;
import com.midknight.juicebar.block.CrucibleBlock;
import com.midknight.juicebar.util.JuiceTab;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class JuiceBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Juicebar.MOD_ID);

    // - - - - - Simple Blocks - - - - - //
    // - - - - - - - - - - - - - - - - - //

    public static final RegistryObject<Block> BOTTLE_GLASS = registerBlock("bottle_glass",
            () -> new BottleGlassBlock(AbstractBlock
                    .Properties.of(Material.GLASS)
                    .sound(SoundType.GLASS)
                    .strength(1.0F)
                    .noOcclusion()
                    .isRedstoneConductor(JuiceBlocks::isntSolid)
                    .isViewBlocking(JuiceBlocks::isntSolid)
            ));

    public static final RegistryObject<Block> BOTTLE_GLASS_PANE = registerBlock("bottle_glass_pane",
            () -> new PaneBlock(AbstractBlock.Properties.of(Material.GLASS)
                    .sound(SoundType.GLASS)
                    .strength(1.0F)
                    .noOcclusion()
            ));

    // - - - - -  Tile Entity Blocks - - - - - //
    // - - - - - - - - - - - - - - - - - - - - //

    public static final RegistryObject<Block> CRUCIBLE = registerBlock("crucible_block",
            () -> new CrucibleBlock(AbstractBlock
                    .Properties.of(Material.CLAY)
                    .strength(4.0F)));


    // - - - - -  Register Methods - - - - - //
    // - - - - - - - - - - - - - - - - - - - //

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registration = BLOCKS.register(name, block);
        registerBlockItem(name, registration);
        return registration;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        JuiceMiscItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(JuiceTab.JUICEBAR)));
    }

    private static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }
}
