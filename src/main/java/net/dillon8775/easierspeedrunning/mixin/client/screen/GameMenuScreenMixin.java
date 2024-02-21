package net.dillon8775.easierspeedrunning.mixin.client.screen;

import net.dillon8775.easierspeedrunning.EasierSpeedrunningClient;
import net.dillon8775.easierspeedrunning.client.screen.ModMenuScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.SaveLevelScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {
    @Shadow @Final
    private boolean showMenu;
    @Unique
    private ButtonWidget createWorldButton;
    @Unique
    private ButtonWidget optionsButton;

    public GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void addButtons(CallbackInfo ci) {
        if (showMenu) {
            createWorldButton = this.addButton(new ButtonWidget(this.width / 2 - 4 - 120 - 2, this.height / 4 + 72 - 16, 20, 20, new LiteralText(""), (buttonWidget) -> {
                if (this.client.inGameHud != null) {
                    this.client.inGameHud.getChatHud().clear(false);
                }
                this.client.world.disconnect();
                this.client.disconnect(new SaveLevelScreen(new TranslatableText("menu.savingLevel")));
                this.client.openScreen(new CreateWorldScreen(this));
            }));
            createWorldButton.active = EasierSpeedrunningClient.clientOptions().fastWorldCreation;

            optionsButton = this.addButton(new ButtonWidget(this.width / 2 - 4 - 120 - 2, this.height / 4 + 96 - 16, 20, 20, new LiteralText(""), (buttonWidget) -> {
                this.client.openScreen(new ModMenuScreen(this, MinecraftClient.getInstance().options));
            }));
        }
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void renderButtonTextures(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (showMenu) {
            this.client.getTextureManager().bindTexture(new Identifier("easierspeedrunning:speedrun.png"));
            drawTexture(matrices, this.width / 2 - 4 - 118 - 2, createWorldButton.y + 2, 0.0F, 0.0F, 16, 16, 16, 16);

            this.client.getTextureManager().bindTexture(new Identifier("minecraft:textures/mob_effect/speed.png"));
            drawTexture(matrices, this.width / 2 - 4 - 119 - 2, optionsButton.y + 1, 0.0F, 0.0F, 18, 18, 18, 18);
        }
    }
}