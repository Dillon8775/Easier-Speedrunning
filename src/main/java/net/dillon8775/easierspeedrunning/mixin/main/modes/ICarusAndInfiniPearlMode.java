package net.dillon8775.easierspeedrunning.mixin.main.modes;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.command.arguments.ItemStackArgumentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.stat.Stats;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Configuration for {@code iCarus mode} and {@code InfiniPearl mode}.
 */
@Mixin(ServerPlayerEntity.class)
public abstract class ICarusAndInfiniPearlMode extends PlayerEntity {
    @Shadow @Final
    private ServerStatHandler statHandler;

    public ICarusAndInfiniPearlMode(World world, BlockPos blockPos, GameProfile gameProfile) {
        super(world, blockPos, gameProfile);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci) throws CommandSyntaxException {
        if (this.statHandler.getStat(Stats.CUSTOM.getOrCreateStat(Stats.PLAY_ONE_MINUTE)) == 0) {
            ItemStack item;
            if (EasierSpeedrunning.options().iCarusMode) {
                item = this.itemStackFromString("minecraft:elytra{Unbreakable:1b}", 1);
                ItemStack item2 = this.itemStackFromString("minecraft:firework_rocket{Fireworks:{Flight:3b}}", 64);
                this.inventory.armor.set(2, item);
                this.inventory.main.set(0, item2);
            }

            if (EasierSpeedrunning.options().infiniPearlMode) {
                item = new ItemStack(Items.ENDER_PEARL, 1);
                item.addEnchantment(Enchantments.INFINITY, 1);
                item.getOrCreateTag().putInt("HideFlags", 1);

                LiteralText text = new LiteralText("InfiniPearl™");
                text.setStyle(text.getStyle().withItalic(false));
                item.setCustomName(text);

                if (!EasierSpeedrunning.options().iCarusMode) {
                    this.inventory.main.set(0, item);
                } else {
                    this.inventory.main.set(1, item);
                }
            }
        }
    }

    @Unique
    private ItemStack itemStackFromString(String string, int count) throws CommandSyntaxException {
        return new ItemStackArgumentType().parse(new StringReader(string)).createStack(count, false);
    }
}