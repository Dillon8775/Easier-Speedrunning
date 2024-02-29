package net.dillon8775.easierspeedrunning.option;

import net.dillon8775.easierspeedrunning.EasierSpeedrunning;
import net.dillon8775.easierspeedrunning.EasierSpeedrunningClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class ModListOptions {
    public static final CyclingOption<ModOptions.StructureSpawnRates> STRUCTURE_SPAWN_RATES =
            CyclingOption.create("easierspeedrunning.options.structure_spawn_rates",
                    ModOptions.StructureSpawnRates.values(), (structureSpawnRates) -> new TranslatableText(structureSpawnRates.getTranslationKey()), (gameOptions) ->
                            EasierSpeedrunning.options().structureSpawnRates, (gameOptions, option, newValue) ->
                            EasierSpeedrunning.options().structureSpawnRates = newValue);
    public static final CyclingOption<Boolean> FASTER_BLOCK_BREAKING =
            CyclingOption.create("easierspeedrunning.options.faster_block_breaking", options ->
                    EasierSpeedrunning.options().fasterBlockBreaking, (gameOptions, option, newValue) ->
                    EasierSpeedrunning.options().fasterBlockBreaking = newValue);
    public static final CyclingOption<Boolean> MODIFIED_BIOMES =
            CyclingOption.create("easierspeedrunning.options.modified_biomes", options ->
                    EasierSpeedrunning.options().modifiedBiomes, (gameOptions, option, newValue) ->
                    EasierSpeedrunning.options().modifiedBiomes = newValue);
    public static final CyclingOption<Boolean> BETTER_FOODS =
            CyclingOption.create("easierspeedrunning.options.better_foods", options ->
                    EasierSpeedrunning.options().betterFoods, (gameOptions, option, newValue) ->
                    EasierSpeedrunning.options().betterFoods = newValue);
    public static final CyclingOption<Boolean> FIREPROOF_ITEMS =
            CyclingOption.create("easierspeedrunning.options.fireproof_items", options ->
                    EasierSpeedrunning.options().fireproofItems, (gameOptions, option, newValue) ->
                    EasierSpeedrunning.options().fireproofItems = newValue);
    public static final CyclingOption<Boolean> ICARUS_MODE =
            CyclingOption.create("easierspeedrunning.options.icarus_mode", options ->
                    EasierSpeedrunning.options().iCarusMode, (gameOptions, option, newValue) ->
                    EasierSpeedrunning.options().iCarusMode = newValue);
    public static final CyclingOption<Boolean> INFINI_PEARL_MODE =
            CyclingOption.create("easierspeedrunning.options.infini_pearl_mode", options ->
                    EasierSpeedrunning.options().infiniPearlMode, (gameOptions, option, newValue) ->
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
    public static final CyclingOption<Boolean> DOOM_MODE =
            CyclingOption.create("easierspeedrunning.options.doom_mode", options ->
                    EasierSpeedrunning.options().doomMode, (gameOptions, option, newValue) ->
                    EasierSpeedrunning.options().doomMode = newValue);
    public static final CyclingOption<Boolean> NETHER_WATER =
            CyclingOption.create("easierspeedrunning.options.nether_water", options ->
                    EasierSpeedrunning.options().netherWater, (gameOptions, option, newValue) ->
                    EasierSpeedrunning.options().netherWater = newValue);
    public static final CyclingOption<Boolean> LAVA_BOATS =
            CyclingOption.create("easierspeedrunning.options.lava_boats", options ->
                    EasierSpeedrunning.options().lavaBoats, (gameOptions, option, newValue) ->
                    EasierSpeedrunning.options().lavaBoats = newValue);
    public static final CyclingOption<Boolean> FALL_DAMAGE =
            CyclingOption.create("easierspeedrunning.options.fall_damage", options ->
                    EasierSpeedrunning.options().fallDamage, (gameOptions, option, newValue) ->
                    EasierSpeedrunning.options().fallDamage = newValue);
    public static final CyclingOption<Boolean> KILL_GHAST_ON_FIREBALL =
            CyclingOption.create("easierspeedrunning.options.kill_ghast_on_fireball", options ->
                    EasierSpeedrunning.options().killGhastOnFireball, (gameOptions, option, newValue) ->
                    EasierSpeedrunning.options().killGhastOnFireball = newValue);
    public static final CyclingOption<Boolean> BETTER_ANVIL =
            CyclingOption.create("easierspeedrunning.options.better_anvil", options ->
                    EasierSpeedrunning.options().betterAnvil, (gameOptions, option, newValue) ->
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
                    (double)EasierSpeedrunning.options().strongholdSpread, (gameOptions, newValue) ->
                    EasierSpeedrunning.options().strongholdSpread = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("easierspeedrunning.options.stronghold_spread") + ": " + (int)option.get(gameOptions)));
    public static final DoubleOption STRONGHOLD_COUNT =
            new DoubleOption("easierspeedrunning.options.stronghold_count",
                    4.0D, 156.0D, 4.0F, gameOptions ->
                    (double)EasierSpeedrunning.options().strongholdCount, (gameOptions, newValue) ->
                    EasierSpeedrunning.options().strongholdCount = newValue.intValue(), (gameOptions, option) ->
                    new LiteralText(I18n.translate("easierspeedrunning.options.stronghold_count") + ": " + (int)option.get(gameOptions)));
    public static final CyclingOption<Boolean> HIGHER_BREATH_TIME =
            CyclingOption.create("easierspeedrunning.options.higher_breath_time", options ->
                    EasierSpeedrunning.options().higherBreathTime, (gameOptions, option, newValue) ->
                    EasierSpeedrunning.options().higherBreathTime = newValue);
    public static final CyclingOption<Boolean> FOG =
            CyclingOption.create("easierspeedrunning.options.fog", options ->
                    EasierSpeedrunningClient.clientOptions().fog, (gameOptions, option, newValue) -> {
                EasierSpeedrunningClient.clientOptions().fog = newValue;
                MinecraftClient.getInstance().worldRenderer.reload();
            });
    public static final CyclingOption<ClientModOptions.Panorama> PANORAMA =
            CyclingOption.create("easierspeedrunning.options.panorama",
                    ClientModOptions.Panorama.values(), (panorama) -> new TranslatableText(panorama.getTranslationKey()), (gameOptions) ->
                            EasierSpeedrunningClient.clientOptions().panorama, (gameOptions, option, newValue) ->
                            EasierSpeedrunningClient.clientOptions().panorama = newValue);
    public static final CyclingOption<ClientModOptions.ItemMessages> ITEM_MESSAGES =
            CyclingOption.create("easierspeedrunning.options.item_messages",
                    ClientModOptions.ItemMessages.values(), (itemMessages) -> new TranslatableText(itemMessages.getTranslationKey()), (gameOptions) ->
                            EasierSpeedrunningClient.clientOptions().itemMessages, (gameOptions, option, newValue) ->
                            EasierSpeedrunningClient.clientOptions().itemMessages = newValue);
    public static final CyclingOption<Boolean> FAST_WORLD_CREATION =
            CyclingOption.create("easierspeedrunning.options.fast_world_creation", options ->
                    EasierSpeedrunningClient.clientOptions().fastWorldCreation, (gameOptions, option, newValue) ->
                    EasierSpeedrunningClient.clientOptions().fastWorldCreation = newValue);
    public static final CyclingOption<ClientModOptions.GameMode> GAMEMODE =
            CyclingOption.create("easierspeedrunning.options.gamemode",
                    ClientModOptions.GameMode.values(), (gameMode) -> new TranslatableText(gameMode.getTranslationKey()), (gameOptions) ->
                            EasierSpeedrunningClient.clientOptions().gameMode, (gameOptions, option, newValue) ->
                            EasierSpeedrunningClient.clientOptions().gameMode = newValue);
    public static final CyclingOption<ClientModOptions.Difficulty> DIFFICULTY =
            CyclingOption.create("easierspeedrunning.options.difficulty",
                    ClientModOptions.Difficulty.values(), (difficulty) -> new TranslatableText(difficulty.getTranslationKey()), (gameOptions) ->
                            EasierSpeedrunningClient.clientOptions().difficulty, (gameOptions, option, newValue) ->
                            EasierSpeedrunningClient.clientOptions().difficulty = newValue);
    public static final CyclingOption<Boolean> ALLOW_CHEATS =
            CyclingOption.create("easierspeedrunning.options.allow_cheats", options ->
                    EasierSpeedrunningClient.clientOptions().allowCheats, (gameOptions, option, newValue) ->
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