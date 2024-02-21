package net.dillon8775.easierspeedrunning.client.screen;

import net.dillon8775.easierspeedrunning.client.util.ModTexts;
import net.dillon8775.easierspeedrunning.option.ClientModOptions;
import net.dillon8775.easierspeedrunning.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.SaveLevelScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.info;
import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.options;
import static net.dillon8775.easierspeedrunning.EasierSpeedrunningClient.clientOptions;

@Environment(EnvType.CLIENT)
public class RestartRequiredScreen extends GameOptionsScreen {
    private static ModOptions.StructureSpawnRates currentMakeStructuresMoreCommon;
    private static boolean currentBetterBiomes;
    private static boolean currentDoomMode;
    private static int currentStrongholdPortalRoomCount;
    private static int currentStrongholdLibraryCount;
    private static int currentStrongholdDistance;
    private static int currentStrongholdSpread;
    private static int currentStrongholdCount;
    private static ClientModOptions.Panorama currentPanorama;

    public RestartRequiredScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_RESTART_REQUIRED);
    }

    @Override
    protected void init() {
        int height = this.height / 6 + 126;
        this.addButton(new ButtonWidget(this.width / 2 - 50 - 105, height, 100, 20, ModTexts.RESTART_NOW, (buttonWidget) -> {
            if (this.client.isInSingleplayer()) {
                this.client.world.disconnect();
                this.client.disconnect(new SaveLevelScreen(new TranslatableText("menu.savingLevel")));
            } else {
                this.client.disconnect();
            }
            ModOptions.saveConfig();
            ClientModOptions.saveClientConfig();
            info("Closing game! Re-launch to apply changes.");
            this.client.scheduleStop();
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 50, height, 100, 20, ModTexts.REVERT_CHANGES, (buttonWidget) -> {
            revertChanges();
            ModOptions.saveConfig();
            ClientModOptions.saveClientConfig();
            info("Changes reverted.");
            this.client.openScreen(this.parent);
        }));
        this.addButton(new ButtonWidget(this.width / 2 - 50 + 105, height, 100, 20, ModTexts.RESTART_LATER, (buttonWidget) -> {
            this.onClose();
        }));
    }

    @Override
    public void onClose() {
        ModOptions.saveConfig();
        ClientModOptions.saveClientConfig();
        info("Saved changes.");
        this.client.openScreen(this.parent);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        this.drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 0xFFFFFF);
        this.drawCenteredText(matrices, this.textRenderer, new TranslatableText("easierspeedrunning.restart_required.line1"), this.width / 2, 110, 16777215);
        this.drawCenteredText(matrices, this.textRenderer, new TranslatableText("easierspeedrunning.restart_required.line2"), this.width / 2, 130, 16777215);
        super.render(matrices, mouseX, mouseY, delta);
    }

    public static void getCurrentOptions() {
        currentMakeStructuresMoreCommon = options().structureSpawnRates;
        currentBetterBiomes = options().modifiedBiomes;
        currentDoomMode = options().doomMode;
        currentStrongholdPortalRoomCount = options().strongholdPortalRoomCount;
        currentStrongholdLibraryCount = options().strongholdLibraryCount;
        currentStrongholdDistance = options().strongholdDistance;
        currentStrongholdSpread = options().strongholdSpread;
        currentStrongholdCount = options().strongholdCount;
        currentPanorama = clientOptions().panorama;
    }

    public static boolean needsRestart() {
        return currentMakeStructuresMoreCommon != options().structureSpawnRates ||
                currentBetterBiomes != options().modifiedBiomes ||
                currentDoomMode != options().doomMode ||
                currentStrongholdPortalRoomCount != options().strongholdPortalRoomCount ||
                currentStrongholdLibraryCount != options().strongholdLibraryCount ||
                currentStrongholdDistance != options().strongholdDistance ||
                currentStrongholdSpread != options().strongholdSpread ||
                currentStrongholdCount != options().strongholdCount ||
                currentPanorama != clientOptions().panorama;
    }

    private static void revertChanges() {
        options().structureSpawnRates = currentMakeStructuresMoreCommon;
        options().modifiedBiomes = currentBetterBiomes;
        options().doomMode = currentDoomMode;
        options().strongholdPortalRoomCount = currentStrongholdPortalRoomCount;
        options().strongholdLibraryCount = currentStrongholdLibraryCount;
        options().strongholdDistance = currentStrongholdDistance;
        options().strongholdSpread = currentStrongholdSpread;
        options().strongholdCount = currentStrongholdSpread;
        clientOptions().panorama = currentPanorama;
    }
}