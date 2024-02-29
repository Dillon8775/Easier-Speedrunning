package net.dillon8775.easierspeedrunning.mixin.main.block;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.dillon8775.easierspeedrunning.tag.ModBlockTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.AbstractBlockState.class)
public class FasterBlockBreaking {

    /**
     * Applies the {@code Faster Block Breaking} option to certain blocks.
     */
    @Inject(method = "getHardness", at = @At("HEAD"), cancellable = true)
    private void applyFasterBlockBreaking(BlockView world, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        if (EasierSpeedrunning.options().fasterBlockBreaking) {
            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.INSTANT_BREAK)) {
                cir.setReturnValue(0.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_ONE_HARDNESS)) {
                cir.setReturnValue(0.1F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_TWO_HARDNESS)) {
                cir.setReturnValue(0.2F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_THREEFIVE_HARDNESS)) {
                cir.setReturnValue(0.35F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_THREESEVEN_HARDNESS)) {
                cir.setReturnValue(0.37F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_FOUR_HARDNESS)) {
                cir.setReturnValue(0.4F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_FIVE_HARDNESS)) {
                cir.setReturnValue(0.5F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_SIX_HARDNESS)) {
                cir.setReturnValue(0.6F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_SIXFIVE_HARDNESS)) {
                cir.setReturnValue(0.65F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_SEVEN_HARDNESS)) {
                cir.setReturnValue(0.7F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ZERO_EIGHT_HARDNESS)) {
                cir.setReturnValue(0.8F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ONE_HARDNESS)) {
                cir.setReturnValue(1.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ONE_THREE_HARDNESS)) {
                cir.setReturnValue(1.3F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ONE_FOUR_HARDNESS)) {
                cir.setReturnValue(1.4F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ONE_FIVE_HARDNESS)) {
                cir.setReturnValue(1.5F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.ONE_SIX_HARDNESS)) {
                cir.setReturnValue(1.6F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.TWO_HARDNESS)) {
                cir.setReturnValue(2.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.TWO_FIVE_HARDNESS)) {
                cir.setReturnValue(2.5F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.THREE_HARDNESS)) {
                cir.setReturnValue(3.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.FOUR_HARDNESS)) {
                cir.setReturnValue(4.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.FOUR_FIVE_HARDNESS)) {
                cir.setReturnValue(4.5F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.FIVE_HARDNESS)) {
                cir.setReturnValue(5.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.SIX_HARDNESS)) {
                cir.setReturnValue(6.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.SEVEN_HARDNESS)) {
                cir.setReturnValue(7.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.EIGHT_HARDNESS)) {
                cir.setReturnValue(8.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.NINE_HARDNESS)) {
                cir.setReturnValue(9.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.TEN_HARDNESS)) {
                cir.setReturnValue(10.0F);
            }

            if (world.getBlockState(pos).isIn(ModBlockTags.BlockHardness.TWENTY_FIVE_HARDNESS)) {
                cir.setReturnValue(25.0F);
            }
        }
    }
}