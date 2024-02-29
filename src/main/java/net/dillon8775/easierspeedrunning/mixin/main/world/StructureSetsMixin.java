package net.dillon8775.easierspeedrunning.mixin.main.world;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.dillon8775.easierspeedrunning.option.ModOptions;
import net.minecraft.structure.StructureSets;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.options;

/**
 * This mixin makes structures generate more commonly, as it was changed in the Minecraft 1.18.2 update.
 * <p>With {@link ModifyArgs}, we can get the current spacing and separation values of each structure, using {@code ordinals}, and replace them with our {@link EasierSpeedrunning} values.</p>
 */
@Mixin(StructureSets.class)
public interface StructureSetsMixin {
    // ORDINALS (for I;I;SpreadType):
    // 0 = village
    // 1 = desert pyramid
    // 3 = jungle pyramid
    // 5 = pillager outpost
    // 6 = ocean monument
    // 7 = woodland mansion
    // 9 = ruined portal
    // 10 = shipwreck
    // 12 = nether complexes
    // 14 = end city

    // no ordinal for stronghold because it uses ConcentricRingsStructurePlacement

    /**
     * Changes the villages {@code spacing} and {@code separation} values.
     */
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/RandomSpreadStructurePlacement;<init>(IILnet/minecraft/world/gen/chunk/placement/SpreadType;I)V", ordinal = 0))
    private static void modifyVillageConfig(Args args) {
        if (isStructureSpawnRates()) {
            args.set(0, EasierSpeedrunning.getVillageSpacing());
            args.set(1, EasierSpeedrunning.getVillageSeparation());
        }
    }

    /**
     * Changes the desert pyramids {@code spacing} and {@code separation} values.
     */
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/RandomSpreadStructurePlacement;<init>(IILnet/minecraft/world/gen/chunk/placement/SpreadType;I)V", ordinal = 1))
    private static void modifyDesertPyramidConfig(Args args) {
        if (isStructureSpawnRates()) {
            args.set(0, EasierSpeedrunning.getDesertPyramidSpacing());
            args.set(1, EasierSpeedrunning.getDesertPyramidSeparation());
        }
    }

    /**
     * Changes the jungle pyramids {@code spacing} and {@code separation} values.
     */
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/RandomSpreadStructurePlacement;<init>(IILnet/minecraft/world/gen/chunk/placement/SpreadType;I)V", ordinal = 3))
    private static void modifyJunglePyramidConfig(Args args) {
        if (isStructureSpawnRates()) {
            args.set(0, EasierSpeedrunning.getJunglePyramidSpacing());
            args.set(1, EasierSpeedrunning.getJunglePyramidSeparation());
        }
    }


    /**
     * Changes the pillager outposts {@code spacing} and {@code separation} values.
     */
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/RandomSpreadStructurePlacement;<init>(IILnet/minecraft/world/gen/chunk/placement/SpreadType;I)V", ordinal = 5))
    private static void modifyPillagerOutpostConfig(Args args) {
        if (isStructureSpawnRates()) {
            args.set(0, EasierSpeedrunning.getPillagerOutpostSpacing());
            args.set(1, EasierSpeedrunning.getPillagerOutpostSeparation());
        }
    }

    /**
     * Changes the end city {@code spacing} and {@code separation} values.
     */
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/RandomSpreadStructurePlacement;<init>(IILnet/minecraft/world/gen/chunk/placement/SpreadType;I)V", ordinal = 14))
    private static void modifyEndCityConfig(Args args) {
        if (isStructureSpawnRates()) {
            args.set(0, EasierSpeedrunning.getEndCitySpacing());
            args.set(1, EasierSpeedrunning.getEndCitySeparation());
        }
    }

    /**
     * Changes the woodland mansions {@code spacing} and {@code separation} values.
     */
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/RandomSpreadStructurePlacement;<init>(IILnet/minecraft/world/gen/chunk/placement/SpreadType;I)V", ordinal = 7))
    private static void modifyWoodlandMansionConfig(Args args) {
        if (isStructureSpawnRates()) {
            args.set(0, EasierSpeedrunning.getWoodlandMansionSpacing());
            args.set(1, EasierSpeedrunning.getWoodlandMansionSeparation());
        }
    }

    /**
     * Changes the ruined portals {@code spacing} and {@code separation} values.
     */
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/RandomSpreadStructurePlacement;<init>(IILnet/minecraft/world/gen/chunk/placement/SpreadType;I)V", ordinal = 9))
    private static void modifyRuinedPortalConfig(Args args) {
        if (isStructureSpawnRates()) {
            args.set(0, EasierSpeedrunning.getRuinedPortalSpacing());
            args.set(1, EasierSpeedrunning.getRuinedPortalSeparation());
        }
    }

    /**
     * Changes the shipwrecks {@code spacing} and {@code separation} values.
     */
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/RandomSpreadStructurePlacement;<init>(IILnet/minecraft/world/gen/chunk/placement/SpreadType;I)V", ordinal = 10))
    private static void modifyShipwreckSpacing(Args args) {
        if (isStructureSpawnRates()) {
            args.set(0, EasierSpeedrunning.getShipwreckSpacing());
            args.set(1, EasierSpeedrunning.getShipwreckSeparation());
        }
    }

    /**
     * Changes the nether complexes (fortresses and bastions) {@code spacing} and {@code separation} values.
     */
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/RandomSpreadStructurePlacement;<init>(IILnet/minecraft/world/gen/chunk/placement/SpreadType;I)V", ordinal = 12))
    private static void modifyNetherComplexesConfig(Args args) {
        if (isStructureSpawnRates()) {
            args.set(0, EasierSpeedrunning.getNetherComplexesSpacing());
            args.set(1, EasierSpeedrunning.getNetherComplexesSeparation());
        }
    }

    /**
     * Changes the stronghold config directly.
     */
    @ModifyArgs(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/placement/ConcentricRingsStructurePlacement;<init>(III)V"))
    private static void modifyStrongholdConfig(Args args) {
        if (isStructureSpawnRates()) {
            args.set(0, options().strongholdDistance);
            args.set(1, options().strongholdSpread);
            args.set(2, options().strongholdCount);
        }
    }

    /**
     * Makes sure {@link net.dillon8775.easierspeedrunning.option.ModOptions.StructureSpawnRates} isn't {@code off} before changing the config values.
     */
    @Unique
    private static boolean isStructureSpawnRates() {
        return options().structureSpawnRates != ModOptions.StructureSpawnRates.OFF;
    }
}