package com.midknight.juicebar.registry;

import com.midknight.juicebar.Juicebar;
import com.midknight.juicebar.effect.SpinyEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JuiceEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Juicebar.MOD_ID);

    public static RegistryObject<MobEffect> SPINY_EFFECT = EFFECTS.register("spiny_effect",
            () -> new SpinyEffect(MobEffectCategory.BENEFICIAL,5870627));
}
