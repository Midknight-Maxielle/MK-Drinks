package com.midknight.owmix.registry;

import com.midknight.owmix.OverworldMixology;
import com.midknight.owmix.data.recipes.CrucibleRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.util.NonNullLazy;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPES =
        DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, OverworldMixology.MOD_ID);

    public static final NonNullLazy<RecipeType<CrucibleRecipe>> CRUCIBLE_RECIPE_TYPE =
        NonNullLazy.of(() -> RecipeType.register("juicebar:crucible"));

    public static final RegistryObject<RecipeSerializer<CrucibleRecipe>> CRUCIBLE_RECIPE =
        RECIPES.register("crucible", () -> CrucibleRecipe.SERIALIZER);

}
