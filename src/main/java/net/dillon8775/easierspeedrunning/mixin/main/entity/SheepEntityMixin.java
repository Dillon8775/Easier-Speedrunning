package net.dillon8775.easierspeedrunning.mixin.main.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin extends AnimalEntity {

    public SheepEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyVariable(method = "sheared", at = @At("STORE"))
    private int sheared(int x) {
        return 6 + this.random.nextInt(4);
    }
}