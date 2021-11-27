package com.midknight.mkdrinks;

import com.midknight.mkdrinks.block.DrinkBlocks;
import com.midknight.mkdrinks.container.DrinkContainers;
import com.midknight.mkdrinks.data.recipes.DrinkRecipes;
import com.midknight.mkdrinks.item.DrinkEquipment;
import com.midknight.mkdrinks.item.DrinkItems;
import com.midknight.mkdrinks.item.DrinkFoodItems;
import com.midknight.mkdrinks.screen.CrucibleScreen;
import com.midknight.mkdrinks.tileentity.DrinkTiles;
import com.midknight.mkdrinks.util.MKItemModelProperties;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MKDrinks.MOD_ID)
public class MKDrinks {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "mkdrinks";
    public static MKDrinks INSTANCE;

    public MKDrinks() {
        INSTANCE = this;
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        DrinkItems.ITEMS.register(eventBus);
        DrinkFoodItems.ITEMS.register(eventBus);
        DrinkEquipment.ITEMS.register(eventBus);
        DrinkBlocks.BLOCKS.register(eventBus);
        DrinkContainers.CONTAINERS.register(eventBus);
        DrinkTiles.TILES.register(eventBus);
        DrinkRecipes.RECIPES.register(eventBus);

        eventBus.addListener(this::ClientSetup);
        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.info("MKDrinks - fully loaded!");
    }

    private void ClientSetup (final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {

            ScreenManager.registerFactory(DrinkContainers.CRUCIBLE_CONTAINER.get(), CrucibleScreen::new);
            RenderTypeLookup.setRenderLayer(DrinkBlocks.CRUCIBLE.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(DrinkBlocks.BOTTLE_GLASS.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(DrinkBlocks.BOTTLE_GLASS_PANE.get(), RenderType.getCutout());
            MKItemModelProperties.makeBow(DrinkEquipment.DRINKMETAL_BOW.get());
        });
    }
}
