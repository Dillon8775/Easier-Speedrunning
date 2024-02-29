package net.dillon8775.easierspeedrunning.mixin.main.nether.nether_portal;

import net.dillon8775.easierspeedrunning.tag.ModBlockTags;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractFireBlock.class)
public class AllowNetherPortalBaseBlocks {

    /**
     * Allows nether portals to be created with crying obsidian or obsidian.
     */
    @Redirect(method = "method_30033", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private static boolean netherPortalBaseBlocks(BlockState state, Block block) {
        return state.isIn(ModBlockTags.NETHER_PORTAL_BASE_BLOCKS);
    }
}