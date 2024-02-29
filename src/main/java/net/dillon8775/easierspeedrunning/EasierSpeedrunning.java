package net.dillon8775.easierspeedrunning;

import net.dillon8775.easierspeedrunning.client.screen.RestartRequiredScreen;
import net.dillon8775.easierspeedrunning.item.CrimsonBoatItem;
import net.dillon8775.easierspeedrunning.item.WarpedBoatItem;
import net.dillon8775.easierspeedrunning.option.ClientModOptions;
import net.dillon8775.easierspeedrunning.option.ModOptions;
import net.dillon8775.easierspeedrunning.sound.ModSoundEvents;
import net.dillon8775.easierspeedrunning.tag.ModBlockTags;
import net.dillon8775.easierspeedrunning.tag.ModFluidTags;
import net.dillon8775.easierspeedrunning.tag.ModItemTags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.SpawnSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunningClient.clientOptions;
import static net.minecraft.world.gen.feature.DefaultBiomeFeatures.addBatsAndMonsters;

/**
 * The main class file for the {@code Easier Speedrunning} mod.
 */
public class EasierSpeedrunning implements ModInitializer {
	public static final String MOD_ID = "easierspeedrunning";
	public static final String MOD_VERSION = "v1.0";
	public static final String VERSION = "Version: " + MOD_VERSION;
	public static final String EASIER_SPEEDRUNNING_STRING = "Easier Speedrunning";
	public static final String WIKI_LINK = "https://sites.google.com/view/dillon8775/easier-speedrunning-mod";
	public static boolean DOOM_MODE = EasierSpeedrunning.options().doomMode;
	public static final String OPTIONS_ERROR_MESSAGE = "Found error with speedrunner mod settings, launching in safe mode.";
	public static final String OPTIONS_WARNING_MESSAGE = "Found an unusual value in the speedrunner mod settings.";
	public static boolean safeBoot;
	private static final Logger LOGGER = LogManager.getLogger("Easier Speedrunning");

	/**
	 * <p>Initializes all of the {@code Easier Speedrunning} features.</p>
	 * <p>This does not include any {@link org.spongepowered.asm.mixin.Mixins}</p>
	 */
	@Override
	public void onInitialize() {
		registerItems();

		ModBlockTags.init();
		ModFluidTags.init();
		ModItemTags.init();

		ModSoundEvents.init();

		ModOptions.loadConfig();

		if (DOOM_MODE) {
			info("You dare attempt Doom Mode? Good luck...");
		}

		info("The Easier Speedrunning Mod (" + MOD_VERSION + ")" + " has successfully initialized!");
	}

	/**
	 * Sends an {@code info} message in console.
	 */
	public static void info(String info) {
		LOGGER.info(info);
	}

	/**
	 * Sends a {@code warning} message in console.
	 */
	public static void warn(String warning) {
		LOGGER.warn(warning);
	}

	/**
	 * Sends an {@code error} message in console.
	 */
	public static void error(String error) {
		LOGGER.error(error);
	}

	/**
	 * Sends a {@code debug} message in console.
	 */
	public static void debug(String debug) {
		LOGGER.debug(debug);
	}

	/**
	 * The {@code Speedrunner Mod} options method.
	 */
	public static ModOptions options() {
		return ModOptions.OPTIONS;
	}

