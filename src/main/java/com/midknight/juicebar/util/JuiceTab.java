package com.midknight.juicebar.util;

import com.midknight.juicebar.registry.JuiceMiscItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class JuiceTab {

    public static final CreativeModeTab JUICEBAR = new CreativeModeTab("juicebarCreativeTab") {

        @Override @Nonnull
        public ItemStack makeIcon() {
            return new ItemStack(JuiceMiscItems.JUICE_BOTTLE.get());
        }
    };
}
