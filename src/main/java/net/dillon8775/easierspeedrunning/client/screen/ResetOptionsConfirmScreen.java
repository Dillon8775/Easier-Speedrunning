package net.dillon8775.easierspeedrunning.client.screen;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.dillon8775.easierspeedrunning.client.util.ModTexts;
import net.dillon8775.easierspeedrunning.option.ClientModOptions;
import net.dillon8775.easierspeedrunning.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.info;

@Environment(EnvType.CLIENT)
public class ResetOptionsConfirmScreen extends GameOptionsScreen {
    private final Screen parent;

    public ResetOptionsConfirmScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_OPTIONS_RESET);
        this.parent = parent;
    }

    @Override
    protected void init() {
        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int height = this.height / 6 + 126;
        this.addDrawableChild(new ButtonWidget(leftSide, height, 150, 20, ModTexts.RESET, (buttonWidget) -> {
            EasierSpeedrunning.resetOptions();
            ModOptions.saveConfig();
            ClientModOptions.saveClientConfig();
            info("Successfully reset all options. Restart the game to take full effect.");
            this.client.setScreen(new ResetOptionsScreen(this.parent, MinecraftClient.getInstance().options));
        }));
        this.addDrawableChild(new ButtonWidget(rightSide, height, 150, 20, ModTexts.NOT_NOW, (buttonWidget) -> {
            this.onClose();
        }));
    }

    @Override
    public void onClose() {
        this.client.setScreen(new ModMenuScreen(this.parent, MinecraftClient.getInstance().options));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("easierspeedrunning.reset_options_confirm"), this.width / 2, 110, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}