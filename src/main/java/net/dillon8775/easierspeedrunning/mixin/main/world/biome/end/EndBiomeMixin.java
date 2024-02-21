package net.dillon8775.easierspeedrunning.mixin.main.world.biome.end;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.EndBiome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.DOOM_MODE;

@Mixin(EndBiome.class)
public class EndBiomeMixin extends Biome {

    public EndBiomeMixin(Settings settings) {
        super(settings);
    }

    /**
     * Changes the enderman's spawn configuration if {@code doom mode} is {@code ON}.
     */
    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/Biome$SpawnEntry;<init>(Lnet/minecraft/entity/EntityType;III)V"))
    private void modifyEndSpawns(Args args) {
        if (DOOM_MODE) {
            // weight
            args.set(1, 85);
            // minSpawnGroupSize
            args.set(2, 1);
        }
    }

    /**
     * Adds {@code doom mode} monster spawns.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void addDoomModeMonsterSpawns(CallbackInfo ci) {
        if (DOOM_MODE) {
            this.addSpawn(SpawnGroup.MONSTER, new Biome.SpawnEntry(EntityType.SKELETON, 75, 1, 4));
            this.addSpawn(SpawnGroup.MONSTER, new Biome.SpawnEntry(EntityType.VINDICATOR, 75, 1, 2));
            this.addSpawn(SpawnGroup.MONSTER, new Biome.SpawnEntry(EntityType.RAVAGER, 50, 1, 1));
            this.addSpawn(SpawnGroup.MONSTER, new Biome.SpawnEntry(EntityType.EVOKER, 50, 1, 1));
            this.addSpawn(SpawnGroup.MONSTER, new Biome.SpawnEntry(EntityType.ZOMBIE, 25, 1, 1));
        }
    }
}