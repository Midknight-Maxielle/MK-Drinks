package com.midknight.owmix.registry;

import com.midknight.owmix.OverworldMixology;
import com.midknight.owmix.util.JuiceArmourMaterial;
import com.midknight.owmix.util.JuiceTab;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryEquipment {

    // =============================== //
    // Item Registry & Register Method //
    // =============================== //
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, OverworldMixology.MOD_ID);

    public static final RegistryObject<Item> DRINKMETAL_BOW = ITEMS.register("drinkmetal_bow",
            () -> new BowItem(new Item.Properties()
                    .tab(JuiceTab.JUICEBAR)
                    .stacksTo(1)
                    .durability(962)
            ));

    public static final RegistryObject<Item> DRINKMETAL_BOOTS = ITEMS.register("drinkmetal_boots",
            () -> new ArmorItem(
                    JuiceArmourMaterial.DRINKMETAL,
                    EquipmentSlot.FEET,
                    new Item.Properties()
                            .tab(JuiceTab.JUICEBAR)
                            .stacksTo(1)
            ));

    public static final RegistryObject<Item> DRINKMETAL_CHEST = ITEMS.register("drinkmetal_chestplate",
            () -> new ArmorItem(
                    JuiceArmourMaterial.DRINKMETAL,
                    EquipmentSlot.CHEST,
                    new Item.Properties()
                            .tab(JuiceTab.JUICEBAR)
                            .stacksTo(1)
            ));

    public static final RegistryObject<Item> DRINKMETAL_LEGGINGS = ITEMS.register("drinkmetal_leggings",
            () -> new ArmorItem(
                    JuiceArmourMaterial.DRINKMETAL,
                    EquipmentSlot.LEGS,
                    new Item.Properties()
                            .tab(JuiceTab.JUICEBAR)
                            .stacksTo(1)
            ));

    public static final RegistryObject<Item> DRINKMETAL_HELMET = ITEMS.register("drinkmetal_helmet",
            () -> new ArmorItem(
                    JuiceArmourMaterial.DRINKMETAL,
                    EquipmentSlot.HEAD,
                    new Item.Properties()
                            .tab(JuiceTab.JUICEBAR)
                            .stacksTo(1)
            ));

}
