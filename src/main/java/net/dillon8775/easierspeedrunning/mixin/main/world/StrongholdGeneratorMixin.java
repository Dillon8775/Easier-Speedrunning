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
    private static final StrongholdGenerator.PieceData[] ALL_PIECES = DOOM_MODE ? new StrongholdGenerator.PieceData[]{
            new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 25, 5),
            new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 50, 5),
            new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 25, 5),
            new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 25, 5),
            new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 75, 5),
            new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 50, 5),
            new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 50, 5),
            new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 50, 5),
            new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 25, 5),
            new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 100, EasierSpeedrunning.options().strongholdLibraryCount * 2) {
                public boolean canGenerate(int chainLength) {
                    return super.canGenerate(chainLength) && chainLength > 3;
                }
            }, new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 50, 1) {
        public boolean canGenerate(int chainLength) {
            return super.canGenerate(chainLength) && chainLength > 5;
        }
    }} : new StrongholdGenerator.PieceData[]{
            new StrongholdGenerator.PieceData(StrongholdGenerator.Corridor.class, 20, 2),
            new StrongholdGenerator.PieceData(StrongholdGenerator.PrisonHall.class, 5, 1),
            new StrongholdGenerator.PieceData(StrongholdGenerator.LeftTurn.class, 10, 2),
            new StrongholdGenerator.PieceData(StrongholdGenerator.RightTurn.class, 10, 2),
            new StrongholdGenerator.PieceData(StrongholdGenerator.SquareRoom.class, 20, 1),
            new StrongholdGenerator.PieceData(StrongholdGenerator.Stairs.class, 10, 1),
            new StrongholdGenerator.PieceData(StrongholdGenerator.SpiralStaircase.class, 10, 1),
            new StrongholdGenerator.PieceData(StrongholdGenerator.FiveWayCrossing.class, 10, 2),
            new StrongholdGenerator.PieceData(StrongholdGenerator.ChestCorridor.class, 25, 2),
            new StrongholdGenerator.PieceData(StrongholdGenerator.PortalRoom.class, 200, EasierSpeedrunning.options().strongholdPortalRoomCount) {
        public boolean canGenerate(int chainLength) {
            return super.canGenerate(chainLength);
        }
    }, new StrongholdGenerator.PieceData(StrongholdGenerator.Library.class, 200, EasierSpeedrunning.options().strongholdLibraryCount) {
        public boolean canGenerate(int chainLength) {
            return super.canGenerate(chainLength);
        }
    }};
}