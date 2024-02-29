package net.dillon8775.easierspeedrunning.mixin.main.world.biome.nether;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.WarpedForestBiome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.DOOM_MODE;

@Mixin(WarpedForestBiome.class)
public class WarpedForestBiomeMixin extends Biome {

    public WarpedForestBiomeMixin(Settings settings) {
        super(settings);
    }

    /**
     * Changes the water color in the {@link WarpedForestBiome}, now that water can be placed in the nether.
     */
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/BiomeEffects$Builder;waterColor(I)Lnet/minecraft/world/biome/BiomeEffects$Builder;"))
    private static int changeWaterColor(int waterColor) {
        return 0x167E86;
    }

    /**
     * Changes the water fog color in the {@link WarpedForestBiome}, now that water can be placed in the nether.
     */
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/BiomeEffects$Builder;waterFogColor(I)Lnet/minecraft/world/biome/BiomeEffects$Builder;"))
    private static int changeWaterFogColor(int waterFogColor) {
        return 0x14B485;
    }

    /**
     * Changes the enderman spawn configuration in the {@link WarpedForestBiome}.
     */
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome$SpawnEntry;<init>(Lnet/minecraft/entity/EntityType;III)V", ordinal = 0))
    private void modifyEndermanSpawns(Args args) {
        if (!DOOM_MODE) {
            // weight
            args.set(1, 5);
        }

        if (DOOM_MODE) {
            args.set(2, 1);
        }
    }

    /**
     * Makes piglins spawn in the {@link WarpedForestBiome}, and if {@code doom mode} is {@code ON}, then also spawn hoglins and magma cubes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void addSpawns(CallbackInfo ci) {
        this.addSpawn(SpawnGroup.MONSTER, new Biome.SpawnEntry(EntityType.PIGLIN, DOOM_MODE ? 25 : 5, DOOM_MODE ? 4 : 1, DOOM_MODE ? 6 : 4));
        if (DOOM_MODE) {
            this.addSpawn(SpawnGroup.MONSTER, new Biome.SpawnEntry(EntityType.HOGLIN, 50, 1, 4));
            this.addSpawn(SpawnGroup.MONSTER, new Biome.SpawnEntry(EntityType.MAGMA_CUBE, 50, 1, 4));
        }
    }
}