	/**
	 * Resets all of the {@code Easier Speedrunning options} back to the default settings.
	 * <p>See {@link RestartRequiredScreen} for more.</p>
	 */
	@Environment(EnvType.CLIENT)
	public static void resetOptions() {
		options().structureSpawnRates = ModOptions.StructureSpawnRates.COMMON;
		options().fasterBlockBreaking = true;
		options().modifiedBiomes = true;
		options().betterFoods = true;
		options().fireproofItems = true;
		options().dragonPerchTime = 30;
		options().netherPortalCooldown = 2;
		options().iCarusMode = false;
		options().infiniPearlMode = false;
		options().doomMode = false;
		options().netherWater = true;
		options().lavaBoats = true;
		options().fallDamage = true;
		options().killGhastOnFireball = false;
		options().betterAnvil = true;
		options().anvilCostLimit = 10;
		options().higherBreathTime = true;
		options().netherPortalCooldown = 3;
		options().anvilCostLimit = 2;
		options().strongholdPortalRoomCount = 3;
		options().strongholdLibraryCount = 2;
		options().strongholdDistance = 4;
		options().strongholdSpread = 3;
		options().strongholdCount = 128;
		options().mobSpawnerMinimumSpawnDuration = 20;
		options().mobSpawnerMaximumSpawnDuration = 40;
		clientOptions().fog = true;
		clientOptions().panorama = ClientModOptions.Panorama.CLASSIC;
		clientOptions().itemMessages = ClientModOptions.ItemMessages.ACTIONBAR;
		clientOptions().fastWorldCreation = true;
		clientOptions().gameMode = ClientModOptions.GameMode.SURVIVAL;
		clientOptions().difficulty = ClientModOptions.Difficulty.EASY;
		clientOptions().allowCheats = false;
	}

	public static BoatEntity.Type CRIMSON_BOAT_TYPE = BoatEntity.Type.valueOf("CRIMSON");
	public static BoatEntity.Type WARPED_BOAT_TYPE = BoatEntity.Type.valueOf("WARPED");
	public static final Item CRIMSON_BOAT_ITEM = new CrimsonBoatItem(
			CRIMSON_BOAT_TYPE, new Item.Settings());
	public static final Item WARPED_BOAT_ITEM = new WarpedBoatItem(
			WARPED_BOAT_TYPE, new Item.Settings());

	/**
	 * <p>Determines {@code "fireproof"} boats.</p>
	 * <p>See {@link net.dillon8775.easierspeedrunning.mixin.main.boat.BoatEntityMixin} for more.</p>
	 */
	public static boolean isFireproofBoat(BoatEntity.Type boatType) {
		return EasierSpeedrunning.options().lavaBoats && boatType.getBaseBlock().getDefaultState().isIn(ModBlockTags.FIREPROOF_BOAT_BASE_BLOCKS);
	}

	/**
	 * Registers the crimson and warped boats.
	 * <p>See {@link CrimsonBoatItem} and {@link WarpedBoatItem} for more.</p>
	 * <p>{@link net.dillon8775.easierspeedrunning.mixin.main.boat.BoatEntityMixin}</p>
	 * <p>{@link net.dillon8775.easierspeedrunning.mixin.main.boat.BoatEntityTypeMixin}</p>
	 * <p>{@link net.dillon8775.easierspeedrunning.mixin.main.entity.basic.EntityMixin}</p>
	 * <p>{@link net.dillon8775.easierspeedrunning.mixin.client.fix.BoatEntityRendererMixin}</p>
	 * <p>{@link net.dillon8775.easierspeedrunning.mixin.client.fix.RenderLayersMixin}</p>
	 */
	public static void registerItems() {
		Registry.register(Registry.ITEM, new Identifier(EasierSpeedrunning.MOD_ID, "crimson_boat"), CRIMSON_BOAT_ITEM);
		info("Registered crimson boat.");

		Registry.register(Registry.ITEM, new Identifier(EasierSpeedrunning.MOD_ID, "warped_boat"), WARPED_BOAT_ITEM);
		info("Registered warped boat.");
	}

	/**
	 * See {@link net.dillon8775.easierspeedrunning.mixin.main.world.StructureSetsMixin} for more.
	 * <p>Because of the way Minecraft changed structure generation in {@code 1.18.2}, we have to locally assign our values to these variables, because using my custom-created method {@code setStructureConfig} no longer works.</p>
	 */
	public static int getVillageSpacing() {
		if (options().structureSpawnRates.everywhere()) {
			return 4;
		} else if (options().structureSpawnRates.veryCommon()) {
			return 10;
		} else if (options().structureSpawnRates.common()) {
			return 16;
		} else if (options().structureSpawnRates.normal()) {
			return 20;
		} else if (options().structureSpawnRates.ddefault()) {
			return 32;
		} else if (options().structureSpawnRates.rare()) {
			return 42;
		} else if (options().structureSpawnRates.veryRare()) {
			return 52;
		} else {
			return 0;
		}
	}

