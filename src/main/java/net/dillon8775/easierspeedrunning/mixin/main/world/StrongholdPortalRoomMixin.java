package net.dillon8775.easierspeedrunning.mixin.main.world;

import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.DOOM_MODE;

@Mixin(StrongholdGenerator.PortalRoom.class)
public class StrongholdPortalRoomMixin {

    /**
     * Increases the chance of an ender eye generating in a stronghold.
     */
    @ModifyConstant(method = "generate", constant = @Constant(floatValue = 0.9F))
    private float changeEyeChance(float constant) {
        return DOOM_MODE ? 0.99F : 0.6F;
    }
}