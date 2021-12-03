package com.midknight.juicebar.item;

import com.midknight.juicebar.registry.JuiceMiscItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class AntiwitherJuice extends JuiceItem {

    // Constructor //
    public AntiwitherJuice(Properties pProperties) {
        super(pProperties);
    }

    // Sets the animation and sound events to use drink FX. //
    public UseAnim getUseAction(ItemStack stack) {
        return UseAnim.DRINK;
    }
    public SoundEvent getDrinkSound() {
        return SoundEvents.GENERIC_DRINK;
    }
    public SoundEvent getEatSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
        super.finishUsingItem(stack, worldIn, entityLiving);
        if (!worldIn.isClientSide()) {
            entityLiving.removeEffect(MobEffects.WITHER);
        }
        if (stack.isEmpty()) {
            return new ItemStack(JuiceMiscItems.JUICE_BOTTLE.get());
        } else {
            if (entityLiving instanceof Player && !((Player)entityLiving).getAbilities().instabuild) {
                ItemStack itemstack = new ItemStack(JuiceMiscItems.JUICE_BOTTLE.get());
                Player playerentity = (Player)entityLiving;
                if (!playerentity.getInventory().add(itemstack)) {
                    playerentity.drop(itemstack, false);
                }
            }
            return stack;
        }
    }
}
