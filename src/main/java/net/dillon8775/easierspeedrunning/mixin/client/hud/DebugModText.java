package net.dillon8775.easierspeedrunning.mixin.client.hud;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.DOOM_MODE;

@Environment(EnvType.CLIENT)
@Mixin(DebugHud.class)
public class DebugModText {

    /**
     * Adds the "easier speedrunning" and the mod version text to the F3 menu.
     */
    @Inject(method = "getRightText", at = @At("RETURN"), cancellable = true)
    private void getRightText(CallbackInfoReturnable<List<String>> cir) {
        List<String> returnValue = cir.getReturnValue();
        returnValue.add(EasierSpeedrunning.EASIER_SPEEDRUNNING_STRING + " " + EasierSpeedrunning.MOD_VERSION);
        if (DOOM_MODE) {
            returnValue.add("What's that? Doom Mode? Oh, flip.");
        }
    }
}