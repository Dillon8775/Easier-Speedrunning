package net.dillon8775.easierspeedrunning.mixin.main.world.biome.nether;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.CrimsonForestBiome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.DOOM_MODE;

@Mixin(CrimsonForestBiome.class)
public class CrimsonForestBiomeMixin extends Biome {

    public CrimsonForestBiomeMixin(Settings settings) {
        super(settings);
    }

    /**
     * Changes the water color in the {@link CrimsonForestBiome}, now that water can be placed in the nether.
     */
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/BiomeEffects$Builder;waterColor(I)Lnet/minecraft/world/biome/BiomeEffects$Builder;"))
    private static int changeWaterColor(int waterColor) {
        return 0xCD6155;
    }

    /**
     * Changes the water fog color in the {@link CrimsonForestBiome}, now that water can be placed in the nether.
     */
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/BiomeEffects$Builder;waterFogColor(I)Lnet/minecraft/world/biome/BiomeEffects$Builder;"))
    private static int changeWaterFogColor(int waterFogColor) {
        return 0xE6B0AA;
    }

    /**
     * Changes the zombified piglin spawn configuration in the {@link CrimsonForestBiome}.
     */
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome$SpawnEntry;<init>(Lnet/minecraft/entity/EntityType;III)V", ordinal = 0))
    private void modifyZombifiedPiglinSpawns(Args args) {
        // minSpawnGroupSize
        args.set(2, 1);
        // maxSpawnGroupSize
        args.set(3, 2);
    }

    /**
     * Changes the hoglin's spawn configuration in the {@link CrimsonForestBiome}.
     */
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome$SpawnEntry;<init>(Lnet/minecraft/entity/EntityType;III)V", ordinal = 1))
    private void modifyHoglinSpawns(Args args) {
        // weight
        args.set(1, DOOM_MODE ? 50 : 6);
        // minSpawnGroupSize
        args.set(2, DOOM_MODE ? 4 : 1);
        // maxSpawnGroupSize
        args.set(3, DOOM_MODE ? 6 : 4);
    }

    /**
     * Changes the piglin's spawn configuration in the {@link CrimsonForestBiome}.
     */
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome$SpawnEntry;<init>(Lnet/minecraft/entity/EntityType;III)V", ordinal = 2))
    private void modifyPiglinSpawns(Args args) {
        // weight
        args.set(1, DOOM_MODE ? 25 : 9);
        // minSpawnGroupSize
        args.set(2, 2);
        // maxSpawnGroupSize
        args.set(3, DOOM_MODE ? 6 : 4);
    }

    /**
     * Makes magma cubes spawn in the {@link CrimsonForestBiome} if {@code doom mode} is {@code ON}.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void addDoomModeMonsterSpawns(CallbackInfo ci) {
        if (DOOM_MODE) {
            this.addSpawn(SpawnGroup.MONSTER, new Biome.SpawnEntry(EntityType.MAGMA_CUBE, 50, 1, 4));
        }
    }
}