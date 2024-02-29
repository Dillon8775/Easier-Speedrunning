package net.dillon8775.easierspeedrunning.mixin.main.entity;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MagmaCubeEntity.class)
public class MagmaCubeEntityMixin extends SlimeEntity {

    public MagmaCubeEntityMixin(EntityType<? extends SlimeEntity> entityType, World world) {
        super(entityType, world);
    }

    @Overwrite
    public int getTicksUntilNextJump() {
        return EasierSpeedrunning.getSlimeJumpTime();
    }

    @Overwrite
    public float getDamageAmount() {
        return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) * EasierSpeedrunning.getMagmaCubeDamageMultiplier();
    }
}