package com.midknight.juicebar.registry;

import com.midknight.juicebar.Juicebar;
import com.midknight.juicebar.container.CrucibleContainer;
import com.midknight.juicebar.tileentity.CrucibleTile;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JuiceContainers {

    public static DeferredRegister<ContainerType<?>> CONTAINERS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, Juicebar.MOD_ID);

    public static final RegistryObject<ContainerType<CrucibleContainer>> CRUCIBLE_CONTAINER =
            CONTAINERS.register("crucible_container",
                    () -> IForgeContainerType.create((windowId, inv, data) -> {

                        BlockPos pos = data.readBlockPos();
                        CrucibleTile crucibleTile = (CrucibleTile) inv.player.getCommandSenderWorld().getBlockEntity(pos);
                        IIntArray dataArray = crucibleTile.getCrucibleData();

                        return new CrucibleContainer(
                                windowId,
                                inv.player.getCommandSenderWorld(),
                                pos,
                                inv,
                                inv.player,
                                dataArray,
                                crucibleTile);
                    }

            ));

}