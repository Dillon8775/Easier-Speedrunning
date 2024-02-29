package net.dillon8775.easierspeedrunning.mixin.main.world;

import net.minecraft.structure.NetherFortressGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Makes nether fortresses generate smaller, making navigating blaze spawners and other rooms easier.
 */
@Mixin(NetherFortressGenerator.class)
public class NetherFortressGeneratorMixin {
    @Shadow
    private static final NetherFortressGenerator.PieceData[] ALL_BRIDGE_PIECES = new NetherFortressGenerator.PieceData[]{
            new NetherFortressGenerator.PieceData(NetherFortressGenerator.Bridge.class, 10, 1),
            new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeCrossing.class, 10, 2),
            new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeSmallCrossing.class, 10, 2),
            new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgeStairs.class, 10, 1),
            new NetherFortressGenerator.PieceData(NetherFortressGenerator.BridgePlatform.class, 50, 3),
            new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorExit.class, 10, 1)};
    @Shadow
    private static final NetherFortressGenerator.PieceData[] ALL_CORRIDOR_PIECES = new NetherFortressGenerator.PieceData[]{
            new NetherFortressGenerator.PieceData(NetherFortressGenerator.SmallCorridor.class, 10, 2),
            new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorCrossing.class, 10, 2),
            new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorRightTurn.class, 25, 3),
            new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorLeftTurn.class, 25, 3),
            new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorStairs.class, 10, 2, true),
            new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorBalcony.class, 7, 2),
            new NetherFortressGenerator.PieceData(NetherFortressGenerator.CorridorNetherWartsRoom.class, 20, 2)};
}