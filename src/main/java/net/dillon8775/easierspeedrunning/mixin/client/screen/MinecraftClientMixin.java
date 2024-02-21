package net.dillon8775.easierspeedrunning.mixin.client.screen;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.dillon8775.easierspeedrunning.EasierSpeedrunningClient;
import net.dillon8775.easierspeedrunning.client.screen.SafeBootScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.options.GameOptions;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;
import java.util.List;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.warn;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    private final long SAVE_INTERVAL = 10000;
    @Shadow
    private GameOptions options;
    @Shadow
    public abstract void openScreen(@Nullable Screen screen);
    private long lastSaveTime = 0;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void safeBoot(RunArgs args, CallbackInfo ci) {
        if (EasierSpeedrunning.safeBoot) {
            this.openScreen(new SafeBootScreen());
            warn("Booted into safe mode, due to corrupt options. It is recommended that you fix these options before proceeding.");
        }
    }

    @Inject(method = "close", at = @At("HEAD"))
    private void close(CallbackInfo info) {
        options.write();
    }

    @Inject(method = "openScreen", at = @At("HEAD"))
    private void sodiumConfig(Screen screen, CallbackInfo info) {
        if (screen instanceof GameMenuScreen && System.currentTimeMillis() - lastSaveTime > SAVE_INTERVAL) {
            lastSaveTime = System.currentTimeMillis();
        }

        if (screen != null && screen.getClass().getSimpleName().equals("SodiumOptionsGUI")) {
            try {
                List<?> optionPages = (List<?>) get(screen, "me.jellysquid.mods.sodium.client.gui.SodiumOptionsGUI", "pages");
                List<?> optionGroups = (List<?>) get(optionPages.get(0), "me.jellysquid.mods.sodium.client.gui.options.OptionPage", "groups");
                List<?> options = (List<?>) get(optionGroups.get(0), "me.jellysquid.mods.sodium.client.gui.options.OptionGroup", "options");
                Object sliderControl = get(options.get(1), "me.jellysquid.mods.sodium.client.gui.options.OptionImpl", "control");
                Class<?> sliderControlClass = Class.forName("me.jellysquid.mods.sodium.client.gui.options.control.SliderControl");
                setInt(sliderControl, sliderControlClass, "min", (int) (1.0D * 100));
                setInt(sliderControl, sliderControlClass, "max", (int) (5.0D * 100));
                setInt(sliderControl, sliderControlClass, "interval", 10);
            }
            catch (NoSuchFieldException | IllegalAccessException | ClassNotFoundException ex) {
                EasierSpeedrunningClient.logException(ex, "an exception occurred during the manipulation of the sodium options gui");
            }
        }
    }

    private Object get(Object instance, String className, String name) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        Field f = Class.forName(className).getDeclaredField(name);
        f.setAccessible(true);
        return f.get(instance);
    }

    private void setInt(Object instance, Class<?> clazz, String field, int value) throws NoSuchFieldException, IllegalAccessException {
        Field f = clazz.getDeclaredField(field);
        f.setAccessible(true);
        f.setInt(instance, value);
    }
}