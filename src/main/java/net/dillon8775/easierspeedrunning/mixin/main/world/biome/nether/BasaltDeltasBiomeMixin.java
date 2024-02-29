package net.dillon8775.easierspeedrunning.mixin.main.world.biome.nether;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.BasaltDeltasBiome;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.DOOM_MODE;

@Mixin(BasaltDeltasBiome.class)
public class BasaltDeltasBiomeMixin extends Biome {

    public BasaltDeltasBiomeMixin(Settings settings) {
        super(settings);
    }

    /**
     * Changes the water color in the {@link BasaltDeltasBiome}, now that water can be placed in the nether.
     */
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/BiomeEffects$Builder;waterColor(I)Lnet/minecraft/world/biome/BiomeEffects$Builder;"))
    private static int changeWaterColor(int waterColor) {
        return 0xCACFD2;
    }

    /**
     * Changes the water fog color in the {@link BasaltDeltasBiome}, now that water can be placed in the nether.
     */
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/BiomeEffects$Builder;waterFogColor(I)Lnet/minecraft/world/biome/BiomeEffects$Builder;"))
    private static int changeWaterFogColor(int waterFogColor) {
        return 0xD5DBDB;
    }

    /**
     * Changes the ghast spawn configuration in the {@link BasaltDeltasBiome}.
     */
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome$SpawnEntry;<init>(Lnet/minecraft/entity/EntityType;III)V", ordinal = 0))
    private void modifyGhastSpawns(Args args) {
        // weight
        args.set(1, DOOM_MODE ? 40 : 25);
    }

    /**
     * Changes the magma cube spawn configuration in the {@link BasaltDeltasBiome}.
     */
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome$SpawnEntry;<init>(Lnet/minecraft/entity/EntityType;III)V", ordinal = 1))
    private void modifyMagmaCubeSpawns(Args args) {
        // weight
        args.set(1, DOOM_MODE ? 50 : 25);
        // minSpawnGroupSize
        args.set(2, 1);
        // maxSpawnGroupSize
        args.set(3, 4);
    }

    /**
     * Makes wither skeletons spawn in the {@link BasaltDeltasBiome} if {@code doom mode} is {@code ON}.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void addDoomModeMonsterSpawns(CallbackInfo ci) {
        if (DOOM_MODE) {
            this.addSpawn(SpawnGroup.MONSTER, new Biome.SpawnEntry(EntityType.WITHER_SKELETON, 50, 1, 4));
        }
    }
}