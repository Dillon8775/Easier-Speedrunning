package net.dillon8775.easierspeedrunning.mixin.main.world;

import net.minecraft.structure.RuinedPortalStructurePiece;
import net.minecraft.world.gen.feature.RuinedPortalFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(RuinedPortalFeature.class)
public class RuinedPortalFeatureMixin {

    /**
     * Fixes ruined portals generating buried, or under the sand in the desert biomes.
     */
    @ModifyVariable(method = "addPieces", at = @At(value = "STORE", ordinal = 0))
    private static RuinedPortalStructurePiece.VerticalPlacement changeDesertVerticalPlacement(RuinedPortalStructurePiece.VerticalPlacement verticalPlacement) {
        return RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
    }
}