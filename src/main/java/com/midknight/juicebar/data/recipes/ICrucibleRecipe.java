package com.midknight.juicebar.data.recipes;

import com.midknight.juicebar.Juicebar;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public interface ICrucibleRecipe extends IRecipe<IInventory> {

    IRecipeType<CrucibleRecipe> TYPE = IRecipeType.register(Juicebar.MOD_ID + ":crucible");
    ResourceLocation TYPE_ID = new ResourceLocation(Juicebar.MOD_ID, "crucible");

    @Override @Nonnull
    default IRecipeType<?> getType() {
        return CrucibleRecipe.TYPE;
    }

    @Override
    default boolean canCraftInDimensions(int width, int height) { return true; }

    @Override
    default boolean isSpecial() { return true; }
}
