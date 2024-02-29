package net.dillon8775.easierspeedrunning.mixin.main.world;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Makes certain biomes, like plains, deserts, and savannas generate more commonly, making getting the requried materials for speedrunning easier.
 <p>See {@link net.minecraft.world.biome.Biome} for biome ids.</p>
 */
@Mixin(value = net.minecraft.world.biome.layer.AddBaseBiomesLayer.class, priority = 999)
public class AddBaseBiomesLayerMixin {
    @Shadow
    private static final int[] DRY_BIOMES = EasierSpeedrunning.options().modifiedBiomes ?
            new int[]{2, 2, 2, 35, 35, 35, 35, 1, 1, 2, 35, 35, 1, 1, 2, 35, 2, 1, 35, 35, 2} :
            new int[]{2, 2, 2, 35, 35, 1};
    @Shadow
    private static final int[] TEMPERATE_BIOMES = EasierSpeedrunning.options().modifiedBiomes ?
            new int[]{4, 29, 2, 2, 2, 1, 1, 35, 35, 2, 35, 1} :
            new int[]{4, 29, 3, 1, 27, 6};
    @Shadow
    private static final int[] COOL_BIOMES = EasierSpeedrunning.options().modifiedBiomes ?
            new int[]{4, 5, 6, 1, 2, 2, 35} :
            new int[]{4, 3, 5, 1};
    @Shadow
    private static final int[] SNOWY_BIOMES = EasierSpeedrunning.options().modifiedBiomes ?
            new int[]{12, 30, 3, 2, 1, 35, 2} :
            new int[]{12, 12, 12, 30};
}