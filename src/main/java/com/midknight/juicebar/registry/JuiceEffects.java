package com.midknight.juicebar.registry;

import com.midknight.juicebar.Juicebar;
import com.midknight.juicebar.effect.SpinyEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JuiceEffects {

    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Juicebar.MOD_ID);

    public static RegistryObject<Effect> SPINY_EFFECT = EFFECTS.register("spiny_effect",
            () -> new SpinyEffect(EffectType.BENEFICIAL,5870627));
}
