package net.dillon8775.easierspeedrunning.mixin.main.entity;

import net.minecraft.entity.projectile.SmallFireballEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.DOOM_MODE;

@Mixin(SmallFireballEntity.class)
public class SmallFireballEntityMixin {

    @ModifyArg(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(I)V"))
    private int onEntityHit(int x) {
        return DOOM_MODE ? 6 : 3;
    }

    @ModifyArg(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"), index = 1)
    private float onEntityHit(float x) {
        return DOOM_MODE ? 5.0F : 3.0F;
    }
}