package net.dillon8775.easierspeedrunning.mixin.main.world;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(OrePlacedFeatures.class)
public abstract class OrePlacedFeaturesMixin {
    @Shadow
    public static final RegistryEntry<PlacedFeature> ORE_DIAMOND, ORE_DIAMOND_LARGE, ORE_DIAMOND_BURIED, ORE_LAPIS, ORE_LAPIS_BURIED, ORE_ANCIENT_DEBRIS_LARGE, ORE_DEBRIS_SMALL;

    static {
        ORE_DIAMOND = PlacedFeatures.register("ore_diamond_" + EasierSpeedrunning.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_SMALL, OrePlacedFeatures.modifiers(CountPlacementModifier.of(4), HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
        ORE_DIAMOND_LARGE = PlacedFeatures.register("ore_diamond_large_" + EasierSpeedrunning.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_LARGE, OrePlacedFeatures.modifiers(CountPlacementModifier.of(4), HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
        ORE_DIAMOND_BURIED = PlacedFeatures.register("ore_diamond_buried_" + EasierSpeedrunning.MOD_ID, OreConfiguredFeatures.ORE_DIAMOND_BURIED, OrePlacedFeatures.modifiers(CountPlacementModifier.of(4), HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80))));
        ORE_LAPIS = PlacedFeatures.register("ore_lapis_" + EasierSpeedrunning.MOD_ID, OreConfiguredFeatures.ORE_LAPIS, OrePlacedFeatures.modifiers(CountPlacementModifier.of(2), HeightRangePlacementModifier.trapezoid(YOffset.fixed(-32), YOffset.fixed(32))));
        ORE_LAPIS_BURIED = PlacedFeatures.register("ore_lapis_buried_" + EasierSpeedrunning.MOD_ID, OreConfiguredFeatures.ORE_LAPIS_BURIED, OrePlacedFeatures.modifiers(CountPlacementModifier.of(2), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64))));
        ORE_ANCIENT_DEBRIS_LARGE = PlacedFeatures.register("ore_ancient_debris_large_" + EasierSpeedrunning.MOD_ID, OreConfiguredFeatures.ORE_ANCIENT_DEBRIS_LARGE, new PlacementModifier[]{CountPlacementModifier.of(2), HeightRangePlacementModifier.trapezoid(YOffset.fixed(8), YOffset.fixed(24)), BiomePlacementModifier.of()});
        ORE_DEBRIS_SMALL = PlacedFeatures.register("ore_debris_small_" + EasierSpeedrunning.MOD_ID, OreConfiguredFeatures.ORE_ANCIENT_DEBRIS_SMALL, new PlacementModifier[]{CountPlacementModifier.of(3), PlacedFeatures.EIGHT_ABOVE_AND_BELOW_RANGE, BiomePlacementModifier.of()});
    }
}