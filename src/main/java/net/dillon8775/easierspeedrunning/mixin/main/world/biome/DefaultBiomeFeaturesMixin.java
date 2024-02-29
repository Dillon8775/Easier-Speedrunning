package net.dillon8775.easierspeedrunning.mixin.main.world.biome;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {

    /**
     * Increases the spawn rate of trees in the {@link net.minecraft.world.biome.PlainsBiome}.
     */
    @ModifyArg(method = "addPlainsFeatures", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/decorator/CountExtraChanceDecoratorConfig;<init>(IFI)V"), index = 0)
    private static int increaseTreeSpawnRate(int count) {
        return EasierSpeedrunning.getPlainsTreeCount();
    }

    /**
     * Makes diamond ore veins bigger.
     * <p>Using {@link ModifyArg}, we can change the count value in {@link net.minecraft.world.gen.feature.OreFeatureConfig}.</p>
     */
    @ModifyArg(method = "addDefaultOres", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/OreFeatureConfig;<init>(Lnet/minecraft/world/gen/feature/OreFeatureConfig$Target;Lnet/minecraft/block/BlockState;I)V", ordinal = 4), index = 2)
    private static int increaseDiamondVeinSize(int size) {
        return 11;
    }

    /**
     * Makes lapis ore veins bigger.
     * Using {@link ModifyArg}, we can change the count value in {@link net.minecraft.world.gen.feature.OreFeatureConfig}.
     */
    @ModifyArg(method = "addDefaultOres", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/OreFeatureConfig;<init>(Lnet/minecraft/world/gen/feature/OreFeatureConfig$Target;Lnet/minecraft/block/BlockState;I)V", ordinal = 5), index = 2)
    private static int increaseLapisVeinSize(int size) {
        return 8;
    }

    /**
     * Makes diamonds generate more commonly.
     * <p>Using {@link ModifyArgs}, we can change the configuration values in the {@link net.minecraft.world.gen.decorator.RangeDecoratorConfig}.</p>
     */
    @ModifyArgs(method = "addDefaultOres", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/decorator/RangeDecoratorConfig;<init>(IIII)V", ordinal = 4))
    private static void makeDiamondsMoreCommon(Args args) {
        // count
        args.set(0, 2);
        // topOffset
        args.set(3, 18);
    }

    /**
     * Makes lapis generate more commonly.
     * <p>Using {@link ModifyArgs}, we can change the configuration values in the {@link net.minecraft.world.gen.decorator.RangeDecoratorConfig}.</p>
     */
    @ModifyArgs(method = "addDefaultOres", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/decorator/CountDepthDecoratorConfig;<init>(III)V"))
    private static void makeLapisMoreCommon(Args args) {
        // count
        args.set(0, 2);
    }

    /**
     * Makes ancient debris generate more commonly in the nether (large blob).
     * <p>Using {@link ModifyArgs}, we can change the configuration values in the {@link net.minecraft.world.gen.decorator.RangeDecoratorConfig}.</p>
     */
    @ModifyArgs(method = "addAncientDebris", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/decorator/CountDepthDecoratorConfig;<init>(III)V"))
    private static void makeAncientDebrisMoreCommonLarge(Args args) {
        // count
        args.set(0, 2);
    }

    /**
     * Makes ancient debris generate more commonly in the nether (small blob).
     * <p>Using {@link ModifyArgs}, we can change the configuration values in the {@link net.minecraft.world.gen.decorator.RangeDecoratorConfig}.</p>
     */
    @ModifyArgs(method = "addAncientDebris", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/decorator/RangeDecoratorConfig;<init>(IIII)V"))
    private static void makeAncientDebrisMoreCommonSmall(Args args) {
        // count
        args.set(0, 2);
        // bottomOffset
        args.set(1, 6);
        // topOffset
        args.set(2, 18);
    }
}