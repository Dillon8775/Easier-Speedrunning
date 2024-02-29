package net.dillon8775.easierspeedrunning.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.dillon8775.easierspeedrunning.EasierSpeedrunningClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.client.render.FogShape;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Environment(EnvType.CLIENT)
@Mixin(BackgroundRenderer.class)
public class Fog {

    /**
     * Applies the {@code Fog} option.
     */
    @Overwrite
    public static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog) {
        float g;
        float f;
        final int fog = 2147483647;
        final float lavaVisionDistance = 35.0F;
        CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
        Entity entity = camera.getFocusedEntity();
        FogShape fogShape = FogShape.SPHERE;
        if (cameraSubmersionType == CameraSubmersionType.LAVA) {
            if (entity.isSpectator()) {
                f = -8.0f;
                g = viewDistance * 0.5f;
            } else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
                f = 0.0f;
                g = lavaVisionDistance;
            } else {
                f = 0.25f;
                g = 1.0f;
            }
        } else if (cameraSubmersionType == CameraSubmersionType.POWDER_SNOW) {
            if (entity.isSpectator()) {
                f = -8.0f;
                g = viewDistance * 0.5f;
            } else {
                f = 0.0f;
                g = 2.0f;
            }
        } else if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(StatusEffects.BLINDNESS)) {
            int i = ((LivingEntity)entity).getStatusEffect(StatusEffects.BLINDNESS).getDuration();
            float h = MathHelper.lerp(Math.min(1.0f, (float)i / 20.0f), viewDistance, 5.0f);
            if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                f = 0.0f;
                g = h * 0.8f;
            } else {
                f = cameraSubmersionType == CameraSubmersionType.WATER ? -4.0f : h * 0.25f;
                g = h;
            }
        } else if (cameraSubmersionType == CameraSubmersionType.WATER) {
            f = -8.0f;
            g = 96.0f;
            if (entity instanceof ClientPlayerEntity) {
                ClientPlayerEntity clientPlayerEntity = (ClientPlayerEntity)entity;
                g *= Math.max(0.25f, clientPlayerEntity.getUnderwaterVisibility());
                RegistryEntry<Biome> registryEntry = clientPlayerEntity.world.getBiome(clientPlayerEntity.getBlockPos());
                if (Biome.getCategory(registryEntry) == Biome.Category.SWAMP) {
                    g *= 0.85f;
                }
            }
            if (g > viewDistance) {
                g = viewDistance;
                fogShape = FogShape.CYLINDER;
            }
        } else if (thickFog) {
            f = viewDistance * 0.05f;
            if (!EasierSpeedrunningClient.clientOptions().fog) {
                g = fog;
            } else {
                g = Math.min(viewDistance, 192.0F) * 0.5F;
            }
        } else if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
            f = 0.0f;
            g = viewDistance;
            fogShape = FogShape.CYLINDER;
        } else {
            float j = MathHelper.clamp(viewDistance / 10.0f, 4.0f, 64.0f);
            f = viewDistance - j;
            if (!EasierSpeedrunningClient.clientOptions().fog) {
                g = fog;
            } else {
                g = viewDistance;
            }
            fogShape = FogShape.CYLINDER;
        }
        RenderSystem.setShaderFogStart(f);
        RenderSystem.setShaderFogEnd(g);
        RenderSystem.setShaderFogShape(fogShape);
    }
}