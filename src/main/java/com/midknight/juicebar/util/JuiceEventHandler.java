package com.midknight.juicebar.util;

import com.midknight.juicebar.registry.JuiceEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class JuiceEventHandler {

    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event) {
        LivingEntity player = event.getEntityLiving();
        LivingEntity attacker = player.getRevengeTarget();

        if(player.isPotionActive(JuiceEffects.SPINY_EFFECT.get()) && !(attacker == null)) {
            attacker.attackEntityFrom(DamageSource.CACTUS, 2.0F);
        }
    }
}
