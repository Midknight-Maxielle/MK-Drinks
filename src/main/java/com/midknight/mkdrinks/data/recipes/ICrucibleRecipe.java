package com.midknight.mkdrinks.data.recipes;

import com.midknight.mkdrinks.MKDrinks;
import com.sun.org.apache.xml.internal.serializer.Serializer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public interface ICrucibleRecipe extends IRecipe<IInventory> {

    public static IRecipeType<CrucibleRecipe> TYPE = IRecipeType.register(MKDrinks.MOD_ID + ":crucible");
    ResourceLocation TYPE_ID = new ResourceLocation(MKDrinks.MOD_ID, "crucible");

    @Override
    default IRecipeType<?> getType() {
        return CrucibleRecipe.TYPE;
    }

    @Override
    default boolean canFit(int width, int height) { return true; }

    @Override
    default boolean isDynamic() { return true; }
}
