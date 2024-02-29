package net.dillon8775.easierspeedrunning.mixin.main.block;

import net.minecraft.loot.function.ApplyBonusLootFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Random;

@Mixin(ApplyBonusLootFunction.OreDrops.class)
public class BetterLoot {

    /**
     * Applies better loot drops from ores.
     */
    @Overwrite
    public int getValue(Random random, int initialCount, int enchantmentLevel) {
        return enchantmentLevel > 0 ? initialCount * (enchantmentLevel + 1) : initialCount;
    }
}