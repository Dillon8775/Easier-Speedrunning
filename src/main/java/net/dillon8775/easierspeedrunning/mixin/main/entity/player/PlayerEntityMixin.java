package net.dillon8775.easierspeedrunning.mixin.main.entity.player;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.DOOM_MODE;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow
    public abstract ItemCooldownManager getItemCooldownManager();
    @Shadow @Final
    public PlayerAbilities abilities;

    public PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Changes the default nether portal cooldown to whatever the user sets it to.
     */
    @Overwrite
    public int getMaxNetherPortalTime() {
        return this.abilities.invulnerable || EasierSpeedrunning.options().netherPortalCooldown <= 0 ? 1 : EasierSpeedrunning.options().netherPortalCooldown * 20;
    }

    /**
     * Makes the giant disable the player's shield when on {@code doom mode}.
     */
    @Inject(method = "takeShieldHit", at = @At("TAIL"))
    private void takeShieldHit(LivingEntity attacker, CallbackInfo ci) {
        if (DOOM_MODE && attacker instanceof GiantEntity) {
            this.getItemCooldownManager().set(Items.SHIELD, 200);
            this.clearActiveItem();
            this.world.sendEntityStatus(this, (byte)30);
        }
    }

    /**
     * Allows player to hold their breath for a longer period of time while underwater.
     */
    @Override
    protected int getNextAirUnderwater(int air) {
        if (EasierSpeedrunning.options().higherBreathTime && this.random.nextInt(4) > 0) {
            return air;
        }

        return super.getNextAirUnderwater(air);
    }

    /**
     * Allows players catch their breath faster after coming out of the water.
     */
    @Override
    public int getNextAirOnLand(int air) {
        final int breathTime = EasierSpeedrunning.options().higherBreathTime ? 8 : 4;
        return Math.min(air + breathTime, this.getMaxAir());
    }
}