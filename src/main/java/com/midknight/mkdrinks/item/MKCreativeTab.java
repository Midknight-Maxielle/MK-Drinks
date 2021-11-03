package com.midknight.mkdrinks.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class MKCreativeTab {

    public static final ItemGroup MKDRINKS = new ItemGroup("mkCreativeTab") {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(MKMiscItems.DRINK_BOTTLE.get());
        }
    };
}
