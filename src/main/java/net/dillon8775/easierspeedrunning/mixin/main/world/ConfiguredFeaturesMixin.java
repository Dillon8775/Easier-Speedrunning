package net.dillon8775.easierspeedrunning.mixin.main.world;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.dillon8775.easierspeedrunning.option.ModOptions;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DepthAverageDecoratorConfig;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ConfiguredFeatures.class)
public class ConfiguredFeaturesMixin {
    @Shadow
    private static final ConfiguredFeature<?, ?> MONSTER_ROOM, ORE_DIAMOND, ORE_LAPIS, ORE_DEBRIS_LARGE, ORE_DEBRIS_SMALL;

    /**
     * Increases the spawn rate of trees in plains biomes.
     */
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/decorator/CountExtraDecoratorConfig;<init>(IFI)V", ordinal = 18), index = 0)
    private static int increaseTreeSpawnRate(int count) {
        return EasierSpeedrunning.getPlainsTreeCount();
    }

    static {
        ORE_DIAMOND = ConfiguredFeatures.register("ore_diamond_" + EasierSpeedrunning.MOD_ID, Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, ConfiguredFeatures.States.DIAMOND_ORE, 8)).rangeOf(16).spreadHorizontally().repeat(4));
        ORE_LAPIS = ConfiguredFeatures.register("ore_lapis_" + EasierSpeedrunning.MOD_ID, Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, ConfiguredFeatures.States.LAPIS_ORE, 7)).decorate(Decorator.DEPTH_AVERAGE.configure(new DepthAverageDecoratorConfig(16, 16))).spreadHorizontally().repeat(2));
        ORE_DEBRIS_LARGE = ConfiguredFeatures.register("ore_debris_large_" + EasierSpeedrunning.MOD_ID, Feature.NO_SURFACE_ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_NETHER, ConfiguredFeatures.States.ANCIENT_DEBRIS, 3)).decorate(Decorator.DEPTH_AVERAGE.configure(new DepthAverageDecoratorConfig(16, 8))).spreadHorizontally().repeat(2));
        ORE_DEBRIS_SMALL = ConfiguredFeatures.register("ore_debris_small_" + EasierSpeedrunning.MOD_ID, Feature.NO_SURFACE_ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_NETHER, ConfiguredFeatures.States.ANCIENT_DEBRIS, 2)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(8, 16, 128))).spreadHorizontally().repeat(3));
        MONSTER_ROOM = EasierSpeedrunning.options().structureSpawnRates != ModOptions.StructureSpawnRates.OFF ? ConfiguredFeatures.register("monster_room_" + EasierSpeedrunning.MOD_ID, Feature.MONSTER_ROOM.configure(FeatureConfig.DEFAULT).rangeOf(32)).spreadHorizontally().repeat(16) : ConfiguredFeatures.register("monster_room_" + EasierSpeedrunning.MOD_ID, Feature.MONSTER_ROOM.configure(FeatureConfig.DEFAULT).rangeOf(256)).spreadHorizontally().repeat(8);
    }
}