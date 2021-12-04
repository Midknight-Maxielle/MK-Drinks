package com.midknight.juicebar.registry;

import com.midknight.juicebar.Juicebar;
import com.midknight.juicebar.container.CrucibleContainer;
import com.midknight.juicebar.blockentity.CrucibleBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JuiceContainers {

    public static DeferredRegister<MenuType<?>> CONTAINERS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, Juicebar.MOD_ID);

    public static final RegistryObject<MenuType<CrucibleContainer>> CRUCIBLE_CONTAINER =
            CONTAINERS.register("crucible_container",
                    () -> IForgeContainerType.create((windowId, inv, data) -> {

                        BlockPos pos = data.readBlockPos();
                        CrucibleBlockEntity crucibleBlockEntity = (CrucibleBlockEntity) inv.player.getCommandSenderWorld().getBlockEntity(pos);
                        ContainerData dataArray = crucibleBlockEntity.getCrucibleData();

                        return new CrucibleContainer(
                                windowId,
                                inv.player.getCommandSenderWorld(),
                                pos,
                                inv,
                                inv.player,
                                dataArray,
                                crucibleBlockEntity);
                    }

            ));

}