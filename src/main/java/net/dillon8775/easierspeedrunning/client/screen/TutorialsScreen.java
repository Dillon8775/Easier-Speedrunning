package net.dillon8775.easierspeedrunning.client.screen;

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
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

@Environment(EnvType.CLIENT)
public class TutorialsScreen extends GameOptionsScreen {
    private final Screen parent;

    public TutorialsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_TUTORIALS);
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();

        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int height = this.height / 6 - 12;

        this.addButton(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("easierspeedrunning.menu.resources.tutorials.bastion_routes"), (button) -> {
            this.client.openScreen(new BastionRoutesScreen(this.parent, MinecraftClient.getInstance().options));
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, new TranslatableText("easierspeedrunning.menu.resources.tutorials.bastion_routes.tooltip"), x, y)));
        this.addButton(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("easierspeedrunning.menu.resources.tutorials.nether_fortresses"), (button) -> {
            Util.getOperatingSystem().open("https://www.youtube.com/watch?v=pmx9LyUvLTk");
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, new TranslatableText("easierspeedrunning.menu.resources.tutorials.nether_fortresses.tooltip"), x, y)));

        height += 24;
        this.addButton(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("easierspeedrunning.menu.resources.tutorials.microlensing"), (button) -> {
            this.client.openScreen(new MicrolensingScreen(this.parent, MinecraftClient.getInstance().options));
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, new TranslatableText("easierspeedrunning.menu.resources.tutorials.microlensing.tooltip"), x, y)));
        this.addButton(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("easierspeedrunning.menu.resources.tutorials.blind_travel"), (button) -> {
            Util.getOperatingSystem().open("https://www.youtube.com/watch?v=Ou58P7e-ZY0");
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, new TranslatableText("easierspeedrunning.menu.resources.tutorials.blind_travel.tooltip"), x, y)));

        height += 24;
        this.addButton(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("easierspeedrunning.menu.resources.tutorials.one_cycling"), (button) -> {
            Util.getOperatingSystem().open("https://www.youtube.com/watch?v=JaVyuTyDxxs");
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, new TranslatableText("easierspeedrunning.menu.resources.tutorials.one_cycling.tooltip"), x, y)));
        this.addButton(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("easierspeedrunning.menu.resources.tutorials.pie_chart"), (button) -> {
            Util.getOperatingSystem().open("https://www.youtube.com/watch?v=ENgEBHIifm8");
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, new TranslatableText("easierspeedrunning.menu.resources.tutorials.pie_chart.tooltip"), x, y)));

        height += 24;
        this.addButton(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("easierspeedrunning.menu.resources.tutorials.f3_menu"), (button) -> {
            Util.getOperatingSystem().open("https://www.youtube.com/watch?v=-fSr7P5LQJY");
        }));
        this.addButton(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("easierspeedrunning.menu.resources.tutorials.buried_treasure"), (button) -> {
            Util.getOperatingSystem().open("https://www.youtube.com/watch?v=_dyD8ZwagDg");
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, new TranslatableText("easierspeedrunning.menu.resources.tutorials.buried_treasure.tooltip"), x, y)));

        height += 24;
        this.addButton(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("easierspeedrunning.menu.resources.tutorials.other_useful_tricks"), (button) -> {
            Util.getOperatingSystem().open("https://www.youtube.com/watch?v=TvvApbI6fis");
        }, (buttonWidget, matrices, x, y) -> this.renderTooltip(matrices, new TranslatableText("easierspeedrunning.menu.resources.tutorials.other_useful_tricks.tooltip"), x, y)));

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

    private static class BastionRoutesScreen extends GameOptionsScreen {
        private final Screen parent;

        public BastionRoutesScreen(Screen parent, GameOptions options) {
            super(parent, options, new TranslatableText("easierspeedrunning.title.resources.tutorials.bastion_routes"));
            this.parent = parent;
        }

        @Override
        protected void init() {
            int leftSide = this.width / 2 - 155;
            int rightSide = leftSide + 160;
            int height = this.height / 6 - 12;

            this.addButton(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("easierspeedrunning.menu.resources.tutorials.bastion_routes.treasure"), (button) -> {
                Util.getOperatingSystem().open("https://www.youtube.com/watch?v=np6fc_z9LUY");
            }));
            this.addButton(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("easierspeedrunning.menu.resources.tutorials.bastion_routes.bridge"), (button) -> {
                Util.getOperatingSystem().open("https://www.youtube.com/watch?v=Dts81nEnzuQ");
            }));

            height += 24;
            this.addButton(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("easierspeedrunning.menu.resources.tutorials.bastion_routes.stables"), (button) -> {
                Util.getOperatingSystem().open("https://www.youtube.com/watch?v=WIN-ZtUJfIc");
            }));
            this.addButton(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("easierspeedrunning.menu.resources.tutorials.bastion_routes.housing"), (button) -> {
                Util.getOperatingSystem().open("https://www.youtube.com/watch?v=S0G5asEjrgk");
            }));

            this.addButton(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, ScreenTexts.DONE, (button) -> {
                this.onClose();
            }));
        }

        @Override
        public void onClose() {
            this.client.openScreen(new TutorialsScreen(this.parent, MinecraftClient.getInstance().options));
        }

        @Override
        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            this.renderBackground(matrices);
            drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
            super.render(matrices, mouseX, mouseY, delta);
        }
    }

    private static class MicrolensingScreen extends GameOptionsScreen {
        private final Screen parent;

        public MicrolensingScreen(Screen parent, GameOptions options) {
            super(parent, options, new TranslatableText("easierspeedrunning.title.resources.tutorials.microlensing"));
            this.parent = parent;
        }

        @Override
        protected void init() {
            int leftSide = this.width / 2 - 155;
            int rightSide = leftSide + 160;
            int height = this.height / 6 - 12;

            this.addButton(new ButtonWidget(leftSide, height, 150, 20, new TranslatableText("easierspeedrunning.menu.resources.tutorials.microlensing.bastion"), (button) -> {
                Util.getOperatingSystem().open("https://www.youtube.com/watch?v=jvTfMLPnMSw");
            }));
            this.addButton(new ButtonWidget(rightSide, height, 150, 20, new TranslatableText("easierspeedrunning.menu.resources.tutorials.microlensing.fortress"), (button) -> {
                Util.getOperatingSystem().open("https://www.youtube.com/watch?v=Kl_-L9XbJko");
            }));

            this.addButton(new ButtonWidget(this.width / 2 - 100, this.height - 29, 200, 20, ScreenTexts.DONE, (button) -> {
                this.onClose();
            }));
        }

        @Override
        public void onClose() {
            this.client.openScreen(new TutorialsScreen(this.parent, MinecraftClient.getInstance().options));
        }

        @Override
        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            this.renderBackground(matrices);
            drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
            super.render(matrices, mouseX, mouseY, delta);
        }
    }
}