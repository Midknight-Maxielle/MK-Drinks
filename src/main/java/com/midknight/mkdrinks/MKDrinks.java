package com.midknight.mkdrinks;

import com.midknight.mkdrinks.block.MKBlocks;
import com.midknight.mkdrinks.container.MKContainers;
import com.midknight.mkdrinks.data.recipes.DrinksRecipes;
import com.midknight.mkdrinks.item.MKGearItems;
import com.midknight.mkdrinks.item.MKItemsRegistry;
import com.midknight.mkdrinks.item.MKMiscItems;
import com.midknight.mkdrinks.item.MKDrinkitems;
import com.midknight.mkdrinks.screen.CrucibleScreen;
import com.midknight.mkdrinks.tileentity.MKTileEntities;
import com.midknight.mkdrinks.util.MKItemModelProperties;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod(MKDrinks.MOD_ID)
public class MKDrinks
{

    public static final String MOD_ID = "mkdrinks";
    public static MKDrinks INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public MKDrinks() {

        INSTANCE = this;
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MKMiscItems.register(eventBus);
        LOGGER.info("MKDrinks - Materials loaded!");

        MKDrinkitems.register(eventBus);
        LOGGER.info("MKDrinks - Pops loaded!");

        MKGearItems.register(eventBus);
        LOGGER.info("MKDrinks - Gear loaded!");

        MKBlocks.register(eventBus);
        MKContainers.register(eventBus);
        MKTileEntities.register(eventBus);
        MKItemsRegistry.register(eventBus);

        DrinksRecipes.RECIPES.register(eventBus);

        // Register the setup method for modloading //
        eventBus.addListener(this::setup);
        eventBus.addListener(this::enqueueIMC);
        eventBus.addListener(this::processIMC);
        eventBus.addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        LOGGER.info("MKDrinks - fully loaded!");
    }

    private void serverSetup(final FMLDedicatedServerSetupEvent event) {
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {

        event.enqueueWork(() -> {

            ScreenManager.registerFactory(MKContainers.CRUCIBLE_CONTAINER.get(), CrucibleScreen::new);

            RenderTypeLookup.setRenderLayer(MKBlocks.CRUCIBLE.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(MKBlocks.BOTTLE_GLASS.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(MKBlocks.BOTTLE_GLASS_PANE.get(), RenderType.getCutout());

            MKItemModelProperties.makeBow(MKGearItems.DRINKMETAL_BOW.get());

        });
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
