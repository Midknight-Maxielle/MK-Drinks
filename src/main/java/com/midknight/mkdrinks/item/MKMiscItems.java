package com.midknight.mkdrinks.item;

import com.midknight.mkdrinks.MKDrinks;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MKMiscItems {

    // =============================== //
    // Item Registry & Register Method //
    // =============================== //
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MKDrinks.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    // ============= //
    // Empty Bottles //
    // ============= //

    public static final RegistryObject<Item> DRINK_BOTTLE = ITEMS.register("drink_bottle",
            () -> new Item(new Item.Properties().group(MKCreativeTab.MKDRINKS)));

    public static final RegistryObject<Item> BIG_DRINK_BOTTLE = ITEMS.register("big_drink_bottle",
            () -> new Item(new Item.Properties().group(MKCreativeTab.MKDRINKS)));

    // ============== //
    // Dusts & Ingots //
    // ============== //

    public static final RegistryObject<Item> METALLIZED_BLEND = ITEMS.register("metallized_blend",
            () -> new Item(new Item.Properties().group(MKCreativeTab.MKDRINKS)));

    public static final RegistryObject<Item> DRINKMETAL_BLEND = ITEMS.register("drinkmetal_blend",
            () -> new Item(new Item.Properties().group(MKCreativeTab.MKDRINKS)));

    public static final RegistryObject<Item> DRINKMETAL_INGOT = ITEMS.register("drinkmetal_ingot",
            () -> new Item(new Item.Properties().group(MKCreativeTab.MKDRINKS)));

}
