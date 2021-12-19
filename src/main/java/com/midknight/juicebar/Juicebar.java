package com.midknight.juicebar;

import com.midknight.juicebar.registry.*;
import com.midknight.juicebar.registry.RegistryBlocks;
import com.midknight.juicebar.screen.CrucibleScreen;
import com.midknight.juicebar.util.JuiceEventHandler;
import com.midknight.juicebar.util.JuiceItemModelProperties;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;

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

        RegistryMiscItems.ITEMS.register(eventBus);
        RegistryFoodItems.ITEMS.register(eventBus);
        RegistryEquipment.ITEMS.register(eventBus);
        RegistryBlocks.BLOCKS.register(eventBus);
        RegistryMenus.CONTAINERS.register(eventBus);
        RegistryBE.BLOCK_ENTITIES.register(eventBus);
        RegistryRecipes.RECIPES.register(eventBus);
        RegistryMobEffects.EFFECTS.register(eventBus);

        RegistryLoot.registerGLMS();

        eventBus.addListener(this::ClientSetup);
        forgeBus.addListener(JuiceEventHandler::onPlayerHurt);

        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.info("Juicebar - fully loaded!");
    }
    // Listener Method
    private void ClientSetup (final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {

            MenuScreens.register(RegistryMenus.CRUCIBLE_CONTAINER.get(), CrucibleScreen::new);
            ItemBlockRenderTypes.setRenderLayer(RegistryBlocks.CRUCIBLE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(RegistryBlocks.BOTTLE_GLASS.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(RegistryBlocks.BOTTLE_GLASS_PANE.get(), RenderType.cutout());
            JuiceItemModelProperties.makeBow(RegistryEquipment.DRINKMETAL_BOW.get());
        });
    }
}
