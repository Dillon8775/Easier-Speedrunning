package net.dillon8775.easierspeedrunning.mixin.main.world;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.world.gen.chunk.StrongholdConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * Makes strongholds generate more commonly.
 * <p>This is done in a separate class because the {@link StrongholdConfig} is it's own configuration, unlike other default Minecraft structures.</p>
 */
@Mixin(StrongholdConfig.class)
public class StrongholdConfigMixin {

    @Overwrite
    public int getDistance() {
        return EasierSpeedrunning.options().strongholdDistance;
    }

    @Overwrite
    public int getSpread() {
        return EasierSpeedrunning.options().strongholdSpread;
    }

    /**
     * @return The total amount of strongholds that can generate per Minecraft world.
     */
    @Overwrite
    public int getCount() {
        return EasierSpeedrunning.options().strongholdCount;
    }
}