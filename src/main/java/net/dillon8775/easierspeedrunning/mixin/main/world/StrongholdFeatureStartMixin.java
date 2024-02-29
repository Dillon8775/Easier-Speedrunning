package net.dillon8775.easierspeedrunning.mixin.main.world;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.structure.MarginedStructureStart;
import net.minecraft.structure.StructureManager;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.HeightLimitView;
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
public abstract class StrongholdFeatureStartMixin extends MarginedStructureStart<DefaultFeatureConfig> {

    public StrongholdFeatureStartMixin(StructureFeature<DefaultFeatureConfig> structureFeature, ChunkPos chunkPos, int i, long l) {
        super(structureFeature, chunkPos, i, l);
    }

    /**
     * Injects after the initial call of {@link StrongholdFeature.Start#randomUpwardTranslation(int, int, Random, int)} cancels it, and "replaces" it with the {@link StrongholdFeature.Start#randomUpwardTranslation(Random, int, int)}, which includes a {@code minimum Y value}, to change the minimum Y value that a stronghold can generate at.
     */
    @Inject(method = "init(Lnet/minecraft/util/registry/DynamicRegistryManager;Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/structure/StructureManager;Lnet/minecraft/util/math/ChunkPos;Lnet/minecraft/world/biome/Biome;Lnet/minecraft/world/gen/feature/DefaultFeatureConfig;Lnet/minecraft/world/HeightLimitView;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/StrongholdFeature$Start;randomUpwardTranslation(IILjava/util/Random;I)V"), cancellable = true)
    private void cancelAndReplace(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos, Biome biome, DefaultFeatureConfig defaultFeatureConfig, HeightLimitView heightLimitView, CallbackInfo ci) {
        ci.cancel();
        this.randomUpwardTranslation(this.random, EasierSpeedrunning.getStrongholdMinY(), EasierSpeedrunning.getStrongholdMaxY());
    }
}