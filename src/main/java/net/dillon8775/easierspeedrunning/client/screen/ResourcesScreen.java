package net.dillon8775.easierspeedrunning.client.screen;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.dillon8775.easierspeedrunning.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.options.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Util;

@Environment(EnvType.CLIENT)
public class ResourcesScreen extends GameOptionsScreen {
    private final Screen parent;

    public ResourcesScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_RESOURCES);
        this.parent = parent;
    }

    @Override
    protected void init() {
        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int height = this.height / 6 - 12;

        this.addButton(new ButtonWidget(leftSide, height, 150, 20, ModTexts.MENU_MODS, (button) -> {
            this.client.openScreen(new ModsScreen(this.parent, MinecraftClient.getInstance().options));
        }));
        this.addButton(new ButtonWidget(rightSide, height, 150, 20, ModTexts.MENU_TUTORIALS, (button) -> {
            this.client.openScreen(new TutorialsScreen(this.parent, MinecraftClient.getInstance().options));
        }));

        height += 24;
        this.addButton(new ButtonWidget(leftSide, height, 150, 20, ModTexts.MENU_WIKI, (button) -> {
            Util.getOperatingSystem().open(EasierSpeedrunning.WIKI_LINK);
        }));

        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, ScreenTexts.DONE, (button) -> {
            this.client.openScreen(this.parent);
        }));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}