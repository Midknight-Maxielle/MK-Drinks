package com.midknight.juicebar.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.ParametersAreNonnullByDefault;


public class SpinyEffect extends MobEffect {

    public SpinyEffect(MobEffectCategory typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @ParametersAreNonnullByDefault
    public void applyEffectTick(LivingEntity entity, int amplifier) {}

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
       return true;
    }
}
