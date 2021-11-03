package com.midknight.mkdrinks.data.recipes;

import com.midknight.mkdrinks.MKDrinks;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MKRecipeTypes {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MKDrinks.MOD_ID);

    public static final RegistryObject<CrucibleRecipe.Serializer> CRUCIBLE_SERIALIZER =
            RECIPE_SERIALIZER.register("crucible",CrucibleRecipe.Serializer::new);

    public static IRecipeType<CrucibleRecipe> CRUCIBLE_RECIPE =
            new CrucibleRecipe.CrucibleRecipeType();

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZER.register(eventBus);
        Registry.register(Registry.RECIPE_TYPE, CrucibleRecipe.TYPE_ID, CRUCIBLE_RECIPE);
    }
}
