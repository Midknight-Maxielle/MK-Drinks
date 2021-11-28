package com.midknight.juicebar.registry;

import com.midknight.juicebar.Juicebar;
import com.midknight.juicebar.util.JuiceArmourMaterial;
import com.midknight.juicebar.util.JuiceTab;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JuiceEquipment {

    // =============================== //
    // Item Registry & Register Method //
    // =============================== //
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Juicebar.MOD_ID);

    public static final RegistryObject<Item> DRINKMETAL_BOW = ITEMS.register("drinkmetal_bow",
            () -> new BowItem(new Item.Properties()
                    .group(JuiceTab.JUICEBAR)
                    .maxStackSize(1)
                    .maxDamage(962)
            ));

    public static final RegistryObject<Item> DRINKMETAL_BOOTS = ITEMS.register("drinkmetal_boots",
            () -> new ArmorItem(
                    JuiceArmourMaterial.DRINKMETAL,
                    EquipmentSlotType.FEET,
                    new Item.Properties()
                            .group(JuiceTab.JUICEBAR)
                            .maxStackSize(1)
            ));

    public static final RegistryObject<Item> DRINKMETAL_CHEST = ITEMS.register("drinkmetal_chestplate",
            () -> new ArmorItem(
                    JuiceArmourMaterial.DRINKMETAL,
                    EquipmentSlotType.CHEST,
                    new Item.Properties()
                            .group(JuiceTab.JUICEBAR)
                            .maxStackSize(1)
            ));

    public static final RegistryObject<Item> DRINKMETAL_LEGGINGS = ITEMS.register("drinkmetal_leggings",
            () -> new ArmorItem(
                    JuiceArmourMaterial.DRINKMETAL,
                    EquipmentSlotType.LEGS,
                    new Item.Properties()
                            .group(JuiceTab.JUICEBAR)
                            .maxStackSize(1)
            ));

    public static final RegistryObject<Item> DRINKMETAL_HELMET = ITEMS.register("drinkmetal_helmet",
            () -> new ArmorItem(
                    JuiceArmourMaterial.DRINKMETAL,
                    EquipmentSlotType.HEAD,
                    new Item.Properties()
                            .group(JuiceTab.JUICEBAR)
                            .maxStackSize(1)
            ));

}
