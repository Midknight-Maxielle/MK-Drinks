package com.midknight.juicebar.registry;

import com.midknight.juicebar.Juicebar;
import com.midknight.juicebar.item.AntienervationJuice;
import com.midknight.juicebar.item.AntitoxinJuice;
import com.midknight.juicebar.item.AntiwitherJuice;
import com.midknight.juicebar.item.JuiceItem;
import com.midknight.juicebar.util.JuiceTab;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryFoodItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Juicebar.MOD_ID);

    public static final RegistryObject<Item> JUICE_APPLE = ITEMS.register("juice_apple",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(8F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            )
    );

    public static final RegistryObject<Item> JUICE_BAMBOO = ITEMS.register("juice_bamboo",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.JUMP, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            )
    );

    public static final RegistryObject<Item> JUICE_BEETROOT = ITEMS.register("juice_beetroot",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.HERO_OF_THE_VILLAGE, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            )
    );

    public static final RegistryObject<Item> JUICE_BERRY = ITEMS.register("juice_berry",
            () -> new AntitoxinJuice(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            )
    );

    public static final RegistryObject<Item> JUICE_CACTUS = ITEMS.register("juice_cactus",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    RegistryMobEffects.SPINY_EFFECT.get(), 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            )
    );

    public static final RegistryObject<Item> JUICE_CARROT = ITEMS.register("juice_carrot",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.NIGHT_VISION, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            )
    );

    public static final RegistryObject<Item> JUICE_COCOA = ITEMS.register("juice_cocoa",
            () -> new AntienervationJuice(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            )
    );

    public static final RegistryObject<Item> JUICE_EGG = ITEMS.register("juice_egg",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.SLOW_FALLING, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            )
    );

    public static final RegistryObject<Item> JUICE_KELP = ITEMS.register("juice_kelp",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.WATER_BREATHING, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            )
    );

    public static final RegistryObject<Item> JUICE_MELON = ITEMS.register("juice_melon",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.REGENERATION, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            )
    );

    public static final RegistryObject<Item> JUICE_MUSHROOM = ITEMS.register("juice_mushroom",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.LEVITATION, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            )
    );

    public static final RegistryObject<Item> JUICE_PICKLE = ITEMS.register("juice_pickle",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.DOLPHINS_GRACE, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            )
    );

    public static final RegistryObject<Item> JUICE_POTATO = ITEMS.register("juice_potato",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.DAMAGE_RESISTANCE, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            )
    );

    public static final RegistryObject<Item> JUICE_PUMPKIN = ITEMS.register("juice_pumpkin",
            () -> new AntiwitherJuice(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            )
    );

    public static final RegistryObject<Item> JUICE_SAPLING = ITEMS.register("juice_sapling",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.DAMAGE_BOOST, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            )
    );

    public static final RegistryObject<Item> JUICE_SUGAR = ITEMS.register("juice_sugar",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.MOVEMENT_SPEED, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            )
    );

    public static final RegistryObject<Item> JUICE_WHEAT = ITEMS.register("juice_wheat",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.FIRE_RESISTANCE, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            )
    );
}
