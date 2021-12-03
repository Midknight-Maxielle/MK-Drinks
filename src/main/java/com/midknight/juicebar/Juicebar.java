package com.midknight.juicebar;

import com.midknight.juicebar.registry.JuiceBlocks;
import com.midknight.juicebar.registry.JuiceContainers;
import com.midknight.juicebar.registry.JuiceRecipes;
import com.midknight.juicebar.registry.JuiceEffects;
import com.midknight.juicebar.registry.JuiceEquipment;
import com.midknight.juicebar.registry.JuiceMiscItems;
import com.midknight.juicebar.registry.JuiceFoods;
import com.midknight.juicebar.screen.CrucibleScreen;
import com.midknight.juicebar.registry.JuiceTiles;
import com.midknight.juicebar.util.JuiceEventHandler;
import com.midknight.juicebar.util.JuiceItemModelProperties;

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

@Mod(Juicebar.MOD_ID)
public class Juicebar {

    //Field Declarations
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "juicebar";
    public static Juicebar INSTANCE;

    // Registry & Listener
    public Juicebar() {
        INSTANCE = this;
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        JuiceMiscItems.ITEMS.register(eventBus);
        JuiceFoods.ITEMS.register(eventBus);
        JuiceEquipment.ITEMS.register(eventBus);
        JuiceBlocks.BLOCKS.register(eventBus);
        JuiceContainers.CONTAINERS.register(eventBus);
        JuiceTiles.TILES.register(eventBus);
        JuiceRecipes.RECIPES.register(eventBus);
        JuiceEffects.EFFECTS.register(eventBus);

        eventBus.addListener(this::ClientSetup);
        forgeBus.addListener(JuiceEventHandler::onPlayerHurt);

        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.info("Juicebar - fully loaded!");
    }
    // Listener Method
    private void ClientSetup (final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {

            ScreenManager.register(JuiceContainers.CRUCIBLE_CONTAINER.get(), CrucibleScreen::new);
            RenderTypeLookup.setRenderLayer(JuiceBlocks.CRUCIBLE.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(JuiceBlocks.BOTTLE_GLASS.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(JuiceBlocks.BOTTLE_GLASS_PANE.get(), RenderType.cutout());
            JuiceItemModelProperties.makeBow(JuiceEquipment.DRINKMETAL_BOW.get());
        });
    }
}
