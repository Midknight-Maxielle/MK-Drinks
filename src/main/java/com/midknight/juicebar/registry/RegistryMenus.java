package com.midknight.juicebar.registry;

import com.midknight.juicebar.Juicebar;
import com.midknight.juicebar.menu.CrucibleMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryMenus {

    public static DeferredRegister<MenuType<?>> CONTAINERS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, Juicebar.MOD_ID);

    public static final RegistryObject<MenuType<CrucibleMenu>> CRUCIBLE_CONTAINER =
        CONTAINERS.register("crucible", () -> IForgeMenuType.create(CrucibleMenu::new));
}