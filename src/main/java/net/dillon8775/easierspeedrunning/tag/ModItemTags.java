package net.dillon8775.easierspeedrunning.tag;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunning.info;

public class ModItemTags {
    public static TagKey<Item> FIREPROOF_ITEMS = TagKey.of(Registry.ITEM_KEY, new Identifier(EasierSpeedrunning.MOD_ID, "fireproof_items"));
    public static TagKey<Item> NETHER_PORTAL_BASE_BLOCKS = TagKey.of(Registry.ITEM_KEY, new Identifier(EasierSpeedrunning.MOD_ID, "nether_portal_base_blocks"));

    public static void init() {
        info("Initialized item tags.");
    }
}