package net.dillon8775.easierspeedrunning.mixin.main.entity;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(PiglinBrain.class)
public class PiglinBrainMixin {

    /**
     * Removes the first argument of the original method, and makes piglins {@code willing to trade}, even if they were previously hit by the player.
     */
    @Overwrite
    public static boolean isWillingToTrade(PiglinEntity piglin, ItemStack nearbyItems) {
        return !PiglinBrain.isAdmiringItem(piglin) && piglin.isAdult() && PiglinBrain.acceptsForBarter(nearbyItems.getItem());
    }

    /**
     * Lowers the distance piglins have to be in order for them to run away from the nearest zombified piglin.
     */
    @ModifyArg(method = "getNearestZombifiedPiglin", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/PiglinEntity;isInRange(Lnet/minecraft/entity/Entity;D)Z"), index = 1)
    private static double nearestZombifiedPiglin(double radius) {
        return EasierSpeedrunning.getZombifiedPiglinRunawayDistance();
    }
}