package net.dillon8775.easierspeedrunning;

import com.google.common.collect.ImmutableMap;
import net.dillon8775.easierspeedrunning.client.screen.RestartRequiredScreen;
import net.dillon8775.easierspeedrunning.item.CrimsonBoatItem;
import net.dillon8775.easierspeedrunning.item.WarpedBoatItem;
import net.dillon8775.easierspeedrunning.mixin.main.structure.StructuresConfigAccessor;
import net.dillon8775.easierspeedrunning.option.ClientModOptions;
import net.dillon8775.easierspeedrunning.option.ModOptions;
import net.dillon8775.easierspeedrunning.tag.ModBlockTags;
import net.dillon8775.easierspeedrunning.tag.ModFluidTags;
import net.dillon8775.easierspeedrunning.tag.ModItemTags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static net.dillon8775.easierspeedrunning.EasierSpeedrunningClient.clientOptions;
import static net.dillon8775.easierspeedrunning.option.ModOptions.isSafe;

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
    private static final Logger LOGGER = LogManager.getLogger("Easier Speedrunning");
	public static final String OPTIONS_ERROR_MESSAGE = "Found error with speedrunner mod settings, launching in safe mode.";
	public static final String OPTIONS_WARNING_MESSAGE = "Found an unusual value in the speedrunner mod settings.";
	public static boolean safeBoot;

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

		ModOptions.loadConfig();

		initializeModStructureConfigs();

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
	public static final SoundEvent ENTITY_BOAT_PADDLE_LAVA =
			Registry.register(Registry.SOUND_EVENT, "entity.boat.paddle_lava",
					new SoundEvent(new Identifier(MOD_ID, "entity.boat.paddle_lava")));

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
	 * Structures don't generate commonly enough for speedrunning. With this, we make them more common, based on the user's desire.
	 * <p>See {@link ModOptions.StructureSpawnRates} for more.</p>
	 * <p>{@link net.minecraft.world.gen.chunk.StructuresConfig}</p>
	 */
	public static void initializeModStructureConfigs() {
		if (options().structureSpawnRates == ModOptions.StructureSpawnRates.EVERYWHERE) {
			setVillageStructureConfig(4, 2);
			setDesertPyramidStructureConfig(3, 2);
			setJunglePyramidStructureConfig(3, 2);
			setPillagerOutpostStructureConfig(3, 2);
			setEndCityStructureConfig(4, 2);
			setWoodlandMansionStructureConfig(6, 4);
			setRuinedPortalStructureConfig(4, 2);
			setShipwreckStructureConfig(3, 2);
			setBastionRemnantStructureConfig(4, 2);
			setNetherFortressStructureConfig(4, 2);
		} else if (options().structureSpawnRates == ModOptions.StructureSpawnRates.VERY_COMMON) {
			setVillageStructureConfig(10, 8);
			setDesertPyramidStructureConfig(8, 7);
			setJunglePyramidStructureConfig(8, 7);
			setPillagerOutpostStructureConfig(8, 7);
			setEndCityStructureConfig(5, 4);
			setWoodlandMansionStructureConfig(16, 8);
			setRuinedPortalStructureConfig(7, 6);
			setShipwreckStructureConfig(8, 7);
			setBastionRemnantStructureConfig(7, 6);
			setNetherFortressStructureConfig(6, 5);
		} else if (options().structureSpawnRates == ModOptions.StructureSpawnRates.COMMON) {
			setVillageStructureConfig(16, 9);
			setDesertPyramidStructureConfig(10, 8);
			setJunglePyramidStructureConfig(10, 8);
			setPillagerOutpostStructureConfig(10, 8);
			setEndCityStructureConfig(7, 6);
			setWoodlandMansionStructureConfig(25, 20);
			setRuinedPortalStructureConfig(9, 8);
			setShipwreckStructureConfig(10, 8);
			setBastionRemnantStructureConfig(9, 8);
			setNetherFortressStructureConfig(8, 7);
		} else if (options().structureSpawnRates == ModOptions.StructureSpawnRates.NORMAL) {
			setVillageStructureConfig(20, 8);
			setDesertPyramidStructureConfig(20, 8);
			setJunglePyramidStructureConfig(20, 8);
			setPillagerOutpostStructureConfig(20, 8);
			setEndCityStructureConfig(15, 10);
			setWoodlandMansionStructureConfig(40, 20);
			setRuinedPortalStructureConfig(16, 9);
			setShipwreckStructureConfig(20, 8);
			setBastionRemnantStructureConfig(16, 9);
			setNetherFortressStructureConfig(15, 8);
		} else if (options().structureSpawnRates == ModOptions.StructureSpawnRates.DEFAULT) {
			setVillageStructureConfig(32, 8);
			setDesertPyramidStructureConfig(32, 8);
			setJunglePyramidStructureConfig(32, 8);
			setPillagerOutpostStructureConfig(32, 8);
			setEndCityStructureConfig(20, 11);
			setWoodlandMansionStructureConfig(80, 20);
			setRuinedPortalStructureConfig(40, 15);
			setShipwreckStructureConfig(24, 4);
			setBastionRemnantStructureConfig(27, 4);
			setNetherFortressStructureConfig(27, 4);
		} else if (options().structureSpawnRates == ModOptions.StructureSpawnRates.RARE) {
			setVillageStructureConfig(42, 10);
			setDesertPyramidStructureConfig(40, 10);
			setJunglePyramidStructureConfig(40, 10);
			setPillagerOutpostStructureConfig(40, 10);
			setEndCityStructureConfig(25, 16);
			setWoodlandMansionStructureConfig(100, 20);
			setRuinedPortalStructureConfig(50, 16);
			setShipwreckStructureConfig(30, 9);
			setBastionRemnantStructureConfig(35, 9);
			setNetherFortressStructureConfig(34, 8);
		} else if (options().structureSpawnRates == ModOptions.StructureSpawnRates.VERY_RARE) {
			setVillageStructureConfig(52, 16);
			setDesertPyramidStructureConfig(50, 16);
			setJunglePyramidStructureConfig(50, 12);
			setPillagerOutpostStructureConfig(50, 12);
			setEndCityStructureConfig(30, 18);
			setWoodlandMansionStructureConfig(120, 25);
			setRuinedPortalStructureConfig(60, 20);
			setShipwreckStructureConfig(40, 10);
			setBastionRemnantStructureConfig(40, 10);
			setNetherFortressStructureConfig(40, 10);
		} else if (options().structureSpawnRates == ModOptions.StructureSpawnRates.OFF) {
			info("Structure spawn rate is off, ignoring custom structure generation.");
		} else {
			isSafe(false);
			error("Found error with structure spawn rates, booting into safe mode.");
		}

		if (options().structureSpawnRates != ModOptions.StructureSpawnRates.OFF) {
			info("Set structure spawn rate values.");

			ServerWorldEvents.LOAD.register((server, world) -> {
				Map<StructureFeature<?>, StructureConfig> map = new HashMap<>(world.getChunkManager().getChunkGenerator().getConfig().getStructures());

				map.computeIfPresent(StructureFeature.VILLAGE, (structureFeature, structureConfig) -> {
					return new StructureConfig(villageSpacing, villageSeparation, 10387312);
				});
				map.computeIfPresent(StructureFeature.DESERT_PYRAMID, (structureFeature, structureConfig) -> {
					return new StructureConfig(desertPyramidSpacing, desertPyramidSeparation, 14357617);
				});
				map.computeIfPresent(StructureFeature.JUNGLE_PYRAMID, (structureFeature, structureConfig) -> {
					return new StructureConfig(junglePyramidSpacing, junglePyramidSeparation, 14357619);
				});
				map.computeIfPresent(StructureFeature.PILLAGER_OUTPOST, (structureFeature, structureConfig) -> {
					return new StructureConfig(pillagerOutpostSpacing, pillagerOutpostSeparation, 165745296);
				});
				map.computeIfPresent(StructureFeature.END_CITY, (structureFeature, structureConfig) -> {
					return new StructureConfig(endCitySpacing, endCitySeparation, 10387313);
				});
				map.computeIfPresent(StructureFeature.MANSION, (structureFeature, structureConfig) -> {
					return new StructureConfig(mansionSpacing, mansionSeparation, 10387319);
				});
				map.computeIfPresent(StructureFeature.RUINED_PORTAL, (structureFeature, structureConfig) -> {
					return new StructureConfig(ruinedPortalSpacing, ruinedPortalSeparation, 34222645);
				});
				map.computeIfPresent(StructureFeature.SHIPWRECK, (structureFeature, structureConfig) -> {
					return new StructureConfig(shipwreckSpacing, shipwreckSeparation, 165745295);
				});
				map.computeIfPresent(StructureFeature.BASTION_REMNANT, (structureFeature, structureConfig) -> {
					return new StructureConfig(bastionSpacing, bastionSeparation, 30084232);
				});
				map.computeIfPresent(StructureFeature.FORTRESS, (structureFeature, structureConfig) -> {
					return new StructureConfig(fortressSpacing, fortressSeparation, 30084232);
				});

				ImmutableMap<StructureFeature<?>, StructureConfig> immutableMap = ImmutableMap.copyOf(map);

				((StructuresConfigAccessor)world.getChunkManager().getChunkGenerator().getConfig()).setStructures(immutableMap);
			});

			info("Initialized structure spawn rates, structures are now more common.");
		}
	}

	private static int villageSpacing;
	private static int villageSeparation;
	private static int desertPyramidSpacing;
	private static int desertPyramidSeparation;
	private static int junglePyramidSpacing;
	private static int junglePyramidSeparation;
	private static int pillagerOutpostSpacing;
	private static int pillagerOutpostSeparation;
	private static int endCitySpacing;
	private static int endCitySeparation;
	private static int mansionSpacing;
	private static int mansionSeparation;
	private static int ruinedPortalSpacing;
	private static int ruinedPortalSeparation;
	private static int shipwreckSpacing;
	private static int shipwreckSeparation;
	private static int bastionSpacing;
	private static int bastionSeparation;
	private static int fortressSpacing;
	private static int fortressSeparation;

	private static void setVillageStructureConfig(int spacing, int separation) {
		villageSpacing = spacing;
		villageSeparation = separation;
	}

	private static void setDesertPyramidStructureConfig(int spacing, int separation) {
		desertPyramidSpacing = spacing;
		desertPyramidSeparation = separation;
	}

	private static void setJunglePyramidStructureConfig(int spacing, int separation) {
		junglePyramidSpacing = spacing;
		junglePyramidSeparation = separation;
	}

	private static void setPillagerOutpostStructureConfig(int spacing, int separation) {
		pillagerOutpostSpacing = spacing;
		pillagerOutpostSeparation = separation;
	}

	private static void setEndCityStructureConfig(int spacing, int separation) {
		endCitySpacing = spacing;
		endCitySeparation = separation;
	}

	private static void setWoodlandMansionStructureConfig(int spacing, int separation) {
		mansionSpacing = spacing;
		mansionSeparation = separation;
	}

	private static void setRuinedPortalStructureConfig(int spacing, int separation) {
		ruinedPortalSpacing = spacing;
		ruinedPortalSeparation = separation;
	}

	private static void setShipwreckStructureConfig(int spacing, int separation) {
		shipwreckSpacing = spacing;
		shipwreckSeparation = separation;
	}

	private static void setBastionRemnantStructureConfig(int spacing, int separation) {
		bastionSpacing = spacing;
		bastionSeparation = separation;
	}

	private static void setNetherFortressStructureConfig(int spacing, int separation) {
		fortressSpacing = spacing;
		fortressSeparation = separation;
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