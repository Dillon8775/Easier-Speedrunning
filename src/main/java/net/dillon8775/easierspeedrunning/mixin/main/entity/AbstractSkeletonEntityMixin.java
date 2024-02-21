package net.dillon8775.easierspeedrunning.mixin.main.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.DOOM_MODE;

@Mixin(AbstractSkeletonEntity.class)
public class AbstractSkeletonEntityMixin extends HostileEntity {

    public AbstractSkeletonEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public int getCurrentExperience(PlayerEntity player) {
        this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 32;
        return super.getCurrentExperience(player);
    }

    @ModifyVariable(method = "updateAttackType", at = @At("STORE"), ordinal = 0)
    private int updateAttackType(int x) {
        int i = DOOM_MODE ? 5 : 20;
        if (this.world.getDifficulty() != Difficulty.HARD) {
            i = DOOM_MODE ? 10 : 20;
        }
        return i;
    }

    @ModifyArg(method = "createAbstractSkeletonAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/entity/attribute/EntityAttribute;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;"), index = 1)
    private static double genericMovementSpeed(double baseValue) {
        return DOOM_MODE ? 0.3D : 0.25D;
    }
}