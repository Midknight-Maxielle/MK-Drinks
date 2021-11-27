package com.midknight.mkdrinks.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class DrinkTab {

    public static final ItemGroup MKDRINKS = new ItemGroup("mkCreativeTab") {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(DrinkItems.DRINK_BOTTLE.get());
        }
    };
}
