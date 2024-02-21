package net.dillon8775.easierspeedrunning.mixin.main.boat;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.vehicle.BoatEntity;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Allows for custom boat entity types to be added to the game.
 * <p>Without this, the game crashes because it thinks {@link BoatEntity.Type} is null.</p>
 */
@Mixin(BoatEntity.Type.class)
public class BoatEntityTypeMixin {
    @Shadow @Final @Mutable
    private static BoatEntity.Type[] field_7724;

    @Invoker("<init>")
    public static BoatEntity.Type invokeInit(String enumName, int internalId, Block wood, String name) {
        throw new AssertionError();
    }

    @Unique
    private static final BoatEntity.Type CRIMSON = addType("CRIMSON", Blocks.CRIMSON_PLANKS, "crimson");
    @Unique
    private static final BoatEntity.Type WARPED = addType("WARPED", Blocks.WARPED_PLANKS, "warped");

    @Unique
    private static BoatEntity.Type addType(String enumName, Block wood, String name) {
        List<BoatEntity.Type> variants = new ArrayList<>(Arrays.asList(field_7724));
        BoatEntity.Type boatType = invokeInit(enumName, variants.get(variants.size() - 1).ordinal() + 1, wood, name);
        variants.add(boatType);
        field_7724 = variants.toArray(new BoatEntity.Type[0]);
        return boatType;
    }
}