package net.dillon8775.easierspeedrunning.mixin.main.item;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.dillon8775.easierspeedrunning.option.ClientModOptions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.ConfiguredStructureFeatureTags;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderEyeItem.class)
public class BetterEnderEye extends Item {

    public BetterEnderEye(Settings settings) {
        super(settings);
    }

    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"))
    private void tellUser(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        String structureType = "Stronghold";
        BlockPos playerPos = user.getBlockPos();
        BlockPos blockPos = ((ServerWorld)world).locateStructure(ConfiguredStructureFeatureTags.EYE_OF_ENDER_LOCATED, user.getBlockPos(), 100, false);
        int structureDistance = MathHelper.floor(getDistance(playerPos.getX(), playerPos.getZ(), blockPos.getX(), blockPos.getZ()));
        boolean bl = ClientModOptions.CLIENT_OPTIONS.itemMessages == ClientModOptions.ItemMessages.ACTIONBAR;
        user.sendMessage(new TranslatableText("item.minecraft.ender_eye.blocks_away", structureType, structureDistance).formatted(bl ? Formatting.GREEN : Formatting.WHITE), bl);
        EasierSpeedrunning.debug("Found stronghold at " + structureDistance + " blocks away.");
    }

    @Unique
    private static float getDistance(int x1, int z1, int x2, int z2) {
        int i = x2 - x1;
        int j = z2 - z1;
        return MathHelper.sqrt((float) (i * i + j * j));
    }
}