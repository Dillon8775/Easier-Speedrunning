package net.dillon8775.easierspeedrunning.mixin.client.fix;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(BoatEntityRenderer.class)
public class BoatEntityRendererMixin {

    /**
     * Fixes a crash bug where the game can't detect the crimson and warped boat texture locations.
     */
    @Inject(at = @At(value = "HEAD"), method = "getTexture(Lnet/minecraft/entity/vehicle/BoatEntity;)Lnet/minecraft/util/Identifier;", cancellable = true)
    public void getTextureLocation(BoatEntity boat, CallbackInfoReturnable<Identifier> cir) {
        if (boat.getBoatType().equals(EasierSpeedrunning.CRIMSON_BOAT_TYPE)) {
            cir.setReturnValue(new Identifier("textures/entity/boat/crimson.png"));
        } else if (boat.getBoatType().equals(EasierSpeedrunning.WARPED_BOAT_TYPE)) {
            cir.setReturnValue(new Identifier("textures/entity/boat/warped.png"));
        }
    }
}