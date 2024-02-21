package net.dillon8775.easierspeedrunning.mixin.main.world;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.NetherFortressFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.DOOM_MODE;

/**
 * Changes the mob spawn rates in nether fortresses, making blazes more common, and lowering spawn rates of wither skeletons and other non-useful speedrunning mobs.
 */
@Mixin(NetherFortressFeature.class)
public class NetherFortressFeatureMixin {
    @Shadow
    private static final List<Biome.SpawnEntry> MONSTER_SPAWNS = DOOM_MODE ? ImmutableList.of(
            new Biome.SpawnEntry(EntityType.BLAZE, 50, 1, 4),
            new Biome.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 25, 1, 1),
            new Biome.SpawnEntry(EntityType.WITHER_SKELETON, 75, 4, 12),
            new Biome.SpawnEntry(EntityType.SKELETON, 50, 5, 5),
            new Biome.SpawnEntry(EntityType.MAGMA_CUBE, 20, 1, 4)) :

            ImmutableList.of(new Biome.SpawnEntry(EntityType.BLAZE, 15, 1, 4),
                    new Biome.SpawnEntry(EntityType.PIGLIN, 15, 2, 4),
                    new Biome.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 3, 1, 2),
                    new Biome.SpawnEntry(EntityType.WITHER_SKELETON, 8, 1, 2),
                    new Biome.SpawnEntry(EntityType.SKELETON, 1, 1, 2),
                    new Biome.SpawnEntry(EntityType.MAGMA_CUBE, 1, 1, 1));
}