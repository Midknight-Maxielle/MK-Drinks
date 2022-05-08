package com.midknight.owmix.data.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.midknight.owmix.registry.RegistryBlocks;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
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
    public boolean matches(Container inv, Level worldIn) {
        return recipeItems.get(0).test(inv.getItem(0));
    }

    public NonNullList<Ingredient> getIngredients() { return recipeItems; }

    @Override
    public ItemStack assemble(Container inv) {
        return output;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ItemStack getToastSymbol() { return new ItemStack(RegistryBlocks.CRUCIBLE.get()); }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    public static class CrucibleRecipeType implements RecipeType<CrucibleRecipe> {
        @Override
        public String toString() { return CrucibleRecipe.TYPE_ID.toString(); }
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<CrucibleRecipe> {

        @Override
        public CrucibleRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

            final NonNullList<Ingredient> inputItem = readInput(GsonHelper.getAsJsonArray(json, "ingredients"));
            if (inputItem.isEmpty()) {
                throw new JsonParseException("Input item missing for crucible recipe.");
            } else if (inputItem.size() > 1) {
                throw new JsonParseException("Too many input items for crucible recipe. Only 1 is required.");
            } else {
                final ItemStack outputItem = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "output"), true);
                return new CrucibleRecipe(recipeId, outputItem, inputItem);
            }
        }

        private static NonNullList<Ingredient> readInput(JsonArray inputArray) {
            NonNullList<Ingredient> NNL = NonNullList.create();

            for (int i = 0; i < inputArray.size(); i++) {
                Ingredient input = Ingredient.fromJson(inputArray.get(i));
                if (!input.isEmpty()) {
                    NNL.add(input);
                }
            }
            return NNL;
        }

        @Nullable
        @Override
        public CrucibleRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {

            int i = buffer.readVarInt();
            NonNullList<Ingredient> inputItem = NonNullList.withSize(i, Ingredient.EMPTY);

            for (int j = 0; j < inputItem.size(); j++) {
                inputItem.set(j, Ingredient.fromNetwork(buffer));
            }

            ItemStack outputItem = buffer.readItem();
            return new CrucibleRecipe(recipeId, outputItem, inputItem);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, CrucibleRecipe recipe) {

            buffer.writeVarInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItem(recipe.output);

        }
    }
}
