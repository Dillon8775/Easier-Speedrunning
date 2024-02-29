package net.dillon8775.easierspeedrunning.mixin.main.entity.living;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow @Final
    public abstract boolean addStatusEffect(StatusEffectInstance effect);
    @Shadow
    public abstract boolean hasStatusEffect(StatusEffect effect);
    @Shadow
    public abstract boolean canWalkOnFluid(FluidState state);
    @Shadow
    protected abstract boolean shouldSwimInFluids();

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "travel", at = @At("TAIL"))
    private void travel(Vec3d movementInput, CallbackInfo ci) {
        FluidState fluidState = this.world.getFluidState(this.getBlockPos());
        if (this.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
            float lavaVelocity = 0.025F;
            if (this.isInLava() && this.shouldSwimInFluids() && !this.canWalkOnFluid(fluidState)) {
                this.updateVelocity(lavaVelocity, movementInput);
                if (!this.hasNoGravity()) {
                    this.setVelocity(this.getVelocity().add(0.0D, -0.02D, 0.0D));
                }
            }
        }

        if (this.hasStatusEffect(StatusEffects.WATER_BREATHING)) {
            float waterVelocity = 0.004F;
            if (this.isTouchingWater() && this.shouldSwimInFluids() && !this.canWalkOnFluid(fluidState)) {
                this.updateVelocity(waterVelocity, movementInput);
            }
        }
    }

    /**
     * Allows entities to "swim upward" at a faster rate if they have fire resistance.
     */
    @ModifyArg(method = "swimUpward", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;add(DDD)Lnet/minecraft/util/math/Vec3d;"), index = 1)
    private double fireResistance(double x) {
        return this.isInLava() && this.hasStatusEffect(StatusEffects.FIRE_RESISTANCE) ? 0.06D : 0.04D;
    }

    /**
     * Applies fire resistance for 2 minutes when using a totem.
     */
    @Inject(method = "tryUseTotem", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)Z"))
    private void applyFireResistance(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        this.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 2400, 0));
    }
}