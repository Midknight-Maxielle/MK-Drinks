package com.midknight.mkdrinks.data.recipes;

import com.midknight.mkdrinks.MKDrinks;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.util.NonNullLazy;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DrinksRecipes {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPES =
        DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MKDrinks.MOD_ID);

    public static final NonNullLazy<IRecipeType<CrucibleRecipe>> CRUCIBLE_RECIPE_TYPE =
        NonNullLazy.of(() -> IRecipeType.register("mkdrinks:crucible"));

    public static final RegistryObject<IRecipeSerializer<CrucibleRecipe>> CRUCIBLE_RECIPE =
        RECIPES.register("crucible", () -> CrucibleRecipe.SERIALIZER);

}
