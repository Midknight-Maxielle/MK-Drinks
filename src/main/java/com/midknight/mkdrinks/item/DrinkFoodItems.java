package com.midknight.mkdrinks.item;

import com.midknight.mkdrinks.MKDrinks;
import com.midknight.mkdrinks.item.specialclasses.BigDrinkitem;
import com.midknight.mkdrinks.item.specialclasses.DrinkItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DrinkFoodItems {

    // =============================== //
    // Item Registry & Register Method //
    // =============================== //
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MKDrinks.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    // ============ //
    // Small Drinks //
    // ============ //

    public static final RegistryObject<Item> APPLE_DRINK = ITEMS.register("apple_drink",
            () -> new DrinkItem(new Item.Properties()
                    .food(new Food.Builder()
                            .hunger(2).saturation(2F)
                            .effect(() -> new EffectInstance(Effects.REGENERATION, 100, 0),1.0F)
                            .setAlwaysEdible().build()).group(DrinkTab.MKDRINKS)));

    public static final RegistryObject<Item> BEETROOT_DRINK = ITEMS.register("beetroot_drink",
            () -> new DrinkItem(new Item.Properties()
                    .food(new Food.Builder()
                            .hunger(2).saturation(2F)
                            .effect(() -> new EffectInstance(Effects.RESISTANCE, 300, 0),1.0F)
                            .setAlwaysEdible().build()).group(DrinkTab.MKDRINKS)));

    public static final RegistryObject<Item> SAPLING_DRINK = ITEMS.register("sapling_drink",
            () -> new DrinkItem(new Item.Properties()
                    .food(new Food.Builder()
                            .hunger(2).saturation(2F)
                            .effect(() -> new EffectInstance(Effects.INVISIBILITY,400,0),1.0F)
                            .setAlwaysEdible().build()).group(DrinkTab.MKDRINKS)));

    public static final RegistryObject<Item> CARROT_DRINK = ITEMS.register("carrot_drink",
            () -> new DrinkItem(new Item.Properties()
                    .food(new Food.Builder()
                            .hunger(2).saturation(2F)
                            .effect(() -> new EffectInstance(Effects.NIGHT_VISION, 400,0),1.0F)
                            .setAlwaysEdible().build()).group(DrinkTab.MKDRINKS)));

    public static final RegistryObject<Item> COCOA_DRINK = ITEMS.register("cocoa_drink",
            () -> new DrinkItem(new Item.Properties()
                    .food(new Food.Builder()
                            .hunger(2).saturation(2F)
                            .effect(() -> new EffectInstance(Effects.STRENGTH, 300, 0),1.0F)
                            .setAlwaysEdible().build()).group(DrinkTab.MKDRINKS)));

    public static final RegistryObject<Item> EGG_DRINK = ITEMS.register("egg_drink",
            () -> new DrinkItem(new Item.Properties()
                    .food(new Food.Builder()
                            .hunger(2).saturation(2F)
                            .effect(() -> new EffectInstance(Effects.SLOW_FALLING, 300,0),1.0F)
                            .setAlwaysEdible().build()).group(DrinkTab.MKDRINKS)));

    public static final RegistryObject<Item> KELP_DRINK = ITEMS.register("kelp_drink",
            () -> new DrinkItem(new Item.Properties()
                    .food(new Food.Builder()
                            .hunger(2).saturation(2F)
                            .effect(() -> new EffectInstance(Effects.DOLPHINS_GRACE, 300,0), 1.0F)
                            .setAlwaysEdible().build()).group(DrinkTab.MKDRINKS)));

    public static final RegistryObject<Item> MUSHROOM_DRINK = ITEMS.register("mushroom_drink",
            () -> new DrinkItem(new Item.Properties().food(
                    new Food.Builder()
                            .hunger(2).saturation(2F)
                            .effect(() -> new EffectInstance(Effects.LEVITATION, 100, 0),1.0F)
                            .setAlwaysEdible().build()).group(DrinkTab.MKDRINKS)));

    // ========== //
    // Big Drinks //
    // ========== //

    public static final RegistryObject<Item> SWEET_DRINK = ITEMS.register("sweet_drink",
            () -> new BigDrinkitem(new Item.Properties().food(
                    new Food.Builder()
                            .hunger(4).saturation(3F)
                            .effect(() -> new EffectInstance(Effects.JUMP_BOOST, 800,1), 1.0F)
                            .effect(() -> new EffectInstance(Effects.SLOW_FALLING,800,1),1.0F)
                            .setAlwaysEdible().build()).group(DrinkTab.MKDRINKS)));

    public static final RegistryObject<Item> VEGETABLE_DRINK = ITEMS.register("vegetable_drink",
            () -> new BigDrinkitem(new Item.Properties().food(
                    new Food.Builder()
                            .hunger(4).saturation(3F)
                            .effect(() -> new EffectInstance(Effects.HEALTH_BOOST,800,1),1.0F)
                            .effect(() -> new EffectInstance(Effects.SATURATION,800,1),1.0F)
                            .setAlwaysEdible().build()).group(DrinkTab.MKDRINKS)));

    public static final RegistryObject<Item> LEAFY_DRINK = ITEMS.register("leafy_drink",
            () -> new BigDrinkitem(new Item.Properties().food(
                    new Food.Builder()
                            .hunger(4).saturation(3F)
                            .effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE,800,1),1.0F)
                            .effect(() -> new EffectInstance(Effects.HERO_OF_THE_VILLAGE,800,1),1.0F)
                            .setAlwaysEdible().build()).group(DrinkTab.MKDRINKS)));

    public static final RegistryObject<Item> WEIRD_DRINK = ITEMS.register("weird_drink",
            () -> new BigDrinkitem(new Item.Properties().food(
                    new Food.Builder()
                            .hunger(4).saturation(3F)
                            .effect(() -> new EffectInstance(Effects.CONDUIT_POWER,800,1),1.0F)
                            .effect(() -> new EffectInstance(Effects.LUCK,800,1),1.0F)
                            .setAlwaysEdible().build()).group(DrinkTab.MKDRINKS)));
}
