package com.example.examplemod;

import com.example.examplemod.config.json.JsonConfig;
import com.example.examplemod.config.json.JsonConfigScreen;
import com.example.examplemod.screen.AutoNeoForgeConfigScreen;
import com.google.gson.JsonObject;
import net.minecraft.util.GsonHelper;
import net.neoforged.fml.ModList;
import net.neoforged.fml.config.ModConfigs;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(value = ExampleMod.MODID, dist = Dist.CLIENT)
public class ExampleMod {
    public static final String MODID = "examplemod";
    public static final Map<String, JsonConfig> modJsonConfigs = new HashMap<>();
    private static final Logger LOGGER = LogUtils.getLogger();

    public ExampleMod(IEventBus modEventBus, ModContainer modContainer) {
        //modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        for (ModContainer container : ModList.get().getSortedMods()) {
            if (IConfigScreenFactory.getForMod(container.getModInfo()).isPresent())
                continue;

            String modid = container.getModId();
            if (!ModConfigs.getModConfigs(modid).isEmpty()) {
                container.registerExtensionPoint(IConfigScreenFactory.class, (c, screen) ->
                        new ConfigurationScreen(c, screen, AutoNeoForgeConfigScreen::new));
                continue;
            }

            Path configPath = FMLPaths.CONFIGDIR.get().resolve(modid + ".json");
            if (configPath.toFile().exists()) {
                try {
                    JsonObject json = GsonHelper.parse(new FileReader(configPath.toFile()));
                    modJsonConfigs.put(modid, new JsonConfig(json, configPath));
                    container.registerExtensionPoint(IConfigScreenFactory.class, (c, screen) ->
                            new JsonConfigScreen(screen, modJsonConfigs.get(modid), Component.literal(modid)));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
