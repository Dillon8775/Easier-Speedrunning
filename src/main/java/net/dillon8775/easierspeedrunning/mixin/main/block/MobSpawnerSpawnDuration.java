package net.dillon8775.easierspeedrunning.mixin.main.block;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.world.MobSpawnerLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Changes the {@code minimum} and {@code maximum} time it takes for mob spawner blocks to spawn mobs.
 */
@Mixin(value = MobSpawnerLogic.class, priority = 999)
public class MobSpawnerSpawnDuration {
    @Shadow
    private int minSpawnDelay = EasierSpeedrunning.options().mobSpawnerMinimumSpawnDuration * 10;
    @Shadow
    private int maxSpawnDelay = EasierSpeedrunning.options().mobSpawnerMaximumSpawnDuration * 10;
}