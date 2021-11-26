package com.midknight.mkdrinks.integration.jei;

import com.midknight.mkdrinks.MKDrinks;
import com.midknight.mkdrinks.data.recipes.CrucibleRecipe;
import com.midknight.mkdrinks.data.recipes.DrinksRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@JeiPlugin
public class MKDrinksJei implements IModPlugin {

    protected static IJeiRuntime runtime;

    public static IJeiRuntime getJeiRuntime() {
        return runtime;
    }

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(MKDrinks.MOD_ID,"jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CrucibleRecipeCategory(registration.getJeiHelpers().getGuiHelper()));

    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ClientWorld world = Minecraft.getInstance().world;
        RecipeManager recipeManager = world.getRecipeManager();

        List<IRecipe<?>> crucibleRecipes = recipeManager.getRecipes().stream()
            .filter(r -> r.getType() == CrucibleRecipe.TYPE).collect(Collectors.toList());

        registration.addRecipes(crucibleRecipes, DrinksRecipes.CRUCIBLE_RECIPE.getId());
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        MKDrinksJei.runtime = jeiRuntime;
    }
}
