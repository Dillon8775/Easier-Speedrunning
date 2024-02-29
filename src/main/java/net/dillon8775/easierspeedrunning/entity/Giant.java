package net.dillon8775.easierspeedrunning.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.Vec3d;

/**
 * See {@link net.dillon8775.easierspeedrunning.mixin.main.entity.giant.GiantEntityMixin} for more.
 */
public interface Giant {

    static boolean tryAttack(LivingEntity attacker, LivingEntity target) {
        float f = (float)attacker.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        float h;
        if (!attacker.isBaby() && (int)f > 0) {
            h = f / 2.0F + (float)attacker.world.random.nextInt((int)f);
        } else {
            h = f;
        }

        boolean bl = target.damage(DamageSource.mob(attacker), h);
        if (bl) {
            attacker.applyDamageEffects(attacker, target);
            if (!attacker.isBaby()) {
                knockback(attacker, target);
            }
        }

        return bl;
    }

    static void knockback(LivingEntity attacker, LivingEntity target) {
        double d = attacker.getAttributeValue(EntityAttributes.GENERIC_ATTACK_KNOCKBACK);
        double e = target.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE);
        double f = d - e;
        if (!(f <= 0.0D)) {
            double g = target.getX() - attacker.getX();
            double h = target.getZ() - attacker.getZ();
            float i = (float)(attacker.world.random.nextInt(21) - 10);
            double j = f * (double)(attacker.world.random.nextFloat() * 0.5F + 0.2F);
            Vec3d vec3d = (new Vec3d(g, 0.0D, h)).normalize().multiply(j).rotateY(i);
            double k = f * (double)attacker.world.random.nextFloat() * 0.5D;
            target.addVelocity(vec3d.x, k, vec3d.z);
            target.velocityModified = true;
        }
    }
}