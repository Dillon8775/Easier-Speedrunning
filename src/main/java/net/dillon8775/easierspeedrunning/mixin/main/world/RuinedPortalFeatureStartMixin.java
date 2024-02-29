package net.dillon8775.easierspeedrunning.mixin.main.world;

import net.minecraft.structure.RuinedPortalStructurePiece;
import net.minecraft.world.gen.feature.RuinedPortalFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(RuinedPortalFeature.Start.class)
public class RuinedPortalFeatureStartMixin {

    /**
     * Fixes ruined portals generating buried, or under the sand in the desert biomes.
     */
    @ModifyVariable(method = "init(Lnet/minecraft/util/registry/DynamicRegistryManager;Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/structure/StructureManager;IILnet/minecraft/world/biome/Biome;Lnet/minecraft/world/gen/feature/RuinedPortalFeatureConfig;)V", at = @At(value = "STORE", ordinal = 0))
    private RuinedPortalStructurePiece.VerticalPlacement changeDesertVerticalPlacement(RuinedPortalStructurePiece.VerticalPlacement verticalPlacement) {
        return RuinedPortalStructurePiece.VerticalPlacement.ON_LAND_SURFACE;
    }
}