package net.dillon8775.easierspeedrunning.mixin.main.entity.basic;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.tag.FluidTags;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    public World world;
    @Shadow @Nullable
    public abstract Entity getVehicle();
    @Shadow
    private int fireTicks;

    /**
     * Makes entities burn for only {@code 7 seconds} from fire, instead of the default, being {@code 15 seconds.}
     */
    @ModifyArg(method = "setOnFireFromLava", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(I)V"))
    private int setOnFireFromLava(int x) {
        return EasierSpeedrunning.getFireFromLavaTime();
    }

    /**
     * Makes entities take less damage from the lava fluid.
     */
    @ModifyArg(method = "setOnFireFromLava", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private float setOnFireFromLava(float x) {
        return EasierSpeedrunning.getLavaDamageAmount();
    }

    /**
     * Allows players to ride in fireproof boats without burning from the lava.
     */
    @Inject(method = "setOnFireFromLava", at = @At("HEAD"), cancellable = true)
    private void setOnFireFromLava(CallbackInfo ci) {
        Entity vehicle = getVehicle();
        Entity passenger = (Entity)(Object)this;

        // Fixes the passenger getting caught on fire upon dismounting
        if (passenger.getFluidHeight(FluidTags.LAVA) == 0.0) {
            ci.cancel();
        }

        if (vehicle instanceof BoatEntity && EasierSpeedrunning.isFireproofBoat(((BoatEntity) vehicle).getBoatType())) {
            if (fireTicks > 0 && fireTicks % 20 == 0) {
                ((Entity)(Object)this).damage(DamageSource.ON_FIRE, 1.0f);
            }
            ci.cancel();
        }
    }
}