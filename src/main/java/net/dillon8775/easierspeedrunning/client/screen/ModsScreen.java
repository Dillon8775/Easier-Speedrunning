package net.dillon8775.easierspeedrunning.client.screen;

import net.dillon8775.easierspeedrunning.client.util.ModLinks;
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
public class ModsScreen extends GameOptionsScreen {
    private final Screen parent;

    public ModsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_MODS);
        this.parent = parent;
    }

    @Override
    protected void init() {
        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int height = this.height / 6 - 12;

        this.addButton(new ButtonWidget(leftSide, height, 150, 20, ModTexts.SODIUM, (buttonWidget) ->  {
            Util.getOperatingSystem().open(ModLinks.SODIUM);
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, ModTexts.SODIUM_TOOLTIP, x, y)));

        this.addButton(new ButtonWidget(rightSide, height, 150, 20, ModTexts.LITHIUM, (buttonWidget) -> {
            Util.getOperatingSystem().open(ModLinks.LITHIUM);
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, ModTexts.LITHIUM_TOOLTIP, x, y)));

        height += 24;
        this.addButton(new ButtonWidget(leftSide, height, 150, 20, ModTexts.PHOSPHOR, (buttonWidget) -> {
            Util.getOperatingSystem().open(ModLinks.PHOSPHOR);
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, ModTexts.PHOSPHOR_TOOLTIP, x, y)));

        this.addButton(new ButtonWidget(rightSide, height, 150, 20, ModTexts.SPEEDRUN_IGT, (buttonWidget) -> {
            Util.getOperatingSystem().open(ModLinks.SPEEDRUN_IGT);
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, ModTexts.SPEEDRUN_IGT_TOOLTIP, x, y)));

        height += 24;
        this.addButton(new ButtonWidget(leftSide, height, 150, 20, ModTexts.LAZYDFU, (buttonWidget) -> {
            Util.getOperatingSystem().open(ModLinks.LAZYDFU);
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, ModTexts.LAZYDFU_TOOLTIP, x, y)));

        ButtonWidget kryptonButton = this.addButton(new ButtonWidget(rightSide, height, 150, 20, ModTexts.KRYPTON, (buttonWidget) -> {
            Util.getOperatingSystem().open(ModLinks.KRYPTON_MOD_LINK);
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, ModTexts.KRYPTON_TOOLTIP, x, y)));
        kryptonButton.active = false;

        height += 24;
        ButtonWidget optifine;
        optifine = this.addButton(new ButtonWidget(leftSide, height, 150, 20, ModTexts.OPTIFINE, (buttonWidget) -> {
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, ModTexts.OPTIFINE_TOOLTIP, x, y)));
        optifine.active = false;

        this.addButton(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, ScreenTexts.DONE, (button) -> {
            this.onClose();
        }));
    }

    @Override
    public void onClose() {
        this.client.openScreen(new ResourcesScreen(this.parent, MinecraftClient.getInstance().options));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }
}