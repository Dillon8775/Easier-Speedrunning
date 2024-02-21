package net.dillon8775.easierspeedrunning.mixin.client.screen;

import net.dillon8775.easierspeedrunning.EasierSpeedrunningClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.SaveLevelScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(DeathScreen.class)
public class DeathScreenMixin extends Screen {

    public DeathScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/DeathScreen;ticksSinceDeath:I"))
    private void addResetButton(CallbackInfo ci) {
        if (EasierSpeedrunningClient.clientOptions().fastWorldCreation) {
            this.addButton(this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 4 + 120, 200, 20, new TranslatableText("easierspeedrunning.reset_world"), (button) -> {
                if (this.client.inGameHud != null) {
                    this.client.inGameHud.getChatHud().clear(false);
                }
                this.client.world.disconnect();
                this.client.disconnect(new SaveLevelScreen(new TranslatableText("menu.savingLevel")));
                this.client.openScreen(new CreateWorldScreen(this));
            })));
        }
    }
}