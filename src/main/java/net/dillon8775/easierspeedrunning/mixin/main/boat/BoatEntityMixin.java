package net.dillon8775.easierspeedrunning.mixin.main.boat;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.dillon8775.easierspeedrunning.tag.ModFluidTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.Tag;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.DOOM_MODE;

@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin extends Entity {
    @Shadow
    public abstract BoatEntity.Type getBoatType();

    public BoatEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    /**
     * Fixes the "fire immune" attribute on modded boats.
     */
    @Override
    public boolean isFireImmune() {
        return EasierSpeedrunning.isFireproofBoat(this.getBoatType()) || super.isFireImmune();
    }

    /**
     * Allows the paddling in lava sound to play when paddling a boat in lava.
     */
    @Inject(method = "getPaddleSoundEvent", at = @At("RETURN"), cancellable = true)
    public void getPaddleSoundEvent(CallbackInfoReturnable<SoundEvent> cir) {
        if (this.isInLava()) {
            cir.setReturnValue(EasierSpeedrunning.ENTITY_BOAT_PADDLE_LAVA);
        }
    }

    /**
     * Makes the correct item drop for modded boats.
     */
    @Inject(method = "asItem", at = @At("RETURN"), cancellable = true)
    public void dropItem(CallbackInfoReturnable<Item> cir) {
        if (this.getBoatType().equals(EasierSpeedrunning.CRIMSON_BOAT_TYPE)) {
            cir.setReturnValue(EasierSpeedrunning.CRIMSON_BOAT_ITEM);
        } else if (this.getBoatType().equals(EasierSpeedrunning.WARPED_BOAT_TYPE)) {
            cir.setReturnValue(EasierSpeedrunning.WARPED_BOAT_ITEM);
        }
    }

    /**
     * Changes the player's fall damage multiplier when landing on a boat.
     */
    @ModifyArg(method = "fall", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/BoatEntity;handleFallDamage(FF)Z"), index = 1)
    private float lowerFallDamage(float par1) {
        if (EasierSpeedrunning.options().fallDamage) {
            return DOOM_MODE ? 1.0F : 0.7F;
        } else {
            return 0.0F;
        }
    }

    /**
     * Allows the modded boats to float in lava, just like it would in water.
     */
    @Redirect(method = "checkBoatInWater", at = @At(value = "FIELD", target = "Lnet/minecraft/tag/FluidTags;WATER:Lnet/minecraft/tag/Tag$Identified;"))
    private Tag.Identified<Fluid> boatsFloatInLava() {
        return (Tag.Identified<Fluid>) ModFluidTags.BOAT_SAFE_FLUIDS;
    }

    /**
     * Fixes a bug where fireproof boats go slightly under lava when landing on it from a high distance.
     */
    @Redirect(method = "method_7544", at = @At(value = "FIELD", target = "Lnet/minecraft/tag/FluidTags;WATER:Lnet/minecraft/tag/Tag$Identified;"))
    private Tag.Identified<Fluid> fixBoatsGoingUnderLava() {
        return (Tag.Identified<Fluid>) ModFluidTags.BOAT_SAFE_FLUIDS;
    }
}