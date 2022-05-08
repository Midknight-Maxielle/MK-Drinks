package com.midknight.owmix.registry;

import com.midknight.owmix.OverworldMixology;
import com.midknight.owmix.item.AntienervationJuice;
import com.midknight.owmix.item.AntitoxinJuice;
import com.midknight.owmix.item.AntiwitherJuice;
import com.midknight.owmix.item.JuiceItem;
import com.midknight.owmix.util.JuiceTab;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class RegistryFoodItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, OverworldMixology.MOD_ID);

    public static final RegistryObject<Item> JUICE_APPLE = ITEMS.register("juice_apple",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(8F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            ) {
                @Override
                @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.juicebar.juice_apple"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
    });

    public static final RegistryObject<Item> JUICE_BAMBOO = ITEMS.register("juice_bamboo",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.JUMP, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            ) {
                @Override
                @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.juicebar.juice_bamboo"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
    });

    public static final RegistryObject<Item> JUICE_BEETROOT = ITEMS.register("juice_beetroot",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.HERO_OF_THE_VILLAGE, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            ) {
                @Override
                @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.juicebar.juice_beetroot"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
    });

    public static final RegistryObject<Item> JUICE_BERRY = ITEMS.register("juice_berry",
            () -> new AntitoxinJuice(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            ) {
                @Override
                @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.juicebar.juice_berry"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
    });

    public static final RegistryObject<Item> JUICE_CACTUS = ITEMS.register("juice_cactus",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    RegistryMobEffects.SPINY_EFFECT.get(), 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            ) {
                @Override
                @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.juicebar.juice_cactus"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
    });

    public static final RegistryObject<Item> JUICE_CARROT = ITEMS.register("juice_carrot",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.NIGHT_VISION, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            ) {
                @Override
                @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.juicebar.juice_carrot"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            });

    public static final RegistryObject<Item> JUICE_COCOA = ITEMS.register("juice_cocoa",
            () -> new AntienervationJuice(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            ) {
                @Override
                @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.juicebar.juice_cocoa"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            });

    public static final RegistryObject<Item> JUICE_EGG = ITEMS.register("juice_egg",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.SLOW_FALLING, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            ) {
                @Override
                @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.juicebar.juice_egg"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            });

    public static final RegistryObject<Item> JUICE_KELP = ITEMS.register("juice_kelp",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.WATER_BREATHING, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            ) {
                @Override
                @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.juicebar.juice_kelp"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            });

    public static final RegistryObject<Item> JUICE_MELON = ITEMS.register("juice_melon",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.REGENERATION, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            ) {
                @Override
                @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.juicebar.juice_melon"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            });

    public static final RegistryObject<Item> JUICE_MUSHROOM = ITEMS.register("juice_mushroom",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.LEVITATION, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            ) {
                @Override
                @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.juicebar.juice_mushroom"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            });

    public static final RegistryObject<Item> JUICE_PICKLE = ITEMS.register("juice_pickle",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.DOLPHINS_GRACE, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            ) {
                @Override
                @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.juicebar.juice_pickle"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            });

    public static final RegistryObject<Item> JUICE_POTATO = ITEMS.register("juice_potato",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.DAMAGE_RESISTANCE, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            ) {
                @Override
                @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.juicebar.juice_potato"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            });

    public static final RegistryObject<Item> JUICE_PUMPKIN = ITEMS.register("juice_pumpkin",
            () -> new AntiwitherJuice(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            ) {
                @Override
                @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.juicebar.juice_pumpkin"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            });

    public static final RegistryObject<Item> JUICE_SAPLING = ITEMS.register("juice_sapling",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.DAMAGE_BOOST, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            ) {
                @Override
                @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.juicebar.juice_sapling"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            });

    public static final RegistryObject<Item> JUICE_SUGAR = ITEMS.register("juice_sugar",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.MOVEMENT_SPEED, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            ) {
                @Override
                @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.juicebar.juice_sugar"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            });

    public static final RegistryObject<Item> JUICE_WHEAT = ITEMS.register("juice_wheat",
            () -> new JuiceItem(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2).saturationMod(4F)
                            .effect(() -> new MobEffectInstance(
                                    MobEffects.FIRE_RESISTANCE, 600, 0), 1.0F)
                            .alwaysEat().build())
                    .tab(JuiceTab.JUICEBAR)
            ) {
                @Override
                @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.juicebar.juice_wheat"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            });
}
