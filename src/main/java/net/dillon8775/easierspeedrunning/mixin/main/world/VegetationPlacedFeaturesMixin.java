package net.dillon8775.easierspeedrunning.mixin.main.world;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(VegetationPlacedFeatures.class)
public class VegetationPlacedFeaturesMixin {

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/PlacedFeatures;createCountExtraModifier(IFI)Lnet/minecraft/world/gen/decorator/PlacementModifier;", ordinal = 0), index = 0)
    private static int increaseTreeSpawnRate(int count) {
        return EasierSpeedrunning.getPlainsTreeCount();
    }
}