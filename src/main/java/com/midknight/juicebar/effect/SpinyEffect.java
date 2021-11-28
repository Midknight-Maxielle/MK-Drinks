package com.midknight.juicebar.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;


public class SpinyEffect extends Effect {

    public SpinyEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    public void performEffect(LivingEntity entity, int amplifier) {

    }

    @Override
    public boolean isReady(int duration, int amplifier) {
       return true;
    }
}
