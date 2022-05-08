package com.midknight.owmix.registry;

import com.midknight.owmix.OverworldMixology;
import com.midknight.owmix.effect.SpinyEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryMobEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, OverworldMixology.MOD_ID);

    public static RegistryObject<MobEffect> SPINY_EFFECT = EFFECTS.register("spiny_effect",
            () -> new SpinyEffect(MobEffectCategory.BENEFICIAL,5870627));
}
