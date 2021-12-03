package com.midknight.juicebar.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import javax.annotation.ParametersAreNonnullByDefault;


public class SpinyEffect extends Effect {

    public SpinyEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @ParametersAreNonnullByDefault
    public void applyEffectTick(LivingEntity entity, int amplifier) {}

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
       return true;
    }
}
