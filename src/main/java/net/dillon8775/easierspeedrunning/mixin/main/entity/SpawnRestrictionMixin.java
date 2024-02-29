package net.dillon8775.easierspeedrunning.mixin.main.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Random;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.DOOM_MODE;

/**
 * Prevents Piglin brutes from spawning midair.
 */
@Mixin(SpawnRestriction.class)
public class SpawnRestrictionMixin {

    static {
        if (DOOM_MODE) {
            SpawnRestriction.register(EntityType.PIGLIN_BRUTE, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnRestrictionMixin::canPiglinBruteSpawn);
        }
    }

    @Unique
    private static boolean canPiglinBruteSpawn(EntityType<PiglinBruteEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return !world.getBlockState(pos.down()).isOf(Blocks.NETHER_WART_BLOCK);
    }
}