	public static int getVillageSeparation() {
		if (options().structureSpawnRates.everywhere()) {
			return 2;
		} else if (options().structureSpawnRates.common()) {
			return 9;
		} else if (options().structureSpawnRates.commonNormalOrDefault()) {
			return 8;
		} else if (options().structureSpawnRates.rare()) {
			return 10;
		} else if (options().structureSpawnRates.veryRare()) {
			return 16;
		} else {
			return 0;
		}
	}

	public static int getDesertPyramidSpacing() {
		if (options().structureSpawnRates.everywhere()) {
			return 3;
		} else if (options().structureSpawnRates.veryCommon()) {
			return 8;
		} else if (options().structureSpawnRates.common()) {
			return 10;
		} else if (options().structureSpawnRates.normal()) {
			return 20;
		} else if (options().structureSpawnRates.ddefault()) {
			return 32;
		} else if (options().structureSpawnRates.rare()) {
			return 42;
		} else if (options().structureSpawnRates.veryRare()) {
			return 52;
		} else {
			return 0;
		}
	}

	public static int getDesertPyramidSeparation() {
		if (options().structureSpawnRates.everywhere()) {
			return 2;
		} else if (options().structureSpawnRates.veryCommon()) {
			return 7;
		} else if (options().structureSpawnRates.commonNormalOrDefault()) {
			return 8;
		} else if (options().structureSpawnRates.rare()) {
			return 10;
		} else if (options().structureSpawnRates.veryRare()) {
			return 16;
		} else {
			return 0;
		}
	}

	public static int getJunglePyramidSpacing() {
		if (options().structureSpawnRates.everywhere()) {
			return 3;
		} else if (options().structureSpawnRates.veryCommon()) {
			return 8;
		} else if (options().structureSpawnRates.common()) {
			return 10;
		} else if (options().structureSpawnRates.normal()) {
			return 20;
		} else if (options().structureSpawnRates.ddefault()) {
			return 32;
		} else if (options().structureSpawnRates.rare()) {
			return 40;
		} else if (options().structureSpawnRates.veryRare()) {
			return 50;
		} else {
			return 0;
		}
	}

	public static int getJunglePyramidSeparation() {
		if (options().structureSpawnRates.everywhere()) {
			return 2;
		} else if (options().structureSpawnRates.veryCommon()) {
			return 7;
		} else if (options().structureSpawnRates.commonNormalOrDefault()) {
			return 8;
		} else if (options().structureSpawnRates.rare()) {
			return 10;
		} else if (options().structureSpawnRates.veryRare()) {
			return 12;
		} else {
			return 0;
		}
	}

	public static int getPillagerOutpostSpacing() {
		if (options().structureSpawnRates.everywhere()) {
			return 3;
		} else if (options().structureSpawnRates.veryCommon()) {
			return 8;
		} else if (options().structureSpawnRates.common()) {
			return 10;
		} else if (options().structureSpawnRates.normal()) {
			return 20;
		} else if (options().structureSpawnRates.ddefault()) {
			return 32;
		} else if (options().structureSpawnRates.rare()) {
			return 40;
		} else if (options().structureSpawnRates.veryRare()) {
			return 50;
		} else {
			return 0;
		}
	}

	public static int getPillagerOutpostSeparation() {
		if (options().structureSpawnRates.everywhere()) {
			return 2;
		} else if (options().structureSpawnRates.veryCommon()) {
			return 7;
		} else if (options().structureSpawnRates.commonNormalOrDefault()) {
			return 8;
		} else if (options().structureSpawnRates.rare()) {
			return 10;
		} else if (options().structureSpawnRates.veryRare()) {
			return 12;
		} else {
			return 0;
		}
	}

