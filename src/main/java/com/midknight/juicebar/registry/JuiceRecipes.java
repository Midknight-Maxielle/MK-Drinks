package com.midknight.juicebar.registry;

import com.midknight.juicebar.Juicebar;
import com.midknight.juicebar.data.recipes.CrucibleRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.util.NonNullLazy;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class JuiceRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPES =
        DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Juicebar.MOD_ID);

    public static final NonNullLazy<RecipeType<CrucibleRecipe>> CRUCIBLE_RECIPE_TYPE =
        NonNullLazy.of(() -> RecipeType.register("juicebar:crucible"));

    public static final RegistryObject<RecipeSerializer<CrucibleRecipe>> CRUCIBLE_RECIPE =
        RECIPES.register("crucible", () -> CrucibleRecipe.SERIALIZER);

}
