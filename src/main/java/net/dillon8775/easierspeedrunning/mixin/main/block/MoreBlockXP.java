package net.dillon8775.easierspeedrunning.mixin.main.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OreBlock.class)
public class MoreBlockXP extends Block {

    public MoreBlockXP(Settings settings) {
        super(settings);
    }

    /**
     * Makes ores drop more experience when mined.
     */
    @Inject(method = "onStacksDropped", at = @At("TAIL"))
    public void moreXP(BlockState state, World world, BlockPos pos, ItemStack stack, CallbackInfo ci) {
        if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
            int f;
            int i;
            if (state.isOf(Blocks.GOLD_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 54;
                i = 2 + world.random.nextInt(5) + f;
                this.dropExperience(world, pos, i);
            } else if (state.isOf(Blocks.IRON_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 48;
                i = 1 + world.random.nextInt(2) + f;
                this.dropExperience(world, pos, i);
            } else if (state.isOf(Blocks.COAL_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 18;
                this.dropExperience(world, pos, f);
            } else if (state.isOf(Blocks.NETHER_GOLD_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 36;
                this.dropExperience(world, pos, f);
            } else if (state.isOf(Blocks.LAPIS_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 54;
                this.dropExperience(world, pos, f);
            } else if (state.isOf(Blocks.DIAMOND_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 66;
                this.dropExperience(world, pos, f);
            } else if (state.isOf(Blocks.EMERALD_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 54;
                this.dropExperience(world, pos, f);
            } else if (state.isOf(Blocks.NETHER_QUARTZ_ORE)) {
                f = EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) * 36;
                this.dropExperience(world, pos, f);
            }
        }
    }
}