package net.dillon8775.easierspeedrunning.mixin.main.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PhantomEntity.class)
public class PhantomEntityMixin extends FlyingEntity {

    public PhantomEntityMixin(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public int getCurrentExperience(PlayerEntity player) {
        this.experiencePoints = 5 + EnchantmentHelper.getLooting(player) * 32;
        return super.getCurrentExperience(player);
    }
}