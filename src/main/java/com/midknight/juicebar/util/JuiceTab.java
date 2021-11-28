package com.midknight.juicebar.util;

import com.midknight.juicebar.registry.JuiceMiscItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class JuiceTab {

    public static final ItemGroup JUICEBAR = new ItemGroup("juicebarCreativeTab") {

        @Override
        public ItemStack createIcon() {
            return new ItemStack(JuiceMiscItems.JUICE_BOTTLE.get());
        }
    };
}
