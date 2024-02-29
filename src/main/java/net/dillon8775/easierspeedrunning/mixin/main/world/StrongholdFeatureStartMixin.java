package net.dillon8775.easierspeedrunning.mixin.main.world;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StrongholdFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

/**
 * Changes how stronghold's initially generate in a world {@code (changes the min and maximum Y value).}
 */
@Mixin(StrongholdFeature.Start.class)
public abstract class StrongholdFeatureStartMixin extends StructureStart<DefaultFeatureConfig> {

    public StrongholdFeatureStartMixin(StructureFeature<DefaultFeatureConfig> feature, int chunkX, int chunkZ, BlockBox box, int references, long seed) {
        super(feature, chunkX, chunkZ, box, references, seed);
    }

    /**
     * Injects after the initial call of {@link StrongholdFeature.Start#method_14978(int, Random, int)} cancels it, and "replaces" it with the {@link StrongholdFeature.Start#method_14976(Random, int, int)}, which includes a {@code minimum Y value}, to change the minimum Y value that a stronghold can generate at.
     */
    @Inject(method = "init(Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/structure/StructureManager;IILnet/minecraft/world/biome/Biome;Lnet/minecraft/world/gen/feature/DefaultFeatureConfig;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/StrongholdFeature$Start;method_14978(ILjava/util/Random;I)V"), cancellable = true)
    private void cancelAndReplace(ChunkGenerator chunkGenerator, StructureManager structureManager, int i, int j, Biome biome, DefaultFeatureConfig defaultFeatureConfig, CallbackInfo ci) {
        ci.cancel();
        this.method_14976(this.random, EasierSpeedrunning.getStrongholdMinY(), EasierSpeedrunning.getStrongholdMaxY());
    }
}