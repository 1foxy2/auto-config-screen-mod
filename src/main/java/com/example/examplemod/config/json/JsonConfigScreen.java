package com.example.examplemod.config.json;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;

public class JsonConfigScreen extends OptionsSubScreen {
    private final JsonConfig config;

    public JsonConfigScreen(Screen lastScreen, JsonConfig jsonConfig, Component title) {
        super(lastScreen, Minecraft.getInstance().options, title);
        this.config = jsonConfig;
    }

    @Override
    protected void addOptions() {
        for (JsonValue<?> value : config.values) {
            this.list.addBig(value.createScreenValue());
        }
    }
}
