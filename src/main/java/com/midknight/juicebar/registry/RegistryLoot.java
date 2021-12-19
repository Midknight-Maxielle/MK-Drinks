package com.midknight.juicebar.registry;

import com.midknight.juicebar.Juicebar;
import com.midknight.juicebar.util.LootInjection;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryLoot {

    public static DeferredRegister<GlobalLootModifierSerializer<?>> GLMS = DeferredRegister.create(
            ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, Juicebar.MOD_ID);

    public static RegistryObject<GlobalLootModifierSerializer<LootInjection.LootInjectionModifier>>
            LOOT_INJECTION = GLMS.register("loot_injection", LootInjection.InjectionSerializer::new);

    public static void registerGLMS() {
        GLMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
