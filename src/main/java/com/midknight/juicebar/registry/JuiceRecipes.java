package com.midknight.juicebar.registry;

import com.midknight.juicebar.Juicebar;
import com.midknight.juicebar.data.recipes.CrucibleRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.common.util.NonNullLazy;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JuiceRecipes {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPES =
        DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Juicebar.MOD_ID);

    public static final NonNullLazy<IRecipeType<CrucibleRecipe>> CRUCIBLE_RECIPE_TYPE =
        NonNullLazy.of(() -> IRecipeType.register("juicebar:crucible"));

    public static final RegistryObject<IRecipeSerializer<CrucibleRecipe>> CRUCIBLE_RECIPE =
        RECIPES.register("crucible", () -> CrucibleRecipe.SERIALIZER);

}
