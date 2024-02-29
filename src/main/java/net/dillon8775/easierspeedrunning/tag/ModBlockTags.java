package net.dillon8775.easierspeedrunning.tag;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.info;

public class ModBlockTags {
    public static Tag<Block> NETHER_PORTAL_BASE_BLOCKS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "nether_portal_base_blocks"));
    public static Tag<Block> FIREPROOF_BOAT_BASE_BLOCKS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "fireproof_boat_base_blocks"));

    public static void init() {
        info("Initialized block tags.");
    }

    public static class BlockHardness {
        public static Tag<Block> INSTANT_BREAK = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/instant_break"));
        public static Tag<Block> ZERO_ONE_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/0-1_hardness"));
        public static Tag<Block> ZERO_TWO_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/0-2_hardness"));
        public static Tag<Block> ZERO_THREEFIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/0-35_hardness"));
        public static Tag<Block> ZERO_THREESEVEN_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/0-37_hardness"));
        public static Tag<Block> ZERO_FOUR_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/0-4_hardness"));
        public static Tag<Block> ZERO_FIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/0-5_hardness"));
        public static Tag<Block> ZERO_SIX_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/0-6_hardness"));
        public static Tag<Block> ZERO_SIXFIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/0-65_hardness"));
        public static Tag<Block> ZERO_SEVEN_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/0-7_hardness"));
        public static Tag<Block> ZERO_EIGHT_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/0-8_hardness"));
        public static Tag<Block> ONE_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/1_hardness"));
        public static Tag<Block> ONE_THREE_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/1-3_hardness"));
        public static Tag<Block> ONE_FOUR_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/1-4_hardness"));
        public static Tag<Block> ONE_FIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/1-5_hardness"));
        public static Tag<Block> ONE_SIX_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/1-6_hardness"));
        public static Tag<Block> TWO_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/2_hardness"));
        public static Tag<Block> TWO_FIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/2-5_hardness"));
        public static Tag<Block> THREE_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/3_hardness"));
        public static Tag<Block> FOUR_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/4_hardness"));
        public static Tag<Block> FOUR_FIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/4-5_hardness"));
        public static Tag<Block> FIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/5_hardness"));
        public static Tag<Block> SIX_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/6_hardness"));
        public static Tag<Block> SEVEN_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/7_hardness"));
        public static Tag<Block> EIGHT_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/8_hardness"));
        public static Tag<Block> NINE_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/9_hardness"));
        public static Tag<Block> TEN_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/10_hardness"));
        public static Tag<Block> TWENTY_FIVE_HARDNESS = TagFactory.BLOCK.create(new Identifier(EasierSpeedrunning.MOD_ID, "block_hardness/25_hardness"));
    }
}