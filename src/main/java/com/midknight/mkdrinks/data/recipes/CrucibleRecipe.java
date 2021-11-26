package com.midknight.mkdrinks.data.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.midknight.mkdrinks.MKDrinks;
import com.midknight.mkdrinks.block.MKBlocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class CrucibleRecipe implements ICrucibleRecipe{

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public static Serializer SERIALIZER = new Serializer();


    public CrucibleRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        if(recipeItems.get(0).test(inv.getStackInSlot(0))) {
            return true;
        }
        return false;
    }

    public NonNullList<Ingredient> getIngredients() { return recipeItems; }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return output;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output.copy();
    }

    @Override
    public ItemStack getIcon() { return new ItemStack(MKBlocks.CRUCIBLE.get()); }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    public static class CrucibleRecipeType implements IRecipeType<CrucibleRecipe> {
        @Override
        public String toString() { return CrucibleRecipe.TYPE_ID.toString(); }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<CrucibleRecipe> {

        @Override
        public CrucibleRecipe read(ResourceLocation recipeId, JsonObject json) {

            final NonNullList<Ingredient> inputItem = readInput(JSONUtils.getJsonArray(json, "ingredients"));
            if (inputItem.isEmpty()) {
                throw new JsonParseException("Input item missing for crucible recipe.");
            } else if (inputItem.size() > 1) {
                throw new JsonParseException("Too many input items for crucible recipe. Only 1 is required.");
            } else {
                final ItemStack outputItem = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
                return new CrucibleRecipe(recipeId, outputItem, inputItem);
            }
        }

        private static NonNullList<Ingredient> readInput(JsonArray inputArray) {
            NonNullList<Ingredient> NNL = NonNullList.create();

            for (int i = 0; i < inputArray.size(); i++) {
                Ingredient input = Ingredient.deserialize(inputArray.get(i));
                if (!input.hasNoMatchingItems()) {
                    NNL.add(input);
                }
            }
            return NNL;
        }

        @Nullable
        @Override
        public CrucibleRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {

            int i = buffer.readVarInt();
            NonNullList<Ingredient> inputItem = NonNullList.withSize(i, Ingredient.EMPTY);

            for (int j = 0; j < inputItem.size(); j++) {
                inputItem.set(j, Ingredient.read(buffer));
            }

            ItemStack outputItem = buffer.readItemStack();
            return new CrucibleRecipe(recipeId, outputItem, inputItem);
        }

        @Override
        public void write(PacketBuffer buffer, CrucibleRecipe recipe) {

            buffer.writeVarInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.write(buffer);
            }

            buffer.writeItemStack(recipe.output);

        }
    }
}
