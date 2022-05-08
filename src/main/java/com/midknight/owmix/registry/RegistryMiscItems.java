package com.midknight.owmix.registry;

import com.midknight.owmix.OverworldMixology;
import com.midknight.owmix.util.JuiceTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryMiscItems {

    // =============================== //
    // Item Registry & Register Method //
    // =============================== //
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, OverworldMixology.MOD_ID);

    // ============= //
    // Empty Bottles //
    // ============= //

    public static final RegistryObject<Item> JUICE_BOTTLE = ITEMS.register("juice_bottle",
            () -> new Item(new Item.Properties().tab(JuiceTab.JUICEBAR)));

    // ============== //
    // Dusts & Ingots //
    // ============== //

    public static final RegistryObject<Item> METALLIZED_BLEND = ITEMS.register("metallized_blend",
            () -> new Item(new Item.Properties().tab(JuiceTab.JUICEBAR)));

    public static final RegistryObject<Item> DRINKMETAL_BLEND = ITEMS.register("drinkmetal_blend",
            () -> new Item(new Item.Properties().tab(JuiceTab.JUICEBAR)));

    public static final RegistryObject<Item> DRINKMETAL_INGOT = ITEMS.register("drinkmetal_ingot",
            () -> new Item(new Item.Properties().tab(JuiceTab.JUICEBAR)));

}
