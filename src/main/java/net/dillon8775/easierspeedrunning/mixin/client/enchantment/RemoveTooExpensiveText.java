package net.dillon8775.easierspeedrunning.mixin.client.enchantment;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Environment(EnvType.CLIENT)
@Mixin(AnvilScreen.class)
public class RemoveTooExpensiveText {

    /**
     * Makes sure that the number on the {@link AnvilScreen} renders correctly with the {@code better anvil} option.
     */
    @ModifyConstant(method = "drawForeground", constant = @Constant(intValue = 40))
    private int mixinLimitInt(int i) {
        if (EasierSpeedrunning.options().betterAnvil) {
            return Integer.MAX_VALUE;
        } else {
            return 40;
        }
    }
}