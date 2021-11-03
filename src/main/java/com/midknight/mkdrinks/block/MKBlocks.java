package com.midknight.mkdrinks.block;

import com.midknight.mkdrinks.MKDrinks;
import com.midknight.mkdrinks.item.MKCreativeTab;
import com.midknight.mkdrinks.item.MKItemsRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class MKBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MKDrinks.MOD_ID);


    public static final RegistryObject<Block> CRUCIBLE = registerBlock("crucible",
            () -> new CrucibleBlock(AbstractBlock
                    .Properties.create(Material.CLAY)
                    .hardnessAndResistance(4)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registration = BLOCKS.register(name, block);
        registerBlockItem(name, registration);
        return registration;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        MKItemsRegistry.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().group(MKCreativeTab.MKDRINKS)));
    }

    public static void register(IEventBus eventBus) { BLOCKS.register(eventBus); }
}
