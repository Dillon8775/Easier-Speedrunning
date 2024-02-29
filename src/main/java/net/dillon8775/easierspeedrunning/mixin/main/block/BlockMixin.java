package net.dillon8775.easierspeedrunning.mixin.main.block;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.DOOM_MODE;

@Mixin(Block.class)
public class BlockMixin {

    /**
     * <p>Lowers fall damage. This applies to all entities.</p>
     * <p>If the entity is {@code sneaking}, then the damage can be reduced by {@code ~1.25F}.</p>
     */
    @Overwrite
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        float fallDamage;
        if (!EasierSpeedrunning.options().fallDamage) {
            fallDamage = 0.0F;
        } else {
            fallDamage = DOOM_MODE ? 1.0F : 0.7F;
            if (entity.isSneaking()) {
                fallDamage = fallDamage / 1.25F;
            }
        }
        entity.handleFallDamage(fallDistance, fallDamage, DamageSource.FALL);
    }
}