package net.dillon8775.easierspeedrunning.client.screen;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.dillon8775.easierspeedrunning.client.util.ModTexts;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.options.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.StringRenderable;
import net.minecraft.util.Util;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.options;

public class ModMenuScreen extends GameOptionsScreen {
    private final Screen parent;
    private ButtonWidget doomModeButton;

    public ModMenuScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE);
        this.parent = parent;
    }

    @Override
    protected void init() {
        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int height = this.height / 6 - 12;

        this.addButton(new ButtonWidget(leftSide, height, 150, 20, ModTexts.MENU_OPTIONS, (button) -> {
            RestartRequiredScreen.getCurrentOptions();
            this.client.openScreen(new ModOptionsScreen(this, MinecraftClient.getInstance().options));
        }));

        this.addButton(new ButtonWidget(rightSide, height, 150, 20, ModTexts.MENU_OPTIONS_RESET, (button) -> {
            this.client.openScreen(new ResetOptionsConfirmScreen(this.parent, MinecraftClient.getInstance().options));
        }));

        height += 24;
        this.addButton(new ButtonWidget(leftSide, height, 150, 20, ModTexts.MENU_RESOURCES, (button) -> {
            this.client.openScreen(new ResourcesScreen(this, MinecraftClient.getInstance().options));
        }));

        this.addButton(new ButtonWidget(rightSide, height, 150, 20, ModTexts.MENU_EXTERNAL, (button) -> {
            this.client.openScreen(new ExternalScreen(this, MinecraftClient.getInstance().options));
        }));

        height += 24;
        doomModeButton = this.addButton(new ButtonWidget(leftSide, height, 150, 20, ModTexts.MENU_DOOM_MODE, (button) -> {
            if (DoomModeScreen.doomModeButtonAlreadyClicked > 0) {
                this.client.openScreen(new DoomModeScreen.ScreenFive(this, MinecraftClient.getInstance().options));
            } else {
                this.client.openScreen(new DoomModeScreen(this, MinecraftClient.getInstance().options));
            }
        }));
        doomModeButton.visible = options().doomMode;

        this.addButton(new ButtonWidget(doomModeButton.visible ? rightSide : leftSide, height, 150, 20, ModTexts.THE_SPEEDRUNNER_MOD, (button) -> {
            Util.getOperatingSystem().open("https://sites.google.com/view/dillon8775/the-speedrunner-mod");
        }));

        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, ScreenTexts.DONE, (button) -> {
            this.client.openScreen(this.parent);
        }));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);

        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int farRightSide = rightSide + 267;
        int height = this.height - 24;
        drawCenteredText(matrices, this.textRenderer, StringRenderable.plain(EasierSpeedrunning.VERSION), farRightSide, height, 16777215);

        this.drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
}