package net.dillon8775.easierspeedrunning.mixin.client.screen;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.dillon8775.easierspeedrunning.client.screen.SafeBootScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.warn;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow
    public abstract void openScreen(@Nullable Screen screen);

    @Inject(method = "<init>", at = @At("TAIL"))
    private void safeBoot(RunArgs args, CallbackInfo ci) {
        if (EasierSpeedrunning.safeBoot) {
            this.openScreen(new SafeBootScreen());
            warn("Booted into safe mode, due to corrupt options. It is recommended that you fix these options before proceeding.");
        }
    }
}