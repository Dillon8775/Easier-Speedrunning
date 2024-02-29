package net.dillon8775.easierspeedrunning.mixin.main.world;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(StrongholdGenerator.PortalRoom.class)
public class StrongholdPortalRoomMixin {

    /**
     * Increases the chance of an ender eye generating in a stronghold.
     */
    @ModifyConstant(method = "generate", constant = @Constant(floatValue = 0.9F))
    private float changeEyeChance(float constant) {
        return EasierSpeedrunning.getEnderEyeChance();
    }
}