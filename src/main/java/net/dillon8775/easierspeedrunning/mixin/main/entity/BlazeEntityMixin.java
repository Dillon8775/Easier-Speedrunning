package net.dillon8775.easierspeedrunning.mixin.main.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.DOOM_MODE;

@Mixin(BlazeEntity.class)
public class BlazeEntityMixin extends HostileEntity {

    public BlazeEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public int getCurrentExperience(PlayerEntity player) {
        this.experiencePoints = 10 + EnchantmentHelper.getLooting(player) * 48;
        return super.getCurrentExperience(player);
    }

    @Overwrite
    public static DefaultAttributeContainer.Builder createBlazeAttributes() {
        final double genericAttackDamage = DOOM_MODE ? 8.0D : 4.0D;
        final double genericMovementSpeed = 0.23000000417232513D;
        final double genericFollowRange = DOOM_MODE ? 48.0D : 16.0D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, genericFollowRange);
    }
}