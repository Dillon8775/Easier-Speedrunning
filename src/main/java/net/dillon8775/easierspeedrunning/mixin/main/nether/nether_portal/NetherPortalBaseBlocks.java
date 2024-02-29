package net.dillon8775.easierspeedrunning.mixin.main.nether.nether_portal;

import net.dillon8775.easierspeedrunning.tag.ModBlockTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.world.dimension.AreaHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AreaHelper.class)
public class NetherPortalBaseBlocks {

    /**
     * Allows nether portals to be built with any block in the {@code "nether_portal_base_blocks"} tag.
     */
    @Shadow
    private static final AbstractBlock.ContextPredicate IS_VALID_FRAME_BLOCK = (state, world, pos) -> {
        return state.isIn(ModBlockTags.NETHER_PORTAL_BASE_BLOCKS);
    };
}