package net.dillon8775.easierspeedrunning.mixin.main.world;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {

    @Overwrite
    public static void addMonsters(net.minecraft.world.biome.SpawnSettings.Builder builder, int zombieWeight, int zombieVillagerWeight, int skeletonWeight) {
        EasierSpeedrunning.modifyMonsterSpawns(builder, zombieWeight, zombieVillagerWeight, skeletonWeight);
    }

    @Overwrite
    public static void addFarmAnimals(net.minecraft.world.biome.SpawnSettings.Builder builder) {
        EasierSpeedrunning.makeAnimalsMoreCommon(builder);
    }

    @Overwrite
    public static void addWarmOceanMobs(net.minecraft.world.biome.SpawnSettings.Builder builder, int squidWeight, int squidMinGroupSize) {
        EasierSpeedrunning.makeDolphinsMoreCommon(builder, squidWeight, squidMinGroupSize);
    }

    @Overwrite
    public static void addEndMobs(net.minecraft.world.biome.SpawnSettings.Builder builder) {
        EasierSpeedrunning.modifyEndMonsterSpawning(builder);
    }
}