package net.dillon8775.easierspeedrunning.mixin.main.item;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Replaces the vanilla Minecraft food items with better foods.
 * <p>See {@link EasierSpeedrunning}.</p>
 */
@Mixin(FoodComponents.class)
public class BetterFoods {
    @Shadow
    private static final FoodComponent APPLE, BAKED_POTATO, BEEF, BEETROOT, BREAD, CARROT, CHICKEN , CHORUS_FRUIT, COD, COOKED_BEEF, COOKED_CHICKEN, COOKED_COD, COOKED_MUTTON, COOKED_PORKCHOP, COOKED_RABBIT, COOKED_SALMON, COOKIE, DRIED_KELP, ENCHANTED_GOLDEN_APPLE, GOLDEN_APPLE, GOLDEN_CARROT, HONEY_BOTTLE, MELON_SLICE, MUTTON, POISONOUS_POTATO, PORKCHOP, POTATO, PUFFERFISH, PUMPKIN_PIE, RABBIT, ROTTEN_FLESH, SALMON, SPIDER_EYE, SWEET_BERRIES, TROPICAL_FISH;

    static {
        APPLE = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.APPLE : FoodComponents.APPLE;
        BAKED_POTATO = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.BAKED_POTATO : FoodComponents.BAKED_POTATO;
        BEEF = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.BEEF : FoodComponents.BEEF;
        BEETROOT = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.BEETROOT : FoodComponents.BEETROOT;
        BREAD = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.BREAD : FoodComponents.BREAD;
        CARROT = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.CARROT : FoodComponents.CARROT;
        CHICKEN = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.CHICKEN : FoodComponents.CHICKEN;
        CHORUS_FRUIT = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.CHORUS_FRUIT : FoodComponents.CHORUS_FRUIT;
        COD = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.COD : FoodComponents.COD;
        COOKED_BEEF = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.COOKED_BEEF : FoodComponents.COOKED_BEEF;
        COOKED_CHICKEN = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.COOKED_CHICKEN : FoodComponents.COOKED_CHICKEN;
        COOKED_COD = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.COOKED_COD : FoodComponents.COOKED_COD;
        COOKED_MUTTON = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.COOKED_MUTTON : FoodComponents.COOKED_MUTTON;
        COOKED_PORKCHOP = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.COOKED_PORKCHOP : FoodComponents.COOKED_PORKCHOP;
        COOKED_RABBIT = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.COOKED_RABBIT : FoodComponents.COOKED_RABBIT;
        COOKED_SALMON = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.COOKED_SALMON : FoodComponents.COOKED_SALMON;
        COOKIE = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.COOKIE : FoodComponents.COOKIE;
        DRIED_KELP = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.DRIED_KELP : FoodComponents.DRIED_KELP;
        ENCHANTED_GOLDEN_APPLE = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.ENCHANTED_GOLDEN_APPLE : FoodComponents.ENCHANTED_GOLDEN_APPLE;
        GOLDEN_APPLE = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.GOLDEN_APPLE : FoodComponents.GOLDEN_APPLE;
        GOLDEN_CARROT = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.GOLDEN_CARROT : FoodComponents.GOLDEN_CARROT;
        HONEY_BOTTLE = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.HONEY_BOTTLE : FoodComponents.HONEY_BOTTLE;
        MELON_SLICE = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.MELON_SLICE : FoodComponents.MELON_SLICE;
        MUTTON = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.MUTTON : FoodComponents.MUTTON;
        POISONOUS_POTATO = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.POISONOUS_POTATO : FoodComponents.POISONOUS_POTATO;
        PORKCHOP = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.PORKCHOP : FoodComponents.PORKCHOP;
        POTATO = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.POTATO : FoodComponents.POTATO;
        PUFFERFISH = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.PUFFERFISH : FoodComponents.PUFFERFISH;
        PUMPKIN_PIE = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.PUMPKIN_PIE : FoodComponents.PUMPKIN_PIE;
        RABBIT = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.RABBIT : FoodComponents.RABBIT;
        ROTTEN_FLESH = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.ROTTEN_FLESH : FoodComponents.ROTTEN_FLESH;
        SALMON = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.SALMON : FoodComponents.SALMON;
        SPIDER_EYE = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.SPIDER_EYE : FoodComponents.SPIDER_EYE;
        SWEET_BERRIES = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.SWEET_BERRIES : FoodComponents.SWEET_BERRIES;
        TROPICAL_FISH = EasierSpeedrunning.options().betterFoods ? EasierSpeedrunning.TROPICAL_FISH : FoodComponents.TROPICAL_FISH;
    }
}