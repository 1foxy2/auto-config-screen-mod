package com.example.examplemod.screen;

import com.electronwill.nightconfig.core.UnmodifiableConfig;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class AutoNeoForgeConfigScreen extends ConfigurationScreen.ConfigurationSectionScreen {
private static final String LANG_PREFIX = "neoforge.configuration.uitext.";
    private static final String SECTION = LANG_PREFIX + "section";
    private static final String SECTION_TEXT = LANG_PREFIX + "sectiontext";
    protected static final ConfigurationScreen.TranslationChecker translationChecker = new ConfigurationScreen.TranslationChecker();

    public AutoNeoForgeConfigScreen(Screen parent, ModConfig.Type type, ModConfig modConfig, Component title) {
        super(parent, type, modConfig, title);
    }

    public AutoNeoForgeConfigScreen(Context parentContext, Screen parent, Map<String, Object> valueSpecs, String key, Set<? extends UnmodifiableConfig.Entry> entrySet, Component title) {
        super(parentContext, parent, valueSpecs, key, entrySet, title);
    }

    @Nullable
    @Override
    protected Element createSection(String key, UnmodifiableConfig subconfig, UnmodifiableConfig subsection) {
        if (subconfig.isEmpty()) return null;
        return new Element(Component.translatable(SECTION, getTranslationComponent(key)), getTooltipComponent(key, null),
                Button.builder(Component.translatable(SECTION, Component.translatable(translationChecker.check(getTranslationKey(key) + ".button", SECTION_TEXT))),
                                button -> minecraft.setScreen(sectionCache.computeIfAbsent(key,
                                        k -> new AutoNeoForgeConfigScreen(context, this, subconfig.valueMap(), key, subsection.entrySet(), Component.translatable(getTranslationKey(key))).rebuild())))
                        .tooltip(Tooltip.create(getTooltipComponent(key, null)))
                        .width(Button.DEFAULT_WIDTH)
                        .build(),
                false);
    }

    @Override
    protected String getTranslationKey(String key) {
        String translationKey = super.getTranslationKey(key);
        if (!I18n.exists(translationKey))
            translationKey = Arrays.stream(translationKey.split("\\.")).toList().getLast();
        return translationKey;
    }
}
