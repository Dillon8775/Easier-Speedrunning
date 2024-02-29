package net.dillon8775.easierspeedrunning.mixin.main.world;

import com.mojang.serialization.Codec;
import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.MarginedStructureFeature;
import net.minecraft.world.gen.feature.StrongholdFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

/**
 * Changes how stronghold's initially generate in a world {@code (changes the min and maximum Y value).}
 */
@Mixin(StrongholdFeature.class)
public abstract class StrongholdFeatureMixin extends MarginedStructureFeature<DefaultFeatureConfig> {

    public StrongholdFeatureMixin(Codec<DefaultFeatureConfig> codec, StructureGeneratorFactory<DefaultFeatureConfig> structureGeneratorFactory) {
        super(codec, structureGeneratorFactory);
    }

    /**
     * Makes strongholds generate at higher Y levels.
     */
    @ModifyArgs(method = "addPieces", at = @At(value = "INVOKE", target = "Lnet/minecraft/structure/StructurePiecesCollector;shiftInto(IILjava/util/Random;I)V"))
    private static void replace(Args args) {
        args.set(1, EasierSpeedrunning.getStrongholdMinY());
        args.set(0, EasierSpeedrunning.getStrongholdMaxY());
    }
}