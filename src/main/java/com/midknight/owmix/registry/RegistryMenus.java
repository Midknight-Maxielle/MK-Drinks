package com.midknight.owmix.registry;

import com.midknight.owmix.OverworldMixology;
import com.midknight.owmix.menu.CrucibleMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryMenus {

    public static DeferredRegister<MenuType<?>> CONTAINERS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, OverworldMixology.MOD_ID);

    public static final RegistryObject<MenuType<CrucibleMenu>> CRUCIBLE_CONTAINER =
        CONTAINERS.register("crucible", () -> IForgeMenuType.create(CrucibleMenu::new));
}