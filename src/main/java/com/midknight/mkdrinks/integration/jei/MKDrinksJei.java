package com.midknight.mkdrinks.integration.jei;

import com.midknight.mkdrinks.MKDrinks;
import com.midknight.mkdrinks.data.recipes.CrucibleRecipe;
import com.midknight.mkdrinks.integration.jei.CrucibleRecipeCategory;
import com.midknight.mkdrinks.data.recipes.MKRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;
import java.util.stream.Collectors;

@JeiPlugin
public class MKDrinksJei implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(MKDrinks.MOD_ID,"jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new CrucibleRecipeCategory(registration.getJeiHelpers().getGuiHelper()));

    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().world).getRecipeManager();
        registration.addRecipes(rm.getRecipesForType(MKRecipeTypes.CRUCIBLE_RECIPE).stream()
                .filter(r -> r instanceof CrucibleRecipe).collect(Collectors.toList()),
                CrucibleRecipeCategory.UID);
    }
}
