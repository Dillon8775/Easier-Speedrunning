package net.dillon8775.easierspeedrunning.tag;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.info;

public class ModItemTags {
    public static Tag<Item> FIREPROOF_ITEMS = TagFactory.ITEM.create(new Identifier(EasierSpeedrunning.MOD_ID, "fireproof_items"));
    public static Tag<Item> NETHER_PORTAL_BASE_BLOCKS = TagFactory.ITEM.create(new Identifier(EasierSpeedrunning.MOD_ID, "nether_portal_base_blocks"));

    public static void init() {
        info("Initialized item tags.");
    }
}