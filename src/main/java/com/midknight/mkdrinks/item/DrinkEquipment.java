package com.midknight.mkdrinks.item;

import com.midknight.mkdrinks.MKDrinks;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DrinkEquipment {

    // =============================== //
    // Item Registry & Register Method //
    // =============================== //
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MKDrinks.MOD_ID);

    public static final RegistryObject<Item> DRINKMETAL_BOW = ITEMS.register("drinkmetal_bow",
            () -> new BowItem(new Item.Properties()
                    .group(DrinkTab.MKDRINKS)
                    .maxStackSize(1)
                    .maxDamage(962)
            ));

    public static final RegistryObject<Item> DRINKMETAL_BOOTS = ITEMS.register("drinkmetal_boots",
            () -> new ArmorItem(
                    DrinkArmourMat.DRINKMETAL,
                    EquipmentSlotType.FEET,
                    new Item.Properties()
                            .group(DrinkTab.MKDRINKS)
                            .maxStackSize(1)
            ));

    public static final RegistryObject<Item> DRINKMETAL_CHEST = ITEMS.register("drinkmetal_chestplate",
            () -> new ArmorItem(
                    DrinkArmourMat.DRINKMETAL,
                    EquipmentSlotType.CHEST,
                    new Item.Properties()
                            .group(DrinkTab.MKDRINKS)
                            .maxStackSize(1)
            ));

    public static final RegistryObject<Item> DRINKMETAL_LEGGINGS = ITEMS.register("drinkmetal_leggings",
            () -> new ArmorItem(
                    DrinkArmourMat.DRINKMETAL,
                    EquipmentSlotType.LEGS,
                    new Item.Properties()
                            .group(DrinkTab.MKDRINKS)
                            .maxStackSize(1)
            ));

    public static final RegistryObject<Item> DRINKMETAL_HELMET = ITEMS.register("drinkmetal_helmet",
            () -> new ArmorItem(
                    DrinkArmourMat.DRINKMETAL,
                    EquipmentSlotType.HEAD,
                    new Item.Properties()
                            .group(DrinkTab.MKDRINKS)
                            .maxStackSize(1)
            ));

}
