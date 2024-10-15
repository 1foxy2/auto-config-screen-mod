package com.example.examplemod.mixin;

import dev.tr7zw.skinlayers.util.ModLoaderUtil;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

@Mixin(ModLoaderUtil.class)
public class SkinLayersFixMixin {
    @Inject(
            method = "registerConfigScreen",
            at = @At(
                    value = "TAIL"
            )
    )
    private static void fixConfigScreen(Function<Screen, Screen> createScreen, CallbackInfo ci) {
        ModList.get().getModContainerById("skinlayers3d").get().registerExtensionPoint(IConfigScreenFactory.class,
                (mc, screen) -> createScreen.apply(screen));
    }
}
