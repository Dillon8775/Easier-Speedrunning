package net.dillon8775.easierspeedrunning.mixin.main.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RedstoneOreBlock.class)
public class MoreBlockXPRedstone extends Block {

    public MoreBlockXPRedstone(Settings settings) {
        super(settings);
    }

    @Inject(method = "onStacksDropped", at = @At("TAIL"))
    private void moreXP(BlockState state, World world, BlockPos pos, ItemStack stack, CallbackInfo ci) {
        if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
            int f;
            if (state.isOf(Blocks.REDSTONE_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 48;
                this.dropExperience(world, pos, f);
            }
        }
    }
}