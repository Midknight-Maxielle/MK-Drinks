package com.midknight.juicebar.data.recipes;

import com.midknight.juicebar.Juicebar;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nonnull;

public interface ICrucibleRecipe extends Recipe<Container> {

    RecipeType<CrucibleRecipe> TYPE = RecipeType.register(Juicebar.MOD_ID + ":crucible");
    ResourceLocation TYPE_ID = new ResourceLocation(Juicebar.MOD_ID, "crucible");

    @Override @Nonnull
    default RecipeType<?> getType() {
        return CrucibleRecipe.TYPE;
    }

    @Override
    default boolean canCraftInDimensions(int width, int height) { return true; }

    @Override
    default boolean isSpecial() { return true; }
}
