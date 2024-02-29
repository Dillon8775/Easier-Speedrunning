package net.dillon8775.easierspeedrunning.mixin.main.world;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.BiomePlacementModifier;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.PlacementModifier;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(OrePlacedFeatures.class)
public class OrePlacedFeaturesMixin {
    @Shadow
    private static final PlacedFeature ORE_DIAMOND, ORE_DIAMOND_LARGE, ORE_DIAMOND_BURIED, ORE_LAPIS, ORE_LAPIS_BURIED, ORE_ANCIENT_DEBRIS_LARGE, ORE_DEBRIS_SMALL;

    static {
        ORE_DIAMOND = PlacedFeatures.register("ore_diamond_" + EasierSpeedrunning.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_SMALL.withPlacement(CountPlacementModifier.of(4), HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
        ORE_DIAMOND_LARGE = PlacedFeatures.register("ore_diamond_large_" + EasierSpeedrunning.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_LARGE.withPlacement(CountPlacementModifier.of(4), HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
        ORE_DIAMOND_BURIED = PlacedFeatures.register("ore_diamond_buried_" + EasierSpeedrunning.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_BURIED.withPlacement(CountPlacementModifier.of(4), HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
        ORE_LAPIS = PlacedFeatures.register("ore_lapis_" + EasierSpeedrunning.MOD_ID, OreConfiguredFeatures.ORE_LAPIS.withPlacement(CountPlacementModifier.of(2), HeightRangePlacementModifier.trapezoid(YOffset.fixed(-32), YOffset.fixed(32))));
        ORE_LAPIS_BURIED = PlacedFeatures.register("ore_lapis_buried_" + EasierSpeedrunning.MOD_ID, OreConfiguredFeatures.ORE_LAPIS_BURIED.withPlacement(CountPlacementModifier.of(2), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));
        ORE_ANCIENT_DEBRIS_LARGE = PlacedFeatures.register("ore_ancient_debris_large_" + EasierSpeedrunning.MOD_ID, OreConfiguredFeatures.ORE_ANCIENT_DEBRIS_LARGE.withPlacement(new PlacementModifier[]{CountPlacementModifier.of(2), HeightRangePlacementModifier.trapezoid(YOffset.fixed(8), YOffset.fixed(24)), BiomePlacementModifier.of()}));
        ORE_DEBRIS_SMALL = PlacedFeatures.register("ore_debris_small_" + EasierSpeedrunning.MOD_ID, OreConfiguredFeatures.ORE_ANCIENT_DEBRIS_SMALL.withPlacement(new PlacementModifier[]{CountPlacementModifier.of(3), PlacedFeatures.EIGHT_ABOVE_AND_BELOW_RANGE, BiomePlacementModifier.of()}));
    }
}