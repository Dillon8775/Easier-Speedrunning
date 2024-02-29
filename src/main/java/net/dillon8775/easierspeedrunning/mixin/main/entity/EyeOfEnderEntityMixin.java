package net.dillon8775.easierspeedrunning.mixin.main.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.EnvironmentInterface;
import net.fabricmc.api.EnvironmentInterfaces;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.DOOM_MODE;

@EnvironmentInterfaces(@EnvironmentInterface(value = EnvType.CLIENT, itf = FlyingItemEntity.class))
@Mixin(EyeOfEnderEntity.class)
public abstract class EyeOfEnderEntityMixin extends Entity implements FlyingItemEntity {
    @Shadow
    private double velocityX, velocityY, velocityZ;
    @Shadow
    private int useCount;

    public EyeOfEnderEntityMixin(EntityType<? extends EyeOfEnderEntity> type, World world) {
        super(type, world);
    }

    /**
     * Changes the function of the eye of ender, and applies different effects to it in certain modes, based off what type it is.
     */
    @Overwrite
    public void tick() {
        super.tick();
        Vec3d vec3d = this.getVelocity();
        double d = this.getX() + vec3d.x;
        double e = this.getY() + vec3d.y;
        double f = this.getZ() + vec3d.z;
        double g = MathHelper.sqrt(squaredHorizontalLength(vec3d));
        this.pitch = ProjectileEntity.updateRotation(this.prevPitch, (float)(MathHelper.atan2(vec3d.y, g) * 57.2957763671875D));
        this.yaw = ProjectileEntity.updateRotation(this.prevYaw, (float)(MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875D));
        if (!this.world.isClient) {
            double h = this.velocityX - d;
            double i = this.velocityZ - f;
            float j = (float)Math.sqrt(h * h + i * i);
            float k = (float)MathHelper.atan2(i, h);
            double l = MathHelper.lerp(0.0025D, g, (double)j);
            double m = vec3d.y;
            if (j < 1.0F) {
                l *= 0.8D;
                m *= 0.8D;
            }

            int n = this.getY() < this.velocityY ? 1 : -1;
            vec3d = new Vec3d(Math.cos((double)k) * l, m + ((double)n - m) * 0.014999999664723873D, Math.sin((double)k) * l);
            this.setVelocity(vec3d);
        }

        float o = 0.25F;
        if (this.isTouchingWater()) {
            for(int p = 0; p < 4; ++p) {
                this.world.addParticle(ParticleTypes.BUBBLE, d - vec3d.x * 0.25D, e - vec3d.y * 0.25D, f - vec3d.z * 0.25D, vec3d.x, vec3d.y, vec3d.z);
            }
        } else if (this.getStack().getItem() == Items.ENDER_EYE && !this.isTouchingWater()) {
            this.world.addParticle(ParticleTypes.PORTAL, d - vec3d.x * 0.25D + this.random.nextDouble() * 0.6D - 0.3D, e - vec3d.y * 0.25D - 0.5D, f - vec3d.z * 0.25D + this.random.nextDouble() * 0.6D - 0.3D, vec3d.x, vec3d.y, vec3d.z);
        }

        if (!this.world.isClient) {
            this.setPos(d, e, f);
            ++this.useCount;
            if (this.useCount > 40 && !this.world.isClient) {
                this.remove();
                this.playSound(SoundEvents.ENTITY_ENDER_EYE_DEATH, 1.0F, 1.0F);
                if (DOOM_MODE) {
                    this.world.syncWorldEvent(2003, this.getBlockPos(), 0);
                } else {
                    this.world.spawnEntity(new ItemEntity(this.world, this.getX(), this.getY(), this.getZ(), this.getStack()));
                }
            }
        } else {
            this.setPos(d, e, f);
        }
    }
}