	public static int getEndCitySpacing() {
		if (options().structureSpawnRates.everywhere()) {
			return 4;
		} else if (options().structureSpawnRates.veryCommon()) {
			return 5;
		} else if (options().structureSpawnRates.common()) {
			return 7;
		} else if (options().structureSpawnRates.normal()) {
			return 15;
		} else if (options().structureSpawnRates.ddefault()) {
			return 20;
		} else if (options().structureSpawnRates.rare()) {
			return 25;
		} else {
			return 0;
		}
	}

	public static int getEndCitySeparation() {
		if (options().structureSpawnRates.everywhere()) {
			return 2;
		} else if (options().structureSpawnRates.veryCommon()) {
			return 4;
		} else if (options().structureSpawnRates.common()) {
			return 6;
		} else if (options().structureSpawnRates.normal()) {
			return 10;
		} else if (options().structureSpawnRates.ddefault()) {
			return 11;
		} else if (options().structureSpawnRates.rare()) {
			return 16;
		} else if (options().structureSpawnRates.veryRare()) {
			return 18;
		} else {
			return 0;
		}
	}

	public static int getWoodlandMansionSpacing() {
		if (options().structureSpawnRates.everywhere()) {
			return 6;
		} else if (options().structureSpawnRates.veryCommon()) {
			return 16;
		} else if (options().structureSpawnRates.common()) {
			return 25;
		} else if (options().structureSpawnRates.normal()) {
			return 40;
		} else if (options().structureSpawnRates.ddefault()) {
			return 80;
		} else if (options().structureSpawnRates.rare()) {
			return 100;
		} else if (options().structureSpawnRates.veryRare()) {
			return 120;
		} else {
			return 0;
		}
	}
	
	public static int getWoodlandMansionSeparation() {
		if (options().structureSpawnRates.everywhere()) {
			return 4;
		} else if (options().structureSpawnRates.veryCommon()) {
			return 8;
		} else if (options().structureSpawnRates.commonNormalOrDefault() || options().structureSpawnRates.rare()) {
			return 20;
		} else if (options().structureSpawnRates.veryRare()) {
			return 25;
		} else {
			return 0;
		}
	}
	
	public static int getRuinedPortalSpacing() {
		if (options().structureSpawnRates.everywhere()) {
			return 4;
		} else if (options().structureSpawnRates.veryCommon()) {
			return 7;
		} else if (options().structureSpawnRates.common()) {
			return 9;
		} else if (options().structureSpawnRates.normal()) {
			return 16;
		} else if (options().structureSpawnRates.ddefault()) {
			return 40;
		} else if (options().structureSpawnRates.rare()) {
			return 50;
		} else if (options().structureSpawnRates.veryRare()) {
			return 60;
		} else {
			return 0;
		}
	}
	
	public static int getRuinedPortalSeparation() {
		if (options().structureSpawnRates.everywhere()) {
			return 2;
		} else if (options().structureSpawnRates.veryCommon()) {
			return 6;
		} else if (options().structureSpawnRates.common()) {
			return 8;
		} else if (options().structureSpawnRates.normal()) {
			return 9;
		} else if (options().structureSpawnRates.ddefault()) {
			return 15;
		} else if (options().structureSpawnRates.rare()) {
			return 16;
		} else if (options().structureSpawnRates.veryRare()) {
			return 20;
		} else {
			return 0;
		}
	}

	public static int getShipwreckSpacing() {
		if (options().structureSpawnRates.everywhere()) {
			return 3;
		} else if (options().structureSpawnRates.veryCommon()) {
			return 8;
		} else if (options().structureSpawnRates.common()) {
			return 10;
		} else if (options().structureSpawnRates.normal()) {
			return 20;
		} else if (options().structureSpawnRates.ddefault()) {
			return 24;
		} else if (options().structureSpawnRates.rare()) {
			return 30;
		} else if (options().structureSpawnRates.veryRare()) {
			return 40;
		} else {
			return 0;
		}
	}

