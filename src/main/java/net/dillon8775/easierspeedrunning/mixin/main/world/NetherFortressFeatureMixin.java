package net.dillon8775.easierspeedrunning.mixin.main.world;

import net.minecraft.entity.EntityType;
import net.minecraft.util.collection.Pool;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.NetherFortressFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.DOOM_MODE;

/**
 * Changes the mob spawn rates in nether fortresses, making blazes more common, and lowering spawn rates of wither skeletons and other non-useful speedrunning mobs.
 */
@Mixin(NetherFortressFeature.class)
public class NetherFortressFeatureMixin {
    @Shadow
    private static final Pool<SpawnSettings.SpawnEntry> MONSTER_SPAWNS = DOOM_MODE ? Pool.of(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 50, 1, 4),
            new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 25, 1, 1),
            new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 75, 4, 12),
            new SpawnSettings.SpawnEntry(EntityType.SKELETON, 50, 5, 5),
            new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 20, 1, 4)) :

            Pool.of(new SpawnSettings.SpawnEntry(EntityType.BLAZE, 15, 1, 4),
                    new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 15, 2, 4),
                    new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 3, 1, 2),
                    new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 8, 1, 2),
                    new SpawnSettings.SpawnEntry(EntityType.SKELETON, 1, 1, 2),
                    new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 1, 1, 1));
}