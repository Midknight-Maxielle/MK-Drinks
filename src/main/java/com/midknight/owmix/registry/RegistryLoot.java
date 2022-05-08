package com.midknight.owmix.registry;

import com.midknight.owmix.OverworldMixology;
import com.midknight.owmix.util.LootInjection;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryLoot {

    public static DeferredRegister<GlobalLootModifierSerializer<?>> GLMS = DeferredRegister.create(
             ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, OverworldMixology.MOD_ID);

    public static RegistryObject<GlobalLootModifierSerializer<LootInjection.LootInjectionModifier>>
            LOOT_INJECTION = GLMS.register("loot_injection", LootInjection.InjectionSerializer::new);

    public static void registerGLMS() {
        GLMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
