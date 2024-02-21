package net.dillon8775.easierspeedrunning.mixin.client;

import net.dillon8775.easierspeedrunning.EasierSpeedrunningClient;
import net.dillon8775.easierspeedrunning.option.ClientModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.SaveLevelScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.debug;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class Keybindings {
    @Shadow @Final
    public InGameHud inGameHud;
    @Shadow @Nullable
    public ClientWorld world;
    @Shadow
    public abstract void openScreen(@Nullable Screen screen);
    @Shadow
    public abstract void disconnect(Screen screen);

    /**
     * Applies the {@code easier speedrunning keybinds} to the game.
     */
    @Inject(method = "handleInputEvents", at = @At("TAIL"))
    private void addKeybindings(CallbackInfo info) {
        while (ClientModOptions.resetKey.wasPressed()) {
            if (EasierSpeedrunningClient.clientOptions().fastWorldCreation) {
                if (this.inGameHud != null) {
                    this.inGameHud.getChatHud().clear(false);
                }
                assert this.world != null;
                this.world.disconnect();
                this.disconnect(new SaveLevelScreen(new TranslatableText("menu.savingLevel")));
                this.openScreen(new CreateWorldScreen(null));
            } else {
                debugWarnEasierSpeedrunning("\"Fast World Creation\" is OFF, please enable to use this feature.");
            }
        }

        while (ClientModOptions.fogKey.wasPressed()) {
            EasierSpeedrunningClient.clientOptions().fog = !EasierSpeedrunningClient.clientOptions().fog;
            ClientModOptions.saveClientConfig();
            MinecraftClient.getInstance().worldRenderer.reload();
            debugWarnEasierSpeedrunning(EasierSpeedrunningClient.clientOptions().fog ? "easierspeedrunning.toggle_fog.on" : "easierspeedrunning.toggle_fog.off");
        }

        while (ClientModOptions.hitboxesKey.wasPressed()) {
            boolean bl = !MinecraftClient.getInstance().getEntityRenderManager().shouldRenderHitboxes();
            String hitboxes = bl ? "ON" : "OFF";
            MinecraftClient.getInstance().getEntityRenderManager().setRenderHitboxes(bl);
            debugWarn(bl ? "debug.show_hitboxes.on" : "debug.show_hitboxes.off");
            debug("Toggled hitboxes " + hitboxes);
        }

        while (ClientModOptions.chunkBordersKey.wasPressed()) {
            boolean bl = MinecraftClient.getInstance().debugRenderer.toggleShowChunkBorder();
            String chunkBorders = bl ? "ON" : "OFF";
            debugWarn(bl ? "debug.chunk_boundaries.on" : "debug.chunk_boundaries.off");
            debug("Toggled chunk borders " + chunkBorders);
        }
    }

    @Unique
    private void debugWarn(String string, Object... objects) {
        this.inGameHud.getChatHud().addMessage((new LiteralText("")).append((new TranslatableText("debug.prefix")).formatted(Formatting.YELLOW, Formatting.BOLD)).append(" ").append(new TranslatableText(string, objects)));
    }

    @Unique
    private void debugWarnEasierSpeedrunning(String string, Object... objects) {
        this.inGameHud.getChatHud().addMessage((new LiteralText("")).append((new TranslatableText("easierspeedrunning.debug.prefix")).formatted(Formatting.AQUA, Formatting.BOLD)).append(" ").append(new TranslatableText(string, objects)));
    }
}