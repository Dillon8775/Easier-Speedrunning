package net.dillon8775.easierspeedrunning.option;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.dillon8775.easierspeedrunning.EasierSpeedrunningClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.BooleanOption;
import net.minecraft.client.options.CyclingOption;
import net.minecraft.client.options.DoubleOption;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class ModListOptions {
    public static final CyclingOption STRUCTURE_SPAWN_RATES = new CyclingOption("easierspeedrunning.options.structure_spawn_rates", (gameOptions, newValue) ->
            EasierSpeedrunning.options().structureSpawnRates = ModOptions.StructureSpawnRates.byId((
                    EasierSpeedrunning.options().structureSpawnRates.getId() + newValue) % 8), (gameOptions, option) ->
            option.getDisplayPrefix().append(new TranslatableText(EasierSpeedrunning.options().structureSpawnRates.getTranslationKey())));
    public static final BooleanOption FASTER_BLOCK_BREAKING =
            new BooleanOption("easierspeedrunning.options.faster_block_breaking", options ->
                    EasierSpeedrunning.options().fasterBlockBreaking, (options, newValue) ->
                    EasierSpeedrunning.options().fasterBlockBreaking = newValue);
    public static final BooleanOption MODIFIED_BIOMES =
            new BooleanOption("easierspeedrunning.options.modified_biomes", options ->
                    EasierSpeedrunning.options().modifiedBiomes, (options, newValue) ->
                    EasierSpeedrunning.options().modifiedBiomes = newValue);
    public static final BooleanOption BETTER_FOODS =
            new BooleanOption("easierspeedrunning.options.better_foods", options ->
                    EasierSpeedrunning.options().betterFoods, (options, newValue) ->
                    EasierSpeedrunning.options().betterFoods = newValue);
    public static final BooleanOption FIREPROOF_ITEMS =
            new BooleanOption("easierspeedrunning.options.fireproof_items", options ->
                    EasierSpeedrunning.options().fireproofItems, (options, newValue) ->
                    EasierSpeedrunning.options().fireproofItems = newValue);
    public static final BooleanOption ICARUS_MODE =
            new BooleanOption("easierspeedrunning.options.icarus_mode", options ->
                    EasierSpeedrunning.options().iCarusMode, (options, newValue) ->
                    EasierSpeedrunning.options().iCarusMode = newValue);
    public static final BooleanOption INFINI_PEARL_MODE =
            new BooleanOption("easierspeedrunning.options.infini_pearl_mode", options ->
                    EasierSpeedrunning.options().infiniPearlMode, (options, newValue) ->
                    EasierSpeedrunning.options().infiniPearlMode = newValue);
    public static final DoubleOption DRAGON_PERCH_TIME =
            new DoubleOption("easierspeedrunning.options.dragon_perch_time",
                    9.0D, 90.0D, 1.0F, gameOptions ->
                    (double)EasierSpeedrunning.options().dragonPerchTime, (gameOptions, newValue) ->
                    EasierSpeedrunning.options().dragonPerchTime = newValue.intValue(), (gameOptions, option) ->
                    EasierSpeedrunning.options().dragonPerchTime <= 9 ?
                            new LiteralText(I18n.translate("easierspeedrunning.options.dragon_perch_time") + ": OFF") :
                            new LiteralText(I18n.translate("easierspeedrunning.options.dragon_perch_time") + ": " + (int)option.get(gameOptions) + "s"));
    public static final DoubleOption NETHER_PORTAL_COOLDOWN =
            new DoubleOption("easierspeedrunning.options.nether_portal_cooldown",
                    0.0D, 20.0D, 1.0F, gameOptions ->
                    (double)EasierSpeedrunning.options().netherPortalCooldown, (gameOptions, newValue) ->
                    EasierSpeedrunning.options().netherPortalCooldown = newValue.intValue(), (gameOptions, option) ->
                    EasierSpeedrunning.options().netherPortalCooldown == 0 ?
                            new LiteralText(I18n.translate("easierspeedrunning.options.portal_cooldown") + ": None") :
                            new LiteralText(I18n.translate("easierspeedrunning.options.nether_portal_cooldown") + ": " + (int)option.get(gameOptions) + "s"));
    public static final BooleanOption DOOM_MODE =
            new BooleanOption("easierspeedrunning.options.doom_mode", options ->
                    EasierSpeedrunning.options().doomMode, (options, newValue) ->
                    EasierSpeedrunning.options().doomMode = newValue);
    public static final BooleanOption NETHER_WATER =
            new BooleanOption("easierspeedrunning.options.nether_water", options ->
                    EasierSpeedrunning.options().netherWater, (options, newValue) ->
                    EasierSpeedrunning.options().netherWater = newValue);
    public static final BooleanOption LAVA_BOATS =
            new BooleanOption("easierspeedrunning.options.lava_boats", options ->
                    EasierSpeedrunning.options().lavaBoats, (options, newValue) ->
                    EasierSpeedrunning.options().lavaBoats = newValue);
    public static final BooleanOption FALL_DAMAGE =
            new BooleanOption("easierspeedrunning.options.fall_damage", options ->
                    EasierSpeedrunning.options().fallDamage, (options, newValue) ->
                    EasierSpeedrunning.options().fallDamage = newValue);
    public static final BooleanOption KILL_GHAST_ON_FIREBALL =
            new BooleanOption("easierspeedrunning.options.kill_ghast_on_fireball", options ->
                    EasierSpeedrunning.options().killGhastOnFireball, (options, newValue) ->
                    EasierSpeedrunning.options().killGhastOnFireball = newValue);
    public static final BooleanOption BETTER_ANVIL =
            new BooleanOption("easierspeedrunning.options.better_anvil", options ->
                    EasierSpeedrunning.options().betterAnvil, (options, newValue) ->
                    EasierSpeedrunning.options().betterAnvil = newValue);
    public static final DoubleOption ANVIL_COST_LIMIT =
            new DoubleOption("easierspeedrunning.options.anvil_cost_limit",
                    1.0D, 50.0D, 1.0F, gameOptions ->
                    (double)EasierSpeedrunning.options().anvilCostLimit, (gameOptions, newValue) ->
                    EasierSpeedrunning.options().anvilCostLimit = newValue.intValue(), (gameOptions, option) ->
                    EasierSpeedrunning.options().anvilCostLimit == 50 ?
                            new LiteralText(I18n.translate("easierspeedrunning.options.anvil_cost_limit") + ": No Limit") :
                            EasierSpeedrunning.options().anvilCostLimit == 1 ?
                                    new LiteralText(I18n.translate("easierspeedrunning.options.anvil_cost_limit") + ": " + (int)option.get(gameOptions) + " level") :
                                    new LiteralText(I18n.translate("easierspeedrunning.options.anvil_cost_limit") + ": " + (int)option.get(gameOptions) + " levels"));
    public static final DoubleOption STRONGHOLD_PORTAL_ROOM_COUNT =
            new DoubleOption("easierspeedrunning.options.stronghold_portal_room_count",
                    1.0D, 3.0D, 1.0F, gameOptions ->
                    (double)EasierSpeedrunning.options().strongholdPortalRoomCount, (gameOptions, newValue) ->
                    EasierSpeedrunning.options().strongholdPortalRoomCount = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("easierspeedrunning.options.stronghold_portal_room_count") + ": " + (int)option.get(gameOptions)));
    public static final DoubleOption STRONGHOLD_LIBRARY_COUNT =
            new DoubleOption("easierspeedrunning.options.stronghold_library_count",
                    1.0D, 8.0D, 1.0F, gameOptions ->
                    (double)EasierSpeedrunning.options().strongholdLibraryCount, (gameOptions, newValue) ->
                    EasierSpeedrunning.options().strongholdLibraryCount = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("easierspeedrunning.options.stronghold_library_count") + ": " + (int)option.get(gameOptions)));
    public static final DoubleOption STRONGHOLD_DISTANCE =
            new DoubleOption("easierspeedrunning.options.stronghold_distance",
                    3.0D, 64.0D, 1.0F, gameOptions ->
                    (double)EasierSpeedrunning.options().strongholdDistance, (gameOptions, newValue) ->
                    EasierSpeedrunning.options().strongholdDistance = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("easierspeedrunning.options.stronghold_distance") + ": " + (int)option.get(gameOptions)));
    public static final DoubleOption STRONGHOLD_SPREAD =
            new DoubleOption("easierspeedrunning.options.stronghold_distance",
                    2.0D, 32.0D, 1.0F, gameOptions ->
                    (double)EasierSpeedrunning.options().strongholdDistance, (gameOptions, newValue) ->
                    EasierSpeedrunning.options().strongholdDistance = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("easierspeedrunning.options.stronghold_spread") + ": " + (int)option.get(gameOptions)));
    public static final DoubleOption STRONGHOLD_COUNT =
            new DoubleOption("easierspeedrunning.options.stronghold_count",
                    4.0D, 156.0D, 4.0F, gameOptions ->
                    (double)EasierSpeedrunning.options().strongholdCount, (gameOptions, newValue) ->
                    EasierSpeedrunning.options().strongholdCount = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("easierspeedrunning.options.stronghold_count") + ": " + (int)option.get(gameOptions)));
    public static final BooleanOption HIGHER_BREATH_TIME =
            new BooleanOption("easierspeedrunning.options.higher_breath_time", options ->
                    EasierSpeedrunning.options().higherBreathTime, (options, newValue) ->
                    EasierSpeedrunning.options().higherBreathTime = newValue);
    public static final BooleanOption FOG =
            new BooleanOption("easierspeedrunning.options.fog", options ->
                    EasierSpeedrunningClient.clientOptions().fog, (options, newValue) -> {
                EasierSpeedrunningClient.clientOptions().fog = newValue;
                MinecraftClient.getInstance().worldRenderer.reload();
            });
    public static final CyclingOption PANORAMA = new CyclingOption("easierspeedrunning.options.panorama", (gameOptions, newValue) ->
            EasierSpeedrunningClient.clientOptions().panorama = ClientModOptions.Panorama.byId((
                    EasierSpeedrunningClient.clientOptions().panorama.getId() + newValue) % 7), (gameOptions, option) ->
            option.getDisplayPrefix().append(new TranslatableText(EasierSpeedrunningClient.clientOptions().panorama.getTranslationKey())));
    public static final CyclingOption ITEM_MESSAGES = new CyclingOption("easierspeedrunning.options.item_messages", (gameOptions, newValue) ->
            EasierSpeedrunningClient.clientOptions().itemMessages = ClientModOptions.ItemMessages.byId((
                    EasierSpeedrunningClient.clientOptions().itemMessages.getId() + newValue) % 3), (gameOptions, option) ->
            option.getDisplayPrefix().append(new TranslatableText(EasierSpeedrunningClient.clientOptions().itemMessages.getTranslationKey())));
    public static final BooleanOption FAST_WORLD_CREATION =
            new BooleanOption("easierspeedrunning.options.fast_world_creation", options ->
                    EasierSpeedrunningClient.clientOptions().fastWorldCreation, (options, newValue) ->
                    EasierSpeedrunningClient.clientOptions().fastWorldCreation = newValue);
    public static final CyclingOption GAMEMODE = new CyclingOption("easierspeedrunning.options.gamemode", (gameOptions, newValue) ->
            EasierSpeedrunningClient.clientOptions().gameMode = ClientModOptions.GameMode.byId((
                    EasierSpeedrunningClient.clientOptions().gameMode.getId() + newValue) % 4), (gameOptions, option) ->
            option.getDisplayPrefix().append(new TranslatableText(EasierSpeedrunningClient.clientOptions().gameMode.getTranslationKey())));

    public static final CyclingOption DIFFICULTY = new CyclingOption("easierspeedrunning.options.difficulty", (gameOptions, newValue) ->
            EasierSpeedrunningClient.clientOptions().difficulty = ClientModOptions.Difficulty.byId((
                    EasierSpeedrunningClient.clientOptions().difficulty.getId() + newValue) % 6), (gameOptions, option) ->
            option.getDisplayPrefix().append(new TranslatableText(EasierSpeedrunningClient.clientOptions().difficulty.getTranslationKey())));
    public static final BooleanOption ALLOW_CHEATS =
            new BooleanOption("easierspeedrunning.options.allow_cheats", options ->
                    EasierSpeedrunningClient.clientOptions().allowCheats, (options, newValue) ->
                    EasierSpeedrunningClient.clientOptions().allowCheats = newValue);
    public static final DoubleOption MOB_SPAWNER_MINIMUM_SPAWN_DURATION =
            new DoubleOption("easierspeedrunning.options.mob_spawner_minimum_spawn_duration",
                    5.0D, 40.0D, 5.0F, gameOptions ->
                    (double)EasierSpeedrunning.options().mobSpawnerMinimumSpawnDuration, (gameOptions, newValue) ->
                    EasierSpeedrunning.options().mobSpawnerMinimumSpawnDuration = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("easierspeedrunning.options.mob_spawner_minimum_spawn_duration") + ": " + (int)option.get(gameOptions) + "s"));
    public static final DoubleOption MOB_SPAWNER_MAXIMUM_SPAWN_DURATION =
            new DoubleOption("easierspeedrunning.options.mob_spawner_maximum_spawn_duration",
                    20.0D, 80.0D, 5.0F, gameOptions ->
                    (double)EasierSpeedrunning.options().mobSpawnerMaximumSpawnDuration, (gameOptions, newValue) ->
                    EasierSpeedrunning.options().mobSpawnerMaximumSpawnDuration = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("easierspeedrunning.options.mob_spawner_maximum_spawn_duration") + ": " + (int)option.get(gameOptions) + "s"));
}