	public static int getShipwreckSeparation() {
		if (options().structureSpawnRates.everywhere()) {
			return 2;
		} else if (options().structureSpawnRates.veryCommon()) {
			return 7;
		} else if (options().structureSpawnRates.common() ||
				   options().structureSpawnRates.normal()) {
			return 8;
		} else if (options().structureSpawnRates.ddefault()) {
			return 4;
		} else if (options().structureSpawnRates.rare()) {
			return 9;
		} else if (options().structureSpawnRates.veryRare()) {
			return 10;
		} else {
			return 0;
		}
	}

	public static int getNetherComplexesSpacing() {
		if (options().structureSpawnRates.everywhere()) {
			return 4;
		} else if (options().structureSpawnRates.veryCommon()) {
			return 6;
		} else if (options().structureSpawnRates.common()) {
			return 8;
		} else if (options().structureSpawnRates.normal()) {
			return 15;
		} else if (options().structureSpawnRates.ddefault()) {
			return 27;
		} else if (options().structureSpawnRates.rare()) {
			return 34;
		} else if (options().structureSpawnRates.veryRare()) {
			return 40;
		} else {
			return 0;
		}
	}

	public static int getNetherComplexesSeparation() {
		if (options().structureSpawnRates.everywhere()) {
			return 2;
		} else if (options().structureSpawnRates.veryCommon()) {
			return 5;
		} else if (options().structureSpawnRates.common()) {
			return 7;
		} else if (options().structureSpawnRates.normal() ||
				   options().structureSpawnRates.rare()) {
			return 8;
		} else if (options().structureSpawnRates.ddefault()) {
			return 4;
		} else if (options().structureSpawnRates.veryRare()) {
			return 10;
		} else {
			return 0;
		}
	}

