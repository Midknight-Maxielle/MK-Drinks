package com.midknight.owmix.util;

import com.midknight.owmix.registry.RegistryMobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class JuiceEventHandler {

    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event) {
        LivingEntity player = event.getEntityLiving();
        LivingEntity attacker = player.getLastHurtByMob();

        if(player.hasEffect(RegistryMobEffects.SPINY_EFFECT.get()) && !(attacker == null)) {
            attacker.hurt(DamageSource.CACTUS, 2.0F);
        }
    }
}
