package net.dillon8775.easierspeedrunning.mixin.main.world;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.DOOM_MODE;

/**
 * Changes the way strongholds generate, making them smaller.
 */
@Mixin(StrongholdGenerator.class)
public class StrongholdGeneratorMixin {
    @Shadow
    private static final StrongholdGenerator.PieceSetting[] ALL_PIECE_SETTINGS = DOOM_MODE ? new StrongholdGenerator.PieceSetting[]{
            new StrongholdGenerator.PieceSetting(StrongholdGenerator.Corridor.class, 25, 5),
            new StrongholdGenerator.PieceSetting(StrongholdGenerator.PrisonHall.class, 50, 5),
            new StrongholdGenerator.PieceSetting(StrongholdGenerator.LeftTurn.class, 25, 5),
            new StrongholdGenerator.PieceSetting(StrongholdGenerator.RightTurn.class, 25, 5),
            new StrongholdGenerator.PieceSetting(StrongholdGenerator.SquareRoom.class, 75, 5),
            new StrongholdGenerator.PieceSetting(StrongholdGenerator.Stairs.class, 50, 5),
            new StrongholdGenerator.PieceSetting(StrongholdGenerator.SpiralStaircase.class, 50, 5),
            new StrongholdGenerator.PieceSetting(StrongholdGenerator.FiveWayCrossing.class, 50, 5),
            new StrongholdGenerator.PieceSetting(StrongholdGenerator.ChestCorridor.class, 25, 5),
            new StrongholdGenerator.PieceSetting(StrongholdGenerator.Library.class, 100, EasierSpeedrunning.options().strongholdLibraryCount * 2) {
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 3;
                }
            }, new StrongholdGenerator.PieceSetting(StrongholdGenerator.PortalRoom.class, 50, 1) {
        public boolean canGenerate(int chainLength) {
            return super.canGenerate(chainLength) && chainLength > 5;
        }
    }} : new StrongholdGenerator.PieceSetting[]{
            new StrongholdGenerator.PieceSetting(StrongholdGenerator.Corridor.class, 20, 2),
            new StrongholdGenerator.PieceSetting(StrongholdGenerator.PrisonHall.class, 5, 1),
            new StrongholdGenerator.PieceSetting(StrongholdGenerator.LeftTurn.class, 10, 2),
            new StrongholdGenerator.PieceSetting(StrongholdGenerator.RightTurn.class, 10, 2),
            new StrongholdGenerator.PieceSetting(StrongholdGenerator.SquareRoom.class, 20, 1),
            new StrongholdGenerator.PieceSetting(StrongholdGenerator.Stairs.class, 10, 1),
            new StrongholdGenerator.PieceSetting(StrongholdGenerator.SpiralStaircase.class, 10, 1),
            new StrongholdGenerator.PieceSetting(StrongholdGenerator.FiveWayCrossing.class, 10, 2),
            new StrongholdGenerator.PieceSetting(StrongholdGenerator.ChestCorridor.class, 25, 2),
            new StrongholdGenerator.PieceSetting(StrongholdGenerator.PortalRoom.class, 200, EasierSpeedrunning.options().strongholdPortalRoomCount) {
        public boolean canGenerate(int chainLength) {
            return super.canGenerate(chainLength);
        }
    }, new StrongholdGenerator.PieceSetting(StrongholdGenerator.Library.class, 200, EasierSpeedrunning.options().strongholdLibraryCount) {
        public boolean canGenerate(int chainLength) {
            return super.canGenerate(chainLength);
        }
    }};
}