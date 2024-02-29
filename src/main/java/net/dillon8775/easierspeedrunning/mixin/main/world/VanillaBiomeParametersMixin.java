package net.dillon8775.easierspeedrunning.mixin.main.world;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Makes certain biomes, like plains, deserts, and savannas generate more commonly, making getting the requried materials for speedrunning easier.
 <p>See {@link net.minecraft.world.biome.Biome} for biome ids.</p>
 */
@Mixin(VanillaBiomeParameters.class)
public class VanillaBiomeParametersMixin {
    @Shadow @Final @Mutable
    private RegistryKey<Biome>[][] commonBiomes;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        this.commonBiomes = EasierSpeedrunning.options().modifiedBiomes ? new RegistryKey[][]{{
            BiomeKeys.PLAINS, BiomeKeys.PLAINS, BiomeKeys.PLAINS, BiomeKeys.DESERT, BiomeKeys.SAVANNA},
                {BiomeKeys.PLAINS, BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA},
                {BiomeKeys.FLOWER_FOREST, BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.DESERT, BiomeKeys.DARK_FOREST},
                {BiomeKeys.SAVANNA, BiomeKeys.SAVANNA, BiomeKeys.FOREST, BiomeKeys.JUNGLE, BiomeKeys.PLAINS},
                {BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT}} :

                new RegistryKey[][]{{BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_PLAINS,
                        BiomeKeys.SNOWY_TAIGA, BiomeKeys.TAIGA}, {BiomeKeys.PLAINS, BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA},
                        {BiomeKeys.FLOWER_FOREST, BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.BIRCH_FOREST, BiomeKeys.DARK_FOREST},
                        {BiomeKeys.SAVANNA, BiomeKeys.SAVANNA, BiomeKeys.FOREST, BiomeKeys.JUNGLE, BiomeKeys.JUNGLE},
                        {BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT, BiomeKeys.DESERT}};
    }
}