package net.dillon8775.easierspeedrunning.mixin.main.entity.giant;

import net.dillon8775.easierspeedrunning.entity.Giant;
import net.dillon8775.easierspeedrunning.entity.GiantAttackGoal;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.DOOM_MODE;

/**
 * Brings the giant to life, for {@code doom mode}.
 */
@Mixin(GiantEntity.class)
public class GiantEntityMixin extends HostileEntity implements Giant {
    @Unique
    protected SwimNavigation waterNavigation;
    @Unique
    protected MobNavigation landNavigation;
    @Unique
    boolean targetingUnderwater;
    @Unique
    private ServerBossBar bossBar;

    public GiantEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Sets how much experience the giant will drop upon death.
     */
    @Override
    public int getCurrentExperience(PlayerEntity player) {
        this.experiencePoints = 50 + EnchantmentHelper.getLooting(player) * 150;
        return super.getCurrentExperience(player);
    }

    /**
     * The Giant's basic configuration and AI.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        if (DOOM_MODE) {
            this.bossBar = (ServerBossBar) (new ServerBossBar(this.getDisplayName(), BossBar.Color.GREEN, BossBar.Style.PROGRESS));
            this.setPathfindingPenalty(PathNodeType.LAVA, 8.0F);
            this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, 0.0F);
            this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, 0.0F);
            this.waterNavigation = new SwimNavigation(this, world);
            this.landNavigation = new MobNavigation(this, world);
        }
    }

    /**
     * The Giant's generic attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createGiantAttributes() {
        final double genericMaxHealth = DOOM_MODE ? 300.0D : 100.0D;
        final double genericMovementSpeed = DOOM_MODE ? 0.3500000528343624D : 0.5D;
        final double genericAttackDamage = DOOM_MODE ? 10.0D : 50.0D;
        final double genericAttackKnockback = DOOM_MODE ? 1.0D : 0.0D;
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, genericMaxHealth).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, genericMovementSpeed).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, genericAttackDamage).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, genericAttackKnockback);
    }

    @Override
    protected void initGoals() {
        if (DOOM_MODE) {
            this.goalSelector.add(1, new SwimGoal(this));
            this.goalSelector.add(2, new GiantAttackGoal((GiantEntity) (Object) this, 1.0D, false));
            this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0D));
            this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 32.0F));
            this.goalSelector.add(8, new LookAroundGoal(this));
            this.targetSelector.add(2, new RevengeGoal(this, new Class[0]));
            this.targetSelector.add(1, new FollowTargetGoal(this, PlayerEntity.class, true));
            this.targetSelector.add(3, new FollowTargetGoal(this, MobEntity.class, true));
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (DOOM_MODE) {
            if (this.age % 10 == 0) {
                this.heal(0.6F);
            }

            this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());

            if (this.world instanceof ServerWorld && this.world.getRegistryKey() == World.END) {
                if (this.getY() < -64.0D) {
                    this.teleport(0, 96, 0, true);
                    if (!this.isSilent()) {
                        this.world.playSound((PlayerEntity) null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.HOSTILE, 10.0F, 1.0F);
                        this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 10.0F, 1.0F);
                    }
                }
            }
        }
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        if (DOOM_MODE) {
            this.onGiantDeath();
            if (!this.isSilent()) {
                this.world.playSound((PlayerEntity) null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.BLOCK_RESPAWN_ANCHOR_DEPLETE, SoundCategory.AMBIENT, 1.0F, 1.0F);
                this.playSound(SoundEvents.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1.0F, 1.0F);
            }
        }
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (DOOM_MODE) {
            Entity entity = source.getSource();

            if (entity instanceof WitherSkullEntity || entity instanceof IronGolemEntity || entity instanceof RavagerEntity || entity instanceof VindicatorEntity || entity instanceof ZombieEntity || entity instanceof EnderDragonEntity) {
                return false;
            }

            if (this.getHealth() <= 150) {
                if (entity instanceof PersistentProjectileEntity) {
                    return false;
                }
            }

            if (this.getHealth() <= 50) {
                if (entity instanceof AreaEffectCloudEntity) {
                    return false;
                }
            }

            if (this.random.nextFloat() < 0.10F) {
                this.onGiantDamage();
            }

            if (this.random.nextFloat() < 0.05F && this.getHealth() <= 250) {
                this.onGiantDamageDropFood();
            }
        }

        return super.damage(source, amount);
    }

    @Override
    public boolean tryAttack(Entity target) {
        if (DOOM_MODE) {
            this.world.sendEntityStatus(this, (byte)4);
        }
        return DOOM_MODE ? Giant.tryAttack(this, (LivingEntity)target) : super.tryAttack(target);
    }

    @Override
    protected void knockback(LivingEntity target) {
        if (DOOM_MODE) {
            Giant.knockback(this, target);
        }
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (DOOM_MODE) {
            if (this.canMoveVoluntarily() && this.isTouchingWater() && this.isTargetingUnderwater()) {
                this.updateVelocity(0.01F, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.9D));
            } else {
                super.travel(movementInput);
            }
        } else {
            super.travel(movementInput);
        }
    }

    @Override
    public void updateSwimming() {
        super.updateSwimming();
        if (DOOM_MODE) {
            if (!this.world.isClient) {
                if (this.canMoveVoluntarily() && this.isTouchingWater() && this.isTargetingUnderwater()) {
                    this.navigation = this.waterNavigation;
                    this.setSwimming(true);
                } else {
                    this.navigation = this.landNavigation;
                    this.setSwimming(false);
                }
            }
        }
    }

    @Override
    public void checkDespawn() {
        if (DOOM_MODE) {
            if (this.world.getDifficulty() == Difficulty.PEACEFUL && this.isDisallowedInPeaceful()) {
                this.remove();
            } else {
                this.despawnCounter = 0;
            }
        }
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier) {
        return !DOOM_MODE;
    }

    @Override
    public boolean addStatusEffect(StatusEffectInstance effect) {
        return !DOOM_MODE;
    }

    @Override
    public boolean isFireImmune() {
        return DOOM_MODE;
    }

    @Override
    public boolean isImmuneToExplosion() {
        return DOOM_MODE;
    }

    @Override
    public boolean canStartRiding(Entity entity) {
        return !DOOM_MODE && super.canStartRiding(entity);
    }

    @Override
    public boolean canUsePortals() {
        return !DOOM_MODE;
    }

    @Override
    public void setCustomName(@Nullable Text name) {
        super.setCustomName(name);
        if (DOOM_MODE) {
            this.bossBar.setName(this.getDisplayName());
        }
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        if (DOOM_MODE) {
            this.bossBar.addPlayer(player);
        }
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        if (DOOM_MODE) {
            this.bossBar.removePlayer(player);
        }
    }

    @Override
    public EntityGroup getGroup() {
        return DOOM_MODE ? EntityGroup.UNDEAD : EntityGroup.DEFAULT;
    }

    @Override
    public SoundCategory getSoundCategory() {
        return DOOM_MODE ? SoundCategory.HOSTILE : SoundCategory.NEUTRAL;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return DOOM_MODE ? SoundEvents.ENTITY_ZOMBIE_AMBIENT : null;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource source) {
        return DOOM_MODE ? SoundEvents.ENTITY_ZOMBIE_HURT : SoundEvents.ENTITY_GENERIC_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return DOOM_MODE ? SoundEvents.ENTITY_ZOMBIE_DEATH : SoundEvents.ENTITY_GENERIC_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        if (DOOM_MODE) {
            this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.25F, 1.0F);
        }
    }

    @Override
    public float getSoundVolume() {
        return DOOM_MODE ? 5.0F : 1.0F;
    }

    @Unique
    boolean isTargetingUnderwater() {
        if (this.targetingUnderwater) {
            return true;
        } else {
            LivingEntity livingEntity = this.getTarget();
            return livingEntity != null && livingEntity.isTouchingWater();
        }
    }

    @Unique
    private void onGiantDamageDropFood() {
        this.dropItem(Items.ROTTEN_FLESH);
        this.dropItem(Items.ROTTEN_FLESH);
        this.dropItem(Items.ROTTEN_FLESH);
        if (this.random.nextFloat() < 0.3F) {
            this.dropItem(Items.ROTTEN_FLESH);
            this.dropItem(Items.ROTTEN_FLESH);
        }

        if (this.random.nextFloat() < 0.2F) {
            this.dropItem(Items.ROTTEN_FLESH);
            this.dropItem(Items.ROTTEN_FLESH);
        }
    }

    @Unique
    private void onGiantDamage() {
        TntEntity tntEntity = (TntEntity) EntityType.TNT.create(this.world);
        tntEntity.setFuse(100);
        tntEntity.refreshPositionAndAngles(this.getX() + 5, this.getY() + 25, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity1 = (TntEntity) EntityType.TNT.create(this.world);
        tntEntity1.setFuse(100);
        tntEntity1.refreshPositionAndAngles(this.getX() - 5, this.getY() + 25, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity2 = (TntEntity) EntityType.TNT.create(this.world);
        tntEntity2.setFuse(100);
        tntEntity2.refreshPositionAndAngles(this.getX() + 5, this.getY() + 25, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity3 = (TntEntity) EntityType.TNT.create(this.world);
        tntEntity3.setFuse(100);
        tntEntity3.refreshPositionAndAngles(this.getX() - 5, this.getY() + 25, this.getZ() - 5, 0.0F, 0.0F);
        this.world.playSound((PlayerEntity) null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.AMBIENT, 5.0F, 1.0F);
        this.world.spawnEntity(tntEntity);
        this.world.spawnEntity(tntEntity1);
        this.world.spawnEntity(tntEntity2);
        this.world.spawnEntity(tntEntity3);
    }

    @Unique
    private void onGiantDeath() {
        TntEntity tntEntity = (TntEntity) EntityType.TNT.create(this.world);
        tntEntity.setInvulnerable(true);
        tntEntity.setFuse(100);
        tntEntity.refreshPositionAndAngles(this.getX() + 5, this.getY() + 25, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity1 = (TntEntity) EntityType.TNT.create(this.world);
        tntEntity1.setFuse(100);
        tntEntity1.refreshPositionAndAngles(this.getX() - 5, this.getY() + 25, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity2 = (TntEntity) EntityType.TNT.create(this.world);
        tntEntity2.setFuse(100);
        tntEntity2.refreshPositionAndAngles(this.getX() + 5, this.getY() + 25, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity3 = (TntEntity) EntityType.TNT.create(this.world);
        tntEntity3.setFuse(100);
        tntEntity3.refreshPositionAndAngles(this.getX() - 5, this.getY() + 25, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity4 = (TntEntity) EntityType.TNT.create(this.world);
        tntEntity4.setFuse(100);
        tntEntity4.refreshPositionAndAngles(this.getX() + 5, this.getY() + 50, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity5 = (TntEntity) EntityType.TNT.create(this.world);
        tntEntity5.setFuse(100);
        tntEntity5.refreshPositionAndAngles(this.getX() - 5, this.getY() + 50, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity6 = (TntEntity) EntityType.TNT.create(this.world);
        tntEntity6.setFuse(100);
        tntEntity6.refreshPositionAndAngles(this.getX() + 5, this.getY() + 50, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity7 = (TntEntity) EntityType.TNT.create(this.world);
        tntEntity7.setFuse(100);
        tntEntity7.refreshPositionAndAngles(this.getX() - 5, this.getY() + 50, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity8 = (TntEntity) EntityType.TNT.create(this.world);
        tntEntity8.setFuse(120);
        tntEntity8.refreshPositionAndAngles(this.getX() + 5, this.getY() + 75, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity9 = (TntEntity) EntityType.TNT.create(this.world);
        tntEntity9.setFuse(120);
        tntEntity9.refreshPositionAndAngles(this.getX() - 5, this.getY() + 75, this.getZ() + 5, 0.0F, 0.0F);
        TntEntity tntEntity10 = (TntEntity) EntityType.TNT.create(this.world);
        tntEntity10.setFuse(120);
        tntEntity10.refreshPositionAndAngles(this.getX() + 5, this.getY() + 75, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity11 = (TntEntity) EntityType.TNT.create(this.world);
        tntEntity11.setFuse(120);
        tntEntity11.refreshPositionAndAngles(this.getX() - 5, this.getY() + 75, this.getZ() - 5, 0.0F, 0.0F);
        TntEntity tntEntity12 = (TntEntity) EntityType.TNT.create(this.world);
        tntEntity12.setFuse(140);
        tntEntity12.refreshPositionAndAngles(this.getX(), this.getY() + 100, this.getZ(), 0.0F, 0.0F);
        this.world.playSound((PlayerEntity) null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.AMBIENT, 5.0F, 1.0F);
        this.world.spawnEntity(tntEntity);
        this.world.spawnEntity(tntEntity1);
        this.world.spawnEntity(tntEntity2);
        this.world.spawnEntity(tntEntity3);
        this.world.spawnEntity(tntEntity4);
        this.world.spawnEntity(tntEntity5);
        this.world.spawnEntity(tntEntity6);
        this.world.spawnEntity(tntEntity7);
        this.world.spawnEntity(tntEntity8);
        this.world.spawnEntity(tntEntity9);
        this.world.spawnEntity(tntEntity10);
        this.world.spawnEntity(tntEntity11);
        this.world.spawnEntity(tntEntity12);
    }
}