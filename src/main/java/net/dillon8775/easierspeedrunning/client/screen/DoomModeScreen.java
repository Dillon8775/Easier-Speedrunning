package net.dillon8775.easierspeedrunning.client.screen;

import net.dillon8775.easierspeedrunning.client.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
class DoomModeScreen extends GameOptionsScreen {
    private final Screen parent;
    protected static int doomModeButtonAlreadyClicked = 0;

    protected DoomModeScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_DOOM_MODE);
        this.parent = parent;
    }

    @Override
    protected void init() {
        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int middle = rightSide - 80;
        int height = this.height / 6 + 126;

        this.addButton(new ButtonWidget(middle, height, 150, 20, new TranslatableText("easierspeedrunning.doom_mode_screen.line1.reply"), (buttonWidget) -> {
            this.client.openScreen(new ScreenTwo(this.parent, MinecraftClient.getInstance().options));
        }));

        height += 24;
        this.addButton(new ButtonWidget(middle, height, 150, 20, ModTexts.BACK, (button) -> {
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
        drawCenteredText(matrices, this.textRenderer, new TranslatableText("easierspeedrunning.doom_mode_screen.line1"), this.width / 2, 110, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }

    protected static class ScreenTwo extends GameOptionsScreen {
        private final Screen parent;

        protected ScreenTwo(Screen parent, GameOptions options) {
            super(parent, options, ModTexts.TITLE_DOOM_MODE);
            this.parent = parent;
        }

        @Override
        protected void init() {
            int leftSide = this.width / 2 - 155;
            int rightSide = leftSide + 160;
            int middle = rightSide - 80;
            int height = this.height / 6 + 126;

            this.addButton(new ButtonWidget(middle, height, 150, 20, new TranslatableText("easierspeedrunning.doom_mode_screen.line2.reply"), (buttonWidget) -> {
                this.client.openScreen(new ScreenThree(this.parent, MinecraftClient.getInstance().options));
            }));

            height += 24;
            this.addButton(new ButtonWidget(middle, height, 150, 20, ModTexts.BACK, (button) -> {
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
            drawCenteredText(matrices, this.textRenderer, new TranslatableText("easierspeedrunning.doom_mode_screen.line2"), this.width / 2, 110, 16777215);
            super.render(matrices, mouseX, mouseY, delta);
        }
    }

    protected static class ScreenThree extends GameOptionsScreen {
        private final Screen parent;

        protected ScreenThree(Screen parent, GameOptions options) {
            super(parent, options, ModTexts.TITLE_DOOM_MODE);
            this.parent = parent;
        }

        @Override
        protected void init() {
            int leftSide = this.width / 2 - 155;
            int rightSide = leftSide + 160;
            int middle = rightSide - 80;
            int height = this.height / 6 + 126;

            this.addButton(new ButtonWidget(middle, height, 150, 20, new TranslatableText("easierspeedrunning.doom_mode_screen.line3.reply"), (buttonWidget) -> {
                this.client.openScreen(new ScreenFour(this.parent, MinecraftClient.getInstance().options));
            }));

            height += 24;
            this.addButton(new ButtonWidget(middle, height, 150, 20, ModTexts.BACK, (button) -> {
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
            drawCenteredText(matrices, this.textRenderer, new TranslatableText("easierspeedrunning.doom_mode_screen.line3"), this.width / 2, 110, 16777215);
            super.render(matrices, mouseX, mouseY, delta);
        }
    }

    protected static class ScreenFour extends GameOptionsScreen {
        private final Screen parent;

        protected ScreenFour(Screen parent, GameOptions options) {
            super(parent, options, ModTexts.TITLE_DOOM_MODE);
            this.parent = parent;
        }

        @Override
        protected void init() {
            int leftSide = this.width / 2 - 155;
            int rightSide = leftSide + 160;
            int middle = rightSide - 80;
            int height = this.height / 6 + 126;

            this.addButton(new ButtonWidget(middle, height, 150, 20, ModTexts.OK, (buttonWidget) -> {
                this.onClose();
                doomModeButtonAlreadyClicked = 1;
            }));
        }

        @Override
        public void onClose() {
            this.client.openScreen(new ModMenuScreen(this.parent, MinecraftClient.getInstance().options));
        }

        @Override
        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            this.renderBackground(matrices);
            drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
            drawCenteredText(matrices, this.textRenderer, new TranslatableText("easierspeedrunning.doom_mode_screen.line4"), this.width / 2, 90, 16777215);
            drawCenteredText(matrices, this.textRenderer, new TranslatableText("easierspeedrunning.doom_mode_screen.line5"), this.width / 2, 110, 16777215);
            drawCenteredText(matrices, this.textRenderer, new TranslatableText("easierspeedrunning.doom_mode_screen.line6"), this.width / 2, 130, 16777215);
            super.render(matrices, mouseX, mouseY, delta);
        }
    }

    protected static class ScreenFive extends GameOptionsScreen {
        private final Screen parent;

        protected ScreenFive(Screen parent, GameOptions options) {
            super(parent, options, ModTexts.TITLE_DOOM_MODE);
            this.parent = parent;
        }

        @Override
        protected void init() {
            int leftSide = this.width / 2 - 155;
            int rightSide = leftSide + 160;
            int middle = rightSide - 80;
            int height = this.height / 6 + 126;

            this.addButton(new ButtonWidget(middle, height, 150, 20, new TranslatableText("easierspeedrunning.doom_mode_screen.line7.reply"), (buttonWidget) -> {
                this.client.openScreen(new ScreenSix(this.parent, MinecraftClient.getInstance().options));
            }));

            height += 24;
            this.addButton(new ButtonWidget(middle, height, 150, 20, ModTexts.BACK, (button) -> {
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
            drawCenteredText(matrices, this.textRenderer, new TranslatableText("easierspeedrunning.doom_mode_screen.line7"), this.width / 2, 110, 16777215);
            super.render(matrices, mouseX, mouseY, delta);
        }
    }

    protected static class ScreenSix extends GameOptionsScreen {
        private final Screen parent;

        protected ScreenSix(Screen parent, GameOptions options) {
            super(parent, options, ModTexts.TITLE_DOOM_MODE);
            this.parent = parent;
        }

        @Override
        protected void init() {
            int leftSide = this.width / 2 - 155;
            int rightSide = leftSide + 160;
            int middle = rightSide - 80;
            int height = this.height / 6 + 126;

            this.addButton(new ButtonWidget(middle, height, 150, 20, new TranslatableText("easierspeedrunning.doom_mode_screen.line8.reply"), (buttonWidget) -> {
                this.client.openScreen(new ScreenSeven(this.parent, MinecraftClient.getInstance().options));
            }));

            height += 24;
            this.addButton(new ButtonWidget(middle, height, 150, 20, ModTexts.BACK, (button) -> {
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
            drawCenteredText(matrices, this.textRenderer, new TranslatableText("easierspeedrunning.doom_mode_screen.line8"), this.width / 2, 110, 16777215);
            super.render(matrices, mouseX, mouseY, delta);
        }
    }

    protected static class ScreenSeven extends GameOptionsScreen {
        private final Screen parent;

        protected ScreenSeven(Screen parent, GameOptions options) {
            super(parent, options, ModTexts.TITLE_DOOM_MODE);
            this.parent = parent;
        }

        @Override
        protected void init() {
            int leftSide = this.width / 2 - 155;
            int rightSide = leftSide + 160;
            int middle = rightSide - 80;
            int height = this.height / 6 + 126;

            this.addButton(new ButtonWidget(middle, height, 150, 20, new TranslatableText("easierspeedrunning.doom_mode_screen.line9.reply"), (buttonWidget) -> {
                this.client.openScreen(new ScreenEight(this.parent, MinecraftClient.getInstance().options));
            }));
        }

        @Override
        public void onClose() {
            this.client.openScreen(new ModMenuScreen(this.parent, MinecraftClient.getInstance().options));
        }

        @Override
        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            this.renderBackground(matrices);
            drawCenteredText(matrices, this.textRenderer, new TranslatableText("easierspeedrunning.doom_mode_screen.line9"), this.width / 2, 110, 16777215);
            super.render(matrices, mouseX, mouseY, delta);
        }
    }

    protected static class ScreenEight extends GameOptionsScreen {
        private final Screen parent;

        protected ScreenEight(Screen parent, GameOptions options) {
            super(parent, options, ModTexts.TITLE_DOOM_MODE);
            this.parent = parent;
        }

        @Override
        protected void init() {
            int leftSide = this.width / 2 - 155;
            int rightSide = leftSide + 160;
            int middle = rightSide - 80;
            int height = this.height / 6 + 126;

            this.addButton(new ButtonWidget(middle, height, 150, 20, ModTexts.OK, (buttonWidget) -> {
                this.client.openScreen(new ScreenNine(this.parent, MinecraftClient.getInstance().options));
            }));
        }

        @Override
        public void onClose() {
            this.client.openScreen(new ModMenuScreen(this.parent, MinecraftClient.getInstance().options));
        }

        @Override
        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            this.renderBackground(matrices);
            drawCenteredText(matrices, this.textRenderer, new TranslatableText("easierspeedrunning.doom_mode_screen.line10"), this.width / 2, 60, 16777215);
            drawCenteredText(matrices, this.textRenderer, new TranslatableText("easierspeedrunning.doom_mode_screen.line11"), this.width / 2, 90, 16777215);
            drawCenteredText(matrices, this.textRenderer, new TranslatableText("easierspeedrunning.doom_mode_screen.line12"), this.width / 2, 110, 16777215);
            drawCenteredText(matrices, this.textRenderer, new TranslatableText("easierspeedrunning.doom_mode_screen.line13"), this.width / 2, 130, 16777215);
            super.render(matrices, mouseX, mouseY, delta);
        }
    }

    protected static class ScreenNine extends GameOptionsScreen {
        private final Screen parent;

        protected ScreenNine(Screen parent, GameOptions options) {
            super(parent, options, ModTexts.TITLE_DOOM_MODE);
            this.parent = parent;
        }

        @Override
        protected void init() {
            int leftSide = this.width / 2 - 155;
            int rightSide = leftSide + 160;
            int middle = rightSide - 80;
            int height = this.height / 6 + 126;

            this.addButton(new ButtonWidget(middle, height, 150, 20, ModTexts.OK, (buttonWidget) -> {
                this.onClose();
                doomModeButtonAlreadyClicked = 0;
            }));
        }

        @Override
        public void onClose() {
            this.client.openScreen(new ModMenuScreen(this.parent, MinecraftClient.getInstance().options));
        }

        @Override
        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            this.renderBackground(matrices);
            drawCenteredText(matrices, this.textRenderer, new TranslatableText("easierspeedrunning.doom_mode_screen.line14"), this.width / 2, 110, 16777215);
            super.render(matrices, mouseX, mouseY, delta);
        }
    }
}