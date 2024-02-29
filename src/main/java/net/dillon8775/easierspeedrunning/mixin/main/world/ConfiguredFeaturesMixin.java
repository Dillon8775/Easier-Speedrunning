package net.dillon8775.easierspeedrunning.mixin.main.world;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.dillon8775.easierspeedrunning.option.ModOptions;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ConfiguredFeatures.class)
public class ConfiguredFeaturesMixin {
    @Shadow
    public static final ConfiguredFeature<?, ?> MONSTER_ROOM, ORE_DIAMOND, PROTOTYPE_ORE_DIAMOND, PROTOTYPE_ORE_DIAMOND_LARGE, ORE_LAPIS, PROTOTYPE_ORE_LAPIS, PROTOTYPE_ORE_LAPIS_BURIED, ORE_DEBRIS_LARGE, ORE_DEBRIS_SMALL;

    /**
     * Increases the spawn rate of trees in plains biomes.
     */
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/decorator/CountExtraDecoratorConfig;<init>(IFI)V", ordinal = 14), index = 0)
    private static int increaseTreeSpawnRate(int count) {
        return EasierSpeedrunning.getPlainsTreeCount();
    }

    static {
        ORE_DIAMOND = ConfiguredFeatures.register("ore_diamond_" + EasierSpeedrunning.MOD_ID, (ConfiguredFeature)((ConfiguredFeature)((ConfiguredFeature)Feature.ORE.configure(new OreFeatureConfig(ConfiguredFeatures.DIAMOND_ORE_TARGETS, 8)).uniformRange(YOffset.getBottom(), YOffset.fixed(15))).spreadHorizontally()).repeat(4));
        PROTOTYPE_ORE_DIAMOND = ConfiguredFeatures.register("prototype_ore_diamond_" + EasierSpeedrunning.MOD_ID, (ConfiguredFeature)((ConfiguredFeature)((ConfiguredFeature)Feature.ORE.configure(new OreFeatureConfig(ConfiguredFeatures.DIAMOND_ORE_TARGETS, 4, 0.5F)).triangleRange(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))).spreadHorizontally()).repeat(6));
        PROTOTYPE_ORE_DIAMOND_LARGE = ConfiguredFeatures.register("prototype_ore_diamond_large_" + EasierSpeedrunning.MOD_ID, (ConfiguredFeature)((ConfiguredFeature)((ConfiguredFeature)((ConfiguredFeature)Feature.ORE.configure(new OreFeatureConfig(ConfiguredFeatures.DIAMOND_ORE_TARGETS, 12, 0.7F)).triangleRange(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))).spreadHorizontally()).applyChance(9)).repeat(4));
        ORE_LAPIS = ConfiguredFeatures.register("ore_lapis_" + EasierSpeedrunning.MOD_ID, (ConfiguredFeature)((ConfiguredFeature)((ConfiguredFeature)Feature.ORE.configure(new OreFeatureConfig(ConfiguredFeatures.LAPIS_ORE_TARGETS, 7)).triangleRange(YOffset.fixed(0), YOffset.fixed(30))).spreadHorizontally()).repeat(2));
        PROTOTYPE_ORE_LAPIS = ConfiguredFeatures.register("prototype_ore_lapis_" + EasierSpeedrunning.MOD_ID, (ConfiguredFeature)((ConfiguredFeature)((ConfiguredFeature)Feature.ORE.configure(new OreFeatureConfig(ConfiguredFeatures.LAPIS_ORE_TARGETS, 7)).triangleRange(YOffset.fixed(-32), YOffset.fixed(32))).spreadHorizontally()).repeat(2));
        PROTOTYPE_ORE_LAPIS_BURIED = ConfiguredFeatures.register("prototype_ore_lapis_buried_" + EasierSpeedrunning.MOD_ID, (ConfiguredFeature)((ConfiguredFeature)((ConfiguredFeature)Feature.SCATTERED_ORE.configure(new OreFeatureConfig(ConfiguredFeatures.LAPIS_ORE_TARGETS, 7, 1.0F)).uniformRange(YOffset.getBottom(), YOffset.fixed(64))).spreadHorizontally()).repeat(4));
        ORE_DEBRIS_LARGE = ConfiguredFeatures.register("ore_debris_large_" + EasierSpeedrunning.MOD_ID, (ConfiguredFeature)((ConfiguredFeature)((ConfiguredFeature)Feature.SCATTERED_ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_NETHER, ConfiguredFeatures.States.ANCIENT_DEBRIS, 3, 1.0F)).triangleRange(YOffset.fixed(8), YOffset.fixed(24))).spreadHorizontally()).repeat(2));
        ORE_DEBRIS_SMALL = ConfiguredFeatures.register("ore_debris_small" + EasierSpeedrunning.MOD_ID, (ConfiguredFeature)((ConfiguredFeature)((ConfiguredFeature)Feature.SCATTERED_ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_NETHER, ConfiguredFeatures.States.ANCIENT_DEBRIS, 2, 1.0F)).range(ConfiguredFeatures.Decorators.BOTTOM_TO_TOP_OFFSET_8)).spreadHorizontally()).repeat(3));
        MONSTER_ROOM = EasierSpeedrunning.options().structureSpawnRates != ModOptions.StructureSpawnRates.OFF ? ConfiguredFeatures.register("monster_room_" + EasierSpeedrunning.MOD_ID, (ConfiguredFeature)((ConfiguredFeature)((ConfiguredFeature)Feature.MONSTER_ROOM.configure(FeatureConfig.DEFAULT).range(ConfiguredFeatures.Decorators.BOTTOM_TO_TOP)).spreadHorizontally()).repeat(16)) : ConfiguredFeatures.register("monster_room_" + EasierSpeedrunning.MOD_ID, (ConfiguredFeature)((ConfiguredFeature)((ConfiguredFeature)Feature.MONSTER_ROOM.configure(FeatureConfig.DEFAULT).range(ConfiguredFeatures.Decorators.BOTTOM_TO_TOP)).spreadHorizontally()).repeat(8));
    }
}