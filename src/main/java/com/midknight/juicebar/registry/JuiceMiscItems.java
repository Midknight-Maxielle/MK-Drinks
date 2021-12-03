package com.midknight.juicebar.registry;

import com.midknight.juicebar.Juicebar;
import com.midknight.juicebar.util.JuiceTab;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JuiceMiscItems {

    // =============================== //
    // Item Registry & Register Method //
    // =============================== //
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Juicebar.MOD_ID);

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
