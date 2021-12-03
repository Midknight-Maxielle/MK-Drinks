package com.midknight.juicebar.item;

import com.midknight.juicebar.registry.JuiceMiscItems;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class JuiceItem extends Item {

    // Constructor //
    public JuiceItem(Properties pProperties) {
        super(pProperties);
    }

    // Sets the animation and sound events to use drink FX. //
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }
    public SoundEvent getDrinkSound() { return SoundEvents.GENERIC_DRINK; }
    public SoundEvent getEatSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        super.finishUsingItem(stack, worldIn, entityLiving);

        if (stack.isEmpty()) {
            return new ItemStack(JuiceMiscItems.JUICE_BOTTLE.get());
        } else {
            if (entityLiving instanceof PlayerEntity && !((PlayerEntity)entityLiving).abilities.instabuild) {
                ItemStack itemstack = new ItemStack(JuiceMiscItems.JUICE_BOTTLE.get());
                PlayerEntity playerentity = (PlayerEntity)entityLiving;
                if (!playerentity.inventory.add(itemstack)) {
                    playerentity.drop(itemstack, false);
                }
            }
            return stack;
        }
    }
}
