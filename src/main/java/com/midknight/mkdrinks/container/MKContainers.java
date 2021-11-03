package com.midknight.mkdrinks.container;

import com.midknight.mkdrinks.MKDrinks;
import com.midknight.mkdrinks.tileentity.CrucibleTile;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MKContainers {

    public static DeferredRegister<ContainerType<?>> CONTAINERS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, MKDrinks.MOD_ID);

    public static final RegistryObject<ContainerType<CrucibleContainer>> CRUCIBLE_CONTAINER =
            CONTAINERS.register("crucible_container",
                    () -> IForgeContainerType.create((windowId, inv, data) -> {

                        BlockPos pos = data.readBlockPos();
                        CrucibleTile crucibleTile = (CrucibleTile) inv.player.getEntityWorld().getTileEntity(pos);
                        IIntArray dataArray = crucibleTile.getCrucibleData();

                        return new CrucibleContainer(
                                windowId,
                                inv.player.getEntityWorld(),
                                pos,
                                inv,
                                inv.player,
                                dataArray,
                                crucibleTile);
                    }

            ));

    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }
}