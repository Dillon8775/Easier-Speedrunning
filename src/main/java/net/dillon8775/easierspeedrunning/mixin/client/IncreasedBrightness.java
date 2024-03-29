package net.dillon8775.easierspeedrunning.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.options.DoubleOption;
import net.minecraft.client.options.GameOptions;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
@Mixin(DoubleOption.class)
public class IncreasedBrightness {
    @Shadow @Final @Mutable
    private BiFunction<GameOptions, DoubleOption, Text> displayStringGetter;
    @Shadow @Final @Mutable
    protected double min;
    @Shadow @Final @Mutable
    protected float step;
    @Shadow @Mutable
    protected double max;

    /**
     * Sets the default maximum brightness to 500, instead of 100.
     */
    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(String key, double min, double max, float step, Function<GameOptions, Double> getter, BiConsumer<GameOptions, Double> setter, BiFunction<GameOptions, DoubleOption, Text> displayStringGetter, CallbackInfo ci) {
        if (key.equals("options.gamma")) {
            this.min = 1.0D;
            this.max = 5.0D;
            this.step = 0.1F;
            this.displayStringGetter = this::displayStringGetter;
        }
    }

    private Text displayStringGetter(GameOptions gameOptions, DoubleOption doubleOption) {
        return new TranslatableText("options.gamma").append(": ").append(gameOptions.gamma == 0 ? new TranslatableText("options.gamma.min") : gameOptions.gamma == 1 ? new TranslatableText("options.gamma.max") : new LiteralText(Math.round(gameOptions.gamma * 100) + "%"));
    }
}