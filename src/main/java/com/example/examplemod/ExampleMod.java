package com.example.examplemod;

import com.example.examplemod.Items.IItemList;
import com.example.examplemod.Items.ItemList;
import com.example.examplemod.blocks.BlockList;
import com.example.examplemod.blocks.IBlockList;
import com.example.examplemod.util.Init;
import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.BlockItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.awt.image.ByteLookupTable;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("samscoolmod")
public class ExampleMod implements IExampleMod {

    public ExampleMod() {
        // get the init class
        Init Init = new Init();

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(Init::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(Init::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(Init::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(Init::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(RegistryEvents.class);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
            LOGGER.info("Test Info Message");
        }

        @SubscribeEvent
        public static void pickupItem(EntityItemPickupEvent event) {
            System.out.println("Pickup Item");
        }

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            event.getRegistry()
                    .registerAll(
                            ItemList.RUBY_BLOCK =
                                    new BlockItem(BlockList.RUBY_BLOCK, new BlockItem.Properties()
                                            .group(ItemGroup.MISC))
                                            .setRegistryName(location("ruby_block")),

                            ItemList.CONSTRUCTION_BLOCK =
                                    new BlockItem(BlockList.CONSTRUCTION_BLOCK, new BlockItem.Properties()
                                            .group(ItemGroup.MISC))
                                            .setRegistryName(location("construction_block")),

                            ItemList.RUBY_ORE =
                                    new BlockItem(BlockList.RUBY_ORE, new BlockItem.Properties()
                                            .group(ItemGroup.MISC))
                                            .setRegistryName(location(ItemList.RUBY_ORE_REGISTER_NAME))
                    );
            LOGGER.info("Items Registered");
        }

        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            event.getRegistry()
                    .registerAll(
                            BlockList.RUBY_BLOCK =
                                    new Block(Block.Properties.create(Material.IRON)
                                            .hardnessAndResistance(IBlockList.RUBY_BLOCK_RESISTANCE, IBlockList.RUBY_BLOCK_HARDNESS)
                                            .sound(IBlockList.RUBY_BLOCK_SOUND))
                                            .setRegistryName(location("ruby_block")),

                            BlockList.CONSTRUCTION_BLOCK =
                                    new Block(Block.Properties.create(Material.IRON)
                                    .hardnessAndResistance(IBlockList.CONSTRUCTION_BLOCK_HARDNESS, IBlockList.CONSTRUCTION_BLOCK_RESISTANCE)
                                    .sound(IBlockList.CONSTRUCTION_BLOCK_SOUND))
                                    .setRegistryName(location(IBlockList.CONSTRUCTION_BLOCK_REGISTRY_NAME)),

                            BlockList.RUBY_ORE =
                                    new Block(Block.Properties.create(Material.IRON)
                                            .hardnessAndResistance(IBlockList.RUBY_ORE_HARDNESS ,IBlockList.RUBY_ORE_RESISTANCE)
                                            .sound(IBlockList.RUBY_ORE_SOUND)
                                            .harvestTool(ToolType.PICKAXE))
                                            .setRegistryName(location(IBlockList.RUBY_ORE_REGISTRY_NAME))
                    );
        }

        private static ResourceLocation location(String name) {
            return new ResourceLocation(IExampleMod.MOD_ID, name);
        }
    }
}
