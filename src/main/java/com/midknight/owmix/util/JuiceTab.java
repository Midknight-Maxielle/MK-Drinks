package com.midknight.owmix.util;

import com.midknight.owmix.registry.RegistryMiscItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class JuiceTab {

    public static final CreativeModeTab JUICEBAR = new CreativeModeTab("owmixTab") {

        @Override @Nonnull
        public ItemStack makeIcon() {
            return new ItemStack(RegistryMiscItems.JUICE_BOTTLE.get());
        }
    };
}