	/**
	 * See {@link net.minecraft.world.gen.feature.DefaultBiomeFeatures}.
	 */
	public static void modifyMonsterSpawns(SpawnSettings.Builder builder, int zombieWeight, int zombieVillagerWeight, int skeletonWeight) {
		if (options().doomMode) {
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, zombieWeight, 1, 4));
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, zombieVillagerWeight, 1, 1));
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, skeletonWeight, 1, 4));
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 1, 4));
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.VINDICATOR, 100, 1, 4));
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 75, 1, 5));
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITCH, 50, 1, 4));
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 50, 1, 4));
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 25, 1, 4));
		} else {
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 100, 4, 4));
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, zombieWeight, 4, 4));
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, zombieVillagerWeight, 1, 1));
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, skeletonWeight, 1, 4));
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 2, 4));
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 100, 1, 4));
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 50, 4, 4));
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITCH, 5, 1, 1));
		}
	}

	/**
	 * See {@link net.minecraft.world.gen.feature.DefaultBiomeFeatures}.
	 */
	public static void makeAnimalsMoreCommon(SpawnSettings.Builder builder) {
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 16, 4, 8));
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 12, 4, 8));
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 8, 4, 8));
		builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 8, 4, 8));
	}

	/**
	 * See {@link net.minecraft.world.gen.feature.DefaultBiomeFeatures}.
	 */
	public static void makeDolphinsMoreCommon(SpawnSettings.Builder builder, int squidWeight, int squidMinGroupSize) {
		builder.spawn(SpawnGroup.WATER_AMBIENT, new SpawnSettings.SpawnEntry(EntityType.TROPICAL_FISH, 10, 4, 8));
		builder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.DOLPHIN, 15, 1, 2));
		builder.spawn(SpawnGroup.WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.SQUID, squidWeight, squidMinGroupSize, 4));
		addBatsAndMonsters(builder);
	}

	/**
	 * See {@link net.minecraft.world.gen.feature.DefaultBiomeFeatures}.
	 */
	public static void modifyEndMonsterSpawning(SpawnSettings.Builder builder) {
		if (options().doomMode) {
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 85, 1, 4));
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 75, 1, 4));
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.VINDICATOR, 75, 1, 2));
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.RAVAGER, 50, 1, 1));
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.EVOKER, 50, 1, 1));
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, 25, 1, 1));
		} else {
			builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 4, 4));
		}
	}

	public static float getBoatInLavaVelocityMultiplier() {
		return 0.95F;
	}

	public static float getBedBlockExplosionPower() {
		return DOOM_MODE ? 15.0F : 5.0F;
	}

	public static int getFireFromLavaTime() {
		return DOOM_MODE ? 15 : 7;
	}

	public static float getLavaDamageAmount() {
		return DOOM_MODE ? 4.0F : 2.0F;
	}

	public static int getPlayerBreathTime() {
		return options().higherBreathTime ? 8 : 4;
	}

	public static int getBlazeFireballCooldown() {
		return DOOM_MODE ? 60 : 180;
	}

	public static int getDolphinRange() {
		return 200;
	}

	public static int getEnderDragonFireballInstantDamageAmplifier() {
		return DOOM_MODE ? 1 : 0;
	}

	public static double getEnderDragonMaxHealth() {
		return DOOM_MODE ? 500.0D : 100.0D;
	}

	public static float getEnderDragonEndCrystalHealAmount() {
		return DOOM_MODE ? 1.7F : 0.1F;
	}

	public static float getEnderDragonDamageMultiplier() {
		return DOOM_MODE ? 12.0F : 3.0F;
	}

	public static float getEnderDragonEndCrystalDestroyedHealthAmount() {
		return DOOM_MODE ? 3.0F : 20.0F;
	}

	public static float getEnderDragonStayPerchedTime() {
		return DOOM_MODE ? 0.18F : 0.60F;
	}

	public static float getEnderPearlDamageMultiplier() {
		return DOOM_MODE ? 5.0F : 2.0F;
	}

	public static int getGhastFireballCooldown() {
		return DOOM_MODE ? -5 : -40;
	}

	public static int getSlimeJumpTime() {
		return DOOM_MODE ? 20 : 100;
	}

	public static float getMagmaCubeDamageMultiplier() {
		return DOOM_MODE ? 2.2F : 1.5F;
	}

	public static double getZombifiedPiglinRunawayDistance() {
		return 2.0D;
	}

	public static int getSilverfishCallForHelpDelay() {
		return DOOM_MODE ? 20 : 100;
	}

	public static int getFireballFireTime() {
		return DOOM_MODE ? 6 : 3;
	}

	public static float getFireballDamageMultiplier() {
		return DOOM_MODE ? 5.0F : 1.0F;
	}

	public static float getVexDecayDamageMultiplier() {
		return DOOM_MODE ? 100.0F : 1.0F;
	}

	public static double getWitherMaxHealth() {
		return DOOM_MODE ? 150.0D : 100.0D;
	}

	public static int getWitherSkeletonWitherEffectDuration() {
		return DOOM_MODE ? 200 : 60;
	}

	public static int getStrongholdMinY() {
		return DOOM_MODE ? -48 : 27;
	}

	public static int getStrongholdMaxY() {
		int seaLevel = 63;
		return DOOM_MODE ? 0 : seaLevel;
	}

	public static float getEnderEyeChance() {
		return DOOM_MODE ? 0.99F : 0.6F;
	}

	public static int getPlainsTreeCount() {
		return 1;
	}

	public static final FoodComponent APPLE = new FoodComponent.Builder().hunger(4).saturationModifier(0.8F).build();
	public static final FoodComponent BAKED_POTATO = new FoodComponent.Builder().hunger(6).saturationModifier(0.9F).build();
	public static final FoodComponent BEEF = new FoodComponent.Builder().hunger(4).saturationModifier(0.7F).meat().build();
	public static final FoodComponent BEETROOT = new FoodComponent.Builder().hunger(2).saturationModifier(1.4F).build();
	public static final FoodComponent BREAD = new FoodComponent.Builder().hunger(5).saturationModifier(1.1F).build();
	public static final FoodComponent CARROT = new FoodComponent.Builder().hunger(3).saturationModifier(1.2F).build();
	public static final FoodComponent CHICKEN = new FoodComponent.Builder().hunger(2).saturationModifier(1.2F).build();
	public static final FoodComponent CHORUS_FRUIT = new FoodComponent.Builder().hunger(4).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).alwaysEdible().build();
	public static final FoodComponent COD = new FoodComponent.Builder().hunger(2).saturationModifier(1.2F).build();
	public static final FoodComponent COOKED_BEEF = new FoodComponent.Builder().hunger(8).saturationModifier(0.9F).meat().build();
	public static final FoodComponent COOKED_CHICKEN = new FoodComponent.Builder().hunger(6).saturationModifier(0.9F).meat().build();
	public static final FoodComponent COOKED_COD = new FoodComponent.Builder().hunger(5).saturationModifier(1.1F).build();
	public static final FoodComponent COOKED_MUTTON = new FoodComponent.Builder().hunger(6).saturationModifier(0.9F).meat().build();
	public static final FoodComponent COOKED_PORKCHOP = new FoodComponent.Builder().hunger(8).saturationModifier(0.9F).meat().build();
	public static final FoodComponent COOKED_RABBIT = new FoodComponent.Builder().hunger(5).saturationModifier(1.1F).meat().build();
	public static final FoodComponent COOKED_SALMON = new FoodComponent.Builder().hunger(6).saturationModifier(0.9F).build();
	public static final FoodComponent COOKIE = new FoodComponent.Builder().hunger(2).saturationModifier(1.3F).build();
	public static final FoodComponent DRIED_KELP = new FoodComponent.Builder().hunger(1).saturationModifier(0.6F).snack().build();
	public static final FoodComponent ENCHANTED_GOLDEN_APPLE = new FoodComponent.Builder().hunger(8).saturationModifier(1.4F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600, 1), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 3), 1.0F).alwaysEdible().build();
	public static final FoodComponent GOLDEN_APPLE = new FoodComponent.Builder().hunger(8).saturationModifier(1.3F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 0), 1.0F).alwaysEdible().build();
	public static final FoodComponent GOLDEN_CARROT = new FoodComponent.Builder().hunger(6).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 1.0F).build();
	public static final FoodComponent HONEY_BOTTLE = new FoodComponent.Builder().hunger(6).saturationModifier(0.5F).build();
	public static final FoodComponent MELON_SLICE = new FoodComponent.Builder().hunger(4).saturationModifier(1.2F).build();
	public static final FoodComponent MUTTON = new FoodComponent.Builder().hunger(2).saturationModifier(0.8F).meat().build();
	public static final FoodComponent POISONOUS_POTATO = new FoodComponent.Builder().hunger(2).saturationModifier(0.7F).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0), 0.6F).build();
	public static final FoodComponent PORKCHOP = new FoodComponent.Builder().hunger(4).saturationModifier(0.4F).meat().build();
	public static final FoodComponent POTATO = new FoodComponent.Builder().hunger(1).saturationModifier(1.0F).build();
	public static final FoodComponent PUFFERFISH = new FoodComponent.Builder().hunger(1).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 1200, 3), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300, 2), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 300, 0), 1.0F).build();
	public static final FoodComponent PUMPKIN_PIE = new FoodComponent.Builder().hunger(8).saturationModifier(0.9F).build();
	public static final FoodComponent RABBIT = new FoodComponent.Builder().hunger(3).saturationModifier(0.9F).meat().build();
	public static final FoodComponent ROTTEN_FLESH = new FoodComponent.Builder().hunger(4).saturationModifier(0.1F).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200, 0), 0.8F).meat().build();
	public static final FoodComponent SALMON = new FoodComponent.Builder().hunger(2).saturationModifier(1.4F).build();
	public static final FoodComponent SPIDER_EYE = new FoodComponent.Builder().hunger(2).saturationModifier(1.1F).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0), 1.0F).build();
	public static final FoodComponent SWEET_BERRIES = new FoodComponent.Builder().hunger(4).saturationModifier(1.2F).build();
	public static final FoodComponent TROPICAL_FISH = new FoodComponent.Builder().hunger(2).saturationModifier(1.2F).build();
}