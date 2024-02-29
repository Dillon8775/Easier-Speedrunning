package net.dillon8775.easierspeedrunning.mixin.main.world.biome.nether;

import net.minecraft.world.biome.SoulSandValleyBiome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.DOOM_MODE;

@Mixin(SoulSandValleyBiome.class)
public class SoulSandValleyBiomeMixin {

    /**
     * Changes the water color in the {@link SoulSandValleyBiome}, now that water can be placed in the nether.
     */
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/BiomeEffects$Builder;waterColor(I)Lnet/minecraft/world/biome/BiomeEffects$Builder;"))
    private static int changeWaterColor(int waterColor) {
        return 0xD98880;
    }

    /**
     * Changes the water fog color in the {@link SoulSandValleyBiome}, now that water can be placed in the nether.
     */
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/BiomeEffects$Builder;waterFogColor(I)Lnet/minecraft/world/biome/BiomeEffects$Builder;"))
    private static int changeWaterFogColor(int waterFogColor) {
        return 0xE6B0AA;
    }

    /**
     * Changes the skeleton's spawn configuration in the {@link SoulSandValleyBiome}.
     */
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome$SpawnEntry;<init>(Lnet/minecraft/entity/EntityType;III)V", ordinal = 0))
    private void modifySkeletonSpawns(Args args) {
        // weight
        args.set(1, DOOM_MODE ? 50 : 10);
        if (!DOOM_MODE) {
            // minSpawnGroupSize
            args.set(2, 1);
            // maxSpawnGroupSize
            args.set(3, 4);
        }
    }

    /**
     * Changes the ghast's spawn configuration in the {@link SoulSandValleyBiome}.
     */
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome$SpawnEntry;<init>(Lnet/minecraft/entity/EntityType;III)V", ordinal = 1))
    private void modifyGhastSpawns(Args args) {
        if (!DOOM_MODE) {
            // minSpawnGroupSize
            args.set(2, 1);
        }
    }

    /**
     * Changes the enderman spawn configuration in the {@link SoulSandValleyBiome}.
     */
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome$SpawnEntry;<init>(Lnet/minecraft/entity/EntityType;III)V", ordinal = 2))
    private void modifyEndermanSpawns(Args args) {
        // weight
        args.set(1, DOOM_MODE ? 10 : 5);
    }
}