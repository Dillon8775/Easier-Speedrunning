package net.dillon8775.easierspeedrunning.mixin.main.world.biome.nether;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.NetherWastesBiome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.DOOM_MODE;

@Mixin(NetherWastesBiome.class)
public class NetherWastesBiomeMixin extends Biome {

    public NetherWastesBiomeMixin(Settings settings) {
        super(settings);
    }

    /**
     * Changes the water color in the {@link NetherWastesBiome}, now that water can be placed in the nether.
     */
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/BiomeEffects$Builder;waterColor(I)Lnet/minecraft/world/biome/BiomeEffects$Builder;"))
    private static int changeWaterColor(int waterColor) {
        return 0xD98880;
    }

    /**
     * Changes the water fog color in the {@link NetherWastesBiome}, now that water can be placed in the nether.
     */
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/BiomeEffects$Builder;waterFogColor(I)Lnet/minecraft/world/biome/BiomeEffects$Builder;"))
    private static int changeWaterFogColor(int waterFogColor) {
        return 0xE6B0AA;
    }

    /**
     * Changes the ghast's spawn configuration in the {@link NetherWastesBiome}.
     */
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome$SpawnEntry;<init>(Lnet/minecraft/entity/EntityType;III)V", ordinal = 0))
    private void modifyGhastSpawns(Args args) {
        // weight
        args.set(1, 20);
        if (!DOOM_MODE) {
            // minSpawnGroupSize
            args.set(2, 1);
        }
    }

    /**
     * Changes the zombified piglin's spawn configuration in the {@link NetherWastesBiome}.
     */
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome$SpawnEntry;<init>(Lnet/minecraft/entity/EntityType;III)V", ordinal = 1))
    private void modifyZombifiedPiglinSpawns(Args args) {
        // weight
        args.set(1, DOOM_MODE ? 50 : 25);
        // minSpawnGroupSize
        args.set(2, DOOM_MODE ? 4 : 1);
    }

    /**
     * Changes the magma cube spawn configuration in the {@link NetherWastesBiome}.
     */
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome$SpawnEntry;<init>(Lnet/minecraft/entity/EntityType;III)V", ordinal = 2))
    private void modifyMagmaCubeSpawns(Args args) {
        if (DOOM_MODE) {
            // weight
            args.set(1, 50);
        }
        // minSpawnGroupSize
        args.set(2, 1);
    }

    /**
     * Changes the enderman's spawn configuration in the {@link NetherWastesBiome}.
     */
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome$SpawnEntry;<init>(Lnet/minecraft/entity/EntityType;III)V", ordinal = 3))
    private void modifyEndermanSpawns(Args args) {
        // weight
        args.set(1, DOOM_MODE ? 20 : 10);
    }

    /**
     * Changes the piglin's spawn configuration in the {@link NetherWastesBiome}.
     */
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome$SpawnEntry;<init>(Lnet/minecraft/entity/EntityType;III)V", ordinal = 4))
    private void modifyPiglinSpawns(Args args) {
        // weight
        args.set(1, DOOM_MODE ? 25 : 50);
        if (DOOM_MODE) {
            // minSpawnGroupSize
            args.set(2, 1);
            // maxSpawnGroupSize
            args.set(3, 2);
        }
    }

    /**
     * Makes wither skeletons and normal skeletons spawn in the {@link NetherWastesBiome} if {@code doom mode} is {@code ON}.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void addDoomModeMonsterSpawns(CallbackInfo ci) {
        if (DOOM_MODE) {
            this.addSpawn(SpawnGroup.MONSTER, new Biome.SpawnEntry(EntityType.WITHER_SKELETON, 100, 1, 4));
            this.addSpawn(SpawnGroup.MONSTER, new Biome.SpawnEntry(EntityType.HOGLIN, 100, 1, 4));
        }
    }
}