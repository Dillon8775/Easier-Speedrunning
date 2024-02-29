package net.dillon8775.easierspeedrunning.mixin.main.nether.nether_portal;

import net.dillon8775.easierspeedrunning.tag.ModBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherPortalBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(NetherPortalBlock.AreaHelper.class)
public class NetherPortalBaseBlocks {

    /**
     * Allows nether portals to be created with crying obsidian or obsidian.
     */
    @Redirect(method = "distanceToPortalEdge", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private boolean isNetherPortalBaseBlockEdge(BlockState blockState, Block block) {
        return blockState.isIn(ModBlockTags.NETHER_PORTAL_BASE_BLOCKS);
    }

    /**
     * Allows nether portals to be created with crying obsidian or obsidian.
     */
    @Redirect(method = "findHeight", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private boolean isNetherPortalBaseBlockHeight(BlockState blockState, Block block) {
        return blockState.isIn(ModBlockTags.NETHER_PORTAL_BASE_BLOCKS);
    }
}