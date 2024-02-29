package net.dillon8775.easierspeedrunning.client.screen;

import net.dillon8775.easierspeedrunning.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.SaveLevelScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.info;

@Environment(EnvType.CLIENT)
public class ResetOptionsScreen extends GameOptionsScreen {
    private final Screen parent;

    public ResetOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, new LiteralText(""));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int height = this.height / 6 + 126;
        this.addButton(new ButtonWidget(leftSide, height, 150, 20, ModTexts.RESTART_NOW, (buttonWidget) -> {
            if (this.client.isInSingleplayer()) {
                this.client.world.disconnect();
                this.client.disconnect(new SaveLevelScreen(new TranslatableText("menu.savingLevel")));
            } else {
                this.client.disconnect();
            }
            info("Closing game! Re-launch to apply changes.");
            this.client.scheduleStop();
        }));
        this.addButton(new ButtonWidget(rightSide, height, 150, 20, ModTexts.RESTART_LATER, (buttonWidget) -> {
            this.onClose();
        }));
    }

    @Override
    public void onClose() {
        this.client.openScreen(new ModMenuScreen(this.parent, MinecraftClient.getInstance().options));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("easierspeedrunning.reset_options_successful.line1"), this.width / 2, 110, 16777215);
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("easierspeedrunning.reset_options_successful.line2"), this.width / 2, 130, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}