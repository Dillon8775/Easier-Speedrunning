package net.dillon8775.easierspeedrunning.mixin.client.screen;

import net.dillon8775.easierspeedrunning.EasierSpeedrunningClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(CreateWorldScreen.class)
public abstract class CreateWorldScreenMixin {
    @Shadow
    public boolean hardcore;
    @Shadow
    private Difficulty field_24289;
    @Shadow
    private Difficulty field_24290;
    @Shadow
    private boolean cheatsEnabled;
    @Shadow
    protected abstract void createLevel();
    @Shadow
    private CreateWorldScreen.Mode currentMode;

    @Inject(method = "init", at = @At("TAIL"))
    private void fastWorldCreation(CallbackInfo ci) {
        if (EasierSpeedrunningClient.clientOptions().fastWorldCreation) {
            Difficulty difficulty = null;

            switch (EasierSpeedrunningClient.clientOptions().difficulty) {
                case PEACEFUL:
                    difficulty = Difficulty.PEACEFUL;
                    break;
                case EASY:
                    difficulty = Difficulty.EASY;
                    break;
                case NORMAL:
                    difficulty = Difficulty.NORMAL;
                    break;
                case HARD:
                    difficulty = Difficulty.HARD;
                    break;
                case HARDCORE:
                    difficulty = Difficulty.HARD;
                    this.hardcore = true;
                    break;
            }

            CreateWorldScreen.Mode mode = null;
            switch (EasierSpeedrunningClient.clientOptions().gameMode) {
                case SURVIVAL:
                    mode = CreateWorldScreen.Mode.SURVIVAL;
                    break;
                case CREATIVE:
                    mode = CreateWorldScreen.Mode.CREATIVE;
                    break;
                case SPECTATOR:
                    mode = CreateWorldScreen.Mode.DEBUG;
                    break;
            }

            assert mode != null;
            assert difficulty != null;
            currentMode = mode;
            field_24289 = difficulty;
            field_24290 = difficulty;
            this.cheatsEnabled = EasierSpeedrunningClient.clientOptions().allowCheats;
            createLevel();
        }
    }
}