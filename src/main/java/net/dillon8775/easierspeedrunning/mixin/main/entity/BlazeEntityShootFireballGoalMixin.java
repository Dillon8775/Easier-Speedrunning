package net.dillon8775.easierspeedrunning.mixin.main.entity;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.BlazeEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlazeEntity.ShootFireballGoal.class)
public class BlazeEntityShootFireballGoalMixin {

    /**
     * Increases the blaze's fireball cooldown, unless it's on {@code doom mode}.
     */
    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/mob/BlazeEntity$ShootFireballGoal;fireballCooldown:I", ordinal = 4, opcode = Opcodes.PUTFIELD))
    private void changeFireballCooldown(BlazeEntity.ShootFireballGoal blaze, int value) {
        blaze.fireballCooldown = EasierSpeedrunning.getBlazeFireballCooldown();
    }

    /**
     * Prevents blazes from melee attacking.
     */
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/BlazeEntity;tryAttack(Lnet/minecraft/entity/Entity;)Z"))
    private boolean disableMeleeAttack(BlazeEntity blaze, Entity entity) {
        return false;
    }
}