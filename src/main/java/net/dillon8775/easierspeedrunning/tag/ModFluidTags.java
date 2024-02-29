package net.dillon8775.easierspeedrunning.tag;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.info;

public class ModFluidTags {
    public static Tag<Fluid> BOAT_SAFE_FLUIDS = TagRegistry.fluid(new Identifier(EasierSpeedrunning.MOD_ID, "boat_safe_fluids"));

    public static void init() {
        info("Initialized fluid tags.");
    }
}