package com.midknight.mkdrinks.integration.jei;

import com.midknight.mkdrinks.MKDrinks;
import com.midknight.mkdrinks.block.DrinkBlocks;
import com.midknight.mkdrinks.data.recipes.CrucibleRecipe;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CrucibleRecipeCategory implements IRecipeCategory<CrucibleRecipe> {

    public final static ResourceLocation UID = new ResourceLocation(MKDrinks.MOD_ID, "crucible");
    public final static ResourceLocation TEXTURE = new ResourceLocation(MKDrinks.MOD_ID, "textures/gui/crucible_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable heat;

    public CrucibleRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 5, 5, 166, 75);
        this.icon = helper.createDrawableIngredient(new ItemStack(DrinkBlocks.CRUCIBLE.get()));
        this.heat = helper.createDrawable(TEXTURE, 176, 13, 16, 17);
    }
    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends CrucibleRecipe> getRecipeClass() {
        return CrucibleRecipe.class;
    }

    @Override
    public String getTitle() {
        return DrinkBlocks.CRUCIBLE.get().getTranslatedName().getString();
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(CrucibleRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());

    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, CrucibleRecipe recipe, IIngredients ingredients) {

        recipeLayout.getItemStacks().init(0,true,47,37);
        recipeLayout.getItemStacks().init(1,false, 102,37);
        recipeLayout.getItemStacks().set(ingredients);

    }

    @Override
    public void draw(CrucibleRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {

        this.heat.draw(matrixStack, 74,57);

        IRecipeCategory.super.draw(recipe, matrixStack, mouseX, mouseY);
    }
}
