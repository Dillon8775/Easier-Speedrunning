package net.dillon8775.easierspeedrunning.mixin.main.structure;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

/**
 * Doesn't exist in the 1.16.1 version of Fabric API, so I had to manually add it.
 * <p>See {@link EasierSpeedrunning#initializeModStructureConfigs()} for more.</p>
 */
@Mixin(StructuresConfig.class)
public interface StructuresConfigAccessor {
    @Mutable
    @Accessor("structures")
    void setStructures(Map<StructureFeature<?>, StructureConfig> structures);
}