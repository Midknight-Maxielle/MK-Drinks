package com.midknight.owmix;

import com.midknight.owmix.registry.*;
import com.midknight.owmix.registry.RegistryBlocks;
import com.midknight.owmix.screen.CrucibleScreen;
import com.midknight.owmix.util.JuiceEventHandler;
import com.midknight.owmix.util.JuiceItemModelProperties;

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

@Mod(OverworldMixology.MOD_ID)
public class OverworldMixology {

    //Field Declarations
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "owmix";
    public static OverworldMixology INSTANCE;

    // Registry & Listener
    public OverworldMixology() {
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

        LOGGER.info("Overworld Mixology - fully loaded!");
    }
    // Listener Method
    private void ClientSetup (final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {

            MenuScreens.register(RegistryMenus.CRUCIBLE_CONTAINER.get(), CrucibleScreen::new);
            ItemBlockRenderTypes.setRenderLayer(RegistryBlocks.CRUCIBLE.get(), RenderType.cutout());
            JuiceItemModelProperties.makeBow(RegistryEquipment.DRINKMETAL_BOW.get());
        });
    }
}
