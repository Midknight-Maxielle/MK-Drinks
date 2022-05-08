package com.midknight.owmix.integration.jei;

import com.midknight.owmix.OverworldMixology;
import com.midknight.owmix.registry.RegistryBlocks;
import com.midknight.owmix.data.recipes.CrucibleRecipe;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;


@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CrucibleRecipeCategory implements IRecipeCategory<CrucibleRecipe> {

    public final static ResourceLocation UID = new ResourceLocation(OverworldMixology.MOD_ID, "crucible");
    public final static ResourceLocation TEXTURE = new ResourceLocation(OverworldMixology.MOD_ID, "textures/gui/crucible_gui.png");

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawable heat;

    public CrucibleRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 5, 5, 166, 75);
        this.icon = helper.createDrawableIngredient(new ItemStack(RegistryBlocks.CRUCIBLE.get()));
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
    public Component getTitle() {
        return RegistryBlocks.CRUCIBLE.get().getName();
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
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());

    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, CrucibleRecipe recipe, IIngredients ingredients) {

        recipeLayout.getItemStacks().init(0,true,47,37);
        recipeLayout.getItemStacks().init(1,false, 102,37);
        recipeLayout.getItemStacks().set(ingredients);

    }

    @Override
    public void draw(CrucibleRecipe recipe, PoseStack matrixStack, double mouseX, double mouseY) {

        this.heat.draw(matrixStack, 74,57);

        IRecipeCategory.super.draw(recipe, matrixStack, mouseX, mouseY);
    }
}
